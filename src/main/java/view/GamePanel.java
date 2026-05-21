package view;

import controller.InputHandler;
import controller.SaveManager;
import model.Arbol;
import model.Bus;
import model.Empanada;
import model.Entity;
import model.Jugador;
import model.Moto;
import model.Taxi;
import model.Vive100;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel principal del juego, se encarga de dibujar todos los elementos
 * y de actualizar los vehiculos y el jugador en cada frame
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */
public class GamePanel extends JPanel {
    private List<Moto> motos;
    private List<Arbol> arboles;
    private List<Bus> buses;
    private List<Taxi> taxis;
    private List<Empanada> empanadas;
    private List<Vive100> vive100s;
    private Jugador jugador;
    private String nombreJugador;
    private MainFrame frame;
    private int tiempoSegundos = 0;
    private int frameCount = 0;

    /**
     * Constructor del panel
     * Inicializa y posiciona todos los vehiculos, arboles e items del mapa
     */
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

        // Items del suelo
        empanadas = new ArrayList<>();
        empanadas.add(new Empanada(200, 380, Entity.uploadImage("sprites/features/empanada.png")));
        empanadas.add(new Empanada(600, 380, Entity.uploadImage("sprites/features/empanada.png")));

        vive100s = new ArrayList<>();
        vive100s.add(new Vive100(400, 380, Entity.uploadImage("sprites/features/vive100.png")));

        setFocusable(true);
        requestFocus();
    }

    /**
     * Asigna el jugador activo y registra el inputhandler pal keylistener
     *
     * @param jugador el personaje seleccionado
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;

        for (KeyListener kl : getKeyListeners()) {
            removeKeyListener(kl);
        }
        addKeyListener(new InputHandler(jugador, this));

        setFocusable(true);
        requestFocus();
    }

    /**
     * @return el jugador activo
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Actualiza la logica de vehiculos y jugador, luego repinta el panel
     */
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

    /**
     * Dibuja todos los elementos del juego
     * carriles, vehiculos, arboles, items y el jugador
     *
     * @param g contexto grafico
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Mapa
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

        // Zona verde y arboles
        g.setColor(new Color(83, 147, 49));
        g.fillRect(0, 355, 1000, 60);
        for (Arbol a : arboles) {
            g2.drawImage(a.getSprite(), a.getX(), a.getY(), 50, 50, null);
        }

        // Vehiculos
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

        for (Moto m : motos) {
            if (m.getSprite() != null) {
                g2.drawImage(m.getSprite(), m.getX(), m.getY(), 80, 60, null);
            }
        }

        // Items
        for (Empanada emp : empanadas) {
            if (emp.isActive() && emp.getSprite() != null) {
                g2.drawImage(emp.getSprite(), emp.getX(), emp.getY(), 35, 35, null);
            }
        }
        for (Vive100 v : vive100s) {
            if (v.isActive() && v.getSprite() != null) {
                g2.drawImage(v.getSprite(), v.getX(), v.getY(), 35, 35, null);
            }
        }


        // Jugador
        if (jugador != null && jugador.getSprite() != null) {
            // Parpadeo cuando esta inmortal tras recibir daño
            if (jugador.isImmortality() && (System.currentTimeMillis() / 100) % 2 == 0) {
                // No dibuja cada 100ms para el efecto del parpadeo
            } else {
                g2.drawImage(jugador.getSprite(), jugador.getX(), jugador.getY(), 50, 70, null);
            }
        }
        if (jugador != null) {

            g.setColor(Color.white);
            g.drawString("Puntos: " + jugador.getScore(), 20, 30);
        }


        // HUD
        dibujarHUD(g2);
    }

    private void dibujarHUD(Graphics2D g2) {
        if (jugador == null) return;

        // fondo hud
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, 1000, 40);

        // Nombre
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Jugador: " + jugador.getNombreJugador(), 10, 25);

        // Vidas como corazones
        g2.setColor(new Color(220, 50, 50));
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String vidas = "";
        for (int i = 0; i < jugador.getLives(); i++) {
            vidas += "❤️ ";
        }
        g2.drawString(vidas, 350, 25);

        // Puntaje
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Puntaje: " + jugador.getScore(), 750, 25);
    }

    // Ayudas pa dibujar el mapa
    private Color carriles = new Color(47, 47, 51);

    /**
     * Dibuja un carril
     *
     * @param g graphics
     * @param y posicion Y del carril
     */
    public void dibujarCarril(Graphics2D g, int y) {
        g.setColor(carriles);
        g.fillRect(0, y, 1000, 80);
    }

    /**
     * Dibuja un anden
     *
     * @param g graphics
     * @param y posicion Y del anden
     */
    public void dibujarAnden(Graphics2D g, int y) {
        g.setColor(new Color(78, 77, 91));
        g.fillRect(0, y, 1000, 60);
    }

    /**
     * Dibuja una ciclovia
     *
     * @param g graphics
     * @param y posicion Y de la ciclovia
     */
    public void dibujarCiclovia(Graphics2D g, int y) {
        g.setColor(carriles);
        g.fillRect(0, y, 1000, 30);
    }

    // Setters y getters de listas
    public void setNombreJugador(String nombre) {
        this.nombreJugador = nombre;
        if (jugador != null) {
            jugador.setNombreJugador(nombre);
        }
    }

    public List<Bus> getBuses() {
        return buses;
    }


    public List<Taxi> getTaxis() {
        return taxis;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public List<Arbol> getArboles() {
        return arboles;
    }

    public List<Empanada> getEmpanadas() {
        return empanadas;
    }

    public List<Vive100> getVive100s() {
        return vive100s;
    }

    public void reiniciar() {
        tiempoSegundos = 0;
        frameCount = 0;
        jugador = null;
        buses.clear();
        motos.clear();
        taxis.clear();
        empanadas.clear();
        vive100s.clear();
        buses.add(new Bus(0, 46, 3));
        buses.add(new Bus(50, 222, -3));
        buses.add(new Bus(600, 428, 3));
        buses.add(new Bus(200, 530, -3));
        motos.add(new Moto(800, 70, 5));
        motos.add(new Moto(500, 245, -5));
        motos.add(new Moto(640, 545, -5));
        taxis.add(new Taxi(400, 134, 3));
        taxis.add(new Taxi(530, 632, -4));
        empanadas.add(new Empanada(200, 380, Entity.uploadImage("sprites/features/empanada.png")));
        empanadas.add(new Empanada(600, 380, Entity.uploadImage("sprites/features/empanada.png")));
        vive100s.add(new Vive100(400, 380, Entity.uploadImage("sprites/features/vive100.png")));
    }
}
