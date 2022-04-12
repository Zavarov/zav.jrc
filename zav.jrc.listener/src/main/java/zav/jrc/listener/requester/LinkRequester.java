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

package zav.jrc.listener.requester;

import static zav.jrc.api.Constants.SUBREDDIT;

import com.google.common.collect.AbstractIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.api.Things;
import zav.jrc.api.endpoint.Listings;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkEntity;

/**
 * This class is used to retrieve the latest submissions from a given subreddit.<br>
 * During the first request, the most recent link is used as a head for future requests and thus
 * will always return an empty list.<br>
 * On future requests, the head is compared against all retrieved links and only those that have
 * been submitted after the head are returned. The head is then updated with the most recent link.
 */
public class LinkRequester extends AbstractIterator<List<LinkEntity>> {
  private static final Logger LOGGER = LogManager.getLogger();

  @Inject
  private Client client;
  
  @Inject
  @Named(SUBREDDIT)
  private String subreddit;

  @Nullable
  private LinkEntity head;

  @Override
  protected List<LinkEntity> computeNext() throws IteratorException {
    try {
      LOGGER.info("Computing next links via {}.", subreddit);
      return head == null ? init() : request();
    } catch (FailedRequestException e) {
      throw new IteratorException(e);
    }
  }

  private List<LinkEntity> init() throws FailedRequestException {
    LOGGER.info("Possible first time this requester is used? Retrieve head.");
    List<LinkEntity> submissions = getNew().limit(1).collect(Collectors.toList());

    if (!submissions.isEmpty()) {
      head = submissions.get(0);
      LOGGER.info("Retrieved {} as the new head.", head.getName());
    }

    return Collections.emptyList();
  }

  private List<LinkEntity> request() throws FailedRequestException {
    assert head != null;
    LOGGER.info("Requesting links after {}.", head.getName());

    List<LinkEntity> result = new ArrayList<>();
    Iterator<LinkEntity> iterator = getNew().iterator();

    while (iterator.hasNext()) {
      LinkEntity link = iterator.next();

      //If the current link is lexicographically larger than the head
      //Then that means it was created after the head, i.e. at a later point in time
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
  
  private Stream<LinkEntity> getNew() throws FailedRequestException {
    Request query = client.newRequest()
          .setEndpoint(Listings.GET_R_SUBREDDIT_NEW)
          .setArgs(subreddit)
          .build()
          .get();
  
    return Things.transformListingOfThings(client.send(query), LinkEntity.class);
  }
  
  /**
   * This exception is thrown whenever the next sequence of links couldn't be requested from the
   * Reddit API. It wraps the {@link FailedRequestException} around an unchecked exception to
   * satisfy due to the signature of {@link #computeNext()}.
   */
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
