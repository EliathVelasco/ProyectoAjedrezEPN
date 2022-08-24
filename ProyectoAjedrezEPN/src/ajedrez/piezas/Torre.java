package ajedrez.piezas;
import ajedrez.partida.*;
import java.util.ArrayList;

public class Torre extends Pieza {
    private boolean primerMovimiento;
    public Torre(ColorPiezas color) {
        super(color,5);
        this.primerMovimiento = true;
    }
    @Override
    public String toString() {
        if(color == ColorPiezas.BLANCAS){
            return "T";
        }else{
            return "t";
        }
    }
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento){
        //ArrayList<ArrayList<int []>> coordenadasPosibles = new ArrayList<>();
        coordenadasPosibles.clear();
        ArrayList<int []> aux = new ArrayList<>();

        for (int i = movimiento.getFilaInicial()+1; i < 8; i++) {
            aux.add(new int[]{i, movimiento.getColumnaInicial()});
        }

        coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();

        for (int i = movimiento.getFilaInicial()-1; i >= 0; i--) {
            aux.add(new int[]{i, movimiento.getColumnaInicial()});
        }

        coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();

        for (int i = movimiento.getColumnaInicial()+1; i < 8; i++) {
            aux.add(new int[]{movimiento.getFilaInicial(), i});
        }

        coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();

        for (int i = movimiento.getColumnaInicial()-1; i >= 0; i--) {
            aux.add(new int[]{movimiento.getFilaInicial(), i});
        }

        coordenadasPosibles.add((ArrayList<int[]>) aux.clone());

        return coordenadasPosibles;
    }

}
