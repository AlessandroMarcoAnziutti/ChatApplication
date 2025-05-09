package Connection.Messages;

import java.time.LocalDateTime;

public class GetChatRequestMessage extends Message {
    public GetChatRequestMessage(String sender) {
        this.sender = sender;
        this.recipient = "Server";
        this.message = "";
        this.id = 3;
        timestamp = LocalDateTime.now();
    }
}
