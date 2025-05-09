package Server.Model;

import Connection.Messages.Message;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final String password;
    private List<Message> chat = new ArrayList<>();
    private UserStatus status;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        status = UserStatus.ONLINE;
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

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void receiveMessage(Message message) {
        chat.add(message);
    }
}
