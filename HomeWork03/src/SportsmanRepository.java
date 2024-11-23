import java.util.List;


public interface SportsmanRepository extends CrudRepository <Sportsman> {

    void removeById(Long id);

    List<Sportsman> findAllByPower(Long power);

    List<Sportsman> findAllByAge(Integer age);

    List<Sportsman> findAllByEmail(String email);

    List<Sportsman> findAllByTelefon(String telefon);
}
