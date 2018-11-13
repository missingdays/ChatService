package request;

import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public class PostMessageRequest extends Request {

    public PostMessageRequest(HttpServerExchange exchange) throws IOException {
        super(exchange);
    }

    public PostMessageRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
