package org.UnsupervisedLearningModelDBSCAN.GeneticAlgorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

//El total de toda una poblacion por generacion
public class Population {

    //Lista de individuos dentro de la Poblacion
    private List<Individual> individuals;

    //Crear una Poblacion con un tamaño especifico
    public Population(int size, boolean createNew) {
        individuals = new ArrayList<>();
        if (createNew) {
            //Crear una nueva poblacion con el tamaño establecido
            createNewPopulation(size);
        }
    }

    //Obtiene a un individuo de la poblacion dado un indice
    protected Individual getIndividual(int index) {
        return individuals.get(index);
    }


    //Obtiene la funcion fitness de forma paralela
    public void parallelGenerateFitness () {
        individuals.parallelStream().forEach(Individual::getFitness);
    }



    //Genera todos los fitness de los Individuos
    public void generateFitness () {
        individuals.forEach(Individual::getFitness);
    }


    //Retorna el Fittest
    protected Individual getFittest() {

        //generateFitness();  //Generamos todos los Fitness de los individuos

        Individual fittest = individuals.get(0);
        for (int i = 0; i < individuals.size(); i++) {
            if (fittest.gettingFitness() <= getIndividual(i).gettingFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    //Metodo que crea la nueva Poblacion dado un tamaño de poblacion especifico
    private void createNewPopulation(int size) {
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual();
            individuals.add(i, newIndividual);
        }
    }

    //Retorna todos los componentes (Individuos) de una Poblacion
    public List<Individual> getIndividuals() {
        return individuals;
    }


    public void printPoblation (int iteration) {
        individuals.forEach(indv -> System.out.println(indv.toString()));
    }

    //Almacena los individuos de la Poblacion Actual en un archivo de texto
    public void saveGeneration(int generation, int maxIteration, Individual fittest) {
        String route = "Your Path\\Folder";
        StringBuilder content = new StringBuilder();

        content.append(generation).append("\n");
        content.append(maxIteration).append("\n");
        for (Individual indv : individuals)
            content.append(indv).append("  -  Fitness : ").append(indv.gettingFitness()).append("\n");

        content.append("Best : ").append(fittest).append("  -  Fitness : ").append(fittest.gettingFitness()).append("\n");

        saveTxt(route,String.valueOf(content));
    }

    //Aqui se crea el archivo de texto y se almacena la generacion actual
    public void saveTxt (String route, String content) {
        try {
            File file = new File(route);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}