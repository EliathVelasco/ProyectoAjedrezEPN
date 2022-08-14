package Piezas;
import Partida.*;

public class Dama extends Pieza{
    public Dama(ColorPiezas color) {
        super(color,9);
    }
    @Override
    public String toString() {
        return "D";
    }
}
