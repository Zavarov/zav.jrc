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

package zav.jrc.its.endpoint.subreddit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.endpoint.subreddit.Subreddit;
import zav.jrc.its.AbstractIntegrationTest;

public class SubredditTest extends AbstractIntegrationTest {
  Subreddit subreddit;

  @BeforeEach
  public void setUp() {
    subreddit = new Subreddit(client, "redditdev");
  }

  @Test
  public void testGetControversial() throws FailedRequestException {
    assertTrue(subreddit.getControversial(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetHot() throws FailedRequestException {
    assertTrue(subreddit.getHot(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetNew() throws FailedRequestException {
    assertTrue(subreddit.getNew(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetRandom() throws FailedRequestException {
    assertTrue(subreddit.getRandom().count() > 0);
  }

  @Test
  public void testGetRising() throws FailedRequestException {
    assertTrue(subreddit.getRising(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetTop() throws FailedRequestException {
    assertTrue(subreddit.getTop(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testSearch() throws FailedRequestException {
    assertTrue(subreddit.search(Map.of("type", "sr", "q", "banana")).count() > 0);
  }

  @Test
  public void testGetPostRequirements() throws FailedRequestException {
    assertFalse(subreddit.getPostRequirements().isEmpty());
  }

  @Test
  public void testGetModerators() throws FailedRequestException {
    assertTrue(subreddit.getModerators().count() > 0);
  }

  @Test
  public void testGetSubmitText() throws FailedRequestException {
    assertNotNull(subreddit.getSubmitText());
  }

  @Test
  public void testGetAbout() throws FailedRequestException {
    SubredditEntity entity = subreddit.getAbout();

    assertEquals(entity.getDisplayName(), "redditdev");
  }

  @Test
  public void testGetRules() throws FailedRequestException {
    assertTrue(subreddit.getRules().getRules().size() > 4);
  }
}
