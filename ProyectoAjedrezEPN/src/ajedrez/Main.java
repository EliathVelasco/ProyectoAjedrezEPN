package ajedrez;
import ajedrez.partida.*;
import ajedrez.excepciones.PartidaFinalizadoException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws PartidaFinalizadoException {
        Scanner scannerDeLaOpcion = new Scanner(System.in);
        System.out.print("Elija una opcion:\n");
        System.out.println("1. Crear nueva partida");
        System.out.println("2. Cargar partida");
        System.out.println("3. Salir");

        int opcion = scannerDeLaOpcion.nextInt();

        switch (opcion) {
            case 1:
                Partida partida = new Partida();
                partida.imprimirTablero();
                partida.jugarPartida();
                break;
            case 2:
                System.out.println("FALTA HACERLO XD");
                break;
            case 3:
                System.out.println("SALTE PUES MMVRG");
                break;
            default:
                System.out.println("Valor ingresado invalido XDDDDDDDDDDDDDDDDDD");
        }

    }
}