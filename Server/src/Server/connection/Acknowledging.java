package Server.connection;

import Server.ConfigProperties;
import Server.connection.handshake.ClientValidation;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Acknowledging implements Runnable {

    @Override
    public void run() {
        try {
            ClientValidation clientValidation = new ClientValidation();
            Registry registry = LocateRegistry.createRegistry((int) ConfigProperties.properties.get("port"));
            registry.rebind("handshake", clientValidation);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
