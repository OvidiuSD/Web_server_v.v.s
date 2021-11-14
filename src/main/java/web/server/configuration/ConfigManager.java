package web.server.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import web.server.json.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigManager {
    private static ConfigManager myConfigurationManager;
    private static Config myCurrentConfiguration;

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigManager();
        }
        return myConfigurationManager;
    }

    public void loadConfigurationFile(String filePath) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new ServerConfigException(e);
        }
        StringBuffer buffer = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                buffer.append((char) i);
            }
        } catch (IOException e) {
            throw new ServerConfigException(e);
        }
        JsonNode config;
        try {
            config = Json.parse(buffer.toString());
        } catch (JsonProcessingException e) {
            throw new ServerConfigException("ERROR AT PARSING CONFIG");
        }
        try {
            myCurrentConfiguration = Json.fromJson(config, Config.class);
        } catch (JsonProcessingException e) {
            throw new ServerConfigException("ERROR PARSING CONFIG INTERNAL");
        }
    }

    public Config getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new ServerConfigException("NO CURRENT CONFIGURATION WAS SET");
        }
        return myCurrentConfiguration;
    }
}
