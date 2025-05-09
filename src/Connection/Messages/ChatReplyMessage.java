package Connection.Messages;

import java.time.LocalDateTime;
import java.util.List;

public class ChatReplyMessage extends Message {
    private List<Message> messages;

    public ChatReplyMessage(String sender, String recipient, List<Message> messages) {
        this.sender = sender;
        this.recipient = recipient;
        this.messages = messages;
        this.id = 4;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
