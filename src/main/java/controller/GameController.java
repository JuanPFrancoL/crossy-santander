package controller;

import model.Bus;
import model.Empanada;
import model.Jugador;
import model.Moto;
import model.Taxi;
import model.Vive100;
import view.GamePanel;
import view.MainFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Controlador principal del juego
 * Implementa runnable para correr el bucle a 60fps en un hilo separado
 * Detecta colisiones y avisa al mainframe cuando ocurre gameover
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */
public class GameController implements Runnable {
    //GamePanel panel = new GamePanel();

    private GamePanel panel; // Panel principal de la vista
    private MainFrame frame; // Ventana principal para ir al gameover
    private volatile boolean enEjecucion = true; // Controla si el bucle esta corriendo
    private ArrayList<Jugador> historial = new ArrayList<Jugador>(); // Historial de jugadores de la sesion

    private Clip choque;
    private Clip musica;

    /**
     * Constructor
     *
     * @param panel el panel del juego
     */
    public GameController(GamePanel panel) {
        this.panel = panel;
    }

    /**
     * Bucle principal del juego a 60fps
     * Actualiza logica y detecta colisiones en cada frame
     */
    @Override
    public void run() {
        while (enEjecucion) {
            panel.update(); // el panel se encarga de redibujar
            comprobarColisiones();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Revisa si el jugador colisiono con algun vehiculo o item
     * Si el jugador pierde todas las vidas avisa al MainFrame para mostrar GameOver
     */
    private void comprobarColisiones() {
        Jugador jugador = panel.getJugador();
        if (jugador == null) return;
        if (!jugador.isActive()) return;

        // Hitbox del jugador
        Rectangle hitJugador = new Rectangle(jugador.getX() + 5, jugador.getY() + 5, 40, 60);

        // Colision con buses
        for (Bus b : panel.getBuses()) {
            Rectangle hitBus = new Rectangle(b.getX() + 5, b.getY() + 5, 110, 90);
            if (hitJugador.intersects(hitBus)) {
                jugador.receiveDamage(Jugador.DAMAGE_VEHICLE);
                verificarGameOver(jugador);
                return;
            }
        }

        // Colision con taxis
        for (Taxi t : panel.getTaxis()) {
            Rectangle hitTaxi = new Rectangle(t.getX() + 5, t.getY() + 5, 110, 90);
            if (hitJugador.intersects(hitTaxi)) {
                jugador.receiveDamage(Jugador.DAMAGE_VEHICLE);
                verificarGameOver(jugador);
                return;
            }
        }

        // Colision con motos
        for (Moto m : panel.getMotos()) {
            Rectangle hitMoto = new Rectangle(m.getX() + 5, m.getY() + 5, 70, 50);
            if (hitJugador.intersects(hitMoto)) {
                jugador.receiveDamage(Jugador.DAMAGE_VEHICLE);
                verificarGameOver(jugador);
                return;
            }
        }

        // Colision con empanadas (dar vida)
        for (Empanada emp : panel.getEmpanadas()) {
            if (!emp.isActive()) continue;
            Rectangle hitEmp = new Rectangle(emp.getX(), emp.getY(), 35, 35);
            if (hitJugador.intersects(hitEmp)) {
                emp.setActive(false);
                jugador.aplicarEmpanada();
            }
        }

        // Colision con vive100 (dar velocidad)
        for (Vive100 v : panel.getVive100s()) {
            if (!v.isActive()) continue;
            Rectangle hitV = new Rectangle(v.getX(), v.getY(), 35, 35);
            if (hitJugador.intersects(hitV)) {
                v.setActive(false);
                jugador.aplicarVive100();
            }
        }
    }

    /**
     * Revisa si el jugador quedo sin vidas
     * Si es asi detiene el bucle y le avisa al MainFrame
     *
     * @param jugador el jugador a verificar
     */
    private void verificarGameOver(Jugador jugador) {
        if (!jugador.isActive()) {
            enEjecucion = false;
            historial.add(jugador);
            System.out.println("Game Over - " + jugador.getNombreJugador() + " | Puntaje: " + jugador.getScore());
        }
    }

    /**
     * Detiene el bucle del juego
     */
    public void detener() {
        enEjecucion = false;
    }

    /**
     * Carga un archivo .wav desde /resources/sounds/ y devuelve un Clip listo para usar.
     */
    private Clip cargarSonido(String archivo) {
        try {
            InputStream is = getClass().getResourceAsStream("/sounds/" + archivo);
            if (is == null) {
                System.err.println("Sonido no encontrado: " + archivo);
                return null;
            }
            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            return clip;
        } catch (Exception e) {
            System.err.println("Error cargando sonido: " + archivo);
            return null;
        }
    }

    /**
     * Reproduce un clip desde el inicio. Si ya sonaba, lo reinicia.
     */
    private void reproducirSonido(Clip clip) {
        if (clip == null) return;
        if (clip.isRunning()) clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Detiene y cierra todos los clips de audio.
     */
    private void cerrarAudio() {
        Clip[] todos = {choque, musica};
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] != null) {
                if (todos[i].isRunning()) todos[i].stop();
                todos[i].close();
            }
        }
    }
}