package ajedrez.piezas;
import ajedrez.partida.*;
import java.util.ArrayList;

import static ajedrez.Main.LARGO_TABLERO;

public class Dama extends Pieza{
    public Dama(ColorPiezas color) {
        super(color,9,LARGO_TABLERO);
    }
    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "D";
        }else{
            return "d";
        }
    }
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento){

        listaPadreDeCoordenadasPosibles.clear();
        obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(movimiento);
        obtenerCoordenadasPosiblesDeManeraVerticalYHorizontalSegunElAlcanceDeLaPieza(movimiento);

        return listaPadreDeCoordenadasPosibles;
    }
}
