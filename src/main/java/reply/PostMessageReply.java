package reply;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.undertow.util.HttpString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import utils.HttpCode;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostMessageReply implements Reply {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public int getStatusCode() {
        return HttpCode.OK.getValue();
    }

    @NotNull
    @Override
    public Map<HttpString, List<String>> getHeaders() {
        return new HashMap<>();
    }

    @NotNull
    @Override
    public ByteBuffer getPayload() {
        if(byteBuffer==null){
            String jsonBody = "";
            ObjectWriter writer = new ObjectMapper().writerFor(this.getClass());
            try {
                jsonBody = writer.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                logger.warn("error while process json");
            }
            byteBuffer = ByteBuffer.wrap(jsonBody.getBytes());
        }
        return byteBuffer;
    }
    private ByteBuffer byteBuffer;
    private static Logger logger = LogManager.getLogger(LoginReply.class);
}
