package controller;

import model.Jugador;
import view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que permite usar teclas para mover el jugador.
 */
public class InputHandler extends KeyAdapter {
    Jugador jugador;
    GamePanel gamePanel;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_LEFT) {
            jugador.setY(jugador.getY() - jugador.getSpeed());
        } else if (tecla == KeyEvent.VK_RIGHT) {

        } else if (tecla == KeyEvent.VK_UP) {

        } else if (tecla == KeyEvent.VK_DOWN) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}