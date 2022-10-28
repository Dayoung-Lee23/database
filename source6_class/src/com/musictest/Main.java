package com.musictest;

import com.musictest.model.Datasource;
import com.musictest.model.SongArtist;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        ArrayList<SongArtist> songArtists = datasource.queryArtistForSong
                ("Go Your Own Way", Datasource.ORDER_BY_ASC);

        if (songArtists == null){//이 if문을 붙여놓으면 Query문에서 뭔가 잘못됐다는 것을 바로 알 수 있다.
            System.out.println("Couldn't find the artist for the song");
            return;
        }// 분기별로 에러메세지가 보이도록 넣어두면 바로 그곳에 가서 재작업을 하기가 용이하다.

        for (SongArtist songArtist: songArtists){
            System.out.println("Artist name = "+ songArtist.getArtistName() + ", Album name = "
                    + songArtist.getAlbumName()+ ", Track = "+ songArtist.getTrack());
        }
        datasource.close();
    }
}