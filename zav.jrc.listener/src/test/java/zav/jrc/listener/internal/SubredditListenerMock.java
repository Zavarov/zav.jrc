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

import java.util.function.Consumer;
import javax.inject.Inject;
import zav.jrc.databind.LinkDto;
import zav.jrc.listener.SubredditListener;

/**
 * A listener mock that records every time it has been notified.
 */
public class SubredditListenerMock implements SubredditListener {
  private final Consumer<SubredditListener> consumer;

  public SubredditListenerMock(Consumer<SubredditListener> consumer) {
    this.consumer = consumer;
  }

  @Inject
  public void handle(LinkDto link) {
    consumer.accept(this);
  }
}
