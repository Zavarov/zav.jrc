/*
 * Copyright (c) 2023 Zavarov.
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

import java.util.EventObject;

/**
 * Base class for all object events. This event is used by all
 * {@link GenericListener}.
 *
 * @param <T> Then entity type contained by this event.
 */
public class GenericEvent<T> extends EventObject {
  private static final long serialVersionUID = 1L;

  public GenericEvent(T source) {
    super(source);
  }

  @SuppressWarnings("unchecked")
  public T getSource() {
    return (T) super.getSource();
  }
}
