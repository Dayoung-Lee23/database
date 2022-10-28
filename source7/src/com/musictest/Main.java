package com.musictest;

import com.musictest.model.Datasource;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        ArrayList<String> metaSongs =
                datasource.metaData(Datasource.TABLE_SONGS);
        for(int i =0; i<metaSongs.size(); i++){
            System.out.println("Column "+(i+1)+" in the "
                    +Datasource.TABLE_SONGS+" table is named as "+metaSongs.get(i));
        }

        for (String columns : metaSongs){
            System.out.println(columns);
        }
        datasource.close();
    }
}