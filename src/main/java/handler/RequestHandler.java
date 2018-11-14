package handler;

import org.jetbrains.annotations.NotNull;
import reply.Reply;
import request.Request;


public interface RequestHandler {
    @NotNull
    Reply handle(@NotNull Request request);
}
