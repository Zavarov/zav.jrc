/*
 * Copyright (c) 2023 Zavarov.
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

package zav.jrc.its;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.ScriptClient;
import zav.jrc.databind.io.CredentialsEntity;
import zav.jrc.databind.io.UserAgentEntity;

public abstract class AbstractIntegrationTest {
  private static Logger LOGGER = LoggerFactory.getLogger(AbstractIntegrationTest.class);
  protected static Client client;

  @BeforeAll
  public static void setUpAll() throws FailedRequestException {
    UserAgentEntity userAgent = new UserAgentEntity();
    userAgent.setAuthor("Zavarov");
    userAgent.setVersion("0.0.1-SNAPSHOT");
    userAgent.setName("JRC");
    userAgent.setPlatform("linux");

    String id = System.getenv("REDDIT_ID");
    if (id == null) {
      LOGGER.error("Reddit ID not contributed as enviroment variable!");
    }

    String secret = System.getenv("REDDIT_SECRET");
    if (secret == null) {
      LOGGER.error("Reddit Secret not contributed as enviroment variable!");
    }

    String username = System.getenv("REDDIT_USERNAME");
    if (username == null) {
      LOGGER.error("Reddit username not contributed as enviroment variable!");
    }

    String password = System.getenv("REDDIT_PASSWORD");
    if (password == null) {
      LOGGER.error("Reddit password not contributed as enviroment variable!");
    }

    CredentialsEntity credentials = new CredentialsEntity();
    credentials.setId(id);
    credentials.setSecret(secret);
    credentials.setUsername(username);
    credentials.setPassword(password);

    client = new ScriptClient(userAgent, credentials);
    client.login(Duration.TEMPORARY);
  }

  @AfterAll
  public static void tearDownAll() throws FailedRequestException {
    client.logout();
  }
}
