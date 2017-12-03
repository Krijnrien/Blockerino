package Server.connection.handshake;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientValidation extends Remote {
    void sendHandshake(Handshake _handshake) throws RemoteException;
}
