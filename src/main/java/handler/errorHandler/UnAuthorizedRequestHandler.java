package handler.errorHandler;

import handler.RequestHandler;
import org.jetbrains.annotations.NotNull;
import reply.Reply;
import reply.error.UnAuthorizedReply;
import request.Request;

public class UnAuthorizedRequestHandler implements RequestHandler {
    @Override
    public @NotNull Reply handle(Request request) {
        return new UnAuthorizedReply();
    }
}
