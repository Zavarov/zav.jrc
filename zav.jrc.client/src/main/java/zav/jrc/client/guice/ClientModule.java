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

package zav.jrc.client.guice;

import static zav.jrc.client.guice.Names.PASSWORD;
import static zav.jrc.client.guice.Names.USERNAME;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import java.io.File;
import java.io.IOException;
import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.databind.io.CredentialsEntity;
import zav.jrc.databind.io.UserAgentEntity;

/**
 * The Guice module responsible for reading and binding the credentials required for
 * authentication.
 */
@NonNullByDefault
public abstract class ClientModule extends AbstractModule {
  private static final File CONFIG = new File("zav-jrc-config");
  private static final File USER_AGENT = new File(CONFIG, "UserAgent.json");
  private static final File CREDENTIALS = new File(CONFIG, "Credentials.json");
  
  @Override
  protected void configure() {
    ObjectMapper om = new ObjectMapper();
    UserAgentEntity userAgent;
    CredentialsEntity credentials;
    
    try {
      userAgent = om.readValue(USER_AGENT, UserAgentEntity.class);
      credentials = om.readValue(CREDENTIALS, CredentialsEntity.class);
    } catch (IOException e) {
      // Client can't log in without password, user agent, etc...
      throw new RuntimeException(e.getMessage(), e);
    }
    
    bind(UserAgentEntity.class).toInstance(userAgent);
    bind(CredentialsEntity.class).toInstance(credentials);
    
    if (credentials.getUsername() != null) {
      bind(String.class).annotatedWith(Names.named(USERNAME)).toInstance(credentials.getUsername());
    }
    
    if (credentials.getPassword() != null) {
      bind(String.class).annotatedWith(Names.named(PASSWORD)).toInstance(credentials.getPassword());
    }
  }
}
