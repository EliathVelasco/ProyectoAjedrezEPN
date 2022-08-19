package ajedrez.piezas;
import ajedrez.partida.*;

import java.util.ArrayList;

public class Caballo extends Pieza {
    public Caballo(ColorPiezas color) {
        super(color,3);
    }

    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "C";
        }else{
            return "c";
        }
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeMovimientosLegales(Movimiento moviemiento){
        return null;
    }
}
