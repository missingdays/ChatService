package handler.messages;

import handler.RequestHandler;
import org.jetbrains.annotations.NotNull;
import reply.Reply;
import reply.error.MethodNotAllowedReply;
import request.Request;

public class MessagesRequestHandler implements RequestHandler {
    @Override
    public @NotNull Reply handle(@NotNull Request request) {
        switch (request.getMethod()) {
            case "POST":
                return new PostMessagesRequestHandler().handle(request);
            case "GET":
                return new GetMessagesRequestHandler().handle(request);
            default:
                return new MethodNotAllowedReply();
        }
    }
}
