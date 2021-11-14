package web.server.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Parser {
    private final static Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    private static final int SP = 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A;

    public Request parseRequest(InputStream inputStream) throws ParsException{
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        Request request = new Request();
        try {
            parseRequestLine(reader, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseHeaders(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, Request request) throws IOException, ParsException {
        StringBuilder processingDataBuffer = new StringBuilder();

        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        int _byte;
        while((_byte = reader.read()) >= 0){
            if(_byte == CR){
                _byte = reader.read();
                if(_byte == LF){
                    LOGGER.debug("Request Line VERSION to Process: {}", processingDataBuffer.toString());
                    if(!methodParsed || !requestTargetParsed){
                        throw new ParsException(Status.CLIENT_ERROR_1_BAD_REQUEST);
                    }

                    try {
                        request.setVersion(processingDataBuffer.toString());
                    } catch (BadParsException e) {
                        throw new ParsException(Status.CLIENT_ERROR_1_BAD_REQUEST);
                    }

                    return;
                }else {
                    throw new ParsException(Status.CLIENT_ERROR_1_BAD_REQUEST);
                }
            }
            if(_byte == SP){
                if(!methodParsed){
                    LOGGER.debug("Request Line METHOD to Process: {}", processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                }else if(!requestTargetParsed){
                    LOGGER.debug("Request Line REQUEST TARGET to Process: {}", processingDataBuffer.toString());
                    request.setRequestTarget(processingDataBuffer.toString());
                    requestTargetParsed = true;
                }else {
                    throw new ParsException(Status.CLIENT_ERROR_1_BAD_REQUEST);
                }
                processingDataBuffer.delete(0, processingDataBuffer.length());
            }else{
                processingDataBuffer.append((char)_byte);
                if(!methodParsed){
                    if(processingDataBuffer.length() > Method.MAX_LENGTH){
                        throw new ParsException(Status.SERVER_ERROR_5_NOT_IMPLEMENTED);
                    }
                }
            }
        }
    }

    private void parseHeaders(InputStreamReader reader, Request request){

    }

    private void parseBody(InputStreamReader reader, Request request){

    }
}
