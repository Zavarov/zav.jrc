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

import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import okhttp3.Request;
import zav.jrc.api.internal.JsonUtils;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AwardDto;
import zav.jrc.databind.KarmaDto;
import zav.jrc.databind.KarmaListDto;
import zav.jrc.databind.PreferencesDto;
import zav.jrc.databind.SelfAccountDto;
import zav.jrc.databind.SubredditDto;
import zav.jrc.databind.TrophyListDto;
import zav.jrc.databind.UserDto;
import zav.jrc.databind.UserListDto;
import zav.jrc.endpoint.Account;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;

/**
 * Representation of the account through which the client is logged in.
 */
public class SelfAccount {
  
  @Inject
  private Client client;
  
  // Account
  
  /**
   * Returns the DTO object of this account.
   *
   * @return The DTO object corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME
   */
  public SelfAccountDto getAbout() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME)
          .build()
          .get();
    
    return JsonUtils.transform(client.send(query), SelfAccountDto.class);
  }
  
  /**
   * Returns a stream over the karma of this account. Each entry corresponds to the karma gained
   * from a single subreddit. The sum of those entries results in the total account karma.
   *
   * @return A stream over the DTOs corresponding to the karma instances.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_KARMA
   */
  public Stream<KarmaDto> getKarma() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_KARMA)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), KarmaListDto.class)
          .getData()
          .stream();
  }
  
  /**
   * Returns the DTO object of this account preferences.
   *
   * @return The DTO object corresponding to this account preferences.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_PREFS
   */
  public PreferencesDto getPreferences() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_PREFS)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), PreferencesDto.class);
  }
  
  /**
   * Updates the account preferences.
   *
   * @return The DTO object corresponding to this account preferences.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#PATCH_API_V1_ME_PREFS
   */
  public PreferencesDto patchPreferences(PreferencesDto preferences) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.PATCH_API_V1_ME_PREFS)
          .setBody(JsonUtils.transform(preferences, Map.class), RestRequest.BodyType.JSON)
          .build()
          .patch();
  
    return JsonUtils.transform(client.send(query), PreferencesDto.class);
  }
  
  /**
   * Returns a stream over all awards this account possesses.
   *
   * @return A stream over the DTOs corresponding to the awards.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_TROPHIES
   */
  public Stream<AwardDto> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_TROPHIES)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), TrophyListDto.class)
          .getTrophies()
          .stream()
          .map(thing -> JsonUtils.transformThing(thing, AwardDto.class));
  }
  
  /**
   * Returns a stream over all users that have been blocked by this account.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_BLOCKED
   */
  public Stream<UserDto> getBlocked() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_BLOCKED)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserListDto.class)
          .getData()
          .getChildren()
          .stream();
  }
  
  /**
   * Returns a stream over all users that have been befriended by this account.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_FRIENDS
   */
  public Stream<UserDto> getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_FRIENDS)
          .build()
          .get();
  
    // @See https://redd.it/p19tsh
    // The first structure matches that of GET /api/v1/me/friends.
    // The second one is always empty.
    // Nobody knows what it’s for.
    return JsonUtils.transform(client.send(query), UserListDto[].class)[0]
          .getData()
          .getChildren()
          .stream();
  }
  
  /**
   * Returns a stream over all users that are trusted by this account.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_TRUSTED
   */
  public Stream<UserDto> getTrusted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_TRUSTED)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserListDto.class)
          .getData()
          .getChildren()
          .stream();
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all subreddits this account has contributed to.
   *
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_CONTRIBUTOR
   */
  public Stream<SubredditDto> getMineContributor(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_CONTRIBUTOR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all subreddits this account moderates.
   *
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_MODERATOR
   */
  public Stream<SubredditDto> getMineModerator(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_MODERATOR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all subreddits this account is subscribed to that contain hosted video
   * links.
   *
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_STREAMS
   */
  public Stream<SubredditDto> getMineStreams(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_STREAMS)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
  
  /**
   * Returns a stream over all subreddits this account is subscribed to.
   *
   * @return A stream over the DTOs corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_SUBSCRIBER
   */
  public Stream<SubredditDto> getMineSubscriber(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_SUBSCRIBER)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), SubredditDto.class);
  }
}
