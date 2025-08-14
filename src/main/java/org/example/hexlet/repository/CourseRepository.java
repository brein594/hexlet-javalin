package org.example.hexlet.repository;

import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository extends BaseRepository {
    public static void initCourse() {}
    public static void save(Course course) {}
    public static void delete(Course course){}
    public static void delete(int id){}
    public static ArrayList<Course> getCourses() {
        return new ArrayList<Course>();
    }
    public static Optional<Course> find(String name) {
        return Optional.empty();
    }
    public static List<Course> search(String term) {
        var course = new Course(0, "","");
        return List.of(course);
    }
    }
