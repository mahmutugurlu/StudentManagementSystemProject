
//course,instructor,student-->CRUD
//data tipinden bağımsız olarak CRUD işlemlerini listeleyen interface
import java.util.List;

//S:entity data tipi , U:id değerleri için
public interface Repository<S,U> {

    void createTable();

    void save(S entity);

    List<S> findAll();

    void update(S entity, U id);

    void deleteById (U id); //id icin farkli bir data tipi kullancagimiz icin U data tipi yazdik

    S findById(U id);

}