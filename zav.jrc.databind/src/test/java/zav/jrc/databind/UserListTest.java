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

package zav.jrc.databind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a user list DTO have been properly deserialized.
 */
public class UserListTest extends AbstractTest {
  static UserList userList;
  static User user;
  
  @BeforeAll
  public static void setUpAll() {
    userList = read("UserList.json", UserList.class);
    user = userList.getData().getChildren().get(0);
  }
  
  @Test
  public void testGetData() {
    assertThat(userList.getData().getChildren()).hasSize(1);
  }
  
  @Test
  public void testGetKind() {
    assertThat(userList.getKind()).isEqualTo("UserList");
  }
  
  @Test
  public void testGetDate() {
    assertThat(user.getDate()).isEqualTo(1.234567890E9);
  }
  
  @Test
  public void testGetName() {
    assertThat(user.getName()).isEqualTo("Bernkastel");
  }
  
  @Test
  public void testGetId() {
    assertThat(user.getId()).isEqualTo("r9_abcdefg");
  }
  
  @Test
  public void testGetUserId() {
    assertThat(user.getUserId()).isEqualTo("t2_124567");
  }
}
