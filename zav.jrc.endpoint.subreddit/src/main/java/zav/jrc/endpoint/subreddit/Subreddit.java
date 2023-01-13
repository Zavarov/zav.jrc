/*
 * Copyright (c) 2023 Zavarov
 * 
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
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.api.endpoint.Listings;
import zav.jrc.api.endpoint.Search;
import zav.jrc.api.endpoint.Subreddits;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.http.Parameter;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.RulesEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.databind.SubredditSettingsEntity;
import zav.jrc.databind.ThingEntity;
import zav.jrc.databind.Things;
import zav.jrc.databind.UserEntity;
import zav.jrc.databind.UserListEntity;
import zav.jrc.databind.core.ListingEntity;

/**
 * Representation of a subreddits. Subreddits are usually of the form
 * r/<i>name</i>.
 */
@NonNullByDefault
public class Subreddit {
  private static final Cache<String, SubredditEntity> subredditCache = Caffeine.newBuilder()
      .expireAfterWrite(Duration.ofDays(1)).build();

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

  // ---------//
  // Listings //
  // ---------//

  /**
   * Returns a stream over all links, sorted by {@code controversial}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_CONTROVERSIAL
   */
  public Stream<LinkEntity> getControversial(Parameter... params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_R_SUBREDDIT_CONTROVERSIAL, name) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code hot}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_HOT
   */
  public Stream<LinkEntity> getHot(Parameter... params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_R_SUBREDDIT_HOT, name) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code new}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_NEW
   */
  public Stream<LinkEntity> getNew(Parameter... params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_R_SUBREDDIT_NEW, name) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over randomly selected links.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_RANDOM
   */
  public Stream<LinkEntity> getRandom(Parameter... params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_R_SUBREDDIT_RANDOM, name) //
        .withParams(params) //
        .get();

    ThingEntity[] things = Things.transform(response, ThingEntity[].class);
    ListingEntity listing = Things.transformThing(things[0], ListingEntity.class);
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
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_R_SUBREDDIT_RISING, name) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  /**
   * Returns a stream over all links, sorted by {@code top}.
   *
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Listings#GET_R_SUBREDDIT_TOP
   */
  public Stream<LinkEntity> getTop(Parameter... params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Listings.GET_R_SUBREDDIT_TOP, name) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  // -------//
  // Search //
  // -------//

  /**
   * Returns a stream over all links matching the search parameters.
   *
   * @param params The search parameters.
   * @return A stream over the entities corresponding to the links.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Search#GET_R_SUBREDDIT_SEARCH
   */
  public Stream<LinkEntity> search(Parameter... params) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Search.GET_R_SUBREDDIT_SEARCH, name) //
        .withParams(params) //
        .get();

    ListingEntity listing = Things.transformThing(response, ListingEntity.class);
    return Things.transformListing(listing, LinkEntity.class);
  }

  // -----------//
  // Subreddits //
  // -----------//

  /**
   * Creates a new subreddit.
   *
   * @param settings The settings of the created subreddit.
   * @return The settings of the created subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SITE_ADMIN
   */
  public SubredditSettingsEntity create(SubredditSettingsEntity settings)
      throws FailedRequestException {
    Map<?, ?> body = Things.transform(settings, Map.class);

    String response = client.newRequest() //
        .withEndpoint(Subreddits.POST_API_SITE_ADMIN) //
        .withBody(body) //
        .withParam("api_type", "json") //
        .withParam("name", name) //
        .post();

    return Things.transformThing(response, SubredditSettingsEntity.class);
  }

  /**
   * Configures an already existing subreddit.
   *
   * @param settings The settings of the created subreddit.
   * @return The settings of the created subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SITE_ADMIN
   */
  public SubredditSettingsEntity configure(SubredditSettingsEntity settings)
      throws FailedRequestException {
    Map<?, ?> body = Things.transform(settings, Map.class);

    SubredditEntity subreddit = getAbout();
    String response = client.newRequest() //
        .withEndpoint(Subreddits.POST_API_SITE_ADMIN) //
        .withBody(body) //
        .withParam("api_type", "json") //
        .withParam("sr", "t5_" + subreddit.getId()) //
        .post();

    return Things.transformThing(response, SubredditSettingsEntity.class);
  }

  /**
   * Subscribe to this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SUBSCRIBE
   */
  public void subscribe() throws FailedRequestException {
    client.newRequest() //
        .withEndpoint(Subreddits.POST_API_SUBSCRIBE) //
        .withBody(Collections.emptyMap()) //
        .withParam("action", "sub") //
        .withParam("sr_name", name) //
        .post();
  }

  /**
   * Unsubscribe from this subreddit.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_API_SUBSCRIBE
   */
  public void unsubscribe() throws FailedRequestException {
    client.newRequest() //
        .withEndpoint(Subreddits.POST_API_SUBSCRIBE) //
        .withBody(Collections.emptyMap()) //
        .withParam("action", "unsub") //
        .withParam("sr_name", name) //
        .post();
  }

  /**
   * Returns the requirements for creating posts in this subreddit.
   *
   * @return A key-value mapping of all potential restrictions.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_API_V1_SUBREDDIT_POST_REQUIREMENTS
   */
  public Map<?, ?> getPostRequirements() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_API_V1_SUBREDDIT_POST_REQUIREMENTS, name) //
        .get();

    return Things.transform(response, Map.class);
  }

  /**
   * Returns a stream over all users that have been banned from this subreddit.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_BANNED
   */
  public Stream<UserEntity> getBanned() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_BANNED, name) //
        .get();

    ThingEntity thing = Things.transform(response, ThingEntity.class);
    ListingEntity listing = Things.transform(thing.getData(), ListingEntity.class);
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
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_CONTRIBUTORS, name) //
        .get();

    ThingEntity thing = Things.transform(response, ThingEntity.class);
    ListingEntity listing = Things.transform(thing.getData(), ListingEntity.class);
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
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MODERATORS, name) //
        .get();

    return Things.transform(response, UserListEntity.class).getData().getChildren().stream();
  }

  /**
   * Returns a stream over all users that have been muted by the moderators of
   * this subreddit.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_MUTED
   */
  public Stream<UserEntity> getMuted() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_MUTED, name) //
        .get();

    ThingEntity thing = Things.transform(response, ThingEntity.class);
    ListingEntity listing = Things.transform(thing.getData(), ListingEntity.class);
    return Things.transformListing(listing, UserEntity.class);
  }

  /**
   * Returns a stream over all users that have been banned from the wiki of this
   * subreddit.
   *
   * @return A stream over the entities corresponding to the users.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_WIKIBANNED
   */
  public Stream<UserEntity> getWikiBanned() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKIBANNED, name) //
        .get();

    ThingEntity thing = Things.transform(response, ThingEntity.class);
    ListingEntity listing = Things.transform(thing.getData(), ListingEntity.class);
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
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_WIKICONTRIBUTORS, name) //
        .get();

    ThingEntity thing = Things.transform(response, ThingEntity.class);
    ListingEntity listing = Things.transform(thing.getData(), ListingEntity.class);
    return Things.transformListing(listing, UserEntity.class);
  }

  /**
   * Deletes the subreddit banner.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_BANNER
   */
  public void deleteBanner() throws FailedRequestException {
    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_BANNER, name) //
        .withBody(Collections.emptyMap()) //
        .withParam("api_type", "json") //
        .post();
  }

  /**
   * Deletes the subreddit header image.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_HEADER
   */
  public void deleteHeader() throws FailedRequestException {
    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_HEADER, name) //
        .withBody(Collections.emptyMap()) //
        .withParam("api_type", "json") //
        .post();
  }

  /**
   * Deletes the subreddit icon.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_API_DELETE_SR_ICON
   */
  public void deleteIcon() throws FailedRequestException {
    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_DELETE_SR_ICON, name) //
        .withBody(Collections.emptyMap()) //
        .withParam("api_type", "json") //
        .post();
  }

  /**
   * Deletes one of the custom subreddit images.
   *
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#POST_R_SUBREDDIT_DELETE_SR_IMAGE
   */
  public void deleteImage(String imageName) throws FailedRequestException {
    client.newRequest() // 
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_DELETE_SR_IMAGE, name) //
        .withBody(Collections.emptyMap()) //
        .withParam("api_type", "json") //
        .withParam("img_name", imageName) //
        .post();
  }

  /**
   * Returns the label of the submit button.
   *
   * @return The label of the submit button.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_API_SUBMIT_TEXT
   */
  public String getSubmitText() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_API_SUBMIT_TEXT, name) //
        .get();

    Map<?, ?> data = Things.transform(response, Map.class);

    return data.get("submit_text").toString();
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

    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_SUBREDDIT_STYLESHEET, name) //
        .withBody(body) //
        .withParam("api_type", "json") //
        .post();
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

    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE) //
        .withImage(data.toByteArray(), imageName) //
        .post();
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

    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE, name) //
        .withHeader(data.toByteArray()) //
        .post();
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

    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE) //
        .withIcon(data.toByteArray()) //
        .post();
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

    client.newRequest() //
        .withEndpoint(Subreddits.POST_R_SUBREDDIT_API_UPLOAD_SR_IMAGE) //
        .withBanner(data.toByteArray()) //
        .post();
  }

  /**
   * Returns the Entity object of this subreddit.
   *
   * @return The Entity object corresponding to this subreddit.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT
   */
  public SubredditEntity getAbout() throws FailedRequestException {
    @Nullable
    SubredditEntity result = subredditCache.getIfPresent(name);

    // Only perform a new request if the account hasn't been cached.
    if (result == null) {
      String response = client.newRequest() //
          .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT, name) //
          .get();

      result = Things.transformThing(response, SubredditEntity.class);

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
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_EDIT, name) //
        .get();

    return Things.transformThing(response, SubredditSettingsEntity.class);
  }

  /**
   * Returns the rules of this subreddit.
   *
   * @return The Entity corresponding to the subreddit rules.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_RULES
   */
  public RulesEntity getRules() throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_RULES, name) //
        .get();

    return Things.transform(response, RulesEntity.class);
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
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_TRAFFIC, name) //
        .get();

    return Things.transform(response, Map.class);
  }

  /**
   * Returns the stickied link.
   *
   * @param index The index of the stickied link. Has to be either {@code 1} or
   *              {@code 2}.
   * @return The Entity corresponding to the link.
   * @throws FailedRequestException If the API requests was rejected.
   * @see Subreddits#GET_R_SUBREDDIT_ABOUT_STICKY
   */
  public LinkEntity getSticky(int index) throws FailedRequestException {
    String response = client.newRequest() //
        .withEndpoint(Subreddits.GET_R_SUBREDDIT_ABOUT_STICKY, name) //
        .withParam("num", index) //
        .get();

    ThingEntity[] things = Things.transform(response, ThingEntity[].class);
    ListingEntity listing = Things.transform(things[0].getData(), ListingEntity.class);
    ThingEntity thing = Things.transformListing(listing, ThingEntity.class).findFirst()
        .orElseThrow();
    return Things.transformThing(thing, LinkEntity.class);
  }
}
