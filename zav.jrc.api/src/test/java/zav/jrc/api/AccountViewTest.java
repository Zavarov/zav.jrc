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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.api.internal.guice.ClientMock;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountViewTest extends AbstractTest {
  
  Account account;
  
  @BeforeEach
  public void setUp() {
    account = REDDIT.getAccount("username");
  }
  
  @Test
  public void testPostBlock() throws FailedRequestException {
    assertThat(account.postBlock()).isNotEmpty();
  }
  
  @Test
  public void testPostReport() throws FailedRequestException {
    if (CLIENT instanceof ClientMock) {
      // DON'T TEST AGAINST THE REAL API
      account.postReport(null);
    }
  }
  
  @Test
  public void testPostUnblock() throws FailedRequestException {
    account.postUnblock();
  }
  
  @Test
  public void testGetUserData() throws FailedRequestException {
    account.getUserData();
  }
  
  @Test
  public void testIsAvailable() throws FailedRequestException {
    assertThat(account.getAvailable()).isFalse();
  }
  
  @Test
  public void testDeleteFriends() throws FailedRequestException {
    account.deleteFriends();
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    UserDto response = account.getFriends();
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testPutFriends() throws FailedRequestException {
    UserDto response = account.putFriends("note");
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<AwardDto> response = account.getTrophies().collect(Collectors.toList());
    assertThat(response).hasSize(2);
    assertThat(response.get(0).getName()).isEqualTo("Four-Year Club");
    assertThat(response.get(1).getName()).isEqualTo("Verified Email");
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    AccountDto response = account.getAbout();
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetComments() throws FailedRequestException {
    List<CommentDto> response = account.getComments().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("t1_Comment");
  }
  
  @Test
  public void testGetDownvoted() throws FailedRequestException {
    List<ThingDto> response = account.getDownvoted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
  
  @Test
  public void testGetGilded() throws FailedRequestException {
    List<ThingDto> response = account.getGilded().collect(Collectors.toList());
    assertThat(response).isEmpty();
  }
  
  @Test
  public void testGetHidden() throws FailedRequestException {
    List<ThingDto> response = account.getHidden().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
  
  @Test
  public void testGetOverview() throws FailedRequestException {
    List<ThingDto> response = account.getOverview().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t1");
  }
  
  @Test
  public void testGetSaved() throws FailedRequestException {
    List<ThingDto> response = account.getSaved().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t1");
  }
  
  @Test
  public void testGetSubmitted() throws FailedRequestException {
    List<LinkDto> response = account.getSubmitted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getSubreddit()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetUpvoted() throws FailedRequestException {
    List<ThingDto> response = account.getUpvoted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
}
