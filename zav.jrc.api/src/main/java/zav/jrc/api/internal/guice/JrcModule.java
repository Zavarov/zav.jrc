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

package zav.jrc.api.internal.guice;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import zav.jrc.api.Account;
import zav.jrc.api.Subreddit;

/**
 * The Guice module responsible for creating the different views.
 */
public class JrcModule extends AbstractModule {
  @Override
  protected void configure() {
    install(module(Account.class, AccountFactory.class));
    install(module(Subreddit.class, SubredditFactory.class));
  }
  
  private static <T> com.google.inject.Module module(Class<T> source, Class<?> target) {
    return new FactoryModuleBuilder()
          .implement(source, source)
          .build(target);
  }
}
