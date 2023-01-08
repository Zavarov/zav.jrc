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
 * REST endpoint for the {@code Private Messages} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_messages">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public final class PrivateMessages {
  /**
   * For blocking the author of a thing via inbox.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | id                     | fullname of a thing                                                |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_BLOCK =
        new Endpoint("api", "block");
  /**
   * Collapse a message.<br>
   * See also {@link #POST_API_UNCOLLAPSE_MESSAGE}.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | id                     | A comma-separated list of items                                    |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_COLLAPSE_MESSAGE =
        new Endpoint("api", "collapse_message");
  /**
   * Handles message composition under /message/compose.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | api_type               | the string {@code json}                                            |
   * | from_sr                | subreddit name                                                     |
   * | g-recaptcha-response   |                                                                    |
   * | subject                | a string no longer than 100 characters                             |
   * | text                   | raw markdown text                                                  |
   * | to                     | the name of an existing user                                       |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_COMPOSE =
        new Endpoint("api", "compose");
  /**
   * Delete messages from the recipient's view of their inbox.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | id                     | fullname of a thing                                                |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_DEL_MSG =
        new Endpoint("api", "del_msg");
  /**
   * Queue up marking all messages for a user as read.<br>
   * This may take some time, and returns 202 to acknowledge acceptance of the request.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | filter_types           | A comma-separated list of items                                    |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_READ_ALL_MESSAGES =
        new Endpoint("api", "read_all_messages");
  /**
   * Marks one or more messages for a user as read.<br>
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | id                     | A comma-separated list of thing fullnames                          |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_READ_MESSAGE =
        new Endpoint("api", "read_message");
  /**
   * Blocking a subreddit prevents the user from receiving any further messages from it.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | id                     | fullname of a thing                                                |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_UNBLOCK_SUBREDDIT =
        new Endpoint("api", "unblock_subreddit");
  /**
   * Uncollapse a message.<br>
   * See also {@link #POST_API_COLLAPSE_MESSAGE}.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | id                     | A comma-separated list of thing fullnames                          |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_UNCOLLAPSE_MESSAGE =
        new Endpoint("api", "uncollapse_message");
  /**
   * Marks one or more messages for a user as unread.<br>
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | id                     | A comma-separated list of thing fullnames                          |
   * | uh / X-Modhash header  | a modhash                                                          |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_UNREAD_MESSAGE =
        new Endpoint("api", "unread_message");
  /**
   * The endpoint for all user messages.<br>
   * This endpoint is a listing.
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | mark                   | one of (true, false)                                               |
   * | mid                    |                                                                    |
   * | after                  | fullname of a thing                                                |
   * | before                 | fullname of a thing                                                |
   * | count                  | a positive integer (default: 0)                                    |
   * | limit                  | the maximum number of items desired (default: 25, maximum: 100)    |
   * | show                   | (optional) the string {@code all}                                  |
   * | sr_detail              | (optional) expand subreddits                                       |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_MESSAGE_WHERE =
        new Endpoint("message", "{where}");
  /**
   * Returns a listing of all inbox messages.<br>
   * See also {@link #GET_MESSAGE_WHERE}
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | mark                   | one of (true, false)                                               |
   * | mid                    |                                                                    |
   * | after                  | fullname of a thing                                                |
   * | before                 | fullname of a thing                                                |
   * | count                  | a positive integer (default: 0)                                    |
   * | limit                  | the maximum number of items desired (default: 25, maximum: 100)    |
   * | show                   | (optional) the string {@code all}                                  |
   * | sr_detail              | (optional) expand subreddits                                       |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_MESSAGE_INBOX =
        new Endpoint(GET_MESSAGE_WHERE.getPath("inbox"));
  /**
   * Returns a listing of all unread inbox messages.<br>
   * See also {@link #GET_MESSAGE_WHERE}
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | mark                   | one of (true, false)                                               |
   * | mid                    |                                                                    |
   * | after                  | fullname of a thing                                                |
   * | before                 | fullname of a thing                                                |
   * | count                  | a positive integer (default: 0)                                    |
   * | limit                  | the maximum number of items desired (default: 25, maximum: 100)    |
   * | show                   | (optional) the string {@code all}                                  |
   * | sr_detail              | (optional) expand subreddits                                       |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_MESSAGE_UNREAD =
        new Endpoint(GET_MESSAGE_WHERE.getPath("unread"));
  /**
   * Returns a listing of all sent messages.<br>
   * See also {@link #GET_MESSAGE_WHERE}
   * <pre>
   * +------------------------+--------------------------------------------------------------------+
   * | Parameter              | Description                                                        |
   * +------------------------+--------------------------------------------------------------------+
   * | mark                   | one of (true, false)                                               |
   * | mid                    |                                                                    |
   * | after                  | fullname of a thing                                                |
   * | before                 | fullname of a thing                                                |
   * | count                  | a positive integer (default: 0)                                    |
   * | limit                  | the maximum number of items desired (default: 25, maximum: 100)    |
   * | show                   | (optional) the string {@code all}                                  |
   * | sr_detail              | (optional) expand subreddits                                       |
   * +------------------------+--------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_MESSAGE_SENT =
        new Endpoint(GET_MESSAGE_WHERE.getPath("sent"));
}
