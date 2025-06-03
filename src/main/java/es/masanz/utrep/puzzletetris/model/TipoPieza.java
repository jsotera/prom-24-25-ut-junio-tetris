package es.masanz.utrep.puzzletetris.model;

public enum TipoPieza {

    T(new int[][]{ {1, 1, 1}, {0, 1, 0} }, "", "T"),
    I(new int[][]{ {1}, {1}, {1}, {1} }, "", "I"),
    O(new int[][]{ {1, 1}, {1, 1} }, "", "O"),
    L(new int[][]{ {1, 0}, {1, 0}, {1, 1} }, "", "L"),
    J(new int[][]{ {0, 1}, {0, 1}, {1, 1} }, "", "J"),
    S(new int[][]{ {0, 1, 1}, {1, 1, 0} }, "", "S"),
    Z(new int[][]{ {1, 1, 0}, {0, 1, 1} }, "", "Z");

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
