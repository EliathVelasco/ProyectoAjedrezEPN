package ajedrez.piezas;
import ajedrez.excepciones.*;
import ajedrez.partida.*;
import java.util.ArrayList;

public class Peon extends Pieza {
    private boolean primerMovimiento;

    public Peon(ColorPiezas color) {
        super(color, 1);
        this.primerMovimiento = true;
    }

    @Override
    public String toString() {
        if (color == ColorPiezas.BLANCAS) {
            return "P";
        } else {
            return "p";
        }
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles (Movimiento movimiento) throws CoronacionAvanzando, CoronacionCapturando{

        if (color == ColorPiezas.BLANCAS) {
            if (movimiento.getFilaFinal() == 7) {
                asignarTipoDeCoronacion(movimiento);
            }
        }else{
            if (movimiento.getFilaFinal() == 0){
                asignarTipoDeCoronacion(movimiento);
            }
        }

        /*
        * movimientoDeCapturaNegra
        * movimientoDeCapturaBlanca
        *
        *
        * */
        return listaDeCoordenadasPosibles;
    }

    private void asignarTipoDeCoronacion(Movimiento movimiento) throws CoronacionAvanzando, CoronacionCapturando{
        if (movimiento.getColumnaInicial() == movimiento.getColumnaFinal()) {
            throw new CoronacionAvanzando();
        } else {
            throw new CoronacionCapturando();
        }

    }
    public void quitarPrimerMovimiento(){
        this.primerMovimiento=false;
    }
}