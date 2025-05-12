package Client;

import Connection.Deserializer;
import Connection.Messages.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMessageHandler implements Runnable {
    private Client client;
    private DataInputStream in;
    private Deserializer deserializer;

    public ClientMessageHandler(Client client, Socket socket) throws IOException {
        this.client = client;
        deserializer = new Deserializer();
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("Arriving message manager started");
        while (true) {
            try {
                Message message = deserializer.deserialize(in.readUTF());
                client.addMessage(message);
                System.out.println("New message received from: " + message.getSender() + "\n" + message.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
