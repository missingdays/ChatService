package request;

import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public class LoginRequest extends Request {
    public LoginRequest(HttpServerExchange exchange) throws IOException {
        super(exchange);
    }
    public LoginRequest() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
