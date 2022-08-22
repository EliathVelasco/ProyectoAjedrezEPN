package ajedrez.excepciones;

public class SintaxisInvalida extends Exception {
    public SintaxisInvalida(String message) {
        super(message);
    }

    public static class CoronacionAvanzando extends Exception {
    }
}
