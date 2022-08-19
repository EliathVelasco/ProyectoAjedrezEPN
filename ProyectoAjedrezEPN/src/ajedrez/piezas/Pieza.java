package ajedrez.piezas;
import ajedrez.partida.*;
import java.util.ArrayList;


public abstract class Pieza {

    private final int valorDePieza;
    protected ColorPiezas color;
    protected ArrayList<ArrayList<int []>> movimientosPosibles;

    public Pieza(ColorPiezas color, int valorDePieza) {
        this.valorDePieza = valorDePieza;
        this.color = color;
        this.movimientosPosibles = new ArrayList<>();

    }

    public abstract ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento);
}
