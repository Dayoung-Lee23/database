package com.musictest.model;

import java.sql.*;
import java.util.ArrayList;

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

    public static final int ORDER_BY_ASC = 1;
    public static final int ORDER_BY_DESC = 2;
    public static final int ORDER_BY_NONE= 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+ " FROM "+ TABLE_ALBUMS+
                    " INNER JOIN "+ TABLE_ARTISTS+" ON " +TABLE_ALBUMS+"."+COLUMN_ALBUM_ARTIST+
                    " = "+ TABLE_ARTISTS + "." +COLUMN_ARTIST_ID +
                    " WHERE "+TABLE_ARTISTS+"."+COLUMN_ARTIST_NAME+ " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT = " ORDER BY "+ TABLE_ALBUMS+ "."+COLUMN_ALBUM_NAME+" ";
    private Connection conn;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("It was connected");
            return true;
        }catch(SQLException e){
            System.out.println("Couldn't close connection: " +e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if(conn != null){
                conn.close();
                System.out.println("connection closed");
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: " +e.getMessage());
        }
    }
    public ArrayList<String> queryAlbumsForArtist(String aritistName, int sortingOrder){
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(aritistName).append("\"");

        if(sortingOrder !=ORDER_BY_NONE){
           sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
           if(sortingOrder == ORDER_BY_DESC){
               sb.append("DESC");
           }else{
               sb.append("ASC");
           }
        }
        System.out.println("SQL INPUT = "+sb);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())){

            ArrayList<String> albums = new ArrayList<>();
            while(results.next()){
                albums.add(results.getString(1));
            }
            return albums;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
