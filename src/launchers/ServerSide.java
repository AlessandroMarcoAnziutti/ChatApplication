package launchers;

import Server.Server;

import java.io.*;

public class ServerSide {
    private static Server server;

    public static void main(String[] args) throws IOException{
        System.out.println("Server starting...");
        server = new Server();
        server.initServer();
    }
}
