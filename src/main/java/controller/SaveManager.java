package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveManager {

    // guardar nombre
    public static void guardarNombre(String nombre) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("jugador.txt", true))) {
            writer.println("Nombre: " + nombre);
        } catch (IOException e) {
            System.out.println("Error guardando nombre: " + e.getMessage());
        }
    }

    //guardar score
    public static void guardarScore(int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("jugador.txt", true))) {
            writer.println("Score: " + score);
        } catch (IOException e) {
            System.out.println("Error guardando score: " + e.getMessage());
        }
    }
}