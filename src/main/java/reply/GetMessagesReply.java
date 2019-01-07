package reply;

import Entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.List;

public class GetMessagesReply implements Reply {
    @Override
    public int getStatusCode() {
        return 200;
    }

    private ByteBuffer byteBuffer;
    private static Logger logger = LogManager.getLogger(LoginReply.class);
    private List<Message> messages;

    @NotNull
    @Override
    public ByteBuffer getPayload() {
        if (byteBuffer == null) {
            String jsonBody = "";
            ObjectWriter writer = new ObjectMapper().writerFor(GetMessagesReply.class);
            try {
                jsonBody = writer.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                logger.warn("error while process json");
            }
            byteBuffer = ByteBuffer.wrap(jsonBody.getBytes());
        }
        return byteBuffer;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
