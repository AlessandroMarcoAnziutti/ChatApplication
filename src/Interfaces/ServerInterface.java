package Interfaces;

import Connection.Messages.Message;

import java.io.IOException;

public interface ServerInterface {
    void sendMessage(Message mex) throws IOException;
}
