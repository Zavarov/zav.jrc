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

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.Assisted;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.api.Subreddit;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkDto;
import zav.jrc.listener.SubredditListener;
import zav.jrc.listener.internal.ObserverModule;
import zav.jrc.listener.requester.LinkRequester;

/**
 * The observer implementation for subreddits. Calling {@link #notifyListener(SubredditListener)} or
 * {@link #notifyAllListeners()} will call the respective {@code handle} functions of the selected
 * listeners.
 */
public class SubredditObserver extends AbstractObserver<SubredditListener> {
  private final Subreddit subreddit;
  private final LinkRequester requester;
  @Nullable
  private List<LinkDto> history;
  @Inject
  private Injector injector;
  
  @Inject
  public SubredditObserver(@Assisted Subreddit subreddit) {
    this.requester = new LinkRequester(subreddit);
    this.subreddit = subreddit;
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
  public void notifyListener(SubredditListener listener) throws FailedRequestException {
    try {
      //History may be null when a listener is called explicitly instead of via notifyAllListener.
      history = history == null ? requester.next() : history;

      //Notify the listener starting with the oldest link first
      List<LinkDto> result = new ArrayList<>(history);
      result.sort(Comparator.comparing(LinkDto::getId));
      
      for (LinkDto link : result) {
        Module module = new ObserverModule(subreddit.getAbout(), link);
        injector.createChildInjector(module).injectMembers(listener);
      }
    } catch (LinkRequester.IteratorException e) {
      throw e.getCause();
    }
  }
}
