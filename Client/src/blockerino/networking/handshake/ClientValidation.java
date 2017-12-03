package blockerino.networking.handshake;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientValidation extends UnicastRemoteObject implements IClientValidation, Serializable {

    ClientValidation() throws RemoteException {
    }

    public void sendHandshake(Handshake _handshake) throws RemoteException {

    }
}
