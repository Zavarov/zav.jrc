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

package zav.jrc.databind.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import zav.jrc.databind.AbstractTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorResponseTest extends AbstractTest {
  static ErrorResponseValueObject error;
  static ErrorResponseDataValueObject data;
  static List<List<String>> messages;
  
  @BeforeAll
  public static void setUpAll() {
    error = read("ErrorResponse.json", ErrorResponseValueObject.class);
    data = error.getJson();
    messages = data.getErrors();
  }
  
  @Test
  public void testMessages() {
    assertThat(messages).hasSize(1);
    assertThat(messages.get(0)).containsExactly("SUBREDDIT_EXISTS", "that subreddit already exists", "name");
  }
}
