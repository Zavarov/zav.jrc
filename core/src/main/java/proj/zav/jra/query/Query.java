package proj.zav.jra.query;

import proj.zav.jra.AbstractClient;
import proj.zav.jra.endpoints.Endpoint;
import proj.zav.jra.http.APIRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A query is request with additional arguments. The query acts as a request builder, in order to avoid overloading the
 * call methods and to handle default values.
 * @param <T> The expected return type.
 */
public abstract class Query <T, Q extends Query<T,Q>> {
    /**
     * The client is required for building and executing the query.
     */
    protected final AbstractClient client;
    /**
     * The endpoint the query is directed to.
     */
    protected final Endpoint endpoint;
    /**
     * Arguments for potential wildcards in the endpoint.<p>
     * E.g the endpoint {@link Endpoint#GET_SUBREDDIT_ABOUT} with the argument {@code RedditDev} results in the
     * qualified endpoint {@code /r/RedditDev/about}.
     */
    protected final Object[] args;
    /**
     * A map containing all customizable parameters of the query. Those are attached to the qualified endpoint as
     * additional arguments <p>
     * E.g. {@link Endpoint#GET_TOP} specifies a time period as an optional parameters, such that {@code /top?t=all} is
     * a valid endpoint.
     */
    protected final Map<Object,Object> params = new HashMap<>();

    /**
     * The host the requests are directed at. Unless some very rare edge cases, this is almost exclusively
     * {@link APIRequest#OAUTH2}, which is also the default value.
     */
    protected String host = APIRequest.OAUTH2;

    /**
     * Provides a reference to the explicit query type. In fashion of the builder pattern, potential setter methods
     * for the parameters return a reference to the query. This method is required to ensure that all of those instances
     * are of the same type.
     * @return {@code this}
     */
    protected abstract Q getRealThis();

    /**
     *
     * @param client The client executing the query.
     * @param endpoint The endpoint the query is directed to.
     * @param args Potential arguments quantifying wildcards in the endpoint.
     */
    public Query(AbstractClient client, Endpoint endpoint, Object... args){
        this.client = client;
        this.endpoint = endpoint;
        this.args = args;
    }

    public void setHost(String host){
        this.host = host;
    }

    public Q setParameter(Object key, Object value){
        params.put(key, value);
        return getRealThis();
    }

    /**
     * Executes the request and extracts the expected data type from the JSON response.
     * @return An instance of the requested data.
     * @throws IOException If the request couldn't be completed.
     * @throws InterruptedException If the query got interrupted while waiting to be executed.
     */
    public abstract T query() throws IOException, InterruptedException;
}
