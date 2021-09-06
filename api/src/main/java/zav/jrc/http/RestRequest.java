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

package zav.jrc.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.Endpoint;
import zav.jrc.Parameter;

/**
 * The base class for every REST request.<p/>
 * There are five different type of requests:
 * <table>
 *   <tr><td> POST   </td><td> Create         </td></tr>
 *   <tr><td> GET    </td><td> Read           </td></tr>
 *   <tr><td> PUT    </td><td> Update/Replace </td></tr>
 *   <tr><td> PATCH  </td><td> Update/Modify  </td></tr>
 *   <tr><td> DELETE </td><td> Delete         </td></tr>
 * </table>
 */
@NonNull
public class RestRequest {
  /**
    * The protocol used for OAuth requests.
   */
  @NonNull
  public static final String HTTPS = "https";
  /**
   * The host accepting all OAuth2 requests.
   */
  @NonNull
  public static final String OAUTH2 = "oauth.reddit.com";
  /**
   * The endpoint used for all requests that don't require OAuth2.
   */
  @NonNull
  public static final String WWW = "www.reddit.com";
  /**
   * The content of the request. May contain serialized JSON objects e.g. to submit new
   * submissions.
   */
  @Nullable
  private RequestBody body;
  /**
   * The host address of the URL. Initialized with {@link #OAUTH2}.
   */
  @NonNull
  private String host = OAUTH2;
  /**
   * Additional parameter appended to the URL. Those may contain additional information, such as
   * the index when requesting stickied posts.
   */
  @NonNull
  private Map<Object, Object> params = new HashMap<>();
  /**
   * The endpoint the request is aimed at.
   */
  @Nullable
  private Endpoint endpoint;
  /**
   * Additional arguments for the {@link Endpoint}. E.g. a  subreddit name.
   */
  @NonNull
  private List<Object> args = new ArrayList<>();
  /**
   * Additional headers such as access token appended to each request.
   */
  @NonNull
  private Map<String, String> headers = new HashMap<>();

  @NonNull
  private String url() {
    HttpUrl.Builder builder = new HttpUrl.Builder().scheme(HTTPS).host(host);
    
    //Append the endpoint URL
    Objects.requireNonNull(endpoint);
    endpoint.getPath(args.toArray()).forEach(builder::addPathSegment);
    
    //Append any additional parameter
    params.forEach((k, v) -> builder.addQueryParameter(Objects.toString(k), Objects.toString(v)));
    
    return builder.build().toString();
  }
  
  private Request.Builder builder() {
    String url = url();
    
    Request.Builder builder = new Request.Builder().url(url);
    headers.forEach(builder::addHeader);
    
    return builder;
  }
  
  @NonNull
  public Request get() {
    return builder().get().build();
  }
  
  @NonNull
  public Request delete() {
    return body == null ? builder().delete().build() : builder().delete(body).build();
  }
  
  @NonNull
  public Request put() {
    Objects.requireNonNull(body);
    return builder().put(body).build();
  }
  
  @NonNull
  public Request post() {
    Objects.requireNonNull(body);
    return builder().post(body).build();
  }
  
  @NonNull
  public Request patch() {
    Objects.requireNonNull(body);
    return builder().patch(body).build();
  }
  
  /**
   * Implements the Builder pattern for constructing REST requests.
   */
  @NonNull
  public static class Builder {
    @NonNull
    private static final Logger LOGGER = LogManager.getLogger(Builder.class);
    @NonNull
    private final RestRequest request = new RestRequest();
  
    /**
     * Sets the request body, containing information about the requested resources. Elements within
     * the body are stored as key-value pairs.<p/>
     * This method will overwrite the existing body.<p/>
     * API request have to use the type {@link BodyType#JSON}, while authentication requests have to
     * use {@link BodyType#FORM}.
     *
     * @param body A collection of key-value pairs included in the request body.
     * @param type The type of request body.
     * @return The current builder instance.
     */
    @NonNull
    public Builder setBody(@NonNull Map<?, ?> body, @NonNull BodyType type) {
      switch (type) {
        case JSON:
          try {
            ObjectMapper om = new ObjectMapper();
            String value = om.writeValueAsString(body);

            MediaType json = MediaType.parse("application/json; charset=utf-8");

            return setBody(RequestBody.create(value, json));
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
          }
        case FORM:
          FormBody.Builder builder = new FormBody.Builder();

          body.forEach((k, v) -> builder.add(k.toString(), v.toString()));

          return setBody(builder.build());
        default:
          throw new UnsupportedOperationException("Unknown body type: " + type);
      }
    }
  
    @NonNull
    public Builder setBody(@NonNull RequestBody body) {
      this.request.body = body;
      return this;
    }
  
    @NonNull
    public Builder setHost(@NonNull String host) {
      this.request.host = host;
      return this;
    }
  
    @NonNull
    public Builder setEndpoint(@NonNull Endpoint endpoint) {
      this.request.endpoint = endpoint;
      return this;
    }
  
    @NonNull
    public <K, V> Builder addParam(@NonNull K key, @NonNull V value) {
      this.request.params.put(key, value);
      return this;
    }
  
    /**
     * Sets additional parameters attached to the query. Parameters are stored as key-value
     * pairs.<p/>
     * e.g. {@code /foo/bar?new=true&time=now}.<p/>
     * This method will overwrite the existing arguments.<p/>
     *
     * @param params A map of key-value pairs attached to the query.
     * @return The current builder instance.
     */
    @NonNull
    public Builder setParams(@NonNull Map<?, ?> params) {
      if (!this.request.params.isEmpty()) {
        LOGGER.debug("params is not empty! Overwriting {} entries", this.request.params.size());
      }
      this.request.params = new HashMap<>(params);
      return this;
    }
  
    /**
     * Sets additional parameters attached to the query. Parameters are stored as key-value
     * pairs.<p/>
     * e.g. {@code /foo/bar?new=true&time=now}.<p/>
     * This method will overwrite the existing arguments.<p/>
     *
     * @param params A list of key-value pairs attached to the query.
     * @return The current builder instance.
     */
    @NonNull
    public Builder setParams(@NonNull Parameter... params) {
      Map<Object, Object> result = new HashMap<>();

      Arrays.stream(params).forEach(param -> result.put(param.getKey(), param.getValue()));
      
      return setParams(result);
    }
  
    @NonNull
    public Builder addArg(@NonNull Object arg) {
      this.request.args.add(arg);
      return this;
    }
  
    /**
     * Sets the arguments that are used to replace the wildcards within the unqualified
     * endpoint.<p/>
     * This method will overwrite the existing arguments.<p/>
     *
     * @param args A list of objects.
     * @return The current builder instance.
     */
    @NonNull
    public Builder setArgs(@NonNull Object... args) {
      if (!this.request.args.isEmpty()) {
        LOGGER.debug("params is not empty! Overwriting {} entries", this.request.args.size());
      }
      this.request.args = new ArrayList<>(Arrays.asList(args));
      return this;
    }
  
    @NonNull
    public Builder addHeader(@NonNull String key, @NonNull String value) {
      this.request.headers.put(key, value);
      return this;
    }
  
    /**
     * Sets the headers of the REST request, overwriting previously set headers.
     *
     * @param headers The new headers used for the REST requests.
     * @return The current builder instance.
     */
    @NonNull
    public Builder setHeaders(@NonNull Map<String, String> headers) {
      if (!this.request.headers.isEmpty()) {
        LOGGER.debug("headers is not empty! Overwriting {} entries", this.request.headers.size());
      }
      this.request.headers = new HashMap<>(headers);
      return this;
    }
  
    @NonNull
    public RestRequest build() {
      return request;
    }
  }
  
  /**
   * The body type of the REST request. When authentication the application, the {@code FORM} type
   * has to be selected, for API requests, the {@code JSON} type has to be selected.
   */
  @NonNull
  public enum BodyType {
    JSON,
    FORM
  }
}
