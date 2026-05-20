package model;

public class Arbol extends Entity {
    private static final String SPRITE = "tree.png";

    public Arbol(int x, int y) {
        super(x, y, uploadImage("sprites/features/tree.png"));

    }

    @Override
    public void update() {

    }
}
