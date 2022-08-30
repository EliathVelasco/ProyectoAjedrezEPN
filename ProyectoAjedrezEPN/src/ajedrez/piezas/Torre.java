package ajedrez.piezas;
import ajedrez.excepciones.EnroqueCorto;
import ajedrez.excepciones.EnroqueLargo;
import ajedrez.partida.*;
import java.util.ArrayList;

import static ajedrez.Main.ANCHO_TABLERO;

public class Torre extends Pieza {
    private boolean primerMovimiento;
    public Torre(ColorPiezas color) {
        super(color,5,ANCHO_TABLERO);
        this.primerMovimiento = true;
    }
    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "T";
        }else{
            return "t";
        }
    }
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Movimiento movimiento){
        listaPadreDeCoordenadasPosibles.clear();
        obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(movimiento);
        return listaPadreDeCoordenadasPosibles;
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Movimiento movimiento) throws EnroqueLargo, EnroqueCorto {
        return obtenerListaDeCoordenadasDondePuedeIr(movimiento);
    }

    public boolean esSuPrimerMovimiento(){
        return primerMovimiento;
    }

    public void quitarPrimerMovimiento() {
        primerMovimiento = false;
    }
}