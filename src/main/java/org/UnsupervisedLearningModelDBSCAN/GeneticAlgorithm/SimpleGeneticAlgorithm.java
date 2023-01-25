package org.UnsupervisedLearningModelDBSCAN.GeneticAlgorithm;

public class SimpleGeneticAlgorithm {

    private double uniformRate;
    private double mutationRate;
    private int tournamentSize;
    private boolean elitism;

    public SimpleGeneticAlgorithm() {}

    //Intancia donde se inicializan los parametros del algoritmo genetico
    public SimpleGeneticAlgorithm(double uniformRate, double mutationRate, int tournamentSize, boolean elitism) {
        this.uniformRate = uniformRate;
        this.mutationRate = mutationRate;
        this.tournamentSize = tournamentSize;
        this.elitism = elitism;
    }


    //Ejecutar el algoritmo genetico recibiendo el tamaño de poblacion y las iteraciones que se haran
    public boolean runAlgorithm(int populationSize, int iterations) throws Exception {
        //Creamos a la Nueva Poblacion
        Population myPop = new Population(populationSize, true);

        for ( int generationCount = 1 ; generationCount <= iterations ; generationCount++ ) {
            System.out.println("Iteracion : " + generationCount + "");
            System.out.println("Almacenando la Generacion Acutal en un Archivo de Texto ...\n");

            //myPop.generateFitness(); //Genera los Valores Fitness de los Individuos

            myPop.parallelGenerateFitness(); //Genera el fitness de todos los Individuos de forma Paralela

            myPop.saveGeneration(generationCount,iterations,myPop.getFittest());
            myPop = evolvePopulation(myPop); //Evolucionamos a la Poblacion quedandonos con los mejores individuos
        }
        return true;
    }

    //Evolucionar a la Poblacion
    public Population evolvePopulation(Population pop) throws Exception {
        int elitismOffset;
        Population newPopulation = new Population(pop.getIndividuals().size(), false);

        if (elitism) {
            newPopulation.getIndividuals().add(0, pop.getFittest());
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < pop.getIndividuals().size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.getIndividuals().add(i, newIndiv);
        }

        //Añadir Factores de Mutacion
        for (int i = elitismOffset; i < newPopulation.getIndividuals().size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        //Devuelve la mejor Poblacion
        return newPopulation;
    }


    //Reglas de Cruce (Aleatorio)
    private Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        for (int i = 0; i < newSol.getDefaultGeneLength(); i++) {
            if (Math.random() <= uniformRate) {
                newSol.setSingleGene(i, indiv1.getSingleGene(i));
            } else {
                newSol.setSingleGene(i, indiv2.getSingleGene(i));
            }
        }
        return newSol;
    }

    //Condiciones de Mutacion (Aleatorio)
    private void mutate(Individual indiv) {
        for (int i = 0; i < indiv.getDefaultGeneLength(); i++) {
            if (Math.random() <= mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setSingleGene(i, gene);
            }
        }
    }

    //Seleccion por Ruleta (Ronda por Ruleta)
    private Individual tournamentSelection(Population pop) throws Exception {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getIndividuals().size());
            tournament.getIndividuals().add(i, pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }


    //En esta funcion fitness se debe llamar a dbscan
    //Dentro de este metodose debe mandar a llamar al codigo de dbscan
    protected static int getFitness(Individual individual)  {
        int fitness = 0;
        for (int i = 0; i < individual.getDefaultGeneLength(); i++) {
            if (individual.getSingleGene(i) == 1) {
                fitness++;
            }
        }
        return fitness;
    }



}