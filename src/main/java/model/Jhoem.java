package model;

/**
 * Personaje jugable Jhoem
 * Hereda de Jugador y define sus sprites
 * y la animacion de recoger items del suelo
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.1
 */

public class Jhoem extends model.Jugador {
    // Posicion inicial
    private static final int SPAWN_X = 200; // Posicion X de aparicion de Jhoem
    private static final int SPAWN_Y = 20; // Posicion Y de aparicion de Jhoem
    // Movimiento
    private static final int SPEED = 3; // Velocidad de movimiento en px por frame

    // Animacion de recoger item
    private static final int PICKUP_DURATION = 30; // Cuantos frames dura la animacion de agacharse a recoger (medio segundo a 60fps)
    private boolean pickingUp; // Indica si esta agachado recogiendo un item del suelo
    private int pickUpCounter; // Contador de frames que faltan para terminar la animacion de recoger

    // Sprites
    /**
     * Frames de animacion del personaje segun estado y direccion
     * Cada indice corresponde a una pose especifica
     */
    private static final String[] FRAMES_WALK = {
            "sprites/jhoem/jhoemBackStand.png", // 0
            "sprites/jhoem/jhoemBackWalk1.png", // 1
            "sprites/jhoem/jhoemBackWalk2.png", // 2
            "sprites/jhoem/jhoemStand.png", // 3
            "sprites/jhoem/jhoemStandLeft.png", // 4
            "sprites/jhoem/jhoemStandRight.png", // 5
            "sprites/jhoem/jhoemWalk1.png", // 6
            "sprites/jhoem/jhoemWalk2.png", // 7
            "sprites/jhoem/jhoemWalkLeft1.png", // 8
            "sprites/jhoem/jhoemWalkRight1.png", // 9
    };

    private static final String SPRITE_TAKING = "main/resources/sprites/jhoem/jhoemTaking.png"; // Sprite agachado al recoger un item del suelo

    /**
     * Constructor de Jhoem
     * Carga el primer frame de animacion y posiciona el personaje
     */
    public Jhoem() {
        super(SPAWN_X, SPAWN_Y, uploadImage(FRAMES_WALK[3]), FRAMES_WALK.length);
        this.pickingUp = false;
        this.pickUpCounter = 0;
        setDirection(DIR_NONE);
    }

    // Logica movimiento

    /**
     * Actualiza la posicion de jhoem segun su direccion actual y velocidad
     * Llama a super.update() para animacion y contadores
     */
    @Override
    public void update() {
        if (isPaused()) return;

        // Mientras recoge un item cuenta frames y no se mueve
        if (pickingUp) {
            pickUpCounter--;
            if (pickUpCounter <= 0) {
                pickingUp = false; // ya termino de recoger y se pone de pie
                updateSprite();
            }
            return;
        }

        setX(getX() + calculateDx() * SPEED);
        setY(getY() + calculateDy() * SPEED);

        super.update(); // animacion con contadores de inmortalidad y powerup
    }

    // Animacion

    /**
     * Actualiza el sprite segun su direccion y frame actual
     * Si esta recogiendo un item muestra el sprite agachado
     */
    @Override
    public void updateSprite() {
        if (pickingUp) {
            setSprite(uploadImage(SPRITE_TAKING));
            return;
        }

        if (getDirection() == DIR_UP) {
            if (getCurrentFrame() % 2 == 0) {
                setSprite(uploadImage(FRAMES_WALK[1])); // jhoemBackWalk1
            } else {
                setSprite(uploadImage(FRAMES_WALK[2])); // jhoemBackWalk2
            }
        } else if (getDirection() == DIR_DOWN) {
            if (getCurrentFrame() % 2 == 0) {
                setSprite(uploadImage(FRAMES_WALK[6])); // jhoemWalk1
            } else {
                setSprite(uploadImage(FRAMES_WALK[7])); // jhoemWalk2
            }
        } else if (getDirection() == DIR_LEFT) {
            setSprite(uploadImage(FRAMES_WALK[8])); // jhoemWalkLeft1
        } else if (getDirection() == DIR_RIGHT) {
            setSprite(uploadImage(FRAMES_WALK[9])); // jhoemWalkRight1
        } else {
            setSprite(uploadImage(FRAMES_WALK[3])); // jhoemStand - quieto
        }
    }

    // Powerup (items del suelo)

    /**
     * El powerup es un item recogido del suelo
     * No aplica logica propia
     */
    @Override
    public void onPowerUpStart() {
        // Logica manejada por el item del suelo
    }

    /**
     * Al terminar el efecto del powerup vuelve al sprite normal
     */
    @Override
    public void onPowerUpEnd() {
        updateSprite();
    }

    // Metodo para recoger items

    /**
     * Inicia la animacion de agacharse apra recoger un item del suelo
     * Durante el tiempo establecido de frames el personaje no se podra mover
     * Llamar desde el controlador al detectar colision con un item
     */
    public void startPickUp() {
        pickingUp = true;
        pickUpCounter = PICKUP_DURATION;
        setSprite(uploadImage(SPRITE_TAKING)); // se agacha inmediatamente
    }

    // Getters

    /**
     * velocidad de Jhoem en px por frame
     */
    public int getSpeed() {
        return SPEED;
    }

    /**
     * @return true si el personaje esta en animacion de recoger un item
     */
    public boolean isPickingUp() {
        return pickingUp;
    }
}
