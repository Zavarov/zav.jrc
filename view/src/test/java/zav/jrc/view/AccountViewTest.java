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
import zav.jrc.view.guice.ClientMock;
import zav.jrc.view.guice.AccountViewFactory;

public class AccountViewTest extends AbstractTest {
  
  AccountView view;
  
  @BeforeEach
  public void setUp() {
    AccountViewFactory factory = GUICE.getInstance(AccountViewFactory.class);
    view = factory.create("username");
  }
  
  @Test
  public void testPostBlock() throws FailedRequestException {
    view.postBlock();
  }
  
  @Test
  public void testPostReport() throws FailedRequestException {
    if (CLIENT instanceof ClientMock) {
      // DON'T TEST AGAINST THE REAL API
      view.postReport(null);
    }
  }
  
  @Test
  public void testPostUnblock() throws FailedRequestException {
    view.postUnblock();
  }
  
  @Test
  public void testGetUserData() throws FailedRequestException {
    view.getUserData();
  }
  
  @Test
  public void testIsAvailable() throws FailedRequestException {
    view.getAvailable();
  }
  
  @Test
  public void testDeleteFriends() throws FailedRequestException {
    view.deleteFriends();
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    view.getFriends();
  }
  
  @Test
  public void testPutFriends() throws FailedRequestException {
    view.putFriends(null);
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    view.getTrophies();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    view.getAbout();
  }
  
  @Test
  public void testGetComments() throws FailedRequestException {
    view.getComments();
  }
  
  @Test
  public void testGetDownvoted() throws FailedRequestException {
    view.getDownvoted();
  }
  
  @Test
  public void testGetGilded() throws FailedRequestException {
    view.getGilded();
  }
  
  @Test
  public void testGetHidden() throws FailedRequestException {
    view.getHidden();
  }
  
  @Test
  public void testGetOverview() throws FailedRequestException {
    view.getOverview();
  }
  
  @Test
  public void testGetSaved() throws FailedRequestException {
    view.getSaved();
  }
  
  @Test
  public void testGetSubmitted() throws FailedRequestException {
    view.getSubmitted();
  }
  
  @Test
  public void testGetUpvoted() throws FailedRequestException {
    view.getUpvoted();
  }
}
