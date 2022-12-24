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

package zav.jrc.api.endpoint;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * REST endpoint for the {@code Widgets} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_widgets">here</a>
 */
@SuppressWarnings("unused")
@NonNullByDefault
public final class Widgets {
  /**
   * Add and return a widget to the specified subreddit.<br>
   * Accepts a JSON payload representing the widget data to be saved. Valid payloads differ in shape
   * based on the "kind" attribute passed on the root object, which must be a valid widget kind.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | json                  | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "linkUrl": A valid URL (optional),                            |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`image`),                                         |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "configuration": {                                                |
   * |                       |     "numEvents": an integer between 1 and 50 (default: 10),         |
   * |                       |     "showDate": boolean value,,                                     |
   * |                       |     "showDescription": boolean value,                               |
   * |                       |     "showLocation": boolean value,                                  |
   * |                       |     "showTime": boolean value,                                      |
   * |                       |     "showTitle": boolean value,                                     |
   * |                       |   },                                                                |
   * |                       |   "googleCalendarId": a valid email address,                        |
   * |                       |   "kind": one of (`calendar`),                                      |
   * |                       |   "requiresSync": boolean value,                                    |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "kind": one of (`textarea`),                                      |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL,                                           |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "children": [                                                 |
   * |                       |         {                                                           |
   * |                       |           "text": a string no longer than 20 characters,            |
   * |                       |           "url": a valid URL,                                       |
   * |                       |         },                                                          |
   * |                       |         ...                                                         |
   * |                       |       ],                                                            |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`menu`),                                          |
   * |                       |   "showWiki": boolean value,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "buttons": [                                                      |
   * |                       |     {                                                               |
   * |                       |       "color": a 6-digit rgb hex color, e.g. `#AABBCC`,             |
   * |                       |       "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |       "hoverState": {                                               |
   * |                       |        "color": a 6-digit rgb hex color, e.g. `#AABBCC`,            |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |      OR                                                             |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "kind": one of (`text`),                                      |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |        "url": a valid URL,                                          |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "hoverState": {                                               |
   * |                       |         "color": a 6-digit rgb hex color, e.g. `#AABBCC`,           |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |       OR                                                            |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "imageUrl": a valid URL of a reddit-hosted image,             |
   * |                       |       "kind": one of (`image`),                                     |
   * |                       |       "linkUrl": a valid URL,                                       |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "description": raw markdown text,                                 |
   * |                       |   "kind": one of (`button`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     subreddit name,                                                 |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`community-list`),                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "css": a string no longer than 100000 characters,                 |
   * |                       |   "height": an integer between 50 and 500,                          |
   * |                       |   "imageData": [                                                    |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "name": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`custom`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "display": one of (`cloud`, `list`),                              |
   * |                       |   "kind": one of (`post-flair`),                                    |
   * |                       |   "order": [                                                        |
   * |                       |     a flair template ID,                                            |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIDGET =
        new Endpoint("r", "{subreddit}", "api", "widget");
  /**
   * Add and return a widget to the specified subreddit.<br>
   * Accepts a JSON payload representing the widget data to be saved. Valid payloads differ in shape
   * based on the "kind" attribute passed on the root object, which must be a valid widget kind.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | json                  | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "linkUrl": A valid URL (optional),                            |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`image`),                                         |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "configuration": {                                                |
   * |                       |     "numEvents": an integer between 1 and 50 (default: 10),         |
   * |                       |     "showDate": boolean value,,                                     |
   * |                       |     "showDescription": boolean value,                               |
   * |                       |     "showLocation": boolean value,                                  |
   * |                       |     "showTime": boolean value,                                      |
   * |                       |     "showTitle": boolean value,                                     |
   * |                       |   },                                                                |
   * |                       |   "googleCalendarId": a valid email address,                        |
   * |                       |   "kind": one of (`calendar`),                                      |
   * |                       |   "requiresSync": boolean value,                                    |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "kind": one of (`textarea`),                                      |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL,                                           |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "children": [                                                 |
   * |                       |         {                                                           |
   * |                       |           "text": a string no longer than 20 characters,            |
   * |                       |           "url": a valid URL,                                       |
   * |                       |         },                                                          |
   * |                       |         ...                                                         |
   * |                       |       ],                                                            |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`menu`),                                          |
   * |                       |   "showWiki": boolean value,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "buttons": [                                                      |
   * |                       |     {                                                               |
   * |                       |       "color": a 6-digit rgb hex color, e.g. `#AABBCC`,             |
   * |                       |       "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |       "hoverState": {                                               |
   * |                       |        "color": a 6-digit rgb hex color, e.g. `#AABBCC`,            |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |      OR                                                             |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "kind": one of (`text`),                                      |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |        "url": a valid URL,                                          |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "hoverState": {                                               |
   * |                       |         "color": a 6-digit rgb hex color, e.g. `#AABBCC`,           |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |       OR                                                            |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "imageUrl": a valid URL of a reddit-hosted image,             |
   * |                       |       "kind": one of (`image`),                                     |
   * |                       |       "linkUrl": a valid URL,                                       |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "description": raw markdown text,                                 |
   * |                       |   "kind": one of (`button`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     subreddit name,                                                 |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`community-list`),                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "css": a string no longer than 100000 characters,                 |
   * |                       |   "height": an integer between 50 and 500,                          |
   * |                       |   "imageData": [                                                    |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "name": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`custom`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "display": one of (`cloud`, `list`),                              |
   * |                       |   "kind": one of (`post-flair`),                                    |
   * |                       |   "order": [                                                        |
   * |                       |     a flair template ID,                                            |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_WIDGET =
        new Endpoint("api", "widget");
  /**
   * Delete a widget from the specified subreddit (if it exists).
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | widget_id             | a valid widget id                                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint DELETE_R_SUBREDDIT_API_WIDGET_WIDGET_ID =
        new Endpoint("r", "{subreddit}", "api", "widget", "{widget_id}");
  /**
   * Delete a widget from the specified subreddit (if it exists).
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | widget_id             | a valid widget id                                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint DELETE_API_WIDGET_WIDGET_ID =
        new Endpoint("api", "widget", "{widget_id}");
  /**
   * Update and return the data of a widget.<br>
   * Accepts a JSON payload representing the widget data to be saved. Valid payloads differ in shape
   * based on the "kind" attribute passed on the root object, which must be a valid widget kind.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | json                  | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "linkUrl": A valid URL (optional),                            |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`image`),                                         |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "configuration": {                                                |
   * |                       |     "numEvents": an integer between 1 and 50 (default: 10),         |
   * |                       |     "showDate": boolean value,,                                     |
   * |                       |     "showDescription": boolean value,                               |
   * |                       |     "showLocation": boolean value,                                  |
   * |                       |     "showTime": boolean value,                                      |
   * |                       |     "showTitle": boolean value,                                     |
   * |                       |   },                                                                |
   * |                       |   "googleCalendarId": a valid email address,                        |
   * |                       |   "kind": one of (`calendar`),                                      |
   * |                       |   "requiresSync": boolean value,                                    |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "kind": one of (`textarea`),                                      |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "display": one of (`full`, `compact`),                            |
   * |                       |   "kind": one of (`subreddit-rules`),                               |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL,                                           |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "children": [                                                 |
   * |                       |         {                                                           |
   * |                       |           "text": a string no longer than 20 characters,            |
   * |                       |           "url": a valid URL,                                       |
   * |                       |         },                                                          |
   * |                       |         ...                                                         |
   * |                       |       ],                                                            |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`menu`),                                          |
   * |                       |   "showWiki": boolean value,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "buttons": [                                                      |
   * |                       |     {                                                               |
   * |                       |       "color": a 6-digit rgb hex color, e.g. `#AABBCC`,             |
   * |                       |       "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |       "hoverState": {                                               |
   * |                       |        "color": a 6-digit rgb hex color, e.g. `#AABBCC`,            |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |      OR                                                             |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "kind": one of (`text`),                                      |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |        "url": a valid URL,                                          |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "hoverState": {                                               |
   * |                       |         "color": a 6-digit rgb hex color, e.g. `#AABBCC`,           |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |       OR                                                            |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "imageUrl": a valid URL of a reddit-hosted image,             |
   * |                       |       "kind": one of (`image`),                                     |
   * |                       |       "linkUrl": a valid URL,                                       |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "description": raw markdown text,                                 |
   * |                       |   "kind": one of (`button`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "currentlyViewingText": a string no longer than 30 characters,    |
   * |                       |   "kind": one of (`id-card`),                                       |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "subscribersText": a string no longer than 30 characters,         |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     subreddit name,                                                 |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`community-list`),                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "css": a string no longer than 100000 characters,                 |
   * |                       |   "height": an integer between 50 and 500,                          |
   * |                       |   "imageData": [                                                    |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "name": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`custom`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "display": one of (`cloud`, `list`),                              |
   * |                       |   "kind": one of (`post-flair`),                                    |
   * |                       |   "order": [                                                        |
   * |                       |     a flair template ID,                                            |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "kind": one of (`moderators`),                                    |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * | widget_id             | a valid widget id                                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PUT_R_SUBREDDIT_API_WIDGET_WIDGET_ID =
        new Endpoint("r", "{subreddit}", "api", "widget", "{widget_id}");
  /**
   * Update and return the data of a widget.<br>
   * Accepts a JSON payload representing the widget data to be saved. Valid payloads differ in shape
   * based on the "kind" attribute passed on the root object, which must be a valid widget kind.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | json                  | json data:                                                          |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "linkUrl": A valid URL (optional),                            |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`image`),                                         |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "configuration": {                                                |
   * |                       |     "numEvents": an integer between 1 and 50 (default: 10),         |
   * |                       |     "showDate": boolean value,,                                     |
   * |                       |     "showDescription": boolean value,                               |
   * |                       |     "showLocation": boolean value,                                  |
   * |                       |     "showTime": boolean value,                                      |
   * |                       |     "showTitle": boolean value,                                     |
   * |                       |   },                                                                |
   * |                       |   "googleCalendarId": a valid email address,                        |
   * |                       |   "kind": one of (`calendar`),                                      |
   * |                       |   "requiresSync": boolean value,                                    |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   }                                                                 |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "kind": one of (`textarea`),                                      |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "display": one of (`full`, `compact`),                            |
   * |                       |   "kind": one of (`subreddit-rules`),                               |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     {                                                               |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL,                                           |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "children": [                                                 |
   * |                       |         {                                                           |
   * |                       |           "text": a string no longer than 20 characters,            |
   * |                       |           "url": a valid URL,                                       |
   * |                       |         },                                                          |
   * |                       |         ...                                                         |
   * |                       |       ],                                                            |
   * |                       |       "text": a string no longer than 20 characters,                |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`menu`),                                          |
   * |                       |   "showWiki": boolean value,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "buttons": [                                                      |
   * |                       |     {                                                               |
   * |                       |       "color": a 6-digit rgb hex color, e.g. `#AABBCC`,             |
   * |                       |       "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |       "hoverState": {                                               |
   * |                       |        "color": a 6-digit rgb hex color, e.g. `#AABBCC`,            |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |      OR                                                             |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "kind": one of (`text`),                                      |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |        "url": a valid URL,                                          |
   * |                       |     }                                                               |
   * |                       |                                                                     |
   * |                       |     OR                                                              |
   * |                       |                                                                     |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "hoverState": {                                               |
   * |                       |         "color": a 6-digit rgb hex color, e.g. `#AABBCC`,           |
   * |                       |         "fillColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |         "kind": one of (`text`),                                    |
   * |                       |         "text": a string no longer than 30 characters,              |
   * |                       |         "textColor": a 6-digit rgb hex color, e.g. `#AABBCC`,       |
   * |                       |       }                                                             |
   * |                       |                                                                     |
   * |                       |       OR                                                            |
   * |                       |                                                                     |
   * |                       |       {                                                             |
   * |                       |         "height": an integer,                                       |
   * |                       |         "imageUrl": a valid URL of a reddit-hosted image,           |
   * |                       |         "kind": one of (`image`),                                   |
   * |                       |         "width": an integer,                                        |
   * |                       |       },                                                            |
   * |                       |       "imageUrl": a valid URL of a reddit-hosted image,             |
   * |                       |       "kind": one of (`image`),                                     |
   * |                       |       "linkUrl": a valid URL,                                       |
   * |                       |       "text": a string no longer than 30 characters,                |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "description": raw markdown text,                                 |
   * |                       |   "kind": one of (`button`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "currentlyViewingText": a string no longer than 30 characters,    |
   * |                       |   "kind": one of (`id-card`),                                       |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "subscribersText": a string no longer than 30 characters,         |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "data": [                                                         |
   * |                       |     subreddit name,                                                 |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`community-list`),                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "css": a string no longer than 100000 characters,                 |
   * |                       |   "height": an integer between 50 and 500,                          |
   * |                       |   "imageData": [                                                    |
   * |                       |     {                                                               |
   * |                       |       "height": an integer,                                         |
   * |                       |       "name": a string no longer than 20 characters,                |
   * |                       |       "url": a valid URL of a reddit-hosted image,                  |
   * |                       |       "width": an integer,                                          |
   * |                       |     },                                                              |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "kind": one of (`custom`),                                        |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       |   "text": raw markdown text,                                        |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "display": one of (`cloud`, `list`),                              |
   * |                       |   "kind": one of (`post-flair`),                                    |
   * |                       |   "order": [                                                        |
   * |                       |     a flair template ID,                                            |
   * |                       |     ...                                                             |
   * |                       |   ],                                                                |
   * |                       |   "shortName": a string no longer than 30 characters,               |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * |                       |                                                                     |
   * |                       | OR                                                                  |
   * |                       |                                                                     |
   * |                       | {                                                                   |
   * |                       |   "kind": one of (`moderators`),                                    |
   * |                       |   "styles": {                                                       |
   * |                       |     "backgroundColor": a 6-digit rgb hex color, e.g. `#AABBCC`,     |
   * |                       |     "headerColor": a 6-digit rgb hex color, e.g. `#AABBCC`,         |
   * |                       |   },                                                                |
   * |                       | }                                                                   |
   * | widget_id             | a valid widget id                                                   |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PUT_API_WIDGET_WIDGET_ID =
        new Endpoint("api", "widget", "{widget_id}");
  /**
   * Acquire and return an upload lease to s3 temp bucket.<br>
   * The return value of this function is a json object containing credentials for uploading assets
   * to S3 bucket, S3 url for upload request and the key to use for uploading. Using this lease the
   * client will upload the emoji image to S3 temp bucket (included as part of the S3 URL).<br>
   * This lease is used by S3 to verify that the upload is authorized.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | filepath              | name and extension of the image file e.g. image1.png                |
   * | mimetype              | mime type of the image e.g. image/png                               |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_R_SUBREDDIT_API_WIDGET_IMAGE_UPLOAD_S3 =
        new Endpoint("r", "{subreddit}", "api", "widget_image_upload_s3");
  /**
   * Acquire and return an upload lease to s3 temp bucket.<br>
   * The return value of this function is a json object containing credentials for uploading assets
   * to S3 bucket, S3 url for upload request and the key to use for uploading. Using this lease the
   * client will upload the emoji image to S3 temp bucket (included as part of the S3 URL).<br>
   * This lease is used by S3 to verify that the upload is authorized.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | filepath              | name and extension of the image file e.g. image1.png                |
   * | mimetype              | mime type of the image e.g. image/png                               |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint POST_API_WIDGET_IMAGE_UPLOAD_S3 =
        new Endpoint("api", "widget_image_upload_s3");
  /**
   * Update the order of widget_ids in the specified subreddit.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | json                  | json data:                                                          |
   * |                       | [                                                                   |
   * |                       |   a string,                                                         |
   * |                       |   ...                                                               |
   * |                       | ]                                                                   |
   * | section               | one of (sidebar)                                                    |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PATCH_R_SUBREDDIT_API_WIDGET_ORDER_SECTION =
        new Endpoint("r", "{subreddit}", "api", "widget_order", "{section}");
  /**
   * Update the order of widget_ids in the specified subreddit.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | json                  | json data:                                                          |
   * |                       | [                                                                   |
   * |                       |   a string,                                                         |
   * |                       |   ...                                                               |
   * |                       | ]                                                                   |
   * | section               | one of (sidebar)                                                    |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint PATCH_API_WIDGET_ORDER_SECTION =
        new Endpoint("api", "widget_order", "{section}");
  /**
   * Return all widgets for the given subreddit.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | progressive_images    | boolean value                                                      |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_R_SUBREDDIT_API_WIDGETS =
        new Endpoint("r", "{subreddit}", "api", "widgets");
  /**
   * Return all widgets for the given subreddit.
   * <pre>
   * +-----------------------+---------------------------------------------------------------------+
   * | Parameter             | Description                                                         |
   * +-----------------------+---------------------------------------------------------------------+
   * | progressive_images    | boolean value                                                      |
   * +-----------------------+---------------------------------------------------------------------+
   * </pre>
   */
  public static final Endpoint GET_API_WIDGETS =
        new Endpoint("api", "widgets");
}
