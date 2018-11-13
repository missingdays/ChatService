package reply.error;

import io.undertow.util.HttpString;
import org.jetbrains.annotations.NotNull;
import reply.Reply;
import utils.HttpCode;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BadRequestReply implements Reply {

    @Override
    public int getStatusCode() {
        return HttpCode.BAD_REQUEST.getValue();
    }

    @NotNull
    @Override
    public Map<HttpString, List<String>> getHeaders() {
        return new HashMap<>();
    }

    @NotNull
    @Override
    public ByteBuffer getPayload() {
        return ByteBuffer.allocate(0);
    }
}
