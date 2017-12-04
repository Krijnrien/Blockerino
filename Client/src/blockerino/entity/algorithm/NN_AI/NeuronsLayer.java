package blockerino.entity.algorithm.NN_AI;

import java.util.ArrayList;

/*
 * @Description: NeuronsLayer contains Neurons. It evaluates these neurons to give
 * out decision.
 */
class NeuronsLayer {
    private static final float BIAS = -1.0f;
    private int totalNeurons;
    // int totalInputs;
    private ArrayList<Neuron> neurons;

    /*
     * Evaluate the inputs from sensors or HiddenLayer and give out the output
     */
    void evaluate(ArrayList<Double> inputs, ArrayList<Double> outputs) {
        int inputIndex = 0;
        for (int i = 0; i < totalNeurons; i++) {
            float activation = 0.0f;
            int numOfInputs = neurons.get(i).numberOfInputs;
            Neuron neuron = neurons.get(i);
            // sum the weights up to numberOfInputs-1 and add the bias
            for (int j = 0; j < numOfInputs - 1; j++) {
                if (inputIndex < inputs.size()) {
                    activation += inputs.get(inputIndex)
                            * neuron.weights.get(j);
                    inputIndex++;
                }
            }
            // Add the bias.
            activation += neuron.weights.get(numOfInputs) * BIAS;
            outputs.add(HelperFunction.Sigmoid(activation, 1.0f));
            inputIndex = 0;
        }
    }

    void loadLayer(ArrayList<Neuron> neurons) {
        totalNeurons = neurons.size();
        this.neurons = neurons;
    }
}
