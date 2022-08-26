package ajedrez.partida;
import ajedrez.excepciones.*;
import java.util.Arrays;
import java.util.regex.*;

public class Movimiento {
    protected int [] coordenadasIniciales;
    protected int [] coordenadasFinales;
    private Jugador jugadorQueRealizoLaJugada;

    public Movimiento(Jugador jugador){
        jugadorQueRealizoLaJugada = jugador;
    }

    public Movimiento(String jugada, Jugador jugador) throws SintaxisInvalida, GuardarLaPartida {
        if(jugada.equals("Guardar")){
            throw new GuardarLaPartida();
        }
        if (!(Pattern.matches("[a-h][1-8][a-h][1-8]", jugada.toLowerCase()))) {
            throw new SintaxisInvalida("Sintaxis no valida");
        }

        this.jugadorQueRealizoLaJugada = jugador;

        coordenadasIniciales[0] = transformarFilaIngresadaEnIndice(jugada.charAt(1));
        coordenadasIniciales[1] = transformarColumnaIngresadaEnIndice(jugada.charAt(0));

        coordenadasFinales[0] = transformarFilaIngresadaEnIndice(jugada.charAt(3));
        coordenadasFinales[1] = transformarColumnaIngresadaEnIndice(jugada.charAt(2));
    }

    private int transformarFilaIngresadaEnIndice(char fila){
        return Character.getNumericValue(fila)-1;
    }

    private int transformarColumnaIngresadaEnIndice(char columna) {
        String cadenaDeLetras = "abcdefgh";
        return cadenaDeLetras.indexOf(columna);
    }

    public int getColumnaInicial(){
        return coordenadasIniciales[1];
    }

    public int getFilaInicial(){
        return coordenadasIniciales[0];
    }

    public int[] getCoordenadasIniciales() {
        return coordenadasIniciales;
    }

    public int[] getCoordenadasFinales() {
        return coordenadasFinales;
    }

    public int getFilaFinal() {
        return coordenadasFinales[0];
    }

    public int getColumnaFinal() {
        return coordenadasFinales[1];
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "coordenadasIniciales=" + Arrays.toString(coordenadasIniciales) +
                ", coordenadasFinales=" + Arrays.toString(coordenadasFinales) +"}";

    }

    public ColorPiezas getColorDeJugador() {
        return jugadorQueRealizoLaJugada.getColor();
    }

    public void ingresarCoordenadasIniciales(String jugada) throws SintaxisInvalida {
        if (!(Pattern.matches("[a-h][1-8]", jugada.toLowerCase()))) {
            throw new SintaxisInvalida("Sintaxis no valida");
        }

        this.coordenadasIniciales = new int [2];

        coordenadasIniciales[0] = transformarFilaIngresadaEnIndice(jugada.charAt(1));
        coordenadasIniciales[1] = transformarColumnaIngresadaEnIndice(jugada.charAt(0));
    }

    public void ingresarCoordenadasFinales(String coordenadasFinales) throws SintaxisInvalida {
        if (!(Pattern.matches("[a-h][1-8]", coordenadasFinales.toLowerCase()))) {
            throw new SintaxisInvalida("Sintaxis no valida");
        }

        this.coordenadasFinales = new int [2];

        this.coordenadasFinales[0] = transformarFilaIngresadaEnIndice(coordenadasFinales.charAt(1));
        this.coordenadasFinales[1] = transformarColumnaIngresadaEnIndice(coordenadasFinales.charAt(0));
    }
}
