package controller;

import view.GamePanel;

/**
 *
 */
public class GameController implements Runnable {
    GamePanel panel = new GamePanel();

    public GameController(GamePanel panel) {
        this.panel = panel;
    }

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
