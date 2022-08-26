package ajedrez.piezas;

import ajedrez.partida.*;

import java.util.ArrayList;
import static ajedrez.Main.LARGO_TABLERO;

public class Alfil extends Pieza {
    public Alfil(ColorPiezas color) {
        super(color, 3,LARGO_TABLERO);
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento) {
        listaPadreDeCoordenadasPosibles.clear();
        obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(movimiento);
        return listaPadreDeCoordenadasPosibles;
    }

    @Override
    public String toString() {
        if (color == ColorPiezas.BLANCAS) {
            return "A";
        } else {
            return "a";
        }
    }
}