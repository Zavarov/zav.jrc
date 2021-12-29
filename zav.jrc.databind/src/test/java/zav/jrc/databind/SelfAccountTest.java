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

package zav.jrc.databind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a self account DTO have been properly deserialized.
 */
public class SelfAccountTest extends AbstractTest {
  static SelfAccountDto selfAccount;
  
  @BeforeAll
  public static void setUpAll() {
    selfAccount = read("SelfAccount.json", SelfAccountDto.class);
  }
  
  @Test
  public void testGetOver18() {
    assertThat(selfAccount.getOver18()).isTrue();
  }
  
  @Test
  public void testGetCommentKarma() {
    assertThat(selfAccount.getCommentKarma()).isEqualTo(1707);
  }
  
  @Test
  public void testGetCreatedUtc() {
    assertThat(selfAccount.getCreatedUtc()).isEqualTo(1471206215.0);
  }
  
  @Test
  public void testGetNumberOfFriends() {
    assertThat(selfAccount.getNumberOfFriends()).isEqualTo(1);
  }
  
  @Test
  public void testGetCreated() {
    assertThat(selfAccount.getCreated()).isEqualTo(1471235015.0);
  }
  
  @Test
  public void testGetTotalKarma() {
    assertThat(selfAccount.getTotalKarma()).isEqualTo(1732);
  }
  
  @Test
  public void testGetLinkKarma() {
    assertThat(selfAccount.getLinkKarma()).isEqualTo(25);
  }
  
  @Test
  public void testGetIconImage() {
    assertThat(selfAccount.getIconImage()).isEqualTo("https://styles.redditmedia.com/t5_abcdef/styles/profileIcon_foo.png");
  }
}
