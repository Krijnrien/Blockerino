package Server.connection;

import Server.ConfigProperties;
import Server.game.GameManager;
import Server.game.entity.character.Player;
import Server.game.util.Vector2f;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import networking.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class CommandServer implements Runnable {
    Server server;
    //HashSet<RegisterNetwork> connectedClients = new HashSet();

    Connection connection;
    GameManager gameManager;

    public CommandServer() {
        gameManager = new GameManager();
    }

    @Override
    public void run() {
        //   gameLoop();
        server = new Server();

        // For consistency, the classes to be sent over the network are registered by the same method for both the client and server.
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection _connection, Object _object) {
                if (_object instanceof RegisterNetwork) {
                    RegisterNetwork registerNetwork = (RegisterNetwork) _object;
                    // Adding client connection to connectClient list. Hashset can only contain unique values and will discard duplicates from being added.
                    // Aka only unique connections at all times exist in this list
                    // connectedClients.add(registerNetwork);
                    try {
                        JAXBContext context = JAXBContext.newInstance(Player.class);
                        Unmarshaller um = context.createUnmarshaller();
                        //TODO Load player save file, not just player properties but also its equipment etc
                        Player player = (Player) um.unmarshal(new FileReader("Server/res/entity/player.xml"));

                        player.setPosition(new Vector2f(0, 0));
                        player.setScale(new Vector2f(3, 3));

                        player.setCollissions();
                        player.setBothBounds();
                        gameManager.players.add(player);

                        ChunksNetwork chunksNetwork = new ChunksNetwork();
                        chunksNetwork.loadedChunks = GameManager.world.loadedChunks;
                        server.sendToTCP(_connection.getID(), chunksNetwork);

                        PlayerNetwork playerNetwork = new PlayerNetwork();
                        playerNetwork.position = gameManager.player.getPosition();
                        server.sendToTCP(_connection.getID(), playerNetwork);

                        //TODO Return player obj and world

                    } catch (JAXBException | FileNotFoundException e) {
                        e.printStackTrace();
                        //TODO Proper error handling
                    }
                    //TODO Add character to player list
                    //TODO Return world object to player
                    System.out.println(registerNetwork.getUsername());
                }
                if (_object instanceof ClientInputNetwork) {
                    ClientInputNetwork clientInputNetwork = (ClientInputNetwork) _object;
                    connection = _connection;
                    update(clientInputNetwork);
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

    private void gameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //      update();
            }
        }, 0, 100);
        stop();
    }


    private void input() {
        //gameStateManager.input(_mouse, _key);
    }

    private void update(ClientInputNetwork clientInputNetwork) {
        gameManager.update(clientInputNetwork);

        ChunksNetwork chunksNetwork = new ChunksNetwork();
        chunksNetwork.loadedChunks = GameManager.world.loadedChunks;
        server.sendToTCP(connection.getID(), chunksNetwork);

        PlayerNetwork playerNetwork = new PlayerNetwork();
        playerNetwork.position = gameManager.player.getPosition();
        server.sendToTCP(connection.getID(), playerNetwork);
    }

    private synchronized void stop() {
        System.out.println("Goodbye");
        //TODO thread join
    }

}