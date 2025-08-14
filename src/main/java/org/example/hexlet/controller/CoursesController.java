package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository1;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {

    public static void index(Context ctx) {
        var header = "course programming";
        var term = ctx.queryParam("term");
        var coursesPage = CourseRepository1.getCourses();
        if (term != null) {
            coursesPage.clear();
            for (var c : CourseRepository1.getCourses()) {
                if (StringUtils.startsWithIgnoreCase(c.getName(), term)) {
                    coursesPage.add(c);
                }
            }
        }
        var page = new CoursesPage(coursesPage, header,term);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        var page = new CoursePage(CourseRepository1.getCourses().get(id - 1));

        ctx.render("courses/show.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var description = ctx.formParam("description");
        var id = CourseRepository1.getCourses().size();
        var course = new Course(id ,name, description);
        CourseRepository1.save(course);
        ctx.sessionAttribute("flash", "Course has been created!");
        ctx.redirect(NamedRoutes.coursePath());
    }

    public static void build(Context ctx) {

        ctx.render("courses/build.jte");
    }
}
