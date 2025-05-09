package Connection.Messages;

public class TextMessage extends Message {
    public TextMessage(String sender, String recipient, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = body;
        this.id = 2;
    }
}
