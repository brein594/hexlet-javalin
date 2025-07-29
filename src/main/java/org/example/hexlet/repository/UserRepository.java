package org.example.hexlet.repository;

import lombok.Getter;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserRepository {
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

    public static ArrayList<User> getUsers() {
        return new ArrayList<>(usersRep);
    }
}
