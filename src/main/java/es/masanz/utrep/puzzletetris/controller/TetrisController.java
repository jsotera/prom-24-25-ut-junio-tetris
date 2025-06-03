package es.masanz.utrep.puzzletetris.controller;

import es.masanz.utrep.puzzletetris.model.Pieza;
import es.masanz.utrep.puzzletetris.model.Puzzle;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class TetrisController {

    private static Puzzle puzzle;
    private static Pieza pieza;

    public static void inicio(Context context) {

        if(puzzle==null){
            puzzle = new Puzzle(10, 10);
        }

        if(pieza==null){
            pieza = puzzle.generarPieza();
        }

        String[][] tablero = puzzle.getTablero();

        Map<String, Object> model = new HashMap<>();
        model.put("tablero", tablero);
        model.put("pieza", pieza);



        context.render("templates/index.ftl", model);

    }

}
