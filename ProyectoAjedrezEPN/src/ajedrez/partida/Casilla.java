package ajedrez.partida;

import ajedrez.piezas.Pieza;

public class Casilla {
    private Pieza pieza;
    private boolean estaSubrayada;
    public Casilla(Pieza pieza) {
        this.pieza = pieza;
        estaSubrayada = false;
    }
    public Casilla() {
        this.pieza = null;
        estaSubrayada = false;
    }

    public boolean hayPieza(){
        return pieza != null;
    }

    @Override
    public String toString() {
        return pieza + "";
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void quitarPieza() {
        this.pieza = null;
    }

    public void ponerPieza(Pieza piezaNueva) {
        this.pieza = piezaNueva;
    }


    public void setPieza(Pieza pieza) {
        this.pieza=pieza;
    }

    public int queHayEnLaCasilla() {
        if (hayPieza()){
            return 1;
        }
        if(estaSubrayada){
            return 2;
        }
        return 0;
    }

    public void subrayar() {
        estaSubrayada = true;
    }

    public void quitarSubrayado() {
        estaSubrayada = false;
    }
}
