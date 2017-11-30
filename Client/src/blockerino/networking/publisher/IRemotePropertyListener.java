package blockerino.networking.publisher;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * IRemotePropertyListener. Interface to remote property listener in order to
 * inform the listener about changes in the domain of properties that the
 * listener is subscribed to. 
 */
public interface IRemotePropertyListener extends IPropertyListener, Remote {

    /**
     * Inform listener about change of a property in the domain. On the basis
     * of the data provided by the instance of PropertyChangeEvent the observer
     * is synchronized with respect to the remote domain.
     * 
     * @throws RemoteException
     */
    void propertyChange(PropertyChangeEvent evt) throws RemoteException;
}
