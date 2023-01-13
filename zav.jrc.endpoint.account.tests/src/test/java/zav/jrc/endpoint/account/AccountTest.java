/*
 * Copyright (c) 2023 Zavarov
 * 
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
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
 * Checks whether the calls to the account-related endpoints return the expected
 * response.
 */
@ExtendWith(MockitoExtension.class)
public class AccountTest {

  MockedStatic<Things> mocked;
  String name = "Account";
  String reason = "Very good reason...";
  Account account;
  @Mock
  Client client;
  RequestBuilder request;

  /**
   * Initializes all fields and binds the {@link #request} to
   * {@link Client#newRequest()}.
   *
   * @throws FailedRequestException Never
   */
  @BeforeEach
  public void setUp() throws FailedRequestException {
    request = spy(new RequestBuilder(client));

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

    verify(request).withEndpoint(Users.POST_API_BLOCK_USER);
    verify(request).withParam("name", name);
    verify(request).post();
  }

  @Test
  public void testReport() throws FailedRequestException {
    account.report(reason);

    verify(request).withEndpoint(Users.POST_API_REPORT_USER);
    verify(request).withBody(Map.of("user", name, "reason", reason), RequestBuilder.BodyType.JSON);
    verify(request).post();
  }

  @Test
  public void testUnblock() throws FailedRequestException {
    mocked.when(() -> Things.transformThing(anyString(), any())).thenReturn(new AccountEntity());
    // Load the account into memory and clear request
    account.getAbout();

    account.unblock();

    verify(request).withEndpoint(Users.POST_API_UNFRIEND);
    verify(request).withParam("container", "t2_null");
    verify(request).withParam("name", name);
    verify(request).withParam("type", "enemy");
    verify(request).post();
  }

  @Test
  public void testIsAvailable() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(true);
    assertTrue(account.isAvailable());

    verify(request).withEndpoint(Users.GET_API_USERNAME_AVAILABLE);
    verify(request).withParam("user", name);
    verify(request).get();
  }

  @Test
  public void testUnfriend() throws FailedRequestException {
    account.unfriend();

    verify(request).withEndpoint(Users.DELETE_API_V1_ME_FRIENDS_USERNAME, name);
    verify(request).delete();
  }

  @Test
  public void testFriend() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new UserEntity());
    assertNotNull(account.friend("note"));

    verify(request).withEndpoint(Users.PUT_API_V1_ME_FRIENDS_USERNAME);
    verify(request).withBody(Map.of("note", "note"), RequestBuilder.BodyType.JSON, name);
    verify(request).put();
  }

  @Test
  public void testGetFriends() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new UserEntity());
    assertNotNull(account.getFriends());

    verify(request).withEndpoint(Users.GET_API_V1_ME_FRIENDS_USERNAME, name);
    verify(request).get();
  }

  @Test
  public void testGetTrophies() throws FailedRequestException {
    mocked.when(() -> Things.transformThing(anyString(), any())).thenReturn(new TrophyListEntity());
    assertNotNull(account.getTrophies());

    verify(request).withEndpoint(Users.GET_API_V1_USER_USERNAME_TROPHIES, name);
    verify(request).get();
  }

  @Test
  public void testGetAbout() throws FailedRequestException {
    mocked.when(() -> Things.transformThing(anyString(), any())).thenReturn(new AccountEntity());
    assertNotNull(account.getAbout());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_ABOUT, name);
    verify(request).get();
  }

  @Test
  public void testGetComments() throws FailedRequestException {
    assertNotNull(account.getComments());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_COMMENTS, name);
    verify(request).get();
  }

  @Test
  public void testGetDownvoted() throws FailedRequestException {
    assertNotNull(account.getDownvoted());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_DOWNVOTED, name);
    verify(request).get();
  }

  @Test
  public void testGetGilded() throws FailedRequestException {
    assertNotNull(account.getGilded());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_GILDED, name);
    verify(request).get();
  }

  @Test
  public void testGetHidden() throws FailedRequestException {
    assertNotNull(account.getHidden());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_HIDDEN, name);
    verify(request).get();
  }

  @Test
  public void testGetOverview() throws FailedRequestException {
    assertNotNull(account.getOverview());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_OVERVIEW, name);
    verify(request).get();
  }

  @Test
  public void testGetSaved() throws FailedRequestException {
    assertNotNull(account.getSaved());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_SAVED, name);
    verify(request).get();
  }

  @Test
  public void testGetSubmitted() throws FailedRequestException {
    assertNotNull(account.getSubmitted());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_SUBMITTED, name);
    verify(request).get();
  }

  @Test
  public void testGetUpvoted() throws FailedRequestException {
    assertNotNull(account.getUpvoted());

    verify(request).withEndpoint(Users.GET_USER_USERNAME_UPVOTED, name);
    verify(request).get();
  }
}
