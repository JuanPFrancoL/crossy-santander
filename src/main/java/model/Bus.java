package model;

import java.awt.image.BufferedImage;

public class Bus extends Vehicle {
    private int speed;


    public Bus(int x, int y, BufferedImage sprite, int speed) {
        super(x, y, sprite, speed);
    }
}
