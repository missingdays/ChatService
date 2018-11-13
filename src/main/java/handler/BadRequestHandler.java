package handler;

import io.undertow.server.HttpServerExchange;
import org.jetbrains.annotations.NotNull;
import reply.BadRequestReply;
import reply.Reply;
import request.Request;

public class BadRequestHandler implements RequestHandler {
    @NotNull
    @Override
    public Reply handle(Request request) {
        return new BadRequestReply();
    }
}
