package Server.connection;

import Server.ConfigProperties;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import networking.Network;
import networking.RegisterNetwork;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommandServer implements Runnable {
    Server server;

    @Override
    public void run() {
        server = new Server();/* {
            protected Connection newConnection() {
                // By providing our own connection implementation, we can store perconnection state without a connection ID to state look up.
                return new CharacterConnection();
            }
        };*/

        // For consistency, the classes to be sent over the network are registered by the same method for both the client and server.
        // Network.register(server);

        Kryo kryo = server.getKryo();
        kryo.register(RegisterNetwork.class);

        server.addListener(new Listener() {
            @Override
            public void received(Connection _connection, Object _object) {
                if (_object instanceof RegisterNetwork) {
                    RegisterNetwork registerNetwork = (RegisterNetwork) _object;
                    System.out.println(registerNetwork.getUsername());
                }
            }

            public void disconnected(Connection _connection) {
//                CharacterConnection connection = (CharacterConnection) _connection;
//                if (connection.character != null) {
                System.out.println("disconnected!");
//                    loggedIn.remove(connection.character);
//
//                    RemoveCharacter removeCharacter = new RemoveCharacter();
//                    removeCharacter.id = connection.character.id;

                // server.sendToAllTCP(removeCharacter);
//                }
            }
        });
        try {
            server.bind(
                    Integer.parseInt(ConfigProperties.properties.getProperty("portTCP")),
                    Integer.parseInt(ConfigProperties.properties.getProperty("portUDP"))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();

    }
}