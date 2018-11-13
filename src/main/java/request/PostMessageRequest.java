package request;

import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public class PostMessageRequest extends Request {

    public PostMessageRequest(HttpServerExchange exchange) throws IOException {
        super(exchange);
    }
    private String message;
}
