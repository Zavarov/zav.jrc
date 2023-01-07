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

package zav.jrc.databind;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Base class for all entity tests.
 */
public abstract class AbstractTest {
  private static final Path RESOURCES = Paths.get("src/test/resources");

  /**
   * Deserializes a class based on the content of a JSON file.
   *
   * @param fileName The name of the file to be read.
   * @param clazz The class of the target type.
   * @param <T> The target type.
   * @return An instance of the target type initializes with the content of the provided file.
   */
  public static <T> T read(String fileName, Class<T> clazz) {
    try {
      ObjectMapper om = new ObjectMapper();
      return om.readValue(RESOURCES.resolve(fileName).toFile(), clazz);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    }
  }

  public static <T> T read(Object source, Class<T> clazz) {
    ObjectMapper om = new ObjectMapper();
    return om.convertValue(source, clazz);
  }
}
