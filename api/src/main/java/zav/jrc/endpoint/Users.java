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

/**
 * REST endpoint for the {@code Users} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_users">here</a>
 */
public class Users {
  /**
   * For blocking a user.
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_block_user">here</a>
   */
  public static final Endpoint POST_API_BLOCK_USER =
        new Endpoint("api", "block_user");
  /**
   * Create a relationship between a user and another user or subreddit.<p/>
   * OAuth2 use requires appropriate scope based on the 'type' of the relationship:
   * <ul>
   *   <li>moderator: Use "moderator_invite"</li>
   *   <li>moderator_invite: modothers</li>
   *   <li>contributor: modcontributors</li>
   *   <li>banned: modcontributors</li>
   *   <li>muted: modcontributors</li>
   *   <li>wikibanned: modcontributors and modwiki</li>
   *   <li>wikicontributor: modcontributors and modwiki</li>
   *   <li>friend: Use /api/v1/me/friends/{username}</li>
   *   <li>enemy: Use /api/block</li>
   * </ul>
   * Complement to #POST_SUBREDDIT_UNFRIEND
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_friend">here</a>
   * @see #POST_R_SUBREDDIT_API_UNFRIEND
   */
  public static final Endpoint POST_R_SUBREDDIT_API_FRIEND =
        new Endpoint("r", "{subreddit}", "api", "friend");
  /**
   * Create a relationship between a user and another user or subreddit.<p/>
   * OAuth2 use requires appropriate scope based on the 'type' of the relationship:
   * <ul>
   *   <li>moderator: Use "moderator_invite"</li>
   *   <li>moderator_invite: modothers</li>
   *   <li>contributor: modcontributors</li>
   *   <li>banned: modcontributors</li>
   *   <li>muted: modcontributors</li>
   *   <li>wikibanned: modcontributors and modwiki</li>
   *   <li>wikicontributor: modcontributors and modwiki</li>
   *   <li>friend: Use /api/v1/me/friends/{username}</li>
   *   <li>enemy: Use /api/block</li>
   * </ul>
   * Complement to #POST_UNFRIEND
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_friend">here</a>
   * @see #POST_API_UNFRIEND
   */
  public static final Endpoint POST_API_FRIEND =
        new Endpoint("api", "friend");
  /**
   * Report a user. Reporting a user brings it to the attention of a Reddit admin.
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_report_user">here</a>
   */
  public static final Endpoint POST_API_REPORT_USER =
        new Endpoint("api", "report_user");
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_setpermissions">here</a>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_SETPERMISSION =
        new Endpoint("r", "{subreddit}", "api", "setpermission");
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_setpermissions">here</a>
   */
  public static final Endpoint POST_API_SETPERMISSION =
        new Endpoint("api", "setpermission");
  /**
   * Remove a relationship between a user and another user or subreddit.<p/>
   * The user can either be passed in by name (nuser) or by fullname (iuser). If type is friend or enemy, 'container'
   * MUST be the current user's fullname; for other types, the subreddit must be set via URL
   * (e.g., /r/funny/api/unfriend).<p/>
   * OAuth2 use requires appropriate scope based on the 'type' of the relationship:
   * <ul>
   *   <li>moderator: modothers</li>
   *   <li>moderator_invite: modothers</li>
   *   <li>contributor: modcontributors</li>
   *   <li>banned: modcontributors</li>
   *   <li>muted: modcontributors</li>
   *   <li>wikibanned: modcontributors and modwiki</li>
   *   <li>wikicontributor: modcontributors and modwiki</li>
   *   <li>friend: Use /api/v1/me/friends/{username}</li>
   *   <li>enemy: privatemessages<li>
   * </ul>
   * Complement to #POST_SUBREDDIT_FRIEND
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_unfriend">here</a>
   * @see #POST_R_SUBREDDIT_API_FRIEND
   */
  public static final Endpoint POST_R_SUBREDDIT_API_UNFRIEND =
        new Endpoint("r", "{subreddit}", "api", "unfriend");
  /**
   * Remove a relationship between a user and another user or subreddit.<p/>
   * The user can either be passed in by name (nuser) or by fullname (iuser). If type is friend or
   * enemy, 'container' MUST be the current user's fullname; for other types, the subreddit must be
   * set via URL (e.g., /r/funny/api/unfriend).<p/>
   * The user can either be passed in by name (nuser) or by fullname (iuser). If type is friend or
   * enemy, 'container' MUST be the current user's fullname; for other types, the subreddit must be
   * set via URL (e.g., /r/funny/api/unfriend).<p/>
   * OAuth2 use requires appropriate scope based on the 'type' of the relationship:
   * <ul>
   *   <li>moderator: modothers</li>
   *   <li>moderator_invite: modothers</li>
   *   <li>contributor: modcontributors</li>
   *   <li>banned: modcontributors</li>
   *   <li>muted: modcontributors</li>
   *   <li>wikibanned: modcontributors and modwiki</li>
   *   <li>wikicontributor: modcontributors and modwiki</li>
   *   <li>friend: Use /api/v1/me/friends/{username}</li>
   *   <li>enemy: privatemessages<li>
   * </ul>
   * Complement to #POST_FRIEND
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_unfriend">here</a>
   * @see #POST_API_FRIEND
   */
  public static final Endpoint POST_API_UNFRIEND =
        new Endpoint("api", "unfriend");
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_user_data_by_account_ids">here</a>
   */
  public static final Endpoint GET_API_USER_DATA_BY_ACCOUNT_IDS =
        new Endpoint("api", "user_data_by_account_ids");
  /**
   * Check whether a username is available for registration.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_username_available">here</a>
   */
  public static final Endpoint GET_API_USERNAME_AVAILABLE =
        new Endpoint("api", "username_available");
  /**
   * Stop being friends with a user.
   *
   * @see <a href="https://www.reddit.com/dev/api#DELETE_api_v1_me_friends_%28username%29">here</a>
   */
  public static final Endpoint DELETE_API_V1_ME_FRIENDS_USERNAME =
        new Endpoint("api", "v1", "me", "friends", "{username}");
  /**
   * Get information about a specific 'friend', such as notes.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_me_friends_%28username%29">here</a>
   */
  public static final Endpoint GET_API_V1_ME_FRIENDS_USERNAME =
        new Endpoint("api", "v1", "me", "friends", "{username}");
  /**
   * Create or update a "friend" relationship.<p/>
   * This operation is idempotent. It can be used to add a new friend, or update an existing friend
   * (e.g., add/change the note on that friend).
   *
   * @see <a href="https://www.reddit.com/dev/api#PUT_api_v1_me_friends_%28username%29">here</a>
   */
  public static final Endpoint PUT_API_V1_ME_FRIENDS_USERNAME =
        new Endpoint("api", "v1", "me", "friends", "{username}");
  /**
   * Return a list of trophies for the a given user.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_user_%28username%29_trophies">here</a>
   */
  public static final Endpoint GET_API_V1_USER_USERNAME_TROPHIES =
        new Endpoint("api", "v1", "user", "{username}", "trophies");
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_%28where%29">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_WHERE =
        new Endpoint("user", "{username}", "{where}");
  /**
   * Return information about the user, including karma and gold status.
   *
   * @see <a href="https://www.reddit.com/dev/api/oauth#GET_user_%28username%29_about">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_ABOUT =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "about"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_comments">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_COMMENTS =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "comments"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_downvoted">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_DOWNVOTED =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "downvoted"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_gilded">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_GILDED =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "gilded"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_hidden">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_HIDDEN =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "hidden"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_overview">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_OVERVIEW =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "overview"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_saved">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_SAVED =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "saved"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_submitted">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_SUBMITTED =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "submitted"));
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_user_%28username%29_upvoted">here</a>
   */
  public static final Endpoint GET_USER_USERNAME_UPVOTED =
        new Endpoint(GET_USER_USERNAME_WHERE.getPath("{username}", "upvoted"));
}
