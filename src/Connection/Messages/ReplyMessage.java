package Connection.Messages;

import java.time.LocalDateTime;

public class ReplyMessage extends Message {
    public ReplyMessage(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.id = 1;
    }
}
