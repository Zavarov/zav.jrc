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

package zav.jrc.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.api.endpoint.Endpoint;

/**
 * The base class for every REST request.<br>
 * There are five different type of requests:
 *
 * <pre>
 *   | Parameter | Description    |
 *   | --------- | -------------- |
 *   | POST      | Create         |
 *   | GET       | Read           |
 *   | PUT       | Update/Replace |
 *   | PATCH     | Update/Modify  |
 *   | DELETE    | Delete         |
 * </pre>
 */
public class RestRequest {
  /**
    * The protocol used for OAuth requests.
   */
  public static final String HTTPS = "https";
  /**
   * The host accepting all OAuth2 requests.
   */
  public static final String OAUTH2 = "oauth.reddit.com";
  /**
   * The endpoint used for all requests that don't require OAuth2.
   */
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
  private String host = OAUTH2;
  /**
   * Additional parameter appended to the URL. Those may contain additional information, such as
   * the index when requesting stickied posts.
   */
  private Map<Object, Object> params = new HashMap<>();
  /**
   * The endpoint the request is aimed at.
   */
  @Nullable
  private Endpoint endpoint;
  /**
   * Additional arguments for the {@link Endpoint}. E.g. a  subreddit name.
   */
  private List<Object> args = new ArrayList<>();
  /**
   * Additional headers such as access token appended to each request.
   */
  private Map<String, String> headers = new HashMap<>();

  private String url() {
    assert endpoint != null;
    
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
  
  public Request get() {
    return builder().get().build();
  }
  
  public Request delete() {
    return body == null ? builder().delete().build() : builder().delete(body).build();
  }
  
  /**
   * Transform this request into a PUT request.
   *
   * @return A PUT request with the current arguments.
   */
  public Request put() {
    assert body != null;
    
    Objects.requireNonNull(body);
    return builder().put(body).build();
  }
  
  /**
   * Transform this request into a POST request.
   *
   * @return A POST request with the current arguments.
   */
  public Request post() {
    assert body != null;
    
    Objects.requireNonNull(body);
    return builder().post(body).build();
  }
  
  /**
   * Transform this request into a PATCH request.
   *
   * @return A PATCH request with the current arguments.
   */
  public Request patch() {
    assert body != null;
    
    Objects.requireNonNull(body);
    return builder().patch(body).build();
  }
  
  /**
   * Implements the Builder pattern for constructing REST requests.
   */
  public static class Builder {
    private static final Logger LOGGER = LogManager.getLogger(Builder.class);
    private final RestRequest request = new RestRequest();
  
    /**
     * Sets the request body, containing information about the requested resources. Elements within
     * the body are stored as key-value pairs.<br>
     * This method will overwrite the existing body.<br>
     * API request have to use the type {@link BodyType#JSON}, while authentication requests have to
     * use {@link BodyType#FORM}.
     *
     * @param body A collection of key-value pairs included in the request body.
     * @param type The type of request body.
     * @return The current builder instance.
     */
    public Builder setBody(Map<?, ?> body, BodyType type) {
      switch (type) {
        case JSON:
          try {
            ObjectMapper om = new ObjectMapper();
            String value = om.writeValueAsString(body);

            @Nullable MediaType json = MediaType.parse("application/json; charset=utf-8");

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
  
    public Builder setBody(RequestBody body) {
      this.request.body = body;
      return this;
    }
  
    public Builder setHost(String host) {
      this.request.host = host;
      return this;
    }
  
    public Builder setEndpoint(Endpoint endpoint) {
      this.request.endpoint = endpoint;
      return this;
    }
  
    public <K, V> Builder addParam(K key, V value) {
      this.request.params.put(key, value);
      return this;
    }
  
    /**
     * Sets additional parameters attached to the query. Parameters are stored as key-value
     * pairs.<br>
     * e.g. {@code /foo/bar?new=true&time=now}.<br>
     * This method will overwrite the existing arguments.<br>
     *
     * @param params A map of key-value pairs attached to the query.
     * @return The current builder instance.
     */
    public Builder setParams(Map<?, ?> params) {
      if (!this.request.params.isEmpty()) {
        LOGGER.debug("params is not empty! Overwriting {} entries", this.request.params.size());
      }
      this.request.params = new HashMap<>(params);
      return this;
    }
  
    /**
     * Sets additional parameters attached to the query. Parameters are stored as key-value
     * pairs.<br>
     * e.g. {@code /foo/bar?new=true&time=now}.<br>
     * This method will overwrite the existing arguments.<br>
     *
     * @param params A list of key-value pairs attached to the query.
     * @return The current builder instance.
     */
    public Builder setParams(Parameter... params) {
      Map<Object, Object> result = new HashMap<>();

      Arrays.stream(params).forEach(param -> result.put(param.getKey(), param.getValue()));
      
      return setParams(result);
    }
  
    public Builder addArg(Object arg) {
      this.request.args.add(arg);
      return this;
    }
  
    /**
     * Sets the arguments that are used to replace the wildcards within the unqualified
     * endpoint.<br>
     * This method will overwrite the existing arguments.<br>
     *
     * @param args A list of objects.
     * @return The current builder instance.
     */
    public Builder setArgs(Object... args) {
      if (!this.request.args.isEmpty()) {
        LOGGER.debug("params is not empty! Overwriting {} entries", this.request.args.size());
      }
      this.request.args = new ArrayList<>(Arrays.asList(args));
      return this;
    }
  
    public Builder addHeader(String key, String value) {
      this.request.headers.put(key, value);
      return this;
    }
  
    /**
     * Sets the headers of the REST request, overwriting previously set headers.
     *
     * @param headers The new headers used for the REST requests.
     * @return The current builder instance.
     */
    public Builder setHeaders(Map<String, String> headers) {
      if (!this.request.headers.isEmpty()) {
        LOGGER.debug("headers is not empty! Overwriting {} entries", this.request.headers.size());
      }
      this.request.headers = new HashMap<>(headers);
      return this;
    }
  
    public RestRequest build() {
      return request;
    }
  }
  
  /**
   * The body type of the REST request. When authentication the application, the {@code FORM} type
   * has to be selected, for API requests, the {@code JSON} type has to be selected.
   */
  public enum BodyType {
    JSON,
    FORM
  }
}
