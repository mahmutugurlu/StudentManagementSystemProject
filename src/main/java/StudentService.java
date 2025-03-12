import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentService {



    //service:business: mantıksal işlemler, kontroller
    //service classları da repository classları ile görüşür

    private Scanner input = StudentController.inp;

    private  Repository repository = new StudentRepository();


    //7-a:student tablosunu oluşturma

    public void createTable(){
        repository.createTable();
    }

    //8-b ogrenci bilgilerini alma
    public Student getStudentInfo() {

        // Student s=new Student();

        System.out.println("AD : ");
        String name=input.nextLine();
        // s.setName(input.nextLine());
        

       // String name=input.nextLine();
        System.out.println("SOYAD : ");
        String lastname=input.nextLine();
        System.out.println("ŞEHİR : ");
        String city=input.nextLine();
        System.out.println("YAŞ : ");
        int age= input.nextInt();
        input.nextLine();

         return new Student(name,lastname,city,age);//id:null


    }


    //8-b:yeni öğrenciyi kaydetme
    public void saveStudent(Student newStudent) {

        // newStudent.getName().length()<=0
        //bu durumda hata fırlatılabilir
        //tüm kontrollerden sonra
        repository.save(newStudent);


    }

    //9-a
    public void printAllStudents() {
        List<Student> studentList=repository.findAll();
        System.out.println("======================= TÜM ÖĞRENCİLER =============================");
        for (Student s:studentList) {
            System.out.println("id :"+s.getId()+
                    "    adı :"+s.getName()+
                    "    soyadı :"+s.getLastname()+
                    "     şehir : "+s.getCity()+
                    "      yaş : "+s.getAge());
        }

    }

    //10-a
    public Student getStudentById(int id){
        Student student= (Student) repository.findById(id);
        if (student==null){
            System.out.println("ID si verilen öğrenci bulunamadı!");
        }
        return student;
    }

    //10-b
    public void printStudentById(int id) {

        Student foundstudent=getStudentById(id);
        if (foundstudent!=null){
            System.out.println(foundstudent);
        }

    }

    //11-a
    public void updateStudent(int id) {
        //bu id ile öğrenci var mı?
        Student found=getStudentById(id);
        if (found!=null){//jack
            //bu öğrencinin yeni bilgileri nedir?
            Student newStudent=getStudentInfo();//id yok, id aynı kalacak
            repository.update(newStudent,id);
        }

    }

    //12-a
    public void deleteStudent(int id) {
        //bu id ile öğrenci var mı?
        getStudentById(id);//9->X
        repository.deleteById(id);//1 ->V  , 9->
    }


    //13-a:tablodaki tüm öğrencilerin ad-soyad bilgilerini txt uzantılı bir dosyaya yazdırma

    public void generateReport() {
        List<Student> allStudent=repository.findAll();
        System.err.println("Report is loading...");
        try {
            Thread.sleep(10000);
            FileWriter writer=new FileWriter("student_report.txt");
            writer.write("*** Student Report ***\n");
            writer.write("-----------------------\n");
            for (Student student:allStudent){
                writer.write("Ad : "+student.getName()+"   ---   Soyad : "+student.getLastname()+"\n");
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();

        }
        System.err.println("Report is generated and printed to student_report.txt...");
    }
}
