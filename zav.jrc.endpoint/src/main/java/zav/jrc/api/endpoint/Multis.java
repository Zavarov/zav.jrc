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
 * REST endpoint for the {@code Multis} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_multis">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public final class Multis {
  /**
   * Copy a multi.<br>
   * Responds with 409 Conflict if the target already exists.<br>
   * A "copied from ..." line will automatically be appended to the description.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | description_md        | raw markdown text                                                   |
   * | display_name          | a string no longer than 50 characters                               |
   * | expand_srs            | boolean value                                                       |
   * | from                  | multireddit url path                                                |
   * | to                    | destination multireddit url path                                    |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_MULTI_COPY = new Endpoint("api", "multi", "copy");
  /**
   * Fetch a list of multis belonging to the current user.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | expand_srs            | boolean value                                                       |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_MULTI_MINE = new Endpoint("api", "multi", "mine");
  /**
   * Fetch a list of public multis belonging to {@code username}.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | expand_srs            | boolean value                                                       |
   * | username              | A valid, existing reddit username                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_MULTI_USER_USERNAME = new Endpoint("api", "multi", "user",
      "{username}");
  /**
   * Delete a multi.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | multipath             | multireddit url path                                                |
   * | uh / X-Modhash header | a modhash                                                           |
   * | expand_srs            | boolean value                                                       |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint DELETE_API_MULTI_MULTIPATH = new Endpoint("api", "multi",
      "{multipath}");
  /**
   * Fetch a multi's data and subreddit list by name.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | expand_srs            | boolean value                                                       |
   * | multipath             | multireddit url path                                                |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_MULTI_MULTIPATH = new Endpoint("api", "multi",
      "{multipath}");
  /**
   * Create a multi. Responds with 409 Conflict if it already exists.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | model                 | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "description_md": raw markdown text,                              |
   * |                       |   "display_name": a string no longer than 50 characters,            |
   * |                       |   "icon_img": one of (`png`, `jpg`, `jpeg`),                        |
   * |                       |   "key_color": a 6-digit rgb hex color, e.g. `#AABBCC`,             |
   * |                       |   "subreddits": [                                                   |
   * |                       |     {                                                               |
   * |                       |       "name": subreddit name,                                       |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "visibility": one of (`private`, `public`, `hidden`)              |
   * |                       | }                                                                   |
   * | multipath             | multireddit url path                                                |
   * | uh / X-Modhash header | a modhash                                                           |
   * | expand_srs            | boolean value                                                       |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_MULTI_MULTIPATH = new Endpoint("api", "multi",
      "{multipath}");
  /**
   * Create or update a multi.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | model                 | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "description_md": raw markdown text,                              |
   * |                       |   "display_name": a string no longer than 50 characters,            |
   * |                       |   "icon_img": one of (`png`, `jpg`, `jpeg`),                        |
   * |                       |   "key_color": a 6-digit rgb hex color, e.g. `#AABBCC`,             |
   * |                       |   "subreddits": [                                                   |
   * |                       |     {                                                               |
   * |                       |       "name": subreddit name,                                       |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "visibility": one of (`private`, `public`, `hidden`)              |
   * |                       | }                                                                   |
   * | multipath             | multireddit url path                                                |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PUT_API_MULTI_MULTIPATH = new Endpoint("api", "multi",
      "{multipath}");
  /**
   * Get a multi's description.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | multipath             | multireddit url path                                                |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_MULTI_MULTIPATH_DESCRIPTION = new Endpoint("api", "multi",
      "{multipath}", "description");
  /**
   * Change a multi's markdown description.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | model                 | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "body_md": raw markdown text                                      |
   * |                       | }                                                                   |
   * | multipath             | multireddit url path                                                |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PUT_API_MULTI_MULTIPATH_DESCRIPTION = new Endpoint("api", "multi",
      "{multipath}", "description");
  /**
   * Remove a subreddit from a multi.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | multipath             | multireddit url path                                                |
   * | srname                | subreddit name                                                      |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint DELETE_API_MULTI_MULTIPATH_R_SRNAME = new Endpoint("api", "multi",
      "{multipath}", "r", "{srname}");
  /**
   * Get data about a subreddit in a multi.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | multipath             | multireddit url path                                                |
   * | srname                | subreddit name                                                      |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_MULTI_MULTIPATH_R_SRNAME = new Endpoint("api", "multi",
      "{multipath}", "r", "{srname}");
  /**
   * Add a subreddit to a multi.
   * 
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | model                 | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "name": subreddit name                                            |
   * |                       | }                                                                   |
   * | multipath             | multireddit url path                                                |
   * | srname                | subreddit name                                                      |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PUT_API_MULTI_MULTIPATH_R_SRNAME = new Endpoint("api", "multi",
      "{multipath}", "r", "{srname}");
}
