package es.masanz.utrep.puzzletetris.model;

public class Pieza implements Cloneable{

    public static final String RESET = "\u001B[0m";

    private int[][] forma;
    private String color;
    private String letra;

    public Pieza(TipoPieza tipoPieza) {
        this.forma = tipoPieza.getForma();
        this.color = tipoPieza.getColor();
        this.letra = tipoPieza.getLetra();
    }

    public Pieza(){

    }

    public void rotar(){
        int filas = forma.length;
        int columnas = forma[0].length;
        int[][] aux = new int[columnas][filas];

        for (int fila = 0; fila < forma.length; fila++) {
            for (int columna = 0; columna < forma[0].length; columna++) {
                int columnasAux = aux[0].length;
                aux[columna][columnasAux-1-fila] = forma[fila][columna];
            }
        }

        forma = aux;
    }

    public void mostrarPieza() {
        for (int fila = 0; fila < forma.length; fila++) {
            for (int columna = 0; columna < forma[0].length; columna++) {
                System.out.print("[");
                int valor = forma[fila][columna];
                if(valor==0){
                    System.out.print(" ");
                } else {
                    System.out.print(color+letra+RESET);
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }

    @Override
    protected Pieza clone() {
        Pieza clon = new Pieza();
        int[][] nuevaForma = new int[forma.length][forma[0].length];
        for (int fila = 0; fila < forma.length; fila++) {
            for (int columna = 0; columna < forma[0].length; columna++) {
                nuevaForma[fila][columna] = forma[fila][columna];
            }
        }
        clon.setForma(nuevaForma);
        clon.setLetra(this.getLetra());
        clon.setColor(this.getColor());
        return clon;
    }

    public int[][] getForma() {
        return forma;
    }

    public void setForma(int[][] forma) {
        this.forma = forma;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}
