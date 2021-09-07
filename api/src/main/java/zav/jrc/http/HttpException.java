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

package zav.jrc.http;

import java.io.IOException;

/**
 * The base class for every exception.<p/>
 * This exception is thrown when there is no more specific exception for the error code.
 */
public class HttpException extends IOException {
  private final int errorCode;
  
  public HttpException(int errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
  
  @Override
  public String toString() {
    return String.format("%d, %s", errorCode, getMessage());
  }
}
