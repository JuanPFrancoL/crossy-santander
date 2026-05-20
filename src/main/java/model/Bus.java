package model;

import java.awt.image.BufferedImage;

public class Bus extends Vehicle {
    private static final BufferedImage[] SPRITES_DERECHA = {
            uploadImage("sprites/grancaldas/grancaldasRight.png"),
            uploadImage("sprites/socobus/socobusRight.png")
    };

    private static final BufferedImage[] SPRITES_IZQUIERDA = {
            uploadImage("sprites/grancaldas/grancaldasLeft.png"),
            uploadImage("sprites/socobus/socobusLeft.png")
    };

    private BufferedImage spriteDerecha;
    private BufferedImage spriteIzquierda;

    public BufferedImage getSpriteDerecha() {
        return spriteDerecha;
    }

    public void setSpriteDerecha(BufferedImage spriteDerecha) {
        this.spriteDerecha = spriteDerecha;
    }

    public BufferedImage getSpriteIzquierda() {
        return spriteIzquierda;
    }

    public void setSpriteIzquierda(BufferedImage spriteIzquierda) {
        this.spriteIzquierda = spriteIzquierda;
    }


    public Bus(int x, int y, int speed) {
        super(x, y, null, speed);
        int indice = (int) (Math.random() * SPRITES_DERECHA.length);
        setSpriteDerecha(SPRITES_DERECHA[indice]);
        setSpriteIzquierda(SPRITES_IZQUIERDA[indice]);
        // asigna sprite inicial según dirección
        if (speed >= 0) {
            setSprite(spriteDerecha);
        } else {
            setSprite(spriteIzquierda);
        }
    }

    @Override
    public void update() {
        setX(getX() + getSpeed()); // avanza

        // cambia sprite según dirección
        if (getSpeed() > 0) {
            setSprite(spriteDerecha);
        } else {
            setSprite(spriteIzquierda);
        }

        // reaparece al otro lado
        if (getX() > 1000) setX(-getWidth());
        if (getX() < -getWidth()) setX(1000);
    }
}
