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


    /*
    * El caballo no puede clavar una pieza, ver si cambiamos esto o ponemos una excepcion
    * yo preferiría una excepcion XD*/
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento){
        //ArrayList<ArrayList<int []>> coordenadasPosibles = new ArrayList<>();
        coordenadasPosibles.clear();
        ArrayList<int []> aux = new ArrayList<>();

        if(movimiento.getFilaInicial() < 6 && movimiento.getColumnaInicial() < 7){
            aux.add(new int[] {movimiento.getFilaInicial()+2, movimiento.getColumnaInicial()+1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getFilaInicial() < 6 && movimiento.getColumnaInicial() > 0){
            aux.add(new int[] {movimiento.getFilaInicial()+2, movimiento.getColumnaInicial()-1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getFilaInicial() < 7 && movimiento.getColumnaInicial() < 6){
            aux.add(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()+2});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getFilaInicial() < 7 && movimiento.getColumnaInicial() > 1){
            aux.add(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()-2});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getFilaInicial() > 1 && movimiento.getColumnaInicial() < 7){
            aux.add(new int[] {movimiento.getFilaInicial()-2, movimiento.getColumnaInicial()+1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getFilaInicial() > 1 && movimiento.getColumnaInicial() > 0){
            aux.add(new int[] {movimiento.getFilaInicial()-2, movimiento.getColumnaInicial()-1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getFilaInicial() > 0 && movimiento.getColumnaInicial() < 6){
            aux.add(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()+2});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getFilaInicial() > 0 && movimiento.getColumnaInicial() > 1){
            aux.add(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()-2});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }


        return coordenadasPosibles;
    }
}
