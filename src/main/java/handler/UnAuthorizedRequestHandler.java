package handler;

import org.jetbrains.annotations.NotNull;
import reply.Reply;
import request.Request;

public class UnAuthorizedRequestHandler implements RequestHandler {
    @Override
    public @NotNull Reply handle(Request request) {
        return null;
    }
}
