package blockerino.states;

import blockerino.Window;
import blockerino.entity.character.Player;
import blockerino.graphics.Sprite;
import blockerino.graphics.UI.GameUI;
import blockerino.resources.ResourceHandler;
import blockerino.util.*;
import blockerino.world.Camera2D;
import blockerino.world.World;
import blockerino.world.generation.NoiseGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PlayState extends GameState {

    public static World world;
    private Player player;
    private Camera2D camera;
    private GameUI gameUI;

    private AffineTransform projectionMatrix;
    private AffineTransform projectionViewMatrix;

    PlayState(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        NoiseGenerator worldGen = new NoiseGenerator(1337);
        worldGen.setAmplitude(32);
        worldGen.setFrequency(16);
        worldGen.setAverageHeight(30);

        world = new World(16, worldGen);
        gameUI = new GameUI();


//        ContainerItem containerItem = new ContainerItem();
//        containerItem.setId(1);
//        containerItem.setName("TravelersBeltpouch");
//        containerItem.setType(Item.ItemTypeEnum.CONTAINER);
//        containerItem.setRarity(Item.ItemRarityEnum.BASIC);
//        containerItem.setStackSize(1);
//        containerItem.setDescription("a beltpouch for travelers");
//        containerItem.setIcon(ResourceHandler.getLoadedTexture("stone"));
//        containerItem.setTexture(ResourceHandler.getLoadedTexture("stone"));
//        containerItem.setSize(20);
//
//        try {
//            File file = new File("bag.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(ContainerItem.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            // output pretty printed
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(containerItem, file);
//            jaxbMarshaller.marshal(containerItem, System.out);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("reached!");
//        System.out.println(containerItem);

        try {
            JAXBContext context = JAXBContext.newInstance(Player.class);
            Unmarshaller um = context.createUnmarshaller();
            player = (Player) um.unmarshal(new FileReader("Client/res/entity/player.xml"));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            //TODO Proper error handling
        }


        player.setSprite(new Sprite(ResourceHandler.getLoadedTexture(player.getTextureName()), new Vector2f(0, 0), new Vector2f(1, 1)));
        player.setPosition(new Vector2f(0, 0));
        player.setScale(new Vector2f(3, 3));

        player.setCollissions();
        player.setBothBounds();
        player.attachAnimation();

        projectionMatrix = new AffineTransform();
        updateProjectionMatrix();

        camera = new Camera2D();
        camera.setScaleValue(50);
        camera.setTarget(player);
        camera.updateViewMatrixWidthOnly();

        projectionViewMatrix = new AffineTransform(projectionMatrix);
        updateProjectionViewMatrix();
        //camera.setPosition(player.getPosition());
    }

    public void update() {
        player.update();

        gameUI.update();

        camera.updateViewMatrixWidthOnly();
        updateProjectionViewMatrix();

        //Generate chunks around player if needed
        world.generateChunksRadius(player.getPosition());
    }

    public void updateProjectionMatrix() {
        projectionMatrix = new AffineTransform();
        projectionMatrix.translate((double) Window.width / 2, (double) Window.height / 2);
    }

    public void updateProjectionViewMatrix() {
        //Combine projection matrix with camera matrix
        projectionViewMatrix = new AffineTransform(projectionMatrix);
        projectionViewMatrix.concatenate(camera.getViewMatrix());
    }

    public void input(MouseHandler _mouse, KeyHandler _key) {
        player.input(_mouse, _key);
        gameUI.input(_key);
    }

    public void render(Graphics2D _graphics2D) {
        world.render(_graphics2D, projectionViewMatrix);
        player.render(_graphics2D, projectionViewMatrix);

        world.renderCollision(_graphics2D, projectionViewMatrix);
        player.renderCollision(_graphics2D, projectionViewMatrix);
    }

    public Camera2D getCamera() {
        return camera;
    }
}
