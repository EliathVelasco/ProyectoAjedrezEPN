package ajedrez.Piezas;
import ajedrez.Partida.*;
import ajedrez.Partida.ColorPiezas;
import java.util.ArrayList;

public abstract class Pieza {
    private int valorDePieza;
    protected ColorPiezas color;

    public Pieza(ColorPiezas color, int valorDePieza) {
        this.valorDePieza = valorDePieza;
        this.color = color;
    }

    public abstract ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento);
}
