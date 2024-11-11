import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Mertens274510";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb_3";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from driver where cast(age as integer) > 25");

        System.out.println("Больше чем 25 лет: ");
        while (result.next()) {
            System.out.println(result.getInt("id") + " "
                    + result.getString("name") + " "
                    + result.getString("surname") + " "
                    + result.getString("age"));
        }

        Scanner scanner = new Scanner(System.in);
        String[][] newUsers = new String[6][3];
        for (int i = 0; i < 6; i++) {
            System.out.println(i+1);
            newUsers[i][0] = scanner.nextLine();
            newUsers[i][1] = scanner.nextLine();
            newUsers[i][2] = scanner.nextLine();
        }

        String sqlInsertUser = "insert into driver(name, surname, age)" +
                "values(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);

        for (int i = 0; i < 6; i++) {
            preparedStatement.setString(i * 3 + 1, newUsers[i][0]);
            preparedStatement.setString(i * 3 + 2, newUsers[i][1]);
            preparedStatement.setString(i * 3 + 3, newUsers[i][2]);
            preparedStatement.addBatch();
        }

        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("Было добавлено " + affectedRows + "строк");
    }
}