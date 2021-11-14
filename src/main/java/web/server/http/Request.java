package web.server.http;

public class Request  extends Message{

    private Method method;
    private String requestTarget;
    private String originalHttpVersion;
    private Version bestCompatibleVersion;

    Request(){

    }

    public Method getMethod() {
        return method;
    }

    void setMethod(String methodName) throws ParsException{
        for(Method method : Method.values()){
            if(methodName.equals(method.name())){
                this.method = method;
                return;
            }
        }
        throw new ParsException(
                Status.SERVER_ERROR_5_NOT_IMPLEMENTED
        );
    }

    void setRequestTarget(String requestTarget) throws ParsException {
        if(requestTarget == null || requestTarget.length() == 0){
            throw new ParsException(Status.SERVER_ERROR_4_INTERNAL_SERVER_ERROR);
        }
        this.requestTarget = requestTarget;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public Version getBestCompatibleVersion() {
        return bestCompatibleVersion;
    }

    public void setVersion(String originalHttpVersion) throws BadParsException, ParsException {
        this.originalHttpVersion = originalHttpVersion;
        this.bestCompatibleVersion = Version.getBestCompatibleVersion(originalHttpVersion);
        if(this.bestCompatibleVersion == null){
            throw new ParsException(
                    Status.SERVER_ERROR_6_VERSION_NOT_SUPPORTED
            );
        }
    }

    public String getOriginalVersion() {
        return originalHttpVersion;
    }
}
