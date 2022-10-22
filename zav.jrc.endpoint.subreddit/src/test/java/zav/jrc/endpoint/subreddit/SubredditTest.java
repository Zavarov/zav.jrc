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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.imageio.ImageIO;
import org.eclipse.jdt.annotation.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.RulesEntity;
import zav.jrc.databind.SubredditEntity;
import zav.jrc.databind.SubredditSettingsEntity;
import zav.jrc.databind.UserEntity;
import zav.jrc.endpoint.test.ClientMock;
import zav.jrc.http.Parameter;

/**
 * Checks whether the calls to the subreddit-related endpoints return the expected response.
 */
public class SubredditTest {
  
  Client client;
  Subreddit subreddit;

  /**
   * Initializes a subreddit view with a mocked Reddit client.
   *
   * @throws FailedRequestException Should never happen.
   */
  @BeforeEach
  public void setUp() throws FailedRequestException {
    client = new ClientMock();
    client.login(Duration.TEMPORARY);
    subreddit = new Subreddit(client, "Subreddit");
  }
  
  @Test
  public void testGetControversial() throws FailedRequestException {
    assertThat(subreddit.getControversial()).hasSize(3);
    
    LinkEntity response = subreddit.getControversial().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetHot() throws FailedRequestException {
    assertThat(subreddit.getHot()).hasSize(25);
  
    LinkEntity response = subreddit.getHot().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetNew() throws FailedRequestException {
    assertThat(subreddit.getNew()).hasSize(25);
  
    LinkEntity response = subreddit.getNew().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetRandom() throws FailedRequestException {
    assertThat(subreddit.getRandom()).hasSize(1);
  
    LinkEntity response = subreddit.getRandom().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("I'm making a reddit bot that deletes spam mesages based on a specific keyword, but for some reason the message doesn't delete.");
  }
  
  @Test
  public void testGetRising() throws FailedRequestException {
    assertThat(subreddit.getRising()).hasSize(25);
  
    LinkEntity response = subreddit.getRising().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Comments automatically get removed immediately after posting");
  }
  
  @Test
  public void testGetTop() throws FailedRequestException {
    assertThat(subreddit.getTop()).hasSize(3);
  
    LinkEntity response = subreddit.getTop().findFirst().orElseThrow();
    assertThat(response.getTitle()).isEqualTo("Getting user description");
  }
  
  // Search
  
  @Test
  public void testSearch() throws FailedRequestException {
    Parameter[] params = new Parameter[]{
        new Parameter("q", "api"),
        new Parameter("restrict_sr", true)
    };
    
    assertThat(subreddit.search(params)).hasSize(25);
  }
  
  // Subreddits
  
  @Test
  public void testCreate() throws FailedRequestException {
    SubredditSettingsEntity settings = subreddit.getEdit();
    SubredditSettingsEntity response = subreddit.create(settings);
  
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testConfigure() throws FailedRequestException {
    SubredditSettingsEntity settings = subreddit.getEdit();
    SubredditSettingsEntity response = subreddit.configure(settings);
  
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testSubscribe() throws FailedRequestException {
    subreddit.subscribe();
  }
  
  @Test
  public void testUnsubscribe() throws FailedRequestException {
    subreddit.unsubscribe();
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
  
    UserEntity user = subreddit.getContributors().findFirst().orElseThrow();
    assertThat(user.getName()).isEqualTo("Name");
  }
  
  @Test
  public void testGetModerators() throws FailedRequestException {
    assertThat(subreddit.getModerators()).hasSize(1);
  
    UserEntity user = subreddit.getModerators().findFirst().orElseThrow();
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
  public void testDeleteBanner() throws FailedRequestException {
    subreddit.deleteBanner();
  }
  
  @Test
  public void testDeleteHeader() throws FailedRequestException {
    subreddit.deleteHeader();
  }
  
  @Test
  public void testDeleteIcon() throws FailedRequestException {
    subreddit.deleteIcon();
  }
  
  @Test
  public void testDeleteImage() throws FailedRequestException {
    subreddit.deleteImage("image");
  }
  
  @Test
  public void testGetSubmitText() throws FailedRequestException {
    assertThat(subreddit.getSubmitText()).contains("Get faster, better responses by including more information");
  }
  
  @Test
  public void testUpdateSubredditStylesheet() throws FailedRequestException, IOException {
    @Nullable InputStream is = getClass().getClassLoader().getResourceAsStream("css/Simple.css");
    Objects.requireNonNull(is);
    String css = new String(is.readAllBytes(), StandardCharsets.UTF_8);
    
    Parameter[] params = new Parameter[]{
        new Parameter("api_type", "json"),
        new Parameter("op", "save"),
        new Parameter("reason", "test"),
        new Parameter("stylesheet_contents", css)
    };
    
    subreddit.updateSubredditStylesheet(params);
  }
  
  @Test
  public void testUploadImage() throws FailedRequestException, IOException {
    @Nullable InputStream is = getClass().getClassLoader().getResourceAsStream("images/Image.png");
    Objects.requireNonNull(is);
    BufferedImage image = ImageIO.read(is);
    
    subreddit.uploadImage(image, "image");
  }
  
  @Test
  public void testUploadHeader() throws FailedRequestException, IOException {
    @Nullable InputStream is = getClass().getClassLoader().getResourceAsStream("images/Header.png");
    Objects.requireNonNull(is);
    BufferedImage header = ImageIO.read(is);
  
    subreddit.uploadHeader(header);
  }
  
  @Test
  public void testUploadIcon() throws FailedRequestException, IOException {
    @Nullable InputStream is = getClass().getClassLoader().getResourceAsStream("images/Icon.png");
    Objects.requireNonNull(is);
    BufferedImage icon = ImageIO.read(is);
  
    subreddit.uploadIcon(icon);
  }
  
  @Test
  public void testUploadBanner() throws FailedRequestException, IOException {
    @Nullable InputStream is = getClass().getClassLoader().getResourceAsStream("images/Banner.png");
    Objects.requireNonNull(is);
    BufferedImage banner = ImageIO.read(is);
  
    subreddit.uploadBanner(banner);
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    SubredditEntity response = subreddit.getAbout();
    assertThat(response.getDisplayName()).isEqualToIgnoringCase("RedditDev");
  }
  
  @Test
  public void testGetEdit() throws FailedRequestException {
    SubredditSettingsEntity response = subreddit.getEdit();
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
  
  @Test
  public void testGetRules() throws FailedRequestException {
    RulesEntity response = subreddit.getRules();
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
    LinkEntity response = subreddit.getSticky(1);
    assertThat(response.getTitle()).isEqualToIgnoringCase("Title");
  }
}
