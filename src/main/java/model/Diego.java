package model;

/**
 * Personaje jugable Diego
 * Hereda de Jugador y define sus sprites propios y la animacion
 * de recoger items del suelo
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */
public class Diego extends Jugador {
    //Posicion inicial
    private static final int SPAWN_X = 400; // Posicion X de aparicion
    private static final int SPAWN_Y = -15; // Posicion Y de aparicion

    // Movimiento
    private static final int SPEED = 3; // Velocidad de movimiento en px por frame

    // Animacion de recoger item
    private boolean pickingUp; // si esta agachado recogiendo un item o no
    private int pickUpCounter; // cuenta cuantos frames faltan para terminar la animacion de recoger
    private static final int PICKUP_DURATION = 30; // cuantos frames dura la animacion de agacharse a recoger (medio segundo a 60fps)

    // Sprites
    /**
     * Frames de animacion del personaje segun estado y direccion
     * Cada indice es una postura especifica
     */
    private static final String[] FRAMES_WALK = {
            "sprites/diego/diegoBackStand.png", // 0
            "sprites/diego/diegoBackWalk1.png", // 1
            "sprites/diego/diegoBackWalk2.png", // 2
            "sprites/diego/diegoStand.png", // 3
            "sprites/diego/diegoStandLeft.png", // 4
            "sprites/diego/diegoStandRight.png", // 5
            "sprites/diego/diegoWalk1.png", // 6
            "sprites/diego/diegoWalk2.png", // 7
            "sprites/diego/diegoWalkLeft1.png", // 8
            "sprites/diego/diegoWalkRight1.png", // 9
    };

    // Sprite agachado al recoger un item del suelo
    private static final String SPRITE_DOWN = "sprites/diego/diegoDown.png";

    /**
     * Constructor
     * Carga el primer frame de animacion y posiciona al personaje
     */
    public Diego() {
        super(SPAWN_X, SPAWN_Y, uploadImage(FRAMES_WALK[3]), FRAMES_WALK.length);
        this.pickingUp = false;
        this.pickUpCounter = 0;
        setDirection(DIR_NONE);
    }

    // Logica de movimiento

    /**
     * Actualiza la posicion segun su direccion actual y velocidad
     * Si esta recogiendo un item no se mueve hasta terminar la animacion
     * Tambien actualiza animacion e inmortalidad llamando a super.update()
     */
    @Override
    public void update() {
        if (isPaused()) return;

        // Mientras recoge un tiem cuenta frames y no se mueve
        if (pickingUp) {
            pickUpCounter--;
            if (pickUpCounter <= 0) {
                pickingUp = false;  // ya termino de recoger se pone de pie
                updateSprite();
            }
            return;
        }

        setX(getX() + calculateDx() * SPEED);
        setY(getY() + calculateDy() * SPEED);

        super.update(); // Animacion,  contenedores de inmortalidad y powerup
    }

    // Animacion

    /**
     * Actualiza el sprite segun su direccion y frame actual
     * Si esta recogiendo un item muestra el sprite agachado
     */
    @Override
    public void updateSprite() {
        if (pickingUp) {
            setSprite(uploadImage(SPRITE_DOWN));
            return;
        }

        if (getDirection() == DIR_UP) {
            if (getCurrentFrame() % 2 == 0) {
                setSprite(uploadImage(FRAMES_WALK[1])); // diegoBackWalk1
            } else {
                setSprite(uploadImage(FRAMES_WALK[2])); // diegoBackWalk2
            }
        } else if (getDirection() == DIR_DOWN) {
            if (getCurrentFrame() % 2 == 0) {
                setSprite(uploadImage(FRAMES_WALK[6])); // diegoWalk1
            } else {
                setSprite(uploadImage(FRAMES_WALK[7])); // diegoWalk2
            }
        } else if (getDirection() == DIR_LEFT) {
            setSprite(uploadImage(FRAMES_WALK[8])); // diegoWalkLeft1
        } else if (getDirection() == DIR_RIGHT) {
            setSprite(uploadImage(FRAMES_WALK[9])); // diegoWalkRight1
        } else {
            setSprite(uploadImage(FRAMES_WALK[3])); // diegoStand - quieto
        }
    }

    // Powerup (item del suelo)

    /**
     * El powerup es un item que se recoge del suelo
     * No aplica logica propia del personaje al activarse
     */
    @Override
    public void onPowerUpStart() {
        // Su logica se maneja por el item del suelo en el controlador
    }

    /**
     * Al terminar el powerup, vuelve al sprite de caminar normal
     */
    @Override
    public void onPowerUpEnd() {
        updateSprite();
    }

    // Metodo recoger items

    /**
     * Inicia la animacion de agacharse para recoger un item del suelo
     * Durante un tiempo establecido de frames el personaje no se puede mover
     * Llamar desde el controlador al detectar colision con un item
     */
    public void startPickUp() {
        pickingUp = true;
        pickUpCounter = PICKUP_DURATION;
        setSprite(uploadImage(SPRITE_DOWN)); // se agacha inmediatamente
    }

    // Getters

    /**
     * @return velocidad de Diego en px por frame
     */
    public int getSpeed() {
        return SPEED;
    }

    /**
     * @return true si esta en animacion de recoger un item
     */
    public boolean isPickingUp() {
        return pickingUp;
    }
}
