package Server;

import Connection.Messages.GetChatRequestMessage;
import Connection.Messages.LoginMessage;
import Connection.Messages.Message;
import Connection.Messages.TextMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler implements Runnable {
    private List<Message> messages;
    private Controller controller;

    public MessageHandler(Controller controller){
        messages = new ArrayList<>();
        this.controller = controller;
    }

    public void run(){
        while(true){
            if(!messages.isEmpty()){
                Message message = getMessage();
                switch(message.getId()){
                    case 0:
                        try { controller.userManagement((LoginMessage) message); }
                        catch (IOException e) { throw new RuntimeException(e); }
                    case 2:
                        try { controller.textMessageManagement((TextMessage) message); }
                        catch (IOException e) { throw new RuntimeException(e); }
                    case 3:
                        try { controller.chatRequestManagement((GetChatRequestMessage) message); }
                        catch (IOException e) { throw new RuntimeException(e); }
                }
            }
        }
    }

    public synchronized void addMessage(Message message){ messages.add(message); }

    private synchronized Message getMessage(){ return messages.removeFirst(); }
}
