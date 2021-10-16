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
import zav.jrc.view.guice.ClientMock;
import zav.jrc.view.guice.AccountViewFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountViewTest extends AbstractTest {
  
  AccountView view;
  
  @BeforeEach
  public void setUp() {
    AccountViewFactory factory = GUICE.getInstance(AccountViewFactory.class);
    view = factory.create("username");
  }
  
  @Test
  public void testPostBlock() throws FailedRequestException {
    assertThat(view.postBlock()).isNotEmpty();
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
    assertThat(view.getAvailable()).isFalse();
  }
  
  @Test
  public void testDeleteFriends() throws FailedRequestException {
    view.deleteFriends();
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    User response = view.getFriends();
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testPutFriends() throws FailedRequestException {
    User response = view.putFriends("note");
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<Award> response = view.getTrophies().collect(Collectors.toList());
    assertThat(response).hasSize(2);
    assertThat(response.get(0).getName()).isEqualTo("Four-Year Club");
    assertThat(response.get(1).getName()).isEqualTo("Verified Email");
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    Account response = view.getAbout();
    assertThat(response.getName()).isEqualTo("Username");
  }
  
  @Test
  public void testGetComments() throws FailedRequestException {
    List<Comment> response = view.getComments().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getName()).isEqualTo("t1_Comment");
  }
  
  @Test
  public void testGetDownvoted() throws FailedRequestException {
    List<Thing> response = view.getDownvoted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
  
  @Test
  public void testGetGilded() throws FailedRequestException {
    List<Thing> response = view.getGilded().collect(Collectors.toList());
    assertThat(response).isEmpty();
  }
  
  @Test
  public void testGetHidden() throws FailedRequestException {
    List<Thing> response = view.getHidden().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
  
  @Test
  public void testGetOverview() throws FailedRequestException {
    List<Thing> response = view.getOverview().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t1");
  }
  
  @Test
  public void testGetSaved() throws FailedRequestException {
    List<Thing> response = view.getSaved().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t1");
  }
  
  @Test
  public void testGetSubmitted() throws FailedRequestException {
    List<Thing> response = view.getSubmitted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
  
  @Test
  public void testGetUpvoted() throws FailedRequestException {
    List<Thing> response = view.getUpvoted().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getKind()).isEqualTo("t3");
  }
}
