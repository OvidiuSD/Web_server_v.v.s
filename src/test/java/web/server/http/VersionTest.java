package web.server.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VersionTest {
    @Test
    void getBestCompatibleVersionExactMatch(){
        Version version = null;
        try {
            version = Version.getBestCompatibleVersion("HTTP/1.1");
        } catch (BadParsException e) {
            fail();
        }

        assertNotNull(version);
        assertEquals(version, Version.HTTP_1_1);

    }

    @Test
    void getBestCompatibleVersionBadFormat(){
        Version version = null;
        try {
            version = Version.getBestCompatibleVersion("http/1.1");
            fail();
        } catch (BadParsException e) {

        }

    }

    @Test
    void getBestCompatibleVersionHigherVersion(){
        Version version = null;
        try {
            version = Version.getBestCompatibleVersion("HTTP/1.2");
            assertNotNull(version);
            assertEquals(version, Version.HTTP_1_1);

        } catch (BadParsException e) {
            fail();
        }
    }
}
