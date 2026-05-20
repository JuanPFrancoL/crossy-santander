package model;

import java.awt.image.BufferedImage;

public class Vehicle extends Entity {
    protected int speed;

    public Vehicle(int x, int y, BufferedImage sprite, int speed) {
        super(x, y, sprite);
        this.speed = speed;
    }

    @Override
    public void update() {

    }
}
