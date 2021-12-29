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
 * Checks whether the attributes of an account DTO have been properly deserialized.
 */
public class AccountTest extends AbstractTest {
  static ThingDto thing;
  static AccountDto account;
  
  @BeforeAll
  public static void setUpAll() {
    thing = read("Account.json", ThingDto.class);
    account = read(thing.getData(), AccountDto.class);
  }
  
  @Test
  public void testGetKind() {
    assertThat(thing.getKind()).isEqualTo("t2");
  }
  
  @Test
  public void testGetCommentKarma() {
    assertThat(account.getCommentKarma()).isEqualTo(3141592);
  }
  
  @Test
  public void testGetHasMail() {
    assertThat(account.getHasMail()).isNull();
  }
  
  @Test
  public void testGetHasModMail() {
    assertThat(account.getHasModMail()).isNull();
  }
  
  @Test
  public void testGetHasVerifiedMail() {
    assertThat(account.getHasVerifiedEmail()).isTrue();
  }
  
  @Test
  public void testGetInboxCount() {
    assertThat(account.getInboxCount()).isNull();
  }
  
  @Test
  public void getIsFriend() {
    assertThat(account.getIsFriend()).isFalse();
  }
  
  @Test
  public void testGetIsGoldMember() {
    assertThat(account.getIsGoldMember()).isTrue();
  }
  
  @Test
  public void testGetIsMod() {
    assertThat(account.getIsMod()).isTrue();
  }
  
  @Test
  public void testGetLinkKarma() {
    assertThat(account.getLinkKarma()).isEqualTo(3141592);
  }
  
  @Test
  public void testGetModHash() {
    assertThat(account.getModHash()).isNull();
  }
  
  @Test
  public void testGetOver18() {
    assertThat(account.getOver18()).isNull();
  }
  
  @Test
  public void testGetHasSubscribed() {
    assertThat(account.getHasSubscribed()).isTrue();
  }
  
  @Test
  public void testGetAwarderKarma() {
    assertThat(account.getAwarderKarma()).isEqualTo(0);
  }
  
  @Test
  public void testGetAwardeeKarma() {
    assertThat(account.getAwardeeKarma()).isEqualTo(17699);
  }
  
  @Test
  public void testGetIconImage() {
    assertThat(account.getIconImage()).isEqualTo("https://styles.redditmedia.com/t5_hv5dz/styles/profileIcon_snoo8658e16c-55fa-486f-b7c7-00726de2e742-headshot.png?width=256&amp;height=256&amp;crop=256:256,smart&amp;s=f61c0e3ac1a1d9357c21086feb78943c31d19d37");
  }
  
  @Test
  public void testGetTotalKarma() {
    assertThat(account.getTotalKarma()).isEqualTo(6300883);
  }
  
  @Test
  public void testGetVerified() {
    assertThat(account.getVerified()).isTrue();
  }
  
  @Test
  public void testGetIsEmployee() {
    assertThat(account.getIsEmployee()).isTrue();
  }
  
  @Test
  public void testGetAcceptChats() {
    assertThat(account.getAcceptChats()).isFalse();
  }
  
  @Test
  public void testGetAcceptPrivateMessages() {
    assertThat(account.getAcceptPrivateMessages()).isTrue();
  }
  
  // Created
  
  @Test
  public void testGetCreated() {
    assertThat(account.getCreated()).isEqualTo(1.1341332E9);
  }
  
  @Test
  public void testGetCreatedUtc() {
    assertThat(account.getCreatedUtc()).isEqualTo(1.1341044E9);
  }
}
