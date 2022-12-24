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

package zav.jrc.endpoint.account;

import java.util.Map;
import java.util.stream.Stream;
import okhttp3.Request;
import org.eclipse.jdt.annotation.NonNullByDefault;

import zav.jrc.api.endpoint.Account;
import zav.jrc.api.endpoint.Subreddits;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.http.Parameter;
import zav.jrc.client.http.RequestBuilder;
import zav.jrc.databind.AwardEntity;
import zav.jrc.databind.KarmaEntity;
import zav.jrc.databind.KarmaListEntity;
import zav.jrc.databind.PreferencesEntity;
import zav.jrc.databind.SelfAccountEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.databind.Things;
import zav.jrc.databind.TrophyListEntity;
import zav.jrc.databind.UserEntity;
import zav.jrc.databind.UserListEntity;

/**
 * Representation of the account through which the client is logged in.
 */
@NonNullByDefault
public class SelfAccount {
  
  private final Client client;
  
  public SelfAccount(Client client) {
    this.client = client;
  }
  
  // Account
  
  /**
   * Returns the Entity object of this account.
   *
   * @return The Entity object corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME
   */
  public SelfAccountEntity getAbout() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME)
          .get();
    
    return Things.transform(client.send(query), SelfAccountEntity.class);
  }
  
  /**
   * Returns a stream over the karma of this account. Each entry corresponds to the karma gained
   * from a single subreddit. The sum of those entries results in the total account karma.
   *
   * @return A stream over the Entitys corresponding to the karma instances.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_KARMA
   */
  public Stream<KarmaEntity> getKarma() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_KARMA)
          .get();
  
    return Things.transform(client.send(query), KarmaListEntity.class)
          .getData()
          .stream();
  }
  
  /**
   * Returns the Entity object of this account preferences.
   *
   * @return The Entity object corresponding to this account preferences.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_PREFS
   */
  public PreferencesEntity getPreferences() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_PREFS)
          .get();
  
    return Things.transform(client.send(query), PreferencesEntity.class);
  }
  
  /**
   * Updates the account preferences.
   *
   * @return The Entity object corresponding to this account preferences.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#PATCH_API_V1_ME_PREFS
   */
  public PreferencesEntity updatePreferences(PreferencesEntity preferences) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.PATCH_API_V1_ME_PREFS)
          .setBody(Things.transform(preferences, Map.class), RequestBuilder.BodyType.JSON)
          .patch();
  
    return Things.transform(client.send(query), PreferencesEntity.class);
  }
  
  /**
   * Returns a stream over all awards this account possesses.
   *
   * @return A stream over the Entitys corresponding to the awards.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_TROPHIES
   */
  public Stream<AwardEntity> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_TROPHIES)
          .get();
  
    return Things.transformThing(client.send(query), TrophyListEntity.class)
          .getTrophies()
          .stream()
          .map(thing -> Things.transformThing(thing, AwardEntity.class));
  }
  
  /**
   * Returns a stream over all users that have been blocked by this account.
   *
   * @return A stream over the Entitys corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_BLOCKED
   */
  public Stream<UserEntity> getBlocked() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_BLOCKED)
          .get();
  
    return Things.transform(client.send(query), UserListEntity.class)
          .getData()
          .getChildren()
          .stream();
  }
  
  /**
   * Returns a stream over all users that have been befriended by this account.
   *
   * @return A stream over the Entitys corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_FRIENDS
   */
  public Stream<UserEntity> getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_FRIENDS)
          .get();
  
    // @See https://redd.it/p19tsh
    // The first structure matches that of GET /api/v1/me/friends.
    // The second one is always empty.
    // Nobody knows what it’s for.
    return Things.transform(client.send(query), UserListEntity[].class)[0]
          .getData()
          .getChildren()
          .stream();
  }
  
  /**
   * Returns a stream over all users that are trusted by this account.
   *
   * @return A stream over the Entitys corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_TRUSTED
   */
  public Stream<UserEntity> getTrusted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_TRUSTED)
          .get();
    
    System.out.println(Things.transform(client.send(query), UserListEntity.class).getData());
  
    return Things.transform(client.send(query), UserListEntity.class)
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
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_CONTRIBUTOR
   */
  public Stream<SubredditEntity> getMineContributor(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_CONTRIBUTOR)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all subreddits this account moderates.
   *
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_MODERATOR
   */
  public Stream<SubredditEntity> getMineModerator(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_MODERATOR)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all subreddits this account is subscribed to that contain hosted video
   * links.
   *
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_STREAMS
   */
  public Stream<SubredditEntity> getMineStreams(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_STREAMS)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
  
  /**
   * Returns a stream over all subreddits this account is subscribed to.
   *
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_SUBSCRIBER
   */
  public Stream<SubredditEntity> getMineSubscriber(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_SUBSCRIBER)
          .setParams(params)
          .get();
  
    return Things.transformListingOfThings(client.send(query), SubredditEntity.class);
  }
}
