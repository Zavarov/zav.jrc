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

import java.util.EventListener;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.client.FailedRequestException;

/**
 * This class provides a skeleton implementation of the observer interface. All observers should
 * inherit from this class.
 *
 * @param <T> The type of listeners that are observed by this class.
 */
@NonNullByDefault
public abstract class AbstractObserver<T extends EventListener> implements Observer<T> {
  private final Set<T> listeners = new CopyOnWriteArraySet<>();

  @Override
  public boolean addListener(T listener) {
    return listeners.add(listener);
  }

  @Override
  public boolean removeListener(T listener) {
    return listeners.remove(listener);
  }

  @Override
  public void notifyAllListeners() throws FailedRequestException {
    for (T listener : listeners) {
      this.notifyListener(listener);
    }
  }

  @Override
  public int size() {
    return listeners.size();
  }
}
