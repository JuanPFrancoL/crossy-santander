package view;

import controller.InputHandler;
import controller.SaveManager;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private List<Moto> motos;
    private List<Arbol> arboles;
    private List<Bus> buses;
    private List<Taxi> taxis;
    private Jugador jugador;

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        addKeyListener(new InputHandler(jugador, this));
        setFocusable(true);
        requestFocus();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public GamePanel() {
        //Arboles, son estaticos
        arboles = new ArrayList<>();
        arboles.add(new Arbol(100, 355));
        arboles.add(new Arbol(300, 355));
        arboles.add(new Arbol(500, 355));
        arboles.add(new Arbol(700, 355));

        // Buses
        buses = new ArrayList<>();
        //Buses carriles superiores
        buses.add(new Bus(0, 46, 3));
        buses.add(new Bus(50, 222, -3)); // negativo = va a la izquierda
        //Buses carriles inferiores
        buses.add(new Bus(600, 428, 3));
        buses.add(new Bus(200, 530, -3));

        // Motos
        motos = new ArrayList<>();
        motos.add(new Moto(800, 70, 5));
        motos.add(new Moto(500, 245, -5));
        motos.add(new Moto(640, 545, -5));

        // Taxis
        taxis = new ArrayList<>();
        taxis.add(new Taxi(400, 134, 3));
        taxis.add(new Taxi(530, 632, -4));

        setFocusable(true);
        requestFocus();
    }

    public void update() {

        for (Moto m : motos) m.update();
        for (Bus b : buses) b.update();
        for (Taxi t : taxis) t.update();

        if (jugador != null) {
            jugador.update();

            if (jugador.getY() > 700 && jugador.isActive()) {
                SaveManager.guardarScore(jugador.getScore());
                jugador.setActive(false);
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        dibujarAnden(g2, 0);
        dibujarCarril(g2, 60);
        dibujarCarril(g2, 150);
        dibujarCarril(g2, 240);

        dibujarAnden(g2, 710);
        dibujarCarril(g2, 450);
        dibujarCarril(g2, 540);
        dibujarCarril(g2, 630);

        dibujarCiclovia(g2, 325);
        dibujarCiclovia(g2, 415);

        g.setColor(new Color(83, 147, 49));
        g.fillRect(0, 355, 1000, 60);
        for (Arbol a : arboles) {
            g2.drawImage(a.getSprite(), a.getX(), a.getY(), 50, 50, null);
        }

        for (Moto m : motos) {
            if (m.getSprite() != null) {
                g2.drawImage(m.getSprite(), m.getX(), m.getY(), 80, 60, null);
            }
        }

        for (Bus b : buses) {
            if (b.getSprite() != null) {
                g2.drawImage(b.getSprite(), b.getX(), b.getY(), 120, 100, null);
            }
        }


        for (Taxi t : taxis) {
            if (t.getSprite() != null) {
                g2.drawImage(t.getSprite(), t.getX(), t.getY(), 120, 100, null);
            }
        }

        if (jugador != null && jugador.getSprite() != null) {
            g2.drawImage(jugador.getSprite(), jugador.getX(), jugador.getY(), 50, 70, null);
        }
        if (jugador != null) {

            g.setColor(Color.white);
            g.drawString("Puntos: " + jugador.getScore(), 20, 30);
        }



    }

    private Color carriles = new Color(47, 47, 51);

    public void dibujarCarril(Graphics2D g, int y) {
        g.setColor(carriles);
        g.fillRect(0, y, 1000, 80);
    }

    public void dibujarAnden(Graphics2D g, int y) {
        g.setColor(new Color(78, 77, 91));
        g.fillRect(0, y, 1000, 60);
    }

    public void dibujarCiclovia(Graphics2D g, int y) {
        g.setColor(carriles);
        g.fillRect(0, y, 1000, 30);
    }



}
