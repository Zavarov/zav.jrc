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
 * REST endpoint for the {@code Captcha} section.
 *
 * @see <a href="https://www.reddit.com/dev/api/#section_captcha">here</a>
 * @deprecated ReCAPTCHAs are no longer needed, therefore this endpoint became obsolete.
 */
@Deprecated
@SuppressWarnings("unused")
@NonNullByDefault
public final class Captcha {
  private Captcha() {}

  /**
   * Check whether ReCAPTCHAs are needed for API methods.<br>
   * This endpoint returns a boolean.
   *
   * @deprecated Reddit no longer requires captchas and thus this endpoint returns 403
   */
  @Deprecated
  public static final Endpoint GET_API_NEEDS_CAPTCHA =
        new Endpoint("api", "needs_captcha");
}
