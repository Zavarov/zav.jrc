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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Parameters are used to provide additional information to the endpoint. Examples for this are
 * the index when requesting stickied posts or the number of elements that should be returned.<br>
 * Each parameter consists of a keyword, as well as a value that is assigned to it.
 */
@NonNullByDefault
public class Parameter {
  private final Object key;
  private final Object value;

  public Parameter(Object key, Object value) {
    this.key = key;
    this.value = value;
  }

  public Object getKey() {
    return key;
  }

  public Object getValue() {
    return value;
  }
}
