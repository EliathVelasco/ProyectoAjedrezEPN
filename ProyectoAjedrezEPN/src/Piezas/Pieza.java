package Piezas;
import  Partida.*;

public abstract class Pieza {
    private int valorDePieza;
    protected ColorPiezas color;

    public Pieza(ColorPiezas color, int valorDePieza) {
        this.valorDePieza = valorDePieza;
        this.color = color;
    }
}
