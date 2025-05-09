package Client.ModelLight;

import Connection.Messages.Message;
import Server.Model.UserStatus;

import java.util.ArrayList;
import java.util.List;

public class UserLight {
    private String username;
    private String password;
    private List<Message> chat = new ArrayList<>();
    private UserStatus status;

    public UserLight() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Message> getChat() {
        return chat;
    }

    public void setChat(List<Message> chat) {
        this.chat = chat;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
