package Piezas;
import Partida.*;

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
}