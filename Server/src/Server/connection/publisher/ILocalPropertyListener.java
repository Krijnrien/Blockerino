package Server.connection.publisher;

import java.beans.PropertyChangeEvent;

/**
 * ILocalPropertyListener. Interface to local property listener in order to
 * inform the listener about changes in the domain of properties that the
 * listener is subscribed to. 
 */
public interface ILocalPropertyListener extends IPropertyListener  {

    /**
     * Inform listener about change of a property in the domain. On the basis
     * of the data provided by the instance of PropertyChangeEvent the observer
     * is synchronized with respect to the domain.
     */
    void propertyChange(PropertyChangeEvent evt);
}
