package web.server.http;

public class ParsException extends Exception{

    private Status errorCode;

    public ParsException(Status errorCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }

    public Status getErrorCode() {
        return errorCode;
    }
}
