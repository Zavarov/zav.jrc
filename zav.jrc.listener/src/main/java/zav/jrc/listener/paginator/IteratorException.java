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

import java.util.Iterator;
import zav.jrc.client.FailedRequestException;

/**
 * This exception is thrown whenever the next sequence of links couldn't be
 * requested from the Reddit API. It wraps the {@link FailedRequestException}
 * around an unchecked exception due to the constraints imposed by
 * {@link Iterator#next()}}.
 */
public final class IteratorException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public IteratorException(FailedRequestException cause) {
    super(cause);
  }

  @Override
  public FailedRequestException getCause() {
    return (FailedRequestException) super.getCause();
  }
}