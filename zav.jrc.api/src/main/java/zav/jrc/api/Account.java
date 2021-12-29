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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.assistedinject.Assisted;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.api.internal.JsonUtils;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.*;
import zav.jrc.endpoint.Users;
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
  private static final Cache<String, AccountDto> accountCache = CacheBuilder.newBuilder()
        .expireAfterWrite(Duration.ofDays(1))
        .build();
  
  private static final Logger LOGGER = LogManager.getLogger(Account.class);
  
  @Inject
  private Client client;

  private final String name;

  @Inject
  public Account(@Assisted String name) {
    this.name = name;
  }
  
  @Override
  @NonNull
  public String toString() {
    return String.format("%s[%s]", getClass(), name);
  }
  
  /**
   * Blocks this account.<br>
   * The returned map contains the following entries:
   * <pre>
   *       date - The date when the user was blocked.
   *   icon_img - The avatar of the blocked user.
   *         id - The id of the blocked user.
   *       name - The name of the blocked user.
   * </pre>
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Map<?, ?> postBlock() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.POST_API_BLOCK_USER)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("name", name)
          .build()
          .post();
    
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  /**
   * Reports this user.
   *
   * @param reason A humanly readable justification why the user was reported.
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public String postReport(String reason) throws FailedRequestException {
    Map<String, String> body = new HashMap<>();
    body.put("user", name);
    body.put("reason", reason);
    
    Request query = client.newRequest()
          .setEndpoint(Users.POST_API_REPORT_USER)
          .setBody(body, RestRequest.BodyType.JSON)
          .build()
          .post();
    
    return client.send(query);
  }
  
  /**
   * Unblocks this account.
   *
   * @throws FailedRequestException If the API requests was rejected.
   */
  public void postUnblock() throws FailedRequestException {
    AccountDto self = getAbout();
    
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
   * Returns the DTO object of this account.
   *
   * @return The DTO object corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   * @deprecated Use {@link #getAbout()} instead.
   */
  @Deprecated
  public AccountDto getUserData() throws FailedRequestException {
    AccountDto self = getAbout();
    
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_USER_DATA_BY_ACCOUNT_IDS)
          .addParam("ids", "t2_" + self.getId())
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), AccountDto.class);
  }
  
  /**
   * Checks whether the username of this account has already been taken.
   *
   * @return {@code true}, when account with this name doesn't exist yet.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public boolean getAvailable() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_USERNAME_AVAILABLE)
          .addParam("user", name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Boolean.class);
  }
  
  /**
   * Unfriends this user.
   *
   * @throws FailedRequestException If the API requests was rejected.
   */
  public void deleteFriends() throws FailedRequestException {
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
   * Get information about this specified 'friend', such as notes.
   *
   * @return The user DTO corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public UserDto getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_ME_FRIENDS_USERNAME)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transform(client.send(query), UserDto.class);
  }
  
  /**
   * Befriends this account.<br>
   * May be used to update the note of the account, in case it already is a 'friend'.
   *
   * @param note A custom note corresponding to this account. May be {@code null}.
   * @return The user DTO corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public UserDto putFriends(@Nullable String note) throws FailedRequestException {
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
  
    return JsonUtils.transform(client.send(query), UserDto.class);
  }
  
  /**
   * Returns a stream over all awards this account possesses.
   *
   * @return A stream over the DTOs corresponding to the awards.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<AwardDto> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_USER_USERNAME_TROPHIES)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), TrophyListDto.class)
          .getTrophies()
          .stream()
          .map(thing -> JsonUtils.transformThing(thing, AwardDto.class));
  }
  
  /**
   * Returns the DTO object of this account.
   *
   * @return The DTO object corresponding to this account.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public AccountDto getAbout() throws FailedRequestException {
    AccountDto result = accountCache.getIfPresent(name);
    
    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      Request query = client.newRequest()
            .setEndpoint(Users.GET_USER_USERNAME_ABOUT)
            .setArgs(name)
            .build()
            .get();
  
      result = JsonUtils.transformThing(client.send(query), AccountDto.class);
  
      accountCache.put(name, result);
      
      LOGGER.debug("Cached new account with name {}.", name);
    }
    
    return result;
  }
  
  /**
   * Returns a stream over all comments that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the comments.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<CommentDto> getComments(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_COMMENTS)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return JsonUtils.transformListingOfThings(client.send(query), CommentDto.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has downvoted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<ThingDto> getDownvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_DOWNVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingDto.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has gilded.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<ThingDto> getGilded(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_GILDED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingDto.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has hidden.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<ThingDto> getHidden(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_HIDDEN)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingDto.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<ThingDto> getOverview(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_OVERVIEW)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingDto.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has saved.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<ThingDto> getSaved(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SAVED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingDto.class);
  }
  
  /**
   * Returns a stream over all links that this account has submitted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<LinkDto> getSubmitted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SUBMITTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links and comments that this account has upvoted.
   *
   * @param params Additional parameters such as {@code sort} or {@code count}.
   * @return A stream over the DTOs corresponding to the links and comments.
   * @throws FailedRequestException If the API requests was rejected.
   */
  public Stream<ThingDto> getUpvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_UPVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingDto.class);
  }
}
