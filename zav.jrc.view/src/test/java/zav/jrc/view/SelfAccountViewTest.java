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

package zav.jrc.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.FailedRequestException;
import zav.jrc.databind.*;
import zav.jrc.view.guice.SelfAccountViewFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SelfAccountViewTest extends AbstractTest {
  
  SelfAccountView view;
  
  @BeforeEach
  public void setUp() {
    SelfAccountViewFactory factory = GUICE.getInstance(SelfAccountViewFactory.class);
    view = factory.create();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    SelfAccount response = view.getAbout();
    assertThat(response.getId()).isEqualTo("abcdef");
  }
  
  @Test
  public void testGetKarma() throws FailedRequestException {
    List<Karma> response = view.getKarma().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getSubreddit()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetPreferences() throws FailedRequestException {
    Preferences response = view.getPreferences();
    assertThat(response.getCountryCode()).isEqualTo("XX");
  }
  
  @Test
  public void testPatchPreferences() throws FailedRequestException {
    Preferences response = view.patchPreferences(view.getPreferences());
    assertThat(response.getCountryCode()).isEqualTo("XX");
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<Award> response = view.getTrophies().collect(Collectors.toList());
    assertThat(response).hasSize(2);
    assertThat(response.get(0).getName()).isEqualTo("Four-Year Club");
    assertThat(response.get(1).getName()).isEqualTo("Verified Email");
  }
  
  @Test
  public void testGetBlocked() throws FailedRequestException {
    List<User> response = view.getBlocked().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    List<User> response = view.getFriends().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetTrusted() throws FailedRequestException {
    List<User> response = view.getTrusted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("Username");
  }
  
  // Subreddit
  
  @Test
  public void testGetMineContributor() throws FailedRequestException {
    List<Subreddit> response = view.getMineContributor().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetMineModerator() throws FailedRequestException {
    List<Subreddit> response = view.getMineModerator().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetMineSubscriber() throws FailedRequestException {
    List<Subreddit> response = view.getMineSubscriber().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getDisplayName()).isEqualTo("Subreddit");
  }
}
