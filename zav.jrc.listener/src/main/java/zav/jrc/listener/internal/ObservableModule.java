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

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import zav.jrc.listener.observer.Observer;
import zav.jrc.listener.observer.SubredditObserver;

/**
 * The Guice module responsible for creating the {@link Observer} instances.
 */
public class ObservableModule extends AbstractModule {
  @Override
  protected void configure() {
    com.google.inject.Module module = new FactoryModuleBuilder()
          .implement(SubredditObserver.class, SubredditObserver.class)
          .build(SubredditObserverFactory.class);
    
    install(module);
  }
}
