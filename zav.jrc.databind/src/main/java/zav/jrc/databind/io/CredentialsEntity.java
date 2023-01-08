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

package zav.jrc.databind.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * This class contains the credentials required to authenticate the application.
 */
@NonNullByDefault
public class CredentialsEntity extends CredentialsTOPEntity {
  @Override
  public String toString() {
    String source = getId() + ":" + getSecret();
    return Base64.getEncoder().encodeToString(source.getBytes(StandardCharsets.UTF_8));
  }

  public static CredentialsEntity read(File file) throws IOException {
    return new ObjectMapper().readValue(file, CredentialsEntity.class);
  }
}
