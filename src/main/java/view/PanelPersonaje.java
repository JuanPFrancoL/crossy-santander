package view;

import model.Diego;
import model.Entity;
import model.Jhoem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PanelPersonaje extends JPanel {
    private MainFrame frame;
    private GamePanel gamePanel;

    private BufferedImage imgP1;
    private BufferedImage imgP2;
    private BufferedImage imgP3;

    public PanelPersonaje(MainFrame frame, GamePanel gamePanel) {
        this.frame = frame;
        this.gamePanel = gamePanel;
        setBackground(new Color(36, 0, 49));

        imgP1 = Entity.uploadImage("sprites/jhoem/jhoemStand.png");
        imgP2 = Entity.uploadImage("sprites/diego/diegoStand.png");
        imgP3 = Entity.uploadImage("sprites/pablo/pabloStand.png");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 100 && x <= 250 && y >= 300 && y <= 450) {
                    gamePanel.setJugador(new Jhoem());
                    frame.mostrarPanel("juego");
                } else if (x >= 420 && x <= 570 && y >= 300 && y <= 450) {
                    gamePanel.setJugador(new Diego());
                    frame.mostrarPanel("juego");
                } else if (x >= 740 && x <= 890 && y >= 300 && y <= 450) {
                    //gamePanel.setJugador(new Pablo());
                    frame.mostrarPanel("juego");
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(159, 29, 224));
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        g2.drawString("Elige tu personaje", 290, 100);

        if (imgP1 != null) g2.drawImage(imgP1, 100, 300, 150, 150, null);
        if (imgP2 != null) g2.drawImage(imgP2, 420, 300, 150, 150, null);
        if (imgP3 != null) g2.drawImage(imgP3, 740, 300, 150, 150, null);

        g2.setColor(new Color(123, 125, 255));
        g2.setFont(new Font("Arial", Font.PLAIN, 22));
        g2.drawString("Personaje 1", 110, 480);
        g2.drawString("Personaje 2", 430, 480);
        g2.drawString("Personaje 3", 750, 480);
    }
}