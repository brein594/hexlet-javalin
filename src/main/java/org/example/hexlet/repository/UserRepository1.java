package org.example.hexlet.repository;

import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepository1 {
    private final static ArrayList<User> usersRep = new ArrayList<>();

    public static void initUser() {
        var id = usersRep.size() + 1;
        var oneUser = new User(id, "Anya", "Petrova", "1234");
        usersRep.add(oneUser);
        id = usersRep.size() + 1;
        var twoUser = new User(id, "Petyz", "Ivanov", "1234");
        usersRep.add(twoUser);
    }

    public static void save(User user) {
        var id = usersRep.size() + 1;
        var userSave = new User (id, user.getFirstName(), user.getLastName(), user.getPassword());
        usersRep.add(userSave);
    }

    public static void delete(User user) {
        usersRep.remove(user.getId());
    }

    public static void delete(int id) {
        usersRep.remove(id);
    }

    public static ArrayList<User> getUsers() {
        return new ArrayList<>(usersRep);
    }

    public static Optional<User> find (int id) {
        return usersRep.stream()
                .filter( user -> user.getId() == id)
                .findAny();
    }

    public static List<User> search(String term) {
        return usersRep.stream()
                .filter(entity -> StringUtils.startsWithIgnoreCase(entity.getFirstName(), term))
                .toList();
    }
}
