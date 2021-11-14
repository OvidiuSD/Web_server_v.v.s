package web.server.configuration;

public class ServerConfigException extends RuntimeException{

    public ServerConfigException(String message) {
        super(message);
    }
    public ServerConfigException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServerConfigException(Throwable cause) {
        super(cause);
    }
}
