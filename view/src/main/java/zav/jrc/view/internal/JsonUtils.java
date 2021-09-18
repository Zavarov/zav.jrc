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
import java.util.List;
import java.util.stream.Stream;
import zav.jrc.databind.Thing;
import zav.jrc.databind.core.Listing;

public class JsonUtils {
  public static <T> Stream<T> transformListing(String source, Class<T> target) {
    return transformListing(transform(source, Listing.class), target);
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
    // Data is internally a map => Convert back to JSON
    return transform(source.getData(), target);
  }
  
  public static <T> T transform(Object source, Class<T> target) {
    try {
      ObjectMapper om = new ObjectMapper();
      return transform(om.writeValueAsString(source), target);
    } catch(IOException e) {
      // Serialization ought to always work
      throw new RuntimeException(e);
    }
  }
  
  public static <T> T transform(String source, Class<T> target) {
    try {
      ObjectMapper om = new ObjectMapper();
      return om.readValue(source, target);
    } catch(IOException e) {
      // The input string ought to always be well-behaved
      throw new RuntimeException(e);
    }
  }
}
