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

package zav.jrc.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AwardDto;
import zav.jrc.databind.KarmaDto;
import zav.jrc.databind.PreferencesDto;
import zav.jrc.databind.SelfAccountDto;
import zav.jrc.databind.SubredditDto;
import zav.jrc.databind.UserDto;

/**
 * Checks whether the calls to the self-account-related endpoints return the expected response.
 */
public class SelfAccountTest extends AbstractTest {
  
  SelfAccount selfAccount;
  
  @BeforeEach
  public void setUp() {
    selfAccount = REDDIT.getSelfAccount();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    SelfAccountDto response = selfAccount.getAbout();
    assertThat(response.getId()).isEqualTo("abcdef");
  }
  
  @Test
  public void testGetKarma() throws FailedRequestException {
    List<KarmaDto> response = selfAccount.getKarma().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getSubreddit()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetPreferences() throws FailedRequestException {
    PreferencesDto response = selfAccount.getPreferences();
    assertThat(response.getCountryCode()).isEqualTo("XX");
  }
  
  @Test
  public void testPatchPreferences() throws FailedRequestException {
    PreferencesDto response = selfAccount.patchPreferences(selfAccount.getPreferences());
    assertThat(response.getCountryCode()).isEqualTo("XX");
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<AwardDto> response = selfAccount.getTrophies().collect(Collectors.toList());
    assertThat(response).hasSize(2);
    assertThat(response.get(0).getName()).isEqualTo("Four-Year Club");
    assertThat(response.get(1).getName()).isEqualTo("Verified Email");
  }
  
  @Test
  public void testGetBlocked() throws FailedRequestException {
    List<UserDto> response = selfAccount.getBlocked().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    List<UserDto> response = selfAccount.getFriends().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetTrusted() throws FailedRequestException {
    List<UserDto> response = selfAccount.getTrusted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  // Subreddit
  
  @Test
  public void testGetMineContributor() throws FailedRequestException {
    List<SubredditDto> response = selfAccount.getMineContributor().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetMineModerator() throws FailedRequestException {
    List<SubredditDto> response = selfAccount.getMineModerator().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetMineSubscriber() throws FailedRequestException {
    List<SubredditDto> response = selfAccount.getMineSubscriber().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
}
