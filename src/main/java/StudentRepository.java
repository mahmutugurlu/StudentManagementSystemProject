import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repository<Student, Integer>{


    //7- t_student  tablosunu olusturalim
    @Override
    public void createTable() {
        JdbcUtils.setConnection();
        JdbcUtils.setStatement();

        try{

            JdbcUtils.statement.execute("CREATE TABLE IF NOT EXISTS t_student(" +
                    "id SERIAL UNIQUE," +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0)," +
                    "lastname VARCHAR(50) NOT NULL CHECK(LENGTH(lastname)>0)," +
                    "city VARCHAR(50) NOT NULL," +
                    "age INTEGER NOT NULL CHECK(age>0) ) ");

        }catch (SQLException e){

            System.out.println(e.getMessage());

        }finally {

            try {
                JdbcUtils.statement.close();
                JdbcUtils.connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

        }



    }

    //8-c:yeni öğrenciyi insert etmek için sorgu gönderme
    @Override
    public void save(Student student) {
        JdbcUtils.setConnection();
        JdbcUtils.setPrst("INSERT INTO t_student(name,lastname,city,age) VALUES(?,?,?,?)");

        try {
            JdbcUtils.prst.setString(1,student.getName());
            JdbcUtils.prst.setString(2,student.getLastname());
            JdbcUtils.prst.setString(3,student.getCity());
            JdbcUtils.prst.setInt(4,student.getAge());
            JdbcUtils.prst.executeUpdate();
            System.out.println("Öğrenci kaydetme başarılı!!!");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.prst.close();
                JdbcUtils.connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }




    //9-b: tablodan tüm kayıtları getirme: fetch
    @Override
    public List<Student> findAll() {

        JdbcUtils.setConnection();
        JdbcUtils.setStatement();
        List<Student> allStudent=new ArrayList<>();

        try {

            ResultSet rs =JdbcUtils.statement.executeQuery("SELECT * FROM t_student");

            while (rs.next()){
                Student student=new Student(rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("city"),
                        rs.getInt("age"));
                student.setId(rs.getInt("id"));
                allStudent.add(student);
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                JdbcUtils.statement.close();
                JdbcUtils.connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }





        return allStudent;

    }

    @Override
    public void update(Student entity, Integer id) {

        JdbcUtils.setConnection();
        JdbcUtils.setPrst("UPDATE t_student SET name=?,lastname=?,city=?,age=? WHERE id=?");

        try {

            JdbcUtils.prst.setString(1,entity.getName());
            JdbcUtils.prst.setString(2,entity.getLastname());
            JdbcUtils.prst.setString(3,entity.getCity());
            JdbcUtils.prst.setInt(4,entity.getAge());
            JdbcUtils.prst.setInt(5,id);
            int updated=JdbcUtils.prst.executeUpdate();
            if (updated>0){
                System.out.println("Güncelleme başarılı...");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.prst.close();
                JdbcUtils.connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void deleteById(Integer id) {

        JdbcUtils.setConnection();
        JdbcUtils.setPrst("DELETE FROM t_student WHERE id=?");

        try {
            JdbcUtils.prst.setInt(1,id);
            int deleted=JdbcUtils.prst.executeUpdate();
            if (deleted>0){
                System.out.println("Silme işlemi başarılı...");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.prst.close();
                JdbcUtils.connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Student findById(Integer id) {

        JdbcUtils.setConnection();
        JdbcUtils.setPrst("SELECT * FROM t_student WHERE id=?");
        Student student =null;
        try {

            JdbcUtils.prst.setInt(1,id);
            ResultSet rs=JdbcUtils.prst.executeQuery();
            if (rs.next()){
                student=new Student(rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("city"),
                        rs.getInt("age"));
                student.setId(rs.getInt("id"));
            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.prst.close();
                JdbcUtils.connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }






        return null;
    }
}
