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

package zav.jrc.endpoint.subreddit;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.api.endpoint.Listings;
import zav.jrc.api.endpoint.Search;
import zav.jrc.api.endpoint.Subreddits;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.databind.ThingEntity;
import zav.jrc.databind.Things;
import zav.jrc.databind.core.ListingEntity;

/**
 * Representation of the Reddit front page (i.e. r/all).
 */
@NonNullByDefault
public class FrontPage {

  private final Client client;

  public FrontPage(Client client) {
    this.client = client;
  }

  // ---------//
  // Listings //
  // ---------//

  /**
   * Returns a stream over all links, sorted by {@code best}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_BEST
   */
  public Stream<LinkEntity> getBest(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_BEST) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code controversial}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_CONTROVERSIAL
   */
  public Stream<LinkEntity> getControversial(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_CONTROVERSIAL) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code hot}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_HOT
   */
  public Stream<LinkEntity> getHot(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_HOT) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code new}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_NEW
   */
  public Stream<LinkEntity> getNew(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_NEW) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over randomly selected links.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_RANDOM
   */
  public Stream<LinkEntity> getRandom() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_RANDOM) //
        .get();

    ThingEntity[] things = Things.transform(response, ThingEntity[].class);
    ListingEntity listing = Things.transformThing(things[0], ListingEntity.class);
    return Things.transformListingOfThings(listing, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code rising}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_RISING
   */
  public Stream<LinkEntity> getRising(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_RISING) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code top}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_TOP
   */
  public Stream<LinkEntity> getTop(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_TOP) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  // -------//
  // Search //
  // -------//

  /**
   * Returns a stream over all things matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Search#GET_SEARCH
   */
  public Stream<ThingEntity> search(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Search.GET_SEARCH) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }

  // -----------//
  // Subreddits //
  // -----------//

  /**
   * Returns a stream over all subreddits matching the title and description
   * provided by the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_SEARCH
   */
  public Stream<SubredditEntity> findSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_SEARCH) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all accounts corresponding to the user subreddits,
   * matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the accounts.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_SEARCH
   */
  public Stream<AccountEntity> findUsers(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_USERS_SEARCH) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, AccountEntity.class);
  }

  /**
   * Returns a stream over all subreddit names matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream of subreddit names.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SEARCH_REDDIT_NAMES
   */
  public Stream<String> searchRedditNames(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.POST_API_SEARCH_REDDIT_NAMES) //
        .withBody(Collections.emptyMap()) //
        .withParams(params) //
        .post();

    Map<?, ?> result = Things.transform(response, Map.class);
    List<?> values = Things.transform(result.get("names"), List.class);
    return values.stream().map(Objects::toString);
  }

  /**
   * Returns a stream over all subreddits that begin with the provided query
   * string.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SEARCH_SUBREDDITS
   */
  public Stream<SubredditEntity> searchSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.POST_API_SEARCH_SUBREDDITS) //
        .withBody(Collections.emptyMap()) //
        .withParams(params) //
        .post();

    Map<?, ?> result = Things.transform(response, Map.class);
    List<?> values = Things.transform(result.get("subreddits"), List.class);
    return values.stream().map(value -> Things.transform(value, SubredditEntity.class));
  }

  /**
   * Returns a stream over all subreddits matching the search parameters. The
   * stream may contain both subreddits and user-subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_API_SUBREDDIT_AUTOCOMPLETE_V2
   */
  public Stream<ThingEntity> getSubredditAutocomplete(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_API_SUBREDDIT_AUTOCOMPLETE_V2) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }

  /**
   * Returns a stream over all default subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_DEFAULT
   */
  public Stream<SubredditEntity> getDefaultSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_DEFAULT) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all subreddits that are only accessible by gilded
   * users.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_GOLD
   */
  public Stream<SubredditEntity> getGoldSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_GOLD) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all newly created subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_NEW
   */
  public Stream<SubredditEntity> getNewSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_NEW) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all popular subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_POPULAR
   */
  public Stream<SubredditEntity> getPopularSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_POPULAR) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all newly created user subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_NEW
   */
  public Stream<SubredditEntity> getNewUserSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_USERS_NEW).withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all popular user subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_POPULAR
   */
  public Stream<SubredditEntity> getPopularUserSubreddits(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_USERS_POPULAR) //
        .withParams(params).get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }
}
