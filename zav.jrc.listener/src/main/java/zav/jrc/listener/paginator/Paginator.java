/*
 * Copyright (c) 2023 Zavarov.
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

package zav.jrc.listener.paginator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.eclipse.jdt.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.client.FailedRequestException;

public abstract class Paginator<T> implements Iterator<List<T>> {
  private static final Logger LOGGER = LoggerFactory.getLogger(Paginator.class);

  private @Nullable T head;

  @Override
  public boolean hasNext() {
    // There is always a "next" page. May be empty, though...
    return true;
  }

  @Override
  public List<T> next() throws IteratorException {
    try {
      return head == null ? init() : request();
    } catch (FailedRequestException e) {
      throw new IteratorException(e);
    }
  }

  protected List<T> init() throws FailedRequestException {
    LOGGER.info("Possible first time this requester is used? Retrieve head...");

    nextPage(null).limit(1).findFirst().ifPresent(newHead -> {
      head = newHead;
      LOGGER.info("Retrieved {} as the new head.", getId(head));

    });

    return Collections.emptyList();
  }

  protected List<T> request() throws FailedRequestException {
    assert head != null;

    List<T> result = new ArrayList<>();

    // All elements have to be newer than the current head
    T after = head;
    // Indicates whether we should request the next page
    boolean hasNext;

    do {
      LOGGER.info("Request next page after {}.", getId(after));

      List<T> currentResult = new ArrayList<>();

      hasNext = request(currentResult, after);

      if (!currentResult.isEmpty()) {
        // The next page should start after the last element of the current page
        after = currentResult.get(currentResult.size() - 1);
        result.addAll(currentResult);
      }

    } while (hasNext);

    if (!result.isEmpty()) {
      // All elements of the next request have to be newer than the new head
      head = result.get(0);
      LOGGER.info("Update 'head' to {}.", getId(head));
    }

    return result;
  }

  protected boolean request(List<T> result, @Nullable T after) throws FailedRequestException {
    LOGGER.info("Requesting page after {}.", getId(after));
    Iterator<T> iterator = nextPage(after).iterator();

    // Iterate over all elements on the current page
    while (iterator.hasNext()) {
      T element = iterator.next();

      // If the current link is lexicographically larger than the head
      // Then that means it was created after the head, i.e. at a later point in time
      if (getId(element).compareTo(getId(head)) > 0) {
        result.add(element);
      } else {
        LOGGER.debug("Last element found. Stop...");
        return false;
      }
    }

    return true;
  }

  protected abstract String getId(T source);

  protected abstract Stream<T> nextPage(@Nullable T after) throws FailedRequestException;
}
