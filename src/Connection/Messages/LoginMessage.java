package Connection.Messages;

import Server.Controller;
import java.io.IOException;
import java.net.Socket;

public class LoginMessage extends Message {

    public LoginMessage(String sender, String password) {
        this.sender = sender;
        this.recipient = "Server";
        this.message = password;
        this.id = 0;
    }

    public void execute(Controller controller) throws IOException {
        controller.userManagement(sender, message);
    }
}
