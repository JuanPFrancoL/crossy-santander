package view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        dibujarAnden(g2, 0);
        dibujarCarril(g2, 80);
        dibujarCarril(g2, 210);

        dibujarAnden(g2, 690);
        dibujarCarril(g2, 570);
        dibujarCarril(g2, 440);

    }

    public void dibujarCarril(Graphics2D g, int y) {
        g.setColor(new Color(47, 47, 51));
        g.fillRect(0, y, 1000, 120);
    }

    public void dibujarAnden(Graphics2D g, int y) {
        g.setColor(new Color(78, 77, 91));
        g.fillRect(0, y, 1000, 80);
    }
}
