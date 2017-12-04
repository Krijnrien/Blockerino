package blockerino.entity.character;

import blockerino.entity.algorithm.NN_AI.Genome;

import java.util.List;

import static blockerino.entity.character.AICharacter.NN_OUTPUT_COUNT;

public class AIManager {


	public static final int HIDDEN_LAYER_NEURONS = 8;
	public static final int MAX_GENOME_POPULATION = 20;

	private List<AICharacter> AICharacters;

	public AIManager() {
		for(int i = 0; i < 20; i++) {
			AICharacters.add(new AICharacter());
		}
	}

	public void update() {
		int deadCount = 0;
		for(AICharacter AICharacter : AICharacters) {
			if(AICharacter.dead) {
				deadCount++;
			} else {
				AICharacter.update();
			}
		}

		if(deadCount >= 20) {
			evolveGenomes();
			AICharacters.clear();
		}
	}


	private List<AICharacter> newGeneration(){
		for(int i = 0; i < 20; i++) {
			AICharacters.add(new AICharacter());
		}
	}

	/**
	 * Generate new test subject after AI fails. The new genome will be fed
	 * in for neural network.
	 */
	private void nextTestSubject() {
		genAlg.setGenomeFitness(currentAgentFitness, genAlg.getCurrentGenomeIndex());
		currentAgentFitness = 0;
		Genome genome = genAlg.getNextGenome();
		neuralNet.fromGenome(genome, Car.FEELER_COUNT, HIDDEN_LAYER_NEURONS, NN_OUTPUT_COUNT);
		car = new Car((int) DEFAULT_POSITION.getX(), (int) DEFAULT_POSITION.getY(), DEFAULT_ROTATION, raceMap);
		car.attach(neuralNet);
		car.clearFailure();
	}


	/**
	 * Make new population after the old population failed to help the car finishing the track.
	 */
	private void evolveGenomes() {
		genAlg.breedPopulation();
		nextTestSubject();
	}

}
