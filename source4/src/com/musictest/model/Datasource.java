package com.musictest.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\402-016\\database\\source1\\"+DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    public static final String ORDER_BY_ASC = "ORDER_BY_ASC";
    public static final String ORDER_BY_DESC = "ORDER_BY_DESC";
    public static final String ORDER_BY_NONE= "ORDER_BY_NONE";

    private Connection conn;

    public boolean open(){
        try{
             conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("It was connected");
            return true;
        }catch(SQLException e){
            System.out.println("Couldn't connect to database: "+e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if(conn != null){
                conn.close();
                System.out.println("connection closed");
            }
        }catch(SQLException e){
            System.out.println("Couldn't close connection: "+e.getMessage());
        }
    }

    public ArrayList<Artist> queryArtists(String sortingOrder){
 //SELECT * FROM artists ORDER BY name ASC;
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS); //StringBuilder를 사용하면 바로 바로 뒤로 붙으니 편리함.
        if(sortingOrder=="ORDER_BY_ASC"){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" ASC");
        }else if(sortingOrder == "ORDER_BY_DESC"){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" DESC");
        }// 이렇게 끝내버리면 ORDER_BY_ASC도 ORDER_BY_DESC도 아니고, SELECT * FROM artist로 끝나는 거임.

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            System.out.println(sb.toString());
            List<Artist> artists = new ArrayList<>();
            while(results.next()){
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
                }
                return (ArrayList<Artist>) artists;

        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            return null;
        }
    }
}
