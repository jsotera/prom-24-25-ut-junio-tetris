package es.masanz.utrep.puzzletetris.model;

public enum TipoPieza {

    T(new int[][]{ {1, 1, 1}, {0, 1, 0} }, "\u001B[31m", "T"),
    I(new int[][]{ {1}, {1}, {1}, {1} }, "\u001B[32m", "I"),
    O(new int[][]{ {1, 1}, {1, 1} }, "\u001B[33m", "O"),
    L(new int[][]{ {1, 0}, {1, 0}, {1, 1} }, "\u001B[34m", "L"),
    J(new int[][]{ {0, 1}, {0, 1}, {1, 1} }, "\u001B[35m", "J"),
    S(new int[][]{ {0, 1, 1}, {1, 1, 0} }, "\u001B[36m", "S"),
    Z(new int[][]{ {1, 1, 0}, {0, 1, 1} }, "\u001B[91m", "Z");

    private final int[][] forma;
    private final String color;
    private final String letra;

    TipoPieza(int[][] forma, String color, String letra){
        this.forma = forma;
        this.color = color;
        this.letra = letra;
    }

    public int[][] getForma() {
        return forma;
    }

    public String getColor() {
        return color;
    }

    public String getLetra() {
        return letra;
    }
}
