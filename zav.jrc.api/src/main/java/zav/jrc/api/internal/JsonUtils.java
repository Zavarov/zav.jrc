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

package zav.jrc.api.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import zav.jrc.databind.AccountDto;
import zav.jrc.databind.AwardDto;
import zav.jrc.databind.CommentDto;
import zav.jrc.databind.LinkDto;
import zav.jrc.databind.SubredditSettingsDto;
import zav.jrc.databind.SubredditDto;
import zav.jrc.databind.ThingDto;
import zav.jrc.databind.TrophyListDto;
import zav.jrc.databind.core.ListingDto;

/**
 * Utility class for deserializing the API responses.
 */
public class JsonUtils {
  private static final Map<String, Class<?>> KINDS = new HashMap<>();
  
  static {
    KINDS.put("t1", CommentDto.class);
    KINDS.put("t2", AccountDto.class);
    KINDS.put("t3", LinkDto.class);
    //KINDS.put("t4", Message.class);
    KINDS.put("t5", SubredditDto.class);
    KINDS.put("t6", AwardDto.class);
    KINDS.put("Listing", ListingDto.class);
    KINDS.put("TrophyList", TrophyListDto.class);
    KINDS.put("subreddit_settings", SubredditSettingsDto.class);
  }
  
  /**
   * Deserialized all objects contained by the {@code Listing}.<br>
   * The listing is contained within a {@link ThingDto}.
   *
   * @param source A serialized {@link ListingDto} of Things.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return A list of {@code T} contained by the {@code Listing}.
   */
  public static <T> Stream<T> transformListingOfThings(String source, Class<T> target) {
    return transformListingOfThings(transformThing(source, ListingDto.class), target);
  }
  
  /**
   * Deserialized all objects contained by the {@code Listing}.
   *
   * @param source A {@link ListingDto} of Things.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return A list of {@code T} contained by the {@code Listing}.
   */
  public static <T> Stream<T> transformListingOfThings(ListingDto source, Class<T> target) {
    List<ThingDto> things = transformListing(source, ThingDto.class).collect(Collectors.toList());
    List<T> result = new ArrayList<>();
    
    for (ThingDto thing : things) {
      result.add(transformThing(thing, target));
    }
    
    return result.stream();
  }
  
  /**
   * Deserialized all objects contained by the {@code Listing}.<br>
   *    * The listing is contained within a {@link ThingDto}.
   *
   * @param source A serialized {@link ListingDto}.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return A list of {@code T} contained by the {@code Listing}.
   */
  public static <T> Stream<T> transformListing(String source, Class<T> target) {
    return transformListing(transformThing(source, ListingDto.class), target);
  }
  
  /**
   * Deserialized all objects contained by the {@code Listing}.
   *
   * @param source A {@link ListingDto}.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return A list of {@code T} contained by the {@code Listing}.
   */
  public static <T> Stream<T> transformListing(ListingDto source, Class<T> target) {
    List<T> objects = new ArrayList<>();
    
    for (Object object : source.getChildren()) {
      objects.add(transform(object, target));
    }
    
    return objects.stream();
  }
  
  /**
   * Deserializes the object contained by the {@link ThingDto}.<br>
   * {@link ThingDto#getKind()} has to match the desired class.
   *
   * @param source A serialized {@link ThingDto}.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transformThing(String source, Class<T> target)  {
    return transformThing(transform(source, ThingDto.class), target);
  }
  
  /**
   * Deserializes the object contained by the {@link ThingDto}.<br>
   * {@link ThingDto#getKind()} has to match the desired class.
   *
   * @param source A {@link ThingDto}.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transformThing(ThingDto source, Class<T> target) {
    // Validation
    Class<?> expectedClass = KINDS.get(source.getKind());
    Objects.requireNonNull(expectedClass, source.getKind());
    if (!Objects.equals(expectedClass, target)) {
      throw new IllegalArgumentException("The provided Thing is not of type " + target + ".");
    }
    // Data is internally a map => Convert back to JSON
    return transform(source.getData(), target);
  }
  
  /**
   * Deserializes the provided Java object.
   *
   * @param source Java object.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transform(Object source, Class<T> target) {
    try {
      ObjectMapper om = new ObjectMapper();
      return transform(om.writeValueAsString(source), target);
    } catch (IOException e) {
      // Serialization ought to always work
      throw new RuntimeException(e);
    }
  }
  
  /**
   * Deserializes the provided JSON string.
   *
   * @param source Raw JSON string.
   * @param target Desired class.
   * @param <T> Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transform(String source, Class<T> target) {
    try {
      ObjectMapper om = new ObjectMapper();
      return om.readValue(source, target);
    } catch (IOException e) {
      // The input string ought to always be well-behaved
      throw new RuntimeException(e);
    }
  }
}
