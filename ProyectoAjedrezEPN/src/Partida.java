import java.util.Scanner;

public class Partida {
    private Tablero tablero;
    public Partida() {
        tablero = new Tablero();
    }

    public Movimiento preguntarMovimiento(Jugador jugador) {
        Scanner scannerDelJugador = new Scanner(System.in);
        String jugada = scannerDelJugador.next();

        return new Movimiento(jugada);
    }

    public void jugarPartida() {

        try {
            for (; ; ) {
                while (colorTurnoActual == BLANCAS) {
                    tablero.hacerMovimiento(preguntarMovimiento(jugadorBlanco));
                }
                while (colorTurnoActual == NEGRAS) {
                    tablero.hacerMovimiento(preguntarMovimiento(jugadorNegro));
                }
            }
        } catch (PartidaFinalizadoException e) {
            System.out.println(e.getMessage());
        }

        if(esJaqueMate()){
            throw new PartidaFinalizadoException();
        }

    }

    public void imprimirTablero(){
        System.out.println(tablero);
    }


}
