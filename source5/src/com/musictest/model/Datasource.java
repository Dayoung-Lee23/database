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

    public static final String INNER_JOIN =
            "SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id WHERE artists.name=";
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
    //SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id WHERE artists.name= "Pink Floyd" ORDER BY albums.name;
    public ArrayList<Albums> queryAlbums(String aritistName, String sortingOrder){
        StringBuilder sb = new StringBuilder(INNER_JOIN);
        sb.append("\""+aritistName+"\"");
        if(sortingOrder=="ORDER_BY_ASC"){
            sb.append(" ORDER BY ");
            sb.append(TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME);
            sb.append(" ASC");
        }else if(sortingOrder == "ORDER_BY_DESC"){
            sb.append(" ORDER BY ");
            sb.append(TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME);
            sb.append(" DESC");
        }
        System.out.println(sb);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())){
            System.out.println(sb.toString());
            List<Albums> artists = new ArrayList<>();
            while(results.next()){
                Albums album = new Albums();
                //album.setId(results.getInt(COLUMN_ALBUM_ID));
                album.setName(results.getString(COLUMN_ALBUM_NAME));
                //album.setArtist(results.getString(COLUMN_ALBUM_ARTIST));
                artists.add(album);
            }
            return (ArrayList<Albums>) artists;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
