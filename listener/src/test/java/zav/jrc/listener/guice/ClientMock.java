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

import okhttp3.Request;
import zav.jrc.Client;
import zav.jrc.Duration;
import zav.jrc.FailedRequestException;
import zav.jrc.databind.api.Token;

import javax.inject.Singleton;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Singleton // Client is shared among all views
public class ClientMock extends Client {
  @Override
  public void login(Duration duration) {
    token = new Token();
  }
  
  @Override
  public void logout() {
    token = null;
  }
  
  @Override
  public void refresh() {
  }
  
  @Override
  public synchronized String send(Request request) throws FailedRequestException {
    throw FailedRequestException.wrap(new UnsupportedOperationException());
  }
}
