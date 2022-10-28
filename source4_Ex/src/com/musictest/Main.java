package com.musictest;

import com.musictest.model.Artist;
import com.musictest.model.Datasource;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        //List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_ASC);
        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
        //List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_NONE);

        if(artists == null){
            System.out.println("Can't find an artist");
            return;
        }
        for (Artist i : artists){
            System.out.println("ID = "+i.getId()+", Name = "+i.getName());
        }
        datasource.close();
    }
}