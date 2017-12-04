package blockerino.entity.algorithm.NN_AI;

import java.util.ArrayList;

/**
 * Neuron object for neural network.
 */
class Neuron {
    /**
     * Number of inputs of neuron.
     */
    int numberOfInputs;

    /**
     * Sigmoid weight of neuron.
     */
    ArrayList<Double> weights;

    /**
     * Construct neuron by weight and number of inputs.
     *
     * @param _weights        Sigmoid weight of neuron.
     * @param _numberOfInputs Amount of inputs for neuron.
     */
    void init(ArrayList<Double> _weights, int _numberOfInputs) {
        this.numberOfInputs = _numberOfInputs;
        weights = _weights;
    }
}
