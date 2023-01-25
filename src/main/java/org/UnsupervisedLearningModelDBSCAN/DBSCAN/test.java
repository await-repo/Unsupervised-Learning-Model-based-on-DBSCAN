package org.UnsupervisedLearningModelDBSCAN.DBSCAN;

public class test {
    public static void main (String []args) throws Exception {

        String route = "Your Path\\Folder";

        //initDbscan init = new initDbscan(route, 0.5, 20);


        String complet = "01000000001001000000000000000000000000000000000000000000000000000000000000001000";

        initDbscan init = new initDbscan(route, complet);
        init.init();
    }
}
