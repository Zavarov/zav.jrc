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

package zav.jrc.databind;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.databind.core.ListingEntity;

/**
 * Utility class for deserializing the API responses.
 */
@SuppressWarnings("unused")
@NonNullByDefault
public class Things {
  private static final Map<String, Class<?>> KINDS = new HashMap<>();

  static {
    KINDS.put("t1", CommentEntity.class);
    KINDS.put("t2", AccountEntity.class);
    KINDS.put("t3", LinkEntity.class);
    // KINDS.put("t4", Message.class);
    KINDS.put("t5", SubredditEntity.class);
    KINDS.put("t6", AwardEntity.class);
    KINDS.put("Listing", ListingEntity.class);
    KINDS.put("TrophyList", TrophyListEntity.class);
    KINDS.put("subreddit_settings", SubredditSettingsEntity.class);
  }

  /**
   * Deserialized all objects contained by the {@link ListingEntity}.<br>
   * The listing is contained within a {@link ThingEntity}.
   *
   * @param source A serialized {@link ListingEntity} of Things.
   * @param target Desired class.
   * @param <T>    Expected type.
   * @return A list of {@code T} contained by the {@link ListingEntity}.
   */
  public static <T> Stream<T> transformListingOfThings(String source, Class<T> target) {
    return transformListingOfThings(transformThing(source, ListingEntity.class), target);
  }

  /**
   * Deserialized all objects contained by the {@link ListingEntity}.
   *
   * @param source A {@link ListingEntity} of Things.
   * @param target Desired class.
   * @param <T>    Expected type.
   * @return A list of {@code T} contained by the {@link ListingEntity}.
   */
  public static <T> Stream<T> transformListingOfThings(ListingEntity source, Class<T> target) {
    List<ThingEntity> things = transformListing(source, ThingEntity.class)
        .collect(Collectors.toList());
    List<T> result = new ArrayList<>();

    for (ThingEntity thing : things) {
      result.add(transformThing(thing, target));
    }

    return result.stream();
  }

  /**
   * Deserialized all objects contained by the {@link ListingEntity}.<br>
   * * The listing is contained within a {@link ThingEntity}.
   *
   * @param source A serialized {@link ListingEntity}.
   * @param target Desired class.
   * @param <T>    Expected type.
   * @return A list of {@code T} contained by the {@link ListingEntity}.
   */
  public static <T> Stream<T> transformListing(String source, Class<T> target) {
    return transformListing(transformThing(source, ListingEntity.class), target);
  }

  /**
   * Deserialized all objects contained by the {@link ListingEntity}.
   *
   * @param source A {@link ListingEntity}.
   * @param target Desired class.
   * @param <T>    Expected type.
   * @return A list of {@code T} contained by the {@link ListingEntity}.
   */
  public static <T> Stream<T> transformListing(ListingEntity source, Class<T> target) {
    List<T> objects = new ArrayList<>();

    for (Object object : source.getChildren()) {
      objects.add(transform(object, target));
    }

    return objects.stream();
  }

  /**
   * Deserializes the object contained by the {@link ThingEntity}.<br>
   * {@link ThingEntity#getKind()} has to match the desired class.
   *
   * @param source A serialized {@link ThingEntity}.
   * @param target Desired class.
   * @param <T>    Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transformThing(String source, Class<T> target) {
    return transformThing(transform(source, ThingEntity.class), target);
  }

  /**
   * Deserializes the object contained by the {@link ThingEntity}.<br>
   * {@link ThingEntity#getKind()} has to match the desired class.
   *
   * @param source A {@link ThingEntity}.
   * @param target Desired class.
   * @param <T>    Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transformThing(ThingEntity source, Class<T> target) {
    // Validation
    Class<?> expectedClass = KINDS.get(source.getKind());
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
   * @param <T>    Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transform(Object source, Class<T> target) {
    try {
      ObjectMapper om = new ObjectMapper();
      return transform(om.writeValueAsString(source), target);
    } catch (IOException e) {
      // Serialization ought to always work
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Deserializes the provided JSON string.
   *
   * @param source Raw JSON string.
   * @param target Desired class.
   * @param <T>    Expected type.
   * @return Instance of {@code T}.
   */
  public static <T> T transform(String source, Class<T> target) {
    try {
      ObjectMapper om = new ObjectMapper();
      return om.readValue(source, target);
    } catch (IOException e) {
      // The input string ought to always be well-behaved
      throw new IllegalArgumentException(e);
    }
  }
}
