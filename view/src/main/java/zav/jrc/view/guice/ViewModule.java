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

package zav.jrc.view.guice;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import zav.jrc.view.AccountView;
import zav.jrc.view.FrontPageView;
import zav.jrc.view.SelfAccountView;
import zav.jrc.view.SubredditView;

public class ViewModule extends AbstractModule {
  
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder().implement(AccountView.class, AccountView.class).build(AccountViewFactory.class));
    install(new FactoryModuleBuilder().implement(SelfAccountView.class, SelfAccountView.class).build(SelfAccountViewFactory.class));
    install(new FactoryModuleBuilder().implement(SubredditView.class, SubredditView.class).build(SubredditViewFactory.class));
    install(new FactoryModuleBuilder().implement(FrontPageView.class, FrontPageView.class).build(FrontPageViewFactory.class));
  }
}
