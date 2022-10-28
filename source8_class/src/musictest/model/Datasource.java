package musictest.model;

import java.sql.*;

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

    public static final String orgNum = "SELECT COUNT (DISTINCT title) AS count FROM ";
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
    public int getCount(String table){
        String sql = orgNum+ table;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)){
            int count = results.getInt("count"); //1로 넣어도 됨
            System.out.format("Count = %d\n", count);
            return count;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return -1; //-1은 int형에서의 false값을 의미함. try/catch 둘 중에 어떤게 실행될지 모르니 둘다 return문을 가지고 있어야 한다.
        }
    }
}
