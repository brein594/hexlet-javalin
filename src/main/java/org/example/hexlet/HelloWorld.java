package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        var oneCourse = new Course(1, "1", "oneCourse");
        var twoCourse = new Course(2, "2", "twoCourse");
        var oneUser = new User(1, "Anya", "Petrova");
        var twoUser = new User(2, "Petyz", "Ivanov");
        var courses = new ArrayList<Course>();
        courses.add(oneCourse);
        courses.add(twoCourse);
        var users = new ArrayList<User>();
        users.add(oneUser);
        users.add(twoUser);


        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> ctx.render("index.jte")); //вывод из шаблона
        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var page = new CoursePage(courses.get(id-1));
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            //var courses = /* Список курсов извлекается из базы данных */
            var header = "course programming";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var page = new UserPage(users.get(id-1));
            ctx.render("users/show.jte", model("page", page));
        });

        app.get("/users", ctx -> {
            //var courses = /* Список курсов извлекается из базы данных */

            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });



        //app.get("/", ctx -> ctx.result("Hello World"));
        //app.get("/users", ctx -> ctx.result("Get resposeble"));
        app.post("/user", ctx -> ctx.result("Post responseble"));
        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name);
        });

        app.get("/users/{id}/post/{postid}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var postid = ctx.pathParam("postid");
            ctx.result("user ID: " + id + " post ID: " + postid);
        });

        app.start(7071);
    }
}