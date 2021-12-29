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
import zav.jrc.api.Subreddit;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkDto;

/**
 * This class is used to retrieve the latest submissions from a given subreddit.<br>
 * During the first request, the most recent link is used as a head for future requests and thus
 * will always return an empty list.<br>
 * On future requests, the head is compared against all retrieved links and only those that have
 * been submitted after the head are returned. The head is then updated with the most recent link.
 */
@NonNull
public class LinkRequester extends AbstractIterator<List<LinkDto>> {
  @NonNull
  private static final Logger LOGGER = LogManager.getLogger(LinkRequester.class);
  @NonNull
  private final Subreddit subreddit;
  @Nullable
  private LinkDto head;
  
  public LinkRequester(@NonNull Subreddit subreddit) {
    this.subreddit = subreddit;
  }

  @Override
  @NonNull
  protected List<LinkDto> computeNext() throws IteratorException {
    try {
      LOGGER.info("Computing next links via {}.", subreddit);
      return head == null ? init() : request();
    } catch (FailedRequestException e) {
      throw new IteratorException(e);
    }
  }

  @NonNull
  private List<LinkDto> init() throws FailedRequestException {
    LOGGER.info("Possible first time this requester is used? Retrieve head.");
    List<LinkDto> submissions = subreddit.getNew().limit(1).collect(Collectors.toList());

    if (!submissions.isEmpty()) {
      head = submissions.get(0);
      LOGGER.info("Retrieved {} as the new head.", head.getName());
    }

    return Collections.emptyList();
  }

  @NonNull
  private List<LinkDto> request() throws FailedRequestException {
    assert head != null;
    LOGGER.info("Requesting links after {}.", head.getName());

    List<LinkDto> result = new ArrayList<>();
    Iterator<LinkDto> iterator = subreddit.getNew().iterator();

    while (iterator.hasNext()) {
      LinkDto link = iterator.next();

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
  
  /**
   * This exception is thrown whenever the next sequence of links couldn't be requested from the
   * Reddit API. It wraps the {@link FailedRequestException} around an unchecked exception to
   * satisfy due to the signature of {@link #computeNext()}.
   */
  @NonNull
  public static final class IteratorException extends RuntimeException {
    public IteratorException(@NonNull FailedRequestException cause) {
      super(cause);
    }
    
    @Override
    public FailedRequestException getCause() {
      return (FailedRequestException) super.getCause();
    }
  }
}
