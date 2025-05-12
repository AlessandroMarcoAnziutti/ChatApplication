package Connection.Messages;

import Server.Controller;

import java.io.IOException;
import java.time.LocalDateTime;

public class GetChatRequestMessage extends Message {
    public GetChatRequestMessage(String sender) {
        this.sender = sender;
        this.recipient = "Server";
        this.message = "";
        this.id = 3;
    }

    public void execute(Controller controller) throws IOException {
        controller.chatRequestManagement(sender);
    }
}
