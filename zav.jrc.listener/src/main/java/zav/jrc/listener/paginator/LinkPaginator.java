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

package zav.jrc.listener.paginator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zav.jrc.api.endpoint.Listings;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkEntity;
import zav.jrc.databind.Things;

/**
 * This class is used to retrieve the latest submissions from a given
 * subreddit.<br>
 * During the first request, the most recent link is used as a head for future
 * requests and thus will always return an empty list.<br>
 * On future requests, the head is compared against all retrieved links and only
 * those that have been submitted after the head are returned. The head is then
 * updated with the most recent link.
 */
@NonNullByDefault
public class LinkPaginator extends Paginator<LinkEntity> {
  private static final Logger LOGGER = LoggerFactory.getLogger(LinkPaginator.class);

  private final Client client;
  private final String subreddit;

  public LinkPaginator(Client client, String subreddit) {
    this.client = client;
    this.subreddit = subreddit;
  }

  @Override
  protected Stream<LinkEntity> nextPage(@Nullable LinkEntity after) throws FailedRequestException {
    Map<Object, Object> params = new HashMap<>();

    if (after != null) {
      LOGGER.info("Request submissions after {}", after.getId());
      params.put("after", after.getId());
    }

    String response = client.newRequest() //
        .withEndpoint(Listings.GET_R_SUBREDDIT_NEW, subreddit) //
        .withParams(params) //
        .get();

    return Things.transformListingOfThings(response, LinkEntity.class);
  }

  @Override
  protected String getId(LinkEntity source) {
    return source.getId();
  }
}
