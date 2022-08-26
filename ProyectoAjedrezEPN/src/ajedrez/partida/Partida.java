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

    public Movimiento preguntarMovimiento(Jugador jugador) throws SintaxisInvalida, GuardarLaPartida, CoronacionAvanzando, EnroqueLargo, EnroqueCorto, CoronacionCapturando, MovimientoInvalido {
        Scanner scannerDelJugador = new Scanner(System.in);
        String jugada = scannerDelJugador.next();
        Movimiento moviento = new Movimiento(jugador);
        moviento.ingresarCoordenadasIniciales(jugada);
        tablero.mostrarCasillasALasQueSePuedeMover(moviento);

        Scanner scannerCoordenadasFinales = new Scanner(System.in);
        String coordenadasFinales = scannerCoordenadasFinales.next();
        moviento.ingresarCoordenadasFinales(coordenadasFinales);
        return moviento;
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
        try {
            for (; ; ) {
                while (colorTurnoActual == ColorPiezas.BLANCAS) {
                    try {
                        tablero.hacerMovimiento(preguntarMovimiento(jugadorBlanco));
                        colorTurnoActual = ColorPiezas.NEGRAS;
                    } catch (SintaxisInvalida si) {
                        System.out.println(si.getMessage());
                    } catch (MovimientoInvalido mi) {
                        System.out.println(mi.getMessage());
                    } catch (CoronacionAvanzando e) {
                        throw new RuntimeException(e);
                    } catch (EnroqueLargo e) {
                        throw new RuntimeException(e);
                    } catch (EnroqueCorto e) {
                        throw new RuntimeException(e);
                    } catch (CoronacionCapturando e) {
                        throw new RuntimeException(e);
                    } finally {
                        System.out.println(tablero);
                    }
                }

                while (colorTurnoActual == ColorPiezas.NEGRAS) {
                    try {
                        tablero.hacerMovimiento(preguntarMovimiento(jugadorNegro));
                        colorTurnoActual = ColorPiezas.BLANCAS;
                    } catch (SintaxisInvalida si) {
                        System.out.println(si.getMessage());
                    } catch (MovimientoInvalido mi) {
                        System.out.println(mi.getMessage());
                    } catch (CoronacionAvanzando e) {
                        throw new RuntimeException(e);
                    } catch (EnroqueLargo e) {
                        throw new RuntimeException(e);
                    } catch (EnroqueCorto e) {
                        throw new RuntimeException(e);
                    } catch (CoronacionCapturando e) {
                        throw new RuntimeException(e);
                    } finally {
                        System.out.println(tablero);
                    }
                }
            }
        }catch (GuardarLaPartida gp){
            String partidaAGuardar = "";
            partidaAGuardar += tablero.guardarPartida();
            partidaAGuardar += colorTurnoActual+"/";
            System.out.println(partidaAGuardar);
        }/*catch (PartidaTerminada pt){

        }*/
    }

    public void imprimirTablero() {
        System.out.println(tablero);
    }
}
