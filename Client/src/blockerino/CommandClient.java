package blockerino;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import networking.*;

import java.io.IOException;

public class CommandClient implements Runnable {

    String addressIP;
    int addressPort;
    private Client client;

    public CommandClient(String _addressIP, int _addressPort) {
        this.addressIP = _addressIP;
        this.addressPort = _addressPort;
    }

    @Override
    public void run() {
        client = new Client();
//        client.start();
        new Thread(client).start();
        // For consistency, the classes to be sent over the network are registered by the same method for both the client and server.
        Network.register(client);

        // ThreadedListener runs the listener methods on a different thread.
        client.addListener(new Listener.ThreadedListener(new Listener() {
            public void connected(Connection connection) {
                System.out.println("sending register packet");
                RegisterNetwork register = new RegisterNetwork();
                client.sendTCP(register);
            }

            public void received(Connection _connection, Object _object) {
                if (_object instanceof RegisterRequiredNetwork) {
                    System.out.println("Server requested client registration");
                    RegisterNetwork register = new RegisterNetwork();
                    client.sendTCP(register);
                }

                if (_object instanceof ChunksNetwork) {
                    ChunksNetwork chunksNetwork = (ChunksNetwork) _object;

                }

                if (_object instanceof PlayerNetwork) {
                    PlayerNetwork playerNetwork = (PlayerNetwork) _object;
                }

            }

            public void disconnected(Connection connection) {
                System.out.println("Disconnected!");
            }
        }));


        try {
            client.connect(5000, "127.0.0.1", 32064, 32065);
            // Server communication after connection can go here, or in Listener#connected().
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
