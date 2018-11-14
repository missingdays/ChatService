package reply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface Reply {
    @JsonIgnore
    int getStatusCode();
    @NotNull
    @JsonIgnore
    default Map<HttpString, List<String>> getHeaders() {
        HttpString headerName = Headers.CONTENT_TYPE;
        List list = Collections.singletonList(JSON);
        return Collections.singletonMap(headerName, list);
    }

    @NotNull
    @JsonIgnore
    ByteBuffer getPayload();

    String JSON = "application/json";
}
