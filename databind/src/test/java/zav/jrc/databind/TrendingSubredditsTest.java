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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrendingSubredditsTest extends AbstractTest{
  static TrendingSubreddits trendingSubreddits;
  
  @BeforeAll
  public static void setUpAll() {
    trendingSubreddits = read("TrendingSubreddits.json", TrendingSubreddits.class);
  }
  
  @Test
  public void testGetCommentCount() {
    assertThat(trendingSubreddits.getCommentCount()).isEqualTo(4);
  }
  
  @Test
  public void testGetCommentUrl() {
    assertThat(trendingSubreddits.getCommentUrl()).isEqualTo("/r/trendingsubreddits/comments/l5xdvw/trending_subreddits_for_20210127_rsmallstreetbets/");
  }
  
  @Test
  public void testGetSubredditNames() {
    assertThat(trendingSubreddits.getSubredditNames()).containsExactly("smallstreetbets", "bluejackets", "japan", "biggreenegg", "BigMouth");
  }
}
