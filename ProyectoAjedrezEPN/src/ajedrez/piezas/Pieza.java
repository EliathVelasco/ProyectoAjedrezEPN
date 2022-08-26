package ajedrez.piezas;
import ajedrez.excepciones.*;
import ajedrez.partida.*;
import java.util.ArrayList;

import static ajedrez.Main.ANCHO_TABLERO;
import static ajedrez.Main.LARGO_TABLERO;


public abstract class Pieza {
    private int valorDePieza;
    private int alcance;
    protected ColorPiezas color;
    protected ArrayList<ArrayList<int []>> listaPadreDeCoordenadasPosibles;

    public Pieza(ColorPiezas color, int valorDePieza, int alcanceDePieza) {
        this.valorDePieza = valorDePieza;
        this.color = color;
        this.listaPadreDeCoordenadasPosibles = new ArrayList<>();
        this.alcance = alcanceDePieza;
    }

    public Pieza(ColorPiezas color, int valorDePieza){
        this.valorDePieza = valorDePieza;
        this.color = color;
        this.listaPadreDeCoordenadasPosibles = new ArrayList<>();
    }


    public abstract ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento) throws EnroqueCorto, EnroqueLargo, CoronacionAvanzando, CoronacionCapturando, MovimientoInvalido;
    public ColorPiezas getColor(){
        return color;
    }
    public void obtenerCoordenadasPosiblesDeManeraDiagonalSegunElAlcanceDeLaPiezas(Movimiento movimiento){
        ArrayList<int[]> listaHijaDeCoordenadas = new ArrayList<>();

        //Derecha-abajo
        for (int i = 1; i<=alcance ; i++){
            if (movimiento.getFilaInicial()+i < ANCHO_TABLERO && movimiento.getColumnaInicial()+i < LARGO_TABLERO){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial()+i,movimiento.getColumnaInicial()+i});
            }else{
                break;
            }
        }
        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

        //Izquierda-abajo
        for (int i =1; i<= alcance;i++){
            if ( movimiento.getFilaInicial()+i < ANCHO_TABLERO && movimiento.getColumnaInicial()-i >= 0){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial()+i,movimiento.getColumnaInicial()-i});
            }else{
                break;
            }
        }
        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

        //Derecha-arriba
        for (int i =1; i<= alcance;i++){
            if ( movimiento.getFilaInicial()-i >= 0 && movimiento.getColumnaInicial()+i < LARGO_TABLERO){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial()-i,movimiento.getColumnaInicial()+i});
            }else{
                break;
            }
        }
        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

        //izquierda-arriba
        for (int i =1; i<= alcance;i++){
            if ( movimiento.getFilaInicial()-i >= 0 && movimiento.getColumnaInicial()-i >=0){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial()-i,movimiento.getColumnaInicial()-i});
            }else{
                break;
            }
        }

        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

    }
    public void obtenerCoordenadasPosiblesDeManeraVerticalYHorizontalSegunElAlcanceDeLaPieza(Movimiento movimiento){
        ArrayList<int[]> listaHijaDeCoordenadas = new ArrayList<>();

        //Arriba
        for (int i = 1; i<=alcance ; i++){
            if (movimiento.getFilaInicial()-i >= 0){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial()-i,movimiento.getColumnaInicial()});
            }else{
                break;
            }
        }
        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

        //Abajo
        for (int i = 1; i<=alcance ; i++){
            if (movimiento.getFilaInicial()+i < ANCHO_TABLERO){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial()+i,movimiento.getColumnaInicial()});
            }else{
                break;
            }
        }
        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

        //Izquierda
        for (int i = 1; i<=alcance ; i++){
            if (movimiento.getColumnaInicial()-i >= 0){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial(),movimiento.getColumnaInicial()-i});
            }else{
                break;
            }
        }
        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

        //Derecha
        for (int i = 1; i<=alcance ; i++){
            if (movimiento.getColumnaInicial()+i < ANCHO_TABLERO){
                listaHijaDeCoordenadas.add(new int[]{movimiento.getFilaInicial(),movimiento.getColumnaInicial()+i});
            }else{
                break;
            }
        }
        agregarListaHijaAListaPadre(listaHijaDeCoordenadas);

    }
    private void agregarListaHijaAListaPadre(ArrayList<int[]> aux) {
        if (aux.size() > 0){
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }

}
