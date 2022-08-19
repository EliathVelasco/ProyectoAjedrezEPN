package ajedrez.Piezas;
import ajedrez.Partida.*;

import java.util.ArrayList;

public class Peon extends Pieza {
    private boolean primerMovimiento;

    public Peon(ColorPiezas color) {
        super(color,1);
        this.primerMovimiento = true;
    }

    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "P";
        }else{
            return "p";
        }
    }
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento){
        return null;
    }
}