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
import zav.jrc.Parameter;
import zav.jrc.view.guice.FrontPageViewFactory;

public class FrontPageViewTest extends AbstractTest {
  
  FrontPageView view;
  
  @BeforeEach
  public void setUp() {
    FrontPageViewFactory factory = GUICE.getInstance(FrontPageViewFactory.class);
    view = factory.create();
  }
  
  @Test
  public void testGetBest() throws FailedRequestException {
    view.getBest();
  }
  
  @Test
  public void testGetControversial() throws FailedRequestException {
    view.getControversial();
  }
  
  @Test
  public void testGetHot() throws FailedRequestException {
    view.getHot();
  }
  
  @Test
  public void testGetNew() throws FailedRequestException {
    view.getNew();
  }
  
  @Test
  public void testGetRandom() throws FailedRequestException {
    view.getRandom();
  }
  
  @Test
  public void testGetRising() throws FailedRequestException {
    view.getRising();
  }
  
  @Test
  public void testGetTop() throws FailedRequestException {
    view.getTop();
  }
  
  // Search
  
  @Test
  public void testGetSearch() throws FailedRequestException {
    view.getSearch(new Parameter("q", "bananapics"));
  }
  
  // Subreddits
  
  @Test
  public void testPostSearchRedditNames() throws FailedRequestException {
    view.postSearchRedditNames(new Parameter("query", "banana"));
  }
  
  @Test
  public void testPostSearchSubreddits() throws FailedRequestException {
    view.postSearchSubreddits(new Parameter("query", "banana"));
  }
  
  @Test
  public void testGetSubredditAutocomplete() throws FailedRequestException {
    view.getSubredditAutocomplete(new Parameter("query", "banana"));
  }
  
  @Test
  public void testGetDefaultSubreddits() throws FailedRequestException {
    view.getDefaultSubreddits();
  }
  
  @Test
  public void testGetGoldSubreddits() throws FailedRequestException {
    view.getGoldSubreddits();
  }
  
  @Test
  public void testGetNewSubreddits() throws FailedRequestException {
    view.getNewSubreddits();
  }
  
  @Test
  public void testGetPopularSubreddits() throws FailedRequestException {
    view.getPopularSubreddits();
  }
  
  @Test
  public void testGetSearchSubreddits() throws FailedRequestException {
    view.getSearchSubreddits(new Parameter("q", "banana"));
  }
  
  @Test
  public void testGetNewUserSubreddits() throws FailedRequestException {
    view.getNewUserSubreddits();
  }
  
  @Test
  public void testGetPopularUserSubreddits() throws FailedRequestException {
    view.getPopularUserSubreddits();
  }
  
  @Test
  public void testGetSearchUserSubreddits() throws FailedRequestException {
    view.getSearchUserSubreddits(new Parameter("q", "banana"));
  }
}
