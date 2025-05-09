package Interfaces;

import Connection.Messages.Message;

import java.io.IOException;
import java.util.List;

public interface ControllerInterface {

    void reply(String user) throws IOException;

    void reply(String user, boolean b) throws IOException;

    void reply(String user, List<Message> messages) throws IOException;
}
