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
import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.client.http.RequestBuilder;
import zav.jrc.client.internal.GrantType;
import zav.jrc.databind.io.CredentialsEntity;
import zav.jrc.databind.io.TokenEntity;
import zav.jrc.databind.io.UserAgentEntity;

/**
 * Runs on hardware you control, such as your own laptop or server. Can keep a secret. Only has
 * access to your account.<br>
 * The script therefore requires both your username and password.
 */
@NonNullByDefault
public class ScriptClient extends Client {
  private final String username;
  private final String password;

  /**
   * Script clients require a username and password.
   *
   * @param userAgent The user agent used for communicating with the REST api.
   * @param credentials The Reddit credentials used for authentication.
   */
  public ScriptClient(UserAgentEntity userAgent, CredentialsEntity credentials) {
    super(userAgent.toString(), credentials.toString());
    this.username = credentials.getUsername();
    this.password = credentials.getPassword();
  }
  
  /**
   * Requests a new access token.<br>
   * It seems like Reddit ignores the value of {@code duration} and never returns a refresh token
   * for scripts. Once the access token expires, a new one has to be requested.
   *
   * @param duration The lifetime of the token.
   * @throws FailedRequestException In case the request was rejected by the API.
   */
  @Override
  public void login(Duration duration) throws FailedRequestException {
    Map<Object, Object> body = new HashMap<>();
    body.put("grant_type", GrantType.PASSWORD);
    body.put("username", username);
    body.put("password", password);
    body.put("duration", duration);
  
    String response = newTokenRequest()
          .setBody(body, RequestBuilder.BodyType.FORM)
          .post();
  
  
    if (duration == Duration.TEMPORARY) {
      addShutdownHook();
    }
  
    try {
      token = TokenEntity.read(response);
    } catch (IOException e) {
      throw FailedRequestException.wrap(e);
    }
  }
}
