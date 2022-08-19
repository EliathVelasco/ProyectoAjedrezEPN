package ajedrez.piezas;
import ajedrez.partida.*;
import java.util.ArrayList;

import java.util.ArrayList;

public class Rey extends Pieza {
    private boolean primerMovimiento;

    public Rey(ColorPiezas color) {
        super(color, 0);
        primerMovimiento = true;
    }

    @Override
    public String toString() {
        if (this.color == ColorPiezas.BLANCAS) {
            return "R";
        } else {
            return "r";
        }
    }
    /*
    * El rey tampoco clava las piezas, por lo que hay que ver que mismo hacemos aqui*/

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento) {
        ArrayList<int []> aux = new ArrayList<>();

        

        if (movimiento.getFilaInicial() < 7){
            aux.add(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()});
            if (movimiento.getColumnaInicial() > 0) aux.add(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()-1});
            if (movimiento.getColumnaInicial() < 7) aux.add(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()+1});
        }

        if (movimiento.getFilaInicial() > 0){
            aux.add(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()});
            if (movimiento.getColumnaInicial() > 0) aux.add(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()-1});
            if (movimiento.getColumnaInicial() < 7) aux.add(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()+1});
        }

        if (movimiento.getColumnaInicial() > 0) aux.add(new int[] {movimiento.getFilaInicial(), movimiento.getColumnaInicial()-1});
        if (movimiento.getColumnaInicial() < 7) aux.add(new int[] {movimiento.getFilaInicial(), movimiento.getColumnaInicial()+1});

        coordenadasPosibles.add(aux);

        return coordenadasPosibles;
    }
}
