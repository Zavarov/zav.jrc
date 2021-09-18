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
import zav.jrc.view.internal.AbstractTest;
import zav.jrc.view.guice.SelfAccountViewFactory;

public class SelfAccountViewTest extends AbstractTest {
  
  SelfAccountView view;
  
  @BeforeEach
  public void setUp() {
    SelfAccountViewFactory factory = GUICE.getInstance(SelfAccountViewFactory.class);
    view = factory.create();
  }
  
  @Test
  public void testGetAbout() throws FailedRequestException {
    view.getAbout();
  }
  
  @Test
  public void testGetKarma() throws FailedRequestException {
    view.getKarma();
  }
  
  @Test
  public void testGetPreferences() throws FailedRequestException {
    view.getPreferences();
  }
  
  @Test
  public void testPatchPreferences() throws FailedRequestException {
    view.patchPreferences(view.getPreferences());
  }
  
  @Test
  public void testGetTrophies() throws FailedRequestException {
    view.getTrophies();
  }
  
  @Test
  public void testGetBlocked() throws FailedRequestException {
    view.getBlocked();
  }
  
  @Test
  public void testGetFriends() throws FailedRequestException {
    view.getFriends();
  }
  
  @Test
  public void testGetTrusted() throws FailedRequestException {
    view.getTrusted();
  }
  
  // Subreddit
  
  @Test
  public void testGetMineContributor() throws FailedRequestException {
    view.getMineContributor();
  }
  
  @Test
  public void testGetMineModerator() throws FailedRequestException {
    view.getMineModerator();
  }
  
  @Test
  public void testGetMineSubscriber() throws FailedRequestException {
    view.getMineSubscriber();
  }
}
