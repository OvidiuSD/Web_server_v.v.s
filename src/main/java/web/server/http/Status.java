package web.server.http;

public enum Status {
    CLIENT_ERROR_1_BAD_REQUEST(1, "Bad Request"),
    CLIENT_ERROR_2_METHOD_NOT_ALLOWED(2, "Method Not Allowed"),
    CLIENT_ERROR_3_BAD_REQUEST(3, "URI Too Long"),
    SERVER_ERROR_4_INTERNAL_SERVER_ERROR(4, "Internal Server Error"),
    SERVER_ERROR_5_NOT_IMPLEMENTED(5, "Not Implemented"),
    SERVER_ERROR_6_VERSION_NOT_SUPPORTED(6, "Version Not Supported");



    public final int STATUS_CODE;
    public final String MESSAGE;


    Status(int STATUS_CODE, String MESSAGE){
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }
}
