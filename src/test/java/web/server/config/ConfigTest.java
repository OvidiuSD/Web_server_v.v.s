package web.server.config;

import org.junit.jupiter.api.Test;
import web.server.configuration.Config;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigTest {
    @Test
    public void webroot_getValue() throws NoSuchFieldException, IllegalAccessException{
        final Config config = new Config();
        final Field field = config.getClass().getDeclaredField("webroot");
        field.setAccessible(true);
        field.set(config, "testWebroot");

        final String result = config.getWebRoot();

        assertEquals(result, "testWebroot");
    }

    @Test
    public void port_getValue() throws NoSuchFieldException, IllegalAccessException{
        final Config config = new Config();
        final Field field = config.getClass().getDeclaredField("port");
        field.setAccessible(true);
        field.set(config, 3355);

        final int result = config.getPort();

        assertEquals(result, 3355);
    }

    @Test
    public void webroot_setValue() throws NoSuchFieldException, IllegalAccessException{
        final Config config = new Config();
        config.setWebRoot("testWebroot");
        final Field field = config.getClass().getDeclaredField("webroot");
        field.setAccessible(true);
        assertEquals(field.get(config), "testWebroot");
    }

    @Test
    public void port_setValue() throws NoSuchFieldException, IllegalAccessException{
        final Config config = new Config();
        config.setPort(3355);
        final Field field = config.getClass().getDeclaredField("port");
        field.setAccessible(true);
        assertEquals(field.get(config), 3355);
    }
}
