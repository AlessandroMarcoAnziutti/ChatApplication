package Server;

import Connection.Messages.Message;
import Connection.Serializer;
import Server.Model.User;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server implements Serializable {
    private List<Message> messages = new ArrayList<>();
    private int port = 8954;
    private Serializer serializer;
    private ObjectOutputStream out;
    private DataInputStream in;
    private ServerSocket ssocket;
    private List<User> users = new ArrayList<>();
    private Map<Socket, String> sockets = new HashMap<>();
    private Controller controller;

    public void sendMessage(Message mex) throws IOException {
        out.writeObject(serializer.serialize(mex));
    }

    public void clientConnectionManagement() {
        System.out.println("Client connnection management started");
        while (true) {
            try {
                Socket socket = ssocket.accept();
                System.out.println("connected to " + socket.getInetAddress().getHostName());
                out = new ObjectOutputStream(socket.getOutputStream());
                sockets.put(socket, null);
                MessageHandler messageHandler = new MessageHandler(this, socket);
                messageHandler.run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clientMessageManagement() {
        System.out.println("Client message management started");
        while (true) {
            if(!messages.isEmpty()) {
                Message message = getMessage();
                try {
                    message.execute(controller);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void enqueueMessage(Message mex) {
        messages.add(mex);
    }

    public Message getMessage() {
        return messages.removeFirst();
    }

    public Map<Socket, String> getSockets() {
        return sockets;
    }

    public List<User> getUsers() {
        return users;
    }

    public void initServer() throws IOException {
        serializer = new Serializer();
        ssocket = new ServerSocket(port);
        controller = new Controller(this);

        Thread clientHandler = new Thread(this::clientConnectionManagement);
        Thread messageHandler = new Thread(this::clientMessageManagement);
        clientHandler.start();
        messageHandler.start();
    }
}
