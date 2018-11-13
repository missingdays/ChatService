package handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.jetbrains.annotations.NotNull;
import reply.Reply;
import request.Request;
import utils.AbstractFactory;

import java.io.IOException;

public class ChatHttpRootHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        RequestHandler handler = handlerFactory.get(httpServerExchange.getRequestPath());
        if(handler==null){
            handler=badRequestHandler;
        }
        Reply reply = handler.handle(new Request(httpServerExchange));
        sendReply(reply, httpServerExchange);
    }

    private ChatHttpRootHandler() throws IOException {
    }

    public static ChatHttpRootHandler getInstance() throws IOException {
        if (instance == null) {
            instance = new ChatHttpRootHandler();
            return instance;
        }
        return instance;
    }

    private static ChatHttpRootHandler instance = null;

    private void sendReply(@NotNull Reply reply, @NotNull HttpServerExchange exchange) {
        exchange.setStatusCode(reply.getStatusCode());
        reply.getHeaders().forEach( (header, values)-> exchange.getResponseHeaders().putAll(header,values ));
        exchange.getResponseSender().send(reply.getPayload());
    }
    private RequestHandler badRequestHandler = new BadRequestHandler();
    private AbstractFactory<RequestHandler> handlerFactory = new AbstractFactory<>("/handlers.properties");
}
