package view;

import controller.GameController;

import javax.swing.*;

public class MainFrame extends JFrame {
    GamePanel panel = new GamePanel();

    public MainFrame() {
        setTitle("Crossy Santander");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setSize(1000, 800);
        setVisible(true);
        GameController controller = new GameController(panel);
        Thread hilo = new Thread(controller);
        hilo.start();
    }
}
