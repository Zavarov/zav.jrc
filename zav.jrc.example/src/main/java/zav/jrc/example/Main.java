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

import java.io.File;
import java.io.IOException;
import zav.jrc.client.Client;
import zav.jrc.client.Duration;
import zav.jrc.client.FailedRequestException;
import zav.jrc.client.UserlessClient;
import zav.jrc.databind.io.CredentialsEntity;
import zav.jrc.databind.io.UserAgentEntity;
import zav.jrc.listener.observable.SimpleSubredditObservable;
import zav.jrc.listener.observer.SubredditObserver;

/**
 * Standalone example of the JRC client.<br>
 * This example registers a listener for the subreddit {@code RedditDev} which prints the name
 * of all newly received links..
 */
public class Main {
  private static UserAgentEntity USER_AGENT;
  private static CredentialsEntity CREDENTIALS;
  private static Client CLIENT;
  private static SimpleSubredditObservable OBSERVABLE;
  private static SubredditObserver OBSERVER;
  
  /**
   * The entry point for the program.
   *
   * @param args An array of command-line arguments.
   * @throws FailedRequestException If one of the API requests was rejected.
   * @throws IOException If one of the JSON files couldn't be read.
   */
  public static void main(String[] args) throws FailedRequestException, IOException {
    USER_AGENT = UserAgentEntity.read(new File("UserAgent.json"));
    CREDENTIALS = CredentialsEntity.read(new File("Credentials.json"));
    CLIENT = new UserlessClient(USER_AGENT, CREDENTIALS);
    OBSERVABLE = new SimpleSubredditObservable(CLIENT);
    
    CLIENT.login(Duration.TEMPORARY);
    
    OBSERVER = OBSERVABLE.getObserver("RedditDev");
    OBSERVER.addListener((e) -> System.out.println(e.getSource()));
    OBSERVER.notifyAllListeners();
    
    CLIENT.logout();
  }
}
