package controller;

import view.GamePanel;

/**
 *
 */
public class GameController implements Runnable {
    GamePanel panel = new GamePanel();

    @Override
    public void run() {
        while (true) {
            panel.update(); // el panel se encarga de redibujar
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
