package Server;

import Connection.Messages.*;
import Connection.ServerSide;
import Interfaces.ControllerInterface;
import Server.Model.User;
import Server.Model.UserStatus;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.*;

public class Controller implements ControllerInterface {
    private List<User> users;

    public Controller() {
        users = new ArrayList<>();
    }

    public void userManagement(LoginMessage message) throws IOException {
        User user = new User(message.getSender(), message.getMessage());
        if(!users.contains(user)){
            users.add(user);
            reply(user.getUsername());
        } else {
            if(isLoginCorrect(user)){
                user.setStatus(UserStatus.ONLINE);
                reply(user.getUsername());
            } else reply(user.getUsername(), true);
        }
    }

    public void chatRequestManagement(GetChatRequestMessage message) throws IOException {
        User user = getUserFromUsers(message.getSender());
        if(user != null) { reply(user.getUsername(), user.getChat()); }
    }

    public void textMessageManagement(TextMessage message) throws IOException {
        User sender = getUserFromUsers(message.getSender());
        if(sender != null) sender.receiveMessage(message);
        User recipient = getUserFromUsers(message.getRecipient());
        if(recipient != null) recipient.receiveMessage(message);

        reply(message);
    }

    private Boolean isLoginCorrect(User user){ for(User us : users){ if(us.getUsername().equals(user.getUsername()) && us.getPassword().equals(user.getPassword())) return true; } return false; }
    private User getUserFromUsers(String username){ for(User us : users){ if(us.getUsername().equals(username)) return us; } return null; }

    @Override
    public void reply(String user) throws IOException {
        ServerSide serverSide = ServerSide.getInstance();
        ReplyMessage mex = new ReplyMessage("Server", user, "Login successful");
        serverSide.sendMessage(mex);
    }

    @Override
    public void reply(String user, boolean b) throws IOException {
        ServerSide serverSide = ServerSide.getInstance();
        ReplyMessage mex = new ReplyMessage("Server", user, "Incorrect username or password");
        serverSide.sendMessage(mex);
    }

    @Override
    public void reply(String user, List<Message> messages) throws IOException {
        ServerSide serverSide = ServerSide.getInstance();
        ChatReplyMessage mex = new ChatReplyMessage("Server", user, messages);
        serverSide.sendMessage(mex);
    }

    public void reply(TextMessage message) throws IOException {
        ServerSide serverSide = ServerSide.getInstance();
        serverSide.sendMessage(message);
    }
}
