package blockerino;

import java.util.*;


public class Commands {
    //TODO Hashmap with functions equaling command
    //TODO Maybe place connectedUsers list here?

    Map<Character, Runnable> clientCommands = new HashMap<>();
    List list = Collections.synchronizedList(new ArrayList());


    private Commands() {
    }

    private static class InstanceHolder {
        private static final Commands instance = new Commands();
    }

    public static Commands getInstance() {
        return InstanceHolder.instance;
    }
}