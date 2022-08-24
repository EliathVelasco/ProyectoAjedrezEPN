package ajedrez.partida;

import ajedrez.excepciones.*;

import java.util.Scanner;

public class Partida {
    private final Tablero tablero;
    private ColorPiezas colorTurnoActual;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;

    public Partida() {
        jugadorBlanco = new Jugador(ColorPiezas.BLANCAS);
        jugadorNegro = new Jugador(ColorPiezas.NEGRAS);
        colorTurnoActual = ColorPiezas.BLANCAS;
        tablero = new Tablero();
    }

    public Movimiento preguntarMovimiento(Jugador jugador) throws SintaxisInvalida {
        Scanner scannerDelJugador = new Scanner(System.in);
        String jugada = scannerDelJugador.next();
        return new Movimiento(jugada, jugador);

    }

    /*
     * hacerMovimiento(){
     *   if(validarMovimiento()){****}
     * }
     *
     * private boolean validarMovimiento(Moviento){
     *
     * }
     * */
    public void jugarPartida() {
        for (; ; ) {
            while (colorTurnoActual == ColorPiezas.BLANCAS) {
                try {
                    tablero.hacerMovimiento(preguntarMovimiento(jugadorBlanco));
                    System.out.println(tablero);
                    colorTurnoActual = ColorPiezas.NEGRAS;
                }catch(SintaxisInvalida si){
                    System.out.println(si.getMessage());
                }catch(MovimientoInvalido mi){
                    System.out.println(mi.getMessage());
                }
            }

            while (colorTurnoActual == ColorPiezas.NEGRAS) {
                try {
                    tablero.hacerMovimiento(preguntarMovimiento(jugadorNegro));
                    System.out.println(tablero);
                    colorTurnoActual = ColorPiezas.BLANCAS;
                }catch(SintaxisInvalida si){
                    System.out.println(si.getMessage());
                }catch(MovimientoInvalido mi){
                    System.out.println(mi.getMessage());
                }
            }
        }
    }

    public void imprimirTablero() {
        System.out.println(tablero);
    }
}
