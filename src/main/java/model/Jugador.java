package main.java.model;

import model.Entity;

import java.awt.image.BufferedImage;

/**
 * Clase abstracta que representa el jugador en el juego
 * Extiende de Entity y agrega la logica de estado del jugador:
 * vidas, puntos, vida, powerups y posicion de spawn
 * <p>
 * Las subclases Diego, Jhoem y Pablo definen los sprites y estadisticas propios de cada personaje
 * Aplica herencia, encapsulamiento y polimorfismo
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */
public abstract class Jugador extends Entity {

    /**
     * Constructor base que inicializa posicion, sprite y dimensiones
     *
     * @param x      Posicion X inicial
     * @param y      Posicion Y inicial
     * @param sprite Imagen de la entidad cargada con uploadImage()
     */
    public Jugador(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }
}
