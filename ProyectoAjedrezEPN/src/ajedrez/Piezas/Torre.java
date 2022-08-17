package ajedrez.Piezas;
import ajedrez.Partida.*;

import java.util.ArrayList;

public class Torre extends Pieza {
    private boolean primerMovimiento;
    public Torre(ColorPiezas color) {
        super(color,5);
        this.primerMovimiento = true;


    }

    /*
    *
    * */


    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "T";
        }else{
            return "t";
        }
    }
    public ArrayList<ArrayList<int[]>> obtenerListaDeMovimientosLegales(Movimiento moviemiento){
        return null;
    }

}
