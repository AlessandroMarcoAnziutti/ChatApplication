package launchers;
import Client.Client;

import java.io.*;

public class ClientSide {
    private static Client client = new Client();


    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to Chat Client");
        client.initClient();
    }
}