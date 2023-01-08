/*
 * Copyright (c) 2023 Zavarov
 * 
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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * REST endpoint for the {@code Misc} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_misc">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public final class Misc {
  /**
   * Retrieve the advisory text about saving media for relevant media links.<br>
   * This endpoint returns a notice for display during the post submission process
   * that is pertinent to media links.
   * 
   * <pre>
   * +---------------------+-----------------------------------------------------------------------+
   * | Parameter           | Description                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * | url                 | a valid URL                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_API_SAVED_MEDIA = new Endpoint("r", "{subreddit}",
      "api", "saved_media_text");
  /**
   * Retrieve descriptions of reddit's OAuth2 scopes.<br>
   * If no scopes are given, information on all scopes are returned.<br>
   * Invalid scope(s) will result in a 400 error with body that indicates the
   * invalid scope(s).
   * 
   * <pre>
   * +---------------------+-----------------------------------------------------------------------+
   * | Parameter           | Description                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * | scopes              | (optional) An OAuth2 scope string                                     |
   * +---------------------+-----------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_V1_SCOPES = new Endpoint("api", "v1", "scopes");
}
