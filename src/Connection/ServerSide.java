package Connection;
import Interfaces.ServerInterface;
import Server.Controller;
import Connection.Messages.Message;
import Server.MessageHandler;

import java.net.*;
import java.io.*;

public class ServerSide implements ServerInterface {
    private static ServerSide instance;
    private final static int port = 8954;
    private static Serializer serializer;
    private static Deserializer deserializer;
    private static ObjectOutputStream out;
    private static DataInputStream in;

    private ServerSide() throws IOException {}

    public static ServerSide getInstance() throws IOException {
        if(instance == null) { instance = new ServerSide(); }
        return instance;
    }

    public static void main(String[] args) throws IOException{
        System.out.println("Server starting...");
        serializer = new Serializer();
        deserializer = new Deserializer();
        ServerSocket ssocket = new ServerSocket(port);
        Socket socket = ssocket.accept();
        System.out.println("connected to " + socket.getInetAddress().getHostName());
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        Controller server = new Controller();

        System.out.println("Creating message handler");
        MessageHandler messageHandler = new MessageHandler(server);
        Thread messageHandlerThread = new Thread(messageHandler);
        messageHandlerThread.start();

        System.out.println("Waiting for messages...");
        while(true) {
            String message = in.readUTF();
            System.out.println("message: " + message);
            Message mex = deserializer.deserialize(message);
            System.out.println("message from " + mex.getSender());
            messageHandler.addMessage(mex);
        }
    }

    @Override
    public void sendMessage(Message mex) throws IOException {
        out.writeObject(serializer.serialize(mex));
    }
}
