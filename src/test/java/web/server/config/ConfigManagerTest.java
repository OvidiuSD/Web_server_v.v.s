package web.server.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import web.server.configuration.ConfigManager;
import web.server.configuration.ServerConfigException;

public class ConfigManagerTest {
    @Test
    public void loadConfigurationFileTest() {

        Assertions.assertThrows(ServerConfigException.class, () -> {
            String filePath = "not found";
            final ConfigManager configManager = ConfigManager.getInstance();
            configManager.loadConfigurationFile(filePath);
        });

    }

}
