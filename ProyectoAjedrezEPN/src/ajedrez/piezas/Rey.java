package ajedrez.piezas;
import ajedrez.partida.*;
import java.util.ArrayList;

import java.util.ArrayList;

public class Rey extends Pieza {
    private boolean primerMovimiento;

    public Rey(ColorPiezas color) {
        super(color, 0);
        primerMovimiento = true;
    }

    @Override
    public String toString() {
        if (this.color == ColorPiezas.BLANCAS) {
            return "R";
        } else {
            return "r";
        }
    }
    /*
    * El rey tampoco clava las piezas, por lo que hay que ver que mismo hacemos aqui*/

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento) {
        return null;
    }
}
