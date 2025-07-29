package org.example.hexlet;

public class NamedRoutes {
    public static String userPath() {
        return "/users";
    }

    public static String buildUserPath() {
        return "/users/build";
    }

    public static String coursePath() {
        return "/courses";
    }

    public static String coursePath(int id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    public static String userPath(int id) {
        //return userPath()+ "/" + String.valueOf(id);
        return userPath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }
}
