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

    public static final String SHOW_TABLES = "SELECT * FROM ";

    public static final int ORDER_BY_ASC = 1;
    public static final int ORDER_BY_DESC = 2;
    public static final int ORDER_BY_NONE= 3;

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
    public ArrayList<String> metaData(String tableName){
        StringBuilder sb = new StringBuilder(SHOW_TABLES);
        sb.append(tableName);

        System.out.println("SQL INPUT = "+sb);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())){

            ResultSetMetaData rsmd = results.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println("columnCount = "+columnCount);

            ArrayList<String> metasongs = new ArrayList<>();
//            for (int i=1; i<=columnCount; i++ ){
//                metasongs.add(rsmd.getColumnName(i));
//            }
            int i=1;
            while(i<=columnCount){
                metasongs.add(rsmd.getColumnName(i));
                i++;
            }
            return metasongs;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
