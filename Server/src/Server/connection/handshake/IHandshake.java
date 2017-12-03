package Server.connection.handshake;

import java.net.InetAddress;

public interface IHandshake {

    String getUsername();
    void setUsername(String username);

    InetAddress getLocalIP();
    void setLocalIP();
}
