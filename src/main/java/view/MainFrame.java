package view;

import controller.AudioManager;
import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GamePanel gamePanel;
    private JPanel contenedor;
    private CardLayout cardLayout;
    private String nombreJugador;
    private PanelGameOver panelGameOver;
    private Thread gameThread;
    private GameController controller;
    private AudioManager audioManager;

    public MainFrame() {
        setTitle("Crossy Santander");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setVisible(true);
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        setLocationRelativeTo(null);
        gamePanel = new GamePanel(this);

        contenedor.add(new PanelBienvenida(this), "bienvenida");
        contenedor.add(new PanelReglas(this), "reglas");
        contenedor.add(new PanelPersonaje(this, gamePanel), "personaje");
        contenedor.add(gamePanel, "juego");
        contenedor.add(new PanelUsuario(this), "usuario");

        add(contenedor);
        setVisible(true);

        cardLayout.show(contenedor, "bienvenida");
        panelGameOver = new PanelGameOver(this);
        contenedor.add(panelGameOver, "gameover");
        audioManager = new AudioManager();
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);

        // Arranca el hilo solo cuando empieza el juego
        if (nombre.equals("juego")) {
            audioManager.reproducirLoop(
                    audioManager.getMusica()
            );
            gamePanel.requestFocusInWindow();

            if (gameThread == null || !gameThread.isAlive()) {

                controller = new GameController(gamePanel);

                gameThread = new Thread(controller);

                gameThread.start();
            }
        }
    }

    public void setNombreJugador(String nombre) {
        this.nombreJugador = nombre;
        gamePanel.setNombreJugador(nombre); // lo pasa al panel
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void mostrarGameOver(String nombre, int puntaje, int tiempo) {
        panelGameOver.setDatos(nombre, puntaje, tiempo);
        mostrarPanel("gameover");
        audioManager.detener(
                audioManager.getMusica()
        );
        audioManager.reproducirLoop(
                audioManager.getWin()
        );
    }

    public void reiniciarJuego() {
        gamePanel.reiniciar();
        mostrarPanel("usuario"); // vuelve al ingreso de nombre
    }
}
