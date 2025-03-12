public class Student {

    //domain-entity-model bu tür classlara böyle projelerde bu isimlerden biri verilir

    //****** !!!!! student sınıfına karşılık gelecek bir tablo gereklidir  ******************

    private Integer id; //eger Integer da deger atamazsak default olarak null atanir

    private String name;

    private String lastname;

    private String city;

    private int age; //eger Integer da deger atamazsak default olarak 0 atanir


    //paramli/paramsiz const

    public Student() {
    }

    public Student(String name, String lastname, String city, int age) {
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.age = age;
    }

    //setter-getter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
    this.id = id;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }




}
