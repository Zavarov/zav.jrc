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

package zav.jrc.listener;

import java.util.EventListener;
import org.eclipse.jdt.annotation.NonNull;
import zav.jrc.databind.Link;
import zav.jrc.databind.Subreddit;

/**
 * This class handles all events associated with a given {@link Subreddit}.
 */
@NonNull
public interface SubredditListener extends EventListener {
  default void handle(@NonNull Link link) {
  }
}
