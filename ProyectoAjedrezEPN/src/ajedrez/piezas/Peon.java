package ajedrez.piezas;
import ajedrez.excepciones.*;
import ajedrez.partida.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Peon extends Pieza {
    private boolean primerMovimiento;

    public Peon(ColorPiezas color) {
        super(color, 1);
        this.primerMovimiento = true;
    }

    @Override
    public String toString() {
        if (color == ColorPiezas.BLANCAS) {
            return "P";
        } else {
            return "p";
        }
    }

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasPosibles (Movimiento movimiento) throws CoronacionAvanzando, CoronacionCapturando, MovimientoInvalido {
        if (movimiento.getCoordenadasFinales()!= null){

            if (!(movimiento.getColumnaInicial()+1 == movimiento.getColumnaFinal() || movimiento.getColumnaInicial()-1 == movimiento.getColumnaFinal() || movimiento.getColumnaInicial() == movimiento.getColumnaFinal())){
                throw new MovimientoInvalido("Jugada invalida, columna no alcanzable");
            }
        }
        if (color == ColorPiezas.BLANCAS) {
            agregarMovimientosPeonesBlancas(movimiento);
            buscarMovimientosDeCapturaBlanca(movimiento);
            if(movimiento.getCoordenadasFinales() != null){
                if (movimiento.getFilaFinal() == 7 && movimiento.getFilaInicial() == 6) {
                    asignarTipoDeCoronacion(movimiento);
                }
            }


        }else{
            agregarMovimientosPeonesNegros(movimiento);
            buscarMovimientosDeCapturaNegras(movimiento);
            if(movimiento.getCoordenadasFinales()!= null){
                if (movimiento.getFilaFinal() == 0 && movimiento.getFilaInicial() == 1){
                    asignarTipoDeCoronacion(movimiento);
                }
            }

        }
        return coordenadasPosibles;
    }

    private void asignarTipoDeCoronacion(Movimiento movimiento) throws CoronacionAvanzando, CoronacionCapturando{
        if (movimiento.getColumnaInicial() == movimiento.getColumnaFinal()) {
            throw new CoronacionAvanzando();
        } else {
            throw new CoronacionCapturando();
        }
    }
    public void quitarPrimerMovimiento(){
        this.primerMovimiento=false;
    }
    public void agregarMovimientosPeonesBlancas(Movimiento movimiento) {
        coordenadasPosibles.clear();
        ArrayList<int[]> aux = new ArrayList<>();

        if (primerMovimiento) {
            for (int i = movimiento.getFilaInicial() + 1; i < movimiento.getFilaInicial() + 3; i++) {
                aux.add(new int[]{i, movimiento.getColumnaInicial()});
            }
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        } else{
            aux.add(new int[]{movimiento.getFilaInicial() + 1, movimiento.getColumnaInicial()});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }
    public void agregarMovimientosPeonesNegros(Movimiento movimiento) {
        coordenadasPosibles.clear();
        ArrayList<int[]> aux = new ArrayList<>();

        if (primerMovimiento) {
            for (int i = movimiento.getFilaInicial() - 1; i > movimiento.getFilaInicial() - 3; i--) {
                aux.add(new int[]{i, movimiento.getColumnaInicial()});
            }
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        } else{
            aux.add(new int[]{movimiento.getFilaInicial() - 1, movimiento.getColumnaInicial()});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }

    }
    public void buscarMovimientosDeCapturaBlanca(Movimiento movimiento){
        ArrayList<int[]> aux = new ArrayList<>();
        if(movimiento.getColumnaInicial()>0){
            aux.add(new int[]{movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()-1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getColumnaInicial()<7 ){
            aux.add(new int[]{movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()+1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }
    public void buscarMovimientosDeCapturaNegras(Movimiento movimiento){
        ArrayList<int[]> aux = new ArrayList<>();
        if(movimiento.getColumnaInicial()<7){
            aux.add(new int[]{movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()+1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if (movimiento.getColumnaInicial()>0){
            aux.add(new int[]{movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()-1});
            coordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }

    public boolean esSuPrimerMovimiento() {
        return primerMovimiento;
    }
}