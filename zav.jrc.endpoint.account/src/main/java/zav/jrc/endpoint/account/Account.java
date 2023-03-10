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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.api.endpoint.Users;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.AwardEntity;
import zav.jrc.databind.CommentEntity;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.ThingEntity;
import zav.jrc.databind.Things;
import zav.jrc.databind.TrophyListEntity;
import zav.jrc.databind.UserEntity;

/**
 * Representation of a Reddit account. Accounts are usually of the form
 * u/<i>username</i>.
 */
@NonNullByDefault
public class Account {
  /**
   * In order to save the number of API calls, the account data is stored in an
   * internal cache, s.t. the cached value is reused, during consecutive calls
   * within a short time frame.<br>
   * Items are cached for a single day.
   */
  private static final Cache<String, AccountEntity> accountCache = Caffeine.newBuilder()
      .expireAfterWrite(Duration.ofDays(1)).build();

  private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

  private final Client client;
  private final String name;

  public Account(Client client, String name) {
    this.client = client;
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("%s[%s]", getClass(), name);
  }

  /**
   * Blocks this account.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#POST_API_BLOCK_USER
   */
  public void block() throws FailedRequestException {
    client.newRequest() //
        .withEndpoint(Users.POST_API_BLOCK_USER) //
        .withBody(Collections.emptyMap()) //
        .withParam("name", name) //
        .post();
  }

  /**
   * Unblocks this account.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#POST_API_UNFRIEND
   */
  public void unblock() throws FailedRequestException {
    AccountEntity self = getAbout();

    client.newRequest() //
        .withEndpoint(Users.POST_API_UNFRIEND) //
        .withBody(Collections.emptyMap()) //
        .withParam("container", "t2_" + self.getId()) //
        .withParam("name", name) //
        .withParam("type", "enemy") //
        .post();
  }

  /**
   * Reports this user.
   *
   * @param reason A humanly readable justification why the user was reported.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#POST_API_REPORT_USER
   */
  public void report(String reason) throws FailedRequestException {
    Map<String, String> body = new HashMap<>();
    body.put("user", name);
    body.put("reason", reason);

    client.newRequest() //
        .withEndpoint(Users.POST_API_REPORT_USER) //
        .withBody(body) //
        .post();
  }

  /**
   * Befriends this account.<br>
   * May be used to update the note of the account, in case it already is a
   * 'friend'.
   *
   * @param note A custom note corresponding to this account. May be {@code null}.
   * @return The user Entity corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#PUT_API_V1_ME_FRIENDS_USERNAME
   */
  public UserEntity friend(@Nullable String note) throws FailedRequestException {
    Map<String, String> body = new HashMap<>();
    if (note != null) {
      body.put("note", note);
    }

    String response = client.newRequest() //
        .withEndpoint(Users.PUT_API_V1_ME_FRIENDS_USERNAME, name) //
        .withBody(body) //
        .put();

    return Things.transform(response, UserEntity.class);
  }

  /**
   * Unfriends this user.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#DELETE_API_V1_ME_FRIENDS_USERNAME
   */
  public void unfriend() throws FailedRequestException {
    client.newRequest() //
        .withEndpoint(Users.DELETE_API_V1_ME_FRIENDS_USERNAME, name) //
        .withBody(Collections.emptyMap()) //
        .delete();
  }

  /**
   * Checks whether the username of this account has already been taken.
   *
   * @return {@code true}, when account with this name doesn't exist yet.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_API_USERNAME_AVAILABLE
   */
  public boolean isAvailable() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_API_USERNAME_AVAILABLE) //
        .withParam("user", name) //
        .get();

    return Things.transform(response, Boolean.class);
  }

  /**
   * Get information about this specified 'friend', such as notes.
   *
   * @return The user Entity corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_API_V1_ME_FRIENDS_USERNAME
   */
  public UserEntity getFriends() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_API_V1_ME_FRIENDS_USERNAME, name) //
        .get();

    return Things.transform(response, UserEntity.class);
  }

  /**
   * Returns a stream over all awards this account possesses.
   *
   * @return A stream over the Entitys corresponding to the awards.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_API_V1_USER_USERNAME_TROPHIES
   */
  public Stream<AwardEntity> getTrophies() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_API_V1_USER_USERNAME_TROPHIES, name) //
        .get();

    return Things.transformThing(response, TrophyListEntity.class).getTrophies().stream()
        .map(thing -> Things.transformThing(thing, AwardEntity.class));
  }

  /**
   * Returns the Entity object of this account.
   *
   * @return The Entity object corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_ABOUT
   */
  public AccountEntity getAbout() throws FailedRequestException {
    @Nullable
    AccountEntity result = accountCache.getIfPresent(name);

    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      String response = client.newRequest() //
          .withEndpoint(Users.GET_USER_USERNAME_ABOUT, name) //
          .get();

      result = Things.transformThing(response, AccountEntity.class);

      accountCache.put(name, result);

      LOGGER.debug("Cached new account with name {}.", name);
    }

    return result;
  }

  /**
   * Returns a stream over all comments that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_COMMENTS
   */
  public Stream<CommentEntity> getComments(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_COMMENTS, name) //
        .get();

    return Things.transformListingOfThings(response, CommentEntity.class);
  }

  /**
   * Returns a stream over all links and comments that this account has downvoted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_DOWNVOTED
   */
  public Stream<ThingEntity> getDownvoted(Map<String, Object> params)
      throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_DOWNVOTED, name) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }

  /**
   * Returns a stream over all links and comments that this account has gilded.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_GILDED
   */
  public Stream<ThingEntity> getGilded(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_GILDED, name) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }

  /**
   * Returns a stream over all links and comments that this account has hidden.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_HIDDEN
   */
  public Stream<ThingEntity> getHidden(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_HIDDEN, name) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }

  /**
   * Returns a stream over all links and comments that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_OVERVIEW
   */
  public Stream<ThingEntity> getOverview(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_OVERVIEW, name) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }

  /**
   * Returns a stream over all links and comments that this account has saved.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_SAVED
   */
  public Stream<ThingEntity> getSaved(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_SAVED, name) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }

  /**
   * Returns a stream over all links that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_SUBMITTED
   */
  public Stream<LinkEntity> getSubmitted(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_SUBMITTED, name) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links and comments that this account has upvoted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_UPVOTED
   */
  public Stream<ThingEntity> getUpvoted(Map<String, Object> params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Users.GET_USER_USERNAME_UPVOTED, name) //
        .withParams(params) //
        .get();

    return Things.transformListing(response, ThingEntity.class);
  }
}
