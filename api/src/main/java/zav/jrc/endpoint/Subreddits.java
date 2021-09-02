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
import zav.jrc.databind.Subreddit;
import zav.jrc.databind.UserList;
import zav.jrc.databind.core.Listing;

/**
 * REST endpoint for the {@code Subreddits} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_subreddits">here</a>
 */
public class Subreddits {
  /**
   * The endpoint for retrieving information about a subreddit with respect to a user.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_%28where%29">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_WHERE = new Endpoint("r", "{subreddit}", "about", "{where}");
  /**
   * The endpoint for retrieving information about a subreddit with respect to a user.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_%28where%29">here</a>
   */
  public static final Endpoint GET_ABOUT_WHERE = new Endpoint("about", "{where}");
  /**
   * All currently banned users.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_banned">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_BANNED = new Endpoint(GET_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "banned"));
  /**
   * All currently banned users.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_banned">here</a>
   */
  public static final Endpoint GET_ABOUT_BANNED = new Endpoint(GET_ABOUT_WHERE.getPath("banned"));
  /**
   * All contributors.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_contributors">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_CONTRIBUTORS = new Endpoint(GET_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "contributors"));
  /**
   * All contributors.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_contributors">here</a>
   */
  public static final Endpoint GET_ABOUT_CONTRIBUTORS = new Endpoint(GET_ABOUT_WHERE.getPath("contributors"));
  /**
   * All moderators.<p/>
   * This endpoint is a {@link UserList}.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_moderators">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_MODERATORS = new Endpoint(GET_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "moderators"));
  /**
   * All moderators.<p/>
   * This endpoint is a {@link UserList}.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_moderators">here</a>
   */
  public static final Endpoint GET_ABOUT_MODERATORS = new Endpoint(GET_ABOUT_WHERE.getPath("moderators"));
  /**
   * All currently muted users.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_muted">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_MUTED = new Endpoint(GET_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "muted"));
  /**
   * All currently muted users.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_muted">here</a>
   */
  public static final Endpoint GET_ABOUT_MUTED = new Endpoint(GET_ABOUT_WHERE.getPath("muted"));
  /**
   * All users banned from the wiki.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikibanned">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_WIKIBANNED = new Endpoint(GET_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "wikibanned"));
  /**
   * All users banned from the wiki.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikibanned">here</a>
   */
  public static final Endpoint GET_ABOUT_WIKIBANNED = new Endpoint(GET_ABOUT_WHERE.getPath("wikibanned"));
  /**
   * All users contributing to the wiki.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikicontributors">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_WIKICONTRIBUTORS = new Endpoint(GET_SUBREDDIT_ABOUT_WHERE.getPath("{subreddit}", "wikicontributors"));
  /**
   * All users contributing to the wiki.<p/>
   * This endpoint is a list of users.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_about_wikicontributors">here</a>
   */
  public static final Endpoint GET_ABOUT_WIKICONTRIBUTORS = new Endpoint(GET_ABOUT_WHERE.getPath("wikicontributors"));
  /**
   * Remove the subreddit's custom mobile banner.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_banner">here</a>
   */
  public static final Endpoint POST_SUBREDDIT_DELETE_SUBREDDIT_BANNER = new Endpoint("r", "{subreddit}", "api", "delete_sr_banner");
  /**
   * Remove the subreddit's custom mobile banner.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_banner">here</a>
   */
  public static final Endpoint POST_DELETE_SUBREDDIT_BANNER = new Endpoint("api", "delete_sr_banner");
  /**
   * Remove the subreddit's custom header image.<p/>
   * The sitewide-default header image will be shown again after this call.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_header">here</a>
   */
  public static final Endpoint POST_SUBREDDIT_DELETE_SUBREDDIT_HEADER = new Endpoint("r", "{subreddit}", "api", "delete_sr_header");
  /**
   * Remove the subreddit's custom header image.<p/>
   * The sitewide-default header image will be shown again after this call.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_header">here</a>
   */
  public static final Endpoint POST_DELETE_SUBREDDIT_HEADER = new Endpoint("api", "delete_sr_header");
  /**
   * Remove the subreddit's custom mobile icon.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_icon">here</a>
   */
  public static final Endpoint POST_SUBREDDIT_DELETE_SUBREDDIT_ICON = new Endpoint("r", "{subreddit}", "api", "delete_sr_icon");
  /**
   * Remove the subreddit's custom mobile icon.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_icon">here</a>
   */
  public static final Endpoint POST_DELETE_SUBREDDIT_ICON = new Endpoint("api", "delete_sr_icon");
  /**
   * Remove an image from the subreddit's custom image set.<p/>
   * The image will no longer count against the subreddit's image limit. However, the actual image
   * data may still be accessible for an unspecified amount of time. If the image is currently
   * referenced by the subreddit's stylesheet, that stylesheet will no longer validate and won't be
   * editable until the image reference is removed.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code img_name}</th><th>a valid subreddit image name</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_img">here</a>
   */
  public static final Endpoint POST_SUBREDDIT_DELETE_SUBREDDIT_IMAGE = new Endpoint("r", "{subreddit}", "api", "delete_sr_img");
  /**
   * Remove an image from the subreddit's custom image set.<p/>
   * The image will no longer count against the subreddit's image limit. However, the actual image
   * data may still be accessible for an unspecified amount of time. If the image is currently
   * referenced by the subreddit's stylesheet, that stylesheet will no longer validate and won't be
   * editable until the image reference is removed.<p/>
   * This endpoints returns a jQuery response.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code img_name}</th><th>a valid subreddit image name</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_delete_sr_img">here</a>
   */
  public static final Endpoint POST_DELETE_SUBREDDIT_IMAGE = new Endpoint("api", "delete_sr_img");
  /**
   * Return subreddits recommended for the given subreddit(s).<p/>
   * Gets a list of subreddits recommended for {@code srnames}, filtering out any that appear in the
   * optional {@code omit} param.
   * <table>
   *   <tr><th>{@code omit}</th><th>comma-delimited list of subreddit names</th></tr>
   *   <tr><th>{@code over_18}</th><th>boolean value</th></tr>
   *   <tr><th>{@code srnames}</th><th>comma-delimited list of subreddit names</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_recommend_sr_%28srnames%29">here</a>
   */
  @Deprecated
  public static final Endpoint GET_RECOMMEND_SUBREDDITS = new Endpoint("api", "recommend", "sr", "{srnames}");
  /**
   * List subreddit names that begin with a query string.<p/>
   * Subreddits whose names begin with {@code query} will be returned. If {@code include_over_18} is
   * {@code false}, subreddits with over-18 content restrictions will be filtered from the
   * results.<p/>
   * If {@code include_unadvertisable} is {@code false}, subreddits that have {@code hide_ad} set to
   * {@code true} or are on the {@code anti_ads_subreddits} list will be filtered.<p/>
   * If {@code exact} is {@code true}, only an exact match will be returned. Exact matches are
   * inclusive of {@code over_18} subreddits, but not {@code hide_ad} subreddits when
   * {@code include_unadvertisable} is {@code false}.<p/>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      {
   *          "names": ["reddit.com", "redditmobile"]
   *      }
   * }
   * </pre>
   * <table>
   *   <tr><th>{@code exact}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_over_18}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_unadvertisable}</th><th>boolean value</th></tr>
   *   <tr><th>{@code query}</th><th>a string up to 50 characters long, consisting of printable characters</th></tr>
   *   <tr><th>{@code search_query_id}</th><th>a uuid</th></tr>
   *   <tr><th>{@code typeahead_active}</th><th>boolean value or {@code None}</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_search_reddit_names">here</a>
   */
  public static final Endpoint GET_SEARCH_REDDIT_NAMES = new Endpoint("api", "search_reddit_names");
  /**
   * List subreddit names that begin with a query string.<p/>
   * Subreddits whose names begin with {@code query} will be returned. If {@code include_over_18} is
   * {@code false}, subreddits with over-18 content restrictions will be filtered from the
   * results.<p/>
   * If {@code include_unadvertisable} is {@code false}, subreddits that have {@code hide_ad} set to
   * {@code true} or are on the {@code anti_ads_subreddits} list will be filtered.<p/>
   * If {@code exact} is {@code true}, only an exact match will be returned. Exact matches are
   * inclusive of {@code over_18} subreddits, but not {@code hide_ad} subreddits when
   * {@code include_unadvertisable} is {@code false}.<p/>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      {
   *          "names": ["reddit.com", "redditmobile"]
   *      }
   * }
   * </pre>
   * <table>
   *   <tr><th>{@code exact}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_over_18}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_unadvertisable}</th><th>boolean value</th></tr>
   *   <tr><th>{@code query}</th><th>a string up to 50 characters long, consisting of printable characters</th></tr>
   *   <tr><th>{@code search_query_id}</th><th>a uuid</th></tr>
   *   <tr><th>{@code typeahead_active}</th><th>boolean value or {@code None}</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_search_reddit_names">here</a>
   */
  public static final Endpoint POST_SEARCH_REDDIT_NAMES = new Endpoint("api", "search_reddit_names");
  /**
   * List subreddit names that begin with a query string.<p/>
   * Subreddits whose names begin with {@code query} will be returned. If {@code include_over_18} is
   * {@code false}, subreddits with over-18 content restrictions will be filtered from the
   * results.<p/>
   * If {@code include_unadvertisable} is {@code false}, subreddits that have {@code hide_ad} set to
   * {@code true} or are on the {@code anti_ads_subreddits} list will be filtered.<p/>
   * If {@code exact} is {@code true}, only an exact match will be returned. Exact matches are
   * inclusive of {@code over_18} subreddits, but not {@code hide_ad} subreddits when
   * {@code include_unadvertisable} is {@code false}.<p/>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      {
   *          "names": ["reddit.com", "redditmobile"]
   *      }
   * }
   * </pre>
   * <table>
   *   <tr><th>{@code exact}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_over_18}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_unadvertisable}</th><th>boolean value</th></tr>
   *   <tr><th>{@code query}</th><th>a string up to 50 characters long, consisting of printable characters</th></tr>
   *   <tr><th>{@code search_query_id}</th><th>a uuid</th></tr>
   *   <tr><th>{@code typeahead_active}</th><th>boolean value or {@code None}</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_search_subreddits">here</a>
   */
  public static final Endpoint POST_SEARCH_SUBREDDITS = new Endpoint("api", "search_subreddits");
  /**
   * Create or configure a subreddit.<p/>
   * If {@code sr} is specified, the request will attempt to modify the specified subreddit. If not,
   * a subreddit with name {@code name} will be created.<p/>
   * This endpoint expects all values to be supplied on every request. If modifying a subset of
   * options, it may be useful to get the current settings from /about/edit.json first.<p/>
   * For backwards compatibility, {@code description} is the sidebar text and
   * {@code public_description} is the publicly visible subreddit description.<p/>
   * Most of the parameters for this endpoint are identical to options visible in the user interface
   * and their meanings are best explained there.
   * <table>
   *   <tr><th>{@code admin_override_spam_comments}</th><th>boolean value</th></tr>
   *   <tr><th>{@code admin_override_spam_links}</th><th>boolean value</th></tr>
   *   <tr><th>{@code admin_override_spam_selfposts}</th><th>boolean value</th></tr>
   *   <tr><th>{@code all_original_content}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_chat_post_creation}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_discovery}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_galleries}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_images}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_polls}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_post_crossposts}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_predictions}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_predictions_tournament}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_top}</th><th>boolean value</th></tr>
   *   <tr><th>{@code allow_videos}</th><th>boolean value</th></tr>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code automated_reporting_level_abuse}</th><th>an integer between 0 and 3</th></tr>
   *   <tr><th>{@code automated_reporting_level_hate}</th><th>an integer between 0 and 3</th></tr>
   *   <tr><th>{@code collapse_deleted_comments}</th><th>boolean value</th></tr>
   *   <tr><th>{@code comment_score_hide_mins}</th><th>an integer between 0 and 1440 (default: 0)</th></tr>
   *   <tr><th>{@code crowd_control_chat_level}</th><th>an integer between 0 and 3</th></tr>
   *   <tr><th>{@code crowd_control_level}</th><th>an integer between 0 and 3</th></tr>
   *   <tr><th>{@code crowd_control_mode}</th><th>boolean value</th></tr>
   *   <tr><th>{@code description}</th><th>raw markdown text</th></tr>
   *   <tr><th>{@code disable_contributor_requests}</th><th>boolean value</th></tr>
   *   <tr><th>{@code exclude_banned_modqueue}</th><th>boolean value</th></tr>
   *   <tr><th>{@code free_form_reports}</th><th>boolean value</th></tr>
   *   <tr><th>{@code g-recaptcha-response}</th><th></th></tr>
   *   <tr><th>{@code header-title}</th><th>a string no longer than 500 characters</th></tr>
   *   <tr><th>{@code hide_ads}</th><th>boolean value</th></tr>
   *   <tr><th>{@code key_color}</th><th>a 6-digit rgb hex color, e.g. #AABBCC</th></tr>
   *   <tr><th>{@code lang}</th><th>a valid IETF language tag (underscore separated)</th></tr>
   *   <tr><th>{@code link_type}</th><th>one of ({@code any}, {@code link}, {@code self})</th></tr>
   *   <tr><th>{@code name}</th><th>subreddit name</th></tr>
   *   <tr><th>{@code new_pinned_post_pns_enabled}</th><th>boolean value</th></tr>
   *   <tr><th>{@code original_content_tag_enabled}</th><th>boolean value</th></tr>
   *   <tr><th>{@code over_18}</th><th>boolean value</th></tr>
   *   <tr><th>{@code prediction_leaderboard_entry_type}</th><th>an integer between 0 and 2</th></tr>
   *   <tr><th>{@code public_description}</th><th>raw markdown text</th></tr>
   *   <tr><th>{@code restrict_commenting}</th><th>boolean value</th></tr>
   *   <tr><th>{@code restrict_posting}</th><th>boolean value</th></tr>
   *   <tr><th>{@code show_media}</th><th>boolean value</th></tr>
   *   <tr><th>{@code show_media_preview}</th><th>boolean value</th></tr>
   *   <tr><th>{@code spam_comments}</th><th>one of ({@code low}, {@code high}, {@code all})</th></tr>
   *   <tr><th>{@code spam_links}</th><th>one of ({@code low}, {@code high}, {@code all})</th></tr>
   *   <tr><th>{@code spam_selfposts}</th><th>one of ({@code low}, {@code high}, {@code all})</th></tr>
   *   <tr><th>{@code spoilers_enabled}</th><th>boolean value</th></tr>
   *   <tr><th>{@code sr}</th><th>fullname of a thing</th></tr>
   *   <tr><th>{@code submit_link_label}</th><th>a string no longer than 60 characters</th></tr>
   *   <tr><th>{@code submit_text}</th><th>raw markdown text</th></tr>
   *   <tr><th>{@code submit_text_label}</th><th>a string no longer than 60 characters</th></tr>
   *   <tr><th>{@code suggested_comment_sort}</th><th>one of ({@code confidence}, {@code top}, {@code new}, {@code controversial}, {@code old}, {@code random}, {@code qa}, {@code live})</th></tr>
   *   <tr><th>{@code title}</th><th>a string no longer than 100 characters</th></tr>
   *   <tr><th>{@code toxicity_threshold_chat_level}</th><th>an integer between 0 and 1</th></tr>
   *   <tr><th>{@code type}</th><th>one of ({@code gold_restricted}, {@code archived}, {@code restricted}, {@code private}, {@code employees_only}, {@code gold_only}, {@code public}, {@code user})</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <tr><th>{@code user_flair_pns_enabled}</th><th>boolean value</th></tr>
   *   <tr><th>{@code welcome_message_enabled}</th><th>boolean value</th></tr>
   *   <tr><th>{@code welcome_message_text}</th><th>raw markdown text</th></tr>
   *   <tr><th>{@code wiki_edit_age}</th><th>an integer between 0 and 36600 (default: 0)</th></tr>
   *   <tr><th>{@code wiki_edit_karma}</th><th>an integer between 0 and 1000000000 (default: 0)</th></tr>
   *   <tr><th>{@code wikimode}</th><th>one of ({@code disabled}, {@code modonly}, {@code anyone})</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_site_admin">here</a>
   */
  public static final Endpoint POST_SITE_ADMIN = new Endpoint("api", "site_admin");
  /**
   * Get the submission text for the subreddit.<p/>
   * This text is set by the subreddit moderators and intended to be displayed on the submission
   * form.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_submit_text">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_SUBMIT_TEXT = new Endpoint("r", "{subreddit}", "api", "submit_text");
  /**
   * Get the submission text for the subreddit.<p/>
   * This text is set by the subreddit moderators and intended to be displayed on the submission
   * form.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_submit_text">here</a>
   */
  public static final Endpoint GET_SUBMIT_TEXT = new Endpoint("api", "submit_text");
  /**
   * Return a list of subreddits and data for subreddits whose names start with 'query'.<p/>
   * Uses typeahead endpoint to recieve the list of subreddits names. Typeahead provides exact
   * matches, typo correction, fuzzy matching and boosts subreddits to the top that the user is
   * subscribed to.<p/>
   * Example response for this endpoint:
   * <pre>
   * {@code
   *      "subreddits": [
   *          {
   *              "numSubscribers": 0,
   *              "name": "u_reddit",
   *              "allowedPostTypes": {"images": true, "text": true, "videos": true, "links": true, "spoilers": true},
   *              "id": "t5_hv5dz",
   *              "primaryColor": "",
   *              "communityIcon": "",
   *              "icon": "https://styles.redditmedia.com/t5_hv5dz/styles/profileIcon_snoo8658e16c-55fa-486f-b7c7-00726de2e742-headshot.png"
   *          }
   *      ]
   * }
   * </pre>
   * <table>
   *   <tr><th>{@code include_over_18}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_profiles}</th><th>boolean value</th></tr>
   *   <tr><th>{@code query}</th><th>a string up to 25 characters long, consisting of printable characters.</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_subreddit_autocomplete">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_AUTOCOMPLETE = new Endpoint("api", "subreddit_autocomplete");
  /**
   * Return a list of subreddits and data for subreddits whose names start with 'query'.<p/>
   * Uses typeahead endpoint to recieve the list of subreddits names. Typeahead provides exact
   * matches, typo correction, fuzzy matching and boosts subreddits to the top that the user is
   * subscribed to.<p/>
   * This endpoint is a listing of things. Those things can either be subreddits or accounts, i.e.
   * personal subreddits.
   * <table>
   *   <tr><th>{@code include_over_18}</th><th>boolean value</th></tr>
   *   <tr><th>{@code include_profiles}</th><th>boolean value</th></tr>
   *   <tr><th>{@code limit}</th><th>an integer between 1 and 10 (default: 5)</th></tr>
   *   <tr><th>{@code query}</th><th>a string up to 25 characters long, consisting of printable characters.</th></tr>
   *   <tr><th>{@code search_query_id}</th><th>a uuid</th></tr>
   *   <tr><th>{@code typeahead_active}</th><th>boolean value or {@code None}</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_subreddit_autocomplete_v2">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_AUTOCOMPLETE_V2 = new Endpoint("api", "subreddit_autocomplete_v2");
  /**
   * Update a subreddit's stylesheet.<p/>
   * {@code op} should be {@code save} to update the contents of the stylesheet.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code op}</th><th>one of ({@code save}, {@code preview})</th></tr>
   *   <tr><th>{@code reason}</th><th>a string up to 256 characters long, consisting of printable characters.</th></tr>
   *   <tr><th>{@code stylesheet_contents}</th><th>the new stylesheet content</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_subreddit_stylesheet">here</a>
   */
  public static final Endpoint POST_SUBREDDIT_SUBREDDIT_STYLESHEET = new Endpoint("r", "{subreddit}", "api", "subreddit_stylesheet");
  /**
   * Update a subreddit's stylesheet.<p/>
   * {@code op} should be {@code save} to update the contents of the stylesheet.
   * <table>
   *   <tr><th>{@code api_type}</th><th>the string {@code json}</th></tr>
   *   <tr><th>{@code op}</th><th>one of ({@code save}, {@code preview})</th></tr>
   *   <tr><th>{@code reason}</th><th>a string up to 256 characters long, consisting of printable characters.</th></tr>
   *   <tr><th>{@code stylesheet_contents}</th><th>the new stylesheet content</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_subreddit_stylesheet">here</a>
   */
  public static final Endpoint POST_SUBREDDIT_STYLESHEET = new Endpoint("api", "subreddit_stylesheet");
  /**
   * Subscribe to or unsubscribe from a subreddit.<p/>
   * To subscribe, {@code action} should be {@code sub}. To unsubscribe, {@code action} should be
   * {@code unsub}. The user must have access to the subreddit to be able to subscribe to it.<p/>
   * The {@code skip_initial_defaults} param can be set to True to prevent automatically subscribing
   * the user to the current set of defaults when they take their first subscription action.<p/>
   * Attempting to set it for an unsubscribe action will result in an error.
   * <table>
   *   <tr><th>{@code action}</th><th>one of ({@code sub}, {@code unsub})</th></tr>
   *   <tr><th>{@code action_source}</th><th>one of ({@code o}, {@code n}, {@code b}, {@code o}, {@code a}, {@code r}, {@code d}, {@code i}, {@code n}, {@code g})</th></tr>
   *   <tr><th>{@code skip_initial_defaults}</th><th>boolean value</th></tr>
   *   <tr><th>{@code sr / sr_name}</th><th>A comma-separated list of subreddit <i>fullnames</i> (when using the "sr" parameter), or of subreddit names (when using the "sr_name" parameter).</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_subscribe">here</a>
   */
  public static final Endpoint POST_SUBSCRIBE = new Endpoint("api", "subscribe");
  /**
   * Add or replace a subreddit image, custom header logo, custom mobile icon, or custom mobile
   * banner.
   * <ul>
   *   <li>If the {@code upload_type} value is {@code img}, an image for use in the subreddit stylesheet is uploaded with the name specified in {@code name}.</li>
   *   <li>If the {@code upload_type} value is {@code header} then the image uploaded will be the subreddit's new logo and {@code name} will be ignored.</li>
   *   <li>If the {@code upload_type} value is {@code icon} then the image uploaded will be the subreddit's new mobile icon and {@code name} will be ignored.</li>
   *   <li>If the {@code upload_type} value is {@code banner} then the image uploaded will be the subreddit's new mobile banner and {@code name} will be ignored.</li>
   * </ul>
   * For backwards compatibility, if {@code upload_type} is not specified, the {@code header} field
   * will be used instead:
   * <ul>
   *   <li>If the {@code header} field has value {@code 0}, then {@code upload_type} is {@code img}.</li>
   *   <li>If the {@code header} field has value {@code 1}, then {@code upload_type} is {@code header}.</li>
   * </ul>
   * The {@code img_type} field specifies whether to store the uploaded image as a PNG or JPEG.<p/>
   * Subreddits have a limited number of images that can be in use at any given time. If no image
   * with the specified name already exists, one of the slots will be consumed.<p/>
   * If an image with the specified name already exists, it will be replaced. This does not affect
   * the stylesheet immediately, but will take effect the next time the stylesheet is saved.<p/>
   * <table>
   *   <tr><th>{@code file}</th><th>file upload with maximum size of 500 KiB</th></tr>
   *   <tr><th>{@code formid}</th><th>(optional) can be ignored</th></tr>
   *   <tr><th>{@code header}</th><th>an integer between 0 and 1</th></tr>
   *   <tr><th>{@code img_type}</th><th>one of png or jpg (default: png)</th></tr>
   *   <tr><th>{@code name}</th><th>a valid subreddit image name</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <tr><th>{@code upload_type}</th><th>one of ({@code img}, {@code header}, {@code icon}, {@code banner})</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_upload_sr_img">here</a>
   */
  public static final Endpoint POST_SUBREDDIT_UPLOAD_SUBREDDIT_IMAGE = new Endpoint("r", "{subreddit}", "api", "upload_sr_img");
  /**
   * Add or replace a subreddit image, custom header logo, custom mobile icon, or custom mobile
   * banner.
   * <ul>
   *   <li>If the {@code upload_type} value is {@code img}, an image for use in the subreddit stylesheet is uploaded with the name specified in {@code name}.</li>
   *   <li>If the {@code upload_type} value is {@code header} then the image uploaded will be the subreddit's new logo and {@code name} will be ignored.</li>
   *   <li>If the {@code upload_type} value is {@code icon} then the image uploaded will be the subreddit's new mobile icon and {@code name} will be ignored.</li>
   *   <li>If the {@code upload_type} value is {@code banner} then the image uploaded will be the subreddit's new mobile banner and {@code name} will be ignored.</li>
   * </ul>
   * For backwards compatibility, if {@code upload_type} is not specified, the {@code header} field
   * will be used instead:
   * <ul>
   *   <li>If the {@code header} field has value {@code 0}, then {@code upload_type} is {@code img}.</li>
   *   <li>If the {@code header} field has value {@code 1}, then {@code upload_type} is {@code header}.</li>
   * </ul>
   * The {@code img_type} field specifies whether to store the uploaded image as a PNG or JPEG.<p/>
   * Subreddits have a limited number of images that can be in use at any given time. If no image
   * with the specified name already exists, one of the slots will be consumed.<p/>
   * If an image with the specified name already exists, it will be replaced. This does not affect
   * the stylesheet immediately, but will take effect the next time the stylesheet is saved.
   * <table>
   *   <tr><th>{@code file}</th><th>file upload with maximum size of 500 KiB</th></tr>
   *   <tr><th>{@code formid}</th><th>(optional) can be ignored</th></tr>
   *   <tr><th>{@code header}</th><th>an integer between 0 and 1</th></tr>
   *   <tr><th>{@code img_type}</th><th>one of png or jpg (default: png)</th></tr>
   *   <tr><th>{@code name}</th><th>a valid subreddit image name</th></tr>
   *   <tr><th>{@code uh / X-Modhash header}</th><th>a <i>modhash</i></th></tr>
   *   <tr><th>{@code upload_type}</th><th>one of ({@code img}, {@code header}, {@code icon}, {@code banner})</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#POST_api_upload_sr_img">here</a>
   */
  public static final Endpoint POST_UPLOAD_SUBREDDIT_IMAGE = new Endpoint("api", "upload_sr_img");
  /**
   * Fetch moderator-designated requirements to post to the subreddit.<p/>
   * Moderators may enable certain restrictions, such as minimum title length, when making a
   * submission to their subreddit.<p/>
   * Clients may use the values returned by this endpoint to pre-validate fields before making a
   * request to POST /api/submit. This may allow the client to provide a better user experience to
   * the user, for example by creating a text field in their app that does not allow the user to
   * enter more characters than the max title length.<p/>
   * A non-exhaustive list of possible requirements a moderator may enable:
   * <ul>
   *   <li>{@code body_blacklisted_strings}: List of strings. Users may not submit posts that contain these words.</li>
   *   <li>{@code body_restriction_policy}: String. One of "required", "notAllowed", or "none", meaning that a self-post body is required, not allowed, or optional, respectively.</li>
   *   <li>{@code domain_blacklist}: List of strings. Users may not submit links to these domains</li>
   *   <li>{@code domain_whitelist}: List of strings. Users submissions MUST be from one of these domains</li>
   *   <li>{@code is_flair_required}: Boolean. If True, flair must be set at submission time.</li>
   *   <li>{@code title_blacklisted_strings}: List of strings. Submission titles may NOT contain any of the listed strings.</li>
   *   <li>{@code title_required_strings}: List of strings. Submission title MUST contain at least ONE of the listed strings.</li>
   *   <li>{@code title_text_max_length}: Integer. Maximum length of the title field.</li>
   *   <li>{@code title_text_min_length}: Integer. Minimum length of the title field.</li>
   * </ul>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_v1_%28subreddit%29_post_requirements">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_POST_REQUIREMENTS = new Endpoint("api", "v1", "{subreddit}", "post_requirements");
  /**
   * Return information about the subreddit.<p/>
   * Data includes the subscriber count, description, and header image.<p/>
   * This endpoint is a {@link Subreddit}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT = new Endpoint("r", "{subreddit}", "about");
  /**
   * Get the current settings of a subreddit.<p/>
   * In the API, this returns the current settings of the subreddit as used by /api/site_admin. On
   * the HTML site, it will display a form for editing the subreddit.
   * <table>
   *   <tr><th>{@code created}</th><th>one of ({@code true}, {@code false})</th></tr>
   *   <tr><th>{@code location}</th><th></th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about_edit">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_EDIT = new Endpoint("r", "{subreddit}", "about", "edit");
  /**
   * Get the rules for the current subreddit.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about_rules">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_RULES = new Endpoint("r", "{subreddit}", "about", "rules");
  /**
   * Get the traffic of a subreddit. I.e. the number of submissions, comments and overall activity
   * over time.
   * <table>
   *   <tr><th>{@code num}</th><th>an integer between 1 and 2 (default: 1)</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_r_%28subreddit%29_about_traffic">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_TRAFFIC = new Endpoint("r", "{subreddit}", "about", "traffic");
  /**
   * Get the sidebar for the current subreddit.<p/>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sidebar">here</a>
   * @deprecated Deprecated in favor of {@link Subreddit#getDescription()}.
   */
  @Deprecated
  public static final Endpoint GET_SUBREDDIT_SIDEBAR = new Endpoint("r", "{subreddit}", "about", "sidebar");
  /**
   * Get the sidebar for the current subreddit.<p/>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sidebar">here</a>
   * @deprecated Deprecated in favor of {@link Subreddit#getDescription()}.
   */
  @Deprecated
  public static final Endpoint GET_SIDEBAR = new Endpoint("about", "sidebar");
  /**
   * Redirect to one of the posts stickied in the current subreddit.<p/>
   * The {@code num} argument can be used to select a specific sticky, and will default to 1
   * (the top sticky) if not specified. Will 404 if there is not currently a sticky post in this
   * subreddit.<p/>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   * <table>
   *   <tr><th>{@code num}</th><th>an integer between 1 and 2 (default: 1)</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sticky">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_ABOUT_STICKY = new Endpoint("r", "{subreddit}", "about", "sticky");
  /**
   * Redirect to one of the posts stickied in the current subreddit.<p/>
   * The {@code num} argument can be used to select a specific sticky, and will default to 1
   * (the top sticky) if not specified. Will 404 if there is not currently a sticky post in this
   * subreddit.<p/>
   * Note: The endpoint, according to the <b>official API</b>, is inaccurate and the endpoint has
   * been moved.
   * <table>
   *   <tr><th>{@code num}</th><th>an integer between 1 and 2 (default: 1)</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_sticky">here</a>
   */
  public static final Endpoint GET_ABOUT_STICKY = new Endpoint("about", "sticky");
  /**
   * Get all subreddits.<p/>
   * The {@code where} parameter chooses the order in which the subreddits are displayed.
   * {@code popular} sorts on the activity of the subreddit and the position of the subreddits can
   * shift around. {@code new} sorts the subreddits based on their creation date, newest first.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_%28where%29">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_WHERE = new Endpoint("subreddits", "{where}");
  /**
   * Get all subreddits.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_default">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_DEFAULT = new Endpoint(GET_SUBREDDITS_WHERE.getPath("default"));
  /**
   * Get all subreddits.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_gold">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_GOLD = new Endpoint(GET_SUBREDDITS_WHERE.getPath("gold"));
  /**
   * Get all subreddits.<p/>
   * {@code popular} sorts on the activity of the subreddit and the position of the subreddits can
   * shift around.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_popular">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_POPULAR = new Endpoint(GET_SUBREDDITS_WHERE.getPath("popular"));
  /**
   * Get all subreddits.<p/>
   * {@code new} sorts the subreddits based on their creation date, newest first.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see #GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_new">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_NEW = new Endpoint(GET_SUBREDDITS_WHERE.getPath("new"));
  /**
   * Search subreddits by title and description.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_search">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_SEARCH = new Endpoint("subreddits", "search");
  /**
   * Get subreddits the user has a relationship with.<p/>
   * The where parameter chooses which subreddits are returned.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_%28where%29">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_WHERE = new Endpoint("subreddits", "mine", "{where}");
  /**
   * Get subreddits the user is an approved user in.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_contributor">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_CONTRIBUTOR = new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("contributor"));
  /**
   * Get subreddits the user is a moderator of.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_moderator">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_MODERATOR = new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("moderator"));
  /**
   * Get subreddits the user subscribed to subreddits that contain hosted video links.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_streams">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_STREAMS = new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("streams"));
  /**
   * Get subreddits the user is subscribed to.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_subreddits_mine_subscriber">here</a>
   */
  public static final Endpoint GET_SUBREDDITS_MINE_SUBSCRIBER = new Endpoint(GET_SUBREDDITS_MINE_WHERE.getPath("subscriber"));
  /**
   * Get all user subreddits.<p/>
   * The {@code where} parameter chooses the order in which the subreddits are displayed.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see <a href="https://www.reddit.com/dev/api#GET_users_%28where%29">here</a>
   */
  public static final Endpoint GET_USERS_WHERE = new Endpoint("users", "{where}");
  /**
   * Get all user subreddits.<p/>
   * {@code new} sorts the user subreddits based on their creation date, newest first.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see #GET_USERS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_users_new">here</a>
   */
  public static final Endpoint GET_USERS_NEW = new Endpoint(GET_USERS_WHERE.getPath("new"));
  /**
   * Get all user subreddits.<p/>
   * {@code popular} sorts on the activity of the subreddit and the position of the subreddits can
   * shift around.<p/>
   * This endpoint is a {@link Listing} of subreddits.
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
   * @see #GET_USERS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_users_popular">here</a>
   */
  public static final Endpoint GET_USERS_POPULAR = new Endpoint(GET_USERS_WHERE.getPath("popular"));
  /**
   * Search user profiles by title and description.<p/>
   * This endpoint is a {@link Listing} of accounts.
   * <table>
   *   <tr><th>{@code after}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code before}</th><th><i>fullname</i> of a thing</th></tr>
   *   <tr><th>{@code count}</th><th>a positive integer (default:0)</th></tr>
   *   <tr><th>{@code limit}</th><th>the maximum number of items desired (default:25, maximum:100)</th></tr>
   *   <tr><th>{@code q}</th><th>a search query</th></tr>
   *   <tr><th>{@code search_query_id}</th><th>a uuid</th></tr>
   *   <tr><th>{@code show}</th><th>(optional) the string {@code all}</th></tr>
   *   <tr><th>{@code sort}</th><th>one of ({@code relevance}, {@code activity})</th></tr>
   *   <tr><th>{@code sr_detail}</th><th>(optional) expand subreddits</th></tr>
   *   <tr><th>{@code typeahead_active}</th><th>boolean value or {@code None}</th></tr>
   *   <caption>Supported Arguments</caption>
   * </table>
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_users_search">here</a>
   */
  public static final Endpoint GET_USERS_SEARCH = new Endpoint("users", "search");
}
