package Server.Model;

import Connection.Messages.Message;
import Connection.Messages.TextMessage;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Message> chat = new ArrayList<>();
    private UserStatus status;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        status = UserStatus.ONLINE;
    }

    public User() {
        this.username = "";
        this.password = "";
        status = UserStatus.LOGIN;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public List<Message> getChat() {
        return chat;
    }
    public UserStatus getStatus() {
        return status;
    }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void receiveMessage(String sender, String recipient, String message) {
        TextMessage mex = new TextMessage(sender, recipient, message);
        chat.add(mex);
    }
}
