package blockerino.entity.character;

import blockerino.entity.ControllableEntity;
import blockerino.entity.algorithm.NN_AI.GeneticAlgorithm;
import blockerino.entity.algorithm.NN_AI.NeuralNetwork;
import blockerino.graphics.Circle;
import blockerino.graphics.Sprite;
import blockerino.resources.Texture;
import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.util.Vector2f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static blockerino.entity.character.AIManager.HIDDEN_LAYER_NEURONS;
import static blockerino.entity.character.AIManager.MAX_GENOME_POPULATION;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AICharacter extends ControllableEntity {

	//region Class variables
	protected String name;

	//endregion

	//region AINN variables
	public static final int NN_OUTPUT_COUNT = 2;

	public static enum NeuralNetOuputs {
		NN_OUTPUT_JUMP, NN_OUTPUT_DUCK,
	}

	static int FEELER_COUNT = 4;

	// fitness is used to measure how far the car could go without collision
	double currentAgentFitness;
	private NeuralNetwork neuralNet;
	private GeneticAlgorithm genAlg;
	public boolean dead;
	//endregion

	public AICharacter() {
		int totalWeights = FEELER_COUNT * HIDDEN_LAYER_NEURONS + HIDDEN_LAYER_NEURONS * NN_OUTPUT_COUNT + HIDDEN_LAYER_NEURONS + NN_OUTPUT_COUNT;

		currentAgentFitness = 0.0;

		genAlg = new GeneticAlgorithm();
		genAlg.generateNewPopulation(MAX_GENOME_POPULATION, totalWeights);

		neuralNet = new NeuralNetwork();
		neuralNet.fromGenome(genAlg.getNextGenome(), FEELER_COUNT, HIDDEN_LAYER_NEURONS, NN_OUTPUT_COUNT);
	}

	private void move() {
		testCollision();
		//apply gravity
		right = true;
		if(dy < 1f) {
			dy += 0.02f;
		}

		if(!topCollision.isColliding()) {
			if(up) {
				if(bottomCollision.isColliding()) {
					dy = -0.5f;
				}
			}
		} else {
			if(dy < 0) {
				dy = 0;
			}
		}

		if(bottomCollision.isColliding()) {
			if(dy > 0) {
				dy = 0;
			}
		}

		if(!leftCollision.isColliding()) {
			if(left) {
				dx -= acceleration;
				if(dx < -maxSpeed) {
					dx = -maxSpeed;
				}
			} else {
				if(dx < 0) {
					dx += deceleration;
					if(dx > 0) {
						dx = 0;
					}
				}
			}

			dx += velocity.x;

		} else {
			dx = 0;
		}

		if(!rightCollision.isColliding()) {
			if(right) {
				dx += acceleration;
				if(dx > maxSpeed) {
					dx = maxSpeed;
				}
			} else {
				if(dx > 0) {
					dx -= deceleration;
					if(dx < 0) {
						dx = 0;
					}
				}
			}

			dx += velocity.x;

		} else {
			if(dx > 0) {
				dx = 0;
			}
		}

		//TODO Update Y position cause gravity
	}

	public void update() {
		super.update();

		move();

		position.x += dx; // get player X position
		position.y += dy; // get player Y position

		updateCollisions();
	}

	@Override
	public void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
		Sprite s = new Sprite(new Texture(animation.getImage()), position, scale);
		s.render(_graphics2D, _projectionViewMatrix);

		Circle circle = new Circle(_graphics2D, new Vector2f(1, 1));
		circle.renderCircle(position.x, position.y, 10, Color.BLUE);
	}

	public void input(MouseHandler _mouse, KeyHandler _key) {
		up = _key.up.down;
		down = _key.down.down;
		left = _key.left.down;
		right = _key.right.down;

		primaryUse = _mouse.button1.down;
		secondaryUse = _mouse.button2.down;
	}

}
