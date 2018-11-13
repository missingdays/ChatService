package handler.errorHandler;

import handler.RequestHandler;
import org.jetbrains.annotations.NotNull;
import reply.error.BadRequestReply;
import reply.Reply;
import request.Request;

public class BadRequestHandler implements RequestHandler {
    @NotNull
    @Override
    public Reply handle(Request request) {
        return new BadRequestReply();
    }
}
