package Connection;
import Client.ClientController;
import Client.ModelLight.UserLight;
import Connection.Messages.Message;
import Server.MessageHandler;

import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientSide {
    private static ClientSide instance;
    private static Serializer serializer;
    private static Deserializer deserializer;
    private static ObjectOutputStream out;
    private static DataInputStream in;

    private ClientSide(){}

    public static ClientSide getInstance() {
        if(instance == null) { instance = new ClientSide(); }
        return instance;
    }
    private static int port = 8954;

    public static void main(String[] args) throws IOException{
        InetAddress host = InetAddress.getLocalHost();
        serializer = new Serializer();
        deserializer = new Deserializer();

        Socket socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        ClientController clientController = new ClientController();
        UserLight userLight = new UserLight();
        Scanner input = new Scanner(System.in);

        while(true){
            String message = input.nextLine();
            List<String> messageSplit = Arrays.asList(message.split(" "));
            switch(messageSplit.getFirst()){
                case "send": if(messageSplit.size() == 2){ clientController.textMessageManager(messageSplit); } break;
                case "chat": if(messageSplit.size() == 1){ clientController.chatRequestManager(); } break;
                case "login": if(messageSplit.size() == 3){ clientController.loginManager(messageSplit); } break;
                case "show-chat": if(messageSplit.size() == 1){ if(!userLight.getChat().isEmpty()) printChat(userLight.getChat());} break;
                default: System.out.println("Invalid message"); break;
            }
        }
    }

    public void sendMessage(Message message) throws IOException {
        out.writeObject(serializer.serialize(message));
    }

    private static void printChat(List<Message> messages) { for(Message message : messages){ System.out.println("<" + message.getTimestamp() + "> " + message.getSender() + " : " + message.getMessage() + "\n"); } }
}