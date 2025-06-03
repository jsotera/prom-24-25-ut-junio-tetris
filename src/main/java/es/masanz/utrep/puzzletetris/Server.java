package es.masanz.utrep.puzzletetris;

import es.masanz.utrep.puzzletetris.controller.TetrisController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinFreemarker;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Server {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinFreemarker());
        }).start(4567);

        app.get("/", TetrisController::inicio);

    }

}
