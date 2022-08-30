package ajedrez.partida;

public class Coordenada {
    private int fila;
    private int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }
}
