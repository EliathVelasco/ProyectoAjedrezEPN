package ajedrez.piezas;
import ajedrez.partida.*;

import java.util.ArrayList;

public class Rey extends Pieza{
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

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeMovimientosLegales(Movimiento movimiento) {
        return null;
    }
}
