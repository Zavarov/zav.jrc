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

package zav.jrc.endpoint.subreddit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.AccountEntity;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.endpoint.test.ClientMock;
import zav.jrc.http.Parameter;

/**
 * Checks whether the calls to the frontpage-related endpoints return the expected response.
 */
public class FrontPageTest {
  
  Client client;
  FrontPage frontPage;

  /**
   * Initializes a front page view with a mocked Reddit client.
   *
   * @throws FailedRequestException Should never happen.
   */
  @BeforeEach
  public void setUp() throws FailedRequestException {
    client = new ClientMock();
    client.login(Duration.TEMPORARY);
    frontPage = new FrontPage(client);
  }
  
  @Test
  public void testGetBest() throws FailedRequestException {
    List<LinkEntity> response = frontPage.getBest().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Omfgg!!!");
  }
  
  @Test
  public void testGetControversial() throws FailedRequestException {
    List<LinkEntity> response = frontPage.getControversial().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Evil Texas legislators smiling as they sign law to take away rights");
  }
  
  @Test
  public void testGetHot() throws FailedRequestException {
    List<LinkEntity> response = frontPage.getHot().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Omfgg!!!");
  }
  
  @Test
  public void testGetNew() throws FailedRequestException {
    List<LinkEntity> response = frontPage.getNew().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Uk. i can print 600*600*800 on resin printer anyone interested?");
  }
  
  @Test
  public void testGetRandom() throws FailedRequestException {
    List<LinkEntity> response = frontPage.getRandom().collect(Collectors.toList());
    assertThat(response).hasSize(1);
    assertThat(response.get(0).getTitle()).isEqualTo("Philadelphia’s Vine Street Expressway after Hurricane Ida 02 September 2021");
  }
  
  @Test
  public void testGetRising() throws FailedRequestException {
    List<LinkEntity> response = frontPage.getRising().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Come again?");
  }
  
  @Test
  public void testGetTop() throws FailedRequestException {
    List<LinkEntity> response = frontPage.getTop().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("People of Reddit, it is our duty!");
  }
  
  // Search
  
  @Test
  public void testSearch() throws FailedRequestException {
    Parameter param = new Parameter("q", "bananapics");
    List<LinkEntity> response = frontPage.search(param).collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Having only one power hotkey is crazytown bananapants");
  }
  
  // Subreddits
  
  @Test
  public void testSearchRedditNames() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<String> response = frontPage.searchRedditNames(param).collect(Collectors.toList());
    assertThat(response).hasSize(5);
    assertThat(response).containsExactly("banana", "BananasForScale", "BananaFish", "bananarchist", "Bananafight");
  }
  
  @Test
  public void testQuerySubreddits() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<SubredditEntity> response = frontPage.querySubreddits(param).collect(Collectors.toList());
    assertThat(response).hasSize(9);
    assertThat(response.get(0).getName()).contains("banana");
  }
  
  @Test
  public void testGetSubredditAutocomplete() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<SubredditEntity> response = frontPage.getSubredditAutocomplete(param).collect(Collectors.toList());
    assertThat(response).hasSize(5);
    assertThat(response.get(0).getDisplayName()).contains("banana");
  }
  
  @Test
  public void testGetDefaultSubreddits() throws FailedRequestException {
    List<SubredditEntity> response = frontPage.getDefaultSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("gadgets");
  }
  
  @Test
  public void testGetGoldSubreddits() throws FailedRequestException {
    List<SubredditEntity> response = frontPage.getGoldSubreddits().collect(Collectors.toList());
    assertThat(response).isEmpty();
  }
  
  @Test
  public void testGetNewSubreddits() throws FailedRequestException {
    List<SubredditEntity> response = frontPage.getNewSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("pusaneko");
  }
  
  @Test
  public void testGetPopularSubreddits() throws FailedRequestException {
    List<SubredditEntity> response = frontPage.getPopularSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Home");
  }
  
  @Test
  public void testSearchSubreddits() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<SubredditEntity> response = frontPage.searchSubreddits(param).collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("banana");
  }
  
  @Test
  public void testGetNewUserSubreddits() throws FailedRequestException {
    List<SubredditEntity> response = frontPage.getNewUserSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("");
  }
  
  @Test
  public void testGetPopularUserSubreddits() throws FailedRequestException {
    List<SubredditEntity> response = frontPage.getPopularUserSubreddits().collect(Collectors.toList());
    assertThat(response).hasSize(25);
    assertThat(response.get(0).getTitle()).isEqualTo("Selben");
  }
  
  @Test
  public void testGetSearchUserSubreddits() throws FailedRequestException {
    Parameter param = new Parameter("q", "banana");
    List<AccountEntity> response = frontPage.getSearchUserSubreddits(param).collect(Collectors.toList());
    assertThat(response).hasSize(11);
    assertThat(response.get(0).getName()).isEqualTo("Hates_escalators");
  }
}
