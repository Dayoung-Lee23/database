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
        ArrayList<String> albumsForArtist =
                datasource.queryAlbumsForArtist("Pink Floyd", Datasource.ORDER_BY_ASC);
        for (String album : albumsForArtist){
            System.out.println(album);
        }
        datasource.close();
    }
}