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

package zav.jrc.databind.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Token extends TokenTOP {
  private static final Logger LOGGER = LogManager.getLogger(Token.class);
  /**
   * The time when this token was created. It is used in combination with {@link #getExpiresIn()}
   * to determine when the token has expired.
   */
  @JsonIgnore
  private final LocalDateTime creationTime = LocalDateTime.now();
  
  /**
   * Checks if the access token has expired. In order to allow a little bit of tolerance, the access token
   * will be treated as expired, the moment it is less than one minute valid. This minimizes the risk of the token
   * expiring between calling this method and sending the request.
   * @return {@code true}, if the access token is no longer valid.
   */
  @JsonIgnore
  public boolean isExpired() {
    LocalDateTime expirationTime = creationTime.plusSeconds(this.getExpiresIn());
    LOGGER.info("Token expires in {} minute(s)", ChronoUnit.MINUTES.between(LocalDateTime.now(), expirationTime));
    //In order to prevent using an expired token, a fresh one
    //has to be requested a minute before the current one expires
    return expirationTime.minusMinutes(1).isBefore(LocalDateTime.now());
  }
}
