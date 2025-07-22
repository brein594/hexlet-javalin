package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        var oneCourse = new Course("1", "oneCourse");
        var twoCourse = new Course("2", "twoCourse");


        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> ctx.render("index.jte")); //вывод из шаблона
        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var page = new CoursePage(oneCourse);
            ctx.render("courses/show.jte", model("page", page));
        });



        app.get("/courses", ctx -> {
            //var courses = /* Список курсов извлекается из базы данных */
            var courses = new ArrayList<Course>();
            courses.add(oneCourse);
            courses.add(twoCourse);
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        //app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/users", ctx -> ctx.result("Get resposeble"));
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