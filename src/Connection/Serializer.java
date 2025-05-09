package Connection;

import Connection.Messages.Message;
import com.google.gson.Gson;


public class Serializer {
    private final Gson gson = new Gson();

    public Serializer() {}

    public String serialize(Message message){
        return gson.toJson(message);
    }
}
