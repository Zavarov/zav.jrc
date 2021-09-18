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

package zav.jrc.view.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import zav.jrc.databind.Account;
import zav.jrc.databind.Award;
import zav.jrc.databind.Link;
import zav.jrc.databind.Subreddit;
import zav.jrc.databind.SubredditSettings;
import zav.jrc.databind.Thing;
import zav.jrc.databind.TrophyList;
import zav.jrc.databind.core.Listing;

public class JsonUtils {
  private static final Map<String, Class<?>> KINDS = new HashMap<>();
  
  static {
    //KINDS.put("t1", Comment.class);
    KINDS.put("t2", Account.class);
    KINDS.put("t3", Link.class);
    //KINDS.put("t4", Message.class);
    KINDS.put("t5", Subreddit.class);
    KINDS.put("t6", Award.class);
    KINDS.put("Listing", Listing.class);
    KINDS.put("TrophyList", TrophyList.class);
    KINDS.put("subreddit_settings", SubredditSettings.class);
  }
  
  public static <T> Stream<T> transformListingOfThings(String source, Class<T> target) {
    return transformListingOfThings(transformThing(source, Listing.class), target);
  }
  
  public static <T> Stream<T> transformListingOfThings(Listing source, Class<T> target) {
    List<Thing> things = transformListing(source, Thing.class).collect(Collectors.toList());
    List<T> result = new ArrayList<>();
    
    for (Thing thing : things) {
      result.add(transformThing(thing, target));
    }
    
    return result.stream();
  }
  
  public static <T> Stream<T> transformListing(String source, Class<T> target) {
    return transformListing(transformThing(source, Listing.class), target);
  }
  
  public static <T> Stream<T> transformListing(Listing source, Class<T> target) {
    List<T> objects = new ArrayList<>();
    
    for (Object object : source.getChildren()) {
      objects.add(transform(object, target));
    }
    
    return objects.stream();
  }
  
  public static <T> T transformThing(String source, Class<T> target)  {
    return transformThing(transform(source, Thing.class), target);
  }
  
  public static <T> T transformThing(Thing source, Class<T> target) {
    // Validation
    Class<?> expectedClass = KINDS.get(source.getKind());
    Objects.requireNonNull(expectedClass, source.getKind());
    if (!Objects.equals(expectedClass, target)) {
      throw new IllegalArgumentException("The provided Thing is not of type " + target + ".");
    }
    // Data is internally a map => Convert back to JSON
    return transform(source.getData(), target);
  }
  
  public static <T> T transform(Object source, Class<T> target) {
    try {
      ObjectMapper om = new ObjectMapper();
      return transform(om.writeValueAsString(source), target);
    } catch (IOException e) {
      // Serialization ought to always work
      throw new RuntimeException(e);
    }
  }
  
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
