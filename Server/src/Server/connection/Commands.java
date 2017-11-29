package Server.connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Commands {
    //TODO Hashmap with functions equaling command
    //TODO Maybe place connectedUsers list here?
    List list = Collections.synchronizedList(new ArrayList());

    private Commands() {}

    private static class InstanceHolder {
        private static final Commands instance = new Commands();
    }

    public static Commands getInstance() {
        return InstanceHolder.instance;
    }
}