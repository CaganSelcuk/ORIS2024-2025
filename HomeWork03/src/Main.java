import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Mertens274510";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-306";


    public static void main(String[] args) throws Exception{
        Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        SportsmanRepository sportsmanRepository = new SportsmanRepositoryJdbcImpl(connection);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of sportsman to be added: ");
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println("Name: ");
            String name = scanner.next();
            System.out.println("Surname: ");
            String surname = scanner.next();
            System.out.println("Age: ");
            int age = scanner.nextInt();
            System.out.println("Email: ");
            String email = scanner.next();
            System.out.println("Telefon number: ");
            String telefon = scanner.next();
            System.out.println("Power: ");
            int power = scanner.nextInt();

            Sportsman sportsman = new Sportsman(null, name, surname, power, age, email, telefon);
            sportsmanRepository.save(sportsman);

        }
    }
}