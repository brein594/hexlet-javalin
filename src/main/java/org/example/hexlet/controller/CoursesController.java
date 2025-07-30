package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.repository.CourseRepository;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {

    public static void index(Context ctx) {
        var header = "course programming";
        var term = ctx.queryParam("term");
        var coursesPage = CourseRepository.getCourses();
        if (term != null) {
            coursesPage.clear();
            for (var c : CourseRepository.getCourses()) {
                if (StringUtils.startsWithIgnoreCase(c.getName(), term)) {
                    coursesPage.add(c);
                }
            }
        }
        var page = new CoursesPage(coursesPage, header,term);
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        var page = new CoursePage(CourseRepository.getCourses().get(id - 1));
        ctx.render("courses/show.jte", model("page", page));
    }
}
