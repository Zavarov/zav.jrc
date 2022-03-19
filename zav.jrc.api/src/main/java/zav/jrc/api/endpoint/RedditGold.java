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
 * REST endpoint for the {@code Reddit Gold} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_gold">here</a>
 */
@SuppressWarnings("unused")
public final class RedditGold {
  /**
   * Gild a comment or submission.
   * <pre>
   * +---------------------+-----------------------------------------------------------------------+
   * | Parameter           | Description                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * | fullname            | fullname of a thing                                                   |
   * +---------------------+-----------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_V1_GOLD_GILD_FULLNAME =
        new Endpoint("api", "v1", "gold", "gild", "{fullname}");
  /**
   * Give gold to a user.
   * <pre>
   * +---------------------+-----------------------------------------------------------------------+
   * | Parameter           | Description                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * | months              | an integer between 1 and 36                                           |
   * | username            | A valid, existing reddit username                                     |
   * +---------------------+-----------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_V1_GOLD_GIVE_USERNAME =
        new Endpoint("api", "v1", "gold", "give", "{username}");
}
