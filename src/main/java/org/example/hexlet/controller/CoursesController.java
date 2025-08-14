package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {

    public static void index(Context ctx) {
        var header = "course programming";
        var term = ctx.queryParam("term");
        try {
            var coursesPage = CourseRepository.getCourses();
            if (term != null) {
                coursesPage.clear();
                for (var c : CourseRepository.getCourses()) {
                    if (StringUtils.startsWithIgnoreCase(c.getName(), term)) {
                        coursesPage.add(c);
                    }
                }

            }
            var page = new CoursesPage(coursesPage, header, term);
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("courses/index.jte", model("page", page));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var page = new CoursePage(CourseRepository.getCourses().get(id - 1));
            ctx.render("courses/show.jte", model("page", page));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");
            var id = CourseRepository.getCourses().size();
            var course = new Course(id, name, description);
            CourseRepository.save(course);
            ctx.sessionAttribute("flash", "Course has been created!");
            ctx.redirect(NamedRoutes.coursePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void build(Context ctx) {
        ctx.render("courses/build.jte");
    }
}
