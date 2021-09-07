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

package zav.jrc;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDateTime;
import okhttp3.Response;
import org.eclipse.jdt.annotation.NonNull;
import zav.jrc.http.RateLimiterException;

/**
 * A rate limiter is used to limit the amount of requests that can be made to the Reddit API with
 * a given interval. Making too many requests at once will trigger a {@link RateLimiterException}
 * and in the worst case, gets the application banned from making any further requests.<p/>
 * API access is managed via windows. Within each window, a fixed amount of requests can be made.
 * Once those have been used up, the application has to wait until the start of the next windows,
 * until further requests can be made.
 */
public class RateLimiter {
  @NonNull
  private static final String USED = "x-ratelimit-used";
  @NonNull
  private static final String REMAINING = "x-ratelimit-remaining";
  @NonNull
  private static final String RESET = "x-ratelimit-reset";

  private LocalDateTime lastResponse;

  private long used = 0;
  private long remaining = 60;
  private long reset = 60;

  public long getUsed() {
    return used;
  }

  public long getRemaining() {
    return remaining;
  }

  public long getReset() {
    return reset;
  }
  
  /**
   * Updates the number of requests that have been made and the number of requests that still can
   * be made within the current window, as well as the time in seconds until the next window starts.
   *
   * @param response The response from the latest REST request.
   */
  public synchronized void update(Response response) {
    lastResponse = LocalDateTime.now();
    String value = response.header(USED);

    if (value != null) {
      used = (long) Double.parseDouble(value);
    }
    
    value = response.header(REMAINING);
    if (value != null) {
      remaining = (long) Double.parseDouble(value);
    }
    
    value = response.header(RESET);
    if (value != null) {
      //In case of fractional seconds, round up
      reset = (long) Math.ceil(Double.parseDouble(value));
    }
  }
  
  /**
   * In case a request can be made within the current window, the method returns immediately.
   * Otherwise the method will block until the start of the next request window.
   *
   * @throws InterruptedException In case the current thread has been interrupted, while waiting
   *     for the start of the next request window.
   */
  public synchronized void acquire() throws InterruptedException {
    //Out of available requests?
    if (remaining <= 0) {
      LocalDateTime now = LocalDateTime.now();
      //Wait until the start of the next period
      if (SECONDS.between(lastResponse, now) < reset) {
        Thread.sleep(reset - SECONDS.between(lastResponse, now));
      }
    }
  }
}
