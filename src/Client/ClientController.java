package Client;

import Connection.ClientSide;
import Connection.Messages.GetChatRequestMessage;
import Connection.Messages.LoginMessage;
import Connection.Messages.TextMessage;

import java.io.IOException;
import java.util.List;

public class ClientController {
    private String username;
    private final ClientSide clientSide = ClientSide.getInstance();

    public void textMessageManager(List<String> messageSplit) throws IOException {
        TextMessage message = new TextMessage(username, messageSplit.get(1), messageSplit.get(2));
        clientSide.sendMessage(message);
    }

    public void chatRequestManager() throws IOException {
        GetChatRequestMessage message = new GetChatRequestMessage(username);
        clientSide.sendMessage(message);
    }

    public void loginManager(List<String> messageSplit) throws IOException {
        LoginMessage message = new LoginMessage(username, messageSplit.get(1));
        clientSide.sendMessage(message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
