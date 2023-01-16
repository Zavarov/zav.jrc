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

package zav.jrc.client.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import zav.jrc.api.endpoint.Endpoint;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;

/**
 * The base class for creating all REST request.<br>
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
@NonNullByDefault
public class RequestBuilder {
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
   * The content of the request. May contain serialized JSON objects e.g. to
   * submit new submissions.
   */
  @Nullable
  private RequestBody body;
  /**
   * The host address of the URL. Initialized with {@link #OAUTH2}.
   */
  private String host = OAUTH2;
  /**
   * Additional parameter appended to the URL. Those may contain additional
   * information, such as the index when requesting stickied posts.
   */
  private Map<Object, Object> params = new HashMap<>();
  /**
   * The endpoint the request is aimed at.
   */
  @Nullable
  private Endpoint endpoint;
  /**
   * Additional arguments for the {@link Endpoint}. E.g. a subreddit name.
   */
  private List<Object> args = new ArrayList<>();
  /**
   * Additional headers such as access token appended to each request.
   */
  private Map<String, String> headers = new HashMap<>();

  private final Client client;

  private final BodyType type;

  public RequestBuilder(Client client, BodyType type) {
    this.client = client;
    this.type = type;
  }

  private String url() {
    assert endpoint != null;

    HttpUrl.Builder builder = new HttpUrl.Builder().scheme(HTTPS).host(host);

    // Append the endpoint URL
    Objects.requireNonNull(endpoint);
    endpoint.getPath(args.toArray()).forEach(builder::addPathSegment);

    // Append any additional parameter
    params.forEach((k, v) -> builder.addQueryParameter(Objects.toString(k), Objects.toString(v)));

    return builder.build().toString();
  }

  private Request.Builder builder() {
    String url = url();

    Request.Builder builder = new Request.Builder().url(url);
    headers.forEach(builder::addHeader);

    return builder;
  }

  public String get() throws FailedRequestException {
    Request request = builder().get().build();

    return client.send(request);
  }

  public String delete() throws FailedRequestException {
    Request request = body == null ? builder().delete().build() : builder().delete(body).build();

    return client.send(request);
  }

  /**
   * Transform this request into a PUT request.
   *
   * @return A PUT request with the current arguments.
   */
  public String put() throws FailedRequestException {
    Objects.requireNonNull(body);

    Request request = builder().put(body).build();

    return client.send(request);
  }

  /**
   * Transform this request into a POST request.
   *
   * @return A POST request with the current arguments.
   */
  public String post() throws FailedRequestException {
    Objects.requireNonNull(body);

    Request request = builder().post(body).build();

    return client.send(request);
  }

  /**
   * Transform this request into a PATCH request.
   *
   * @return A PATCH request with the current arguments.
   */
  public String patch() throws FailedRequestException {
    Objects.requireNonNull(body);

    Request request = builder().patch(body).build();

    return client.send(request);
  }

  /**
   * Sets the request body, containing information about the requested resources.
   * Elements within the body are stored as key-value pairs.<br>
   * This method will overwrite the existing body.
   *
   * @param body A collection of key-value pairs included in the request body.
   * @return The current builder instance.
   */
  public RequestBuilder withBody(Map<?, ?> body) {
    switch (type) {
      case JSON:
        try {
          ObjectMapper om = new ObjectMapper();
          String value = om.writeValueAsString(body);

          @Nullable
          MediaType json = MediaType.parse("application/json; charset=utf-8");

          this.body = RequestBody.create(value, json);
        } catch (JsonProcessingException e) {
          throw new IllegalArgumentException(e.getMessage(), e);
        }
        break;
      case FORM:
        FormBody.Builder builder = new FormBody.Builder();

        body.forEach((k, v) -> builder.add(k.toString(), v.toString()));

        this.body = builder.build();
        break;
      default:
        throw new UnsupportedOperationException("Unknown body type: " + type);
    }

    return this;
  }

  public RequestBuilder withImage(byte[] data, String name) {
    this.body = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file", name, RequestBody.create(data, MediaType.parse("image/png")))
        .addFormDataPart("header", "0").addFormDataPart("name", name)
        .addFormDataPart("img_type", "png").addFormDataPart("upload_type", "img").build();
    return this;
  }

  public RequestBuilder withHeader(byte[] data) {
    this.body = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file", "header", RequestBody.create(data, MediaType.parse("image/png")))
        .addFormDataPart("header", "1").addFormDataPart("img_type", "png")
        .addFormDataPart("upload_type", "header").build();
    return this;
  }

  public RequestBuilder withIcon(byte[] data) {
    this.body = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file", "icon", RequestBody.create(data, MediaType.parse("image/png")))
        .addFormDataPart("header", "0").addFormDataPart("img_type", "png")
        .addFormDataPart("upload_type", "icon").build();
    return this;
  }

  public RequestBuilder withBanner(byte[] data) {
    this.body = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file", "icon", RequestBody.create(data, MediaType.parse("image/png")))
        .addFormDataPart("header", "0").addFormDataPart("img_type", "png")
        .addFormDataPart("upload_type", "banner").build();
    return this;
  }

  public RequestBuilder withHost(String host) {
    this.host = host;
    return this;
  }

  public RequestBuilder withEndpoint(Endpoint endpoint, Object... args) {
    this.endpoint = endpoint;
    this.args = List.of(args);
    return this;
  }

  public <K, V> RequestBuilder withParam(K key, V value) {
    this.params.put(key, value);
    return this;
  }

  /**
   * Sets additional parameters attached to the query. Parameters are stored as
   * key-value pairs.<br>
   * e.g. {@code /foo/bar?new=true&time=now}.<br>
   * This method will overwrite the existing arguments.<br>
   *
   * @param params A map of key-value pairs attached to the query.
   * @return The current builder instance.
   */
  public RequestBuilder withParams(Map<?, ?> params) {
    this.params.putAll(params);
    return this;
  }

  public RequestBuilder withHeader(String key, String value) {
    this.headers.put(key, value);
    return this;
  }

  /**
   * Sets the headers of the REST request, overwriting previously set headers.
   *
   * @param headers The new headers used for the REST requests.
   * @return The current builder instance.
   */
  public RequestBuilder withHeader(Map<String, String> headers) {
    this.headers.putAll(headers);
    return this;
  }

  /**
   * The body type of the REST request. When authentication the application, the
   * {@code FORM} type has to be selected, for API requests, the {@code JSON} type
   * has to be selected.
   */
  public enum BodyType {
    JSON, FORM
  }
}
