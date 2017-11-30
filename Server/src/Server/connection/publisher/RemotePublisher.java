package Server.connection.publisher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * RemotePublisher. Remote version of Publisher.
 */
public class RemotePublisher extends UnicastRemoteObject implements IRemotePublisherForDomain {

    // Local publisher
    Publisher publisher;
    
    /**
     * Default no-arg constructor for RemotePublisher.
     * 
     * @throws java.rmi.RemoteException
     */
    public RemotePublisher() throws RemoteException {
        publisher = new Publisher();
    }

    /**
     * Constructor for RemotePublisher. Property listeners may subscribe to given properties.
     *
     * @param properties
     * @throws java.rmi.RemoteException
     */
    public RemotePublisher(String[] properties) throws RemoteException {
        publisher = new Publisher(properties);
    }

    @Override
    public void registerProperty(String property) throws RemoteException {
        publisher.registerProperty(property);
    }

    @Override
    public void unregisterProperty(String property) throws RemoteException {
        publisher.unregisterProperty(property);
    }

    @Override
    public void inform(String property, Object oldValue, Object newValue)
            throws RemoteException {
        publisher.inform(property, oldValue, newValue);
    }

    @Override
    public List<String> getProperties() throws RemoteException {
        return publisher.getProperties();
    }
}
