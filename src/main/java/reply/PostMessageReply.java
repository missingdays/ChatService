package reply;

import io.undertow.util.HttpString;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostMessageReply implements Reply {
    private int id;
    private String message;

    @Override
    public int getStatusCode() {
        return 0;
    }

    @NotNull
    @Override
    public Map<HttpString, List<String>> getHeaders() {
        return new HashMap<>();
    }

    @NotNull
    @Override
    public ByteBuffer getPayload() {
        return ByteBuffer.allocate(1);
    }
}
