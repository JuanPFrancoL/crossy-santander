package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelGameOver extends JPanel {
    private MainFrame frame;
    private String nombreJugador = "";
    private int puntaje = 0;
    private int tiempo = 0;

    public PanelGameOver(MainFrame frame) {
        this.frame = frame;
        setBackground(new Color(20, 20, 20));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                // Botón reiniciar
                if (x >= 250 && x <= 480 && y >= 500 && y <= 560) {
                    frame.reiniciarJuego();
                }

                // Botón salir
                if (x >= 520 && x <= 750 && y >= 500 && y <= 560) {
                    System.exit(0);
                }
            }
        });
    }

    // El GameController llama esto antes de mostrar el panel
    public void setDatos(String nombre, int puntaje, int tiempo) {
        this.nombreJugador = nombre;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Título
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 80));
        g2.drawString("GAME OVER", 180, 180);

        // Nombre
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        g2.drawString("Jugador: " + nombreJugador, 350, 280);

        // Puntaje
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.drawString("Puntaje: " + puntaje, 380, 350);

        // Tiempo
        g2.setColor(Color.CYAN);
        g2.setFont(new Font("Arial", Font.PLAIN, 28));
        g2.drawString("Tiempo: " + tiempo + " segundos", 340, 420);

        // Botón reiniciar
        g2.setColor(new Color(83, 147, 49));
        g2.fillRoundRect(250, 500, 230, 60, 20, 20);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 26));
        g2.drawString("Reiniciar", 295, 540);

        // Botón salir
        g2.setColor(new Color(180, 30, 30));
        g2.fillRoundRect(520, 500, 230, 60, 20, 20);
        g2.setColor(Color.WHITE);
        g2.drawString("Salir", 600, 540);
    }
}