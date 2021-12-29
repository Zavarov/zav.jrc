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

package zav.jrc.client.guice;

import static zav.jrc.client.guice.Names.PASSWORD;
import static zav.jrc.client.guice.Names.USERNAME;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import zav.jrc.databind.io.CredentialsDto;
import zav.jrc.databind.io.UserAgentDto;

/**
 * The Guice module responsible for reading and binding the credentials required for
 * authentication.
 */
public abstract class ClientModule extends AbstractModule {
  private static final URL USER_AGENT =
        ClientModule.class.getClassLoader().getResource("UserAgent.json");
  private static final URL CREDENTIALS =
        ClientModule.class.getClassLoader().getResource("Credentials.json");
  
  @Override
  protected void configure() {
    ObjectMapper om = new ObjectMapper();
    UserAgentDto userAgent;
    CredentialsDto credentials;
    UUID uuid;
    
    try {
      userAgent = om.readValue(USER_AGENT, UserAgentDto.class);
      credentials = om.readValue(CREDENTIALS, CredentialsDto.class);
      uuid = UUID.randomUUID();
    } catch (IOException e) {
      // Client can't log in without password, user agent, etc...
      throw new RuntimeException(e.getMessage(), e);
    }
    
    bind(UUID.class).toInstance(uuid);
    bind(UserAgentDto.class).toInstance(userAgent);
    bind(CredentialsDto.class).toInstance(credentials);
    
    if (credentials.getUsername() != null) {
      bind(String.class).annotatedWith(Names.named(USERNAME)).toInstance(credentials.getUsername());
    }
    
    if (credentials.getPassword() != null) {
      bind(String.class).annotatedWith(Names.named(PASSWORD)).toInstance(credentials.getPassword());
    }
  }
}
