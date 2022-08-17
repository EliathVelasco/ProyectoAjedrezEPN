package ajedrez.Piezas;
import ajedrez.Partida.*;
import ajedrez.IMovimientoEspecial;

import java.util.ArrayList;

public class Rey extends Pieza implements IMovimientoEspecial {
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
    public void realizarMovimientoEspecial(Movimiento movimiento) {

    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeMovimientosLegales(Movimiento moviemiento) {
        return null;
    }
}
