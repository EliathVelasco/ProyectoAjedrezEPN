package ajedrez.piezas;
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


    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento){
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

    private void agregarALaLista(int[] coordenadas) {
        ArrayList<int []> aux = new ArrayList<>();
        aux.add(coordenadas);
        listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();
    }
}
