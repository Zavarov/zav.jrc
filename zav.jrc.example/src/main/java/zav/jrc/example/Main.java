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

package zav.jrc.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.guice.UserlessClientModule;
import zav.jrc.listener.observable.SubredditObservable;
import zav.jrc.listener.observer.SubredditObserver;

/**
 * Standalone example of the JRC client.<br>
 * This example registers a listener for the subreddit {@code RedditDev} which prints the name
 * of all newly received links..
 */
public class Main {
  protected static Injector GUICE;
  protected static Client CLIENT;
  protected static SubredditObservable OBSERVABLE;
  protected static SubredditObserver OBSERVER;
  
  /**
   * The entry point for the program.
   *
   * @param args An array of command-line arguments.
   * @throws FailedRequestException If one of the API requests was rejected.
   */
  public static void main(String[] args) throws FailedRequestException {
    GUICE = Guice.createInjector(new UserlessClientModule());
    CLIENT = GUICE.getInstance(Client.class);
    OBSERVABLE = GUICE.getInstance(SubredditObservable.class);
    
    CLIENT.login(Duration.TEMPORARY);
    
    OBSERVER = OBSERVABLE.getObserver("RedditDev");
    OBSERVER.addListener((e) -> System.out.println(e.getSource()));
    OBSERVER.notifyAllListeners();
    
    CLIENT.logout();
  }
}
