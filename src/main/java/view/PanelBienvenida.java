package view;

import javax.swing.*;

public class PanelBienvenida extends JPanel {
    private MainFrame frame;

    public PanelBienvenida(MainFrame frame) {
        this.frame = frame;
        // botón para ir al siguiente panel
        JButton btnJugar = new JButton("Jugar");
        //btnJugar.addActionListener(e -> frame.mostrarPanel("reglas"));
        add(btnJugar);
    }
}
