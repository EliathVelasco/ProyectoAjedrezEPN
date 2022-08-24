package ajedrez.partida;

public class Jugador {
    private final ColorPiezas colorDelJugador;
    public Jugador(ColorPiezas colorDelJugador) {
        this.colorDelJugador = colorDelJugador;
    }

    public ColorPiezas getColor() {
        return colorDelJugador;
    }
}
