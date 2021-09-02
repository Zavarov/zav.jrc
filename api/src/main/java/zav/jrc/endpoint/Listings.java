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
import zav.jrc.databind.Link;
import zav.jrc.databind.Subreddit;

/**
 * REST endpoint for the {@code Listings} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_listings">here</a>
 */
public final class Listings {
  private Listings() {}
  
  /**
   * Return a list of trending subreddits, link to the comment in r/trendingsubreddits, and the
   * comment count of that link.<p/>
   * Note: The actual endpoint deviates from the documentation as in that it ends with
   * {@code .json}. It also seems to be one of the few endpoints that can't be accessed via OAuth2.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_api_trending_subreddits">here</a>
   */
  public static final Endpoint GET_API_TRENDING_SUBREDDITS = new Endpoint("api", "trending_subreddits.json");
  /**
   * TODO What does it do?
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_best">here</a>
   */
  public static final Endpoint GET_BEST = new Endpoint("best");
  /**
   * Get a listing of links by fullname.<p/>
   * {@code names} is a list of fullnames for links separated by commas or spaces.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_by_id_%28names%29">here</a>
   */
  public static final Endpoint GET_BY_ID = new Endpoint("by_id", "{names}");
  /**
   * Get the comment tree for a given Link {@code article}.<p/>
   * If supplied, {@code comment} is the ID36 of a comment in the comment tree for {@code article}.
   * This comment will be the (highlighted) focal point of the returned view and context will be the
   * number of parents shown.<p/>
   * {@code depth} is the maximum depth of subtrees in the thread.<p/>
   * {@code limit} is the maximum number of comments to return.
   * TODO see also /api/morechildren
   * TODO see also /api/comment
   *
   * @see #GET_SUBREDDIT_COMMENTS
   * @see <a href="https://www.reddit.com/dev/api#GET_comments_%28article%29">here</a>
   */
  public static final Endpoint GET_COMMENTS = new Endpoint("comments", "{article}");
  /**
   * Get the comment tree for a given Link {@code article}.<p/>
   * If supplied, {@code comment} is the ID36 of a comment in the comment tree for {@code article}.
   * This comment will be the (highlighted) focal point of the returned view and context will be the
   * number of parents shown.<p/>
   * {@code depth} is the maximum depth of subtrees in the thread.<p/>
   * {@code limit} is the maximum number of comments to return.
   * TODO see also /api/morechildren
   * TODO see also /api/comment
   *
   * @see #GET_COMMENTS
   * @see <a href="https://www.reddit.com/dev/api#GET_comments_%28article%29">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_COMMENTS = new Endpoint("r", "{subreddit}", "comments", "{article}");
  /**
   * Return a list of other submissions of the same URL.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_duplicates_%28article%29">here</a>
   */
  public static final Endpoint GET_DUPLICATES = new Endpoint("duplicates", "{article}");
  /**
   * Get all links.<p/>
   * In case the user is logged in, the links will be fetched from all subscribed subreddits,
   * otherwise from the frontpage.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_hot">here</a>
   */
  public static final Endpoint GET_HOT = new Endpoint("hot");
  /**
   * Get all links from the given subreddit.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_hot">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_HOT = new Endpoint("r", "{subreddit}", "hot");
  /**
   * Get all links.<p/>
   * In case the user is logged in, the links will be fetched from all subscribed subreddits,
   * otherwise from the frontpage.<p/>
   * {@code new} sorts the subreddits based on their creation date, newest first.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_new">here</a>
   */
  public static final Endpoint GET_NEW = new Endpoint("new");
  /**
   * Get all links from the given subreddit.<p/>
   * {@code new} sorts the submissions based on their creation date, newest first.
   *
   * @see Subreddits#GET_SUBREDDITS_WHERE
   * @see <a href="https://www.reddit.com/dev/api#GET_new">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_NEW = new Endpoint("r", "{subreddit}", "new");
  /**
   * The Serendipity button. I.e. it fetches a random {@link Link}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_random">here</a>
   */
  public static final Endpoint GET_RANDOM = new Endpoint("random");
  /**
   * The Serendipity button. I.e. it fetches a random {@link Link} from the {@link Subreddit}.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_random">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_RANDOM = new Endpoint("r", "{subreddit}", "random");
  /**
   * Get all links.<p/>
   * In case the user is logged in, the links will be fetched from all subscribed subreddits,
   * otherwise from the frontpage.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_rising">here</a>
   */
  public static final Endpoint GET_RISING = new Endpoint("rising");
  /**
   * Get all links from the specified subreddit.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_rising">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_RISING = new Endpoint("r", "{subreddit}", "rising");
  /**
   * Get all links.<p/>
   * In case the user is logged in, the links will be fetched from all subscribed subreddits,
   * otherwise from the frontpage.<p/>
   * {@code sort} indicates the order in which the links are sorted.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_%28sort%29">here</a>
   */
  public static final Endpoint GET_SORT = new Endpoint("{sort}");
  /**
   * Get all links from the specified subreddit.<p/>
   * {@code sort} indicates the order in which the links are sorted.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_%28sort%29">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_SORT = new Endpoint("r", "{subreddit}", "{sort}");
  /**
   * Get all links.<p/>
   * In case the user is logged in, the links will be fetched from all subscribed subreddits,
   * otherwise from the frontpage.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_top">here</a>
   */
  public static final Endpoint GET_TOP = new Endpoint(GET_SORT.getPath("top"));
  /**
   * Get all links from the specified subreddit.
   *
   * @see <a href="https://www.reddit.com/dev/api#GET_top">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_TOP = new Endpoint(GET_SUBREDDIT_SORT.getPath("{subreddit}", "top"));
  /**
   * TODO What does it do?
   *
   * @see #GET_SORT
   * @see <a href="https://www.reddit.com/dev/api#GET_controversial">here</a>
   */
  public static final Endpoint GET_CONTROVERSIAL = new Endpoint(GET_SORT.getPath("controversial"));
  /**
   * TODO What does it do?
   *
   * @see #GET_SUBREDDIT_SORT
   * @see <a href="https://www.reddit.com/dev/api#GET_controversial">here</a>
   */
  public static final Endpoint GET_SUBREDDIT_CONTROVERSIAL = new Endpoint(GET_SUBREDDIT_SORT.getPath("{subreddit}", "controversial"));
}
