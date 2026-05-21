package controller;

import model.Bus;
import model.Empanada;
import model.Jugador;
import model.Moto;
import model.Taxi;
import model.Vive100;
import view.GamePanel;

import java.awt.*;

/**
 *
 */
public class GameController implements Runnable {
    private GamePanel panel;
    private boolean enEjecucion = true;
    private Jugador jugador;

    public GameController(GamePanel panel) {
        this.panel = panel;
    }

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
     * Revisa si el jugador colisiono con algun vehiculo.
     * Si colisiona llama a receiveDamage() en el jugador.
     * Si el jugador no tiene mas vidas, detiene el juego.
     */
    private void comprobarColisiones() {
        Jugador jugador = panel.getJugador();
        if (jugador == null) return;
        if (!jugador.isActive()) return;

        // Hitbox del jugador con margen reducido para que se sienta justo
        Rectangle hitJugador = new Rectangle(
                jugador.getX() + 5,
                jugador.getY() + 5,
                40,   // ancho del jugador dibujado (50) menos margen
                60    // alto del jugador dibujado (70) menos margen
        );

        // Revisar colision con buses
        for (Bus b : panel.getBuses()) {
            Rectangle hitBus = new Rectangle(b.getX() + 5, b.getY() + 5, 110, 90);
            if (hitJugador.intersects(hitBus)) {
                jugador.receiveDamage(Jugador.DAMAGE_VEHICLE);
                return; // un choque por frame es suficiente
            }
        }

        // Revisar colision con taxis
        for (Taxi t : panel.getTaxis()) {
            Rectangle hitTaxi = new Rectangle(t.getX() + 5, t.getY() + 5, 110, 90);
            if (hitJugador.intersects(hitTaxi)) {
                jugador.receiveDamage(Jugador.DAMAGE_VEHICLE);
                return;
            }
        }

        // Revisar colision con motos
        for (Moto m : panel.getMotos()) {
            Rectangle hitMoto = new Rectangle(m.getX() + 5, m.getY() + 5, 70, 50);
            if (hitJugador.intersects(hitMoto)) {
                jugador.receiveDamage(Jugador.DAMAGE_VEHICLE);
                return;
            }
        }

        // Colisión con empanada
        Empanada empanada = panel.getEmpanada();
        if (empanada != null && empanada.isActive()) {
            if (hitJugador.intersects(empanada.getHitBox())) {
                empanada.setActive(false);
                jugador.aplicarEmpanada();
            }
        }

        // Colisión con vive100
        Vive100 vive100 = panel.getVive100();
        if (vive100 != null && vive100.isActive()) {
            if (hitJugador.intersects(vive100.getHitBox())) {
                vive100.setActive(false);
                jugador.aplicarVive100();
            }
        }

        // Sin vidas → detener juego (aqui pueden cambiar a mostrar pantalla game over)
        if (!jugador.isActive()) {
            enEjecucion = false;
            System.out.println("Game Over - " + jugador.getNombreJugador()
                    + " | Puntaje: " + jugador.getScore());
        }
    }

    /**
     * Detiene el bucle del juego.
     */
    public void detener() {
        enEjecucion = false;
    }

}
