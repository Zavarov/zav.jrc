package zav.jra.query;

import zav.jra.AbstractClient;
import zav.jra.endpoints.Endpoint;
import zav.jra.http.APIRequest;

import java.io.IOException;
import java.util.function.Function;

public class QueryGet<Q> extends QueryBase<Q, QueryGet<Q>>{

    public QueryGet(Function<String, Q> mapper, AbstractClient client, Endpoint endpoint, Object... args) {
        super(mapper, client, endpoint, args);
    }

    @Override
    protected QueryGet<Q> getRealThis() {
        return this;
    }

    @Override
    public Q query() throws IOException, InterruptedException{
        String source = new APIRequest.Builder(client)
                .setHost(host)
                .setParams(params)
                .setEndpoint(endpoint)
                .setArgs(args)
                .build()
                .get();

        return mapper.apply(source);
    }
}
