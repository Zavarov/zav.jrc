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

package zav.jrc.api.endpoint;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * REST endpoint for the {@code Collections} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_collections">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public final class Collections {
  /**
   * Add a post to a collection.
   * <pre>
   *   | Parameter             | Description               |
   *   | --------------------- | ------------------------- |
   *   | collection_id         | the UUID of a collection  |
   *   | link_fullname         | a fullname of a link      |
   *   | uh / X-Modhash header | a modhash                 |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_ADD_POST_TO_COLLECTION =
        new Endpoint("api", "v1", "collections", "add_post_to_collection");
  /**
   * Fetch a collection including all the links.
   * <pre>
   *   | Parameter             | Description               |
   *   | --------------------- | ------------------------- |
   *   | collection_id         | the UUID of a collection  |
   *   | include_links         | boolean value             |
   * </pre>
   */
  public static final Endpoint GET_API_V1_COLLECTIONS_COLLECTION =
        new Endpoint("api", "v1", "collections", "collection");
  /**
   * Create a collection.
   * <pre>
   *   | Parameter             | Description                                         |
   *   | --------------------- | --------------------------------------------------- |
   *   | description           | a string no longer than 500 characters              |
   *   | display_layout        | one of (TIMELINE, GALLERY)                          |
   *   | sr_fullname           | a fullname of a subreddit                           |
   *   | title                 | title of the submission. up to 300 characters long  |
   *   | uh / X-Modhash header | a modhash                                           |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_CREATE_COLLECTION =
        new Endpoint("api", "v1", "collections", "create_collection");
  /**
   * Delete a collection.
   * <pre>
   *   | Parameter             | Description               |
   *   | --------------------- | ------------------------- |
   *   | collection_id         | the UUID of a collection  |
   *   | link_fullname         | a fullname of a link      |
   *   | uh / X-Modhash header | a modhash                 |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_DELETE_COLLECTION =
        new Endpoint("api", "v1", "collections", "delete_collection");
  /**
   * Follow or unfollow a collection.<br>
   * To follow, {@code follow} should be {@code true}. To unfollow, {@code follow} should be
   * {@code false}. The user must have access to the subreddit to be able to follow a collection
   * within it.
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_FOLLOW_COLLECTION =
        new Endpoint("api", "v1", "collections", "follow_collection");
  /**
   * Remove a post from a collection.
   * <pre>
   *   | Parameter             | Description               |
   *   | --------------------- | ------------------------- |
   *   | collection_id         | the UUID of a collection  |
   *   | link_fullname         | a fullname of a link      |
   *   | uh / X-Modhash header | a modhash                 |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_REMOVE_POST_IN_COLLECTION =
        new Endpoint("api", "v1", "collections", "remove_post_in_collection");
  /**
   * Reorder posts in a collection.
   * <pre>
   *   | Parameter             | Description                          |
   *   | --------------------- | ------------------------------------ |
   *   | collection_id         | the UUID of a collection             |
   *   | link_ids              | the list of comma separated link_ids |
   *   |                       | in the order to set them in          |
   *   | uh / X-Modhash header | a modhash                            |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_REORDER_COLLECTION =
        new Endpoint("api", "v1", "collections", "reorder_collection");
  /**
   * Fetch collections for the subreddit.
   * <pre>
   *   | Parameter             | Description                                         |
   *   | --------------------- | --------------------------------------------------- |
   *   | sr_fullname           | a fullname of a subreddit                           |
   * </pre>
   */
  public static final Endpoint GET_API_V1_COLLECTIONS_SUBREDDIT_COLLECTIONS =
        new Endpoint("api", "v1", "collections", "subreddit_collections");
  /**
   * Update a collection's description.
   * <pre>
   *   | Parameter             | Description                            |
   *   | --------------------- | -------------------------------------- |
   *   | collection_id         | the UUID of a collection               |
   *   | description           | a string no longer than 500 characters |
   *   | uh / X-Modhash header | a modhash                              |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_UPDATE_COLLECTION_DESCRIPTION =
        new Endpoint("api", "v1", "collections", "update_collection_description");
  /**
   * Update a collection's display layout.
   * <pre>
   *   | Parameter             | Description                |
   *   | --------------------- | -------------------------- |
   *   | collection_id         | the UUID of a collection   |
   *   | display_layout        | one of (TIMELINE, GALLERY) |
   *   | uh / X-Modhash header | a modhash                  |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_UPDATE_COLLECTION_DISPLAY_LAYOUT =
        new Endpoint("api", "v1", "collections", "update_collection_display_layout");
  /**
   * Update a collection's title.
   * <pre>
   *   | Parameter             | Description                                         |
   *   | --------------------- | --------------------------------------------------- |
   *   | collection_id         | the UUID of a collection                            |
   *   | title                 | title of the submission. up to 300 characters long  |
   *   | uh / X-Modhash header | a modhash                                           |
   * </pre>
   */
  public static final Endpoint POST_API_V1_COLLECTIONS_UPDATE_COLLECTION_TITLE =
        new Endpoint("api", "v1", "collections", "update_collection_title");
}
