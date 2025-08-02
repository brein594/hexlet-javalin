package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.MainPageSession;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {
    public static void build(Context ctx) {
        ctx.render("sessions/build.jte");
    }
    public static void create(Context ctx) {
        var nickname = ctx.formParam("nickname");
        var password = ctx.formParam("password");
        ctx.sessionAttribute("currentUser", nickname);
        ctx.redirect("/s");
    }
    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/s");
    }
}
