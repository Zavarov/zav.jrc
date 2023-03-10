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

package zav.jrc.endpoint.account;

import java.util.Map;
import java.util.stream.Stream;
import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.api.endpoint.Account;
import zav.jrc.api.endpoint.Subreddits;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
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
    String response = client.newRequest() //
        .withEndpoint(Account.GET_API_V1_ME) //
        .get();

    return Things.transform(response, SelfAccountEntity.class);
  }

  /**
   * Returns a stream over the karma of this account. Each entry corresponds to
   * the karma gained from a single subreddit. The sum of those entries results in
   * the total account karma.
   *
   * @return A stream over the Entitys corresponding to the karma instances.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_KARMA
   */
  public Stream<KarmaEntity> getKarma() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Account.GET_API_V1_ME_KARMA) //
        .get();

    return Things.transform(response, KarmaListEntity.class).getData().stream();
  }

  /**
   * Returns the Entity object of this account preferences.
   *
   * @return The Entity object corresponding to this account preferences.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_PREFS
   */
  public PreferencesEntity getPreferences() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Account.GET_API_V1_ME_PREFS) //
        .get();

    return Things.transform(response, PreferencesEntity.class);
  }

  /**
   * Updates the account preferences.
   *
   * @return The Entity object corresponding to this account preferences.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#PATCH_API_V1_ME_PREFS
   */
  public PreferencesEntity updatePreferences(PreferencesEntity preferences)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Account.PATCH_API_V1_ME_PREFS) //
        .withBody(Things.transform(preferences, Map.class)) //
        .patch();

    return Things.transform(response, PreferencesEntity.class);
  }

  /**
   * Returns a stream over all awards this account possesses.
   *
   * @return A stream over the Entitys corresponding to the awards.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_API_V1_ME_TROPHIES
   */
  public Stream<AwardEntity> getTrophies() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Account.GET_API_V1_ME_TROPHIES) //
        .get();

    return Things.transformThing(response, TrophyListEntity.class).getTrophies().stream()
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
    String response = client.newRequest() //
        .withEndpoint(Account.GET_PREFS_BLOCKED) //
        .get();

    return Things.transform(response, UserListEntity.class).getData().getChildren().stream();
  }

  /**
   * Returns a stream over all users that have been befriended by this account.
   *
   * @return A stream over the Entitys corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_FRIENDS
   */
  public Stream<UserEntity> getFriends() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Account.GET_PREFS_FRIENDS) //
        .get();

    // @See https://redd.it/p19tsh
    // The first structure matches that of GET /api/v1/me/friends.
    // The second one is always empty.
    // Nobody knows what it???s for.
    return Things.transform(response, UserListEntity[].class)[0].getData().getChildren().stream();
  }

  /**
   * Returns a stream over all users that are trusted by this account.
   *
   * @return A stream over the Entitys corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Account#GET_PREFS_TRUSTED
   */
  public Stream<UserEntity> getTrusted() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Account.GET_PREFS_TRUSTED) //
        .get();

    return Things.transform(response, UserListEntity.class).getData().getChildren().stream();
  }

  // ----------------------------------------------------------------------------------------------//
  // //
  // Subreddits //
  // //
  // ----------------------------------------------------------------------------------------------//

  /**
   * Returns a stream over all subreddits this account has contributed to.
   *
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_CONTRIBUTOR
   */
  public Stream<SubredditEntity> getMineContributor(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_MINE_CONTRIBUTOR) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all subreddits this account moderates.
   *
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_MODERATOR
   */
  public Stream<SubredditEntity> getMineModerator(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_MINE_MODERATOR) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all subreddits this account is subscribed to that
   * contain hosted video links.
   *
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_STREAMS
   */
  public Stream<SubredditEntity> getMineStreams(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_MINE_STREAMS) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }

  /**
   * Returns a stream over all subreddits this account is subscribed to.
   *
   * @return A stream over the Entitys corresponding to the subreddits.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_SUBREDDITS_MINE_SUBSCRIBER
   */
  public Stream<SubredditEntity> getMineSubscriber(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_SUBREDDITS_MINE_SUBSCRIBER) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, SubredditEntity.class);
  }
}
