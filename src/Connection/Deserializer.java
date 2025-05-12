package Connection;

import Connection.Messages.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

public class Deserializer {

    private final Gson gson = new Gson();

    public Deserializer() {}

    public Message deserialize(String messageString) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(messageString).getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        switch (id){
            case 0: return gson.fromJson(messageString, LoginMessage.class);
            case 1: return gson.fromJson(messageString, ReplyMessage.class);
            case 2: return gson.fromJson(messageString, TextMessage.class);
            case 3: return gson.fromJson(messageString, GetChatRequestMessage.class);
            case 4: return gson.fromJson(messageString, ChatReplyMessage.class);
            default: return null;
        }
    }
}