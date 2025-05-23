package Connection.Messages;

import Server.Controller;

import java.io.IOException;
import java.time.LocalDateTime;

/*
    0 LoginMessage
    1 ReplyMessage
    2 TextMessage
    3 GetChatRequestMessage
    4 ChatReplyMessage
 */

public abstract class Message {
    String sender;
    String recipient;
    String message;
    int id;

    public Message() {}

    public String getSender() { return sender; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public int getId() { return id; }

    public String getRecipient() { return recipient; }

    public void execute(Controller controller) throws IOException {}
}
