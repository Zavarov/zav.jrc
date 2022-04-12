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

/**
 * Specifies the type of authentication that is used when requesting an access token.<br>
 * Depending on the type of authentication, different endpoints may become available. e.g. an
 * application without user context is unable to use any of the endpoints related to an account.
 */
public enum GrantType {
  USERLESS("https://oauth.reddit.com/grants/installed_client"),
  PASSWORD("password"),
  CLIENT("client_credentials"),
  REFRESH("refresh_token");
  
  private final String value;

  GrantType(String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
    return value;
  }
}