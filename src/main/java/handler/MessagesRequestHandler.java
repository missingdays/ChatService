package handler;

import Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import reply.LoginReply;
import reply.MethodNotAllowedReply;
import reply.Reply;
import request.LoginRequest;
import request.PostMessageRequest;
import request.Request;
import utils.db.MessageDAO;
import utils.db.UserDAO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MessagesRequestHandler implements RequestHandler {
    private static final Logger logger = LogManager.getLogger(RequestHandler.class);
    private ObjectReader reader = new ObjectMapper().readerFor(LoginRequest.class);

    @NotNull
    @Override
    public Reply handle(Request request) {
        logger.trace(String.format("Login:%s; path:%s;method: %s",
                new String(request.getPayload(), StandardCharsets.UTF_8),
                request.getPath(),
                request.getMethod()));
        if(isAllowedMethod(request)){
            return new MethodNotAllowedReply();
        }
        HeaderValues authorization = request.getHeaders().get(Headers.AUTHORIZATION);
        User user;
        if ((authorization == null)
                || ((user = userDAO.getUserByName(authorization.getFirst())) == null)){
            logger.info("unauthorized user");
            return new UnAuthorizedRequestHandler().handle(request);
        }
        try {
            PostMessageRequest messageRequest = reader.readValue(request.getPayload());

        } catch (IOException e) {
            logger.warn("error while reading LoginRequest from "+new String(request.getPayload()));
            return new BadRequestHandler().handle(request);
        }
    }

    private boolean isTokenExists(String first) {
        return false;
    }

    @NotNull
    private LoginReply getLoginReply(String name) {
        LoginReply reply = new LoginReply();
        reply.setUsername(name);
        reply.setOnline(true);
        reply.setId(generteId());
        reply.setToken(generateToken());
        return reply;
    }

    private boolean isAllowedMethod(Request request) {
        return !request.getMethod().equals("POST");
    }

    private int generteId() {
        return 0;
    }

    private boolean isLoginInUse(String login){
        return false;
    }
    private String generateToken(){
        return String.valueOf(UUID.randomUUID());
    }
    private UserDAO userDAO;
    private MessageDAO messageDAO;
}
