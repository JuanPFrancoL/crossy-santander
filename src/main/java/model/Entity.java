package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Clase abstracta base para todas las entidades del juego
 * Define posicion, sprite, direccion y dimensiones
 * Las subclases deben implementar el metodo update()
 *
 * @author Diego, Jhoem, Pablo
 * @version 1.0
 */

public abstract class Entity {
    public static final int DIR_NONE = 0;
    public static final int DIR_LEFT = 1;
    public static final int DIR_UP = 2;
    public static final int DIR_RIGHT = 3;
    public static final int DIR_DOWN = 4;

    private int x; // Posicion horizontal en el panel
    private int y; // Posicion vertical en el panel
    private int width; // Ancho del sprite en pixeles
    private int height; // Alto del sprite en pixeles
    private BufferedImage sprite;
    private boolean active; // Indica si la entidad esta activa en el juego
    private int direction; // Direccion actual del movimiento

    /**
     * Constructor base que inicializa posicion, sprite y dimensiones
     *
     * @param x      Posicion X inicial
     * @param y      Posicion Y inicial
     * @param sprite Imagen de la entidad cargada con uploadImage()
     */
    public Entity(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        if (sprite != null) {
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }
        this.active = true;
        this.direction = DIR_NONE;
    }

    /**
     * Actualiza la logica de la entidad en cada frame
     * Cada subclase define su propio comportamiento
     */
    public abstract void update();

    /**
     * Retorna el hitbox (colision) de la entidad en su posicion actual
     *
     * @return Rectangle con la posicion y dimensiones actuales
     */
    public Rectangle getHitBox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Retorna el hitbox de la entidad en una posicion futura
     * Para predecir colisiones antes de mover la entidad
     *
     * @param px Posicion X futura
     * @param py Posicion Y futura
     * @return Rectangle en la posicion indicada
     */
    public Rectangle getHitBox(int px, int py) {
        return new Rectangle(px, py, width, height);
    }

    /**
     * Calcula el desplazamiento horizontal
     *
     * @return -1 si es izquierda, 1 si es a la derecha, 0 si no es para los lados
     */
    public int calculateDx() {
        if (direction == DIR_LEFT) {
            return -1;
        }
        if (direction == DIR_RIGHT) {
            return 1;
        }
        return 0;
    }

    /**
     * Calcula el desplazamiento vertical
     *
     * @return -1 si es arriba, 1 si es a la abajo, 0 si no de forma vertical
     */
    public int calculateDy() {
        if (direction == DIR_UP) {
            return -1;
        }
        if (direction == DIR_DOWN) {
            return 1;
        }
        return 0;
    }

    /**
     * Carga una imagen desde la carpeta de recursos.
     * Uso: Entidad.cargarImagen("imagen.png")
     */
    public static BufferedImage uploadImage(String nombre) {
        try {
            InputStream is = Entity.class.getResourceAsStream("/" + nombre);
            if (is == null) {
                System.err.println("Imagen no encontrada: " + nombre);
                return null;
            }
            return ImageIO.read(is);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + nombre);
            return null;
        }
    }

    //  Getters y Setters
    // posicion X actual
    public int getX() {
        return x;
    }

    // nueva posicion x
    public void setX(int x) {
        this.x = x;
    }

    // posicion Y actual
    public int getY() {
        return y;
    }

    // nueva posicion Y
    public void setY(int y) {
        this.y = y;
    }

    // ancho actual en px
    public int getWidth() {
        return width;
    }

    // nuevo ancho
    public void setWidth(int width) {
        this.width = width;
    }

    // alto actual en px
    public int getHeight() {
        return height;
    }

    // nueva altura
    public void setHeight(int height) {
        this.height = height;
    }

    // sprite (imagen) actual
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * Asigna un nuevo sprite y actualiza las dimensiones automaticamente
     *
     * @param sprite nueva imagen, si es null no actualiza dimensiones
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
        if (sprite != null) {
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }
    }

    // Si la entidad esta activa devuelve true
    public boolean isActive() {
        return active;
    }

    // true para activar, false para desactivar
    public void setActive(boolean active) {
        this.active = active;
    }

    // direccion actual
    public int getDirection() {
        return direction;
    }

    // nueva direccion
    public void setDirection(int direction) {
        this.direction = direction;
    }
}
