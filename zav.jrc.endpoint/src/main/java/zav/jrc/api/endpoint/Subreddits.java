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
 * REST endpoint for the {@code Subreddits} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_subreddits">here</a>
 */
@NonNullByDefault
public final class Subreddits {
  /**
   * The endpoint for retrieving information about a subreddit with respect to a user.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_%28where%29">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_WHERE =
        new Endpoint("r", "{subreddit}", "about", "{where}");
  /**
   * The endpoint for retrieving information about a subreddit with respect to a user.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_%28where%29">here</a>
   */
  public static final Endpoint GET_ABOUT_WHERE =
        new Endpoint("about", "{where}");
  /**
   * All currently banned users.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_banned">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_BANNED =
        new Endpoint(GET_R_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "banned"));
  /**
   * All currently banned users.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_banned">here</a>
   */
  public static final Endpoint GET_ABOUT_BANNED =
        new Endpoint(GET_ABOUT_WHERE.getPath("banned"));
  /**
   * All contributors.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_contributors">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS =
        new Endpoint(GET_R_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "contributors"));
  /**
   * All contributors.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_contributors">here</a>
   */
  public static final Endpoint GET_ABOUT_CONTRIBUTORS =
        new Endpoint(GET_ABOUT_WHERE.getPath("contributors"));
  /**
   * All moderators.<br>
   * This endpoint is a {@code UserList}.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_moderators">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_MODERATORS =
        new Endpoint(GET_R_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "moderators"));
  /**
   * All moderators.<br>
   * This endpoint is a {@code UserList}.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_moderators">here</a>
   */
  public static final Endpoint GET_ABOUT_MODERATORS =
        new Endpoint(GET_ABOUT_WHERE.getPath("moderators"));
  /**
   * All currently muted users.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_muted">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_MUTED =
        new Endpoint(GET_R_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "muted"));
  /**
   * All currently muted users.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_muted">here</a>
   */
  public static final Endpoint GET_ABOUT_MUTED =
        new Endpoint(GET_ABOUT_WHERE.getPath("muted"));
  /**
   * All users banned from the wiki.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikibanned">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_WIKIBANNED =
        new Endpoint(GET_R_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "wikibanned"));
  /**
   * All users banned from the wiki.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikibanned">here</a>
   */
  public static final Endpoint GET_ABOUT_WIKIBANNED =
        new Endpoint(GET_ABOUT_WHERE.getPath("wikibanned"));
  /**
   * All users contributing to the wiki.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikicontributors">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS =
        new Endpoint(GET_R_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "wikicontributors"));
  /**
   * All users contributing to the wiki.<br>
   * This endpoint is a list of users.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikicontributors">here</a>
   */
  public static final Endpoint GET_ABOUT_WIKICONTRIBUTORS =
        new Endpoint(GET_ABOUT_WHERE.getPath("wikicontributors"));
  /**
   * Remove the subreddit's custom mobile banner.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description       |
   *   | --------------------- | ----------------- |
   *   | api_type              | the string 'json' |
   *   | uh / X-Modhash header | modhash           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_banner">here</a>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_DELETE_SR_BANNER =
        new Endpoint("r", "{subreddit}", "api", "delete_sr_banner");
  /**
   * Remove the subreddit's custom mobile banner.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description       |
   *   | --------------------- | ----------------- |
   *   | api_type              | the string 'json' |
   *   | uh / X-Modhash header | modhash           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_banner">here</a>
   */
  public static final Endpoint POST_API_DELETE_SR_BANNER =
        new Endpoint("api", "delete_sr_banner");
  /**
   * Remove the subreddit's custom header image.<br>
   * The sitewide-default header image will be shown again after this call.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description       |
   *   | --------------------- | ----------------- |
   *   | api_type              | the string 'json' |
   *   | uh / X-Modhash header | modhash           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_header">here</a>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_DELETE_SR_HEADER =
        new Endpoint("r", "{subreddit}", "api", "delete_sr_header");
  /**
   * Remove the subreddit's custom header image.<br>
   * The sitewide-default header image will be shown again after this call.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description       |
   *   | --------------------- | ----------------- |
   *   | api_type              | the string 'json' |
   *   | uh / X-Modhash header | modhash           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_header">here</a>
   */
  public static final Endpoint POST_API_DELETE_SR_HEADER =
        new Endpoint("api", "delete_sr_header");
  /**
   * Remove the subreddit's custom mobile icon.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description       |
   *   | --------------------- | ----------------- |
   *   | api_type              | the string 'json' |
   *   | uh / X-Modhash header | modhash           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_icon">here</a>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_DELETE_SR_ICON =
        new Endpoint("r", "{subreddit}", "api", "delete_sr_icon");
  /**
   * Remove the subreddit's custom mobile icon.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description       |
   *   | --------------------- | ----------------- |
   *   | api_type              | the string 'json' |
   *   | uh / X-Modhash header | modhash           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_icon">here</a>
   */
  public static final Endpoint POST_API_DELETE_SR_ICON =
        new Endpoint("api", "delete_sr_icon");
  /**
   * Remove an image from the subreddit's custom image set.<br>
   * The image will no longer count against the subreddit's image limit. However, the actual image
   * data may still be accessible for an unspecified amount of time. If the image is currently
   * referenced by the subreddit's stylesheet, that stylesheet will no longer validate and won't be
   * editable until the image reference is removed.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description                  |
   *   | --------------------- | ---------------------------- |
   *   | api_type              | the string 'json'            |
   *   | img_name              | a valid subreddit image name |
   *   | uh / X-Modhash header | modhash                      |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_img">here</a>
   */
  public static final Endpoint POST_R_SUBREDDIT_DELETE_SR_IMAGE =
        new Endpoint("r", "{subreddit}", "api", "delete_sr_img");
  /**
   * Remove an image from the subreddit's custom image set.<br>
   * The image will no longer count against the subreddit's image limit. However, the actual image
   * data may still be accessible for an unspecified amount of time. If the image is currently
   * referenced by the subreddit's stylesheet, that stylesheet will no longer validate and won't be
   * editable until the image reference is removed.<br>
   * This endpoints returns a jQuery response.
   * <pre>
   *   | Parameter             | Description                  |
   *   | --------------------- | ---------------------------- |
   *   | api_type              | the string 'json'            |
   *   | img_name              | a valid subreddit image name |
   *   | uh / X-Modhash header | modhash                      |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_img">here</a>
   */
  public static final Endpoint POST_API_DELETE_SR_IMAGE =
        new Endpoint("api", "delete_sr_img");
  /**
   * Return subreddits recommended for the given subreddit(s).<br>
   * Gets a list of subreddits recommended for {@code srnames}, filtering out any that appear in the
   * optional {@code omit} param.
   * <pre>
   *   | Parameter | Description                             |
   *   | --------- | --------------------------------------- |
   *   | omit      | comma-delimited list of subreddit names |
   *   | over_18   | boolean value                           |
   *   | srnames   | comma-delimited list of subreddit names |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_recommend_sr_%28srnames%29">here</a>
   */
  @Deprecated
  public static final Endpoint GET_API_RECOMMEND_SR_SRNAMES =
        new Endpoint("api", "recommend", "sr", "{srnames}");
  /**
   * List subreddit names that begin with a query string.<br>
   * Subreddits whose names begin with {@code query} will be returned. If {@code include_over_18} is
   * {@code false}, subreddits with over-18 content restrictions will be filtered from the
   * results.<br>
   * If {@code include_unadvertisable} is {@code false}, subreddits that have {@code hide_ad} set to
   * {@code true} or are on the {@code anti_ads_subreddits} list will be filtered.<br>
   * If {@code exact} is {@code true}, only an exact match will be returned. Exact matches are
   * inclusive of {@code over_18} subreddits, but not {@code hide_ad} subreddits when
   * {@code include_unadvertisable} is {@code false}.<br>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      {
   *          "names": ["reddit.com", "redditmobile"]
   *      }
   * }
   * </pre>
   * <pre>
   *   | Parameter              | Description                        |
   *   | ---------------------- | ---------------------------------- |
   *   | exact                  | boolean value                      |
   *   | include_over_18        | boolean value                      |
   *   | include_unadvertisable | boolean value                      |
   *   | query                  | a string up to 50 characters long, |
   *   |                        | consisting of printable characters |
   *   | search_query_id        | a uuid                             |
   *   | typeahead_active       | boolean value or None              |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_search_reddit_names">here</a>
   */
  public static final Endpoint GET_API_SEARCH_REDDIT_NAMES =
        new Endpoint("api", "search_reddit_names");
  /**
   * List subreddit names that begin with a query string.<br>
   * Subreddits whose names begin with {@code query} will be returned. If {@code include_over_18} is
   * {@code false}, subreddits with over-18 content restrictions will be filtered from the
   * results.<br>
   * If {@code include_unadvertisable} is {@code false}, subreddits that have {@code hide_ad} set to
   * {@code true} or are on the {@code anti_ads_subreddits} list will be filtered.<br>
   * If {@code exact} is {@code true}, only an exact match will be returned. Exact matches are
   * inclusive of {@code over_18} subreddits, but not {@code hide_ad} subreddits when
   * {@code include_unadvertisable} is {@code false}.<br>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      {
   *          "names": ["reddit.com", "redditmobile"]
   *      }
   * }
   * </pre>
   * <pre>
   *   | Parameter              | Description                        |
   *   | ---------------------- | ---------------------------------- |
   *   | exact                  | boolean value                      |
   *   | include_over_18        | boolean value                      |
   *   | include_unadvertisable | boolean value                      |
   *   | query                  | a string up to 50 characters long, |
   *   |                        | consisting of printable characters |
   *   | search_query_id        | a uuid                             |
   *   | typeahead_active       | boolean value or None              |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_search_reddit_names">here</a>
   */
  public static final Endpoint POST_API_SEARCH_REDDIT_NAMES =
        new Endpoint("api", "search_reddit_names");
  /**
   * List subreddit names that begin with a query string.<br>
   * Subreddits whose names begin with {@code query} will be returned. If {@code include_over_18} is
   * {@code false}, subreddits with over-18 content restrictions will be filtered from the
   * results.<br>
   * If {@code include_unadvertisable} is {@code false}, subreddits that have {@code hide_ad} set to
   * {@code true} or are on the {@code anti_ads_subreddits} list will be filtered.<br>
   * If {@code exact} is {@code true}, only an exact match will be returned. Exact matches are
   * inclusive of {@code over_18} subreddits, but not {@code hide_ad} subreddits when
   * {@code include_unadvertisable} is {@code false}.<br>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      {
   *          "names": ["reddit.com", "redditmobile"]
   *      }
   * }
   * </pre>
   * <pre>
   *   | Parameter              | Description                        |
   *   | ---------------------- | ---------------------------------- |
   *   | exact                  | boolean value                      |
   *   | include_over_18        | boolean value                      |
   *   | include_unadvertisable | boolean value                      |
   *   | query                  | a string up to 50 characters long, |
   *   |                        | consisting of printable characters |
   *   | search_query_id        | a uuid                             |
   *   | typeahead_active       | boolean value or None              |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_search_subreddits">here</a>
   */
  public static final Endpoint POST_API_SEARCH_SUBREDDITS =
        new Endpoint("api", "search_subreddits");
  /**
   * Create or configure a subreddit.<br>
   * If {@code sr} is specified, the request will attempt to modify the specified subreddit. If not,
   * a subreddit with name {@code name} will be created.<br>
   * This endpoint expects all values to be supplied on every request. If modifying a subset of
   * options, it may be useful to get the current settings from /about/edit.json first.<br>
   * For backwards compatibility, {@code description} is the sidebar text and
   * {@code public_description} is the publicly visible subreddit description.<br>
   * Most of the parameters for this endpoint are identical to options visible in the user interface
   * and their meanings are best explained there.
   * <pre>
   *   | Parameter                         | Description                                         |
   *   | --------------------------------- | --------------------------------------------------- |
   *   | admin_override_spam_comments      | boolean value                                       |
   *   | admin_override_spam_links         | boolean value                                       |
   *   | admin_override_spam_selfposts     | boolean value                                       |
   *   | all_original_content              | boolean value                                       |
   *   | allow_chat_post_creation          | boolean value                                       |
   *   | allow_discovery                   | boolean value                                       |
   *   | allow_galleries                   | boolean value                                       |
   *   | allow_images                      | boolean value                                       |
   *   | allow_polls                       | boolean value                                       |
   *   | allow_post_crossposts             | boolean value                                       |
   *   | allow_predictions                 | boolean value                                       |
   *   | allow_predictions_tournament      | boolean value                                       |
   *   | allow_top                         | boolean value                                       |
   *   | allow_videos                      | boolean value                                       |
   *   | api_type                          | the string 'json'                                   |
   *   | automated_reporting_level_abuse   | an integer between 0 and 3                          |
   *   | automated_reporting_level_hate    | an integer between 0 and 3                          |
   *   | collapse_deleted_comments         | boolean value                                       |
   *   | comment_score_hide_mins           | an integer between 0 and 1440 (default: 0)          |
   *   | crowd_control_chat_level          | an integer between 0 and 3                          |
   *   | crowd_control_level               | an integer between 0 and 3                          |
   *   | crowd_control_mode                | boolean value                                       |
   *   | description                       | boolean value                                       |
   *   | disable_contributor_requests      | raw markdown text                                   |
   *   | exclude_banned_modqueue           | boolean value                                       |
   *   | free_form_reports                 | boolean value                                       |
   *   | g-recaptcha-response              |                                                     |
   *   | header-title                      | a string no longer than 500 characters              |
   *   | hide_ads                          | boolean value                                       |
   *   | allow_predictions_tournament      | boolean value                                       |
   *   | key_color                         | a 6-digit rgb hex color, e.g. #AABBCC               |
   *   | lang                              | a valid IETF language tag (underscore separated)    |
   *   | link_type                         | one of 'any', 'link', 'self'                        |
   *   | name                              | subreddit name                                      |
   *   | new_pinned_post_pns_enabled       | boolean value                                       |
   *   | original_content_tag_enabled      | boolean value                                       |
   *   | over_18                           | boolean value                                       |
   *   | prediction_leaderboard_entry_type | an integer between 0 and 2                          |
   *   | public_description                | raw markdown text                                   |
   *   | restrict_commenting               | boolean value                                       |
   *   | restrict_posting                  | boolean value                                       |
   *   | show_media                        | boolean value                                       |
   *   | show_media_preview                | boolean value                                       |
   *   | spam_comments                     | one of 'low', 'high', 'all'                         |
   *   | spam_links                        | one of 'low', 'high', 'all'                         |
   *   | spam_selfposts                    | one of 'low', 'high', 'all'                         |
   *   | spoilers_enabled                  | boolean value                                       |
   *   | sr                                | fullname of a thing                                 |
   *   | submit_link_label                 | a string no longer than 60 characters               |
   *   | submit_text                       | raw markdown text                                   |
   *   | submit_text_label                 | a string no longer than 60 characters               |
   *   | suggested_comment_sort            | one of 'confidence', 'top', 'new', 'controversial', |
   *   |                                   | 'old', 'random','qa', 'live'                        |
   *   | title                             | a string no longer than 100 characters              |
   *   | toxicity_threshold_chat_level     | an integer between 0 and 1                          |
   *   | type                              | one of 'gold_restricted', 'archived', 'restricted', |
   *   |                                   | 'private', 'employees_only', 'gold_only', 'public', |
   *   |                                   | 'user'                                              |
   *   | uh / X-Modhash header             | a modhash                                           |
   *   | user_flair_pns_enabled            | boolean value                                       |
   *   | welcome_message_enabled           | boolean value                                       |
   *   | welcome_message_text              | raw markdown text                                   |
   *   | allow_predictions_tournament      | boolean value                                       |
   *   | wiki_edit_age                     | an integer between 0 and 36600 (default: 0)         |
   *   | wiki_edit_karma                   | an integer between 0 and 1000000000 (default: 0)    |
   *   | wikimode                          | one of 'disabled', 'modonly', 'anyone'              |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_site_admin">here</a>
   */
  public static final Endpoint POST_API_SITE_ADMIN =
        new Endpoint("api", "site_admin");
  /**
   * Get the submission text for the subreddit.<br>
   * This text is set by the subreddit moderators and intended to be displayed on the submission
   * form.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_submit_text">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_API_SUBMIT_TEXT =
        new Endpoint("r", "{subreddit}", "api", "submit_text");
  /**
   * Get the submission text for the subreddit.<br>
   * This text is set by the subreddit moderators and intended to be displayed on the submission
   * form.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_submit_text">here</a>
   */
  public static final Endpoint GET_API_SUBMIT_TEXT =
        new Endpoint("api", "submit_text");
  /**
   * Return a list of subreddits and data for subreddits whose names start with 'query'.<br>
   * Uses typeahead endpoint to recieve the list of subreddits names. Typeahead provides exact
   * matches, typo correction, fuzzy matching and boosts subreddits to the top that the user is
   * subscribed to.<br>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      "subreddits": [
   *          {
   *              "numSubscribers": 0,
   *              "name": "u_reddit",
   *              "allowedPostTypes": {
   *                  "images": true, "text": true, "videos": true, "links": true, "spoilers": true
   *              },
   *              "id": "t5_hv5dz",
   *              "primaryColor": "",
   *              "communityIcon": "",
   *              "icon": "https://styles.redditmedia.com/t5_hv5dz/styles/profileIcon.png"
   *          }
   *      ]
   * }
   * </pre>
   * <pre>
   *   | Parameter        | Description                                                            |
   *   | ---------------- | ---------------------------------------------------------------------- |
   *   | include_over_18  | boolean value                                                          |
   *   | include_profiles | boolean value                                                          |
   *   | query            | a string up to 25 characters long, consisting of printable characters. |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_subreddit_autocomplete">here</a>
   */
  public static final Endpoint GET_API_SUBREDDIT_AUTOCOMPLETE =
        new Endpoint("api", "subreddit_autocomplete");
  /**
   * Return a list of subreddits and data for subreddits whose names start with 'query'.<br>
   * Uses typeahead endpoint to recieve the list of subreddits names. Typeahead provides exact
   * matches, typo correction, fuzzy matching and boosts subreddits to the top that the user is
   * subscribed to.<br>
   * This endpoint is a listing of things. Those things can either be subreddits or accounts, i.e.
   * personal subreddits.
   * <pre>
   *   | Parameter        | Description                                                            |
   *   | ---------------- | ---------------------------------------------------------------------- |
   *   | include_over_18  | boolean value                                                          |
   *   | include_profiles | boolean value                                                          |
   *   | limit | an integer between 1 and 10 (default: 5)                                          |
   *   | query            | a string up to 25 characters long, consisting of printable characters. |
   *   | search_query_id | a uuid                                                                  |
   *   | include_profiles | boolean value or None                                                  |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_subreddit_autocomplete_v2">here</a>
   */
  public static final Endpoint GET_API_SUBREDDIT_AUTOCOMPLETE_V2 =
        new Endpoint("api", "subreddit_autocomplete_v2");
  /**
   * Update a subreddit's stylesheet.<br>
   * {@code op} should be {@code save} to update the contents of the stylesheet.
   * <pre>
   *   | Parameter             | Description                         |
   *   | --------------------- | ----------------------------------- |
   *   | api_type              | the string 'json'                   |
   *   | op                    | one of 'save', 'preview'            |
   *   | reason                | a string up to 256 characters long, |
   *   |                       | consisting of printable characters. |
   *   | stylesheet_contents   | the new stylesheet content          |
   *   | uh / X-Modhash header | a modhash                           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_subreddit_stylesheet">here</a>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_SUBREDDIT_STYLESHEET =
        new Endpoint("r", "{subreddit}", "api", "subreddit_stylesheet");
  /**
   * Update a subreddit's stylesheet.<br>
   * {@code op} should be {@code save} to update the contents of the stylesheet.
   * <pre>
   *   | Parameter             | Description                         |
   *   | --------------------- | ----------------------------------- |
   *   | api_type              | the string 'json'                   |
   *   | op                    | one of 'save', 'preview'            |
   *   | reason                | a string up to 256 characters long, |
   *   |                       | consisting of printable characters. |
   *   | stylesheet_contents   | the new stylesheet content          |
   *   | uh / X-Modhash header | a modhash                           |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_subreddit_stylesheet">here</a>
   */
  public static final Endpoint POST_API_SUBREDDIT_STYLESHEET =
        new Endpoint("api", "subreddit_stylesheet");
  /**
   * Subscribe to or unsubscribe from a subreddit.<br>
   * To subscribe, {@code action} should be {@code sub}. To unsubscribe, {@code action} should be
   * {@code unsub}. The user must have access to the subreddit to be able to subscribe to it.<br>
   * The {@code skip_initial_defaults} param can be set to True to prevent automatically subscribing
   * the user to the current set of defaults when they take their first subscription action.<br>
   * <pre>
   *   | Parameter             | Description                                             |
   *   | --------------------- | ------------------------------------------------------- |
   *   | action                | one of 'sub', 'unsub'                                   |
   *   | action_source         | one of 'o', 'n', 'b', 'o', 'a', 'r', 'd', 'i', 'n', 'g' |
   *   | skip_initial_defaults | boolean value                                           |
   *   | sr / sr_name          | A comma-separated list of subreddit fullnames           |
   *   |                       | (when using the "sr" parameter), or of subreddit names  |
   *   |                       | (when using the "sr_name" parameter)                    |
   *   | uh / X-Modhash header | a modhash                                               |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_subscribe">here</a>
   */
  public static final Endpoint POST_API_SUBSCRIBE =
        new Endpoint("api", "subscribe");
  /**
   * Add or replace a subreddit image, custom header logo, custom mobile icon, or custom mobile
   * banner.
   * <pre>
   *   | upload_type | Description                                   |
   *   | ----------- | --------------------------------------------- |
   *   | img         | an image for use in the subreddit stylesheet  |
   *   |             | is uploaded with the name specified in 'name' |
   *   | header      | the image uploaded will be the subreddit's    |
   *   |             | new logo and 'name' will be ignored.          |
   *   | icon        | the image uploaded will be the subreddit's    |
   *   |             | new mobile icon and 'name' will be ignored.   |
   *   | banner      | the image uploaded will be the subreddit's    |
   *   |             | new mobile banner and 'name' will be ignored. |
   * </pre>
   * For backwards compatibility, if {@code upload_type} is not specified, the {@code header} field
   * will be used instead:
   * <pre>
   *   | header | Description               |
   *   | ------ | ------------------------- |
   *   | 0      | 'upload_type' is 'img'    |
   *   | 1      | 'upload_type' is 'header' |
   * </pre>
   * The {@code img_type} field specifies whether to store the uploaded image as a PNG or JPEG.<br>
   * Subreddits have a limited number of images that can be in use at any given time. If no image
   * with the specified name already exists, one of the slots will be consumed.<br>
   * If an image with the specified name already exists, it will be replaced. This does not affect
   * the stylesheet immediately, but will take effect the next time the stylesheet is saved.<br>
   * <pre>
   *   | Parameter             | Description                              |
   *   | --------------------- | ---------------------------------------- |
   *   | file                  | file upload with maximum size of 500 KiB |
   *   | formid                | (optional) can be ignored                |
   *   | header                | an integer between 0 and 1               |
   *   | img_type              | one of png or jpg (default: png)         |
   *   | name                  | a valid subreddit image name             |
   *   | uh / X-Modhash header | a modhash                                |
   *   | upload_type           | one of 'img', 'header', 'icon', 'banner' |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_upload_sr_img">here</a>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE =
        new Endpoint("r", "{subreddit}", "api", "upload_sr_img");
  /**
   * Add or replace a subreddit image, custom header logo, custom mobile icon, or custom mobile
   * banner.
   * <pre>
   *   | upload_type | Description                                   |
   *   | ----------- | --------------------------------------------- |
   *   | img         | an image for use in the subreddit stylesheet  |
   *   |             | is uploaded with the name specified in 'name' |
   *   | header      | the image uploaded will be the subreddit's    |
   *   |             | new logo and 'name' will be ignored.          |
   *   | icon        | the image uploaded will be the subreddit's    |
   *   |             | new mobile icon and 'name' will be ignored.   |
   *   | banner      | the image uploaded will be the subreddit's    |
   *   |             | new mobile banner and 'name' will be ignored. |
   * </pre>
   * For backwards compatibility, if {@code upload_type} is not specified, the {@code header} field
   * will be used instead:
   * <pre>
   *   | header | Description               |
   *   | ------ | ------------------------- |
   *   | 0      | 'upload_type' is 'img'    |
   *   | 1      | 'upload_type' is 'header' |
   * </pre>
   * The {@code img_type} field specifies whether to store the uploaded image as a PNG or JPEG.<br>
   * Subreddits have a limited number of images that can be in use at any given time. If no image
   * with the specified name already exists, one of the slots will be consumed.<br>
   * If an image with the specified name already exists, it will be replaced. This does not affect
   * the stylesheet immediately, but will take effect the next time the stylesheet is saved.
   * <pre>
   *   | Parameter             | Description                              |
   *   | --------------------- | ---------------------------------------- |
   *   | file                  | file upload with maximum size of 500 KiB |
   *   | formid                | (optional) can be ignored                |
   *   | header                | an integer between 0 and 1               |
   *   | img_type              | one of png or jpg (default: png)         |
   *   | name                  | a valid subreddit image name             |
   *   | uh / X-Modhash header | a modhash                                |
   *   | upload_type           | one of 'img', 'header', 'icon', 'banner' |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_upload_sr_img">here</a>
   */
  public static final Endpoint POST_API_UPLOAD_SR_IMAGE =
        new Endpoint("api", "upload_sr_img");
  /**
   * Fetch moderator-designated requirements to post to the subreddit.<br>
   * Moderators may enable certain restrictions, such as minimum title length, when making a
   * submission to their subreddit.<br>
   * Clients may use the values returned by this endpoint to pre-validate fields before making a
   * request to POST /api/submit. This may allow the client to provide a better user experience to
   * the user, for example by creating a text field in their app that does not allow the user to
   * enter more characters than the max title length.<br>
   * A non-exhaustive list of possible requirements a moderator may enable:
   * <pre>
   *   | Parameter                 | Description                                                   |
   *   | ------------------------- | ------------------------------------------------------------- |
   *   | body_blacklisted_strings  | List of strings. Users may not submit posts that contain      |
   *   |                           | these words.                                                  |
   *   | body_restriction_policy   | String. One of "required", "notAllowed", or "none", meaning   |
   *   |                           | that a self-post body is required, not allowed, or optional,  |
   *   |                           | respectively.                |                                |
   *   | domain_blacklist          | List of strings. Users may not submit links to these domains. |
   *   | domain_whitelist          | List of strings. Users submissions MUST be from one of these  |
   *   |                           | domains.                                                      |
   *   | is_flair_required         | Boolean. If True, flair must be set at submission time.       |
   *   | title_blacklisted_strings | List of strings. Submission titles may NOT contain any of the |
   *   |                           | listed strings.                                               |
   *   | title_required_strings    | List of strings. Submission title MUST contain at least ONE   |
   *   |                           | of the listed strings.                                        |
   *   | title_text_max_length     | Integer. Minimum length of the title field                    |
   *   | title_text_min_length     | Integer. Minimum length of the title field                    |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_%28subreddit%29_post_requirements">here</a>
   */
  public static final Endpoint GET_API_V1_SUBREDDIT_POST_REQUIREMENTS =
        new Endpoint("api", "v1", "{subreddit}", "post_requirements");
  /**
   * Return information about the subreddit.<br>
   * Data includes the subscriber count, description, and header image.<br>
   * This endpoint is a {@code Subreddit}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT =
        new Endpoint("r", "{subreddit}", "about");
  /**
   * Get the current settings of a subreddit.<br>
   * In the API, this returns the current settings of the subreddit as used by /api/site_admin. On
   * the HTML site, it will display a form for editing the subreddit.
   * <pre>
   *   | Parameter | Description            |
   *   | --------- | ---------------------- |
   *   | created   | one of 'true', 'false' |
   *   | location  |                        |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about_edit">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_EDIT =
        new Endpoint("r", "{subreddit}", "about", "edit");
  /**
   * Get the rules for the current subreddit.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about_rules">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_RULES =
        new Endpoint("r", "{subreddit}", "about", "rules");
  /**
   * Get the traffic of a subreddit. I.e. the number of submissions, comments and overall activity
   * over time.
   * <pre>
   *   | Parameter | Description                             |
   *   | --------- | --------------------------------------- |
   *   | num       | an integer between 1 and 2 (default: 1) |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about_traffic">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_TRAFFIC =
        new Endpoint("r", "{subreddit}", "about", "traffic");
  /**
   * Get the sidebar for the current subreddit.<br>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sidebar">here</a>
   * @deprecated Deprecated in favor of {@code Subreddit#getDescription()}.
   */
  @Deprecated
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_SIDEBAR =
        new Endpoint("r", "{subreddit}", "about", "sidebar");
  /**
   * Get the sidebar for the current subreddit.<br>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sidebar">here</a>
   * @deprecated Deprecated in favor of {@code Subreddit#getDescription()}.
   */
  @Deprecated
  public static final Endpoint GET_ABOUT_SIDEBAR =
        new Endpoint("about", "sidebar");
  /**
   * Redirect to one of the posts stickied in the current subreddit.<br>
   * The {@code num} argument can be used to select a specific sticky, and will default to 1
   * (the top sticky) if not specified. Will 404 if there is not currently a sticky post in this
   * subreddit.<br>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   * <pre>
   *   | Parameter | Description                             |
   *   | --------- | --------------------------------------- |
   *   | num       | an integer between 1 and 2 (default: 1) |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sticky">here</a>
   */
  public static final Endpoint GET_R_SUBREDDIT_ABOUT_STICKY =
        new Endpoint("r", "{subreddit}", "about", "sticky");
  /**
   * Redirect to one of the posts stickied in the current subreddit.<br>
   * The {@code num} argument can be used to select a specific sticky, and will default to 1
   * (the top sticky) if not specified. Will 404 if there is not currently a sticky post in this
   * subreddit.<br>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   * <pre>
   *   | Parameter | Description                             |
   *   | --------- | --------------------------------------- |
   *   | num       | an integer between 1 and 2 (default: 1) |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sticky">here</a>
   */
  public static final Endpoint GET_ABOUT_STICKY =
        new Endpoint("about", "sticky");
  /**
   * Get all subreddits.<br>
   * The {@code where} parameter chooses the order in which the subreddits are displayed.
   * {@code popular} sorts on the activity of the subreddit and the position of the subreddits can
   * shift around. {@code new} sorts the subreddits based on their creation date, newest first.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_%28where%29">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_WHERE =
        new Endpoint("subreddits", "{where}");
  /**
   * Get all subreddits.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_default">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_DEFAULT =
        new Endpoint(GET_SUBREDDITS_WHERE.getPath("default"));
  /**
   * Get all subreddits.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_gold">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_GOLD =
        new Endpoint(GET_SUBREDDITS_WHERE.getPath("gold"));
  /**
   * Get all subreddits.<br>
   * {@code popular} sorts on the activity of the subreddit and the position of the subreddits can
   * shift around.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_popular">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_POPULAR =
        new Endpoint(GET_SUBREDDITS_WHERE.getPath("popular"));
  /**
   * Get all subreddits.<br>
   * {@code new} sorts the subreddits based on their creation date, newest first.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_new">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_NEW =
        new Endpoint(GET_SUBREDDITS_WHERE.getPath("new"));
  /**
   * Search subreddits by title and description.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_search">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_SEARCH =
        new Endpoint("subreddits", "search");
  /**
   * Get subreddits the user has a relationship with.<br>
   * The where parameter chooses which subreddits are returned.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_%28where%29">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_WHERE =
        new Endpoint("subreddits", "mine", "{where}");
  /**
   * Get subreddits the user is an approved user in.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_contributor">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_CONTRIBUTOR =
        new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("contributor"));
  /**
   * Get subreddits the user is a moderator of.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_moderator">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_MODERATOR =
        new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("moderator"));
  /**
   * Get subreddits the user subscribed to subreddits that contain hosted video links.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_streams">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_STREAMS =
        new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("streams"));
  /**
   * Get subreddits the user is subscribed to.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_subscriber">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_SUBSCRIBER =
        new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("subscriber"));
  /**
   * Get all user subreddits.<br>
   * The {@code where} parameter chooses the order in which the subreddits are displayed.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_users_%28where%29">here</a>
   */
  public static final Endpoint GET_USERS_WHERE =
        new Endpoint("users", "{where}");
  /**
   * Get all user subreddits.<br>
   * {@code new} sorts the user subreddits based on their creation date, newest first.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see #GET_USERS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_users_new">here</a>
   */
  public static final Endpoint GET_USERS_NEW =
        new Endpoint(GET_USERS_WHERE.getPath("new"));
  /**
   * Get all user subreddits.<br>
   * {@code popular} sorts on the activity of the subreddit and the position of the subreddits can
   * shift around.<br>
   * This endpoint is a {@code Listing} of subreddits.
   * <pre>
   *   | Parameter | Description                         |
   *   | --------- | ----------------------------------- |
   *   | after     | fullname of a thing                 |
   *   | before    | fullname of a thing                 |
   *   | count     | a positive integer                  |
   *   |           | (default:0)                         |
   *   | limit     | the maximum number of items desired |
   *   |           | (default:25, maximum:100)           |
   *   | show      | the string 'all'                    |
   *   |           | (optional)                          |
   *   | sr_detail | expand subreddits                   |
   *   |           | (optional)                          |
   * </pre>
   *
   * @see #GET_USERS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_users_popular">here</a>
   */
  public static final Endpoint GET_USERS_POPULAR =
        new Endpoint(GET_USERS_WHERE.getPath("popular"));
  /**
   * Search user profiles by title and description.<br>
   * This endpoint is a {@code Listing} of accounts.
   * <pre>
   *   | Parameter        | Description                         |
   *   | ---------------- | ----------------------------------- |
   *   | after            | fullname of a thing                 |
   *   | before           | fullname of a thing                 |
   *   | count            | a positive integer                  |
   *   |                  | (default:0)                         |
   *   | limit            | the maximum number of items desired |
   *   |                  | (default:25, maximum:100)           |
   *   | q                |  search query                       |
   *   | search_query_id  | a uuid                              |
   *   | show             | the string 'all'                    |
   *   |                  | (optional)                          |
   *   | sort             | one of 'relevance', 'activity'      |
   *   | sr_detail        | expand subreddits                   |
   *   |                  | (optional)                          |
   *   | typeahead_active | boolean value or None               |
   * </pre>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_users_search">here</a>
   */
  public static final Endpoint GET_USERS_SEARCH =
        new Endpoint("users", "search");
}
