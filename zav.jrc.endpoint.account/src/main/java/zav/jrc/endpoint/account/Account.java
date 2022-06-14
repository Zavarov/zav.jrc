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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.Request;
import org.eclipse.jdt.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.api.Constants;
import zav.jrc.api.Things;
import zav.jrc.api.endpoint.Users;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.AwardEntity;
import zav.jrc.databind.CommentEntity;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.ThingEntity;
import zav.jrc.databind.TrophyListEntity;
import zav.jrc.databind.UserEntity;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;

/**
 * Representation of a Reddit account. Accounts are usually of the form u/<i>username</i>.
 */
public class Account {
  /**
   * In order to save the number of API calls, the account data is stored in an internal cache, s.t.
   * the cached value is reused, during consecutive calls within a short time frame.<br>
   * Items are cached for a single day.
   */
  private static final Cache<String, AccountEntity> accountCache = CacheBuilder.newBuilder()
        .expireAfterWrite(Duration.ofDays(1))
        .build();
  
  private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);
  
  private final Client client;
  private final String name;
  
  @Inject
  public Account(Client client, @Named(Constants.ACCOUNT) String name) {
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
    Request query = client.newRequest()
          .setEndpoint(Users.POST_API_BLOCK_USER)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("name", name)
          .build()
          .post();
    
    client.send(query);
  }
  
  /**
   * Unblocks this account.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#POST_API_UNFRIEND
   */
  public void unblock() throws FailedRequestException {
    AccountEntity self = getAbout();
    
    Request query = client.newRequest()
          .setEndpoint(Users.POST_API_UNFRIEND)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("container", "t2_" + self.getId())
          .addParam("name", name)
          .addParam("type", "enemy")
          .build()
          .post();
    
    // Returns {}
    client.send(query);
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
    
    Request query = client.newRequest()
          .setEndpoint(Users.POST_API_REPORT_USER)
          .setBody(body, RestRequest.BodyType.JSON)
          .build()
          .post();
    
    client.send(query);
  }
  
  /**
   * Befriends this account.<br>
   * May be used to update the note of the account, in case it already is a 'friend'.
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
    
    Request query = client.newRequest()
          .setEndpoint(Users.PUT_API_V1_ME_FRIENDS_USERNAME)
          .setBody(body, RestRequest.BodyType.JSON)
          .setArgs(name)
          .build()
          .put();
    
    return Things.transform(client.send(query), UserEntity.class);
  }
  
  /**
   * Unfriends this user.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#DELETE_API_V1_ME_FRIENDS_USERNAME
   */
  public void unfriend() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.DELETE_API_V1_ME_FRIENDS_USERNAME)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .setArgs(name)
          .build()
          .delete();
    
    //Returns an empty String
    client.send(query);
  }
  
  /**
   * Checks whether the username of this account has already been taken.
   *
   * @return {@code true}, when account with this name doesn't exist yet.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_API_USERNAME_AVAILABLE
   */
  public boolean isAvailable() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_USERNAME_AVAILABLE)
          .addParam("user", name)
          .build()
          .get();
    
    return Things.transform(client.send(query), Boolean.class);
  }
  
  /**
   * Returns the Entity object of this account.
   *
   * @return The Entity object corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_API_USER_DATA_BY_ACCOUNT_IDS
   * @deprecated Use {@link #getAbout()} instead.
   */
  @Deprecated
  public AccountEntity getUserData() throws FailedRequestException {
    AccountEntity self = getAbout();
    
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_USER_DATA_BY_ACCOUNT_IDS)
          .addParam("ids", "t2_" + self.getId())
          .build()
          .get();
  
    return Things.transform(client.send(query), AccountEntity.class);
  }
  
  /**
   * Get information about this specified 'friend', such as notes.
   *
   * @return The user Entity corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_API_V1_ME_FRIENDS_USERNAME
   */
  public UserEntity getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_ME_FRIENDS_USERNAME)
          .setArgs(name)
          .build()
          .get();
    
    return Things.transform(client.send(query), UserEntity.class);
  }
  
  /**
   * Returns a stream over all awards this account possesses.
   *
   * @return A stream over the Entitys corresponding to the awards.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_API_V1_USER_USERNAME_TROPHIES
   */
  public Stream<AwardEntity> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_USER_USERNAME_TROPHIES)
          .setArgs(name)
          .build()
          .get();
  
    return Things.transformThing(client.send(query), TrophyListEntity.class)
          .getTrophies()
          .stream()
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
    @Nullable AccountEntity result = accountCache.getIfPresent(name);
    
    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      Request query = client.newRequest()
            .setEndpoint(Users.GET_USER_USERNAME_ABOUT)
            .setArgs(name)
            .build()
            .get();
  
      result = Things.transformThing(client.send(query), AccountEntity.class);
  
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
  public Stream<CommentEntity> getComments(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_COMMENTS)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return Things.transformListingOfThings(client.send(query), CommentEntity.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has downvoted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_DOWNVOTED
   */
  public Stream<ThingEntity> getDownvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_DOWNVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return Things.transformListing(client.send(query), ThingEntity.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has gilded.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_GILDED
   */
  public Stream<ThingEntity> getGilded(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_GILDED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return Things.transformListing(client.send(query), ThingEntity.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has hidden.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_HIDDEN
   */
  public Stream<ThingEntity> getHidden(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_HIDDEN)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return Things.transformListing(client.send(query), ThingEntity.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_OVERVIEW
   */
  public Stream<ThingEntity> getOverview(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_OVERVIEW)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return Things.transformListing(client.send(query), ThingEntity.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has saved.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_SAVED
   */
  public Stream<ThingEntity> getSaved(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SAVED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return Things.transformListing(client.send(query), ThingEntity.class);
  }
  
  /**
   * Returns a stream over all links that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_SUBMITTED
   */
  public Stream<LinkEntity> getSubmitted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SUBMITTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has upvoted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the Entitys corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Users#GET_USER_USERNAME_UPVOTED
   */
  public Stream<ThingEntity> getUpvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_UPVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return Things.transformListing(client.send(query), ThingEntity.class);
  }
}
