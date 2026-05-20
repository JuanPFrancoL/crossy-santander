package view;

import javax.swing.*;

public class MainFrame extends JFrame {
    GamePanel panel = new GamePanel();

    public MainFrame() {
        setTitle("Cuadrado KeyPressed");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setSize(1000, 800);
        setVisible(true);
    }
}
