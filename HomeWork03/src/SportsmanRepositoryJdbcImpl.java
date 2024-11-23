import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SportsmanRepositoryJdbcImpl implements SportsmanRepository {
    private Connection connection;
    private static final String SQL_SELECT_ALL_FROM_SPORTSMAN = "SELECT * FROM sportsman";

    public SportsmanRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Sportsman> findAll() {
        List<Sportsman> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_SPORTSMAN)) {
            while (resultSet.next()) {
                Sportsman sportsman = new Sportsman(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("power"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("telefon")
                );
                result.add(sportsman);
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return result;
    }

    @Override
    public Optional<Sportsman> findById() {
        return Optional.empty();
    }


    public Optional<Sportsman> findById(Long id) {
        String sql = "SELECT * FROM sportsman WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Sportsman sportsman = new Sportsman(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("power"),
                            resultSet.getInt("age"),
                            resultSet.getString("email"),
                            resultSet.getString("telefon")
                    );
                    return Optional.of(sportsman);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return Optional.empty();
    }

    @Override
    public void save(Sportsman entity) {
        String sql = "INSERT INTO sportsman (name, surname, power, age, email, telefon) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setInt(3, entity.getPower());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getTelefon());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void update(Sportsman entity) {
        String sql = "UPDATE sportsman SET name = ?, surname = ?, power = ?, age = ?, email = ?, telefon = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setInt(3, entity.getPower());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getTelefon());
            preparedStatement.setLong(7, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void remove(Sportsman entity) {
        removeById(entity.getId());
    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public void removeById(Long id) {
        String sql = "DELETE FROM sportsman WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public List<Sportsman> findAllByPower(Long power) {
        return filterByField("power", power);
    }

    @Override
    public List<Sportsman> findAllByAge(Integer age) {
        return filterByField("age", age);
    }

    @Override
    public List<Sportsman> findAllByEmail(String email) {
        return filterByField("email", email);
    }

    @Override
    public List<Sportsman> findAllByTelefon(String telefon) {
        return filterByField("telefon", telefon);
    }

    private <T> List<Sportsman> filterByField(String fieldName, T value) {
        String sql = "SELECT * FROM sportsman WHERE " + fieldName + " = ?";
        List<Sportsman> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, value);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Sportsman sportsman = new Sportsman(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("power"),
                            resultSet.getInt("age"),
                            resultSet.getString("email"),
                            resultSet.getString("telefon")
                    );
                    result.add(sportsman);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return result;
    }
}
