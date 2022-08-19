package ajedrez.piezas;
import ajedrez.partida.*;

import java.util.ArrayList;
import java.util.Vector;

public class Dama extends Pieza{
    public Dama(ColorPiezas color) {
        super(color,9);
    }
    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "D";
        }else{
            return "d";
        }
    }
    public ArrayList<ArrayList<int[]>> obtenerListaDeMovimientosLegales(Movimiento moviemiento){
        return listaDeMovimientosLegales;
    }
}
