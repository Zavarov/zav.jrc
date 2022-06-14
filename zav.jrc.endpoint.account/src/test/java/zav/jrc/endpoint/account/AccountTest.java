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
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.AwardEntity;
import zav.jrc.databind.CommentEntity;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.ThingEntity;
import zav.jrc.databind.UserEntity;
import zav.jrc.endpoint.test.ClientMock;

/**
 * Checks whether the calls to the account-related endpoints return the expected response.
 */
public class AccountTest {
  
  String name = "Account";
  Client client;
  Account account;
  
  @BeforeEach
  public void setUp() throws FailedRequestException {
    client = new ClientMock();
    client.login(Duration.TEMPORARY);
    account = new Account(client, name);
  }
  
  @Test
  public void testBlock() throws FailedRequestException {
    account.block();
  }
  
  @Test
  public void testReport() throws FailedRequestException {
    // DON'T TEST AGAINST THE REAL API
    account.report("Very good reason...");
  }
  
  @Test
  public void testUnblock() throws FailedRequestException {
    account.unblock();
  }
  
  @Test
  public void testGetUserData() throws FailedRequestException {
    account.getUserData();
  }
  
  @Test
  public void testIsAvailable() throws FailedRequestException {
    assertThat(account.isAvailable()).isFalse();
  }
  
  @Test
  public void testUnfriend() throws FailedRequestException {
    account.unfriend();
  }
  
  @Test
  public void testFriend() throws FailedRequestException {
    UserEntity response = account.friend("note");
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    UserEntity response = account.getFriends();
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<AwardEntity> response = account.getTrophies().collect(Collectors.toList());
    assertThat(response).hasSize(2);
    assertThat(response.get(0).getName()).isEqualTo("Four-Year Club");
    assertThat(response.get(1).getName()).isEqualTo("Verified Email");
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    AccountEntity response = account.getAbout();
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetComments() throws FailedRequestException {
    List<CommentEntity> response = account.getComments().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("t1_Comment");
  }
  
  @Test
  public void testGetDownvoted() throws FailedRequestException {
    List<ThingEntity> response = account.getDownvoted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
  
  @Test
  public void testGetGilded() throws FailedRequestException {
    List<ThingEntity> response = account.getGilded().collect(Collectors.toList());
    assertThat(response).isEmpty();
  }
  
  @Test
  public void testGetHidden() throws FailedRequestException {
    List<ThingEntity> response = account.getHidden().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
  
  @Test
  public void testGetOverview() throws FailedRequestException {
    List<ThingEntity> response = account.getOverview().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t1");
  }
  
  @Test
  public void testGetSaved() throws FailedRequestException {
    List<ThingEntity> response = account.getSaved().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t1");
  }
  
  @Test
  public void testGetSubmitted() throws FailedRequestException {
    List<LinkEntity> response = account.getSubmitted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getSubreddit()).isEqualTo("Subreddit");
  }
  
  @Test
  public void testGetUpvoted() throws FailedRequestException {
    List<ThingEntity> response = account.getUpvoted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
}
