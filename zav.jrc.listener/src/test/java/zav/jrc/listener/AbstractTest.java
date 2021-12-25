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

package zav.jrc.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.listener.guice.ListenerModule;
import zav.jrc.listener.guice.ClientMockModule;
import zav.jrc.api.guice.ViewModule;

public abstract class AbstractTest {
    protected static Injector GUICE;
    protected static Client CLIENT;
    
    @BeforeAll
    public static void setUpAll() throws FailedRequestException {
        GUICE = Guice.createInjector(new ClientMockModule(), new ViewModule(), new ListenerModule());
        CLIENT = GUICE.getInstance(Client.class);
        CLIENT.login(Duration.TEMPORARY);
    }
    
    @AfterAll
    public static void tearDown() throws FailedRequestException {
        CLIENT.logout();
    }
}
