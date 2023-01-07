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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a karma list entity have been properly deserialized.
 */
public class KarmaListEntityTest extends AbstractTest {
  static KarmaListEntity karmaList;
  static KarmaEntity karma;

  @BeforeAll
  public static void setUpAll() {
    karmaList = read("KarmaList.json", KarmaListEntity.class);
    karma = karmaList.getData().get(0);
  }

  @Test
  public void testGetData() {
    assertEquals(karmaList.getData().size(), 1);
  }

  @Test
  public void testGetKind() {
    assertEquals(karmaList.getKind(), "KarmaList");
  }

  @Test
  public void testGetCommentKarma() {
    assertEquals(karma.getCommentKarma(), 69);
  }

  @Test
  public void testGetLinkKarma() {
    assertEquals(karma.getLinkKarma(), 42);
  }

  @Test
  public void testGetSubreddit() {
    assertEquals(karma.getSubreddit(), "bananapics");
  }
}
