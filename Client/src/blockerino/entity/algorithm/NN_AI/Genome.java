package blockerino.entity.algorithm.NN_AI;

import java.util.ArrayList;

/**
 * Genome object.
 * Keeping track of fitness of genome and sigmoid weight value.
 */
public class Genome {
    /**
     * Id to identify genome
     */
    public int ID;


    /**
     * Fitness to determine progress of genome.
     */
    public double fitness;


    /**
     * Sigmoid weight of genome
     */
    public ArrayList<Double> weights;
}
