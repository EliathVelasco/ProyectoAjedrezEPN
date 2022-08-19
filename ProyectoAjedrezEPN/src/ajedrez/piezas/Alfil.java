package ajedrez.Piezas;
import ajedrez.Partida.*;
import java.util.ArrayList;



public class Alfil extends Pieza{
    public Alfil(ColorPiezas color) {
        super(color, 3);
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento){
        ArrayList<ArrayList<int []>> movimientosPosibles = new ArrayList<>();
        ArrayList<int []> aux = new ArrayList<>();


        int auxColumna = movimiento.getColumnaInicial();

        for (int i = movimiento.getFilaInicial(); i < 8; i++){
            aux.add(new int[] {i, auxColumna});
            if (auxColumna < 7){
                auxColumna++;
            }else {
                break;
            }
        }

        movimientosPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();

        auxColumna = movimiento.getColumnaInicial();

        for (int i = movimiento.getFilaInicial(); i < 8; i++){
            aux.add(new int[] {i, auxColumna});
            if (auxColumna > 0){
                auxColumna--;
            }else {
                break;
            }
        }

        movimientosPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();

        auxColumna = movimiento.getColumnaInicial();

        for (int i = movimiento.getFilaInicial(); i >= 0; i--){
            aux.add(new int[] {i, auxColumna});
            if (auxColumna < 7){
                auxColumna++;
            }else {
                break;
            }
        }
        movimientosPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();

        auxColumna = movimiento.getColumnaInicial();

        for (int i = movimiento.getFilaInicial(); i >= 0; i--){
            aux.add(new int[] {i, auxColumna});
            if (auxColumna > 0){
                auxColumna--;
            }else {
                break;
            }
        }

        movimientosPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();

        return movimientosPosibles;
    }


    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "A";
        }else{
            return "a";
        }
    }
}