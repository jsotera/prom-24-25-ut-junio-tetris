package es.masanz.utrep.puzzletetris;

import es.masanz.utrep.puzzletetris.model.Pieza;
import es.masanz.utrep.puzzletetris.model.Puzzle;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(10, 10);
        Pieza pieza = puzzle.generarPieza();
        mostrarMenu(pieza, puzzle);
    }

    public static void mostrarMenu(Pieza pieza, Puzzle puzzle){
        System.out.println("¿Qué hacer? [s - descender (por defecto), w - descenso absoluto, a - izquierda, d - derecha, r - rotar]");
        puzzle.mostrarTablero();
        String respuesta = scanner.nextLine();
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
            mostrarMenu(pieza, puzzle);
        } else {
            finDePartida(puzzle);
        }
    }

    private static void finDePartida(Puzzle puzzle) {
        System.out.println("Has hecho lo que has podido, eres un paquete:");
        puzzle.mostrarTablero();
    }

}