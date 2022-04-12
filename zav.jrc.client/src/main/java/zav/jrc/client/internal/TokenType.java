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

package zav.jrc.client.internal;

import java.util.Locale;

/**
 * The token type is used to inform Reddit about the kind of token that is transmitted. It is
 * required when refreshing the access token or invalidating already existing tokens.
 */
public enum TokenType {
  /**
   * The access token is required to authenticate the application when using the OAuth2 endpoints.
   */
  ACCESS_TOKEN,
  /**
   * The refresh token is required when requesting a new access token, once the previous one
   * expired.
   */
  REFRESH_TOKEN;
  
  /**
   * The resulting string can be used in the JSON request to identify the type of token.
   *
   * @return A string representation of the enum type.
   */
  @Override
  public String toString() {
    return name().toLowerCase(Locale.ENGLISH);
  }
}
