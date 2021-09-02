/*
 * Copyright (c) 2021 Zavarov.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package zav.jrc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.databind.api.Credentials;
import zav.jrc.databind.api.Token;
import zav.jrc.databind.api.UserAgent;
import zav.jrc.http.RestRequest;
import zav.jrc.http.exception.ForbiddenException;
import zav.jrc.http.exception.HttpException;
import zav.jrc.http.exception.NotFoundException;
import zav.jrc.http.exception.RateLimiterException;
import zav.jrc.http.exception.UnauthorizedException;

/**
 * The base class for authenticating the application.<p/>
 * This class should be inherited by the clients implementing the individual authenticating methods
 * (e.g. via password, userless, etc.).<p/>
 * It is responsible for requesting and invalidating the access token, as well as performing all
 * API requests.
 */
public abstract class Client {
  @NonNull
  private static final Logger LOGGER = LogManager.getLogger(Client.class);
  @Inject
  protected UserAgent userAgent;
  @Inject
  protected Credentials credentials;
  @Inject
  protected RateLimiter rateLimiter;
  @Inject
  protected OkHttpClient http;
  @Nullable
  protected Token token;

  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    send                                                                                      //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//

  /**
   * This function has two purposes. The primary purpose is to execute the provided {@link Request}.
   * However it also checks if the current access token is still valid. In case it expired, a new
   * one will be fetched automatically.
   *
   * @param request The request transmitted to Reddit.
   * @return The HTTP {@link Response} corresponding to the {@link Request}.
   * @throws InterruptedException If the query got interrupted while waiting to be executed.
   * @throws IOException If the request couldn't be completed.
   */
  public synchronized String send(Request request) throws IOException, InterruptedException {
    Objects.requireNonNull(token);
    
    //Make sure that the token is still valid
    if (token.isExpired()) {
      refresh();
    }
    
    return _send(request);
  }

  /**
   * This method serves three purposes. The primary purpose is to execute the provided
   * {@link Request}. In addition, it also makes sure that all requests are made within the rate
   * limit and, if necessary, waits until the next {@link Request} can be made.<p/>
   * It also checks if the {@link Request} was accepted and, upon error, throws the corresponding
   * exception.<p/>
   * This method should <b>NEVER</b> be used anywhere outside the {@code login()} and
   * {@code refresh} methods as it bypasses the token validation.
   *
   * @param request The request transmitted to Reddit.
   * @return The HTTP {@link Response} corresponding to the {@link Request}.
   * @throws InterruptedException If the query got interrupted while waiting to be executed.
   * @throws IOException If the request couldn't be completed.
   */
  protected synchronized String _send(Request request) throws InterruptedException, IOException {
    //Wait if we're making too many requests at once
    rateLimiter.acquire();
    
    LOGGER.debug("--> {}", request);
    Response response = http.newCall(request).execute();
    rateLimiter.update(response);
    LOGGER.debug("<-- {}", response);
    LOGGER.debug("{} used, {} remain, {} seconds until next period", rateLimiter.getUsed(), rateLimiter.getRemaining(), rateLimiter.getReset());
    
    if (!response.isSuccessful()) {
      switch (response.code()) {
        case 401:
          //Unauthorized
          throw new UnauthorizedException(response.code(), response.message());
        case 403:
          //Forbidden
          throw new ForbiddenException(response.code(), response.message());
        case 404:
          //Not Found
          throw new NotFoundException(response.code(), response.message());
        case 429:
          //Too Many Requests
          throw new RateLimiterException(response.code(), response.message());
        default:
          throw new HttpException(response.code(), response.message());
      }
    }
    
    ResponseBody responseBody = response.body();
    return Objects.requireNonNull(responseBody).string();
  }

  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Login                                                                                     //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//

  public abstract void login(Duration duration) throws IOException, InterruptedException;

  /**
   * Requests a new access and refresh token.
   *
   * @throws InterruptedException If the query got interrupted while waiting to be executed.
   * @throws IOException If the request couldn't be completed.
   */
  public void login() throws InterruptedException, IOException {
    LOGGER.info("Request token.");
    login(Duration.PERMANENT);
  }

  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Refresh                                                                                   //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//

  /**
   * Requests a new access token.
   *
   * @throws InterruptedException If the query got interrupted while waiting to be executed.
   * @throws IOException If the request couldn't be completed.
   */
  public synchronized void refresh() throws IOException, InterruptedException {
    LOGGER.info("Refresh access token.");
    Objects.requireNonNull(token);
    Objects.requireNonNull(token.getRefreshToken());

    Map<Object, Object> body = new HashMap<>();
    body.put("grant_type", GrantType.REFRESH);
    body.put("refresh_token", token.getRefreshToken());
    
    Request request = new RestRequest.Builder()
          .setHost(RestRequest.WWW)
          .setEndpoint(OAuth2.ACCESS_TOKEN)
          .setBody(body, RestRequest.BodyType.FORM)
          .addHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials)
          .addHeader(HttpHeaders.USER_AGENT, userAgent.toString())
          .build()
          .post();

    //_send(...) -> Skip token validation
    ObjectMapper om = new ObjectMapper();
    token = om.readValue(_send(request), Token.class);
  }

  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Logout                                                                                    //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Invalidates both the access and (if present) the refresh token.<p/>
   * Returns immediately in case the application isn't authenticated.
   *
   * @throws InterruptedException If the query got interrupted while waiting to be executed.
   * @throws IOException If the request couldn't be completed.
   */
  public void logout() throws IOException, InterruptedException {
    try {
      revokeAccessToken();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      revokeRefreshToken();
    }
  }

  /**
   * A helper method invalidating the refresh token, if present.
   *
   * @throws InterruptedException If the query got interrupted while waiting to be executed.
   * @throws IOException If the request couldn't be completed.
   */
  private void revokeRefreshToken() throws IOException, InterruptedException {
    if (token == null || token.getRefreshToken() == null) {
      return;
    }
  
    LOGGER.info("Revoke refresh token.");

    Map<Object, Object> body = new HashMap<>();
    body.put("token", token.getRefreshToken());
    body.put("token_type_hint", TokenType.REFRESH_TOKEN);

    Request request = new RestRequest.Builder()
          .setHost(RestRequest.WWW)
          .setEndpoint(OAuth2.REVOKE_TOKEN)
          .setBody(body, RestRequest.BodyType.FORM)
          .addHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials)
          .addHeader(HttpHeaders.USER_AGENT, userAgent.toString())
          .build()
          .post();

    //Skip token validation
    _send(request);
  }

  /**
   * A helper method invalidating the access token.
   *
   * @throws InterruptedException If the query got interrupted while waiting to be executed.
   * @throws IOException If the request couldn't be completed.
   */
  private void revokeAccessToken() throws IOException, InterruptedException {
    if (token == null) {
      return;
    }
    
    LOGGER.info("Revoke access token.");

    Map<Object, Object> body = new HashMap<>();
    body.put("token", token.getAccessToken());
    body.put("token_type_hint", TokenType.ACCESS_TOKEN);

    Request request = new RestRequest.Builder()
          .setHost(RestRequest.WWW)
          .setEndpoint(OAuth2.REVOKE_TOKEN)
          .setBody(body, RestRequest.BodyType.FORM)
          .addHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials)
          .addHeader(HttpHeaders.USER_AGENT, userAgent.toString())
          .build()
          .post();

    //Skip token validation
    _send(request);
  }

  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    request                                                                                   //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Creates a new builder instance and initializes it with the {@link HttpHeaders#AUTHORIZATION}
   * and {@link HttpHeaders#USER_AGENT} headers, based on the configuration files.
   *
   * @return A new builder instance for a REST request.
   */
  public RestRequest.Builder newRequest() {
    Objects.requireNonNull(token);
    
    return new RestRequest.Builder()
          .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken())
          .addHeader(HttpHeaders.USER_AGENT, userAgent.toString());
  }
}
