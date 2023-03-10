/*
 * Copyright (c) 2023 Zavarov
 * 
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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * This exception is thrown whenever a request was rejected by the API. Use
 * {@link Exception#getCause()} to get the reason why this exception was thrown.
 */
@NonNullByDefault
public class FailedRequestException extends Exception {
  private static final long serialVersionUID = 1L;

  private FailedRequestException(Exception cause) {
    super(cause);
  }

  public static FailedRequestException wrap(Exception cause) {
    return new FailedRequestException(cause);
  }
}
