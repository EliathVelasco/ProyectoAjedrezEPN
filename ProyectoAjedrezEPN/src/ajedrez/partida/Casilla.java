package ajedrez.partida;

import ajedrez.piezas.Pieza;

public class Casilla {
    private Pieza pieza;
    public Casilla(Pieza pieza) {
        this.pieza = pieza;
    }
    public Casilla() {
        this.pieza = null;
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
}
