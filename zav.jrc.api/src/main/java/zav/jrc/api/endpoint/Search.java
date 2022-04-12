/*
 * Copyright (c) 2022 Zavarov.
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

package zav.jrc.api.endpoint;

/**
 * REST endpoint for the {@code Search} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_search">here</a>
 */
public final class Search {
  /**
   * Search links page.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_search">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_SEARCH =
        new Endpoint("r", "{subreddit}", "search");
  /**
   * Search links page.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_search">here</a>
   */
  public static final Endpoint GET_SEARCH =
        new Endpoint("search");
}
