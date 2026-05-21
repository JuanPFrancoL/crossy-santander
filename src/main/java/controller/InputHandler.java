package controller;

import model.Jugador;
import view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**

 Clase que permite usar teclas para mover el jugador.*/
public class InputHandler extends KeyAdapter {
    private Jugador jugador;
    private GamePanel gamePanel;

    //Constructor
    public InputHandler(Jugador jugador, GamePanel gamePanel) {
        this.jugador = jugador;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_LEFT) {
            jugador.setDirection(Jugador.DIR_LEFT);
        } else if (tecla == KeyEvent.VK_RIGHT) {
            jugador.setDirection(Jugador.DIR_RIGHT);
        } else if (tecla == KeyEvent.VK_UP) {
            jugador.setDirection(Jugador.DIR_UP);
        } else if (tecla == KeyEvent.VK_DOWN) {
            jugador.setDirection(Jugador.DIR_DOWN);
        }
        jugador.update();
    }


    @Override
    public void keyReleased(KeyEvent e) {
        jugador.setDirection(Jugador.DIR_NONE);
    }
}