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

package zav.jrc.endpoint.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AwardEntity;
import zav.jrc.databind.KarmaEntity;
import zav.jrc.databind.PreferencesEntity;
import zav.jrc.databind.SelfAccountEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.databind.UserEntity;
import zav.jrc.endpoint.test.ClientMock;

/**
 * Checks whether the calls to the self-account-related endpoints return the expected response.
 */
public class SelfAccountTest {
  
  Client client;
  SelfAccount selfAccount;
  
  @BeforeEach
  public void setUp() throws FailedRequestException {
    client = new ClientMock();
    client.login(Duration.TEMPORARY);
    selfAccount = new SelfAccount(client);
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    SelfAccountEntity response = selfAccount.getAbout();
    assertThat(response.getId()).isEqualTo("abcdef");
  }
  
  @Test
  public void testGetKarma() throws FailedRequestException {
    List<KarmaEntity> response = selfAccount.getKarma().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getSubreddit()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetPreferences() throws FailedRequestException {
    PreferencesEntity response = selfAccount.getPreferences();
    assertThat(response.getCountryCode()).isEqualTo("XX");
  }
  
  @Test
  public void testUpdatePreferences() throws FailedRequestException {
    PreferencesEntity response = selfAccount.updatePreferences(selfAccount.getPreferences());
    assertThat(response.getCountryCode()).isEqualTo("XX");
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<AwardEntity> response = selfAccount.getTrophies().collect(Collectors.toList());
    assertThat(response).hasSize(2);
    assertThat(response.get(0).getName()).isEqualTo("Four-Year Club");
    assertThat(response.get(1).getName()).isEqualTo("Verified Email");
  }
  
  @Test
  public void testGetBlocked() throws FailedRequestException {
    List<UserEntity> response = selfAccount.getBlocked().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    List<UserEntity> response = selfAccount.getFriends().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetTrusted() throws FailedRequestException {
    List<UserEntity> response = selfAccount.getTrusted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  // Subreddit
  
  @Test
  public void testGetMineContributor() throws FailedRequestException {
    List<SubredditEntity> response = selfAccount.getMineContributor().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetMineModerator() throws FailedRequestException {
    List<SubredditEntity> response = selfAccount.getMineModerator().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetMineSubscriber() throws FailedRequestException {
    List<SubredditEntity> response = selfAccount.getMineSubscriber().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
}
