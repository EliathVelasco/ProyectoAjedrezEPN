package ajedrez.piezas;
import ajedrez.excepciones.*;
import ajedrez.partida.*;
import java.util.ArrayList;

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

    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeIr(Movimiento movimiento) throws CoronacionAvanzando, CoronacionCapturando, MovimientoInvalido {
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
        return listaPadreDeCoordenadasPosibles;
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Movimiento movimiento) throws EnroqueLargo, EnroqueCorto {
        listaPadreDeCoordenadasPosibles.clear();
        if (color == ColorPiezas.BLANCAS) {
            buscarMovimientosDeCapturaBlanca(movimiento);
        }else{
            buscarMovimientosDeCapturaNegras(movimiento);
        }
        return listaPadreDeCoordenadasPosibles;
    }

    @Override
    public ArrayList<ArrayList<int[]>> obtenerListaDeCoordenadasDondePuedeComer(Coordenada coordenadaInicial) {
        listaPadreDeCoordenadasPosibles.clear();
        if (color == ColorPiezas.BLANCAS){
            agregarMovimientosDeCapturaBlancaALaListaPadreDeMovimientos(coordenadaInicial);
        } else {
            agregarMovimientosDeCapturaNegraALaListaPadreDeMovimientos(coordenadaInicial);
        }
        return listaPadreDeCoordenadasPosibles;
    }

    private void agregarMovimientosDeCapturaNegraALaListaPadreDeMovimientos(Coordenada coordenadaInicial) {
        ArrayList<int[]> aux = new ArrayList<>();
        if(coordenadaInicial.getColumna()<7){
            aux.add(new int[]{coordenadaInicial.getFila()-1, coordenadaInicial.getColumna()+1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if (coordenadaInicial.getColumna()>0){
            aux.add(new int[]{coordenadaInicial.getFila()-1, coordenadaInicial.getColumna()-1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
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
        listaPadreDeCoordenadasPosibles.clear();
        ArrayList<int[]> aux = new ArrayList<>();

        if (primerMovimiento) {
            for (int i = movimiento.getFilaInicial() + 1; i < movimiento.getFilaInicial() + 3; i++) {
                aux.add(new int[]{i, movimiento.getColumnaInicial()});
            }
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        } else{
            aux.add(new int[]{movimiento.getFilaInicial() + 1, movimiento.getColumnaInicial()});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }
    public void agregarMovimientosPeonesNegros(Movimiento movimiento) {
        listaPadreDeCoordenadasPosibles.clear();
        ArrayList<int[]> aux = new ArrayList<>();

        if (primerMovimiento) {
            for (int i = movimiento.getFilaInicial() - 1; i > movimiento.getFilaInicial() - 3; i--) {
                aux.add(new int[]{i, movimiento.getColumnaInicial()});
            }
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        } else{
            aux.add(new int[]{movimiento.getFilaInicial() - 1, movimiento.getColumnaInicial()});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }

    }

    public void agregarMovimientosDeCapturaBlancaALaListaPadreDeMovimientos(Coordenada coordenadaInicial){
        ArrayList<int[]> aux = new ArrayList<>();
        if(coordenadaInicial.getColumna()>0){
            aux.add(new int[]{coordenadaInicial.getFila()+1, coordenadaInicial.getColumna()-1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(coordenadaInicial.getColumna()<7 ){
            aux.add(new int[]{coordenadaInicial.getFila()+1, coordenadaInicial.getColumna()+1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }

    public void buscarMovimientosDeCapturaBlanca(Movimiento movimiento){
        ArrayList<int[]> aux = new ArrayList<>();
        if(movimiento.getColumnaInicial()>0){
            aux.add(new int[]{movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()-1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if(movimiento.getColumnaInicial()<7 ){
            aux.add(new int[]{movimiento.getFilaInicial()+1, movimiento.getColumnaInicial()+1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }
    public void buscarMovimientosDeCapturaNegras(Movimiento movimiento){
        ArrayList<int[]> aux = new ArrayList<>();
        if(movimiento.getColumnaInicial()<7){
            aux.add(new int[]{movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()+1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
        if (movimiento.getColumnaInicial()>0){
            aux.add(new int[]{movimiento.getFilaInicial()-1, movimiento.getColumnaInicial()-1});
            listaPadreDeCoordenadasPosibles.add((ArrayList<int[]>) aux.clone());
            aux.clear();
        }
    }

    public boolean esSuPrimerMovimiento() {
        return primerMovimiento;
    }
}