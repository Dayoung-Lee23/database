package com.musictest.model;

import java.sql.*;
import java.util.ArrayList;

//ID = 1, Name = Mahogany Rush 형식으로 불러오기
public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\402-016\\database\\source1\\"+DB_NAME;
    private Connection conn;

    public boolean open(){ //여기서 메서드를 만듦.
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("It was connected");
            return true;
        }catch(SQLException e){
            System.out.println("Couldn't connect to database: "+e.getMessage());
            return false;
        }
    }//분기를 더 세분하게 나눠주는 거임.

    public void artistsResult() throws SQLException{
        if ( )
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
}

