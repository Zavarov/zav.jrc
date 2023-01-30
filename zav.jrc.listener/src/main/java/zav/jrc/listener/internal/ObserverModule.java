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
import zav.jrc.databind.LinkValueObject;
import zav.jrc.databind.SubredditValueObject;

public class ObserverModule extends AbstractModule {
  private final SubredditValueObject subreddit;
  private final LinkValueObject link;

  public ObserverModule(SubredditValueObject subreddit, LinkValueObject link) {
    this.subreddit = subreddit;
    this.link = link;
  }
  
  @Override
  protected void configure() {
    bind(SubredditValueObject.class).toInstance(subreddit);
    bind(LinkValueObject.class).toInstance(link);
  }
}
