package org.UnsupervisedLearningModelDBSCAN.DBSCAN;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class results {

    Hashtable<Integer,Integer> hashtable = new Hashtable<>();
    int noise = 0;

    public void generateResults (String results) {

        Matcher matcherCluster = Pattern.compile("[\\d]{1,200} {6}[\\d]{1,200}").matcher( results );
        Matcher matcherUnclusteredInstances = Pattern.compile("Unclustered instances : [\\d]{1,200}").matcher( results );

        while ( matcherCluster.find() ){
            String[] clusters = matcherCluster.group().split(" {6}");
            hashtable.put(Integer.parseInt(clusters[0]),Integer.parseInt(clusters[1]));
        }

        if ( matcherUnclusteredInstances.find() ){
            String[] noises = matcherUnclusteredInstances.group().split("Unclustered instances : ");
            noise = Integer.parseInt(noises[1]);
        }
    }

    public void getResult () {
        System.out.println(hashtable.toString());
        System.out.println(noise);

    }

}
