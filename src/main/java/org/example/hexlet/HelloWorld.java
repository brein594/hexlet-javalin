package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;
import static java.util.stream.Collectors.toList;

import io.javalin.validation.ValidationException;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.User;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.repository.UserRepository;

import java.util.ArrayList;
import java.util.Objects;


public class HelloWorld {
    public static void main(String[] args) {
        var oneCourse = new Course(1, "1", "oneCourse");
        var twoCourse = new Course(2, "2", "twoCourse");
        var courses = new ArrayList<Course>();
        courses.add(oneCourse);
        courses.add(twoCourse);

        UserRepository.initUser();

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> ctx.render("index.jte")); //вывод из шаблона
        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var page = new CoursePage(courses.get(id - 1));
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get(NamedRoutes.coursePath(), ctx -> {
            //var courses = /* Список курсов извлекается из базы данных */
            var header = "course programming";
            var term = ctx.queryParam("term");
            ArrayList<Course> coursesPage = new ArrayList<>(courses);
            if (term != null) {
                coursesPage.clear();
                for (var c : courses) {
                    if (StringUtils.startsWithIgnoreCase(c.getName(), term)) {
                        coursesPage.add(c);
                    }
                }
            }
            var page = new CoursesPage(coursesPage, header, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get(NamedRoutes.buildUserPath(), UsersController::build //ctx -> {
            //var page = new BuildUserPage();
            //ctx.render("users/build.jte", model("page", page));
        //}
        );

        app.get(NamedRoutes.userPath("{id}"), UsersController::show //ctx -> {
          //  var id = ctx.pathParamAsClass("id", Integer.class).get();
           // var page = new UserPage(users.get(id - 1));
          //  ctx.render("users/show.jte", model("page", page));
        //}
        );

        app.get(NamedRoutes.userPath(), UsersController::index
                //ctx -> {
            //var courses = /* Список  извлекается из базы данных */
            //var page = new UsersPage(users);
            //ctx.render("users/index.jte", model("page", page));
        //}
        );

        app.post(NamedRoutes.userPath(), UsersController::create /*ctx -> {

            var id = users.size() + 1;
            var firsName = ctx.formParam("firstName");
            var lastName = ctx.formParam("lastName");

            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                        .check(value -> value.length() > 6, "Длина пароля больше 6 символов")
                        .get();
                var user = new User(id, firsName, lastName, password);
                users.add(user);
                ctx.redirect(NamedRoutes.userPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(firsName, lastName, e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
        }*/
        );


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