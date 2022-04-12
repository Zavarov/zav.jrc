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
 * REST endpoint for the {@code Flair} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_flair">here</a>
 */
@SuppressWarnings("unused")
public final class Flair {
  /**
   * Removes all user/link flairs.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_CLEARFLAIRTEMPLATES =
        new Endpoint("r", "{subreddit}", "api", "clearflairtemplates");
  /**
   * Removes all user/link flairs.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_CLEARFLAIRTEMPLATES =
        new Endpoint("api", "clearflairtemplates");
  /**
   * Removes a user flair.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | name                           | a user by name                                             |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_DELETEFLAIR =
        new Endpoint("r", "{subreddit}", "api", "deleteflair");
  /**
   * Removes a user flair.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | name                           | a user by name                                             |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_DELETEFLAIR =
        new Endpoint("api", "deleteflair");
  /**
   * Deletes a flair template.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_template_id              |                                                            |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_DELETEFLAIRTEMPLATE =
        new Endpoint("r", "{subreddit}", "api", "deleteflairtemplate");
  /**
   * Deletes a flair template.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_template_id              |                                                            |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_DELETEFLAIRTEMPLATE =
        new Endpoint("api", "deleteflairtemplate");
  /**
   * Sets the user flair.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | css_class                      | a valid subreddit image name                               |
   * | link                           | a fullname of a link                                       |
   * | name                           | a user by name                                             |
   * | text                           | a string no longer than 64 characters                      |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_FLAIR =
        new Endpoint("r", "{subreddit}", "api", "flair");
  /**
   * Sets the user flair.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | css_class                      | a valid subreddit image name                               |
   * | link                           | a fullname of a link                                       |
   * | name                           | a user by name                                             |
   * | text                           | a string no longer than 64 characters                      |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_FLAIR =
        new Endpoint("api", "flair");
  /**
   * Update the order of flair templates in the specified subreddit.<br>
   * Order should contain every single flair id for that flair type; omitting any id will result in
   * a loss of data.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | subreddit                      | subreddit name                                             |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PATCH_R_SUBREDDIT_API_FLAIR_TEMPLATE_ORDER =
        new Endpoint("r", "{subreddit}", "api", "flair_template_order");
  /**
   * Update the order of flair templates in the specified subreddit.<br>
   * Order should contain every single flair id for that flair type; omitting any id will result in
   * a loss of data.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | subreddit                      | subreddit name                                             |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PATCH_API_FLAIR_TEMPLATE_ORDER =
        new Endpoint("api", "flair_template_order");
  /**
   * Update the flair configuration.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_enabled                  | boolean value                                              |
   * | flair_position                 | one of (left, right)                                       |
   * | flair_self_assign_enabled      | boolean value                                              |
   * | link_flair_position            | one of (left,right)                                        |
   * | link_flair_self_assign_enabled | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_FLAIRCONFIG =
        new Endpoint("r", "{subreddit}", "api", "flairconfig");
  /**
   * Update the flair configuration.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_enabled                  | boolean value                                              |
   * | flair_position                 | one of (left, right)                                       |
   * | flair_self_assign_enabled      | boolean value                                              |
   * | link_flair_position            | one of (left,right)                                        |
   * | link_flair_self_assign_enabled | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_FLAIRCONFIG =
        new Endpoint("api", "flairconfig");
  /**
   * Change the flair of multiple users in the same subreddit with a single API call.<br>
   * Requires a string 'flair_csv' which has up to 100 lines of the form 'user,flairtext,cssclass'
   * (Lines beyond the 100th are ignored).<br>
   * If both {@code cssclass} and {@code flairtext} are the empty string for a given user, instead
   * clears that user's flair.<br>
   * Returns an array of objects indicating if each flair setting was applied, or a reason for the
   * failure.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | flair_csv                      | comma-separated flair information                          |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_FLAIRCSV =
        new Endpoint("r", "{subreddit}", "api", "flaircsv");
  /**
   * Change the flair of multiple users in the same subreddit with a single API call.<br>
   * Requires a string 'flair_csv' which has up to 100 lines of the form 'user,flairtext,cssclass'
   * (Lines beyond the 100th are ignored).<br>
   * If both {@code cssclass} and {@code flairtext} are the empty string for a given user, instead
   * clears that user's flair.<br>
   * Returns an array of objects indicating if each flair setting was applied, or a reason for the
   * failure.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | flair_csv                      | comma-separated flair information                          |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_FLAIRCSV =
        new Endpoint("api", "flaircsv");
  /**
   * This endpoint is a listing.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | after                          | fullname of a thing                                        |
   * | before                         | fullname of a thing                                        |
   * | count                          | a positive integer (default: 0)n                           |
   * | limit                          | the maximum number of items desired                        |
   * |                                | (default: 25, maximum: 1000)                               |
   * | name                           | a user by name                                             |
   * | show                           | (optional) the string all                                  |
   * | sr_detail                      | (optional) expand subreddits                               |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_API_FLAIRLIST =
        new Endpoint("r", "{subreddit}", "api", "flairlist");
  /**
   * This endpoint is a listing.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | after                          | fullname of a thing                                        |
   * | before                         | fullname of a thing                                        |
   * | count                          | a positive integer (default: 0)n                           |
   * | limit                          | the maximum number of items desired                        |
   * |                                | (default: 25, maximum: 1000)                               |
   * | name                           | a user by name                                             |
   * | show                           | (optional) the string all                                  |
   * | sr_detail                      | (optional) expand subreddits                               |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_FLAIRLIST =
        new Endpoint("api", "flairlist");
  /**
   * Return information about a users' flair options.<br>
   * If {@code link} is given, return link flair options for an existing link. If {@code is_newlink}
   * is {@code True}, return link flairs options for a new link submission. Otherwise, return user
   * flair options for this subreddit.<br>
   * The logged-in user's flair is also returned. Subreddit moderators may give a user by
   * {@code name} to instead retrieve that user's flair.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | is_newlink                     | boolean value                                              |
   * | link                           | a fullname of a link                                       |
   * | name                           | a user by name                                             |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_FLAIRSELECTOR =
        new Endpoint("r", "{subreddit}", "api", "flairselector");
  /**
   * Return information about a users' flair options.<br>
   * If {@code link} is given, return link flair options for an existing link. If {@code is_newlink}
   * is {@code True}, return link flairs options for a new link submission. Otherwise, return user
   * flair options for this subreddit.<br>
   * The logged-in user's flair is also returned. Subreddit moderators may give a user by
   * {@code name} to instead retrieve that user's flair.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | is_newlink                     | boolean value                                              |
   * | link                           | a fullname of a link                                       |
   * | name                           | a user by name                                             |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_FLAIRSELECTOR =
        new Endpoint("api", "flairselector");
  /**
   * Create or update a flair template.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | css_class                      | a valid subreddit image name                               |
   * | flair_template_id              |                                                            |
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | text                           | a string no longer than 64 characters                      |
   * | text_editable                  | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_FLAIRTEMPLATE =
        new Endpoint("r", "{subreddit}", "api", "flairtemplate");
  /**
   * Create or update a flair template.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | css_class                      | a valid subreddit image name                               |
   * | flair_template_id              |                                                            |
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | text                           | a string no longer than 64 characters                      |
   * | text_editable                  | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_FLAIRTEMPLATE =
        new Endpoint("api", "flairtemplate");
  /**
   * Create or update a flair template.<br>
   * This new endpoint is primarily used for the redesign.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | allowable_content              | one of (all, emoji, text)                                  |
   * | api_type                       | the string {@code json}                                    |
   * | background_color               | a 6-digit rgb hex color, e.g. #AABBCC                      |
   * | css_class                      | a valid subreddit image name                               |
   * | flair_template_id              |                                                            |
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | max_emojis                     | an integer between 1 and 10 (default: 10)                  |
   * | mod_only                       | boolean value                                              |
   * | override_css                   |                                                            |
   * | text                           | a string no longer than 64 characters                      |
   * | text_color                     | one of (light, dark)                                       |
   * | text_editable                  | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_FLAIRTEMPLATE_V2 =
        new Endpoint("r", "{subreddit}", "api", "flairtemplate_v2");
  /**
   * Create or update a flair template.<br>
   * This new endpoint is primarily used for the redesign.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | allowable_content              | one of (all, emoji, text)                                  |
   * | api_type                       | the string {@code json}                                    |
   * | background_color               | a 6-digit rgb hex color, e.g. #AABBCC                      |
   * | css_class                      | a valid subreddit image name                               |
   * | flair_template_id              |                                                            |
   * | flair_type                     | one of (USER_FLAIR, LINK_FLAIR)                            |
   * | max_emojis                     | an integer between 1 and 10 (default: 10)                  |
   * | mod_only                       | boolean value                                              |
   * | override_css                   |                                                            |
   * | text                           | a string no longer than 64 characters                      |
   * | text_color                     | one of (light, dark)                                       |
   * | text_editable                  | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_FLAIRTEMPLATE_V2 =
        new Endpoint("api", "flairtemplate_v2");
  /**
   * Return list of available link flair for the current subreddit.<br>
   * Will not return flair if the user cannot set their own link flair, and they are not a moderator
   * that can set flair.
   */
  public static final Endpoint GET_R_SUBREDDIT_API_LINK_FLAIR =
        new Endpoint("r", "{subreddit}", "api", "link_flair");
  /**
   * Return list of available link flair for the current subreddit.<br>
   * Will not return flair if the user cannot set their own link flair, and they are not a moderator
   * that can set flair.
   */
  public static final Endpoint GET_API_LINK_FLAIR =
        new Endpoint("api", "link_flair");
  /**
   * Return list of available link flair for the current subreddit.<br>
   * Will not return flair if the user cannot set their own link flair, and they are not a moderator
   * that can set flair.
   */
  public static final Endpoint GET_R_SUBREDDIT_API_LINK_FLAIR_V2 =
        new Endpoint("r", "{subreddit}", "api", "link_flair_v2");
  /**
   * Return list of available link flair for the current subreddit.<br>
   * Will not return flair if the user cannot set their own link flair, and they are not a moderator
   * that can set flair.
   */
  public static final Endpoint GET_API_LINK_FLAIR_V2 =
        new Endpoint("api", "link_flair_v2");
  /**
   * Select the flair of a user/link.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | background_color               | a 6-digit rgb hex color, e.g. #AABBCC                      |
   * | css_class                      | a valid subreddit image name                               |
   * | flair_template_id              |                                                            |
   * | link                           | a fullname of a link                                       |
   * | name                           | a user by name                                             |
   * | return_rtson                   | [all|only|none]: "all" saves attributes and returns rtjson |
   * |                                | "only" only returns rtjson"none" only saves attributes     |
   * | text                           | a string no longer than 64 characters                      |
   * | text_color                     | one of (light, dark)                                       |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_SELECTFLAIR =
        new Endpoint("r", "{subreddit}", "api", "selectflair");
  /**
   * Select the flair of a user/link.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | background_color               | a 6-digit rgb hex color, e.g. #AABBCC                      |
   * | css_class                      | a valid subreddit image name                               |
   * | flair_template_id              |                                                            |
   * | link                           | a fullname of a link                                       |
   * | name                           | a user by name                                             |
   * | return_rtson                   | [all|only|none]: "all" saves attributes and returns rtjson |
   * |                                | "only" only returns rtjson"none" only saves attributes     |
   * | text                           | a string no longer than 64 characters                      |
   * | text_color                     | one of (light, dark)                                       |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_SELECTFLAIR =
        new Endpoint("api", "selectflair");
  /**
   * Enables user/link flairs.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_enabled                  | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_SETFLAIRENABLED =
        new Endpoint("r", "{subreddit}", "api", "setflairenabled");
  /**
   * Enables user/link flairs.
   * <pre>
   * +--------------------------------+------------------------------------------------------------+
   * | Parameter                      | Description                                                |
   * +--------------------------------+------------------------------------------------------------+
   * | api_type                       | the string {@code json}                                    |
   * | flair_enabled                  | boolean value                                              |
   * | uh / X-Modhash header          | a modhash                                                  |
   * +--------------------------------+------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_SETFLAIRENABLED =
        new Endpoint("api", "setflairenabled");
  /**
   * Return list of available user flair for the current subreddit.<br>
   * Will not return flair if flair is disabled on the subreddit, the user cannot set their own
   * flair, or they are not a moderator that can set flair.
   */
  public static final Endpoint GET_R_SUBREDDIT_API_USER_FLAIR =
        new Endpoint("r", "{subreddit}", "api", "user_flair");
  /**
   * Return list of available user flair for the current subreddit.<br>
   * Will not return flair if flair is disabled on the subreddit, the user cannot set their own
   * flair, or they are not a moderator that can set flair.
   */
  public static final Endpoint GET_API_USER_FLAIR =
        new Endpoint("api", "user_flair");
  /**
   * Return list of available user flair for the current subreddit.<br>
   * If user is not a mod of the subreddit, this endpoint filters out mod_only templates.
   */
  public static final Endpoint GET_R_SUBREDDIT_API_USER_FLAIR_V2 =
        new Endpoint("r", "{subreddit}", "api", "user_flair_v2");
  /**
   * Return list of available user flair for the current subreddit.<br>
   * If user is not a mod of the subreddit, this endpoint filters out mod_only templates.
   */
  public static final Endpoint GET_API_USER_FLAIR_V2 =
        new Endpoint("api", "user_flair_v2");
}