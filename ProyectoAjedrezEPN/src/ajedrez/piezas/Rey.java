package ajedrez.piezas;
import ajedrez.excepciones.*;
import ajedrez.partida.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles(Movimiento movimiento) throws EnroqueCorto, EnroqueLargo{
        coordenadasPosibles.clear();
        if(primerMovimiento){
            if(color == ColorPiezas.BLANCAS){
                if(Arrays.equals(movimiento.getCoordenadasIniciales(), new int[]{0,4}) && Arrays.equals(movimiento.getCoordenadasFinales(), new int[]{0,7})){
                    throw new EnroqueLargo();
                }
                if (Arrays.equals(movimiento.getCoordenadasIniciales(), new int[]{0,4}) && Arrays.equals(movimiento.getCoordenadasFinales(), new int[]{0,0})){
                    throw new EnroqueCorto();
                }
            }
            if(color == ColorPiezas.NEGRAS){
                if(Arrays.equals(movimiento.getCoordenadasIniciales(), new int[]{7,4}) && Arrays.equals(movimiento.getCoordenadasFinales(), new int[]{7,7})){
                    throw new EnroqueLargo();
                }
                if (Arrays.equals(movimiento.getCoordenadasIniciales(), new int[]{7,4}) && Arrays.equals(movimiento.getCoordenadasFinales(), new int[]{7,0})){
                    throw new EnroqueCorto();
                }
            }
        }


        if (movimiento.getFilaInicial() < 7){
            agregarALaLista(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()});
            if (movimiento.getColumnaInicial() > 0) agregarALaLista(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()-1});
            if (movimiento.getColumnaInicial() < 7) agregarALaLista(new int[] {movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()+1});
        }

        if (movimiento.getFilaInicial() > 0){
            agregarALaLista(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()});
            if (movimiento.getColumnaInicial() > 0) agregarALaLista(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()-1});
            if (movimiento.getColumnaInicial() < 7) agregarALaLista(new int[] {movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()+1});
        }

        if (movimiento.getColumnaInicial() > 0) agregarALaLista(new int[] {movimiento.getFilaInicial(), movimiento.getColumnaInicial()-1});
        if (movimiento.getColumnaInicial() < 7) agregarALaLista(new int[] {movimiento.getFilaInicial(), movimiento.getColumnaInicial()+1});


        return coordenadasPosibles;
    }

    private void agregarALaLista(int[] coordenadas) {
        ArrayList<int []> aux = new ArrayList<>();
        aux.add(coordenadas);
        coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
        aux.clear();
    }

    public boolean esSuPrimerMovimiento(){
        return primerMovimiento;
    }

    public void quitarPrimerMovimiento() {
        primerMovimiento = false;
    }
}
