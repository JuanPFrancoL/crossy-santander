package model;

import java.awt.image.BufferedImage;

public class Taxi extends Vehicle {
    private BufferedImage spriteDerecha;
    private BufferedImage spriteIzquierda;

    public Taxi(int x, int y, int speed) {
        super(x, y, null, speed);
        setSpriteDerecha(uploadImage("sprites/taxi/taxiRight.png"));
        setSpriteIzquierda(uploadImage("sprites/taxi/taxiLeft.png"));

        if (speed >= 0) {
            setSprite(getSpriteDerecha());
        } else {
            setSprite(getSpriteIzquierda());
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
