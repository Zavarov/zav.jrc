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
import zav.jrc.databind.SubredditSettings;
import zav.jrc.view.guice.SubredditViewFactory;
import zav.jrc.view.internal.AbstractTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SubredditViewTest extends AbstractTest {
  SubredditView view;
  
  @BeforeEach
  public void setUp() {
    SubredditViewFactory factory = GUICE.getInstance(SubredditViewFactory.class);
    view = factory.create("Subreddit");
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
    view.getSearch(new Parameter("q", "api"), new Parameter("restrict_sr", true));
  }
  
  // Subreddits
  
  @Test
  public void testPostCreate() throws FailedRequestException {
    SubredditSettings settings = view.getEdit();
    
    view.postCreate(settings);
  }
  
  @Test
  public void testPostConfigure() throws FailedRequestException {
    SubredditSettings settings = view.getEdit();
    view.postConfigure(settings);
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
    view.getPostRequirements();
  }
  
  @Test
  public void testGetBanned() throws FailedRequestException {
    view.getBanned();
  }
  
  @Test
  public void testGetContributors() throws FailedRequestException {
    view.getContributors();
  }
  
  @Test
  public void testGetModerators() throws FailedRequestException {
    view.getModerators();
  }
  
  @Test
  public void testGetMuted() throws FailedRequestException {
    view.getMuted();
  }
  
  @Test
  public void testGetWikiBanned() throws FailedRequestException {
    view.getWikiBanned();
  }
  
  @Test
  public void testGetWikiContributors() throws FailedRequestException {
    view.getWikiContributors();
  }
  
  @Test
  public void testPostDeleteBanner() throws FailedRequestException {
    view.postDeleteBanner();
  }
  
  @Test
  public void testPostDeleteHeader() throws FailedRequestException {
    view.postDeleteHeader();
  }
  
  @Test
  public void testPostDeleteIcon() throws FailedRequestException {
    view.postDeleteIcon();
  }
  
  @Test
  public void testPostDeleteImage() throws FailedRequestException {
    view.postDeleteImage("image");
  }
  
  @Test
  public void testGetSubmitText() throws FailedRequestException {
    view.getSubmitText();
  }
  
  @Test
  public void testPostSubredditStylesheet() throws FailedRequestException, IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream("css/Simple.css");
    Objects.requireNonNull(is);
    String css = new String(is.readAllBytes(), StandardCharsets.UTF_8);
    
    view.postSubredditStylesheet(new Parameter("api_type", "json"), new Parameter("op", "save"), new Parameter("reason", "test"), new Parameter("stylesheet_contents", css));
  }
  
  @Test
  public void testPostUploadImage() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Image.png");
    Objects.requireNonNull(url);
    BufferedImage image = ImageIO.read(url);
    
    view.postUploadImage(image, "image");
  }
  
  @Test
  public void testPostUploadHeader() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Header.png");
    Objects.requireNonNull(url);
    BufferedImage header = ImageIO.read(url);
  
    view.postUploadHeader(header);
  }
  
  @Test
  public void testPostUploadIcon() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Icon.png");
    Objects.requireNonNull(url);
    BufferedImage icon = ImageIO.read(url);
  
    view.postUploadIcon(icon);
  }
  
  @Test
  public void testPostUploadBanner() throws FailedRequestException, IOException {
    URL url = getClass().getResource("/images/Banner.png");
    Objects.requireNonNull(url);
    BufferedImage banner = ImageIO.read(url);
  
    view.postUploadBanner(banner);
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    view.getAbout();
  }
  
  @Test
  public void testGetEdit() throws FailedRequestException {
    view.getEdit();
  }
  
  @Test
  public void testGetRules() throws FailedRequestException {
    view.getRules();
  }
  
  @Test
  public void testGetTraffic() throws FailedRequestException {
    view.getTraffic();
  }
  
  @Test
  public void testGetSticky() throws FailedRequestException {
    view.getSticky(1);
  }
}
