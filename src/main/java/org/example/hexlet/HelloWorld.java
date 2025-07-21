package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var course = new Course("1", "oneCourse");
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/users", ctx -> ctx.result("Get resposeble"));
        app.post("/user", ctx -> ctx.result("Post responseble"));
        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name);
        });

        app.get("/users/{id}/post/{postid}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class);
            var postid = ctx.pathParam("postid");
            ctx.result("user ID: " + id + " post ID: " + postid);
        });

        app.start(7070);
    }
}