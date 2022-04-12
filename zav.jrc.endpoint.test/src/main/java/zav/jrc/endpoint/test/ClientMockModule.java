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

package zav.jrc.endpoint.test;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import java.util.UUID;
import zav.jrc.client.Client;
import zav.jrc.databind.io.CredentialsEntity;
import zav.jrc.databind.io.UserAgentEntity;

/**
 * Guice module to bind the mock client to the base client class.<br>
 * This is required s.t. it can be used during testing.
 */
public class ClientMockModule extends AbstractModule {
  @Override
  protected void configure() {
    UserAgentEntity userAgent = new UserAgentEntity();
    CredentialsEntity credentials = new CredentialsEntity();
    UUID uuid = UUID.randomUUID();
    
    bind(UUID.class).toInstance(uuid);
    bind(UserAgentEntity.class).toInstance(userAgent);
    bind(CredentialsEntity.class).toInstance(credentials);
    bind(String.class).annotatedWith(Names.named("username")).toInstance("user");
    bind(String.class).annotatedWith(Names.named("password")).toInstance("pw");
    bind(Client.class).to(ClientMock.class);
  }
}