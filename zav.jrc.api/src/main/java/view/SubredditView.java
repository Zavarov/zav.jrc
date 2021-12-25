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

package view;

import com.google.inject.assistedinject.Assisted;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.eclipse.jdt.annotation.NonNull;
import zav.jrc.Client;
import zav.jrc.FailedRequestException;
import zav.jrc.Parameter;
import zav.jrc.databind.Link;
import zav.jrc.databind.Rules;
import zav.jrc.databind.Subreddit;
import zav.jrc.databind.SubredditSettings;
import zav.jrc.databind.Thing;
import zav.jrc.databind.User;
import zav.jrc.databind.UserList;
import zav.jrc.databind.core.Listing;
import zav.jrc.endpoint.Listings;
import zav.jrc.endpoint.Search;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.RestRequest;
import view.internal.JsonUtils;

public class SubredditView {
  @Inject
  private Client client;
  
  private final String name;
  
  @Inject
  public SubredditView(@Assisted String name) {
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
  
  public Stream<Link> getControversial(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_CONTROVERSIAL)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Link.class);
  }
  
  public Stream<Link> getHot(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_HOT)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Link.class);
  }
  
  public Stream<Link> getNew(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_NEW)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Link.class);
  }
  
  public Stream<Link> getRandom(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RANDOM)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    Thing[] response = JsonUtils.transform(client.send(query), Thing[].class);
    Listing listing = JsonUtils.transformThing(response[0], Listing.class);
    return JsonUtils.transformListingOfThings(listing, Link.class);
  }
  
  public Stream<Link> getRising(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RISING)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Link.class);
  }
  
  public Stream<Link> getTop(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_TOP)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), Link.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Search                                                                                    //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  public Stream<Link> getSearch(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Search.GET_R_SUBREDDIT_SEARCH)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    Listing response = JsonUtils.transformThing(client.send(query), Listing.class);
    return JsonUtils.transformListing(response, Link.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//

  public SubredditSettings postCreate(SubredditSettings settings) throws FailedRequestException {
    Map<?, ?> body = JsonUtils.transform(settings, Map.class);
    
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("name", name)
          .build()
          .post();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettings.class);
  }
  
  public SubredditSettings postConfigure(SubredditSettings settings) throws FailedRequestException {
    Map<?, ?> body = JsonUtils.transform(settings, Map.class);
  
    Subreddit subreddit = getAbout();
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("sr", "t5_" + subreddit.getId())
          .build()
          .post();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettings.class);
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
  
  public Stream<User> getBanned() throws FailedRequestException {
    Request query = client.newRequest()
        .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_BANNED)
        .setArgs(name)
        .build()
        .get();
  
    Thing response = JsonUtils.transform(client.send(query), Thing.class);
    Listing listing = JsonUtils.transform(response.getData(), Listing.class);
    return JsonUtils.transformListing(listing, User.class);
  }
  
  public Stream<User> getContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS)
          .setArgs(name)
          .build()
          .get();
  
    Thing response = JsonUtils.transform(client.send(query), Thing.class);
    Listing listing = JsonUtils.transform(response.getData(), Listing.class);
    return JsonUtils.transformListing(listing, User.class);
  }
  
  public Stream<User> getModerators() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MODERATORS)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserList.class).getData().getChildren().stream();
  }
  
  public Stream<User> getMuted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MUTED)
          .setArgs(name)
          .build()
          .get();
  
    Thing response = JsonUtils.transform(client.send(query), Thing.class);
    Listing listing = JsonUtils.transform(response.getData(), Listing.class);
    return JsonUtils.transformListing(listing, User.class);
  }
  
  public Stream<User> getWikiBanned() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKIBANNED)
          .setArgs(name)
          .build()
          .get();
  
    Thing response = JsonUtils.transform(client.send(query), Thing.class);
    Listing listing = JsonUtils.transform(response.getData(), Listing.class);
    return JsonUtils.transformListing(listing, User.class);
  }
  
  public Stream<User> getWikiContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS)
          .setArgs(name)
          .build()
          .get();
  
    Thing response = JsonUtils.transform(client.send(query), Thing.class);
    Listing listing = JsonUtils.transform(response.getData(), Listing.class);
    return JsonUtils.transformListing(listing, User.class);
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
  
  public Subreddit getAbout() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), Subreddit.class);
  }
  
  public SubredditSettings getEdit() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_EDIT)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettings.class);
  }
  
  public Rules getRules() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_RULES)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Rules.class);
  }
  
  public Map<?, ?> getTraffic() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_TRAFFIC)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  public Link getSticky(int index) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_STICKY)
          .setArgs(name)
          .addParam("num", index)
          .build()
          .get();
  
    Thing[] response = JsonUtils.transform(client.send(query), Thing[].class);
    Listing listing = JsonUtils.transform(response[0].getData(), Listing.class);
    Thing thing = JsonUtils.transformListing(listing, Thing.class).findFirst().orElseThrow();
    return JsonUtils.transformThing(thing, Link.class);
  }
}
