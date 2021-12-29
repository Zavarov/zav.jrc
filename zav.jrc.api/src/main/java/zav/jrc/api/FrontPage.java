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
import zav.jrc.databind.AccountValueObject;
import zav.jrc.databind.LinkValueObject;
import zav.jrc.databind.SubredditValueObject;
import zav.jrc.databind.ThingValueObject;
import zav.jrc.databind.core.ListingValueObject;
import zav.jrc.endpoint.Listings;
import zav.jrc.endpoint.Search;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;

public class FrontPage {
  @Inject
  private Client client;
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Listings                                                                                  //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  public Stream<LinkValueObject> getBest(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_BEST)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getControversial(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_CONTROVERSIAL)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getHot(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_HOT)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getNew(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_NEW)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getRandom(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_RANDOM)
          .setParams(params)
          .build()
          .get();
  
    ThingValueObject[] response = JsonUtils.transform(client.send(query), ThingValueObject[].class);
    ListingValueObject listing = JsonUtils.transformThing(response[0], ListingValueObject.class);
    return JsonUtils.transformListingOfThings(listing, LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getRising(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_RISING)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getTop(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_TOP)
          .setParams(params)
          .build()
          .get();
    
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Search                                                                                    //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  public Stream<LinkValueObject> getSearch(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Search.GET_SEARCH)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
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
  
  public Stream<SubredditValueObject> postSearchSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SEARCH_SUBREDDITS)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .setParams(params)
          .build()
          .post();
  
    Map<?, ?> result = JsonUtils.transform(client.send(query), Map.class);
    List<?> values = JsonUtils.transform(result.get("subreddits"), List.class);
    return values.stream().map(value -> JsonUtils.transform(value, SubredditValueObject.class));
  }
  
  public Stream<SubredditValueObject> getSubredditAutocomplete(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_API_SUBREDDIT_AUTOCOMPLETE_V2)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<SubredditValueObject> getDefaultSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_DEFAULT)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<SubredditValueObject> getGoldSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_GOLD)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<SubredditValueObject> getNewSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_NEW)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<SubredditValueObject> getPopularSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_POPULAR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<SubredditValueObject> getSearchSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_SEARCH)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<SubredditValueObject> getNewUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_NEW)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<SubredditValueObject> getPopularUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_POPULAR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditValueObject.class);
  }
  
  public Stream<AccountValueObject> getSearchUserSubreddits(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_USERS_SEARCH)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), AccountValueObject.class);
  }
}
