/*
 * Copyright (c) 2022 Zavarov.
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

package zav.jrc.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.core.HttpHeaders;
import okhttp3.Request;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.client.http.RequestBuilder;
import zav.jrc.client.internal.GrantType;
import zav.jrc.client.internal.OAuth2;
import zav.jrc.databind.io.CredentialsEntity;
import zav.jrc.databind.io.TokenEntity;
import zav.jrc.databind.io.UserAgentEntity;

/**
 * Runs on hardware you control, such as your own laptop or server. Doesn't have a user context
 * and therefore can't keep a secret.<br>
 */
@NonNullByDefault
public class UserlessClient extends Client {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserlessClient.class);
  
  private final UUID uuid = UUID.randomUUID();

  /**
   * Userless clients require an access token.
   *
   * @param userAgent The user agent used for communicating with the REST api.
   * @param credentials The Reddit credentials used for authentication.
   */
  public UserlessClient(UserAgentEntity userAgent, CredentialsEntity credentials) {
    super(userAgent.toString(), credentials.toString());
  }
  
  /**
   * Requests a new access token.<br>
   * Depending on the value of {@code duration}, Reddit will also return a refresh token with which
   * the client is able to retrieve a new access token, once the old one expired.
   *
   * @param duration The lifetime of the token.
   * @throws FailedRequestException In case the request was rejected by the API.
   */
  @Override
  public void login(Duration duration) throws FailedRequestException {
    Map<Object, Object> body = new HashMap<>();
    body.put("grant_type", GrantType.USERLESS);
    body.put("device_id", uuid);
    body.put("duration", duration);
    
    // Using a permanent token for an userless client doesn't make much sense, as it has to send
    // its credentials anyway. Therefore, it would make more sense to request a new access token
    // directly.
    if (duration == Duration.PERMANENT) {
      LOGGER.warn("You're requesting a permanent token for an userless client. Are you sure?");
    } else {
      // Revoke the (temporary) access token before shutting down
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
          logout();
          LOGGER.info("Revoking access token.");
        } catch (Exception e) {
          LOGGER.error(e.getMessage(), e);
        }
      }));
    }
    
    Request request = new RequestBuilder()
          .setHost(RequestBuilder.WWW)
          .setEndpoint(OAuth2.ACCESS_TOKEN)
          .setBody(body, RequestBuilder.BodyType.FORM)
          .addHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials)
          .addHeader(HttpHeaders.USER_AGENT, userAgent)
          .post();
  
    //_send(...) -> Skip token validation
    String response = _send(request);
    
    try {
      token = TokenEntity.read(response);
    } catch (IOException e) {
      throw FailedRequestException.wrap(e);
    }
  }
  
  @Override
  public synchronized void refresh() throws FailedRequestException {
    login(Duration.TEMPORARY);
  }
}
