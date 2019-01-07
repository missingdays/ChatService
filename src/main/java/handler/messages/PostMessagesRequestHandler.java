package handler.messages;

import Entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import handler.RequsetHandlerBase;
import handler.errorHandler.BadRequestHandler;
import org.jetbrains.annotations.NotNull;
import reply.PostMessageReply;
import reply.Reply;
import request.PostMessageRequest;
import request.Request;

import java.io.IOException;

class PostMessagesRequestHandler extends RequsetHandlerBase {
    private ObjectReader reader = new ObjectMapper().readerFor(PostMessageRequest.class);

    @Override
    protected boolean isRequestValid(Request request) {
        try {
            parsedRequest = reader.readValue(request.getPayload());
        } catch (IOException e) {
            logger.warn("error during parse json to {}");
        }
        return parsedRequest != null;
    }

    @Override
    protected @NotNull Reply createReply(Request request) {
        assert (parsedRequest != null);
        try {
            PostMessageRequest messageRequest = reader.readValue(request.getPayload());
            Message message = messageDAO.addMessage(messageRequest.getMessage(), user.getId(), user.getUsername());
            return getPostMessageReply(message);
        } catch (IOException e) {
            logger.warn("error while reading PostMessageRequest from " + new String(request.getPayload()));
            return new BadRequestHandler().handle(request);
        }
    }

    @NotNull
    private PostMessageReply getPostMessageReply(Message message) {
        PostMessageReply reply = new PostMessageReply();
        reply.setId(message.getId());
        reply.setMessage(message.getMessage());
        return reply;
    }

    @Override
    protected boolean isAllowedMethod(Request request) {
        return !request.getMethod().equals("POST");
    }

    private PostMessageRequest parsedRequest;
}
