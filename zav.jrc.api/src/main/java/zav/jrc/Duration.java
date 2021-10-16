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

package zav.jrc;

import java.util.Locale;
import org.eclipse.jdt.annotation.NonNull;

/**
 * If {@link #PERMANENT} is selected, a refresh token is provided together with the access token
 * when authenticating the application. When the access token expires, this refresh token can
 * then be used to request a new access token.<br>
 * If {@link #TEMPORARY} is selected, only the access token is provided.
 */
@NonNull
public enum Duration {
  PERMANENT,
  TEMPORARY;
  
  @Override
  @NonNull
  public String toString() {
    return name().toLowerCase(Locale.ENGLISH);
  }
}