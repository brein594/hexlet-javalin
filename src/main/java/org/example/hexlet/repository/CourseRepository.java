package org.example.hexlet.repository;

import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CourseRepository {
    private final static ArrayList<Course> coursesRep = new ArrayList<>();

    public static void initCourse() {
        var id = coursesRep.size() + 1;
        var oneCourse = new Course(id, "1", "oneCourse");
        coursesRep.add(oneCourse);
        id = coursesRep.size() + 1;
        var twoCourse = new Course(id, "2", "twoCourse");
        coursesRep.add(twoCourse);
    }

    public static void save(Course course) {
        var id = coursesRep.size() + 1;
        var courseSave = new Course (id, course.getName(), course.getDescription());
        coursesRep.add(courseSave);
    }

    public static void delete(Course course) {
        coursesRep.remove(course.getId());
    }

    public static void delete(int id) {
        coursesRep.remove(id);
    }

    public static ArrayList<Course> getCourses() {
        return new ArrayList<>(coursesRep);
    }

    public static Optional<Course> find (String name) {
        return coursesRep.stream()
                .filter( course -> course.getName().equals(name))
                .findAny();
    }

    public static List<Course> search(String term) {
        return coursesRep.stream()
                .filter(entity -> StringUtils.startsWithIgnoreCase(entity.getName(), term))
                .toList();
    }
}
