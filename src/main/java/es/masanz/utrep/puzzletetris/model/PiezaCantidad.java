package es.masanz.utrep.puzzletetris.model;

import org.jetbrains.annotations.NotNull;

public class PiezaCantidad implements Comparable<PiezaCantidad> {

    private Pieza pieza;
    private int cantidad;

    public PiezaCantidad(Pieza pieza) {
        this.pieza = pieza;
        this.cantidad = 1;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void aumentarCantidad() {
        this.cantidad++;
    }

    @Override
    public int compareTo(PiezaCantidad otraPieza) {
        return otraPieza.getCantidad() - this.cantidad;
    }
}
