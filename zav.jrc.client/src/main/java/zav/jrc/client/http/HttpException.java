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

package zav.jrc.client.http;

import java.io.IOException;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The base class for every exception.<br>
 * This exception is thrown when there is no more specific exception for the error code.
 */
@NonNullByDefault
public class HttpException extends IOException {
  private static final long serialVersionUID = 1L;
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
