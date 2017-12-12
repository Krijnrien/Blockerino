package Server.connection;

import Server.ConfigProperties;
import Server.util.FileHandler;
import Server.util.ListHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class IncomingConnectionHandler implements Runnable {
    public static boolean listening;
    private static final int PORT = 1978;

    private List<String> whitelistIps;
    private List<String> whitelistNames;
    private List<String> blacklistIps;
    private List<String> blacklistNames;

    private List<String> connectedClients; //TODO Ip list of current connected clients to restrict amount of players on server. if(blacklistIps.contains(socket.getRemoteSocketAddress().toString())){ }

    public IncomingConnectionHandler() {

        blacklistIps = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered(ConfigProperties.resource + "blacklistIps.txt"));
        blacklistNames = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered(ConfigProperties.resource + "blacklistNames.txt"));
        blacklistNames = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered(ConfigProperties.resource + "whitelistIps.txt"));
        whitelistNames = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered(ConfigProperties.resource + "whitelistNames.txt"));
    }

    @Override
    public void run() {
        try {
            Socket socket = null;
            //TODO Fix parsing int .toString()
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(ConfigProperties.properties.get("port").toString()));
            listening = true;
            while (listening) {
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
                //	socket.getRemoteSocketAddress()

                // new thread for a client
                new ServerClient(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
