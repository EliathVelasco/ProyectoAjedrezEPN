package Piezas;
import  Partida.*;


public class Alfil extends Pieza{
    public Alfil(ColorPiezas color) {
        super(color, 3);
    }

    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "A";
        }else{
            return "a";
        }
    }
}