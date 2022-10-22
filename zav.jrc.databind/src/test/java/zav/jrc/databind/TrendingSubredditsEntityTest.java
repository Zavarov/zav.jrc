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
 * Checks whether the attributes of a trending subreddits entity have been properly deserialized.
 */
public class TrendingSubredditsEntityTest extends AbstractTest {
  static TrendingSubredditsEntity trendingSubreddits;
  
  @BeforeAll
  public static void setUpAll() {
    trendingSubreddits = read("TrendingSubreddits.json", TrendingSubredditsEntity.class);
  }
  
  @Test
  public void testGetCommentCount() {
    assertEquals(trendingSubreddits.getCommentCount(), 4);
  }
  
  @Test
  public void testGetCommentUrl() {
    assertEquals(trendingSubreddits.getCommentUrl(), "/r/trendingsubreddits/comments/l5xdvw/trending_subreddits_for_20210127_rsmallstreetbets/");
  }
  
  @Test
  public void testGetSubredditNames() {
    assertEquals(trendingSubreddits.getSubredditNames().size(), 5);
    assertEquals(trendingSubreddits.getSubredditNames().get(0), "smallstreetbets");
    assertEquals(trendingSubreddits.getSubredditNames().get(1), "bluejackets");
    assertEquals(trendingSubreddits.getSubredditNames().get(2), "japan");
    assertEquals(trendingSubreddits.getSubredditNames().get(3), "biggreenegg");
    assertEquals(trendingSubreddits.getSubredditNames().get(4), "BigMouth");
  }
}
