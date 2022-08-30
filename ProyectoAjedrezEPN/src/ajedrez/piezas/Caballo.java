package ajedrez.piezas;
import ajedrez.excepciones.EnroqueCorto;
import ajedrez.excepciones.EnroqueLargo;
import ajedrez.partida.*;
import java.util.ArrayList;

public class Caballo extends Pieza {
    public Caballo(ColorPiezas color) {
        super(color,3);
    }

    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "C";
        }else{
            return "c";
        }
    }


    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Movimiento movimiento){
        //ArrayList<ArrayList<int []>> coordenadasPosibles = new ArrayList<>();
        listaPadreDeCoordenadasPosibles.clear();

        if(movimiento.getFilaInicial() < 6 && movimiento.getColumnaInicial() < 7){
            agregarALaLista(new int[] {movimiento.getFilaInicial()+2, movimiento.getColumnaInicial()+1});
        }
        if(movimiento.getFilaInicial() < 6 && movimiento.getColumnaInicial() > 0){
            agregarALaLista(new int[] {movimiento.getFilaInicial()+2, movimiento.getColumnaInicial()-1});
        }
        if(movimiento.getFilaInicial() < 7 && movimiento.getColumnaInicial() < 6){
            agregarALaLista(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()+2});
        }
        if(movimiento.getFilaInicial() < 7 && movimiento.getColumnaInicial() > 1){
            agregarALaLista(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()-2});
        }
        if(movimiento.getFilaInicial() > 1 && movimiento.getColumnaInicial() < 7){
            agregarALaLista(new int[] {movimiento.getFilaInicial()-2, movimiento.getColumnaInicial()+1});
        }
        if(movimiento.getFilaInicial() > 1 && movimiento.getColumnaInicial() > 0){
            agregarALaLista(new int[] {movimiento.getFilaInicial()-2, movimiento.getColumnaInicial()-1});
        }
        if(movimiento.getFilaInicial() > 0 && movimiento.getColumnaInicial() < 6){
            agregarALaLista(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()+2});
        }
        if(movimiento.getFilaInicial() > 0 && movimiento.getColumnaInicial() > 1){
            agregarALaLista(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()-2});
        }

        return listaPadreDeCoordenadasPosibles;
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Coordenada coordenadasIniciales){
        //ArrayList<ArrayList<int []>> coordenadasPosibles = new ArrayList<>();
        listaPadreDeCoordenadasPosibles.clear();

        if(coordenadasIniciales.getFila() < 6 && coordenadasIniciales.getColumna() < 7){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()+2, coordenadasIniciales.getColumna()+1});
        }
        if(coordenadasIniciales.getFila() < 6 && coordenadasIniciales.getColumna() > 0){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()+2, coordenadasIniciales.getColumna()-1});
        }
        if(coordenadasIniciales.getFila() < 7 && coordenadasIniciales.getColumna() < 6){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()+1, coordenadasIniciales.getColumna()+2});
        }
        if(coordenadasIniciales.getFila() < 7 && coordenadasIniciales.getColumna() > 1){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()+1, coordenadasIniciales.getColumna()-2});
        }
        if(coordenadasIniciales.getFila() > 1 && coordenadasIniciales.getColumna() < 7){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()-2, coordenadasIniciales.getColumna()+1});
        }
        if(coordenadasIniciales.getFila() > 1 && coordenadasIniciales.getColumna() > 0){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()-2, coordenadasIniciales.getColumna()-1});
        }
        if(coordenadasIniciales.getFila() > 0 && coordenadasIniciales.getColumna() < 6){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()-1, coordenadasIniciales.getColumna()+2});
        }
        if(coordenadasIniciales.getFila() > 0 && coordenadasIniciales.getColumna() > 1){
            agregarALaLista(new int[] {coordenadasIniciales.getFila()-1, coordenadasIniciales.getColumna()-2});
        }

        return listaPadreDeCoordenadasPosibles;
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Coordenada coordenadaInicial) {
        return obtenerListaDeCoordenadasDondePuedeIr(coordenadaInicial);
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Movimiento movimiento) throws EnroqueLargo, EnroqueCorto {
        return obtenerListaDeCoordenadasDondePuedeIr(movimiento);
    }

    private void agregarALaLista(int[] coordenadas) {
        ArrayList<int []> aux = new ArrayList<>();
        aux.add(coordenadas);
        listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();
    }
}
