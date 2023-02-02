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

package zav.jrc.listener.observer;

import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.client.FailedRequestException;
import zav.jrc.listener.GenericListener;

/**
 * Implements the observer pattern. Notifications to the observer are
 * automatically delegated to either all registered listeners or specific ones.
 *
 * @param <T> The type of listener that are observed by this class.
 */
@NonNullByDefault
public interface Observer<T> {
  boolean addListener(GenericListener<T> listener);

  boolean removeListener(GenericListener<T> listener);

  void notifyListener(GenericListener<T> listener) throws FailedRequestException;

  void notifyAllListeners() throws FailedRequestException;

  int size();
}
