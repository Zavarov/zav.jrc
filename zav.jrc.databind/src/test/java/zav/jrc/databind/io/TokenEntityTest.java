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

package zav.jrc.databind.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import zav.jrc.databind.AbstractTest;

/**
 * Checks whether the attributes of a reddit token entity have been properly deserialized.
 */
public class TokenEntityTest extends AbstractTest {
  static TokenEntity token;
  
  @BeforeAll
  public static void setUpAll() {
    token = read("Token.json", TokenEntity.class);
  }
  
  @Test
  public void testGetAccessToken() {
    assertEquals(token.getAccessToken(), "ACCESS_TOKEN");
  }
  
  @Test
  public void testGetTokenType() {
    assertEquals(token.getTokenType(), "bearer");
  }
  
  @Test
  public void testGetExpiresIn() {
    assertEquals(token.getExpiresIn(), 12345);
  }
  
  @Test
  public void testGetScope() {
    assertEquals(token.getScope(), "all");
  }
  
  @Test
  public void testGetRefreshToken() {
    assertEquals(token.getRefreshToken(), "REFRESH_TOKEN");
  }
}
