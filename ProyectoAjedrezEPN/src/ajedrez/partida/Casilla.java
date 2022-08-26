package ajedrez.partida;

import ajedrez.piezas.Pieza;

public class Casilla {
    private Pieza pieza;
    private boolean highLighted;
    public Casilla(Pieza pieza) {
        this.pieza = pieza;
        highLighted = false;
    }
    public Casilla() {
        this.pieza = null;
        highLighted = false;
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
        if(highLighted){
            return 2;
        }
        return 0;
    }

    public void subrayar() {
        highLighted = true;
    }

    public void quitarSubrayado() {
        highLighted = false;
    }
}
