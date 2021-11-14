package web.server.json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
public class Json {
    private static ObjectMapper myObjectMapper=defaultObjectMapper();
    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper obj  = new ObjectMapper();
        obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return obj;
    }
    public static JsonNode parse(String source) throws JsonProcessingException {
        return myObjectMapper.readTree(source);
    }
    public static <A> A fromJson(JsonNode node , Class<A> classz) throws JsonProcessingException {
        return  myObjectMapper.treeToValue(node,classz);
    }

}
