package Connection.Messages;

import Server.Controller;

import java.io.IOException;

public class TextMessage extends Message {
    public TextMessage(String sender, String recipient, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = body;
        this.id = 2;
    }

    public void execute(Controller controller) throws IOException {
        controller.textMessageManagement(sender, recipient, message);
    }
}
