package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelReglas extends JPanel {
    private MainFrame frame;

    public PanelReglas(MainFrame frame) {
        this.frame = frame;
        setBackground(new Color(30, 30, 30));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // clic en botón continuar
                if (x >= 400 && x <= 600 && y >= 600 && y <= 650) {
                    frame.mostrarPanel("personaje");
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        g2.drawString("Reglas e Instrucciones", 200, 100);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 24));
        g2.drawString("• Cruza todos los carriles sin ser atropellado", 100, 200);
        g2.drawString("• Usa las flechas del teclado para moverte", 100, 260);
        g2.drawString("• Evita los buses, taxis y motos", 100, 320);
        g2.drawString("• Llega al andén del otro lado para ganar", 100, 380);
        g2.drawString("• Tienes 3 vidas", 100, 440);

        // Botón continuar
        g2.setColor(new Color(83, 147, 49));
        g2.fillRoundRect(400, 600, 200, 50, 20, 20);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Continuar", 440, 633);
    }
}