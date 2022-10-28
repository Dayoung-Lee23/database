import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\402-016\\database\\" + DB_NAME;
    public static final String TABLE_CONTACT2 = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
//이 값들은 고정적으로 쓰이기 때문에 final변수로써 상수화, 또한 값을 일괄적으로 수정할 때에도 편리하다.
//내 프로젝트 DB를 쓸 때도 어떤 부분을 final 변수로 설정할지 생각하기

    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);//우리가 연결하려는 것 괄호안에 주소 적어주기
// JDBC를 통해 DB와 연결. java.sql.*를 import해 getConnection 메소드 사용 (마우스 커서 올려보면 설명나옴ㅎ)
//Attempts to establish a connection to the given database URL. The DriverManager attempts to select an appropriate driver from the set of registered JDBC drivers.
            Statement statement = conn.createStatement();
    //SQL문을 사용하겠다는 뜻. createStatement는 connection이 먼저 들어가야 시행가능
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACT2);
            //다음의 SQL문을 실행: 같은 테이블 있으면 반영하지 않고 드랍하도록. 삭제!
            //DELETE는 조건을 주어 row 등을 삭제, DROP은 테이블 혹은 DB 전체를 삭제.

            statement.execute("CREATE TABLE IF NOT EXISTS "+ TABLE_CONTACT2+ " ("+ COLUMN_NAME
                    + " TEXT, "+COLUMN_PHONE + " INTEGER, "+ COLUMN_EMAIL + " TEXT) ");
//테이블을 생성해라(만약 이미 있으면 만들지 말고). 테이블의 컬럼들과 각각의 자료형까지 주어줌.
            insertContents(statement, "John", 1111, "jonh@email.com");
            insertContents(statement, "Mattew", 2222, "mattew@wmail.com");
            insertContents(statement, "Mark", 3333, "mark@email.com");
            //메서드 형식으로 만든 것을 활용하면 매개변수 만으로 input값을 넣어 가독성도 좋고 용이하게. 반복적으로 쓸 때 경제적임.
            statement.execute("UPDATE "+ TABLE_CONTACT2 + " SET " + COLUMN_PHONE + " = 1111 " + "WHERE " + COLUMN_NAME + " = 'tim'");
            statement.execute("DELETE FROM "+ TABLE_CONTACT2+ " WHERE "+ COLUMN_NAME+ " = 'tim'");
//execute method can run both select and insert/update statements.
            ResultSet results = statement.executeQuery("SELECT * FROM "+TABLE_CONTACT2);
//import java.sql.* 로 ResultSet 사용,SELECT 쿼리문의 결과를 저장할 때 사용.
//executeQuery method execute statements that returns a result set by fetching some data from the database. It executes only select statements.
            while(results.next()){//.next() 뒤에 뭐가 있으면 true, 없어서 null값이면 false로 인식
                System.out.println(results.getString(COLUMN_NAME)+ " " + results.getInt(COLUMN_PHONE) + " "+ results.getString(COLUMN_EMAIL));
            }//result는 배열인데 result.next()라는 것은 sout내의 한 줄, 그 뒤 배열이 있으면 그 다음줄, 마지막줄까지 i++식으로 출력하고 result.next()가 null값을 만나면 false로 인식해 while문 종료
            //result.getString(COLUMN_NAME) 컬럼에서 네임에 해당하는 내용을 String 형태로 가져와라 results.getInt(COLUMN_PHONE) int형태로 가져와라
            results.close();

            statement.close();
            conn.close();

        }catch (SQLException e){ //try안에를 실행하다가 SQLException의 에러가 발생하면 catch문을 실행하라.
            System.out.println("Something went wrong..." + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void insertContents(Statement s, String name, int phone, String email) throws SQLException{
//private static 같은 클래스 내부니까 언제든지 사용가능. 우리가 .execute 메서드를 사용해야하려면 Statement 객체가 필요.
// throws Exception을 하는 이유는 자바에서 import한 statement.execute 메서드 자체 내에 throws java.sql.SQLException이 되어있기때문에 어디에서든 try-catch를 통해 이를 처리해줘야한다.
        //Statement 클래스의 객체가 매개변수로 들어와야 이 insertContents 함수 내에서 .execute문을 사용할 수 있다. 이거를 안하고
        //Statement 객체를 이 함수내에서 또 생성해서 써도 되긴 하지만 connection도 다시 껐다 켰다 하는 코드도 포함해야하므로 이렇게 가져와서 씀.
        s.execute("INSERT INTO "+TABLE_CONTACT2+ " (" +COLUMN_NAME +", " + COLUMN_PHONE+ ", "
                + COLUMN_EMAIL+") "+"VALUES('"+name+ "', "+phone+", '" + email+"')");
    }//private static 개념 찾아보기
}