package Piezas;
import Partida.*;

public class Torre extends Pieza {
    private boolean primerMovimiento;
    public Torre(ColorPiezas color) {
        super(color,5);
        this.primerMovimiento = true;

    }


    @Override
    public String toString() {
        return "T";
    }

}
