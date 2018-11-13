package handler;

import Entity.Message;
import Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import handler.errorHandler.BadRequestHandler;
import handler.errorHandler.UnAuthorizedRequestHandler;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import reply.PostMessageReply;
import reply.Reply;
import reply.error.MethodNotAllowedReply;
import request.PostMessageRequest;
import request.Request;
import utils.db.MessageDAO;
import utils.db.MessageDAOImplDummy;
import utils.db.UserDAO;
import utils.db.UserDAOImplDummy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MessagesRequestHandler implements RequestHandler {
    private static final Logger logger = LogManager.getLogger(RequestHandler.class);
    private ObjectReader reader = new ObjectMapper().readerFor(PostMessageRequest.class);

    @NotNull
    @Override
    public Reply handle(Request request) {
        logger.trace(String.format("Login:%s; path:%s;method: %s",
                new String(request.getPayload(), StandardCharsets.UTF_8),
                request.getPath(),
                request.getMethod()));
        if (isAllowedMethod(request)) {
            return new MethodNotAllowedReply();
        }
        HeaderValues authorization = request.getHeaders().get(Headers.AUTHORIZATION);
        if (authorization == null){
            logger.info("unauthorized user");
            return new UnAuthorizedRequestHandler().handle(request);
        }
        User user = userDAO.getUserByToken(authorization.getFirst());
        if(user==null){
            logger.info("unauthorized user - bad token");
            return new UnAuthorizedRequestHandler().handle(request);
        }
        try {
            PostMessageRequest messageRequest = reader.readValue(request.getPayload());
            Message message = messageDAO.addMessage(messageRequest.getMessage(), user.getId());
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

    private boolean isAllowedMethod(Request request) {
        return !request.getMethod().equals("POST");
    }

    private UserDAO userDAO = new UserDAOImplDummy();
    private MessageDAO messageDAO = new MessageDAOImplDummy();
}
