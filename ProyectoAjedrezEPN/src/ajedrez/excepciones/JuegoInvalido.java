package ajedrez.excepciones;

public class JuegoInvalido extends Throwable {
    public JuegoInvalido(String mensaje) {
        super(mensaje);
    }
}
