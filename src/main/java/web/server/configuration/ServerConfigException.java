package web.server.configuration;

public class ServerConfigException extends RuntimeException{

    public ServerConfigException(String message) {
        super(message);
    }

    public ServerConfigException(Throwable cause) {
        super(cause);
    }
}
