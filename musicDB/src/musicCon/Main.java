package musicCon;

import java.sql.*;

class Main {
    public static void main(String[] args){
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\402-016\\musicSQL\\music.db");
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM albums");
            while(results.next()){
                System.out.println(results.getInt("_id")+" "+results.getString("name")+" "
                +results.getInt("artist"));
            }
            results.close();
            statement.close();
            conn.close();
        }catch(SQLException e){
            System.out.println("Something went wrong..."+e.getMessage());
        }
    }
}
