package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public abstract class Entity {
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage sprite;

    public Entity(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        if (sprite != null) {
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }
    }

    //Getters y Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        if (sprite != null) {
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Cada clase define su propia clase de actualizacion de entidad
     */
    public abstract void update();

    /**
     * Carga una imagen desde la carpeta de recursos.
     * Uso: Entidad.cargarImagen("pacman_left.png")
     */
    public static BufferedImage uploadImage(String nombre) {
        try {
            InputStream is = Entity.class.getResourceAsStream("/images/" + nombre);
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
}
