package ajedrez.partida;

import ajedrez.piezas.*;
import ajedrez.excepciones.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Tablero {
    private static final int LARGO_TABLERO = 8;
    private static final int ANCHO_TABLERO = 8;
    private Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[LARGO_TABLERO][ANCHO_TABLERO];
        colocarPiezasEnElTablero();
    }

    public void hacerMovimiento(Movimiento movimiento) throws MovimientoInvalido {
        try {
            if (movimientoEsValido(movimiento)) {
                if (movimientoEsUnaCaptura(movimiento)) {
                    capturarPieza(movimiento);
                } else {
                    moverPieza(movimiento);
                }
                quitarPrimerMovimiento(movimiento);
            }
        } catch (EnroqueCorto ec) {
            hacerEnroqueCorto(movimiento);
        } catch (EnroqueLargo el) {
            hacerEnroqueLargo(movimiento);
        } catch (CoronacionAvanzando ca) {
            hacerCoronacionAvanzando(movimiento);
        } catch (CoronacionCapturando cc) {
            hacerCoronacionCapturando(movimiento);
        }
    }

    private void quitarPrimerMovimiento(Movimiento movimiento) {
        if (casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza() instanceof Peon){
            ((Peon) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).quitarPrimerMovimiento();
        }
        if (casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza() instanceof Torre){
            ((Torre) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).quitarPrimerMovimiento();
        }
        if (casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza() instanceof Rey){
            ((Rey) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).quitarPrimerMovimiento();
        }
    }

    private void hacerCoronacionCapturando(Movimiento movimiento) {
        
    }

    private void hacerCoronacionAvanzando(Movimiento movimiento) {
    }

    private void hacerEnroqueLargo(Movimiento movimiento) throws MovimientoInvalido {
        if(movimiento.getColorDeJugador() == ColorPiezas.BLANCAS){
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{0,3});
            listaACorroborar.add(new int[]{0,2});
            listaACorroborar.add(new int[]{0,1});

            hacerEnroque(movimiento, listaACorroborar);
        }else{
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{7,3});
            listaACorroborar.add(new int[]{7,2});
            listaACorroborar.add(new int[]{7,1});

            hacerEnroque(movimiento, listaACorroborar);
        }
    }

    private void capturarPieza(Movimiento movimiento) {
        casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].setPieza(casillas[movimiento.coordenadasIniciales[0]][movimiento.coordenadasIniciales[1]].getPieza());
        casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].quitarPieza();

    }

    private boolean movimientoEsUnaCaptura(Movimiento movimiento) throws MovimientoInvalido {
        if (casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza() instanceof Peon) {
            return verSiElPeonRealizaUnaCaptura(movimiento);
        }
        return casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].hayPieza() && colorDePiezaDiferente(movimiento);
    }



    private boolean colorDePiezaDiferente(Movimiento movimiento) {
        return casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza().getColor() != casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza().getColor();
    }

    private void hacerEnroqueCorto(Movimiento movimiento) throws MovimientoInvalido {
        if(movimiento.getColorDeJugador() == ColorPiezas.BLANCAS){
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{0,5});
            listaACorroborar.add(new int[]{0,6});

            hacerEnroque(movimiento, listaACorroborar);
        }else{
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{7,5});
            listaACorroborar.add(new int[]{7,6});

            hacerEnroque(movimiento, listaACorroborar);
        }
    }

    private void hacerEnroque(Movimiento movimiento, ArrayList<int[]> listaACorroborar) throws MovimientoInvalido {
        for (int i = 0; i < listaACorroborar.size(); i++) {
            if (casillas[listaACorroborar.get(i)[0]][listaACorroborar.get(i)[1]].hayPieza()) {
                throw new MovimientoInvalido("No se puede realizar el enroque");
            }
        }

        if(!esElPrimerMovimientoDelReyYLaTorre(movimiento)){
            throw new MovimientoInvalido("No se puede realizar el enroque");
        }

        casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]-1].setPieza(casillas[movimiento.coordenadasIniciales[0]][movimiento.coordenadasIniciales[1]].getPieza());
        casillas[movimiento.coordenadasIniciales[0]][movimiento.coordenadasIniciales[1]+1].setPieza(casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza());
        casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].quitarPieza();
        casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].quitarPieza();
    }

    private boolean esElPrimerMovimientoDelReyYLaTorre(Movimiento movimiento) {
        return ((Rey) casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza()).esSuPrimerMovimiento() && casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza() instanceof Torre && ((Torre) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).esSuPrimerMovimiento();
    }


    private boolean movimientoEsValido(Movimiento movimiento) throws EnroqueCorto, EnroqueLargo, CoronacionCapturando, CoronacionAvanzando, MovimientoInvalido {
        System.out.println(movimiento);

        if (!(hayPiezaParaMover(movimiento))) {
            throw new MovimientoInvalido("No hay pieza en la coordenada inicial pendejo");
        }
        if (!(laPiezaQueQuiereMoverEsDeSuColor(movimiento))) {
            throw new MovimientoInvalido("La pieza que quiere mover no es de su color");
        }

        ArrayList<ArrayList<int[]>> listaDeCoordenadas;
        listaDeCoordenadas = casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza().obtenerListaDeCoordenadasPosibles(movimiento);
        if (!(laPiezaPuedeIrAEsaCoordenada(movimiento, listaDeCoordenadas))) {
            throw new MovimientoInvalido("La pieza no se puede mover a la coordenada ingresada");
        }
        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            System.out.print("Lista#" + i + "{");
            for (int j = 0; j < listaDeCoordenadas.get(i).size(); j++) {
                System.out.print("" + j + Arrays.toString(listaDeCoordenadas.get(i).get(j)));
            }
            System.out.print("}\n");
        }
        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            if (estaListaContineLaJugadaFinal(listaDeCoordenadas.get(i), movimiento.getCoordenadasFinales())) {
                for (int j = 0; j < listaDeCoordenadas.get(i).size(); j++) {
                    if (Arrays.equals(listaDeCoordenadas.get(i).get(j), movimiento.getCoordenadasFinales())) {
                        break;
                    }
                    if (casillas[listaDeCoordenadas.get(i).get(j)[0]][listaDeCoordenadas.get(i).get(j)[1]].hayPieza()) {
                        throw new MovimientoInvalido("Existe una pieza entre la coordenada inicial y final");
                    }

                }
            }
        }
        return true;
    }

    private boolean laPiezaPuedeIrAEsaCoordenada(Movimiento movimiento, ArrayList<ArrayList<int[]>> listaDeCoordenadas) {
        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            if (estaListaContineLaJugadaFinal(listaDeCoordenadas.get(i), movimiento.getCoordenadasFinales())) {
                return true;
            }
        }
        return false;
    }

    private boolean hayPiezaParaMover(Movimiento movimiento) {
        return casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].hayPieza();
    }

    private boolean laPiezaQueQuiereMoverEsDeSuColor(Movimiento movimiento) {
        return movimiento.getColorDeJugador() == casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza().getColor();
    }

    private boolean estaListaContineLaJugadaFinal(ArrayList<int[]> ints, int[] coordenadasFinales) {
        for (int i = 0; i < ints.size(); i++) {
            if (ints.get(i)[0] == coordenadasFinales[0] && ints.get(i)[1] == coordenadasFinales[1]) {
                return true;
            }
        }
        return false;
    }
    private boolean verSiElPeonRealizaUnaCaptura(Movimiento movimiento) throws MovimientoInvalido {
        if (movimiento.getColumnaInicial() != movimiento.getColumnaFinal() && casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].hayPieza() && colorDePiezaDiferente(movimiento)) {
            return true;
        } else if (movimiento.getColumnaInicial() == movimiento.getColumnaFinal()) {
            return false;
        } else {
            throw new MovimientoInvalido("Peon no puede capturar, porque no hay pieza");
        }
    }
    private void moverPieza(Movimiento movimiento) {
        casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].setPieza(casillas[movimiento.coordenadasIniciales[0]][movimiento.coordenadasIniciales[1]].getPieza());
        casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].quitarPieza();
    }
    private void dejarTableroVacio() {
        for (int i=0;i<LARGO_TABLERO;i++){
            for (int j =0; j<ANCHO_TABLERO;j++){
                casillas[i][j] = new Casilla();
            }
        }
    }
    private void colocarPiezasEnElTablero() {
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
}

