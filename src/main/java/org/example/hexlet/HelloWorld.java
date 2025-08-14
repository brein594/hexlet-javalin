package org.example.hexlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.dto.MainPageSession;
import org.example.hexlet.repository.BaseRepository;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.repository.UserRepository1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


public class HelloWorld {
    public static Javalin getApp() throws Exception {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet_project;DB_CLOSE_DELAY=-1;");

        var dataSource = new HikariDataSource(hikariConfig);
        var url = HelloWorld.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        UserRepository.initUser();
        CourseRepository.initCourse();

        app.get("/", ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var page = new MainPage(visited);
            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        }); //вывод из шаблона

        app.get(NamedRoutes.buildSessionsPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.get(NamedRoutes.destroySessionsPath("{id}"), SessionsController::destroy);

        app.get("/s", ctx -> {
            var page = new MainPageSession(ctx.sessionAttribute("currentUser"));
            ctx.render("sessions/index.jte", model("page", page));

        });
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);

        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);

        app.get(NamedRoutes.coursePath(), CoursesController::index);

        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        app.get(NamedRoutes.usersPath("{id}"), UsersController::show);

        app.get(NamedRoutes.usersPath(), UsersController::index);

        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.post(NamedRoutes.coursePath(), CoursesController::create);


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

        return app;
    }


    public static void main(String[] args) throws Exception {

        var app = getApp();
        app.start(7071);
    }
}