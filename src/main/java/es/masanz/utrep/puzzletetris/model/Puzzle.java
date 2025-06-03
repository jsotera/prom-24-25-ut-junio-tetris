package es.masanz.utrep.puzzletetris.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle implements Cloneable {

    private String[][] tablero;
    private int filas, columnas;
    private Pieza piezaJuego;
    private int filaPieza, columnaPieza;
    private List<PiezaCantidad> contadorPiezas;

    public Puzzle(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.tablero = new String[filas][columnas];
        this.contadorPiezas = new ArrayList<>();
    }

    public Pieza generarPieza() {
        TipoPieza[] tipoPiezas = TipoPieza.values();
        TipoPieza tipoPiezaRandom = tipoPiezas[(int) (Math.random()*tipoPiezas.length)];
        Pieza pieza = new Pieza(tipoPiezaRandom);
        piezaJuego = pieza;
        filaPieza = 0;
        columnaPieza = (tablero[0].length - pieza.getForma()[0].length) / 2;
        boolean sePuedeColocar = validarPieza(piezaJuego, filaPieza, columnaPieza);
        if(sePuedeColocar) {
            contarPieza(pieza);
            return pieza;
        } else {
            return null;
        }
    }

    private void contarPieza(Pieza pieza) {
        boolean existe = false;
        for (PiezaCantidad contadorPieza : contadorPiezas) {
            if(contadorPieza.getPieza().equals(pieza)){
                contadorPieza.aumentarCantidad();
                existe = true;
                break;
            }
        }
        if(!existe) {
            PiezaCantidad piezaCantidad = new PiezaCantidad(pieza);
            contadorPiezas.add(piezaCantidad);
        }

    }

    public boolean validarPieza(Pieza pieza, int fila, int columna) {
        // validar dimensiones del tablero
        int columnasTablero = tablero[0].length;
        int columnasPieza = pieza.getForma()[0].length;
        if(columna > (columnasTablero - columnasPieza)){
            return false;
        }
        if(columna < 0){
            return false;
        }
        int filasTablero = tablero.length;
        int filasPieza = pieza.getForma().length;
        if(fila > (filasTablero - filasPieza)){
            return false;
        }
        if(fila < 0){
            return false;
        }
        // validar que el tablero tiene las casilla necesarias libres
        for (int filaPieza = 0; filaPieza < pieza.getForma().length; filaPieza++) {
            for (int columnaPieza = 0; columnaPieza < pieza.getForma()[0].length; columnaPieza++) {
                if(pieza.getForma()[filaPieza][columnaPieza] == 1){
                    if(tablero[fila+filaPieza][columna+columnaPieza]!=null) {
                        return false;
                    }
                }
            }
        }
        //si to va bien, genial
        return true;
    }

    public boolean descender() {
        boolean validarDescenso = validarPieza(piezaJuego, filaPieza+1, columnaPieza);
        if(validarDescenso) {
            filaPieza++;
        } else {
            colocarPieza(piezaJuego, filaPieza, columnaPieza);
            eliminarFilas();
        }
        return validarDescenso;
    }

    private void eliminarFilas() {
        for (int fila = 0; fila < tablero.length; fila++) {
            boolean eliminarFila = true;
            for (int columna = 0; columna < tablero[0].length; columna++) {
                if(tablero[fila][columna] == null) {
                    eliminarFila = false;
                }
            }
            if(eliminarFila){
                desplazarFilas(fila);
            }
        }
    }

    private void desplazarFilas(int hastaFila) {
        for (int fila = hastaFila; fila >= 1; fila--) {
            for (int columna = 0; columna < tablero[0].length; columna++) {
                tablero[fila][columna] = tablero[fila-1][columna];
            }
        }
        for (int columna = 0; columna < tablero[0].length; columna++) {
            tablero[0][columna] = null;
        }
    }

    public boolean desplazamientoIzquierda(Pieza pieza) {
        boolean validarDesplazamiento = validarPieza(piezaJuego, filaPieza, columnaPieza-1);
        if(validarDesplazamiento) {
            columnaPieza--;
        }
        return validarDesplazamiento;
    }

    public boolean desplazamientoDerecha(Pieza pieza) {
        boolean validarDesplazamiento = validarPieza(piezaJuego, filaPieza, columnaPieza+1);
        if(validarDesplazamiento) {
            columnaPieza++;
        }
        return validarDesplazamiento;
    }

    public void rotar(Pieza pieza) {
        Pieza piezaClonada = pieza.clone();
        piezaClonada.rotar();
        boolean puedoRotar = validarPieza(piezaClonada, filaPieza, columnaPieza);
        if(puedoRotar){
            pieza.rotar();
        }
    }

    public boolean colocarPieza(Pieza pieza, int fila, int columna) {
        if(validarPieza(pieza, fila, columna)){
            for (int filaPieza = 0; filaPieza < pieza.getForma().length; filaPieza++) {
                for (int columnaPieza = 0; columnaPieza < pieza.getForma()[0].length; columnaPieza++) {
                    if(pieza.getForma()[filaPieza][columnaPieza] == 1){
                        tablero[fila+filaPieza][columna+columnaPieza] = pieza.getColor()+pieza.getLetra()+Pieza.RESET;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean descolocarPieza(Pieza pieza, int fila, int columna) {
        for (int filaPieza = 0; filaPieza < pieza.getForma().length; filaPieza++) {
            for (int columnaPieza = 0; columnaPieza < pieza.getForma()[0].length; columnaPieza++) {
                if(pieza.getForma()[filaPieza][columnaPieza] == 1){
                    tablero[fila+filaPieza][columna+columnaPieza] = null;
                }
            }
        }
        return true;
    }

    public boolean tableroCompletado() {
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[0].length; columna++) {
                String contenido = tablero[fila][columna];
                if(contenido==null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void mostrarTablero() {
        colocarPieza(piezaJuego, filaPieza, columnaPieza);
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[0].length; columna++) {
                String contenido = tablero[fila][columna];
                System.out.print("[");
                if(contenido==null){
                    System.out.print(" ");
                } else {
                    System.out.print(contenido);
                }
                System.out.print("]");
            }
            System.out.println();
        }
        descolocarPieza(piezaJuego, filaPieza, columnaPieza);
    }

    @Override
    public Puzzle clone() {
        Puzzle nuevoPuzzle = new Puzzle(this.filas, this.columnas);
        String[][] nuevoTablero = nuevoPuzzle.getTablero();
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[0].length; columna++) {
                nuevoTablero[fila][columna] = tablero[fila][columna];
            }
        }
        nuevoPuzzle.setPiezaJuego(piezaJuego.clone());
        nuevoPuzzle.setFilaPieza(filaPieza);
        nuevoPuzzle.setColumnaPieza(columnaPieza);
        return nuevoPuzzle;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Pieza getPiezaJuego() {
        return piezaJuego;
    }

    public void setPiezaJuego(Pieza piezaJuego) {
        this.piezaJuego = piezaJuego;
    }

    public int getFilaPieza() {
        return filaPieza;
    }

    public void setFilaPieza(int filaPieza) {
        this.filaPieza = filaPieza;
    }

    public int getColumnaPieza() {
        return columnaPieza;
    }

    public void setColumnaPieza(int columnaPieza) {
        this.columnaPieza = columnaPieza;
    }

    public List<PiezaCantidad> getContadorPiezas() {
        return contadorPiezas;
    }

    public void setContadorPiezas(List<PiezaCantidad> contadorPiezas) {
        this.contadorPiezas = contadorPiezas;
    }
}
