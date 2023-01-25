package org.UnsupervisedLearningModelDBSCAN.DBSCAN;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.DBSCAN;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class dbscan {

    private double epsilon;
    private int minPoints;

    private Instances instances;
    private DBSCAN dbscan;
    private ClusterEvaluation eval;

    public dbscan(double epsilon, int minPoints) {
        this.epsilon = epsilon;
        this.minPoints = minPoints;
    }

    //En este argumento se pasan el argumento en cadena de bits
    public dbscan(byte[] epsilon, byte[] minPoints) {
        this.epsilon = convertEpsilon(epsilon);
        this.minPoints = convertMinPoints(minPoints);
    }


    //Convertir en el argumeento epsilon dada una parte de entrada de Bits
    public Double convertEpsilon (byte[] args) {
        return 0.0;
    }

    //Convertir en el argumeento minPoints dada una parte de entrada de Bits
    public int convertMinPoints (byte[] args) {
        return 0;
    }

    //Convertir en los dos argumeentos dada una entrada de Bits
    public int convert (byte[] args) {
        return 0;
    }

    //Cargar la ruta donde esta el archivo .arff para generar las Intancias
    public void loadIntances (String route) throws Exception {
        ConverterUtils.DataSource src = new ConverterUtils.DataSource( route );
        instances = src.getDataSet();
    }

    //Genera el experimento con los valores establecidos por el constructor
    public void generate () throws Exception {
        dbscan = new DBSCAN();
        dbscan.setEpsilon(epsilon);
        dbscan.setMinPoints(minPoints);
        dbscan.buildClusterer(instances);

        eval = new ClusterEvaluation();
        eval.setClusterer(dbscan);
        eval.evaluateClusterer(instances);

        eval.clusterResultsToString();
        eval.getNumClusters();
    }

    //Imprime el resultado de dbscan
    public void getDbscan () {
        System.out.println(dbscan);
    }

    //Muestra los resultados del experimento
    public void getResults () {
        results results = new results();
        results.generateResults( eval.clusterResultsToString() );
        results.getResult();
        System.out.println( eval.getNumClusters() );
    }


}
