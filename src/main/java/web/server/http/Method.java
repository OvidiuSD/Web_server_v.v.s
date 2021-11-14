package web.server.http;

public enum Method {
    GET, HEAD;
    public static final int MAX_LENGTH;

    static{
        int tempMaxLength = -1;
        for(Method method : values()){
            if(method.name().length() > tempMaxLength){
                tempMaxLength = method.name().length();
            }
        }
        MAX_LENGTH = tempMaxLength;
    }
}