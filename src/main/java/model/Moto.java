package model;

import java.awt.image.BufferedImage;

public class Moto extends Vehicle {
    private BufferedImage spriteDerecha;
    private BufferedImage spriteIzquierda;
    private static final BufferedImage[] SPRITES_DERECHA = {
            uploadImage("sprites/xtz/xtzRight.png"),
            uploadImage("sprites/bws/bwsRight.png"),
            uploadImage("sprites/cb/cbRight.png")
    };

    private static final BufferedImage[] SPRITES_IZQUIERDA = {
            uploadImage("sprites/xtz/xtzLeft.png"),
            uploadImage("sprites/bws/bwsLeft.png"),
            uploadImage("sprites/cb/cbLeft.png")
    };

    public Moto(int x, int y, BufferedImage sprite, int speed) {
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

    @Override
    public void update() {
        setX(getX() + getSpeed());

        if (getSpeed() > 0) {
            setSprite(spriteDerecha);
        } else {
            setSprite(spriteIzquierda);
        }

        if (getX() > 1000) setX(-getWidth());
        if (getX() < -getWidth()) setX(1000);
    }
}
