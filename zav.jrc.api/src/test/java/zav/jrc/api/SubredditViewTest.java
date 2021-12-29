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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.*;
import zav.jrc.http.Parameter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class SubredditViewTest extends AbstractTest {
  Subreddit subreddit;
  
  @BeforeEach
  public void setUp() {
    subreddit = REDDIT.getSubreddit("Subreddit");
  }
  
  @Test
  public void testGetControversial() throws FailedRequestException {
    assertThat(subreddit.getControversial()).hasSize(3);
    
    LinkValueObject response = subreddit.getControversial().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetHot() throws FailedRequestException {
    assertThat(subreddit.getHot()).hasSize(25);
  
    LinkValueObject response = subreddit.getHot().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetNew() throws FailedRequestException {
    assertThat(subreddit.getNew()).hasSize(25);
  
    LinkValueObject response = subreddit.getNew().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetRandom() throws FailedRequestException {
    assertThat(subreddit.getRandom()).hasSize(1);
  
    LinkValueObject response = subreddit.getRandom().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("I'm making a reddit bot that deletes spam mesages based on a specific keyword, but for some reason the message doesn't delete.");
  }
  
  @Test
  public void testGetRising() throws FailedRequestException {
    assertThat(subreddit.getRising()).hasSize(25);
  
    LinkValueObject response = subreddit.getRising().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetTop() throws FailedRequestException {
    assertThat(subreddit.getTop()).hasSize(3);
  
    LinkValueObject response = subreddit.getTop().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Getting user description");
  }
  
  // Search
  
  @Test
  public void testGetSearch() throws FailedRequestException {
    Parameter[] params = new Parameter[]{
          new Parameter("q", "api"),
          new Parameter("restrict_sr", true)
    };
    
    assertThat(subreddit.getSearch(params)).hasSize(25);
  }
  
  // Subreddits
  
  @Test
  public void testPostCreate() throws FailedRequestException {
    SubredditSettingsValueObject settings = subreddit.getEdit();
    SubredditSettingsValueObject response = subreddit.postCreate(settings);
  
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testPostConfigure() throws FailedRequestException {
    SubredditSettingsValueObject settings = subreddit.getEdit();
    SubredditSettingsValueObject response = subreddit.postConfigure(settings);
  
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testPostSubscribe() throws FailedRequestException {
    subreddit.postSubscribe();
  }
  
  @Test
  public void testPostUnsubscribe() throws FailedRequestException {
    subreddit.postUnsubscribe();
  }
  
  @Test
  public void testGetPostRequirements() throws FailedRequestException {
    assertThat(subreddit.getPostRequirements()).isNotEmpty();
  }
  
  @Test
  public void testGetBanned() throws FailedRequestException {
    assertThat(subreddit.getBanned()).isEmpty();
  }
  
  @Test
  public void testGetContributors() throws FailedRequestException {
    assertThat(subreddit.getContributors()).isNotEmpty();
  
    UserValueObject user = subreddit.getContributors().findFirst().orElseThrow();
    assertThat(user.getName()).isEqualTo("Name");
  }
  
  @Test
  public void testGetModerators() throws FailedRequestException {
    assertThat(subreddit.getModerators()).hasSize(1);
  
    UserValueObject user = subreddit.getModerators().findFirst().orElseThrow();
    assertThat(user.getName()).isEqualTo("User");
  }
  
  @Test
  public void testGetMuted() throws FailedRequestException {
    assertThat(subreddit.getMuted()).isEmpty();
  }
  
  @Test
  public void testGetWikiBanned() throws FailedRequestException {
    assertThat(subreddit.getWikiBanned()).isEmpty();
  }
  
  @Test
  public void testGetWikiContributors() throws FailedRequestException {
    assertThat(subreddit.getWikiContributors()).isEmpty();
  }
  
  @Test
  public void testPostDeleteBanner() throws FailedRequestException {
    assertThat(subreddit.postDeleteBanner()).isNotEmpty();
  }
  
  @Test
  public void testPostDeleteHeader() throws FailedRequestException {
    assertThat(subreddit.postDeleteHeader()).isNotEmpty();
  }
  
  @Test
  public void testPostDeleteIcon() throws FailedRequestException {
    assertThat(subreddit.postDeleteIcon()).isNotEmpty();
  }
  
  @Test
  public void testPostDeleteImage() throws FailedRequestException {
    assertThat(subreddit.postDeleteImage("image")).isNotEmpty();
  }
  
  @Test
  public void testGetSubmitText() throws FailedRequestException {
    assertThat(subreddit.getSubmitText()).contains("Get faster, better responses by including more information");
  }
  
  @Test
  public void testPostSubredditStylesheet() throws FailedRequestException, IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream("css/Simple.css");
    Objects.requireNonNull(is);
    String css = new String(is.readAllBytes(), StandardCharsets.UTF_8);
    
    Parameter[] params = new Parameter[]{
          new Parameter("api_type", "json"),
          new Parameter("op", "save"),
          new Parameter("reason", "test"),
          new Parameter("stylesheet_contents", css)
    };
    
    assertThat(subreddit.postSubredditStylesheet(params)).isNotEmpty();
  }
  
  @Test
  public void testPostUploadImage() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Image.png");
    Objects.requireNonNull(url);
    BufferedImage image = ImageIO.read(url);
    
    assertThat(subreddit.postUploadImage(image, "image")).isNotEmpty();
  }
  
  @Test
  public void testPostUploadHeader() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Header.png");
    Objects.requireNonNull(url);
    BufferedImage header = ImageIO.read(url);
  
    assertThat(subreddit.postUploadHeader(header)).isNotEmpty();
  }
  
  @Test
  public void testPostUploadIcon() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Icon.png");
    Objects.requireNonNull(url);
    BufferedImage icon = ImageIO.read(url);
  
    assertThat(subreddit.postUploadIcon(icon)).isNotEmpty();
  }
  
  @Test
  public void testPostUploadBanner() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Banner.png");
    Objects.requireNonNull(url);
    BufferedImage banner = ImageIO.read(url);
  
    assertThat(subreddit.postUploadBanner(banner)).isNotEmpty();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    SubredditValueObject response = subreddit.getAbout();
    assertThat(response.getDisplayName()).isEqualToIgnoringCase("RedditDev");
  }
  
  @Test
  public void testGetEdit() throws FailedRequestException {
    SubredditSettingsValueObject response = subreddit.getEdit();
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testGetRules() throws FailedRequestException {
    RulesValueObject response = subreddit.getRules();
    assertThat(response.getRules()).isEmpty();
    assertThat(response.getSiteRules()).hasSize(3);
    assertThat(response.getSiteRulesFlow()).hasSize(4);
  }
  
  @Test
  public void testGetTraffic() throws FailedRequestException {
    assertThat(subreddit.getTraffic()).isNotEmpty();
  }
  
  @Test
  public void testGetSticky() throws FailedRequestException {
    LinkValueObject response = subreddit.getSticky(1);
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
}
