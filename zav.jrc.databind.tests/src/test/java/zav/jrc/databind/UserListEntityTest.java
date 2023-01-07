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

package zav.jrc.databind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a user list entity have been properly deserialized.
 */
public class UserListEntityTest extends AbstractTest {
  static UserListEntity userList;
  static UserEntity user;

  @BeforeAll
  public static void setUpAll() {
    userList = read("UserList.json", UserListEntity.class);
    user = userList.getData().getChildren().get(0);
  }

  @Test
  public void testGetData() {
    assertEquals(userList.getData().getChildren().size(), 1);
  }

  @Test
  public void testGetKind() {
    assertEquals(userList.getKind(), "UserList");
  }

  @Test
  public void testGetDate() {
    assertEquals(user.getDate(), 1.234567890E9);
  }

  @Test
  public void testGetName() {
    assertEquals(user.getName(), "Bernkastel");
  }

  @Test
  public void testGetId() {
    assertEquals(user.getId(), "r9_abcdefg");
  }

  @Test
  public void testGetUserId() {
    assertEquals(user.getUserId(), "t2_124567");
  }
}
