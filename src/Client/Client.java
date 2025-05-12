package Client;

import Client.ModelLight.UserLight;
import Connection.Messages.Message;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    private List<Message> messages;
    private ClientController clientController;
    private DataOutputStream out;
    private InetAddress host;
    private UserLight userLight;

    public Client() {
        messages = new ArrayList<>();
    }

    private static void printChat(List<Message> messages) { for(Message message : messages){ System.out.println(message.getSender() + " : " + message.getMessage() + "\n"); } }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
        System.out.println("Message sent");
    }

    private void inputManager() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input manager started");
        try {
            while (true) {
                String message = input.nextLine();
                List<String> messageSplit = Arrays.asList(message.split(" "));
                switch (messageSplit.getFirst()) {
                    case "send":
                        if (messageSplit.size() == 3) {
                            clientController.textMessageManager(messageSplit);
                        } else System.out.println("Invalid message");
                        break;
                    case "chat":
                        if (messageSplit.size() == 1) {
                            clientController.chatRequestManager();
                        } else System.out.println("Invalid message");
                        break;
                    case "login":
                        if (messageSplit.size() == 3) {
                            clientController.loginManager(messageSplit);
                        } else System.out.println("Invalid message");
                        break;
                    case "show-chat":
                        if (messageSplit.size() == 1) {
                            if (!userLight.getChat().isEmpty()) printChat(userLight.getChat());
                        } else System.out.println("Invalid message");
                        break;
                    default:
                        System.out.println("Invalid message");
                        break;
                }
            }
        }catch (IOException e){ System.out.println("Invalid input"); }
    }

    public void addMessage(Message message) throws IOException {
        messages.add(message);
    }

    public void initClient() throws IOException {
        clientController = new ClientController(this);
        userLight = new UserLight();

        host = InetAddress.getLocalHost();
        Socket socket = new Socket(host, 8954);
        System.out.println("Connected to " + socket.getInetAddress().getHostName());
        out = new DataOutputStream(socket.getOutputStream());

        Thread thread = new Thread(this::inputManager);
        thread.start();
        ClientMessageHandler clientMessageHandler = new ClientMessageHandler(this, socket);
        clientMessageHandler.run();
    }
}
