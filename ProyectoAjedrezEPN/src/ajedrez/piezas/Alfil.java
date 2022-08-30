package ajedrez.piezas;

import ajedrez.partida.*;

import java.util.ArrayList;
import static ajedrez.Main.LARGO_TABLERO;

public class Alfil extends Pieza {
    public Alfil(ColorPiezas color) {
        super(color, 3,LARGO_TABLERO);
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Movimiento movimiento) {
        listaPadreDeCoordenadasPosibles.clear();
        obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(movimiento);
        return listaPadreDeCoordenadasPosibles;
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Movimiento movimiento) {
        return obtenerListaDeCoordenadasDondePuedeIr(movimiento);
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Coordenada coordenadaInicial) {
        return obtenerListaDeCoordenadasDondePuedeIr(coordenadaInicial);
    }

    private ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Coordenada coordenadaInicial) {
        listaPadreDeCoordenadasPosibles.clear();
        obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(coordenadaInicial);
        return listaPadreDeCoordenadasPosibles;
    }


    @Override
    public String toString() {
        if (color == ColorPiezas.BLANCAS) {
            return "A";
        } else {
            return "a";
        }
    }
}