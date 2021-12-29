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
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.api.internal.JsonUtils;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkDto;
import zav.jrc.databind.RulesDto;
import zav.jrc.databind.SubredditDto;
import zav.jrc.databind.SubredditSettingsDto;
import zav.jrc.databind.ThingDto;
import zav.jrc.databind.UserDto;
import zav.jrc.databind.UserListDto;
import zav.jrc.databind.core.ListingDto;
import zav.jrc.endpoint.Listings;
import zav.jrc.endpoint.Search;
import zav.jrc.endpoint.Subreddits;
import zav.jrc.http.Parameter;
import zav.jrc.http.RestRequest;

/**
 * Representation of a subreddits. Subreddits are usually of the form r/<i>name</i>.
 */
public class Subreddit {
  private static final Cache<String, SubredditDto> subredditCache = CacheBuilder.newBuilder()
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
  public String toString() {
    return String.format("%s[%s]", getClass(), name);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Listings                                                                                  //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all links, sorted by {@code controversial}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_CONTROVERSIAL
   */
  public Stream<LinkDto> getControversial(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_CONTROVERSIAL)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code hot}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_HOT
   */
  public Stream<LinkDto> getHot(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_HOT)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code new}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_NEW
   */
  public Stream<LinkDto> getNew(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_NEW)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over randomly selected links.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_RANDOM
   */
  public Stream<LinkDto> getRandom(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RANDOM)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    ThingDto[] response = JsonUtils.transform(client.send(query), ThingDto[].class);
    ListingDto listing = JsonUtils.transformThing(response[0], ListingDto.class);
    return JsonUtils.transformListingOfThings(listing, LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code rising}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_RISING
   */
  public Stream<LinkDto> getRising(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RISING)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code top}.
   *
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_TOP
   */
  public Stream<LinkDto> getTop(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_TOP)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformListingOfThings(client.send(query), LinkDto.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Search                                                                                    //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Returns a stream over all links matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the DTOs corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Search#GET_R_SUBREDDIT_SEARCH
   */
  public Stream<LinkDto> getSearch(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Search.GET_R_SUBREDDIT_SEARCH)
          .setParams(params)
          .setArgs(name)
          .build()
          .get();
  
    ListingDto response = JsonUtils.transformThing(client.send(query), ListingDto.class);
    return JsonUtils.transformListing(response, LinkDto.class);
  }
  
  //----------------------------------------------------------------------------------------------//
  //                                                                                              //
  //    Subreddits                                                                                //
  //                                                                                              //
  //----------------------------------------------------------------------------------------------//
  
  /**
   * Creates a new subreddit.
   *
   * @param settings The settings of the created subreddit.
   * @return The settings of the created subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SITE_ADMIN
   */
  public SubredditSettingsDto postCreate(SubredditSettingsDto settings) throws FailedRequestException {
    Map<?, ?> body = JsonUtils.transform(settings, Map.class);
    
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("name", name)
          .build()
          .post();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettingsDto.class);
  }
  
  /**
   * Configures an already existing subreddit.
   *
   * @param settings The settings of the created subreddit.
   * @return The settings of the created subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SITE_ADMIN
   */
  public SubredditSettingsDto postConfigure(SubredditSettingsDto settings) throws FailedRequestException {
    Map<?, ?> body = JsonUtils.transform(settings, Map.class);
  
    SubredditDto subreddit = getAbout();
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RestRequest.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("sr", "t5_" + subreddit.getId())
          .build()
          .post();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettingsDto.class);
  }
  
  /**
   * Subscribe to this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SUBSCRIBE
   */
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
  
  /**
   * Unsubscribe from this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SUBSCRIBE
   */
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
  
  /**
   * Returns the requirements for creating posts in this subreddit..
   *
   * @return A key-value mapping of all potential restrictions.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_API_V1_SUBREDDIT_POST_REQUIREMENTS
   */
  public Map<?, ?> getPostRequirements() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_API_V1_SUBREDDIT_POST_REQUIREMENTS)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  /**
   * Returns a stream over all users that have been banned from this subreddit.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_BANNED
   */
  public Stream<UserDto> getBanned() throws FailedRequestException {
    Request query = client.newRequest()
        .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_BANNED)
        .setArgs(name)
        .build()
        .get();
  
    ThingDto response = JsonUtils.transform(client.send(query), ThingDto.class);
    ListingDto listing = JsonUtils.transform(response.getData(), ListingDto.class);
    return JsonUtils.transformListing(listing, UserDto.class);
  }
  
  /**
   * Returns a stream over all subreddit contributors.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS
   */
  public Stream<UserDto> getContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS)
          .setArgs(name)
          .build()
          .get();
  
    ThingDto response = JsonUtils.transform(client.send(query), ThingDto.class);
    ListingDto listing = JsonUtils.transform(response.getData(), ListingDto.class);
    return JsonUtils.transformListing(listing, UserDto.class);
  }
  
  /**
   * Returns a stream over all subreddit moderators.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_MODERATORS
   */
  public Stream<UserDto> getModerators() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MODERATORS)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), UserListDto.class).getData().getChildren().stream();
  }
  
  /**
   * Returns a stream over all users that have been muted by the moderators of this subreddit.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_MUTED
   */
  public Stream<UserDto> getMuted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MUTED)
          .setArgs(name)
          .build()
          .get();
  
    ThingDto response = JsonUtils.transform(client.send(query), ThingDto.class);
    ListingDto listing = JsonUtils.transform(response.getData(), ListingDto.class);
    return JsonUtils.transformListing(listing, UserDto.class);
  }
  
  /**
   * Returns a stream over all users that have been banned from the wiki of this subreddit.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_WIKIBANNED
   */
  public Stream<UserDto> getWikiBanned() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKIBANNED)
          .setArgs(name)
          .build()
          .get();
  
    ThingDto response = JsonUtils.transform(client.send(query), ThingDto.class);
    ListingDto listing = JsonUtils.transform(response.getData(), ListingDto.class);
    return JsonUtils.transformListing(listing, UserDto.class);
  }
  
  /**
   * Returns a stream over all wiki contributors of this subreddit.
   *
   * @return A stream over the DTOs corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS
   */
  public Stream<UserDto> getWikiContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS)
          .setArgs(name)
          .build()
          .get();
  
    ThingDto response = JsonUtils.transform(client.send(query), ThingDto.class);
    ListingDto listing = JsonUtils.transform(response.getData(), ListingDto.class);
    return JsonUtils.transformListing(listing, UserDto.class);
  }
  
  /**
   * Deletes the subreddit banner.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_BANNER
   */
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
  
  /**
   * Deletes the subreddit header image.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_HEADER
   */
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
  
  /**
   * Deletes the subreddit icon.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_ICON
   */
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
  
  /**
   * Deletes one of the custom subreddit images.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_DELETE_SR_IMAGE
   */
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
  
  /**
   * Returns the label of the submit button.
   *
   * @return The label of the submit button.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_API_SUBMIT_TEXT
   */
  public String getSubmitText() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_API_SUBMIT_TEXT)
          .setArgs(name)
          .build()
          .get();
  
    Map<?, ?> response = JsonUtils.transform(client.send(query), Map.class);
    
    return response.get("submit_text").toString();
  }
  
  /**
   * Updates the stylesheet of this subreddit.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_SUBREDDIT_STYLESHEET
   */
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
  
  /**
   * Uploads a custom image to this subreddit.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
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
  
  /**
   * Updates the header image of this subreddit.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
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
  
  /**
   * Updates the icon of this subreddit.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
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
  
  /**
   * Updates the banner of this subreddit.
   *
   * @return The raw JSON response.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
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
  
  /**
   * Returns the DTO object of this subreddit.
   *
   * @return The DTO object corresponding to this subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT
   */
  public SubredditDto getAbout() throws FailedRequestException {
    @Nullable SubredditDto result = subredditCache.getIfPresent(name);
  
    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      Request query = client.newRequest()
            .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT)
            .setArgs(name)
            .build()
            .get();
    
      result = JsonUtils.transformThing(client.send(query), SubredditDto.class);
  
      subredditCache.put(name, result);
    
      LOGGER.debug("Cached new subreddit with name {}.", name);
    }
  
    return result;
  }
  
  /**
   * Returns the current subreddit settings.
   *
   * @return The DTO corresponding to the subreddit settings.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_EDIT
   */
  public SubredditSettingsDto getEdit() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_EDIT)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transformThing(client.send(query), SubredditSettingsDto.class);
  }
  
  /**
   * Returns the rules of this subreddit.
   *
   * @return The DTO corresponding to the subreddit rules.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_RULES
   */
  public RulesDto getRules() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_RULES)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), RulesDto.class);
  }
  
  /**
   * Returns the traffic in this subreddit.<br>
   * The charts show the traffic of this hour, day and week.
   *
   * @return A mapping over the subreddit traffic.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_TRAFFIC
   */
  public Map<?, ?> getTraffic() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_TRAFFIC)
          .setArgs(name)
          .build()
          .get();
  
    return JsonUtils.transform(client.send(query), Map.class);
  }
  
  /**
   * Returns the stickied link.
   *
   * @param index The index of the stickied link. Has to be either {@code 1} or {@code 2}.
   * @return The DTO corresponding to the link.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_STICKY
   */
  public LinkDto getSticky(int index) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_STICKY)
          .setArgs(name)
          .addParam("num", index)
          .build()
          .get();
  
    ThingDto[] response = JsonUtils.transform(client.send(query), ThingDto[].class);
    ListingDto listing = JsonUtils.transform(response[0].getData(), ListingDto.class);
    ThingDto thing = JsonUtils.transformListing(listing, ThingDto.class).findFirst().orElseThrow();
    return JsonUtils.transformThing(thing, LinkDto.class);
  }
}
