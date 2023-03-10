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

package zav.jrc.client.http;

import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * This exception is thrown whenever the API rejected a request.
 */
@NonNullByDefault
public class ResponseException extends Exception {
  private static final long serialVersionUID = 1L;

  public ResponseException(List<String> errors) {
    super(errors.stream().collect(Collectors.joining(System.lineSeparator())));
  }
}
