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

package zav.jrc.endpoint;

import zav.jrc.Endpoint;
import zav.jrc.databind.KarmaList;
import zav.jrc.databind.Messaging;
import zav.jrc.databind.Preferences;
import zav.jrc.databind.SelfAccount;
import zav.jrc.databind.TrophyList;
import zav.jrc.databind.UserList;

/**
 * REST endpoint for the {@code Account}.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_account">here</a>
 */
public final class Account {
  private Account() {}
  
  /**
   * The identity of the authenticated user.<p/>
   * This endpoint is a {@link SelfAccount}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_me">here</a>
   */
  public static final Endpoint GET_API_V1_ME =
        new Endpoint("api", "v1", "me");
  /**
   * Returns all blocked users.<p/>
   * This endpoint is a {@link UserList}.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th>/tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_me_blocked">here</a>
   * @deprecated Deprecated in favor of {@link #GET_PREFS_BLOCKED}.
   */
  @Deprecated
  public static final Endpoint GET_API_V1_ME_BLOCKED =
        new Endpoint("api", "v1", "me", "blocked");
  /**
   * Returns all friends.<p/>
   * This endpoint is a {@link UserList}.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th>/tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_me_friends">here</a>
   * @deprecated Deprecated in favor of {@link #GET_PREFS_FRIENDS}.
   */
  @Deprecated
  public static final Endpoint GET_API_V1_ME_FRIENDS =
        new Endpoint("api", "v1", "me", "friends");
  /**
   * Return a breakdown of subreddit karma.<p/>
   * This endpoint is a {@link KarmaList}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_me_karma">here</a>
   */
  public static final Endpoint GET_API_V1_ME_KARMA =
        new Endpoint("api", "v1", "me", "karma");
  /**
   * Return the preference settings of the logged in user.<p/>
   * This endpoint is a {@link Preferences}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_me_prefs">here</a>
   */
  public static final Endpoint GET_API_V1_ME_PREFS =
        new Endpoint("api", "v1", "me", "prefs");
  /**
   * Update the preference settings of the logged in user.<p/>
   *
   * @see <a href="https://www.reddit.com/dev/api#PATCH_api_v1_me_prefs">here</a>
   */
  public static final Endpoint PATCH_API_V1_ME_PREFS =
        new Endpoint("api", "v1", "me", "prefs");
  /**
   * Return a list of trophies for the current user.<p/>
   * This endpoint is a {@link TrophyList}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_me_trophies">here</a>
   */
  public static final Endpoint GET_API_V1_ME_TROPHIES =
        new Endpoint("api", "v1", "me", "trophies");
  /**
   * The endpoint for all user preferences.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th>/tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_prefs_%28where%29">here</a>
   */
  public static final Endpoint GET_PREFS_WHERE =
        new Endpoint("prefs", "{where}");
  /**
   * Returns all blocked users.<p/>
   * This endpoint is a {@link UserList}.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th>/tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_prefs_blocked">here</a>
   */
  public static final Endpoint GET_PREFS_BLOCKED =
        new Endpoint(GET_PREFS_WHERE.getPath("blocked"));
  /**
   * Returns all friends.<p/>
   * This endpoint is a {@link UserList}.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th>/tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_prefs_friends">here</a>
   */
  public static final Endpoint GET_PREFS_FRIENDS =
        new Endpoint(GET_PREFS_WHERE.getPath("friends"));
  /**
   * Returns all blocked and trusted users.<p/>
   * This endpoint is a {@link Messaging}.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_prefs_messaging">here</a>
   */
  public static final Endpoint GET_PREFS_MESSAGING =
        new Endpoint(GET_PREFS_WHERE.getPath("messaging"));
  /**
   * Returns all trusted users.<p/>
   * This endpoint is a {@link UserList}.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th>/tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_prefs_trusted">here</a>
   */
  public static final Endpoint GET_PREFS_TRUSTED =
        new Endpoint(GET_PREFS_WHERE.getPath("trusted"));
}
