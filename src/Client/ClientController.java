package Client;

import Connection.Messages.GetChatRequestMessage;
import Connection.Messages.LoginMessage;
import Connection.Messages.TextMessage;
import Connection.Serializer;

import java.io.IOException;
import java.util.List;

public class ClientController {
    private String username;
    private Client client;
    private Serializer serializer;

    public ClientController(Client client){
        this.client = client;
        serializer = new Serializer();
    }

    public void textMessageManager(List<String> messageSplit) throws IOException {
        TextMessage message = new TextMessage(username, messageSplit.get(1), messageSplit.get(2));
        client.sendMessage(serializer.serialize(message));
    }

    public void chatRequestManager() throws IOException {
        GetChatRequestMessage message = new GetChatRequestMessage(username);
        client.sendMessage(serializer.serialize(message));
    }

    public void loginManager(List<String> messageSplit) throws IOException {
        LoginMessage message = new LoginMessage(username, messageSplit.get(1));
        client.sendMessage(serializer.serialize(message));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
