package Piezas;
import Partida.*;
import java.util.Vector;


public class Caballo extends Pieza {
    public Caballo(ColorPiezas color) {
        super(color,3);
    }

    @Override
    public String toString() {
        return "C";
    }
}
