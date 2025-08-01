package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

import java.util.ArrayList;


public class HelloWorld {
    public static void main(String[] args) {

        UserRepository.initUser();
        CourseRepository.initCourse();

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var page = new MainPage(visited);
            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        }); //вывод из шаблона

        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);

        app.get(NamedRoutes.coursePath(), CoursesController::index);

        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        app.get(NamedRoutes.usersPath("{id}"), UsersController::show);

        app.get(NamedRoutes.usersPath(), UsersController::index);

        app.post(NamedRoutes.usersPath(), UsersController::create);


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