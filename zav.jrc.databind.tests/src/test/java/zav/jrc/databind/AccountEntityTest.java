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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of an account entity have been properly deserialized.
 */
public class AccountEntityTest extends AbstractTest {
  static ThingEntity thing;
  static AccountEntity account;
  
  @BeforeAll
  public static void setUpAll() {
    thing = read("Account.json", ThingEntity.class);
    account = read(thing.getData(), AccountEntity.class);
  }
  
  @Test
  public void testGetKind() {
    assertEquals(thing.getKind(), "t2");
  }
  
  @Test
  public void testGetCommentKarma() {
    assertEquals(account.getCommentKarma(), 3141592);
  }
  
  @Test
  public void testGetHasMail() {
    assertNull(account.getHasMail());
  }
  
  @Test
  public void testGetHasModMail() {
    assertNull(account.getHasModMail());
  }
  
  @Test
  public void testGetHasVerifiedMail() {
    assertTrue(account.getHasVerifiedEmail());
  }
  
  @Test
  public void testGetInboxCount() {
    assertNull(account.getInboxCount());
  }
  
  @Test
  public void getIsFriend() {
    assertFalse(account.getIsFriend());
  }
  
  @Test
  public void testGetIsGoldMember() {
    assertTrue(account.getIsGoldMember());
  }
  
  @Test
  public void testGetIsMod() {
    assertTrue(account.getIsMod());
  }
  
  @Test
  public void testGetLinkKarma() {
    assertEquals(account.getLinkKarma(), 3141592);
  }
  
  @Test
  public void testGetModHash() {
    assertNull(account.getModHash());
  }
  
  @Test
  public void testGetOver18() {
    assertNull(account.getOver18());
  }
  
  @Test
  public void testGetHasSubscribed() {
    assertTrue(account.getHasSubscribed());
  }
  
  @Test
  public void testGetAwarderKarma() {
    assertEquals(account.getAwarderKarma(), 0);
  }
  
  @Test
  public void testGetAwardeeKarma() {
    assertEquals(account.getAwardeeKarma(), 17699);
  }
  
  @Test
  public void testGetIconImage() {
    assertEquals(account.getIconImage(), "https://styles.redditmedia.com/t5_hv5dz/styles/profileIcon_snoo8658e16c-55fa-486f-b7c7-00726de2e742-headshot.png?width=256&amp;height=256&amp;crop=256:256,smart&amp;s=f61c0e3ac1a1d9357c21086feb78943c31d19d37");
  }
  
  @Test
  public void testGetTotalKarma() {
    assertEquals(account.getTotalKarma(), 6300883);
  }
  
  @Test
  public void testGetVerified() {
    assertTrue(account.getVerified());
  }
  
  @Test
  public void testGetIsEmployee() {
    assertTrue(account.getIsEmployee());
  }
  
  @Test
  public void testGetAcceptChats() {
    assertFalse(account.getAcceptChats());
  }
  
  @Test
  public void testGetAcceptPrivateMessages() {
    assertTrue(account.getAcceptPrivateMessages());
  }
  
  // Created
  
  @Test
  public void testGetCreated() {
    assertEquals(account.getCreated(), 1.1341332E9);
  }
  
  @Test
  public void testGetCreatedUtc() {
    assertEquals(account.getCreatedUtc(), 1.1341044E9);
  }
}
