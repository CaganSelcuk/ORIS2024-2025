import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class MainRepository {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Mertens274510";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-306";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        SportsmanRepository sportsmanRepository = new SportsmanRepositoryJdbcImpl(connection);
        List<Sportsman> sportsmen = sportsmanRepository.findAll();
        sportsmen.forEach(sportsman -> System.out.println(sportsman.getName()));

    }
}
