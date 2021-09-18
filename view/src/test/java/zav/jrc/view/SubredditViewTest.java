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
import zav.jrc.databind.*;
import zav.jrc.view.guice.SubredditViewFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class SubredditViewTest extends AbstractTest {
  SubredditView view;
  
  @BeforeEach
  public void setUp() {
    SubredditViewFactory factory = GUICE.getInstance(SubredditViewFactory.class);
    view = factory.create("Subreddit");
  }
  
  @Test
  public void testGetControversial() throws FailedRequestException {
    assertThat(view.getControversial()).hasSize(3);
    
    Link response = view.getControversial().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetHot() throws FailedRequestException {
    assertThat(view.getHot()).hasSize(25);
  
    Link response = view.getHot().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetNew() throws FailedRequestException {
    assertThat(view.getNew()).hasSize(25);
  
    Link response = view.getNew().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetRandom() throws FailedRequestException {
    assertThat(view.getRandom()).hasSize(1);
  
    Link response = view.getRandom().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("I'm making a reddit bot that deletes spam mesages based on a specific keyword, but for some reason the message doesn't delete.");
  }
  
  @Test
  public void testGetRising() throws FailedRequestException {
    assertThat(view.getRising()).hasSize(25);
  
    Link response = view.getRising().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetTop() throws FailedRequestException {
    assertThat(view.getTop()).hasSize(3);
  
    Link response = view.getTop().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Getting user description");
  }
  
  // Search
  
  @Test
  public void testGetSearch() throws FailedRequestException {
    Parameter[] params = new Parameter[]{
          new Parameter("q", "api"),
          new Parameter("restrict_sr", true)
    };
    
    assertThat(view.getSearch(params)).hasSize(25);
  }
  
  // Subreddits
  
  @Test
  public void testPostCreate() throws FailedRequestException {
    SubredditSettings settings = view.getEdit();
    SubredditSettings response = view.postCreate(settings);
  
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testPostConfigure() throws FailedRequestException {
    SubredditSettings settings = view.getEdit();
    SubredditSettings response = view.postConfigure(settings);
  
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testPostSubscribe() throws FailedRequestException {
    view.postSubscribe();
  }
  
  @Test
  public void testPostUnsubscribe() throws FailedRequestException {
    view.postUnsubscribe();
  }
  
  @Test
  public void testGetPostRequirements() throws FailedRequestException {
    assertThat(view.getPostRequirements()).isNotEmpty();
  }
  
  @Test
  public void testGetBanned() throws FailedRequestException {
    assertThat(view.getBanned()).isEmpty();
  }
  
  @Test
  public void testGetContributors() throws FailedRequestException {
    assertThat(view.getContributors()).isNotEmpty();
  
    User user = view.getContributors().findFirst().orElseThrow();
    assertThat(user.getName()).isEqualTo("Name");
  }
  
  @Test
  public void testGetModerators() throws FailedRequestException {
    assertThat(view.getModerators()).hasSize(1);
    
    User user = view.getModerators().findFirst().orElseThrow();
    assertThat(user.getName()).isEqualTo("User");
  }
  
  @Test
  public void testGetMuted() throws FailedRequestException {
    assertThat(view.getMuted()).isEmpty();
  }
  
  @Test
  public void testGetWikiBanned() throws FailedRequestException {
    assertThat(view.getWikiBanned()).isEmpty();
  }
  
  @Test
  public void testGetWikiContributors() throws FailedRequestException {
    assertThat(view.getWikiContributors()).isEmpty();
  }
  
  @Test
  public void testPostDeleteBanner() throws FailedRequestException {
    assertThat(view.postDeleteBanner()).isNotEmpty();
  }
  
  @Test
  public void testPostDeleteHeader() throws FailedRequestException {
    assertThat(view.postDeleteHeader()).isNotEmpty();
  }
  
  @Test
  public void testPostDeleteIcon() throws FailedRequestException {
    assertThat(view.postDeleteIcon()).isNotEmpty();
  }
  
  @Test
  public void testPostDeleteImage() throws FailedRequestException {
    assertThat(view.postDeleteImage("image")).isNotEmpty();
  }
  
  @Test
  public void testGetSubmitText() throws FailedRequestException {
    assertThat(view.getSubmitText()).contains("Get faster, better responses by including more information");
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
    
    assertThat(view.postSubredditStylesheet(params)).isNotEmpty();
  }
  
  @Test
  public void testPostUploadImage() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Image.png");
    Objects.requireNonNull(url);
    BufferedImage image = ImageIO.read(url);
    
    assertThat(view.postUploadImage(image, "image")).isNotEmpty();
  }
  
  @Test
  public void testPostUploadHeader() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Header.png");
    Objects.requireNonNull(url);
    BufferedImage header = ImageIO.read(url);
  
    assertThat(view.postUploadHeader(header)).isNotEmpty();
  }
  
  @Test
  public void testPostUploadIcon() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Icon.png");
    Objects.requireNonNull(url);
    BufferedImage icon = ImageIO.read(url);
  
    assertThat(view.postUploadIcon(icon)).isNotEmpty();
  }
  
  @Test
  public void testPostUploadBanner() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Banner.png");
    Objects.requireNonNull(url);
    BufferedImage banner = ImageIO.read(url);
  
    assertThat(view.postUploadBanner(banner)).isNotEmpty();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    Subreddit response = view.getAbout();
    assertThat(response.getDisplayName()).isEqualToIgnoringCase("RedditDev");
  }
  
  @Test
  public void testGetEdit() throws FailedRequestException {
    SubredditSettings response = view.getEdit();
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testGetRules() throws FailedRequestException {
    Rules response = view.getRules();
    assertThat(response.getRules()).isEmpty();
    assertThat(response.getSiteRules()).hasSize(3);
    assertThat(response.getSiteRulesFlow()).hasSize(4);
  }
  
  @Test
  public void testGetTraffic() throws FailedRequestException {
    assertThat(view.getTraffic()).isNotEmpty();
  }
  
  @Test
  public void testGetSticky() throws FailedRequestException {
    Link response = view.getSticky(1);
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
}
