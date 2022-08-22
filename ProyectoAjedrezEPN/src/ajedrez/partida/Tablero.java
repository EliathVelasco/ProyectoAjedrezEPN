package ajedrez.partida;

import ajedrez.piezas.*;
import ajedrez.excepciones.*;

public class Tablero {
    private static final int LARGO_TABLERO = 8;
    private static final int ANCHO_TABLERO = 8;
    private Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[LARGO_TABLERO][ANCHO_TABLERO];

        casillas[0][0] = new Casilla(new Torre(ColorPiezas.BLANCAS));
        casillas[0][7] = new Casilla(new Torre(ColorPiezas.BLANCAS));
        casillas[7][0] = new Casilla(new Torre(ColorPiezas.NEGRAS));
        casillas[7][7] = new Casilla(new Torre(ColorPiezas.NEGRAS));
        casillas[0][1] = new Casilla(new Caballo(ColorPiezas.BLANCAS));
        casillas[0][6] = new Casilla(new Caballo(ColorPiezas.BLANCAS));
        casillas[7][1] = new Casilla(new Caballo(ColorPiezas.NEGRAS));
        casillas[7][6] = new Casilla(new Caballo(ColorPiezas.NEGRAS));
        casillas[0][2] = new Casilla(new Alfil(ColorPiezas.BLANCAS));
        casillas[0][5] = new Casilla(new Alfil(ColorPiezas.BLANCAS));
        casillas[7][2] = new Casilla(new Alfil(ColorPiezas.NEGRAS));
        casillas[7][5] = new Casilla(new Alfil(ColorPiezas.NEGRAS));
        casillas[0][3] = new Casilla(new Dama(ColorPiezas.BLANCAS));
        casillas[7][3] = new Casilla(new Dama(ColorPiezas.NEGRAS));
        casillas[0][4] = new Casilla(new Rey(ColorPiezas.BLANCAS));
        casillas[7][4] = new Casilla(new Rey(ColorPiezas.NEGRAS));

        for (int i = 0; i < ANCHO_TABLERO; i++) {
            casillas[1][i] = new Casilla(new Peon(ColorPiezas.BLANCAS));
            casillas[6][i] = new Casilla(new Peon(ColorPiezas.NEGRAS));
        }

        for (int i = 2; i < LARGO_TABLERO - 2; i++) {
            for (int j = 0; j < 8; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    @Override
    public String toString() {
        String tableroCompleto = "";
        tableroCompleto += " a  b  c  d  e  f  g  h \n";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (casillas[i][j].hayPieza()) {
                    tableroCompleto += "[" + casillas[i][j] + "]";
                } else {
                    tableroCompleto += "[ ]";
                }
            }
            tableroCompleto += (i + 1);
            tableroCompleto += '\n';
        }

        return tableroCompleto;
    }

    public void hacerMovimiento(Movimiento movimiento) {
        try{
            if (movimientoEsValido(movimiento)){
                casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].setPieza(casillas[movimiento.coordenadasIniciales[0]][movimiento.coordenadasIniciales[1]].getPieza());
            }
        }catch (EnroqueCorto ec){
            hacerEn
        }catch (EnroqueLargo el){

        }catch (CoronacionAvanzando ca){

        }catch (CoronacionCapturando cc){

        }

    }

    private void hacerEnroqueLargo() {
    }

    private void hacerEnroqueCorto() {

    }

    private void hacerCoronacion(Movimiento movimiento) {

    }

    private boolean movimientoEsValido(Movimiento movimiento) {

        //ver la lista de mov y ver que el movimiento este en la lista
        return true;
    }
}

