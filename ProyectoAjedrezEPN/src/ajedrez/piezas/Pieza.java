package ajedrez.piezas;
import ajedrez.excepciones.*;
import ajedrez.partida.*;
import java.util.ArrayList;

public abstract class Pieza {
    private final int valorDePieza;
    protected ColorPiezas color;
    protected ArrayList<ArrayList<int []>> coordenadasPosibles;

    public Pieza(ColorPiezas color, int valorDePieza) {
        this.valorDePieza = valorDePieza;
        this.color = color;
        this.coordenadasPosibles = new ArrayList<>();

    }

    public abstract ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento) throws EnroqueCorto, EnroqueLargo, CoronacionAvanzando, CoronacionCapturando;
    public ColorPiezas getColor(){
        return color;
    }
}
