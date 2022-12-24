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

package zav.jrc.endpoint.subreddit;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import javax.inject.Inject;
import okhttp3.Request;
import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.api.Things;
import zav.jrc.api.endpoint.Listings;
import zav.jrc.api.endpoint.Search;
import zav.jrc.api.endpoint.Subreddits;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.http.Parameter;
import zav.jrc.client.http.RequestBuilder;
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.databind.ThingEntity;
import zav.jrc.databind.core.ListingEntity;

/**
 * Representation of the Reddit front page (i.e. r/all).
 */
@NonNullByDefault
public class FrontPage {

  private final Client client;
  
  @Inject
  public FrontPage(Client client) {
    this.client = client;
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Listings                                                                                  //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all links, sorted by {@code best}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_BEST
   */
  public Stream<LinkEntity> getBest(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_BEST)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code controversial}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_CONTROVERSIAL
   */
  public Stream<LinkEntity> getControversial(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_CONTROVERSIAL)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code hot}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_HOT
   */
  public Stream<LinkEntity> getHot(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_HOT)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code new}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_NEW
   */
  public Stream<LinkEntity> getNew(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_NEW)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over randomly selected links.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_RANDOM
   */
  public Stream<LinkEntity> getRandom(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_RANDOM)
          .setParams(params)
          .get();
  
    ThingEntity[] response = Things.transform(client.send(query), ThingEntity[].class);
    ListingEntity listing = Things.transformThing(response[0], ListingEntity.class);
    return Things.transformListingOfThings(listing, LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code rising}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_RISING
   */
  public Stream<LinkEntity> getRising(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_RISING)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code top}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_TOP
   */
  public Stream<LinkEntity> getTop(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_TOP)
          .setParams(params)
          .get();
    
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Search                                                                                    //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all links matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Search#GET_SEARCH
   */
  public Stream<LinkEntity> search(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Search.GET_SEARCH)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all subreddits matching the title and description provided by the
   * search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_SEARCH
   */
  public Stream<SubredditEntity> searchSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_SEARCH)
          .setParams(params)
          .get();
    
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all subreddit names matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream of subreddit names.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SEARCH_REDDIT_NAMES
   */
  public Stream<String> searchRedditNames(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
        .setEndpoint(Subreddits.POST_API_SEARCH_REDDIT_NAMES)
        .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
        .setParams(params)
        .post();
  
    Map<?, ?> result = Things.transform(client.send(query), Map.class);
    List<?> values = Things.transform(result.get("names"), List.class);
    return values.stream().map(Objects::toString);
  }
  
  /**
   * Returns a stream over all subreddits that begin with the provided query string.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SEARCH_SUBREDDITS
   */
  public Stream<SubredditEntity> querySubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SEARCH_SUBREDDITS)
          .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
          .setParams(params)
          .post();
  
    Map<?, ?> result = Things.transform(client.send(query), Map.class);
    List<?> values = Things.transform(result.get("subreddits"), List.class);
    return values.stream().map(value -> Things.transform(value, SubredditEntity.class));
  }
  
  /**
   * Returns a stream over all links matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_API_SUBREDDIT_AUTOCOMPLETE_V2
   */
  public Stream<SubredditEntity> getSubredditAutocomplete(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_API_SUBREDDIT_AUTOCOMPLETE_V2)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all default subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_DEFAULT
   */
  public Stream<SubredditEntity> getDefaultSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_DEFAULT)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all subreddits that are only accessible by gilded users.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_GOLD
   */
  public Stream<SubredditEntity> getGoldSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_GOLD)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all newly created subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_NEW
   */
  public Stream<SubredditEntity> getNewSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_NEW)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all popular subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_POPULAR
   */
  public Stream<SubredditEntity> getPopularSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_POPULAR)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all newly created user subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_NEW
   */
  public Stream<SubredditEntity> getNewUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_NEW)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all popular user subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_POPULAR
   */
  public Stream<SubredditEntity> getPopularUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_POPULAR)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all accounts corresponding to the user subreddits, matching the search
   * parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the accounts.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_SEARCH
   */
  public Stream<AccountEntity> getSearchUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_SEARCH)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), AccountEntity.class);
  }
}
