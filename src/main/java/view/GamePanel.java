package view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
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
        dibujarCiclovia(g2, 425);


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
        g.fillRect(0, y, 1000, 20);
    }
}
