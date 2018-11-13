package handler;

import Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import handler.errorHandler.BadRequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import reply.LoginReply;
import reply.error.MethodNotAllowedReply;
import reply.Reply;
import request.LoginRequest;
import request.Request;
import utils.db.UserDAO;
import utils.db.UserDAOImplDummy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LoginRequestHandler implements RequestHandler {
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
        try {
            LoginRequest loginRequest = reader.readValue(request.getPayload());
            String name = loginRequest.getName();
            logger.info("get login request for name "+name);
            User newUser = userDAO.addUser(name);
            if (newUser==null){
                logger.warn("relogin trying with username"+name);
                return LoginReply.loginInUse(name);
            }else {
                return getLoginReply(newUser);
            }
        } catch (IOException e) {
            logger.warn("error while reading LoginRequest from "+new String(request.getPayload()));
            return new BadRequestHandler().handle(request);
        }
    }

    @NotNull
    private LoginReply getLoginReply(User user) {
        LoginReply reply = new LoginReply();
        reply.setUsername(user.getUsername());
        reply.setOnline(true);
        reply.setId(generteId());
        reply.setToken(user.getToken());
        return reply;
    }

    private boolean isAllowedMethod(Request request) {
        return !request.getMethod().equals("POST");
    }

    private int generteId() {
        return 0;
    }


    private UserDAO userDAO = new UserDAOImplDummy();
}
