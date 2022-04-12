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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a messaging entity have been properly deserialized.
 */
public class MessagingEntityTest extends AbstractTest {
  static UserListEntity[] messaging;
  static UserListEntity blocked;
  static UserListEntity trusted;
  
  /**
   * Instantiates the messaging DTO and retrieves the trusted and blocked users.
   */
  @BeforeAll
  public static void setUpAll() {
    messaging = read("Messaging.json", UserListEntity[].class);
    trusted = messaging[0];
    blocked = messaging[1];
  }
  
  @Test
  public void testGetTrusted() {
    List<UserEntity> children = trusted.getData().getChildren();
    UserEntity child = children.get(0);
    
    assertThat(trusted.getKind()).isEqualTo("UserList");
    assertThat(children).hasSize(1);
    assertThat(child.getDate()).isEqualTo(1.612556744E9);
    assertThat(child.getName()).isEqualTo("Jimbo");
    assertThat(child.getId()).isEqualTo("r9_aabbcc");
    assertThat(child.getUserId()).isEqualTo("t2_ccddee");
  }
  
  @Test
  public void testGetBlocked() {
    List<UserEntity> children = blocked.getData().getChildren();
    UserEntity child = children.get(0);
    
    assertThat(blocked.getKind()).isEqualTo("UserList");
    assertThat(children).hasSize(1);
    assertThat(child.getDate()).isEqualTo(1.522543721E9);
    assertThat(child.getName()).isEqualTo("Citrine");
    assertThat(child.getId()).isEqualTo("r9_ddeeff");
    assertThat(child.getUserId()).isEqualTo("t2_ffgghh");
  }
}
