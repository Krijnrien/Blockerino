package Server;

import Server.connection.CommandServer;

public class Main {

    public static void main(String args[]) {
        System.out.println("Listening for connections..."); //TODO add proper logging
        CommandServer commandServer = new CommandServer();
        Thread handshakeThread = new Thread(commandServer, "connectionThread");
        handshakeThread.start();
    }
}
