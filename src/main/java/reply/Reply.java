package reply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.undertow.util.HttpString;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public interface Reply {
    @JsonIgnore
    int getStatusCode();
    @NotNull
    @JsonIgnore
    Map<HttpString, List<String>> getHeaders();

    @NotNull
    @JsonIgnore
    ByteBuffer getPayload();
}
