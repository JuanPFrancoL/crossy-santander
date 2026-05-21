package model;

import java.awt.image.BufferedImage;

public class Vive100 extends Entity {
    /**
     * Constructor base que inicializa posicion, sprite y dimensiones
     *
     * @param x      Posicion X inicial
     * @param y      Posicion Y inicial
     * @param sprite Imagen de la entidad cargada con uploadImage()
     */
    public Vive100(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.aplicarVive100();
    }

    @Override
    public void update() {

    }
}
