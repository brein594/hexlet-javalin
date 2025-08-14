package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;


public class UsersController {

    public static void index(Context ctx) {
        try {
            var users = UserRepository.getUsers();
            var page = new UsersPage(users);
            page.setFlash(ctx.consumeSessionAttribute("addUser"));
            ctx.render("users/index.jte", model("page", page));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        try {
            var page = new UserPage(UserRepository.getUsers().get(id - 1));
            ctx.render("users/show.jte", model("page", page));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        //var id = users.size() + 1;
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .check(value -> value.length() >= 3, "Длина пароля больше 3 символов")
                    .get();
            var user = new User(firstName, lastName, password);
            UserRepository.save(user);
            ctx.sessionAttribute("addUser", "User has been created!");
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            var page = new BuildUserPage(firstName, lastName, e.getErrors());
            ctx.render("users/build.jte", model("page", page));
        } catch (SQLException er) {
            throw new RuntimeException(er);
        }
    }

    public static void edit(Context ctx) {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        try {
            var user = UserRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("User with id " + id + " not found"));
            var page = new UserPage(user);
            ctx.render("users/edit.jte", model("page", page));
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Context ctx) {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var password = ctx.formParam("password");

        try {
            var user = UserRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("User with id " + id + " not found"));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);

            UserRepository.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ctx.redirect(NamedRoutes.usersPath());
    }

    public static void destroy(Context ctx) throws SQLException  {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        UserRepository.delete(id);
        ctx.redirect(NamedRoutes.usersPath());
    }

}
