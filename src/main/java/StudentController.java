import java.util.Scanner;

public class StudentController {

    //uygulamayı başlatan ve (controller:istemci ile iletişime geçilecek)
//!!! controller ile sadece service classları görüşür

    public static Scanner inp=new Scanner(System.in);

    public static void main(String[] args) {
        //5-uygulama başlangıcı ve menü gösterimi
        start();

    }

    private static void start() {

        StudentService service = new StudentService();

        //7-b:uygulama ilk başlatıldığında tablo hazır olmalı
        service.createTable();


        int select;
        int id;
        do {
            System.out.println("========================================================");
            System.out.println("Öğrenci Yönetim Paneli");
            System.out.println("1-Öğrenci Kaydetme");
            System.out.println("2-Tüm Öğrencileri Görüntüleme");
            System.out.println("3-Öğrenciyi Güncelleme");
            System.out.println("4-Öğrenciyi Silme");
            System.out.println("5-Tek Bir Öğrenciyi Görüntüleme");
            System.out.println("6-Tüm Öğrencilerin AD-SOYAD bilgilerini rapora yazdırma");
            System.out.println("0-ÇIKIŞ");
            System.out.println("İşlem Seçiniz : ");
            select=inp.nextInt();
            inp.nextLine();

            switch (select){
                case 1:
                    //8-bilgileri verilen öğrenciyi kaydetme
                    Student newStudent=service.getStudentInfo();
                    new Thread(()-> service.saveStudent(newStudent)).start();
                    break;
                case 2:
                    //tüm öğrencileri konsolda yazdırma

                    service.printAllStudents();


                    break;
                case 3:
                    //id?
                    id=getIdInfo();

                    //11- id si verilen öğrenciyi güncelleme

                    service.updateStudent(id);

                    break;
                case 4:
                    //id?
                    id=getIdInfo();

                    //12- id si verilen öğrenciyi silme
                    service.deleteStudent(id);

                    break;
                case 5:
                    //id?
                    id=getIdInfo();
                    //id si verilen öğrenciyi görüntüleme

                    service.printStudentById(id);
                    break;
                case 6:
                    //13-rapor hazırlama:txt dosyasına
                    new Thread(()->{
                        service.generateReport();
                    }).start();
                    break;
                case 0:
                    System.out.println("İyi günler...");
                    break;
                default:
                    System.out.println("Hatalı giriş!!!");
                    break;
            }


        }while (select!=0);
    }

    //id bilgisini alma
    private static int getIdInfo(){
        System.out.println("Öğrenci id : ");
        int id=inp.nextInt();
        inp.nextLine();
        return id;
    }


}
