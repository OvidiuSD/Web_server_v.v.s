package web.server.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParserTest {

    private Parser Parser;

    @BeforeAll
    public void beforeClass(){
        Parser = new Parser();
    }

    @Test
    void parseRequestBadMethod1() {
        try {
            Request request = Parser.parseRequest(
                    generateBadTestCaseMethod1()
            );
        } catch (ParsException e) {
            e.printStackTrace();
            assertEquals(e.getErrorCode(), Status.SERVER_ERROR_5_NOT_IMPLEMENTED);
        }
    }

    @Test
    void parseRequestBadMethod2() {
        try {
            Request request = Parser.parseRequest(
                    generateBadTestCaseMethod2()
            );
            fail();
        } catch (ParsException e) {
            assertEquals(e.getErrorCode(), Status.SERVER_ERROR_5_NOT_IMPLEMENTED);
        }
    }

    @Test
    void parseRequestInvNumItems() {
        try {
            Request request = Parser.parseRequest(
                    generateBadTestCaseRequestLineInvNumItems()
            );
            fail();
        } catch (ParsException e) {
            assertEquals(e.getErrorCode(), Status.CLIENT_ERROR_1_BAD_REQUEST);
        }
    }

    @Test
    void parseRequestNoLF() {
        try {
            Request request = Parser.parseRequest(
                    generateBadTestCaseRequestLineNoLF()
            );
            fail();
        } catch (ParsException e) {
            assertEquals(e.getErrorCode(), Status.CLIENT_ERROR_1_BAD_REQUEST);
        }
    }

    @Test
    void parseEmptyRequest() {
        try {
            Request request = Parser.parseRequest(
                    generateBadTestCaseEmptyRequestLine()
            );
            fail();
        } catch (ParsException e) {
            assertEquals(e.getErrorCode(), Status.CLIENT_ERROR_1_BAD_REQUEST);
        }
    }

    @Test
    void parseRequest() {
        Request request = null;
        try {
            request = Parser.parseRequest(
                    generateValidGETTestCase()
            );
        } catch (ParsException e) {
            fail(e);
        }

        assertNotNull(request);
        assertEquals(request.getMethod(), Method.GET);
        assertEquals(request.getRequestTarget(), "/");
        assertEquals(request.getOriginalVersion(), "HTTP/1.1");
        assertEquals(request.getBestCompatibleVersion(), Version.HTTP_1_1);
    }

    @Test
    void parseRequestBadHttpVersion() {
        try {
            Request request = Parser.parseRequest(
                    generateBadVersionRequest()
            );
            fail();
        } catch (ParsException e) {
            e.printStackTrace();
            assertEquals(e.getErrorCode(), Status.CLIENT_ERROR_1_BAD_REQUEST);
        }
    }

    @Test
    void parseRequestUnsupportedHttpVersion() {
        try {
            Request request = Parser.parseRequest(
                    generateUnsupportedVersionRequest()
            );
            fail();
        } catch (ParsException e) {
            e.printStackTrace();
            assertEquals(e.getErrorCode(), Status.SERVER_ERROR_6_VERSION_NOT_SUPPORTED);
        }
    }

    @Test
    void parseRequestSupportedHttpVersion() {
        try {
            Request request = Parser.parseRequest(
                    generateSupportedVersionRequest()
            );
            assertNotNull(request);
            assertEquals(request.getBestCompatibleVersion(), Version.HTTP_1_1);
            assertEquals(request.getOriginalVersion(), "HTTP/1.2");
        } catch (ParsException e) {
            fail();
        }
    }

    private InputStream generateBadTestCaseMethod1(){
        String rawData = "GET / HTTP/1.1\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateBadTestCaseMethod2(){
        String rawData = "GETTTT / HTTP/1.1\r\n" +
                "Host: localhost:1234\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateBadTestCaseRequestLineInvNumItems(){
        String rawData = "GET / AAA HTTP/1.1\r\n" +
                "Host: localhost:1234\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateBadTestCaseRequestLineNoLF(){
        String rawData = "GET / HTTP/1.1\r" +
                "Host: localhost:1234\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateBadTestCaseEmptyRequestLine(){
        String rawData = "\r\n" +
                "Host: localhost:1234\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateValidGETTestCase(){
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:1234\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"\r\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateBadVersionRequest(){
        String rawData = "GET / HTP/1.1\r\n" +
                "Host: localhost:1234\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"\r\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateUnsupportedVersionRequest(){
        String rawData = "GET / HTTP/2.1\r\n" +
                "Host: localhost:1234\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"\r\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateSupportedVersionRequest(){
        String rawData = "GET / HTTP/1.2\r\n" +
                "Host: localhost:1234\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"\r\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8,ro;q=0.7,de;q=0.6,hu;q=0.5\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }}
