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

package zav.jrc.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Request;
import org.eclipse.jdt.annotation.NonNull;
import zav.jrc.Client;
import zav.jrc.Duration;
import zav.jrc.FailedRequestException;
import zav.jrc.GrantType;
import zav.jrc.OAuth2;
import zav.jrc.databind.api.Token;
import zav.jrc.http.RestRequest;

/**
 * Runs on hardware you control, such as your own laptop or server. Can keep a secret. Only has
 * access to your account.</p>
 * The script therefore requires both your username and password.
 */
@Singleton // All requests have to go through a single client
public class ScriptClient extends Client {
  @Inject
  @Named("username")
  private String username;
  @Inject
  @Named("password")
  private String password;
  
  /**
   * Requests a new access token.<p/>
   * It seems like Reddit ignores the value of {@code duration} and never returns a refresh token
   * for scripts. Once the access token expires, a new one has to be requested.
   *
   * @param duration The lifetime of the token.
   * @throws FailedRequestException In case the request was rejected by the API.
   */
  @Override
  public void login(@NonNull Duration duration) throws FailedRequestException {
    Map<Object, Object> body = new HashMap<>();
    body.put("grant_type", GrantType.PASSWORD);
    body.put("username", username);
    body.put("password", password);
    body.put("duration", duration);
  
    Request request = new RestRequest.Builder()
          .setHost(RestRequest.WWW)
          .setEndpoint(OAuth2.ACCESS_TOKEN)
          .setBody(body, RestRequest.BodyType.FORM)
          .addHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials)
          .addHeader(HttpHeaders.USER_AGENT, userAgent.toString())
          .build()
          .post();
  
    //_send(...) -> Skip token validation
    try {
      ObjectMapper om = new ObjectMapper();
      token = om.readValue(_send(request), Token.class);
    } catch (IOException e) {
      throw FailedRequestException.wrap(e);
    }
  }
}
