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

package zav.jrc.listener.guice;

import com.google.inject.name.Names;
import zav.jrc.client.Client;
import zav.jrc.databind.io.CredentialsValueObject;
import zav.jrc.databind.io.UserAgentValueObject;
import zav.jrc.client.guice.JrcModule;

import java.util.UUID;

public class ClientMockModule extends JrcModule {
  @Override
  protected void configure() {
    UserAgentValueObject userAgent = new UserAgentValueObject();
    CredentialsValueObject credentials = new CredentialsValueObject();
    UUID uuid = UUID.randomUUID();
    
    bind(UUID.class).toInstance(uuid);
    bind(UserAgentValueObject.class).toInstance(userAgent);
    bind(CredentialsValueObject.class).toInstance(credentials);
    bind(String.class).annotatedWith(Names.named("username")).toInstance("user");
    bind(String.class).annotatedWith(Names.named("password")).toInstance("pw");
    bind(Client.class).to(ClientMock.class);
  }
}
