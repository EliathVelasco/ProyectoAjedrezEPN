package Partida;
import errores.*;

import java.util.regex.*;

public class Movimiento {
    private final int [] coordenadasIniciales = new int[2];
    private int [] coordenadasFinales = new int[2];
    private final Jugador jugadorQueRealizó;

    public Movimiento(String jugada, Jugador jugadorQueRealizó) throws SintaxisInvalida {
        if (!(Pattern.matches("[a-h][1-8][a-h][1-8]", jugada.toLowerCase()))) {
            throw new SintaxisInvalida("Sintaxis no valida");
        }

        coordenadasIniciales[0] = transformarFilaIngresadaEnIndice(jugada.charAt(1));
        coordenadasIniciales[1] = transformarColumnaIngresadaEnIndice(jugada.charAt(0));

        coordenadasFinales[0] = transformarFilaIngresadaEnIndice(jugada.charAt(3));
        coordenadasFinales[1] = transformarColumnaIngresadaEnIndice(jugada.charAt(2));

        this.jugadorQueRealizó = jugadorQueRealizó;
    }

    private int transformarFilaIngresadaEnIndice(char fila){
        return Character.getNumericValue(fila)-1;
    }

    private int transformarColumnaIngresadaEnIndice(char columna) {
        String cadenaDeLetras = "abcdefgh";
        return cadenaDeLetras.indexOf(columna);
    }
}
