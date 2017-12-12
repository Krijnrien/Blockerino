package networking;

import blockerino.auth.User;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RegisterNetwork {

    private String username;
    private String localIP;

    public RegisterNetwork() {
        this.username = User.username;
        setLocalIP();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP() {
        try {
            this.localIP = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
