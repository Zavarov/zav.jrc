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

package zav.jrc.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.stream.Stream;
import zav.jrc.Client;
import zav.jrc.Duration;
import zav.jrc.FailedRequestException;
import zav.jrc.databind.Link;
import zav.jrc.guice.UserlessModule;
import zav.jrc.view.SubredditView;
import zav.jrc.view.guice.SubredditViewFactory;
import zav.jrc.view.guice.ViewModule;

public class Main {
  protected static Injector GUICE;
  protected static Client CLIENT;
  
  public static void main(String[] args) throws FailedRequestException {
    GUICE = Guice.createInjector(new UserlessModule(), new ViewModule());
    CLIENT = GUICE.getInstance(Client.class);
    CLIENT.login(Duration.TEMPORARY);
    
    SubredditViewFactory factory = GUICE.getInstance(SubredditViewFactory.class);
    SubredditView view = factory.create("RedditDev");
    Stream<Link> links = view.getNew();
    
    links.forEach(link -> {
      System.out.println(link.getTitle());
    });
    
    CLIENT.logout();
  }
}
