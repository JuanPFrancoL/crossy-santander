package view;

import javax.swing.*;
import java.awt.*;

public class PanelUsuario extends JPanel {
    private MainFrame frame;
    private JTextField campoNombre;
    private String nombreIngresado = "";

    public PanelUsuario(MainFrame frame) {
        this.frame = frame;
        setBackground(new Color(36, 0, 49));
        setLayout(null); // posicionamiento manual

        // Campo de texto
        campoNombre = new JTextField();
        campoNombre.setBounds(300, 350, 400, 50);
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 24));
        campoNombre.setHorizontalAlignment(JTextField.CENTER);
        add(campoNombre);

        // Botón continuar
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(400, 450, 200, 50);
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 22));
        btnContinuar.setBackground(new Color(88, 19, 110));
        btnContinuar.setForeground(new Color(105, 138, 255));
        btnContinuar.addActionListener(e -> {
            nombreIngresado = campoNombre.getText().trim();
            if (!nombreIngresado.isEmpty()) {
                frame.setNombreJugador(nombreIngresado);
                frame.mostrarPanel("personaje");
            } else {
                campoNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
        });
        add(btnContinuar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(101, 29, 155));
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        g2.drawString("Ingresa tu nombre", 286, 250);

        g2.setFont(new Font("Arial", Font.PLAIN, 22));
        g2.setColor(new Color(46, 94, 180));
        g2.drawString("Escribe tu nombre y presiona Continuar", 300, 320);
    }
}