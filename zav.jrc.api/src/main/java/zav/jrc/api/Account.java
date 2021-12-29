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
import zav.jrc.databind.AccountValueObject;
import zav.jrc.databind.AwardValueObject;
import zav.jrc.databind.CommentValueObject;
import zav.jrc.databind.ThingValueObject;
import zav.jrc.databind.TrophyListValueObject;
import zav.jrc.databind.UserValueObject;
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
  private static final Cache<String, AccountValueObject> accountCache = CacheBuilder.newBuilder()
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
   * Blocks this account.
   * @return
   * @throws FailedRequestException
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
  
  public void postUnblock() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.POST_API_UNFRIEND)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("container", "t2_1mfivm")
          .addParam("name", name)
          .addParam("type", "enemy")
          .build()
          .post();
    
    // Returns {}
    client.send(query);
  }
  
  public String getUserData() throws FailedRequestException {
    AccountValueObject account = getAbout();
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_USER_DATA_BY_ACCOUNT_IDS)
          .addParam("ids", "t2_" + account.getId())
          .build()
          .get();
    
    return client.send(query);
  }
  
  public boolean getAvailable() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_USERNAME_AVAILABLE)
          .addParam("user", name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Boolean.class);
  }
  
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
  
  public UserValueObject getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_ME_FRIENDS_USERNAME)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transform(client.send(query), UserValueObject.class);
  }
  
  public UserValueObject putFriends(@Nullable String note) throws FailedRequestException {
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
  
    return JsonUtils.transform(client.send(query), UserValueObject.class);
  }
  
  public Stream<AwardValueObject> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_USER_USERNAME_TROPHIES)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), TrophyListValueObject.class)
          .getTrophies()
          .stream()
          .map(thing -> JsonUtils.transformThing(thing, AwardValueObject.class));
  }
  
  public AccountValueObject getAbout() throws FailedRequestException {
    AccountValueObject result = accountCache.getIfPresent(name);
    
    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      Request query = client.newRequest()
            .setEndpoint(Users.GET_USER_USERNAME_ABOUT)
            .setArgs(name)
            .build()
            .get();
  
      result = JsonUtils.transformThing(client.send(query), AccountValueObject.class);
  
      accountCache.put(name, result);
      
      LOGGER.debug("Cached new account with name {}.", name);
    }
    
    return result;
  }
  
  public Stream<CommentValueObject> getComments(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_COMMENTS)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return JsonUtils.transformListingOfThings(client.send(query), CommentValueObject.class);
  }
  
  public Stream<ThingValueObject> getDownvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_DOWNVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingValueObject.class);
  }
  
  public Stream<ThingValueObject> getGilded(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_GILDED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingValueObject.class);
  }
  
  public Stream<ThingValueObject> getHidden(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_HIDDEN)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingValueObject.class);
  }
  
  public Stream<ThingValueObject> getOverview(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_OVERVIEW)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingValueObject.class);
  }
  
  public Stream<ThingValueObject> getSaved(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SAVED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingValueObject.class);
  }
  
  public Stream<ThingValueObject> getSubmitted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SUBMITTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return JsonUtils.transformListing(client.send(query), ThingValueObject.class);
  }
  
  public Stream<ThingValueObject> getUpvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_UPVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), ThingValueObject.class);
  }
}
