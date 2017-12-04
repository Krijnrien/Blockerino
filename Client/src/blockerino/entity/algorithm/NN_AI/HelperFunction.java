package blockerino.entity.algorithm.NN_AI;

import java.util.Random;

/*
 * Description: Global helper functions
 */
class HelperFunction {
	/*
	 * normalize value to make it from 1 to -1
	 */
	static double Sigmoid(float a, float p) {
		float ap = (-a) / p;
		return (1 / (1 + Math.exp(ap)));
	}

	/*
	 * random number from -1 to 1;
	 */
	static double RandomSigmoid() {
		Random ran = new Random(System.nanoTime());
		return ran.nextDouble() - ran.nextDouble();
	}
}
