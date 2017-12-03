package Server.connection.handshake;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientValidation extends UnicastRemoteObject implements IClientValidation, Serializable {

    public ClientValidation() throws RemoteException {
    }

    public void sendHandshake(Handshake _handshake) throws RemoteException {
        //TODO Handle handshake
        //TODO Check if server has available player slots
        //TODO Check if player is banned (name and IP)
        //TODO Check if player has good enough internet speed??
        if (true) {
            System.out.println("user connecting: " + _handshake.username);
            //TODO Send world data update to new client
            //TODO Accept incoming player commands from new client
        }
    }
}
