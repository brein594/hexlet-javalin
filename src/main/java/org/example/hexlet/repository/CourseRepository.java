package org.example.hexlet.repository;

import org.example.hexlet.model.Course;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository extends BaseRepository {
    public static void initCourse() throws SQLException {
        var oneCourse = new Course( "1", "oneCourse");
        save(oneCourse);
        var twoCourse = new Course("2", "twoCourse");
        save(twoCourse);
    }
    public static void save(Course course) throws SQLException {
        var sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }
    public static void delete(Course course){}
    public static void delete(int id){}
    public static ArrayList<Course> getCourses() throws SQLException{
        var sql = "SELECT * FROM courses";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
             var resultSet = stmt.executeQuery();
             var result = new ArrayList<Course>();
             while (resultSet.next()) {
                 var id = resultSet.getInt("id");
                 var name = resultSet.getString("name");
                 var description = resultSet.getString("description");
                 var course = new Course(id, name, description);
                 result.add(course);
             }
             return result;
        }
    }
    public static Optional<Course> find(String name) throws SQLException{
        var sql = "SELECT * FROM courses WHERE name = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getInt("id");
                var nameRes = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(id, nameRes, description);
                return Optional.of(course);
            }
            return Optional.empty();
        }
    }
    public static List<Course> search(String term) {
        var course = new Course(0, "","");
        return List.of(course);
    }
    }
