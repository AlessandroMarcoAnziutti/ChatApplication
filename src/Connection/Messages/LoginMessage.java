package Connection.Messages;

import java.security.Timestamp;
import java.time.LocalDateTime;

public class LoginMessage extends Message {
    public LoginMessage(String sender, String password) {
        this.sender = sender;
        this.recipient = "Server";
        this.message = password;
        this.id = 0;
        timestamp = LocalDateTime.now();
    }
}
