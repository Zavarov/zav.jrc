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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import zav.jrc.api.endpoint.Account;
import zav.jrc.api.endpoint.Subreddits;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.http.RequestBuilder;
import zav.jrc.databind.KarmaListEntity;
import zav.jrc.databind.PreferencesEntity;
import zav.jrc.databind.SelfAccountEntity;
import zav.jrc.databind.Things;
import zav.jrc.databind.TrophyListEntity;
import zav.jrc.databind.UserListDataEntity;
import zav.jrc.databind.UserListEntity;

/**
 * Checks whether the calls to the self-account-related endpoints return the expected response.
 */
@ExtendWith(MockitoExtension.class)
public class SelfAccountTest {
  
  MockedStatic<Things> mocked;
  SelfAccount selfAccount;
  UserListEntity users;
  TrophyListEntity trophies;
  @Mock Client client;
  @Spy RequestBuilder request;
  
  /**
   * Initializes all fields and binds the {@link #request} to {@link Client#newRequest()}.<br>
   * {@link #users} is initialized with an empty list of users.<br>
   * {@link #trophies} is initialized with an empty list of trophies.<br>
   *
   * @throws FailedRequestException Never
   */
  @BeforeEach
  public void setUp() throws FailedRequestException {
    when(client.newRequest()).thenReturn(request);
    when(client.send(any())).thenReturn("{}");
    selfAccount = new SelfAccount(client);
    trophies = new TrophyListEntity();
    trophies.setTrophies(Collections.emptyList());
    users = new UserListEntity();
    users.setData(new UserListDataEntity());
    mocked = mockStatic(Things.class);
  }
  
  @AfterEach
  public void tearDown() {
    mocked.close();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new SelfAccountEntity());
    assertNotNull(selfAccount.getAbout());
  
    verify(request).setEndpoint(Account.GET_API_V1_ME);
    verify(request).get();
  }
  
  @Test
  public void testGetKarma() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new KarmaListEntity());
    assertNotNull(selfAccount.getKarma());
  
    verify(request).setEndpoint(Account.GET_API_V1_ME_KARMA);
    verify(request).get();
  }
  
  @Test
  public void testGetPreferences() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new PreferencesEntity());
    assertNotNull(selfAccount.getPreferences());
  
    verify(request).setEndpoint(Account.GET_API_V1_ME_PREFS);
    verify(request).get();
  }
  
  @Test
  public void testUpdatePreferences() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new PreferencesEntity());
    assertNotNull(selfAccount.updatePreferences(new PreferencesEntity()));
  
    verify(request).setEndpoint(Account.PATCH_API_V1_ME_PREFS);
    verify(request).patch();
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    mocked.when(() -> Things.transformThing(anyString(), any())).thenReturn(trophies);
    assertNotNull(selfAccount.getTrophies());
  
    verify(request).setEndpoint(Account.GET_API_V1_ME_TROPHIES);
    verify(request).get();
  }
  
  @Test
  public void testGetBlocked() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(users);
    assertNotNull(selfAccount.getBlocked());
  
    verify(request).setEndpoint(Account.GET_PREFS_BLOCKED);
    verify(request).get();
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(new UserListEntity[]{users});
    assertNotNull(selfAccount.getFriends());
  
    verify(request).setEndpoint(Account.GET_PREFS_FRIENDS);
    verify(request).get();
  }
  
  @Test
  public void testGetTrusted() throws FailedRequestException {
    mocked.when(() -> Things.transform(anyString(), any())).thenReturn(users);
    assertNotNull(selfAccount.getTrusted());
    
  
    verify(request).setEndpoint(Account.GET_PREFS_TRUSTED);
    verify(request).get();
  }
  
  @Test
  public void testGetMineContributor() throws FailedRequestException {
    assertNotNull(selfAccount.getMineContributor());
  
    verify(request).setEndpoint(Subreddits.GET_SUBREDDITS_MINE_CONTRIBUTOR);
    verify(request).get();
  }
  
  @Test
  public void testGetMineModerator() throws FailedRequestException {
    assertNotNull(selfAccount.getMineModerator());
  
    verify(request).setEndpoint(Subreddits.GET_SUBREDDITS_MINE_MODERATOR);
    verify(request).get();
  }
  
  @Test
  public void testGetMineSubscriber() throws FailedRequestException {
    assertNotNull(selfAccount.getMineSubscriber());
  
    verify(request).setEndpoint(Subreddits.GET_SUBREDDITS_MINE_SUBSCRIBER);
    verify(request).get();
    
  }
}
