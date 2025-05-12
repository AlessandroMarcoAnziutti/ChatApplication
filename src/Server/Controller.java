package Server;

import Connection.Messages.*;
import Server.Model.User;
import Server.Model.UserStatus;

import java.io.IOException;
import java.util.*;

public class Controller {
    private Server server;

    public Controller(Server server) {
        this.server = server;
    }

    public void userManagement(String name, String password) throws IOException {
        User user = new User(name, password);
        if(!server.getUsers().contains(user)){
            server.getUsers().add(user);
            reply(user.getUsername());
        } else {
            if(isLoginCorrect(user)){
                user.setStatus(UserStatus.ONLINE);
                reply(user.getUsername());
            } else reply(user.getUsername(), true);
        }
    }

    public void chatRequestManagement(String name) throws IOException {
        User user = getUserFromUsers(name);
        if(user != null) { reply(user.getUsername(), user.getChat()); }
    }

    public void textMessageManagement(String ssender, String srecipient, String message) throws IOException {
        User sender = getUserFromUsers(ssender);
        if(sender != null) sender.receiveMessage(ssender, srecipient, message);
        User recipient = getUserFromUsers(srecipient);
        if(recipient != null) recipient.receiveMessage(ssender, srecipient, message);

        send(ssender, srecipient, message);
    }

    private Boolean isLoginCorrect(User user){ for(User us : server.getUsers()){ if(us.getUsername().equals(user.getUsername()) && us.getPassword().equals(user.getPassword())) return true; } return false; }
    private User getUserFromUsers(String username){ for(User us : server.getUsers()){ if(us.getUsername().equals(username)) return us; } return null; }

    public void reply(String user) throws IOException {
        ReplyMessage mex = new ReplyMessage("Server", user, "Login successful");
        server.sendMessage(mex);
    }

    public void reply(String user, boolean b) throws IOException {
        ReplyMessage mex = new ReplyMessage("Server", user, "Incorrect username or password");
        server.sendMessage(mex);
    }

    public void reply(String user, List<Message> messages) throws IOException {
        ChatReplyMessage mex = new ChatReplyMessage("Server", user, messages);
        server.sendMessage(mex);
    }

    public void send(String sender, String recipient, String message) throws IOException {
        TextMessage mex = new TextMessage(sender, recipient, message);
        server.sendMessage(mex);
    }
}
