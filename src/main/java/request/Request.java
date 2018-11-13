package request;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Request {
    protected String method;
    private String path;

    protected HeaderMap headers;
    protected byte[] payload;
    private long payload_length;

    public Request(HttpServerExchange exchange) throws IOException {
        method = exchange.getRequestMethod().toString();
        headers = exchange.getRequestHeaders();
        path = exchange.getRequestPath();
        payload_length = exchange.getRequestContentLength();
        ByteBuffer bb = ByteBuffer.allocate((int) payload_length);
        exchange.getRequestChannel().read(bb);
        payload=bb.array();
    }
    Request(){

    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
