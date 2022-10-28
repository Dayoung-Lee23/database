package musictest;

import musictest.model.Datasource;

public class Main {
    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        int count = datasource.getCount(datasource.TABLE_SONGS);
        System.out.println("Number of songs : " +count);

        datasource.close();
    }
}