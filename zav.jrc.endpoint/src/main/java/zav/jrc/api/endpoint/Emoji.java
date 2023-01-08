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
 * REST endpoint for the {@code Emoji} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_emoji">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public final class Emoji {
  /**
   * Add an emoji to the DB by posting a message on emoji_upload_q. A job
   * processor that listens on a queue, uses the s3_key provided in the request to
   * locate the image in S3 Temp Bucket and moves it to the PERM bucket. It also
   * adds it to the DB using name as the column and sr_fullname as the key and
   * sends the status on the websocket URL that is provided as part of this
   * response.<br>
   * This endpoint should also be used to update custom subreddit emojis with new
   * images. If only the permissions on an emoji require updating the
   * POST_emoji_permissions endpoint should be requested, instead.
   * 
   * <pre>
   * +---------------------+-----------------------------------------------------------------------+
   * | Parameter           | Description                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * | mod_flair_only      | boolean value                                                         |
   * | name                | Name of the emoji to be created. It can be alphanumeric without any   |
   * |                     | special characters except '-' &amp; '_' and cannot exceed 24          |
   * |                     | characters.                                                           |
   * | post_flair_allowed  | boolean value                                                         |
   * | s3_key              | S3 key of the uploaded image which can be obtained from the S3 url.   |
   * |                     | This is of the form subreddit/hash_value.                             |
   * | user_flair_allowed  | boolean value                                                         |
   * +---------------------+-----------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_V1_SUBREDDIT_EMOJI_JSON = new Endpoint("api", "v1",
      "{subreddit}", "emoji.json");
  /**
   * Delete a Subreddit emoji. Remove the emoji from Cassandra and purge the
   * assets from S3 and the image resizing provider.
   */
  public static final Endpoint DELETE_API_V1_SUBREDDIT_EMOJI_EMOJI_NAME = new Endpoint("api", "v1",
      "{subreddit}", "emoji", "{emoji_name}");
  /**
   * Acquire and return an upload lease to s3 temp bucket. The return value of
   * this function is a json object containing credentials for uploading assets to
   * S3 bucket, S3 url for upload request and the key to use for uploading. Using
   * this lease the client will upload the emoji image to S3 temp bucket (included
   * as part of the S3 URL).<br>
   * This lease is used by S3 to verify that the upload is authorized.
   * 
   * <pre>
   * +---------------------+-----------------------------------------------------------------------+
   * | Parameter           | Description                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * | filepath            | name and extension of the image file e.g. image1.png                  |
   * | mimetype            | mime type of the image e.g. image/png                                 |
   * +---------------------+-----------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_V1_SUBREDDIT_EMOJI_ASSES_UPLOAD_S3_JSON = new Endpoint(
      "api", "v1", "{subreddit}", "emoji_asset_upload_s3.json");
  /**
   * Set custom emoji size.<br>
   * Omitting width or height will disable custom emoji sizing.
   * 
   * <pre>
   * +---------------------+-----------------------------------------------------------------------+
   * | Parameter           | Description                                                           |
   * +---------------------+-----------------------------------------------------------------------+
   * | height              | an integer between 1 and 40 (default: 0)                              |
   * | width               | an integer between 1 and 40 (default: 0)                              |
   * +---------------------+-----------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_V1_SUBREDDIT_EMOJI_CUSTOM_SIZE = new Endpoint("api", "v1",
      "{subreddit}", "emoji_custom_size");
  /**
   * Get all emojis for a SR. The response includes snoomojis as well as emojis
   * for the SR specified in the request.<br>
   * The response has 2 keys: - snoomojis - SR emojis.
   */
  public static final Endpoint GET_API_V1_SUBREDDIT_EMOJIS_ALL = new Endpoint("api", "v1",
      "{subreddit}", "emojis", "all");
}
