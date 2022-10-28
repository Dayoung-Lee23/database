import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjava.db"; //변수 네이밍 규칙: 자바 키워드와 겹치지 않게 설정!
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\402-016\\database\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contact12";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static String insert(String name, int phone, String email) {
        return "INSERT INTO "+TABLE_CONTACTS + " VALUES('"+name+"',"+ phone+",'"+email+"')" ;
    }//sql문 쓸 때 space랑 ' , 주의!

    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);//우리가 연결하려는 것 괄호안에 주소 적어주기
            Statement statement = conn.createStatement(); //연결을 하고 이제 statement를 할거야
            //Statement를 쓰면 sql관련 유틸리티가 import 됨.
            statement.execute("DROP TABLE IF EXISTS "+TABLE_CONTACTS);

            statement.execute("CREATE TABLE "+TABLE_CONTACTS+"("+ COLUMN_NAME+" TEXT,"+COLUMN_PHONE+" INTEGER,"+COLUMN_EMAIL+" TEXT)");//테이블 하나 만들기
            statement.execute(insert("sam", 2222, "sam@email.com"));
            statement.execute(insert("lora", 3333, "lora@email.com"));
            statement.execute(insert("tim", 4444, "tim@email.com"));
                                    //자바 문자열과 sql문 문자를 구분하기 위해 작은 따옴표, 큰 따옴표 구분해서 쓰기.
//            statement.execute("UPDATE contact1 SET phone=1111 WHERE phone=4444");
//            statement.execute("DELETE FROM contact1 WHERE name = 'tim'");
            ResultSet results = statement.executeQuery("SELECT * FROM contact12");
            while(results.next()){
                System.out.println(results.getString("name")+" "+ results.getInt("phone")+ " " + results.getString("email"));
            }//result에 다음 값이 없으면 close
            results.close();
            statement.close();
            conn.close(); // 이 두 순서 지키기

        }catch(SQLException e){
            System.out.println("Something went wrong..."+e.getMessage());
            e.printStackTrace();
        }
    }
}