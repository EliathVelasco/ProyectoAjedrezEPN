package ajedrez.partida;

import ajedrez.excepciones.*;
import ajedrez.piezas.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static ajedrez.Main.LARGO_TABLERO;
import static ajedrez.Main.ANCHO_TABLERO;


public class Tablero {
    private Casilla[][] casillas;
    private ArrayList<Pieza> piezasCapturadas;

    public Tablero() {
        this.casillas = new Casilla[LARGO_TABLERO][ANCHO_TABLERO];
        dejarTableroVacio();
        casillas[0][0] = new Casilla(new Dama(ColorPiezas.BLANCAS));
        casillas[7][5] = new Casilla(new Rey(ColorPiezas.BLANCAS));

        casillas[5][4] = new Casilla(new Rey(ColorPiezas.NEGRAS));
        casillas[0][1] = new Casilla(new Dama(ColorPiezas.NEGRAS));
    }

    public void hacerMovimiento(Movimiento movimiento) {
        try {
            try {
                if (!movimientoEsValido(movimiento)) {
                    throw new MovimientoInvalido("Jugada Invalida");
                }

                if (movimientoEsUnaCaptura(movimiento)) {
                    capturarPieza(movimiento);
                } else {
                    moverPieza(movimiento);
                }
                quitarPrimerMovimientoAPiezaQueEstaEnLasCoordenadasInicialesDelMovimiento(movimiento);
            } catch (EnroqueCorto ec) {
                hacerEnroqueCorto(movimiento);
            } catch (EnroqueLargo el) {
                hacerEnroqueLargo(movimiento);
            } catch (CoronacionAvanzando ca) {
                hacerCoronacionAvanzando(movimiento);
            } catch (CoronacionCapturando cc) {
                hacerCoronacionCapturando(movimiento);
            } finally {
            /*if (reyDelAdversarioEstaEnJaque(movimiento)) {
                ponerEstadoReyEnJaqueALasPiezas(movimiento.getColorDeJugador());
            }
            clavarPiezas();
            partidarTermino(movimiento);*/
            }
        }catch(MovimientoInvalido mi) {
            System.out.println(mi.getMessage());
        }
    }
    private void ponerEstadoReyEnJaqueALasPiezas(ColorPiezas color) {
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            for (int j = 0; j < LARGO_TABLERO; j++) {
                if (casillas[i][j].hayPieza() && casillas[i][j].getPieza().getColor() != color){
                    casillas[i][j].getPieza().ponerEstado(Estado.REY_EN_JAQUE);
                }
            }
        }
    }

    private boolean reyDelAdversarioEstaEnJaque(Movimiento movimiento) throws CoronacionAvanzando, EnroqueLargo, EnroqueCorto, CoronacionCapturando, MovimientoInvalido, JuegoInvalido {
        ArrayList<ArrayList<int[]>> listaPadreDeCoordenadas = new ArrayList<>();
        Coordenada coordenadasDelRey;
        coordenadasDelRey = obtenerCoordenadasDelReyAdversarioEnElTablero(movimiento.getColorDeJugador());

        listaPadreDeCoordenadas.addAll(obtenerTodasLasCoordenasDondePuedanComerTodasLasPiezas(movimiento.getColorDeJugador()));

        for (int i = 0; i < ANCHO_TABLERO; i++) {
            for (int j = 0; j < LARGO_TABLERO; j++) {
                int[] coordenadasIniciales = {i, j};
                movimiento.setCoordenadasIniciales(coordenadasIniciales);
                if (casillas[i][j].hayPieza() && casillas[i][j].getPieza().getColor() == movimiento.getColorDeJugador()) {
                    listaPadreDeCoordenadas.addAll(casillas[i][j].getPieza().obtenerListaDeCoordenadasDondePuedeComer(movimiento));
                }
            }
        }

        mostrarElContenidoDelArrayListDeArrayLists(listaPadreDeCoordenadas);

        for (int i = 0; i < listaPadreDeCoordenadas.size(); i++) {
            for (int j = 0; j < listaPadreDeCoordenadas.get(i).size(); j++) {
                if (casillas[listaPadreDeCoordenadas.get(i).get(j)[0]][listaPadreDeCoordenadas.get(i).get(j)[1]].hayPieza()) {
                    if (casillas[listaPadreDeCoordenadas.get(i).get(j)[0]][listaPadreDeCoordenadas.get(i).get(j)[1]].getPieza().getColor() != movimiento.getColorDeJugador()) {
                        quitarMovimientosNoAlcanzables(listaPadreDeCoordenadas.get(i), j + 1);
                    }else{
                        quitarMovimientosNoAlcanzables(listaPadreDeCoordenadas.get(i), j);
                    }
                }
            }
        }

        mostrarElContenidoDelArrayListDeArrayLists(listaPadreDeCoordenadas);

        for (int i = 0; i < listaPadreDeCoordenadas.size(); i++) {
            if(estaListaContineLaCoordenada(listaPadreDeCoordenadas.get(i), posicionDelRey)){
                System.out.println("REY EN JAQUE");
                return true;
            }
        }

        return false;
    }

    private ArrayList<ArrayList<int[]>> obtenerTodasLasCoordenasDondePuedanComerTodasLasPiezas(ColorPiezas colorDeJugador) {
        ArrayList<ArrayList<int[]>> auxiliarParaPasarTodasLasCoordenadas = new ArrayList<>();
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            for (int j = 0; j < LARGO_TABLERO; j++) {
                if (casillas[i][j].hayPieza() && casillas[i][j].getPieza().getColor() == colorDeJugador){
                    auxiliarParaPasarTodasLasCoordenadas.addAll(casillas[i][j].getPieza().obtenerListaDeCoordenadasDondePuedeComer(new Coordenada(i,j)));
                }
            }
        }
    }

    private Coordenada obtenerCoordenadasDelReyAdversarioEnElTablero(ColorPiezas colorDeJugador) throws JuegoInvalido {
        for (int i =0; i<LARGO_TABLERO;i++){
            for (int j = 0 ; j<ANCHO_TABLERO;j++){
                if (casillas[i][j].hayPieza() && casillas[i][j].getPieza().getColor() != colorDeJugador && casillas[i][j].getPieza() instanceof Rey){
                    return new Coordenada(i,j);
                }
            }
        }
        throw new JuegoInvalido("Falta un rey en el tablero");
    }

    private void mostrarElContenidoDelArrayListDeArrayLists(ArrayList<ArrayList<int[]>> listaPadreDeCoordenadas) {
        System.out.println("Lista mas grande {");
        for (int i = 0; i< listaPadreDeCoordenadas.size(); i++){
            System.out.println("\tLista chiquita #"+i+" {");
            for (int j = 0; j< listaPadreDeCoordenadas.get(i).size(); j++){
                System.out.println("\t\t"+ Arrays.toString(listaPadreDeCoordenadas.get(i).get(j)));
            }
            System.out.println("\t}");
        }
        System.out.println("}");
    }


    private void clavarPiezas() {

    }

    private void partidarTermino(Movimiento movimiento) {
        int[] posicionDelRey = new int[2];
        for (int i = 0; i < 8; i++) {
            if (encontrarAlReyEnEsaFila(movimiento, posicionDelRey, i)) {
                break;
            }
        }

        System.out.println(Arrays.toString(posicionDelRey));

    }

    private boolean encontrarAlReyEnEsaFila(Movimiento movimiento, int[] posicionDelRey, int i) {
        for (int j = 0; j < 8; j++) {
            if (casillas[i][j].hayPieza() && casillas[i][j].getPieza().getColor() != movimiento.getColorDeJugador() && casillas[i][j].getPieza() instanceof Rey) {
                posicionDelRey[0] = i;
                posicionDelRey[1] = j;
                return true;
            }
        }
        return false;
    }

    private void quitarPrimerMovimientoAPiezaQueEstaEnLasCoordenadasInicialesDelMovimiento(Movimiento movimiento) {
        if (casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza() instanceof Peon) {
            ((Peon) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).quitarPrimerMovimiento();
        }
        if (casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza() instanceof Torre) {
            ((Torre) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).quitarPrimerMovimiento();
        }
        if (casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza() instanceof Rey) {
            ((Rey) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).quitarPrimerMovimiento();
        }
    }

    private void hacerCoronacionCapturando(Movimiento movimiento) throws MovimientoInvalido {
        if (casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].hayPieza() && casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza().getColor() != movimiento.getColorDeJugador()) {
            mostrarMenuParaEscogerLaPiezaALaQueQuiereCoronar();
        } else {
            throw new MovimientoInvalido("Movimiento invalido");
        }
    }

    private void hacerCoronacionAvanzando(Movimiento movimiento) throws MovimientoInvalido {
        if (!(casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].hayPieza())) {
            mostrarMenuParaEscogerLaPiezaALaQueQuiereCoronar();
        } else {
            throw new MovimientoInvalido("No se puede avanzar el peon, hay una pieza adelante");
        }
    }

    private void mostrarMenuParaEscogerLaPiezaALaQueQuiereCoronar() {
    }

    private void hacerEnroqueLargo(Movimiento movimiento) throws MovimientoInvalido {
        if (movimiento.getColorDeJugador() == ColorPiezas.BLANCAS) {
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{0, 3});
            listaACorroborar.add(new int[]{0, 2});
            listaACorroborar.add(new int[]{0, 1});

            hacerEnroque(movimiento, listaACorroborar);
        } else {
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{7, 3});
            listaACorroborar.add(new int[]{7, 2});
            listaACorroborar.add(new int[]{7, 1});

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
        if (casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].hayPieza()) {
            if (colorDePiezaDiferente(movimiento)) {
                return true;
            } else {
                throw new MovimientoInvalido("La pieza que quiere capturar es del mismo color pendejo");
            }
        }
        return false;
    }


    private boolean colorDePiezaDiferente(Movimiento movimiento) {
        return casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza().getColor() != casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza().getColor();
    }

    private void hacerEnroqueCorto(Movimiento movimiento) throws MovimientoInvalido {
        if (movimiento.getColorDeJugador() == ColorPiezas.BLANCAS) {
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{0, 5});
            listaACorroborar.add(new int[]{0, 6});

            hacerEnroque(movimiento, listaACorroborar);
        } else {
            ArrayList<int[]> listaACorroborar = new ArrayList<>();
            listaACorroborar.add(new int[]{7, 5});
            listaACorroborar.add(new int[]{7, 6});

            hacerEnroque(movimiento, listaACorroborar);
        }
    }

    private void hacerEnroque(Movimiento movimiento, ArrayList<int[]> listaACorroborar) throws MovimientoInvalido {
        for (int i = 0; i < listaACorroborar.size(); i++) {
            if (casillas[listaACorroborar.get(i)[0]][listaACorroborar.get(i)[1]].hayPieza()) {
                throw new MovimientoInvalido("No se puede realizar el enroque porque hay piezas entre ellos");
            }
        }

        if (!esElPrimerMovimientoDelReyYLaTorre(movimiento)) {
            throw new MovimientoInvalido("No se puede realizar el enroque porque el rey o la torre ya fuero movidos");
        }

        casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1] - 1].setPieza(casillas[movimiento.coordenadasIniciales[0]][movimiento.coordenadasIniciales[1]].getPieza());
        casillas[movimiento.coordenadasIniciales[0]][movimiento.coordenadasIniciales[1] + 1].setPieza(casillas[movimiento.coordenadasFinales[0]][movimiento.coordenadasFinales[1]].getPieza());
        casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].quitarPieza();
        casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].quitarPieza();
    }

    private boolean esElPrimerMovimientoDelReyYLaTorre(Movimiento movimiento) {
        return ((Rey) casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza()).esSuPrimerMovimiento() && casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza() instanceof Torre && ((Torre) casillas[movimiento.getFilaFinal()][movimiento.getColumnaFinal()].getPieza()).esSuPrimerMovimiento();
    }


    private boolean movimientoEsValido(Movimiento movimiento) throws EnroqueCorto, EnroqueLargo, CoronacionCapturando, CoronacionAvanzando, MovimientoInvalido {
        if (!(hayPiezaParaMover(movimiento))) {
            throw new MovimientoInvalido("No hay pieza en la coordenada inicia");
        }
        if (!(laPiezaQueQuiereMoverEsDeSuColor(movimiento))) {
            throw new MovimientoInvalido("La pieza que quiere mover no es de su color");
        }

        ArrayList<ArrayList<int[]>> listaDeCoordenadas;
        listaDeCoordenadas = casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza().obtenerListaDeCoordenadasDondePuedeIr(movimiento);

        if (!(laPiezaPuedeIrAEsaCoordenada(movimiento, listaDeCoordenadas))) {
            throw new MovimientoInvalido("La pieza no se puede mover a la coordenada ingresada");
        }




        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            if (estaListaContineLaCoordenada(listaDeCoordenadas.get(i), movimiento.getCoordenadasFinales())) {
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
            if (estaListaContineLaCoordenada(listaDeCoordenadas.get(i), movimiento.getCoordenadasFinales())) {
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

    private boolean estaListaContineLaCoordenada(ArrayList<int[]> ints, int[] coordenadasFinales) {
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
        for (int i = 0; i < LARGO_TABLERO; i++) {
            for (int j = 0; j < ANCHO_TABLERO; j++) {
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
                switch (casillas[i][j].queHayEnLaCasilla()) {
                    case 1:
                        tableroCompleto += "[" + casillas[i][j] + "]";
                        break;
                    case 2:
                        tableroCompleto += "[*]";
                        break;
                    case 0:
                        tableroCompleto += "[ ]";
                        break;
                }
            }
            tableroCompleto += (i + 1);
            tableroCompleto += '\n';
        }

        return tableroCompleto;
    }

    public String guardarPartida() {
        String guardarPartida = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (casillas[i][j].hayPieza()) {
                    guardarPartida += "" + casillas[i][j].getPieza() + i + j;
                    if (casillas[i][j].getPieza() instanceof Rey) {
                        if (!(((Rey) casillas[i][j].getPieza()).esSuPrimerMovimiento())) {
                            guardarPartida += "f";
                        }
                    }
                    if (casillas[i][j].getPieza() instanceof Torre) {
                        if (!(((Torre) casillas[i][j].getPieza()).esSuPrimerMovimiento())) {
                            guardarPartida += "f";
                        }
                    }
                    if (casillas[i][j].getPieza() instanceof Peon) {
                        if (!(((Peon) casillas[i][j].getPieza()).esSuPrimerMovimiento())) {
                            guardarPartida += "f";
                        }
                    }
                    guardarPartida += "/";
                }
            }
        }

        guardarPartida += obtenerPiezasCapturadas();
        return guardarPartida;

    }

    private String obtenerPiezasCapturadas() {
        return "";
    }

    public void mostrarCasillasALasQueSePuedeMover(Movimiento movimiento) throws MovimientoInvalido, CoronacionAvanzando, EnroqueLargo, EnroqueCorto, CoronacionCapturando {
        if (!(hayPiezaParaMover(movimiento))) {
            throw new MovimientoInvalido("No hay pieza en la coordenada inicial pendejo");
        }
        if (!(laPiezaQueQuiereMoverEsDeSuColor(movimiento))) {
            throw new MovimientoInvalido("La pieza que quiere mover no es de su color");
        }

        ArrayList<ArrayList<int[]>> listaDeCoordenadas;
        listaDeCoordenadas = casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza().obtenerListaDeCoordenadasDondePuedeIr(movimiento);

        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            for (int j = 0; j < listaDeCoordenadas.get(i).size(); j++) {
                if (casillas[listaDeCoordenadas.get(i).get(j)[0]][listaDeCoordenadas.get(i).get(j)[1]].hayPieza()) {
                    quitarMovimientosNoAlcanzables(listaDeCoordenadas.get(i), j);
                }
                if (casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza() instanceof Peon) {
                    if (listaDeCoordenadas.get(i).size() != 0 && listaDeCoordenadas.get(i).get(j)[1] != movimiento.getColumnaInicial()) {
                        if (!(casillas[listaDeCoordenadas.get(i).get(j)[0]][listaDeCoordenadas.get(i).get(j)[1]].hayPieza())) {
                            listaDeCoordenadas.get(i).clear();
                        }
                    }
                }
            }
        }

        ArrayList<ArrayList<int[]>> aux1 = new ArrayList<>();
        for (int i =0; i<listaDeCoordenadas.size();i++){
            if (listaDeCoordenadas.get(i).size() != 0){
                aux1.add(listaDeCoordenadas.get(i));
            }
        }
        listaDeCoordenadas = aux1;

        if (!(listaDeCoordenadasTieneAlMenosUna(listaDeCoordenadas))) {
            throw new MovimientoInvalido("Esa pieza no se puede mover a ningun lado pendejo");
        }



        if (casillas[movimiento.getFilaInicial()][movimiento.getColumnaInicial()].getPieza() instanceof Rey){
            ArrayList<ArrayList<int[]>> listaPadreDeCoordenadas = new ArrayList<>();
            for (int i = 0; i < ANCHO_TABLERO; i++) {
                for (int j = 0; j < LARGO_TABLERO; j++) {
                    Movimiento auxLaPutaMadre = new Movimiento(new Jugador(movimiento.getColorDeJugador()));
                    int[] coordenadasIniciales = {i, j};
                    movimiento.setCoordenadasIniciales(coordenadasIniciales);
                    if (casillas[i][j].hayPieza() && casillas[i][j].getPieza().getColor() != movimiento.getColorDeJugador()) {
                        listaPadreDeCoordenadas.addAll(casillas[i][j].getPieza().obtenerListaDeCoordenadasDondePuedeComer(movimiento));
                    }
                }
            }

            System.out.println("}");
            ArrayList<ArrayList<int[]>> aux2 = new ArrayList<>();
            for (int i=0; i<listaPadreDeCoordenadas.size();i++){
                if (listaPadreDeCoordenadas.get(i).size() != 0){
                    aux2.add(listaPadreDeCoordenadas.get(i));
                }
            }
            listaPadreDeCoordenadas = aux2;

            System.out.println("ANTES DE LA LIMPIEZA\n\nLista mas grande {");
            for (int i=0; i<listaPadreDeCoordenadas.size();i++){
                System.out.println("\tLista chiquita #"+i+" {");
                for (int j =0; j<listaPadreDeCoordenadas.get(i).size();j++){
                    System.out.println("\t\t"+ Arrays.toString(listaPadreDeCoordenadas.get(i).get(j)));
                }
                System.out.println("\t}");
            }
            for (int i =0; i<listaDeCoordenadas.size();i++){
                for (int j =0; j<listaPadreDeCoordenadas.size();j++){
                    if (estaListaContineLaCoordenada(listaPadreDeCoordenadas.get(j),listaDeCoordenadas.get(i).get(0))){
                        System.out.println(listaDeCoordenadas.get(i));
                        listaDeCoordenadas.get(i).clear();
                        System.out.println(listaDeCoordenadas.get(i));
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            for (int j = 0; j < listaDeCoordenadas.get(i).size(); j++) {
                casillas[listaDeCoordenadas.get(i).get(j)[0]][listaDeCoordenadas.get(i).get(j)[1]].subrayar();
            }
        }

        System.out.println(this);

        quitarSubrayado();


    }

    private boolean listaDeCoordenadasTieneAlMenosUna(ArrayList<ArrayList<int[]>> listaDeCoordenadas) {
        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            if (listaDeCoordenadas.get(i).size() != 0) {
                return true;
            }
        }
        return false;
    }

    private void mostrarListaDeJugadas(ArrayList<ArrayList<int[]>> listaDeCoordenadas) {
        for (int i = 0; i < listaDeCoordenadas.size(); i++) {
            System.out.print("Lista#" + i + "{");
            for (int j = 0; j < listaDeCoordenadas.get(i).size(); j++) {
                System.out.print("" + j + Arrays.toString(listaDeCoordenadas.get(i).get(j)));
            }
            System.out.print("}\n");
        }
    }

    private void quitarSubrayado() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casillas[i][j].quitarSubrayado();
            }
        }
    }

    private void quitarMovimientosNoAlcanzables(ArrayList<int[]> ints, int j) {
        ArrayList<int[]> aux = new ArrayList<>();
        if (ints.size() > (j)) {
            for (int i = 0; i < j; i++) {
                aux.add(ints.get(i));
            }
        }
        ints.clear();

        for (int k = 0; k < aux.size(); k++) {
            ints.add(aux.get(k));
        }
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }
}

