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

package zav.jrc.api;

import com.google.inject.Injector;
import zav.jrc.api.internal.guice.AccountFactory;
import zav.jrc.api.internal.guice.JrcModule;
import zav.jrc.api.internal.guice.SubredditFactory;

import javax.inject.Inject;

public class Reddit {
  @Inject
  private Injector injector;
  
  public FrontPage getFrontPage() {
    return injector.getInstance(FrontPage.class);
  }
  
  public Account getAccount(String accountName) {
    return injector.createChildInjector(new JrcModule())
          .getInstance(AccountFactory.class).create(accountName);
  }
  
  public SelfAccount getSelfAccount() {
    return injector.getInstance(SelfAccount.class);
  }
  
  public Subreddit getSubreddit(String subredditName) {
    return injector.createChildInjector(new JrcModule())
          .getInstance(SubredditFactory.class).create(subredditName);
  }
}
