package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelBienvenida extends JPanel {
    private MainFrame frame;

    public PanelBienvenida(MainFrame frame) {
        this.frame = frame;
        setBackground(new Color(30, 30, 30));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.mostrarPanel("reglas");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(83, 147, 49));
        g2.setFont(new Font("Arial", Font.BOLD, 72));
        g2.drawString("Crossing Santander", 100, 250);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 28));
        g2.drawString("Haz clic para continuar", 350, 500);
    }
}