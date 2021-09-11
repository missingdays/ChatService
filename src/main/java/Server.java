import handler.ChatHttpRootHandler;
import io.undertow.Undertow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.util.*;


public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv("PORT"));
        Undertow server = Undertow.builder()
                .addHttpListener(port, "0.0.0.0", ChatHttpRootHandler.getInstance())
                .build();
        logger.info("port: " + port);
        server.start();
    }

}
