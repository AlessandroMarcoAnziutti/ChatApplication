package Server;

import Connection.Deserializer;
import Connection.Messages.Message;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler implements Runnable {
    private Server server;
    private Socket socket;

    public MessageHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
    }

    public void run(){
        System.out.println("Created a message handler for: " + socket.getInetAddress().getHostName());
        Deserializer deserializer = new Deserializer();
        DataInputStream in;
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            while(true) {
                Message message;
                try {
                    message = deserializer.deserialize(in.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("message: " + message);
                System.out.println("message from " + message.getSender());
                server.enqueueMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
