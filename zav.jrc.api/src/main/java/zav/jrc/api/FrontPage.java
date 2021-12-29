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

package zav.jrc.api;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import javax.inject.Inject;
import okhttp3.Request;
import zav.jrc.api.internal.JsonUtils;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AccountDto;
import zav.jrc.databind.LinkDto;
import zav.jrc.databind.SubredditDto;
import zav.jrc.databind.ThingDto;
import zav.jrc.databind.core.ListingDto;
import zav.jrc.endpoint.Listings;
import zav.jrc.endpoint.Search;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;

/**
 * Representation of the Reddit front page (i.e. r/all).
 */
public class FrontPage {
  @Inject
  private Client client;
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Listings                                                                                  //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all links, sorted by {@code best}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_BEST
   */
  public Stream<LinkDto> getBest(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_BEST)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code controversial}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_CONTROVERSIAL
   */
  public Stream<LinkDto> getControversial(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_CONTROVERSIAL)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code hot}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_HOT
   */
  public Stream<LinkDto> getHot(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_HOT)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code new}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_NEW
   */
  public Stream<LinkDto> getNew(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_NEW)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over randomly selected links.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_RANDOM
   */
  public Stream<LinkDto> getRandom(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_RANDOM)
          .setParams(params)
          .build()
          .get();
  
    ThingDto[] response = JsonUtils.transform(client.send(query), ThingDto[].class);
    ListingDto listing = JsonUtils.transformThing(response[0], ListingDto.class);
    return JsonUtils.transformListingOfThings(listing, LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code rising}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_RISING
   */
  public Stream<LinkDto> getRising(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_RISING)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code top}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_TOP
   */
  public Stream<LinkDto> getTop(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_TOP)
          .setParams(params)
          .build()
          .get();
    
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
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
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Search#GET_SEARCH
   */
  public Stream<LinkDto> getSearch(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Search.GET_SEARCH)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all subreddit names matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream of subreddit names.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SEARCH_REDDIT_NAMES
   */
  public Stream<String> postSearchRedditNames(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
        .setEndpoint(Subreddits.POST_API_SEARCH_REDDIT_NAMES)
        .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
        .setParams(params)
        .build()
        .post();
  
    Map<?, ?> result = JsonUtils.transform(client.send(query), Map.class);
    List<?> values = JsonUtils.transform(result.get("names"), List.class);
    return values.stream().map(Objects::toString);
  }
  
  /**
   * Returns a stream over all subreddits matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SEARCH_SUBREDDITS
   */
  public Stream<SubredditDto> postSearchSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SEARCH_SUBREDDITS)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .setParams(params)
          .build()
          .post();
  
    Map<?, ?> result = JsonUtils.transform(client.send(query), Map.class);
    List<?> values = JsonUtils.transform(result.get("subreddits"), List.class);
    return values.stream().map(value -> JsonUtils.transform(value, SubredditDto.class));
  }
  
  /**
   * Returns a stream over all links matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_API_SUBREDDIT_AUTOCOMPLETE_V2
   */
  public Stream<SubredditDto> getSubredditAutocomplete(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_API_SUBREDDIT_AUTOCOMPLETE_V2)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all default subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_DEFAULT
   */
  public Stream<SubredditDto> getDefaultSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_DEFAULT)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all subreddits that are only accessible by gilded users.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_GOLD
   */
  public Stream<SubredditDto> getGoldSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_GOLD)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all newly created subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_NEW
   */
  public Stream<SubredditDto> getNewSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_NEW)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all popular subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_POPULAR
   */
  public Stream<SubredditDto> getPopularSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_POPULAR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all subreddits matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_SEARCH
   */
  public Stream<SubredditDto> getSearchSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_SEARCH)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all newly created user subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_NEW
   */
  public Stream<SubredditDto> getNewUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_NEW)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all popular user subreddits.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_POPULAR
   */
  public Stream<SubredditDto> getPopularUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_POPULAR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all accounts corresponding to the user subreddits, matching the search
   * parameters.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the accounts.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_USERS_SEARCH
   */
  public Stream<AccountDto> getSearchUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_SEARCH)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), AccountDto.class);
  }
}
