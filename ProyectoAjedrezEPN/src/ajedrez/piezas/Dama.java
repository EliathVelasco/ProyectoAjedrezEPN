package ajedrez.piezas;
import ajedrez.excepciones.EnroqueCorto;
import ajedrez.excepciones.EnroqueLargo;
import ajedrez.excepciones.MovimientoInvalido;
import ajedrez.partida.*;
import java.util.ArrayList;
import java.util.Arrays;

import static ajedrez.Main.LARGO_TABLERO;

public class Dama extends Pieza{
    public Dama(ColorPiezas color) {
        super(color,9,7);
    }
    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "D";
        }else{
            return "d";
        }
    }
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Movimiento movimiento) throws MovimientoInvalido {
        if (estado == Estado.REY_EN_JAQUE){
            throw new MovimientoInvalido("El rey esta en jaque");
        }

        listaPadreDeCoordenadasPosibles.clear();
        obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(movimiento);
        obtenerCoordenadasPosiblesDeManeraVerticalYHorizontalSegunElAlcanceDeLaPieza(movimiento);

        return listaPadreDeCoordenadasPosibles;
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Coordenada coordenadasIniciales) throws MovimientoInvalido {
        if (estado == Estado.REY_EN_JAQUE){
            throw new MovimientoInvalido("El rey esta en jaque");
        }

        listaPadreDeCoordenadasPosibles.clear();
        obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(coordenadasIniciales);
        obtenerCoordenadasPosiblesDeManeraVerticalYHorizontalSegunElAlcanceDeLaPieza(coordenadasIniciales);

        return listaPadreDeCoordenadasPosibles;
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Movimiento movimiento) throws MovimientoInvalido {
        return obtenerListaDeCoordenadasDondePuedeIr(movimiento);
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Coordenada coordenadaInicial) throws MovimientoInvalido {
        return obtenerListaDeCoordenadasDondePuedeIr(coordenadaInicial);
    }







}
