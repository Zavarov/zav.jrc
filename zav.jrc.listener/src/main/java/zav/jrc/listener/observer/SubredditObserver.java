/*
 * Copyright (c) 2023 Zavarov
 * 
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

package zav.jrc.listener.observer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkEntity;
import zav.jrc.listener.GenericEvent;
import zav.jrc.listener.GenericListener;
import zav.jrc.listener.internal.LinkRequester;

/**
 * The observer implementation for subreddits. Calling
 * {@link #notifyListener(GenericListener)} or {@link #notifyAllListeners()}
 * will call the respective {@link GenericListener#notify(LinkEvent)} methods of
 * all registered listeners.
 */
@NonNullByDefault
public class SubredditObserver extends AbstractObserver<LinkEntity> {
  @Nullable
  private List<LinkEntity> history;
  private final LinkRequester requester;

  public SubredditObserver(Client client, String subreddit) {
    this.requester = new LinkRequester(client, subreddit);
  }

  @Override
  public void notifyAllListeners() throws FailedRequestException {
    try {
      history = requester.next(); // History is computed once for all listeners
      super.notifyAllListeners();
    } catch (LinkRequester.IteratorException e) {
      throw e.getCause();
    } finally {
      history = null; // Clear cache even in case an exception was thrown.
    }
  }

  @Override
  public void notifyListener(GenericListener<LinkEntity> listener) throws FailedRequestException {
    try {
      // History may be null when a listener is called explicitly instead of via
      // notifyAllListener.
      history = history == null ? requester.next() : history;

      // Notify the listener starting with the oldest link first
      List<LinkEntity> result = new ArrayList<>(history);
      result.sort(Comparator.comparing(LinkEntity::getId));
      result.stream().map(GenericEvent::new).forEach(listener::notify);
    } catch (LinkRequester.IteratorException e) {
      throw e.getCause();
    }
  }
}
