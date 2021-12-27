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
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkValueObject;
import zav.jrc.databind.RulesValueObject;
import zav.jrc.databind.SubredditValueObject;
import zav.jrc.databind.SubredditSettingsValueObject;
import zav.jrc.databind.ThingValueObject;
import zav.jrc.databind.UserValueObject;
import zav.jrc.databind.UserListValueObject;
import zav.jrc.databind.core.ListingValueObject;
import zav.jrc.endpoint.Listings;
import zav.jrc.endpoint.Search;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;
import zav.jrc.api.internal.JsonUtils;

public class Subreddit {
  private static Cache<String, SubredditValueObject> subredditCache = CacheBuilder.newBuilder()
        .expireAfterWrite(Duration.ofDays(1))
        .build();
  
  private static final Logger LOGGER = LogManager.getLogger(Subreddit.class);
  
  @Inject
  private Client client;
  
  private final String name;
  
  @Inject
  public Subreddit(@Assisted String name) {
    this.name = name;
  }
  
  @Override
  @NonNull
  public String toString() {
    return String.format("%s[%s]", getClass(), name);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Listings                                                                                  //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  public Stream<LinkValueObject> getControversial(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_CONTROVERSIAL)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getHot(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_HOT)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getNew(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_NEW)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getRandom(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RANDOM)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    ThingValueObject[] response = JsonUtils.transform(client.send(query), ThingValueObject[].class);
    ListingValueObject listing = JsonUtils.transformThing(response[0], ListingValueObject.class);
    return JsonUtils.transformListingOfThings(listing, LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getRising(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RISING)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  public Stream<LinkValueObject> getTop(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_TOP)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkValueObject.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Search                                                                                    //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  public Stream<LinkValueObject> getSearch(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Search.GET_R_SUBREDDIT_SEARCH)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    ListingValueObject response = JsonUtils.transformThing(client.send(query), ListingValueObject.class);
    return JsonUtils.transformListing(response, LinkValueObject.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//

  public SubredditSettingsValueObject postCreate(SubredditSettingsValueObject settings) throws FailedRequestException {
    Map<?, ?> body = JsonUtils.transform(settings, Map.class);
    
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("name", name)
          .build()
          .post();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettingsValueObject.class);
  }
  
  public SubredditSettingsValueObject postConfigure(SubredditSettingsValueObject settings) throws FailedRequestException {
    Map<?, ?> body = JsonUtils.transform(settings, Map.class);
  
    SubredditValueObject subreddit = getAbout();
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("sr", "t5_" + subreddit.getId())
          .build()
          .post();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettingsValueObject.class);
  }
  
  public void postSubscribe() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SUBSCRIBE)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("action", "sub")
          .addParam("sr_name", name)
          .build()
          .post();
  
    //returns {}
    client.send(query);
  }
  
  public void postUnsubscribe() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SUBSCRIBE)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("action", "unsub")
          .addParam("sr_name", name)
          .build()
          .post();
  
    //returns {}
    client.send(query);
  }
  
  public Map<?, ?> getPostRequirements() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_API_V1_SUBREDDIT_POST_REQUIREMENTS)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Stream<UserValueObject> getBanned() throws FailedRequestException {
    Request query = client.newRequest()
        .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_BANNED)
        .setArgs(name)
        .build()
        .get();
  
    ThingValueObject response = JsonUtils.transform(client.send(query), ThingValueObject.class);
    ListingValueObject listing = JsonUtils.transform(response.getData(), ListingValueObject.class);
    return JsonUtils.transformListing(listing, UserValueObject.class);
  }
  
  public Stream<UserValueObject> getContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS)
          .setArgs(name)
          .build()
          .get();
  
    ThingValueObject response = JsonUtils.transform(client.send(query), ThingValueObject.class);
    ListingValueObject listing = JsonUtils.transform(response.getData(), ListingValueObject.class);
    return JsonUtils.transformListing(listing, UserValueObject.class);
  }
  
  public Stream<UserValueObject> getModerators() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MODERATORS)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserListValueObject.class).getData().getChildren().stream();
  }
  
  public Stream<UserValueObject> getMuted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MUTED)
          .setArgs(name)
          .build()
          .get();
  
    ThingValueObject response = JsonUtils.transform(client.send(query), ThingValueObject.class);
    ListingValueObject listing = JsonUtils.transform(response.getData(), ListingValueObject.class);
    return JsonUtils.transformListing(listing, UserValueObject.class);
  }
  
  public Stream<UserValueObject> getWikiBanned() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKIBANNED)
          .setArgs(name)
          .build()
          .get();
  
    ThingValueObject response = JsonUtils.transform(client.send(query), ThingValueObject.class);
    ListingValueObject listing = JsonUtils.transform(response.getData(), ListingValueObject.class);
    return JsonUtils.transformListing(listing, UserValueObject.class);
  }
  
  public Stream<UserValueObject> getWikiContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS)
          .setArgs(name)
          .build()
          .get();
  
    ThingValueObject response = JsonUtils.transform(client.send(query), ThingValueObject.class);
    ListingValueObject listing = JsonUtils.transform(response.getData(), ListingValueObject.class);
    return JsonUtils.transformListing(listing, UserValueObject.class);
  }
  
  public Map<?, ?> postDeleteBanner() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_BANNER)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Map<?, ?> postDeleteHeader() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_HEADER)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Map<?, ?> postDeleteIcon() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_ICON)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Map<?, ?> postDeleteImage(String imageName) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_DELETE_SR_IMAGE)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("img_name", imageName)
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public String getSubmitText() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_API_SUBMIT_TEXT)
          .setArgs(name)
          .build()
          .get();
  
    Map<?, ?> response = JsonUtils.transform(client.send(query), Map.class);
    
    return response.get("submit_text").toString();
  }
  
  public Map<?, ?> postSubredditStylesheet(Parameter... params) throws FailedRequestException {
    Map<Object, Object> body = new HashMap<>();
    Arrays.stream(params).forEach(param -> body.put(param.getKey(), param.getValue()));
    
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_SUBREDDIT_STYLESHEET)
          .setBody(body, RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .setArgs(name)
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Map<?, ?> postUploadImage(RenderedImage image, String imageName) throws FailedRequestException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
    
    try {
      ImageIO.write(image, "png", data);
    } catch (IOException e) {
      throw FailedRequestException.wrap(e);
    }
    
    MultipartBody body = new MultipartBody.Builder()
          .setType(MultipartBody.FORM)
          .addFormDataPart("file", imageName, RequestBody.create(data.toByteArray(), MediaType.parse("image/png")))
          .addFormDataPart("header", "0")
          .addFormDataPart("img_type", "png")
          .addFormDataPart("name", imageName)
          .addFormDataPart("upload_type", "img")
          .build();
    
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE)
          .setBody(body)
          .setArgs(name)
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Map<?, ?> postUploadHeader(RenderedImage image) throws FailedRequestException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
  
    try {
      ImageIO.write(image, "png", data);
    } catch (IOException e) {
      throw FailedRequestException.wrap(e);
    }
  
    MultipartBody body = new MultipartBody.Builder()
          .setType(MultipartBody.FORM)
          .addFormDataPart("file", "header", RequestBody.create(data.toByteArray(), MediaType.parse("image/png")))
          .addFormDataPart("header", "1")
          .addFormDataPart("img_type", "png")
          .addFormDataPart("upload_type", "header")
          .build();
  
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE)
          .setBody(body)
          .setArgs(name)
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Map<?, ?> postUploadIcon(RenderedImage image) throws FailedRequestException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
  
    try {
      ImageIO.write(image, "png", data);
    } catch (IOException e) {
      throw FailedRequestException.wrap(e);
    }
  
    MultipartBody body = new MultipartBody.Builder()
          .setType(MultipartBody.FORM)
          .addFormDataPart("file", "icon", RequestBody.create(data.toByteArray(), MediaType.parse("image/png")))
          .addFormDataPart("header", "0")
          .addFormDataPart("img_type", "png")
          .addFormDataPart("upload_type", "icon")
          .build();
  
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE)
          .setBody(body)
          .setArgs(name)
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Map<?, ?> postUploadBanner(RenderedImage image) throws FailedRequestException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
  
    try {
      ImageIO.write(image, "png", data);
    } catch (IOException e) {
      throw FailedRequestException.wrap(e);
    }
  
    MultipartBody body = new MultipartBody.Builder()
          .setType(MultipartBody.FORM)
          .addFormDataPart("file", "icon", RequestBody.create(data.toByteArray(), MediaType.parse("image/png")))
          .addFormDataPart("header", "0")
          .addFormDataPart("img_type", "png")
          .addFormDataPart("upload_type", "banner")
          .build();
  
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE)
          .setBody(body)
          .setArgs(name)
          .build()
          .post();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public SubredditValueObject getAbout() throws FailedRequestException {
    SubredditValueObject result = subredditCache.getIfPresent(name);
  
    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      Request query = client.newRequest()
            .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT)
            .setArgs(name)
            .build()
            .get();
    
      result = JsonUtils.transformThing(client.send(query), SubredditValueObject.class);
  
      subredditCache.put(name, result);
    
      LOGGER.debug("Cached new subreddit with name {}.", name);
    }
  
    return result;
  }
  
  public SubredditSettingsValueObject getEdit() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_EDIT)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettingsValueObject.class);
  }
  
  public RulesValueObject getRules() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_RULES)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), RulesValueObject.class);
  }
  
  public Map<?, ?> getTraffic() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_TRAFFIC)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public LinkValueObject getSticky(int index) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_STICKY)
          .setArgs(name)
          .addParam("num", index)
          .build()
          .get();
  
    ThingValueObject[] response = JsonUtils.transform(client.send(query), ThingValueObject[].class);
    ListingValueObject listing = JsonUtils.transform(response[0].getData(), ListingValueObject.class);
    ThingValueObject thing = JsonUtils.transformListing(listing, ThingValueObject.class).findFirst().orElseThrow();
    return JsonUtils.transformThing(thing, LinkValueObject.class);
  }
}
