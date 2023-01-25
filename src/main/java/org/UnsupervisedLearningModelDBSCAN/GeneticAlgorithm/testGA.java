package org.UnsupervisedLearningModelDBSCAN.GeneticAlgorithm;

public class testGA {
    public static void main (String []args) throws Exception {

        SimpleGeneticAlgorithm GA = new SimpleGeneticAlgorithm(0.5,0.025,5,true);

        GA.runAlgorithm(10, 2);
    }
}
