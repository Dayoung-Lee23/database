package com.musictest;

import com.musictest.model.All;
import com.musictest.model.Datasource;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        ArrayList<All> allLists =
                datasource.querySongArtistAlbum("Go Your Own Way", Datasource.ORDER_BY_ASC);
        for (All i : allLists){
            System.out.println("Artist name = "+ i.artisti.getName() + ", Album name = "+ i.albumi.getName()+
                    ", Track = "+ i.songi.getTrack());
        }
        datasource.close();
    }
}