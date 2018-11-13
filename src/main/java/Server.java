import handler.ChatHttpRootHandler;
import io.undertow.Undertow;
import io.undertow.util.Headers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    public static void main(String[] args) throws IOException {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost", ChatHttpRootHandler.getInstance())
                .build();
        server.start();
    }

}
