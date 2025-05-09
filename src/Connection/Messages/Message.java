package Connection.Messages;

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
    LocalDateTime timestamp;

    public Message() {}

    public String getSender() { return sender; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public int getId() { return id; }

    public String getRecipient() { return recipient; }

    public LocalDateTime getTimestamp() { return timestamp; }
}
