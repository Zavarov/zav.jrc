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
 * REST endpoint for {@code Links} and {@code Comments} section.
 *
 * @see <a href=
 *      "https://www.reddit.com/dev/api#section_links_and_comments">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public final class LinksComments {
  /**
   * Submit a new comment or reply to a message.<br>
   * {@code parent} is the fullname of the thing being replied to. Its value
   * changes the kind of object created by this request:
   * 
   * <pre>
   *  - the fullname of a Link: a top-level comment in that Link's thread. (requires submit scope)
   *  - the fullname of a Comment: a comment reply to that comment. (requires submit scope)
   *  - the fullname of a Message: a message reply to that message. (requires privatemessages scope)
   * </pre>
   * 
   * {@code text} should be the raw markdown body of the comment or message.<br>
   * To start a new message thread, use {@code /api/compose}.
   * 
   * <pre>
   *   | Parameter             | Description              |
   *   | --------------------- | ------------------------ |
   *   | api_type              | the string 'json'        |
   *   | return_rtjson         | boolean value            |
   *   | richtext_json         | JSON data                |
   *   | text                  | raw markdown text        |
   *   | thing_id              | fullname of parent thing |
   *   | uh / X-Modhash header | modhash                  |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_comment">here</a>
   */
  public static final Endpoint POST_API_COMMENT = new Endpoint("api", "comment");
  /**
   * Delete a Link or Comment.
   * 
   * <pre>
   *   | Parameter             | Description                                    |
   *   | --------------------- | ---------------------------------------------- |
   *   | id                    | fullname of a thing created by the user        |
   *   | uh / X-Modhash header | modhash                                        |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_del">here</a>
   */
  public static final Endpoint POST_API_DELETE = new Endpoint("api", "del");
  /**
   * Edit the body text of a comment or self-post.
   * 
   * <pre>
   *   | Parameter             | Description              |
   *   | --------------------- | ------------------------ |
   *   | api_type              | the string 'json'        |
   *   | return_rtjson         | boolean value            |
   *   | richtext_json         | JSON data                |
   *   | text                  | raw markdown text        |
   *   | thing_id              | fullname of parent thing |
   *   | uh / X-Modhash header | modhash                  |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_editusertext">here</a>
   */
  public static final Endpoint POST_API_EDIT_USER_TEXT = new Endpoint("api", "editusertext");
  /**
   * Add or modify post event times. To remove event info leave these fields
   * empty.
   * 
   * <pre>
   *   | Parameter             | Description                                |
   *   | --------------------- | ------------------------------------------ |
   *   | event_end             | a datetime string e.g. 2018-09-11T12:00:00 |
   *   | event_start           | a datetime string e.g. 2018-09-11T12:00:00 |
   *   | richtext_json         | JSON data                                  |
   *   | event_tz              | a pytz timezone e.g. America/Los_Angeles   |
   *   | id                    | fullname of a link                         |
   *   | uh / X-Modhash header | modhash                                    |
   * </pre>
   *
   * @see <a href=
   *      "https://www.reddit.com/dev/api#POST_api_event_post_time">here</a>
   */
  public static final Endpoint POST_API_EVENT_POST_TIME = new Endpoint("api", "event_post_time");
  /**
   * Follow or unfollow a post.<br>
   * To follow, {@code follow} should be {@code true}. To unfollow, {@code follow}
   * should be {@code false}. The user must have access to the subreddit to be
   * able to follow a post within it.
   * 
   * <pre>
   *   | Parameter             | Description                                  |
   *   | --------------------- | -------------------------------------------- |
   *   | follow                | boolean: True to follow or False to unfollow |
   *   | fullname              | fullname of a link                           |
   *   | uh / X-Modhash header | modhash                                      |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_follow_post">here</a>
   */
  public static final Endpoint POST_API_FOLLOW_POST = new Endpoint("api", "follow_post");
  /**
   * Hide a link.<br>
   * This removes it from the user's default view of subreddit listings.<br>
   * See also: {@code /api/unhide}.
   * 
   * <pre>
   *   | Parameter             | Description                                    |
   *   | --------------------- | ---------------------------------------------- |
   *   | id                    | fullname of a thing created by the user        |
   *   | uh / X-Modhash header | modhash                                        |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_hide">here</a>
   */
  public static final Endpoint POST_API_HIDE = new Endpoint("api", "hide");
  /**
   * Return a listing of things specified by their fullnames.<br>
   * Only Links, Comments, and Subreddits are allowed.
   * 
   * <pre>
   *   | Parameter | Description                               |
   *   | --------- | ----------------------------------------- |
   *   | id        | A comma-separated list of thing fullnames |
   *   | sr_name   | comma-delimited list of subreddit names   |
   *   | url       | a valid URL                               |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_info">here</a>
   */
  public static final Endpoint GET_API_INFO = new Endpoint("api", "info");
  /**
   * Return a listing of things specified by their fullnames.<br>
   * Only Links, Comments, and Subreddits are allowed.
   * 
   * <pre>
   *   | Parameter | Description                               |
   *   | --------- | ----------------------------------------- |
   *   | id        | A comma-separated list of thing fullnames |
   *   | sr_name   | comma-delimited list of subreddit names   |
   *   | url       | a valid URL                               |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_info">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_API_INFO = new Endpoint("r", "{subreddit}", "api",
      "info");
  /**
   * Lock a link or comment.<br>
   * Prevents a post or new child comments from receiving new comments.<br>
   * See also: {@code /api/unlock}.
   * 
   * <pre>
   *   | Parameter             | Description                                    |
   *   | --------------------- | ---------------------------------------------- |
   *   | id                    | fullname of a thing created by the user        |
   *   | uh / X-Modhash header | modhash                                        |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_lock">here</a>
   */
  public static final Endpoint POST_API_LOCK = new Endpoint("api", "lock");
  /**
   * Mark a link NSFW.<br>
   * See also: {@code /api/unmarknsfw}.
   * 
   * <pre>
   *   | Parameter             | Description                                    |
   *   | --------------------- | ---------------------------------------------- |
   *   | id                    | fullname of a thing created by the user        |
   *   | uh / X-Modhash header | modhash                                        |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_marknsfw">here</a>
   */
  public static final Endpoint POST_API_MARK_NSFW = new Endpoint("api", "marknsfw");
  /**
   * Retrieve additional comments omitted from a base comment tree.<br>
   * When a comment tree is rendered, the most relevant comments are selected for
   * display first. Remaining comments are stubbed out with "MoreComments" links.
   * This API call is used to retrieve the additional comments represented by
   * those stubs, up to 100 at a time.<br>
   * The two core parameters required are {@code link} and {@code children}.
   * {@code link} is the fullname of the link whose comments are being fetched.
   * children is a comma-delimited list of comment ID36s that need to be
   * fetched.<br>
   * If id is passed, it should be the ID of the MoreComments object this call is
   * replacing. This is needed only for the HTML UI's purposes and is optional
   * otherwise.<br>
   * <b>NOTE:</b> you may only make one request at a time to this API endpoint.
   * Higher concurrency will result in an error being returned.<br>
   * If {@code limit_children} is {@code true}, only return the children
   * requested.<br>
   * {@code depth} is the maximum depth of subtrees in the thread.
   * 
   * <pre>
   *   | Parameter      | Description                                                         |
   *   | -------------- | ------------------------------------------------------------------- |
   *   | api_type       | the string 'json'                                                   |
   *   | children       | boolean value                                                       |
   *   | depth          | (optional) an integer                                               |
   *   | id             | (optional) id of the associated MoreChildren object                 |
   *   | limit_children | boolean value                                                       |
   *   | link_id        | fullname of a link                                                  |
   *   | sort           | one of (confidence, top, new, controversial, old, random, qa, live) |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_morechildren">here</a>
   */
  public static final Endpoint POST_API_MORE_CHILDREN = new Endpoint("api", "morechildren");
  /**
   * Report a link, comment or message. Reporting a thing brings it to the
   * attention of the subreddit's moderators. Reporting a message sends it to a
   * system for admin review. For links and comments, the thing is implicitly
   * hidden as well (see {@code /api/hide} for details).<br>
   * See {@code /r/{subreddit}/about/rules} for for more about subreddit rules,
   * and {@code /r/{subreddit}/about} for more about free_form_reports.
   * 
   * <pre>
   *   | Parameter             | Description                             |
   *   | --------------------- | --------------------------------------- |
   *   | additional_info       | a string no longer than 2000 characters |
   *   | api_type              | the string 'json'                       |
   *   | custom_text           | a string no longer than 2000 characters |
   *   | from_help_desk        | boolean value                           |
   *   | from_modmail          | boolean value                           |
   *   | modmail_conv_id       | base36 modmail conversation id          |
   *   | other_reason          | a string no longer than 100 characters  |
   *   | reason                | a string no longer than 100 characters  |
   *   | rule_reason           | a string no longer than 100 characters  |
   *   | site_reason           | a string no longer than 100 characters  |
   *   | sr_name               | a string no longer than 1000 characters |
   *   | thing_id              | fullname of a thing                     |
   *   | uh / X-Modhash header | a modhash                               |
   *   | usernames             | A comma-separated list of items         |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_report">here</a>
   */
  public static final Endpoint POST_API_REPORT = new Endpoint("api", "report");
  /**
   * Report an award. Reporting an award brings it to the attention of a Reddit
   * admin.
   *
   * <pre>
   *   | Parameter | Description                             |
   *   | --------- | --------------------------------------- |
   *   | award_id  | a string                                |
   *   | reason    | a string no longer than 100 characters  |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_report_award">here</a>
   */
  public static final Endpoint POST_API_REPORT_AWARD = new Endpoint("api", "report_award");
  /**
   * Save a link or comment.<br>
   * Saved things are kept in the user's saved listing for later perusal.<br>
   * See also: {@code /api/unsave}.
   * 
   * <pre>
   *   | Parameter             | Description         |
   *   | --------------------- | ------------------- |
   *   | category              | a category name     |
   *   | id                    | fullname of a thing |
   *   | uh / X-Modhash header | modhash             |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_save">here</a>
   */
  public static final Endpoint POST_API_SAVE = new Endpoint("api", "save");
  /**
   * Get a list of categories in which things are currently saved.<br>
   * See also: {@code /api/save}.
   *
   * @see <a href=
   *      "https://www.reddit.com/dev/api#GET_api_saved_categories">here</a>
   */
  public static final Endpoint GET_API_SAVED_CATEGORIES = new Endpoint("api", "saved_categories");
  /**
   * Enable or disable inbox replies for a link or comment.<br>
   * {@code state} is a boolean that indicates whether you are enabling or
   * disabling inbox replies - true to enable, false to disable.
   * 
   * <pre>
   *   | Parameter             | Description                             |
   *   | --------------------- | --------------------------------------- |
   *   | id                    | fullname of a thing created by the user |
   *   | state                 | boolean value                           |
   *   | uh / X-Modhash header | modhash                                 |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_sendreplies">here</a>
   */
  public static final Endpoint POST_API_SEND_REPLIES = new Endpoint("api", "sendreplies");
  /**
   * Set or unset "contest mode" for a link's comments.<br>
   * {@code state} is a boolean that indicates whether you are enabling or
   * disabling contest mode - true to enable, false to disable.
   * 
   * <pre>
   *   | Parameter             | Description       |
   *   | --------------------- | ----------------- |
   *   | api_type              | the string 'json' |
   *   | id                    |                   |
   *   | state                 | boolean value     |
   *   | uh / X-Modhash header | modhash           |
   * </pre>
   *
   * @see <a href=
   *      "https://www.reddit.com/dev/api#POST_api_set_contest_mode">here</a>
   */
  public static final Endpoint POST_API_SET_CONTEST_MODE = new Endpoint("api", "set_context_mode");
  /**
   * Set or unset a Link as the sticky in its subreddit.<br>
   * {@code state} is a boolean that indicates whether to sticky or unsticky this
   * post - true to sticky, false to unsticky.<br>
   * The {@code num} argument is optional, and only used when stickying a post. It
   * allows specifying a particular "slot" to sticky the post into, and if there
   * is already a post stickied in that slot it will be replaced. If there is no
   * post in the specified slot to replace, or {@code num} is None, the
   * bottom-most slot will be used.
   * 
   * <pre>
   *   | Parameter             | Description                |
   *   | --------------------- | -------------------------- |
   *   | api_type              | the string 'json'          |
   *   | id                    |                            |
   *   | num                   | an integer between 1 and 4 |
   *   | state                 | boolean value              |
   *   | to_profile            | boolean value              |
   *   | uh / X-Modhash header | modhash                    |
   * </pre>
   *
   * @see <a href=
   *      "https://www.reddit.com/dev/api#POST_api_set_subreddit_sticky">here</a>
   */
  public static final Endpoint POST_API_SET_SUBREDDIT_STICKY = new Endpoint("api",
      "set_subreddit_sticky");
  /**
   * Set a suggested sort for a link.<br>
   * Suggested sorts are useful to display comments in a certain preferred way for
   * posts. For example, casual conversation may be better sorted by new by
   * default, or AMAs may be sorted by Q&amp;A. A sort of an empty string clears
   * the default sort.
   * 
   * <pre>
   *   | Parameter             | Description                                  |
   *   | --------------------- | -------------------------------------------- |
   *   | api_type              | the string 'json'                            |
   *   | id                    |                                              |
   *   | limit_children        | boolean value                                |
   *   | link_id               | fullname of a link                           |
   *   | sort                  | one of (confidence, top, new, controversial, |
   *   |                       | old, random, qa, live, blank)                |
   *   | uh / X-Modhash header | modhash                                      |
   * </pre>
   *
   * @see <a href=
   *      "https://www.reddit.com/dev/api#POST_api_set_suggested_sort">here</a>
   */
  public static final Endpoint POST_API_SET_SUGGESTED_SORT = new Endpoint("api",
      "set_suggested_sort");
  /**
   * Mark a link as spoiler.
   * 
   * <pre>
   *   | Parameter             | Description        |
   *   | --------------------- | ------------------ |
   *   | id                    | fullname of a link |
   *   | uh / X-Modhash header | modhash            |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_spoiler">here</a>
   */
  public static final Endpoint POST_API_SPOILER = new Endpoint("api", "spoiler");
  /**
   * Requires a subscription to [reddit premium].
   * 
   * <pre>
   *   | Parameter             | Description                              |
   *   | --------------------- | ---------------------------------------- |
   *   | links                 | A comma-separated list of link fullnames |
   *   | uh / X-Modhash header | modhash                                  |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_store_visits">here</a>
   */
  public static final Endpoint POST_API_STORE_VISITS = new Endpoint("api", "store_visits");
  /**
   * Submit a link to a subreddit.<br>
   * Submit will create a link or self-post in the subreddit {@code sr} with the
   * title {@code title}. If kind is "link", then {@code url} is expected to be a
   * valid URL to link to. Otherwise, {@code text}, if present, will be the body
   * of the self-post unless {@code richtext_json} is present, in which case it
   * will be converted into the body of the self-post. An error is thrown if both
   * {@code text} and {@code richtext_json} are present.<br>
   * If a link with the same URL has already been submitted to the specified
   * subreddit an error will be returned unless resubmit is true. extension is
   * used for determining which view-type (e.g. {@code json}, {@code compact}
   * etc.) to use for the redirect that is generated if the resubmit error occurs.
   * 
   * <pre>
   *   | Parameter             | Description                                        |
   *   | --------------------- | -------------------------------------------------- |
   *   | ad                    | boolean value                                      |
   *   | api_type              | the string 'json'                                  |
   *   | app                   |                                                    |
   *   | collection_id         | (beta) the UUID of a collection                    |
   *   | event_end             | (beta) a datetime string e.g. 2018-09-11T12:00:00  |
   *   | event_start           | (beta) a datetime string e.g. 2018-09-11T12:00:00  |
   *   | event_tz              | (beta) a pytz timezone e.g. America/Los_Angeles    |
   *   | extension             | extension used for redirects                       |
   *   | flair_id              | a string no longer than 36 characters              |
   *   | flair_text            | a string no longer than 64 characters              |
   *   | g-recaptcha-response  |                                                    |
   *   | kind                  | one of (link, self, image, video, videogif)        |
   *   | nsfw                  | boolean value                                      |
   *   | resubmit              | boolean value                                      |
   *   | richtext_json         | JSON data                                          |
   *   | sendreplies           | boolean value                                      |
   *   | spoiler               | boolean value                                      |
   *   | sr                    | subreddit name                                     |
   *   | text                  | raw markdown text                                  |
   *   | title                 | title of the submission. up to 300 characters long |
   *   | uh / X-Modhash header | modhash                                            |
   *   | url                   | a valid URL                                        |
   *   | video_poster_url      | a valid URL                                        |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_submit">here</a>
   */
  public static final Endpoint POST_API_SUBMIT = new Endpoint("api", "submit");
  /**
   * Unhide a link.<br>
   * See also: {@code /api/hide}.
   * 
   * <pre>
   *   | Parameter             | Description                              |
   *   | --------------------- | ---------------------------------------- |
   *   | links                 | A comma-separated list of link fullnames |
   *   | uh / X-Modhash header | modhash                                  |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_unhide">here</a>
   */
  public static final Endpoint POST_API_UNHIDE = new Endpoint("api", "unhide");
  /**
   * Unlock a link or comment.<br>
   * Allow a post or comment to receive new comments.<br>
   * See also: {@code /api/lock}.
   * 
   * <pre>
   *   | Parameter             | Description         |
   *   | --------------------- | ------------------- |
   *   | id                    | fullname of a thing |
   *   | uh / X-Modhash header | modhash             |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_unlock">here</a>
   */
  public static final Endpoint POST_API_UNLOCK = new Endpoint("api", "unlock");
  /**
   * Remove the NSFW marking from a link.<br>
   * See also: {@code /api/marknsfw}.
   * 
   * <pre>
   *   | Parameter             | Description         |
   *   | --------------------- | ------------------- |
   *   | id                    | fullname of a thing |
   *   | uh / X-Modhash header | modhash             |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_unmarknsfw">here</a>
   */
  public static final Endpoint POST_API_UNMARK_NSFW = new Endpoint("api", "unmarknsfw");
  /**
   * Unsave a link or comment.<br>
   * This removes the thing from the user's saved listings as well.<br>
   * See also: {@code /api/save}.
   * 
   * <pre>
   *   | Parameter             | Description         |
   *   | --------------------- | ------------------- |
   *   | id                    | fullname of a thing |
   *   | uh / X-Modhash header | modhash             |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_unsave">here</a>
   */
  public static final Endpoint POST_API_UNSAFE = new Endpoint("api", "unsafe");
  /**
   * Unspoilers a link.
   * 
   * <pre>
   *   | Parameter             | Description         |
   *   | --------------------- | ------------------- |
   *   | id                    | fullname of a thing |
   *   | uh / X-Modhash header | modhash             |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_unspoiler">here</a>
   */
  public static final Endpoint POST_API_UNSPOILER = new Endpoint("api", "unspoiler");
  /**
   * Cast a vote on a thing.<br>
   * {@code id} should be the fullname of the Link or Comment to vote on.<br>
   * {@code dir} indicates the direction of the vote. Voting {@code 1} is an
   * upvote, {@code -1} is a downvote, and {@code 0} is equivalent to "un-voting"
   * by clicking again on a highlighted arrow.<br>
   * <b>Note: votes must be cast by humans.</b> That is, API clients proxying a
   * human's action one-for-one are OK, but bots deciding how to vote on content
   * or amplifying a human's vote are not. See the reddit rules for more details
   * on what constitutes vote cheating.
   * 
   * <pre>
   *   | Parameter             | Description                       |
   *   | --------------------- | --------------------------------- |
   *   | dir                   | vote direction. one of (1, 0, -1) |
   *   | id                    | fullname of a thing               |
   *   | rank                  | an integer greater than 1         |
   *   | uh / X-Modhash header | modhash                           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_vote">here</a>
   */
  public static final Endpoint POST_API_VOTE = new Endpoint("api", "vote");
}
