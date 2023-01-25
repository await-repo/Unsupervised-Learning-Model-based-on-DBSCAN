package org.UnsupervisedLearningModelDBSCAN.GeneticAlgorithm;

//Es el individuo de una poblacion
//Por ejemplo : Una poblacion de tamaÑo 50 tiene 50 individuos
public class Individual {

    protected int defaultGeneLength = 80;
    private byte[] genes = new byte[defaultGeneLength];
    private int fitness = 0;


    //Genera una secuencia de bits aleatoria para el nuevo individuo
    public Individual() {
        for (int i = 0; i < genes.length; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    //Obtiene el bit dentro del individuo dado un indice
    protected byte getSingleGene(int index) {
        return genes[index];
    }

    //Se introduce un bit dentro de algun indice del gen en el individuo
    //Dato un indice y un valor (byte) pasados como parametro
    protected void setSingleGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    //Obtiene la funcion fintnss
    public int getFitness() {
        if (fitness == 0) {
            fitness = SimpleGeneticAlgorithm.getFitness(this);
        }
        return fitness;
    }


    public int gettingFitness() {
        return fitness;
    }


    //Retorna el tamaño del individuo
    public int getDefaultGeneLength() {
        return defaultGeneLength;
    }

    //Retorna los genes del individuo
    public byte[] getGenes() {
        return genes;
    }


    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < genes.length; i++) {
            geneString += getSingleGene(i);
        }
        //geneString += "     " + fitness;
        return geneString;
    }

}