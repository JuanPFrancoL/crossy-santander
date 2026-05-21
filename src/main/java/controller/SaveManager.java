package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que proporciona metodos estaticos para almacenar
 * el historial de los jugadores y sus respectivos puntajes
 * en un archivo txt
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */
public class SaveManager {

    /**
     * Guarda el nombre de un jugador en el archivo de historial txt
     * Hace append para no borrar los registros hechos previamente
     *
     * @param nombre nombre ingresado por el usuario en la interfaz
     */
    public static void guardarNombre(String nombre) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("jugador.txt", true))) {
            writer.println("Nombre: " + nombre);
        } catch (IOException e) {
            System.out.println("Error guardando nombre: " + e.getMessage());
        }
    }

    /**
     * Guarda el puntaje obtenido por un jugador en el archivo txt
     * Usa append para registrar la puntuacion al final del archivo
     *
     * @param score puntuacion final acumulada por el jugador durante la partida
     */
    public static void guardarScore(int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("jugador.txt", true))) {
            writer.println("Score: " + score);
        } catch (IOException e) {
            System.out.println("Error guardando score: " + e.getMessage());
        }
    }
}