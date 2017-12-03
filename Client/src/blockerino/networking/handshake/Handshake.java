package blockerino.networking.handshake;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Handshake implements IHandshake {

    String username;
    InetAddress localIP;

    public Handshake(String _username) {
        this.username = _username;
        setLocalIP();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getLocalIP() {
        return localIP;
    }

    public void setLocalIP() {
        try {
            this.localIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
