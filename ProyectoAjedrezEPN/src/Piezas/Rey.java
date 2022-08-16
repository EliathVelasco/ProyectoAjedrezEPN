package Piezas;
import  Partida.*;

public class Rey extends Pieza {
    private boolean primerMovimiento;

    public Rey(ColorPiezas color) {
        super(color, 0);
        this.color = color;
        primerMovimiento = true;
    }

    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "R";
        }else{
            return "r";
        }
    }
}
