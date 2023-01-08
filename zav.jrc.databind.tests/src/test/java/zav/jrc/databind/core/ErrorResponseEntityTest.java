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

package zav.jrc.databind.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import zav.jrc.databind.AbstractTest;

/**
 * Checks whether the attributes of an error response entity have been properly
 * deserialized.
 */
public class ErrorResponseEntityTest extends AbstractTest {
  static ErrorResponseEntity error;
  static ErrorResponseDataEntity data;
  static List<List<String>> messages;

  /**
   * Instantiates the error response DTO and retrieves its error messages.
   */
  @BeforeAll
  public static void setUpAll() {
    error = read("ErrorResponse.json", ErrorResponseEntity.class);
    data = error.getJson();
    messages = data.getErrors();
  }

  @Test
  public void testMessages() {
    assertEquals(messages.get(0).size(), 3);
    assertEquals(messages.get(0).get(0), "SUBREDDIT_EXISTS");
    assertEquals(messages.get(0).get(1), "that subreddit already exists");
    assertEquals(messages.get(0).get(2), "name");
  }
}
