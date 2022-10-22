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

package zav.jrc.listener.event;

import org.eclipse.jdt.annotation.NonNullByDefault;
import zav.jrc.databind.LinkEntity;

/**
 * This event if fired whenever a new link has been submitted to a subreddit.
 */
@NonNullByDefault
public class LinkEvent implements Event<LinkEntity> {
  private final LinkEntity source;
  
  public LinkEvent(LinkEntity source) {
    this.source = source;
  }
  
  
  @Override
  public LinkEntity getSource() {
    return source;
  }
}
