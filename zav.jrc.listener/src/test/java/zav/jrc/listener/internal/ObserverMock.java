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

package zav.jrc.listener.internal;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.jdt.annotation.NonNull;
import zav.jrc.databind.LinkDto;
import zav.jrc.databind.SubredditDto;
import zav.jrc.listener.SubredditListener;
import zav.jrc.listener.observer.AbstractObserver;

import javax.inject.Inject;

public class ObserverMock extends AbstractObserver<SubredditListener> {
  @Inject
  private Injector injector;
  
  @Override
  public void notifyListener(@NonNull SubredditListener listener) {
    Module module = new ObserverModule(new SubredditDto(), new LinkDto());
    
    injector.createChildInjector(module).injectMembers(listener);
  }
}
