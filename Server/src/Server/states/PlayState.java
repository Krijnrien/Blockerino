package Server.states;

import Server.entity.character.Player;
import Server.util.KeyHandler;
import Server.util.MouseHandler;
import Server.util.Vector2f;
import Server.world.World;
import Server.world.generation.NoiseGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PlayState {

    public static World world;
    private Player player;

    PlayState(){
        NoiseGenerator worldGen = new NoiseGenerator(1337);
        worldGen.setAmplitude(32);
        worldGen.setFrequency(16);
        worldGen.setAverageHeight(30);

        world = new World(16, worldGen);

        try {
            JAXBContext context = JAXBContext.newInstance(Player.class);
            Unmarshaller um = context.createUnmarshaller();
            player = (Player) um.unmarshal(new FileReader("client/res/entity/player.xml"));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            //TODO Proper error handling
        }

        player.setPosition(new Vector2f(0, 0));
        player.setScale(new Vector2f(3, 3));

        player.setCollissions();
        player.setBothBounds();
    }

    public void update() {
        player.update();
        player.updateCollisions();
        player.testCollision();

        //Generate chunks around player if needed
        world.generateChunksRadius(player.getPosition());
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        player.input(_mouse, _key);
    }
}
