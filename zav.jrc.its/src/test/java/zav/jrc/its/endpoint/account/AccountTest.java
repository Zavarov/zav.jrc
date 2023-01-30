/*
 * Copyright (c) 2023 Zavarov.
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

package zav.jrc.its.endpoint.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.AwardEntity;
import zav.jrc.endpoint.account.Account;
import zav.jrc.its.AbstractIntegrationTest;

public class AccountTest extends AbstractIntegrationTest {
  Account account;

  @BeforeEach
  public void setUp() {
    account = new Account(client, "Exact-Analysis5010");
  }

  @Test
  public void testIsAvailable() throws FailedRequestException {
    assertFalse(account.isAvailable());
  }

  @Test
  public void testGetTrophies() throws FailedRequestException {
    List<AwardEntity> response = account.getTrophies().collect(Collectors.toList());
    assertEquals(response.size(), 1);
    assertEquals(response.get(0).getName(), "Verified Email");
  }

  @Test
  public void testGetAbout() throws FailedRequestException {
    AccountEntity entity = account.getAbout();

    assertEquals(entity.getName(), "Exact-Analysis5010");
    assertEquals(entity.getCreated(), 1.673637596E9);
    assertEquals(entity.getVerified(), true);
  }

  @Test
  public void testGetComments() throws FailedRequestException {
    assertEquals(account.getComments(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetDownvoted() throws FailedRequestException {
    assertEquals(account.getDownvoted(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetGilded() throws FailedRequestException {
    assertEquals(account.getGilded(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetHidden() throws FailedRequestException {
    assertEquals(account.getHidden(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetOverview() throws FailedRequestException {
    assertEquals(account.getOverview(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetSaved() throws FailedRequestException {
    assertEquals(account.getSaved(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetSubmitted() throws FailedRequestException {
    assertEquals(account.getSubmitted(Collections.emptyMap()).count(), 0);
  }

  @Test
  public void testGetUpvoted() throws FailedRequestException {
    assertEquals(account.getUpvoted(Collections.emptyMap()).count(), 0);
  }
}
