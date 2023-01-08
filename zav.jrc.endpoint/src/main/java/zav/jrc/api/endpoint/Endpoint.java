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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The base class for all Reddit endpoints.<br>
 * An endpoint is an URL to which REST request can be made.
 */
@NonNullByDefault
public final class Endpoint {
  private final List<String> path;

  public Endpoint(String... path) {
    this(Arrays.asList(path));
  }

  public Endpoint(List<String> path) {
    this.path = Collections.unmodifiableList(path);
  }

  /**
   * Calculates the qualified path of an endpoint. An endpoint may contain wildcards, represented by
   * the string "{...}". Each of those wildcard is replaced with one of the arguments.<br>
   * The arguments are substituted in the order they are provided.<br>
   * The number of wildcards has to match the number of provided arguments.
   *
   * @param args A list of arguments with which the wildcards are substituted with.
   * @return A list of qualified parts, constituting the full path of the endpoint.
   */
  public List<String> getPath(Object... args) {
    String substitution = "\\{\\w*}";
    List<String> result = new ArrayList<>();
    int i = 0;

    for (String entry : path) {
      if (entry.matches(substitution)) {
        result.add(args[i++].toString());
      } else {
        result.add(entry);
      }
    }

    //All arguments have to be consumed
    assert i == args.length;

    return Collections.unmodifiableList(result);
  }
}
