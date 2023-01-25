package org.UnsupervisedLearningModelDBSCAN.DBSCAN;

public class initDbscan {

    String route;
    Double epsilon;
    int minPoints;


    // Double  Epsilon             Int  minPoints
    // 1111111111               101010101010101010

    //


    //En este argumento se pasan el argumento en su forma normal
    public initDbscan(String route, Double epsilon, int minPoints) {
        this.route = route;
        this.epsilon = epsilon;
        this.minPoints = minPoints;
    }

    //En este argumento se pasan el argumento en cadena de bits
    public initDbscan(String route, String params) {
        this.route = route;

        String epsilon = params.substring(0,64);
        String minPoints = params.substring(64,80);

        this.epsilon = convertEpsilon(epsilon);
        this.minPoints = convertMinPoints(minPoints);
    }

    //Convertir en el argumeento epsilon dada una parte de entrada de Bits
    public Double convertEpsilon (String epsilon) {
        return Double.longBitsToDouble( Long.parseLong( epsilon ,2) );
    }

    //Convertir en el argumeento minPoints dada una parte de entrada de Bits
    public int convertMinPoints (String minPoints) {
        return Integer.parseInt(minPoints,2);
    }



    //Inicializa los experimentos de dbscan
    public void init () {
        dbscan dbscan = new dbscan (epsilon,minPoints);
        try {
            dbscan.loadIntances(route);
            dbscan.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dbscan.getDbscan();
        dbscan.getResults();
    }

}
