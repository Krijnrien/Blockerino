package blockerino.states;

import blockerino.Window;
import blockerino.entity.character.AICharacter;
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
	private AICharacter AINN;
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

		try {
			JAXBContext context = JAXBContext.newInstance(Player.class);
			Unmarshaller um = context.createUnmarshaller();
			player = (Player) um.unmarshal(new FileReader("Client/res/entity/player.xml"));
		} catch(JAXBException | FileNotFoundException e) {
			e.printStackTrace();
			//TODO Proper error handling
		}


		player.setSprite(new Sprite(ResourceHandler.getLoadedTexture(player.getTextureName()), new Vector2f(0, 0), new Vector2f(1, 1)));
		player.setPosition(new Vector2f(0, 0));
		player.setScale(new Vector2f(3, 3));

		player.setCollissions();
		player.setBothBounds();
		player.attachAnimation();


		//region AI NN
		try {
			JAXBContext AIcontext = JAXBContext.newInstance(AICharacter.class);
			Unmarshaller AIum = AIcontext.createUnmarshaller();
			AINN = (AICharacter) AIum.unmarshal(new FileReader("Client/res/entity/aiCharacter.xml"));
		} catch(JAXBException | FileNotFoundException e) {
			e.printStackTrace();
			//TODO Proper error handling
		}


		AINN.setSprite(new Sprite(ResourceHandler.getLoadedTexture(AINN.getTextureName()), new Vector2f(0, 0), new Vector2f(1, 1)));
		AINN.setPosition(new Vector2f(-10, 0));
		AINN.setScale(new Vector2f(3, 3));

		AINN.setCollissions();
		AINN.setBothBounds();
		AINN.attachAnimation();
		//endregion


		projectionMatrix = new AffineTransform();
		updateProjectionMatrix();

		camera = new Camera2D();
		camera.setScaleValue(50);
		camera.setTarget(player);
		camera.updateViewMatrixWidthOnly();

		projectionViewMatrix = new AffineTransform(projectionMatrix);
		updateProjectionViewMatrix();
	}

	public void update() {
		player.update();
		AINN.update();

		gameUI.update();


		camera.updateViewMatrixWidthOnly();
		updateProjectionViewMatrix();

		//Generate chunks around player if needed
		world.generateChunksRadius(player.getPosition());
	}

	private void updateProjectionMatrix() {
		projectionMatrix = new AffineTransform();
		projectionMatrix.translate((double) Window.width / 2, (double) Window.height / 2);
	}

	private void updateProjectionViewMatrix() {
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
		AINN.render(_graphics2D, projectionViewMatrix);

		world.renderCollision(_graphics2D, projectionViewMatrix);
		player.renderCollision(_graphics2D, projectionViewMatrix);
		AINN.renderCollision(_graphics2D, projectionViewMatrix);
	}

	public Camera2D getCamera() {
		return camera;
	}
}
