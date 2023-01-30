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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import zav.jrc.client.FailedRequestException;
import zav.jrc.endpoint.subreddit.FrontPage;
import zav.jrc.its.AbstractIntegrationTest;

public class FrontPageTest extends AbstractIntegrationTest {
  FrontPage frontPage;

  @BeforeEach
  public void setUp() {
    frontPage = new FrontPage(client);
  }

  @Test
  public void testGetBest() throws FailedRequestException {
    assertTrue(frontPage.getBest(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetControversial() throws FailedRequestException {
    assertTrue(frontPage.getControversial(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetHot() throws FailedRequestException {
    assertTrue(frontPage.getHot(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetNew() throws FailedRequestException {
    assertTrue(frontPage.getNew(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetRandom() throws FailedRequestException {
    assertTrue(frontPage.getRandom().count() > 0);
  }

  @Test
  public void testGetRising() throws FailedRequestException {
    assertTrue(frontPage.getRising(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetTop() throws FailedRequestException {
    assertTrue(frontPage.getTop(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testSearch() throws FailedRequestException {
    assertTrue(frontPage.search(Map.of("type", "sr", "q", "banana")).count() > 0);
  }

  @Test
  public void testFindSubreddits() throws FailedRequestException {
    assertTrue(frontPage.findSubreddits(Map.of("q", "pasta")).count() > 0);
  }

  @Test
  public void testFindUsers() throws FailedRequestException {
    assertTrue(frontPage.findUsers(Map.of("q", "automod")).count() > 0);
  }

  @Test
  public void testSearchRedditNames() throws FailedRequestException {
    assertTrue(frontPage.searchRedditNames(Map.of("query", "penguin")).count() > 0);
  }

  @Test
  public void testQuerySubreddits() throws FailedRequestException {
    assertTrue(frontPage.searchSubreddits(Map.of("query", "apple")).count() > 0);
  }

  @Test
  public void testGetSubredditAutocomplete() throws FailedRequestException {
    assertTrue(frontPage.getSubredditAutocomplete(Map.of("query", "gaming")).count() > 0);
  }

  @Test
  public void testGetDefaultSubreddits() throws FailedRequestException {
    assertTrue(frontPage.getDefaultSubreddits(Collections.emptyMap()).count() > 0);
  }

  @Test
  @Disabled
  public void testGetGoldSubreddits() throws FailedRequestException {
    assertTrue(frontPage.getGoldSubreddits(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetNewSubreddits() throws FailedRequestException {
    assertTrue(frontPage.getNewSubreddits(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetPopularSubreddits() throws FailedRequestException {
    assertTrue(frontPage.getPopularSubreddits(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetNewUserSubreddits() throws FailedRequestException {
    assertTrue(frontPage.getNewUserSubreddits(Collections.emptyMap()).count() > 0);
  }

  @Test
  public void testGetPopularUserSubreddits() throws FailedRequestException {
    assertTrue(frontPage.getPopularUserSubreddits(Collections.emptyMap()).count() > 0);
  }
}
