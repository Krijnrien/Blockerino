package Server.game;

import Server.game.entity.character.Player;
import Server.game.util.Vector2f;
import Server.game.world.World;
import Server.game.world.generation.NoiseGenerator;
import networking.ClientInputNetwork;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    public static World world; //TODO All loaded chunks (note that unconnected chunks can be loaded due to players being far apart)
    // public HashMap<Integer, ClientInputNetwork> clientInput = new HashMap<Integer, ClientInputNetwork>();

    //public Player player; //TODO Should be list of connected players, something like above.

    public List<Player> players = new ArrayList<>();

    public GameManager() {
        NoiseGenerator worldGen = new NoiseGenerator(1337);
        worldGen.setAmplitude(32);
        worldGen.setFrequency(16);
        worldGen.setAverageHeight(30);

        world = new World(16, worldGen);
    }

    public void update(ClientInputNetwork clientInputNetwork) {
        for (Player player : players) {
            player.update(clientInputNetwork);
            player.updateCollisions();
            player.testCollision();

            //Generate chunks around player if needed
            world.generateChunksRadius(player.getPosition());
        }
    }

    public void input() {
        // player.input();
    }
}
