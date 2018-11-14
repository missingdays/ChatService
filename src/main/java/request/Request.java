package request;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.Map;

public class Request {
    private String method;
    private String path;

    private HeaderMap headers;
    private byte[] payload;

    @Nullable
    public Map<String, Deque<String>> getQueryMap() {
        return queryMap;
    }

    @Nullable
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    private Map<String, Deque<String>> queryMap;
    private String authorizationToken;
    private long payload_length;

    public Request(HttpServerExchange exchange) throws IOException {
        method = exchange.getRequestMethod().toString();
        headers = exchange.getRequestHeaders();
        queryMap = exchange.getQueryParameters();
        path = exchange.getRequestPath();
        payload_length = exchange.getRequestContentLength();
        authorizationToken = exchange.getRequestHeaders().get(Headers.AUTHORIZATION, 0);
        ByteBuffer bb = ByteBuffer.allocate((int) payload_length);
        exchange.getRequestChannel().read(bb);
        payload=bb.array();
    }
    Request(){

    }

    @NotNull
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @NotNull
    public String getPath() {
        return path;
    }

    public void setPath(@NotNull String path) {
        this.path = path;
    }

    @NotNull
    public HeaderMap getHeaders() {
        return headers;
    }

    public void setHeaders(HeaderMap headers) {
        this.headers = headers;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public long getPayload_length() {
        return payload_length;
    }

    public void setPayload_length(int payload_length) {
        this.payload_length = payload_length;
    }
}
