package web.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.server.configuration.Config;
import web.server.configuration.ConfigManager;
import web.server.threads.SrvThread;

import java.io.IOException;

public class Server {
    private final static Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {


        LOGGER.info("SERVER STARTING...");
        ConfigManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Config config = ConfigManager.getInstance().getCurrentConfiguration();
        LOGGER.info("USING PORT: " + config.getPort());
        LOGGER.info("USING WEBROOT:" + config.getWebRoot());


        try {
            SrvThread serverListenerThread = new SrvThread(config.getPort(), config.getWebRoot());

            serverListenerThread.start();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
}


