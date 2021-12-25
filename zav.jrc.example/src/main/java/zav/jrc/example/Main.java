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
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkValueObject;
import zav.jrc.client.guice.UserlessModule;
import zav.jrc.api.Subreddit;
import zav.jrc.api.guice.SubredditFactory;
import zav.jrc.api.guice.ApiModule;

/**
 * This example illustrates how to use Guice and the views to retrieve a stream of latest links from
 * the {@link Subreddit} {@code RedditDev}.<br>
 * Note that for this program to run, you need to specify your credentials in the
 * {@code Credentials.json} and {@code UserAgent.json} in the {@code resources} directory.
 */
public class Main {
  protected static Injector GUICE;
  protected static Client CLIENT;
  
  /**
   * The entry point for the program.
   *
   * @param args An array of command-line arguments.
   * @throws FailedRequestException If one of the API requests was rejected.
   */
  public static void main(String[] args) throws FailedRequestException {
    GUICE = Guice.createInjector(new UserlessModule(), new ApiModule());
    CLIENT = GUICE.getInstance(Client.class);
    CLIENT.login(Duration.TEMPORARY);
    
    SubredditFactory factory = GUICE.getInstance(SubredditFactory.class);
    Subreddit view = factory.create("RedditDev");
    Stream<LinkValueObject> links = view.getNew();
    
    links.forEach(link -> System.out.println(link.getTitle()));
    
    CLIENT.logout();
  }
}
