package reply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.undertow.util.HttpString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import utils.HttpCode;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginReply implements Reply {
    private static Logger logger = LogManager.getLogger(LoginReply.class);
    private int id = 0;
    @JsonIgnore
    private int statusCode = 200;
    private HashMap<HttpString, List<String>> headerMap = new HashMap<>();
    private ByteBuffer byteBuffer;

    public LoginReply() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    @NotNull
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String username;
    private boolean online = true;
    private String token;
    @NotNull
    static public LoginReply loginInUse(String username){
        LoginReply reply = new LoginReply();
        reply.statusCode= HttpCode.UNAUTHORIZED.getValue();
        reply.headerMap.put(new HttpString("WWW-Authenticate"), Collections.singletonList("Token realm='Username is already in use'"));
        reply.byteBuffer=ByteBuffer.allocate(0);
        return reply;
    }
    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @NotNull
    @Override
    public Map<HttpString, List<String>> getHeaders() {
        return headerMap;
    }

    @NotNull
    @Override
    public ByteBuffer getPayload() {
        if(byteBuffer==null){
            String jsonBody = "";
            ObjectWriter writer = new ObjectMapper().writerFor(LoginReply.class);
            try {
                jsonBody = writer.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                logger.warn("error while process json");
            }
            byteBuffer = ByteBuffer.wrap(jsonBody.getBytes());
        }
        return byteBuffer;
    }
}
