package reply;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class GetMessagesReply implements Reply {
    @Override
    public int getStatusCode() {
        return 200;
    }

    @Override
    public @NotNull ByteBuffer getPayload() {
        return null;
    }
}
