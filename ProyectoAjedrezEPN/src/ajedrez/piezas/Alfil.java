package ajedrez.piezas;

import ajedrez.partida.*;

import java.util.ArrayList;

public class Alfil extends Pieza {
    public Alfil(ColorPiezas color) {
        super(color, 3);
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento) {
        //ArrayList<ArrayList<int []>> coordenadasPosibles = new ArrayList<>();
        coordenadasPosibles.clear();
        ArrayList<int[]> aux = new ArrayList<>();
        int auxColumna = 0;


        if (movimiento.getColumnaInicial() < 7) {
            auxColumna = movimiento.getColumnaInicial() + 1;
            for (int i = movimiento.getFilaInicial() + 1; i < 8; i++) {
                aux.add(new int[]{i, auxColumna});
                if (auxColumna < 7) {
                    auxColumna++;
                } else {
                    break;
                }
            }
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();

            auxColumna = movimiento.getColumnaInicial() + 1;

            for (int i = movimiento.getFilaInicial()-1; i > 0; i--) {
                aux.add(new int[]{i, auxColumna});
                if (auxColumna < 7) {
                    auxColumna++;
                } else {
                    break;
                }
            }
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }



        if (movimiento.getColumnaInicial() > 0){
            auxColumna = movimiento.getColumnaInicial()-1;
            for (int i = movimiento.getFilaInicial() + 1; i < 8; i++) {
                aux.add(new int[]{i, auxColumna});
                if (auxColumna > 0) {
                    auxColumna--;
                } else {
                    break;
                }
            }

            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();

            auxColumna = movimiento.getColumnaInicial()-1;

            for (int i = movimiento.getFilaInicial()-1; i > 0; i--) {
                aux.add(new int[]{i, auxColumna});
                if (auxColumna > 0) {
                    auxColumna--;
                } else {
                    break;
                }
            }

            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }

        return coordenadasPosibles;
    }

    @Override
    public String toString() {
        if (color == ColorPiezas.BLANCAS) {
            return "A";
        } else {
            return "a";
        }
    }
}