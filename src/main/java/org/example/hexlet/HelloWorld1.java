package org.example.hexlet;
import io.javalin.Javalin;

public class HelloWorld1 {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParam("id");
            ctx.contentType("html");
            ctx.result("<h1>" + id + "</h1>");
        });

        app.start(7072);
    }
}
