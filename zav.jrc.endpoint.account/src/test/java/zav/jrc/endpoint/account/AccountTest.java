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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import zav.jrc.api.endpoint.Users;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.http.RequestBuilder;
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.Things;
import zav.jrc.databind.TrophyListEntity;
import zav.jrc.databind.UserEntity;

/**
 * Checks whether the calls to the account-related endpoints return the expected response.
 */
@ExtendWith(MockitoExtension.class)
public class AccountTest {
  
  MockedStatic<Things> mocked;
  String name = "Account";
  String reason = "Very good reason...";
  Account account;
  @Mock Client client;
  @Spy RequestBuilder request;
  
  /**
   * Initializes all fields and binds the {@link #request} to {@link Client#newRequest()}.
   *
   * @throws FailedRequestException Never
   */
  @BeforeEach
  public void setUp() throws FailedRequestException {
    when(client.newRequest()).thenReturn(request);
    when(client.send(any())).thenReturn("{}");
    account = new Account(client, name);
    mocked = mockStatic(Things.class);
  }
  
  @AfterEach
  public void tearDown() {
    mocked.close();
  }
  
  @Test
  public void testBlock() throws FailedRequestException {
    account.block();
    
    verify(request).setEndpoint(Users.POST_API_BLOCK_USER);
    verify(request).addParam("name", name);
    verify(request).post();
  }
  
  @Test
  public void testReport() throws FailedRequestException {
    account.report(reason);
    
    verify(request).setEndpoint(Users.POST_API_REPORT_USER);
    verify(request).setBody(Map.of("user", name, "reason", reason), RequestBuilder.BodyType.JSON);
    verify(request).post();
  }
  
  @Test
  public void testUnblock() throws FailedRequestException {
    mocked.when(() -> Things.transformThing(anyString(), any())).thenReturn(new AccountEntity());
    // Load the account into memory and clear request
    account.getAbout();
    request.setArgs();
    
    account.unblock();
  
    verify(request).setEndpoint(Users.POST_API_UNFRIEND);
    verify(request).addParam("container", "t2_null");
    verify(request).addParam("name", name);
    verify(request).addParam("type", "enemy");
    verify(request).post();
  }
  
  @Test
  public void testIsAvailable() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(true);
    assertTrue(account.isAvailable());
  
    verify(request).setEndpoint(Users.GET_API_USERNAME_AVAILABLE);
    verify(request).addParam("user", name);
    verify(request).get();
  }
  
  @Test
  public void testUnfriend() throws FailedRequestException {
    account.unfriend();
  
    verify(request).setEndpoint(Users.DELETE_API_V1_ME_FRIENDS_USERNAME);
    verify(request).setArgs(name);
    verify(request).delete();
  }
  
  @Test
  public void testFriend() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new UserEntity());
    assertNotNull(account.friend("note"));
  
    verify(request).setEndpoint(Users.PUT_API_V1_ME_FRIENDS_USERNAME);
    verify(request).setBody(Map.of("note", "note"), RequestBuilder.BodyType.JSON);
    verify(request).setArgs(name);
    verify(request).put();
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new UserEntity());
    assertNotNull(account.getFriends());
  
    verify(request).setEndpoint(Users.GET_API_V1_ME_FRIENDS_USERNAME);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    mocked.when(() -> Things.transformThing(anyString(), any())).thenReturn(new TrophyListEntity());
    assertNotNull(account.getTrophies());
  
    verify(request).setEndpoint(Users.GET_API_V1_USER_USERNAME_TROPHIES);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    mocked.when(() -> Things.transformThing(anyString(), any())).thenReturn(new AccountEntity());
    assertNotNull(account.getAbout());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_ABOUT);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetComments() throws FailedRequestException {
    assertNotNull(account.getComments());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_COMMENTS);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetDownvoted() throws FailedRequestException {
    assertNotNull(account.getDownvoted());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_DOWNVOTED);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetGilded() throws FailedRequestException {
    assertNotNull(account.getGilded());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_GILDED);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetHidden() throws FailedRequestException {
    assertNotNull(account.getHidden());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_HIDDEN);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetOverview() throws FailedRequestException {
    assertNotNull(account.getOverview());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_OVERVIEW);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetSaved() throws FailedRequestException {
    assertNotNull(account.getSaved());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_SAVED);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetSubmitted() throws FailedRequestException {
    assertNotNull(account.getSubmitted());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_SUBMITTED);
    verify(request).setArgs(name);
    verify(request).get();
  }
  
  @Test
  public void testGetUpvoted() throws FailedRequestException {
    assertNotNull(account.getUpvoted());
  
    verify(request).setEndpoint(Users.GET_USER_USERNAME_UPVOTED);
    verify(request).setArgs(name);
    verify(request).get();
  }
}
