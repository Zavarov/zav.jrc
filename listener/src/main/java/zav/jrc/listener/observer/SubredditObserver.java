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

package zav.jrc.listener.observer;

import com.google.inject.assistedinject.Assisted;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.FailedRequestException;
import zav.jrc.databind.Link;
import zav.jrc.listener.SubredditListener;
import zav.jrc.listener.requester.LinkRequester;
import zav.jrc.view.SubredditView;

public class SubredditObserver extends AbstractObserver<SubredditListener> {
  @NonNull
  private final LinkRequester requester;
  @Nullable
  private List<? extends Link> history;
  
  @Inject
  public SubredditObserver(@Assisted @NonNull SubredditView subreddit) {
    this.requester = new LinkRequester(subreddit);
  }

  @Override
  public void notifyAllListeners() throws FailedRequestException {
    try {
      history = requester.next(); //History is computed once for all listeners
      super.notifyAllListeners();
    } catch (LinkRequester.IteratorException e) {
      throw e.getCause();
    } finally {
      history = null; //Clear cache even in case an exception was thrown.
    }
  }

  @Override
  public void notifyListener(@NonNull SubredditListener listener) throws FailedRequestException {
    try {
      //History may be null when a listener is called explicitly instead of via notifyAllListener.
      history = history == null ? requester.next() : history;
      //Notify the listener starting with the oldest link first
      history.stream().sorted(Comparator.comparing(Link::getId)).forEach(listener::handle);
    } catch (LinkRequester.IteratorException e) {
      throw e.getCause();
    }
  }
}
