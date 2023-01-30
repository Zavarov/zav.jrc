/*
 * Copyright (c) 2023 Zavarov.
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

package zav.jrc.its.endpoint.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AwardEntity;
import zav.jrc.databind.PreferencesEntity;
import zav.jrc.databind.SelfAccountEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.endpoint.account.SelfAccount;
import zav.jrc.its.AbstractIntegrationTest;

public class SelfAccountTest extends AbstractIntegrationTest {
  SelfAccount selfAccount;

  @BeforeEach
  public void setUp() {
    selfAccount = new SelfAccount(client);
  }

  @Test
  public void testGetAbout() throws FailedRequestException {
    SelfAccountEntity response = selfAccount.getAbout();

    assertEquals(response.getId(), "vh12g04a");
  }

  @Test
  public void testGetKarma() throws FailedRequestException {
    assertEquals(selfAccount.getKarma().count(), 0);
  }

  @Test
  public void testGetPreferences() throws FailedRequestException {
    PreferencesEntity response = selfAccount.getPreferences();
    assertEquals(response.getAcceptPms(), "everyone");
    assertEquals(response.getLayout(), "card");
    assertEquals(response.getMedia(), "subreddit");
  }

  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<AwardEntity> response = selfAccount.getTrophies().collect(Collectors.toList());
    assertEquals(response.size(), 1);
    assertEquals(response.get(0).getName(), "Verified Email");
  }

  @Test
  public void testGetBlocked() throws FailedRequestException {
    assertEquals(selfAccount.getBlocked().count(), 0);
  }

  @Test
  public void testGetFriends() throws FailedRequestException {
    assertEquals(selfAccount.getFriends().count(), 0);
  }

  @Test
  public void testGetTrusted() throws FailedRequestException {
    assertEquals(selfAccount.getTrusted().count(), 0);
  }

  @Test
  public void testGetMineContributor() throws FailedRequestException {
    List<SubredditEntity> response = selfAccount.getMineContributor(Collections.emptyMap())
        .collect(Collectors.toList());

    assertEquals(response.size(), 1);
    assertEquals(response.get(0).getDisplayName(), "u_Exact-Analysis5010");
  }

  @Test
  public void testGetMineModerator() throws FailedRequestException {
    assertEquals(selfAccount.getMineModerator(Collections.emptyMap()).count(), 0);
  }

  @Test
  @Disabled
  public void testGetMineStreams() throws FailedRequestException {
    assertEquals(selfAccount.getMineStreams(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetMineSubscriber() throws FailedRequestException {
    assertEquals(selfAccount.getMineSubscriber(Collections.emptyMap()).count(), 0);
  }
}
