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

package zav.jrc.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AccountDto;
import zav.jrc.databind.LinkDto;
import zav.jrc.databind.SubredditDto;
import zav.jrc.http.Parameter;

/**
 * Checks whether the calls to the frontpage-related endpoints return the expected response.
 */
public class FrontPageTest extends AbstractTest {
  
  FrontPage frontPage;
  
  @BeforeEach
  public void setUp() {
    frontPage = REDDIT.getFrontPage();
  }
  
  @Test
  public void testGetBest() throws FailedRequestException {
    List<LinkDto> response = frontPage.getBest().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Omfgg!!!");
  }
  
  @Test
  public void testGetControversial() throws FailedRequestException {
    List<LinkDto> response = frontPage.getControversial().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Evil Texas legislators smiling as they sign law to take away rights");
  }
  
  @Test
  public void testGetHot() throws FailedRequestException {
    List<LinkDto> response = frontPage.getHot().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Omfgg!!!");
  }
  
  @Test
  public void testGetNew() throws FailedRequestException {
    List<LinkDto> response = frontPage.getNew().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Uk. i can print 600*600*800 on resin printer anyone interested?");
  }
  
  @Test
  public void testGetRandom() throws FailedRequestException {
    List<LinkDto> response = frontPage.getRandom().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getTitle()).isEqualTo("Philadelphia’s Vine Street Expressway after Hurricane Ida 02 September 2021");
  }
  
  @Test
  public void testGetRising() throws FailedRequestException {
    List<LinkDto> response = frontPage.getRising().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Come again?");
  }
  
  @Test
  public void testGetTop() throws FailedRequestException {
    List<LinkDto> response = frontPage.getTop().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("People of Reddit, it is our duty!");
  }
  
  // Search
  
  @Test
  public void testGetSearch() throws FailedRequestException {
    Parameter param = new Parameter("q", "bananapics");
    List<LinkDto> response = frontPage.getSearch(param).collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Having only one power hotkey is crazytown bananapants");
  }
  
  // Subreddits
  
  @Test
  public void testPostSearchRedditNames() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<String> response = frontPage.postSearchRedditNames(param).collect(Collectors.toList());
    assertThat(response).hasSize(5);
    assertThat(response).containsExactly("banana", "BananasForScale", "BananaFish", "bananarchist", "Bananafight");
  }
  
  @Test
  public void testPostSearchSubreddits() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<SubredditDto> response = frontPage.postSearchSubreddits(param).collect(Collectors.toList());
    assertThat(response).hasSize(9);
    assertThat(response.get(0).getName()).contains("banana");
  }
  
  @Test
  public void testGetSubredditAutocomplete() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<SubredditDto> response = frontPage.getSubredditAutocomplete(param).collect(Collectors.toList());
    assertThat(response).hasSize(5);
    assertThat(response.get(0).getDisplayName()).contains("banana");
  }
  
  @Test
  public void testGetDefaultSubreddits() throws FailedRequestException {
    List<SubredditDto> response = frontPage.getDefaultSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("gadgets");
  }
  
  @Test
  public void testGetGoldSubreddits() throws FailedRequestException {
    List<SubredditDto> response = frontPage.getGoldSubreddits().collect(Collectors.toList());
    assertThat(response).isEmpty();
  }
  
  @Test
  public void testGetNewSubreddits() throws FailedRequestException {
    List<SubredditDto> response = frontPage.getNewSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("pusaneko");
  }
  
  @Test
  public void testGetPopularSubreddits() throws FailedRequestException {
    List<SubredditDto> response = frontPage.getPopularSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Home");
  }
  
  @Test
  public void testGetSearchSubreddits() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<SubredditDto> response = frontPage.getSearchSubreddits(param).collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("banana");
  }
  
  @Test
  public void testGetNewUserSubreddits() throws FailedRequestException {
    List<SubredditDto> response = frontPage.getNewUserSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("");
  }
  
  @Test
  public void testGetPopularUserSubreddits() throws FailedRequestException {
    List<SubredditDto> response = frontPage.getPopularUserSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Selben");
  }
  
  @Test
  public void testGetSearchUserSubreddits() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<AccountDto> response = frontPage.getSearchUserSubreddits(param).collect(Collectors.toList());
    assertThat(response).hasSize(11);
    assertThat(response.get(0).getName()).isEqualTo("Hates_escalators");
  }
}
