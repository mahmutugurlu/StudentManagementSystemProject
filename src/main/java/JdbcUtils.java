import java.sql.*;

public class JdbcUtils {

    //4-jdbc için gerekli olan nesnelerin hazırlığı

    public static Connection connection;

    public static Statement statement;

    public static PreparedStatement prst;


    //4-a: connection oluşturma

    public static  void setConnection(){

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/StudentManagementJdbc_db",
                    "techpro",
                    "password");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //4-b:statement oluşturma
    public static void setStatement(){
        try {
            statement = connection.createStatement();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //4-c : prst oluşturma
    public static void setPrst(String sql){

        try {
            prst=connection.prepareStatement(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }





}
