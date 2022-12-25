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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * REST endpoint for the {@code Wiki} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_wiki">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public class Wiki {
  /**
   * Allow/deny username to edit this wiki {@code page}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | act                   | one of (del, add)                                                   |
   * | page                  | the name of an existing wiki page                                   |
   * | uh / X-Modhash header | a modhash                                                           |
   * | username              | the name of an existing user                                        |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIKI_ALLOWEDIT_ACT =
        new Endpoint("r", "{subreddit}", "api", "wiki", "allowedit", "{act}");
  /**
   * Allow/deny username to edit this wiki {@code page}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | act                   | one of (del, add)                                                   |
   * | page                  | the name of an existing wiki page                                   |
   * | uh / X-Modhash header | a modhash                                                           |
   * | username              | the name of an existing user                                        |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIKI_ALLOWEDIT_DEL =
        new Endpoint(POST_R_SUBREDDIT_API_WIKI_ALLOWEDIT_ACT.getPath("{subreddit}", "del"));
  /**
   * Allow/deny username to edit this wiki {@code page}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | act                   | one of (del, add)                                                   |
   * | page                  | the name of an existing wiki page                                   |
   * | uh / X-Modhash header | a modhash                                                           |
   * | username              | the name of an existing user                                        |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIKI_ALLOWEDIT_ADD =
        new Endpoint(POST_R_SUBREDDIT_API_WIKI_ALLOWEDIT_ACT.getPath("{subreddit}", "add"));
  /**
   * Edit a wiki {@code}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | content               |                                                                     |
   * | page                  | the name of an existing page or a new page to create                |
   * | previous              | the starting point revision for this edit                           |
   * | reason                | a string up to 256 characters long, consisting of printable         |
   * |                       | characters.                                                         |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIKI_EDIT =
        new Endpoint("r", "{subreddit}", "api", "wiki", "edit");
  /**
   * Toggle the public visibility of a wiki page revision.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | page                  | the name of an existing wiki page                                   |
   * | previous              | the starting point revision for this edit                           |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIKI_HIDE =
        new Endpoint("r", "{subreddit}", "api", "wiki", "hide");
  /**
   * Revert a wiki {@code page} to {@code revision}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | page                  | the name of an existing wiki page                                   |
   * | previous              | the starting point revision for this edit                           |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIKI_REVERT =
        new Endpoint("r", "{subreddit}", "api", "wiki", "revert");
  /**
   * Retrieve a list of discussions about this wiki {@code page}.<br>
   * This endpoint is a listing.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | after                 | fullname of a thing                                                 |
   * | before                | fullname of a thing                                                 |
   * | count                 | a positive integer (default:0)                                      |
   * | limit                 | the maximum number of items desired (default:25, maximum:100)       |
   * | page                  | the name of an existing wiki page                                   |
   * | show                  | (optional) the string 'all'                                         |
   * | sr_detail             | (optional) expand subreddits                                        |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_WIKI_DISCUSSIONS_PAGE =
        new Endpoint("r", "{subreddit}", "wiki", "discussions", "{page}");
  /**
   * Retrieve a list of wiki pages in this subreddit.
   */
  public static final Endpoint GET_R_SUBREDDIT_WIKI_PAGES =
        new Endpoint("r", "{subreddit}", "wiki", "pages");
  /**
   * Retrieve a list of recently changed wiki pages in this subreddit.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | after                 | fullname of a thing                                                 |
   * | before                | fullname of a thing                                                 |
   * | count                 | a positive integer (default:0)                                      |
   * | limit                 | the maximum number of items desired (default:25, maximum:100)       |
   * | show                  | (optional) the string 'all'                                         |
   * | sr_detail             | (optional) expand subreddits                                        |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_WIKI_REVISIONS =
        new Endpoint("r", "{subreddit}", "wiki", "revisions");
  /**
   * Retrieve a list of revisions of this wiki {@code page}.<br>
   * This endpoint is a {@link ListingEntity Listing}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | after                 | fullname of a thing                                                 |
   * | before                | fullname of a thing                                                 |
   * | count                 | a positive integer (default:0)                                      |
   * | limit                 | the maximum number of items desired (default:25, maximum:100)       |
   * | page                  | the name of an existing wiki page                                   |
   * | show                  | (optional) the string 'all'                                         |
   * | sr_detail             | (optional) expand subreddits                                        |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_WIKI_REVISIONS_PAGE =
        new Endpoint("r", "{subreddit}", "wiki", "revisions", "{page}");
  /**
   * Retrieve the current permission settings for {@code page}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | page                  | the name of an existing wiki page                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_WIKI_SETTINGS_PAGE =
        new Endpoint("r", "{subreddit}", "wiki", "settings", "{page}");
  /**
   * Update the permissions and visibility of wiki {@code page}.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | listed                | boolean value                                                       |
   * | page                  | the name of an existing wiki page                                   |
   * | permlevel             | an integer                                                          |
   * | uh / X-Modhash header | a modhash                                                           |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_WIKI_SETTINGS_PAGE =
        new Endpoint("r", "{subreddit}", "wiki", "settings", "{page}");
  /**
   * Return the content of a wiki page.<br>
   * If {@code v} is given, show the wiki page as it was at that version If both {@code v} and
   * {@code v2} are given, show a diff of the two.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | page                  | the name of an existing wiki page                                   |
   * | v                     | a wiki revision ID                                                  |
   * | v2                    | a wiki revision ID                                                  |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_WIKI_PAGE =
        new Endpoint("r", "{subreddit}", "wiki", "{page}");
}
