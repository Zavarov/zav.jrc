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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a self account entity have been properly deserialized.
 */
public class SelfAccountEntityTest extends AbstractTest {
  static SelfAccountEntity selfAccount;

  @BeforeAll
  public static void setUpAll() {
    selfAccount = read("SelfAccount.json", SelfAccountEntity.class);
  }

  @Test
  public void testGetOver18() {
    assertTrue(selfAccount.getOver18());
  }

  @Test
  public void testGetCommentKarma() {
    assertEquals(selfAccount.getCommentKarma(), 1707);
  }

  @Test
  public void testGetCreatedUtc() {
    assertEquals(selfAccount.getCreatedUtc(), 1471206215.0);
  }

  @Test
  public void testGetNumberOfFriends() {
    assertEquals(selfAccount.getNumberOfFriends(), 1);
  }

  @Test
  public void testGetCreated() {
    assertEquals(selfAccount.getCreated(), 1471235015.0);
  }

  @Test
  public void testGetTotalKarma() {
    assertEquals(selfAccount.getTotalKarma(), 1732);
  }

  @Test
  public void testGetLinkKarma() {
    assertEquals(selfAccount.getLinkKarma(), 25);
  }

  @Test
  public void testGetIconImage() {
    assertEquals(selfAccount.getIconImage(), "https://styles.redditmedia.com/t5_abcdef/styles/profileIcon_foo.png");
  }
}
