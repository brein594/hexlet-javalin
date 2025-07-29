package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.util.ArrayList;

import static io.javalin.rendering.template.TemplateUtil.model;


public class UsersController {

    public static void index(Context ctx) {
        var users = UserRepository.getUsers();
        var page = new UsersPage(users);
        ctx.render("users/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        var page = new UserPage(UserRepository.getUsers().get(id - 1));
        ctx.render("users/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        //var id = users.size() + 1;
        var firsName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .check(value -> value.length() >= 3, "Длина пароля больше 3 символов")
                    .get();
            var user = new User(firsName, lastName, password);
            UserRepository.save(user);
            ctx.redirect(NamedRoutes.userPath());
        } catch (ValidationException e) {
            var page = new BuildUserPage(firsName, lastName, e.getErrors());
            ctx.render("users/build.jte", model("page", page));
        }
    }
}
