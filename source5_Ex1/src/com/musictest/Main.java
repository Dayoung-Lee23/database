package com.musictest;

import com.musictest.model.Albums;
import com.musictest.model.Datasource;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("artist의 이름을 입력하시오: ");
        String artistName = sc.nextLine();

        List<Albums> albums = datasource.queryAlbums(artistName, datasource.ORDER_BY_ASC);
        //List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
        //List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_NONE);

        if(albums == null){
            System.out.println("Can't find an artist");
            return;
        }
        for (Albums i : albums){
            System.out.println(i.getName());
        }
        datasource.close();
    }
}