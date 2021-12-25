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
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.Award;
import zav.jrc.databind.Karma;
import zav.jrc.databind.KarmaList;
import zav.jrc.databind.Preferences;
import zav.jrc.databind.SelfAccount;
import zav.jrc.databind.Subreddit;
import zav.jrc.databind.TrophyList;
import zav.jrc.databind.User;
import zav.jrc.databind.UserList;
import zav.jrc.endpoint.Account;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;
import zav.jrc.api.internal.JsonUtils;

public class SelfAccountView {
  
  @Inject
  private Client client;
  
  // Account
  
  public SelfAccount getAbout() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME)
          .build()
          .get();
    
    return JsonUtils.transform(client.send(query), SelfAccount.class);
  }
  
  public Stream<Karma> getKarma() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_KARMA)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), KarmaList.class)
          .getData()
          .stream();
  }
  
  public Preferences getPreferences() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_PREFS)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Preferences.class);
  }
  
  public Preferences patchPreferences(Preferences preferences) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.PATCH_API_V1_ME_PREFS)
          .setBody(JsonUtils.transform(preferences, Map.class), RestRequest.BodyType.JSON)
          .build()
          .patch();
  
    return JsonUtils.transform(client.send(query), Preferences.class);
  }
  
  public Stream<Award> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_TROPHIES)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), TrophyList.class)
          .getTrophies()
          .stream()
          .map(thing -> JsonUtils.transformThing(thing, Award.class));
  }
  
  public Stream<User> getBlocked() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_BLOCKED)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserList.class)
          .getData()
          .getChildren()
          .stream();
  }
  
  public Stream<User> getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_FRIENDS)
          .build()
          .get();
  
    // @See https://redd.it/p19tsh
    // The first structure matches that of GET /api/v1/me/friends.
    // The second one is always empty.
    // Nobody knows what it’s for.
    return JsonUtils.transform(client.send(query), UserList[].class)[0]
          .getData()
          .getChildren()
          .stream();
  }
  
  public Stream<User> getTrusted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_TRUSTED)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserList.class)
          .getData()
          .getChildren()
          .stream();
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  public Stream<Subreddit> getMineContributor(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_CONTRIBUTOR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Subreddit.class);
  }
  
  public Stream<Subreddit> getMineModerator(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_MODERATOR)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Subreddit.class);
  }
  
  public Stream<Subreddit> getMineStreams(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_STREAMS)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Subreddit.class);
  }
  
  public Stream<Subreddit> getMineSubscriber(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_SUBREDDITS_MINE_SUBSCRIBER)
          .setParams(params)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Subreddit.class);
  }
}
