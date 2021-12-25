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

package zav.jrc.listener.guice;

import org.eclipse.jdt.annotation.NonNull;
import zav.jrc.listener.observer.SubredditObserver;
import zav.jrc.api.Subreddit;

/**
 * Instances of {@link SubredditObserver} have to be created via this class rather than directly.
 * Otherwise Guice is unable obtain the arguments required by the constructor.
 */
@NonNull
public interface SubredditObserverFactory {
  @NonNull
  SubredditObserver create(@NonNull Subreddit subreddit);
}
