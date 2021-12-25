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
import zav.jrc.databind.AwardValueObject;
import zav.jrc.databind.KarmaValueObject;
import zav.jrc.databind.KarmaListValueObject;
import zav.jrc.databind.PreferencesValueObject;
import zav.jrc.databind.SelfAccountValueObject;
import zav.jrc.databind.SubredditValueObject;
import zav.jrc.databind.TrophyListValueObject;
import zav.jrc.databind.UserValueObject;
import zav.jrc.databind.UserListValueObject;
import zav.jrc.endpoint.Account;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;
import zav.jrc.api.internal.JsonUtils;

public class SelfAccount {
  
  @Inject
  private Client client;
  
  // Account
  
  public SelfAccountValueObject getAbout() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME)
          .build()
          .get();
    
    return JsonUtils.transform(client.send(query), SelfAccountValueObject.class);
  }
  
  public Stream<KarmaValueObject> getKarma() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_KARMA)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), KarmaListValueObject.class)
          .getData()
          .stream();
  }
  
  public PreferencesValueObject getPreferences() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_PREFS)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), PreferencesValueObject.class);
  }
  
  public PreferencesValueObject patchPreferences(PreferencesValueObject preferences) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.PATCH_API_V1_ME_PREFS)
          .setBody(JsonUtils.transform(preferences, Map.class), RestRequest.BodyType.JSON)
          .build()
          .patch();
  
    return JsonUtils.transform(client.send(query), PreferencesValueObject.class);
  }
  
  public Stream<AwardValueObject> getTrophies() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_API_V1_ME_TROPHIES)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), TrophyListValueObject.class)
          .getTrophies()
          .stream()
          .map(thing -> JsonUtils.transformThing(thing, AwardValueObject.class));
  }
  
  public Stream<UserValueObject> getBlocked() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_BLOCKED)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserListValueObject.class)
          .getData()
          .getChildren()
          .stream();
  }
  
  public Stream<UserValueObject> getFriends() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_FRIENDS)
          .build()
          .get();
  
    // @See https://redd.it/p19tsh
    // The first structure matches that of GET /api/v1/me/friends.
    // The second one is always empty.
    // Nobody knows what it’s for.
    return JsonUtils.transform(client.send(query), UserListValueObject[].class)[0]
          .getData()
          .getChildren()
          .stream();
  }
  
  public Stream<UserValueObject> getTrusted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Account.GET_PREFS_TRUSTED)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserListValueObject.class)
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
