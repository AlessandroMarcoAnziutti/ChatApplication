package Connection;

import Connection.Messages.GetChatRequestMessage;
import Connection.Messages.LoginMessage;
import Connection.Messages.Message;
import Connection.Messages.ReplyMessage;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.io.*;

public class Deserializer {

    private final Gson gson = new Gson();

    public Deserializer() {}

    public Message deserialize(String messageString) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(messageString).getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        switch (id){
            case 0:
                return gson.fromJson(messageString, LoginMessage.class);
            case 2:
                return gson.fromJson(messageString, ReplyMessage.class);
            case 3:
                return gson.fromJson(messageString, GetChatRequestMessage.class);
            default:
                return null;
        }
    }


    public String serialize(Message message){
        return gson.toJson(message);
    }

}