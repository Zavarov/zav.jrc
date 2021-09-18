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

package zav.jrc.listener.requester;

import com.google.common.collect.AbstractIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.FailedRequestException;
import zav.jrc.databind.Link;
import zav.jrc.view.SubredditView;

public class LinkRequester extends AbstractIterator<List<? extends Link>> {
  @NonNull
  private static final Logger LOGGER = LogManager.getLogger(LinkRequester.class);
  @NonNull
  private final SubredditView subreddit;
  @Nullable
  private Link head;
  
  public LinkRequester(@NonNull SubredditView subreddit) {
    this.subreddit = subreddit;
  }

  @Override
  protected List<? extends Link> computeNext() throws IteratorException {
    try {
      //LOGGER.info("Computing next links for r/{}.", subreddit.getDisplayName());
      return head == null ? init() : request();
    } catch (FailedRequestException e) {
      throw new IteratorException(e);
    }
  }

  private List<Link> init() throws FailedRequestException {
    LOGGER.info("Possible first time this requester is used? Retrieve head.");
    List<? extends Link> submissions = subreddit.getNew().limit(1).collect(Collectors.toList());

    if (!submissions.isEmpty()) {
      head = submissions.get(0);
      LOGGER.info("Retrieved {} as the new head.", head.getName());
    }

    return Collections.emptyList();
  }

  private List<Link> request() throws FailedRequestException {
    assert head != null;
    LOGGER.info("Requesting links after {}.", head.getName());

    List<Link> result = new ArrayList<>();
    Iterator<Link> iterator = subreddit.getNew().iterator();

    while (iterator.hasNext()) {
      Link link = iterator.next();

      //If the current link is lexicographically larger then the head
      //Then that means it was created after the head, i.e at a later point in time
      if (link.getId().compareTo(head.getId()) > 0) {
        result.add(link);
      } else {
        break;
      }
    }

    if (!result.isEmpty()) {
      //Change head to the newest submission
      head = result.get(0);
      LOGGER.info("Update 'head' to {}.", head.getName());
    }

    return result;
  }

  public static final class IteratorException extends RuntimeException {
    public IteratorException(FailedRequestException cause) {
      super(cause);
    }
    
    @Override
    public FailedRequestException getCause() {
      return (FailedRequestException) super.getCause();
    }
  }
}
