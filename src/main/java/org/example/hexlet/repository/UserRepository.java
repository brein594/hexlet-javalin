package org.example.hexlet.repository;

import org.example.hexlet.model.User;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.hexlet.repository.BaseRepository.dataSource;

public class UserRepository extends BaseRepository {

    public static void save(User user) throws SQLException  {
        String sql = "INSERT INTO users (firstName, lastName, password) VALUES (?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }
    public static void delete(User user) throws SQLException  {
        var sql = "DELETE FROM users WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getId());
        }

    }
    public static void delete(int id) throws SQLException {
        var sql = "DELETE FROM users WHERE id = ?";
        try (var conn = dataSource.getConnection();
              var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
        }
    }
    public static ArrayList<User> getUsers() throws SQLException  {
        var sql = "SELECT * FROM users";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<User>();
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var firstName = resultSet.getString("firstName");
                var lastName = resultSet.getString("LastName");
                var password = resultSet.getString("password");
                var user = new User(id, firstName, lastName, password);
                result.add(user);
            }
            return result;
        }
    }
    public static Optional<User> find (int id) throws SQLException  {
        var sql = "SELECT * FROM users WHERE id = ?";
        try (var conn = dataSource.getConnection();
           var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            if(resultSet.next()){
                var firstName = resultSet.getString("firstName");
                var lastName = resultSet.getString("lastName");
                var password = resultSet.getString("password");
                var user = new User(firstName, lastName, password);
                user.setId(id);
                return Optional.of(user);
            }
            return Optional.empty();
        }

    }
    public static List<User> search(String term) throws SQLException {
        var sql = "SELECT * FROM users WHERE firstName ILIKE '?%'";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,term);
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<User>();
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var firstName = resultSet.getString("firstName");
                var lastName = resultSet.getString("lastName");
                var password = resultSet.getString("password");
                var user = new User(id, firstName, lastName, password);
                result.add(user);
            }
            return result;
        }
    }
    public static void initUser() throws SQLException {
        var oneUser = new User("Anya", "Petrova", "1234");
        save(oneUser);
        var twoUser = new User("Petya", "Ivanov", "1234");
        save(twoUser);
    }
}
