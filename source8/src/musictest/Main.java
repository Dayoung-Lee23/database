package musictest;

import musictest.model.Datasource;

public class Main {
    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        datasource.querySongs(datasource.TABLE_SONGS);

        datasource.close();
    }
}