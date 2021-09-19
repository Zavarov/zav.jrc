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

package zav.jrc.view;

import com.google.inject.assistedinject.Assisted;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.inject.Inject;
import okhttp3.Request;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.Client;
import zav.jrc.FailedRequestException;
import zav.jrc.Parameter;
import zav.jrc.databind.Account;
import zav.jrc.databind.Award;
import zav.jrc.databind.Comment;
import zav.jrc.databind.Link;
import zav.jrc.databind.TrophyList;
import zav.jrc.endpoint.Users;
import zav.jrc.http.RestRequest;
import zav.jrc.view.internal.JsonUtils;

public class AccountView {
  @Inject
  private Client client;

  private final String name;

  @Inject
  public AccountView(@Assisted String name) {
    this.name = name;
  }
  
  @Override
  @NonNull
  public String toString() {
    return String.format("%s[%s]", getClass(), name);
  }
  
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
    Account account = getAbout();
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
  
  public String getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_ME_FRIENDS_USERNAME)
          .setArgs(name)
          .build()
          .get();
    
    return client.send(query);
  }
  
  public String putFriends(@Nullable String note) throws FailedRequestException {
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
    
    return client.send(query);
  }
  
  public Stream<Award> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_API_V1_USER_USERNAME_TROPHIES)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), TrophyList.class)
          .getTrophies()
          .stream()
          .map(thing -> JsonUtils.transformThing(thing, Award.class));
  }
  
  public Account getAbout() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_ABOUT)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformThing(client.send(query), Account.class);
  }
  
  public Stream<Comment> getComments(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_COMMENTS)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return JsonUtils.transformListing(client.send(query), Comment.class);
  }
  
  public Stream<Comment> getDownvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_DOWNVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), Comment.class);
  }
  
  public Stream<Comment> getGilded(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_GILDED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), Comment.class);
  }
  
  public Stream<Comment> getHidden(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_HIDDEN)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), Comment.class);
  }
  
  public Stream<Comment> getOverview(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_OVERVIEW)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), Comment.class);
  }
  
  public Stream<Comment> getSaved(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SAVED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), Comment.class);
  }
  
  public Stream<Link> getSubmitted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_SUBMITTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();

    return JsonUtils.transformListing(client.send(query), Link.class);
  }
  
  public Stream<Comment> getUpvoted(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Users.GET_USER_USERNAME_UPVOTED)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
    
    return JsonUtils.transformListing(client.send(query), Comment.class);
  }
}
