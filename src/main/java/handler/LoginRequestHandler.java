package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import reply.LoginReply;
import reply.MethodNotAllowedReply;
import reply.Reply;
import request.LoginRequest;
import request.Request;

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
            if (isLoginInUse(name)){
                logger.warn("relogin trying with username"+name);
                return LoginReply.loginInUse(name);
            }else {
                return getLoginReply(name);
            }
        } catch (IOException e) {
            logger.warn("error while reading LoginRequest from "+new String(request.getPayload()));
            return new BadRequestHandler().handle(request);
        }
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
}
