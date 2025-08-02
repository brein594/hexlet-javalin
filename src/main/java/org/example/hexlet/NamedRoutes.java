package org.example.hexlet;

public class NamedRoutes {
    public static String usersPath() {
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

    public static String usersPath(int id) {
        //return userPath()+ "/" + String.valueOf(id);
        return usersPath(String.valueOf(id));
    }

    public static String usersPath(String id) {
        return "/users/" + id;
    }

    public static String sessionsPath() {
        return "/sessions";
    }
    public static String buildSessionsPath() {
        return "/sessions/build";
    }
    public static String destroySessionsPath() {
        return "/sessions/destroy";
    }
}
