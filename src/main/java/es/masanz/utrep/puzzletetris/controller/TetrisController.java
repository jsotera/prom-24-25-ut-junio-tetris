package es.masanz.utrep.puzzletetris.controller;

import es.masanz.utrep.puzzletetris.model.Pieza;
import es.masanz.utrep.puzzletetris.model.PiezaCantidad;
import es.masanz.utrep.puzzletetris.model.Puzzle;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TetrisController {

    private static Puzzle puzzle;
    private static Pieza pieza;

    public static void inicio(Context context) {

        if(puzzle==null){
            puzzle = new Puzzle(10, 10);
            /*
            pieza = puzzle.generarPieza();
            puzzle.colocarPieza(pieza, 0, 0);
            pieza = puzzle.generarPieza();
            puzzle.colocarPieza(pieza, 2, 2);
            pieza = puzzle.generarPieza();
            puzzle.colocarPieza(pieza, 4, 4);
            pieza = puzzle.generarPieza();
            puzzle.colocarPieza(pieza, 6, 6);
            pieza = null;
            */
        }

        if(pieza==null){
            pieza = puzzle.generarPieza();
        }

        Puzzle puzzleClon = puzzle.clone();
        puzzleClon.colocarPieza(pieza, puzzle.getFilaPieza(), puzzle.getColumnaPieza());
        String[][] tablero = puzzleClon.getTablero();

        Map<String, Object> model = new HashMap<>();

        model.put("tablero", tablero);
        model.put("pieza", pieza);

        List<PiezaCantidad> estadisticas = puzzle.getContadorPiezas();
        Collections.sort(estadisticas);

        model.put("contadorPiezas", estadisticas);

        context.render("templates/index.ftl", model);

    }

    public static void mover(Context context) {
        String respuesta = context.formParam("tecla");

        if(respuesta.equalsIgnoreCase("s")){
            boolean heDescendido = puzzle.descender();
            if(!heDescendido){
                pieza = puzzle.generarPieza();
            }
        } else if(respuesta.equalsIgnoreCase("w")){
            boolean heDescendido = puzzle.descender();
            while(heDescendido){
                heDescendido = puzzle.descender();
            }
            if(!heDescendido){
                pieza = puzzle.generarPieza();
            }
        } else if(respuesta.equalsIgnoreCase("r")){
            puzzle.rotar(pieza);
        } else if(respuesta.equalsIgnoreCase("a")){
            puzzle.desplazamientoIzquierda(pieza);
        } else if(respuesta.equalsIgnoreCase("d")){
            puzzle.desplazamientoDerecha(pieza);
        }
        if(pieza!=null) {

        } else {

        }



        context.redirect("/");
    }
}
