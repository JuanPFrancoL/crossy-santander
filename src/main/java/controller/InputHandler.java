package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que permite usar teclas para mover el jugador.
 */
public class InputHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_LEFT) {

        } else if (tecla == KeyEvent.VK_RIGHT) {

        } else if (tecla == KeyEvent.VK_UP) {

        } else if (tecla == KeyEvent.VK_DOWN) {
            
        }
    }
}
