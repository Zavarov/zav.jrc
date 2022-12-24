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

package zav.jrc.endpoint.subreddit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.api.Things;
import zav.jrc.api.endpoint.Listings;
import zav.jrc.api.endpoint.Search;
import zav.jrc.api.endpoint.Subreddits;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.http.Parameter;
import zav.jrc.client.http.RequestBuilder;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.RulesEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.databind.SubredditSettingsEntity;
import zav.jrc.databind.ThingEntity;
import zav.jrc.databind.UserEntity;
import zav.jrc.databind.UserListEntity;
import zav.jrc.databind.core.ListingEntity;

/**
 * Representation of a subreddits. Subreddits are usually of the form r/<i>name</i>.
 */
@NonNullByDefault
public class Subreddit {
  private static final Cache<String, SubredditEntity> subredditCache = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofDays(1))
        .build();
  
  private static final Logger LOGGER = LoggerFactory.getLogger(Subreddit.class);
  
  private final Client client;
  private final String name;
  
  public Subreddit(Client client, String name) {
    this.client = client;
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
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_CONTROVERSIAL
   */
  public Stream<LinkEntity> getControversial(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_CONTROVERSIAL)
          .setParams(params)
          .setArgs(name)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code hot}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_HOT
   */
  public Stream<LinkEntity> getHot(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_HOT)
          .setParams(params)
          .setArgs(name)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code new}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_NEW
   */
  public Stream<LinkEntity> getNew(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_NEW)
          .setParams(params)
          .setArgs(name)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over randomly selected links.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_RANDOM
   */
  public Stream<LinkEntity> getRandom(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RANDOM)
          .setParams(params)
          .setArgs(name)
          .get();
  
    ThingEntity[] response = Things.transform(client.send(query), ThingEntity[].class);
    ListingEntity listing = Things.transformThing(response[0], ListingEntity.class);
    return Things.transformListingOfThings(listing, LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code rising}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_RISING
   */
  public Stream<LinkEntity> getRising(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_RISING)
          .setParams(params)
          .setArgs(name)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * Returns a stream over all links, sorted by {@code top}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_TOP
   */
  public Stream<LinkEntity> getTop(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_TOP)
          .setParams(params)
          .setArgs(name)
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
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
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Search#GET_R_SUBREDDIT_SEARCH
   */
  public Stream<LinkEntity> search(Parameter... params) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Search.GET_R_SUBREDDIT_SEARCH)
          .setParams(params)
          .setArgs(name)
          .get();
  
    ListingEntity response = Things.transformThing(client.send(query), ListingEntity.class);
    return Things.transformListing(response, LinkEntity.class);
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
  public SubredditSettingsEntity create(SubredditSettingsEntity settings) throws FailedRequestException {
    Map<?, ?> body = Things.transform(settings, Map.class);
    
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RequestBuilder.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("name", name)
          .post();
  
    return Things.transformThing(client.send(query), SubredditSettingsEntity.class);
  }
  
  /**
   * Configures an already existing subreddit.
   *
   * @param settings The settings of the created subreddit.
   * @return The settings of the created subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SITE_ADMIN
   */
  public SubredditSettingsEntity configure(SubredditSettingsEntity settings) throws FailedRequestException {
    Map<?, ?> body = Things.transform(settings, Map.class);
  
    SubredditEntity subreddit = getAbout();
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SITE_ADMIN)
          .setBody(body, RequestBuilder.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("sr", "t5_" + subreddit.getId())
          .post();
  
    return Things.transformThing(client.send(query), SubredditSettingsEntity.class);
  }
  
  /**
   * Subscribe to this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SUBSCRIBE
   */
  public void subscribe() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SUBSCRIBE)
          .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
          .addParam("action", "sub")
          .addParam("sr_name", name)
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
  public void unsubscribe() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_API_SUBSCRIBE)
          .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
          .addParam("action", "unsub")
          .addParam("sr_name", name)
          .post();
  
    //returns {}
    client.send(query);
  }
  
  /**
   * Returns the requirements for creating posts in this subreddit.
   *
   * @return A key-value mapping of all potential restrictions.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_API_V1_SUBREDDIT_POST_REQUIREMENTS
   */
  public Map<?, ?> getPostRequirements() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_API_V1_SUBREDDIT_POST_REQUIREMENTS)
          .setArgs(name)
          .get();
  
    return Things.transform(client.send(query), Map.class);
  }
  
  /**
   * Returns a stream over all users that have been banned from this subreddit.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_BANNED
   */
  public Stream<UserEntity> getBanned() throws FailedRequestException {
    Request query = client.newRequest()
        .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_BANNED)
        .setArgs(name)
        .get();
  
    ThingEntity response = Things.transform(client.send(query), ThingEntity.class);
    ListingEntity listing = Things.transform(response.getData(), ListingEntity.class);
    return Things.transformListing(listing, UserEntity.class);
  }
  
  /**
   * Returns a stream over all subreddit contributors.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS
   */
  public Stream<UserEntity> getContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS)
          .setArgs(name)
          .get();
  
    ThingEntity response = Things.transform(client.send(query), ThingEntity.class);
    ListingEntity listing = Things.transform(response.getData(), ListingEntity.class);
    return Things.transformListing(listing, UserEntity.class);
  }
  
  /**
   * Returns a stream over all subreddit moderators.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_MODERATORS
   */
  public Stream<UserEntity> getModerators() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MODERATORS)
          .setArgs(name)
          .get();
  
    return Things.transform(client.send(query), UserListEntity.class).getData().getChildren().stream();
  }
  
  /**
   * Returns a stream over all users that have been muted by the moderators of this subreddit.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_MUTED
   */
  public Stream<UserEntity> getMuted() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MUTED)
          .setArgs(name)
          .get();
  
    ThingEntity response = Things.transform(client.send(query), ThingEntity.class);
    ListingEntity listing = Things.transform(response.getData(), ListingEntity.class);
    return Things.transformListing(listing, UserEntity.class);
  }
  
  /**
   * Returns a stream over all users that have been banned from the wiki of this subreddit.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_WIKIBANNED
   */
  public Stream<UserEntity> getWikiBanned() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKIBANNED)
          .setArgs(name)
          .get();
  
    ThingEntity response = Things.transform(client.send(query), ThingEntity.class);
    ListingEntity listing = Things.transform(response.getData(), ListingEntity.class);
    return Things.transformListing(listing, UserEntity.class);
  }
  
  /**
   * Returns a stream over all wiki contributors of this subreddit.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS
   */
  public Stream<UserEntity> getWikiContributors() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS)
          .setArgs(name)
          .get();
  
    ThingEntity response = Things.transform(client.send(query), ThingEntity.class);
    ListingEntity listing = Things.transform(response.getData(), ListingEntity.class);
    return Things.transformListing(listing, UserEntity.class);
  }
  
  /**
   * Deletes the subreddit banner.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_BANNER
   */
  public void deleteBanner() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_BANNER)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
          .addParam("api_type", "json")
          .post();
  
    client.send(query);
  }
  
  /**
   * Deletes the subreddit header image.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_HEADER
   */
  public void deleteHeader() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_HEADER)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
          .addParam("api_type", "json")
          .post();
  
    client.send(query);
  }
  
  /**
   * Deletes the subreddit icon.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_ICON
   */
  public void deleteIcon() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_ICON)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
          .addParam("api_type", "json")
          .post();
  
    client.send(query);
  }
  
  /**
   * Deletes one of the custom subreddit images.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_DELETE_SR_IMAGE
   */
  public void deleteImage(String imageName) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_DELETE_SR_IMAGE)
          .setArgs(name)
          .setBody(Collections.emptyMap(), RequestBuilder.BodyType.JSON)
          .addParam("api_type", "json")
          .addParam("img_name", imageName)
          .post();
  
    client.send(query);
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
          .get();
  
    Map<?, ?> response = Things.transform(client.send(query), Map.class);
    
    return response.get("submit_text").toString();
  }
  
  /**
   * Updates the stylesheet of this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_SUBREDDIT_STYLESHEET
   */
  public void updateSubredditStylesheet(Parameter... params) throws FailedRequestException {
    Map<Object, Object> body = new HashMap<>();
    Arrays.stream(params).forEach(param -> body.put(param.getKey(), param.getValue()));
    
    Request query = client.newRequest()
          .setEndpoint(Subreddits.POST_R_SUBREDDIT_API_SUBREDDIT_STYLESHEET)
          .setBody(body, RequestBuilder.BodyType.JSON)
          .addParam("api_type", "json")
          .setArgs(name)
          .post();
  
    client.send(query);
  }
  
  /**
   * Uploads a custom image to this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
  public void uploadImage(RenderedImage image, String imageName) throws FailedRequestException {
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
          .post();
  
    client.send(query);
  }
  
  /**
   * Updates the header image of this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
  public void uploadHeader(RenderedImage image) throws FailedRequestException {
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
          .post();
  
    client.send(query);
  }
  
  /**
   * Updates the icon of this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
  public void uploadIcon(RenderedImage image) throws FailedRequestException {
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
          .post();
  
    client.send(query);
  }
  
  /**
   * Updates the banner of this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE
   */
  public void uploadBanner(RenderedImage image) throws FailedRequestException {
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
          .post();
  
    client.send(query);
  }
  
  /**
   * Returns the Entity object of this subreddit.
   *
   * @return The Entity object corresponding to this subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT
   */
  public SubredditEntity getAbout() throws FailedRequestException {
    @Nullable SubredditEntity result = subredditCache.getIfPresent(name);
  
    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      Request query = client.newRequest()
            .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT)
            .setArgs(name)
            .get();
    
      result = Things.transformThing(client.send(query), SubredditEntity.class);
  
      subredditCache.put(name, result);
    
      LOGGER.debug("Cached new subreddit with name {}.", name);
    }
  
    return result;
  }
  
  /**
   * Returns the current subreddit settings.
   *
   * @return The Entity corresponding to the subreddit settings.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_EDIT
   */
  public SubredditSettingsEntity getEdit() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_EDIT)
          .setArgs(name)
          .get();
  
    return Things.transformThing(client.send(query), SubredditSettingsEntity.class);
  }
  
  /**
   * Returns the rules of this subreddit.
   *
   * @return The Entity corresponding to the subreddit rules.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_RULES
   */
  public RulesEntity getRules() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_RULES)
          .setArgs(name)
          .get();
  
    return Things.transform(client.send(query), RulesEntity.class);
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
          .get();
  
    return Things.transform(client.send(query), Map.class);
  }
  
  /**
   * Returns the stickied link.
   *
   * @param index The index of the stickied link. Has to be either {@code 1} or {@code 2}.
   * @return The Entity corresponding to the link.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_STICKY
   */
  public LinkEntity getSticky(int index) throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_STICKY)
          .setArgs(name)
          .addParam("num", index)
          .get();
  
    ThingEntity[] response = Things.transform(client.send(query), ThingEntity[].class);
    ListingEntity listing = Things.transform(response[0].getData(), ListingEntity.class);
    ThingEntity thing = Things.transformListing(listing, ThingEntity.class).findFirst().orElseThrow();
    return Things.transformThing(thing, LinkEntity.class);
  }
}
