package model;

/**
 * Personaje jugable Pablo
 * Hereda de Jugador y define sus sprites y aniamcion
 * de recoger items del suelo
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */
public class Pablo extends Jugador {
    // Posicion inicial
    private static final int SPAWN_X = 200; // Posicion X de aparicion
    private static final int SPAWN_Y = 20; // Posicion Y de aparicion

    // Movimiento
    private static final int SPEED = 3; // Velocidad de movimiento en px por frame

    // Animacion de recoger item
    private static final int PICKUP_DURATION = 30; // Cuantos frames dura la animacion (medio segundo a 60fps)
    private boolean pickingUp; // Dice si esta agachado recogiendo un item del suelo
    private int pickUpCounter; // Contador de frames que faltan para terminar la animacion

    // Sprites
    /**
     * Frames de animacion segun estado y direccion
     * Cada indice representa una postura especifica
     */
    private static final String[] FRAMES_WALK = {
            "sprites/pablo/pabloBackStand.png", // 0
            "sprites/pablo/pabloBackWalk1.png", // 1
            "sprites/pablo/pabloBackWalk2.png", // 2
            "sprites/pablo/pabloStand.png", // 3
            "sprites/pablo/pabloStandLeft.png", // 4
            "sprites/pablo/pabloStandRight.png", // 5
            "sprites/pablo/pabloWalk1.png", // 6
            "sprites/pablo/pabloWalk2.png", // 7
            "sprites/pablo/pabloWalkLeft.png", // 8
            "sprites/pablo/pabloWalkRight.png", // 9
    };

    public static final String SPRITE_DOWN = "sprites/pablo/pabloDown.png";

    /**
     * Constructor
     * Carga el sprite de frente quieto y lo posiciona
     */
    public Pablo() {
        super(SPAWN_X, SPAWN_Y, uploadImage(FRAMES_WALK[3]), FRAMES_WALK.length);
        this.pickingUp = false;
        this.pickUpCounter = 0;
        setDirection(DIR_NONE);
    }


    // Logica de movimiento

    /**
     * Actualiza la posicion segun su direccion y velocidad
     * Si esta recogiendo un item no se mueve hasta terminar la animacion
     * Llama a super.update() para animacion e inmortalidad
     */
    @Override
    public void update() {
        if (isPaused()) return;

        // Mientras recoge un item: cuenta frames y no se mueve
        if (pickingUp) {
            pickUpCounter--;
            if (pickUpCounter <= 0) {
                pickingUp = false; // termino de recoger, se pone de pie
                updateSprite();
            }
            return;
        }

        setX(getX() + calculateDx() * SPEED);
        setY(getY() + calculateDy() * SPEED);

        super.update(); // animacion + contadores de invencibilidad y powerup
    }

    // Animacion

    /**
     * Actualiza el sprite segun su direccion y frame actual
     * Si esta recogiendo un item muestra sprite agachado
     */
    @Override
    public void updateSprite() {
        if (pickingUp) {
            setSprite(uploadImage(SPRITE_DOWN));
            return;
        }

        if (getDirection() == DIR_UP) {
            if (getCurrentFrame() % 2 == 0) {
                setSprite(uploadImage(FRAMES_WALK[1]));
            } else {
                setSprite(uploadImage(FRAMES_WALK[2]));
            }
        } else if (getDirection() == DIR_DOWN) {
            if (getCurrentFrame() % 2 == 0) {
                setSprite(uploadImage(FRAMES_WALK[6]));
            } else {
                setSprite(uploadImage(FRAMES_WALK[7]));
            }
        } else if (getDirection() == DIR_LEFT) {
            setSprite(uploadImage(FRAMES_WALK[8]));
        } else if (getDirection() == DIR_RIGHT) {
            setSprite(uploadImage(FRAMES_WALK[9]));
        } else {
            setSprite(uploadImage(FRAMES_WALK[3]));
        }
    }

    // powerup (item del suelo)

    /**
     * El powerup es un item recogido del suelo
     * No aplica logica desde personaje
     */
    @Override
    public void onPowerUpStart() {
        // Logica manejada por el item del suelo
    }

    /**
     * Al terminar el efecto del powerup vuelve a sprite normal
     */
    @Override
    public void onPowerUpEnd() {
        updateSprite();
    }

    // Metodo recoge items

    /**
     * Inicia la animacion de agacharse pa recoger un item del suelo
     * Llammar desde el controlador al detectar colision con un item
     */
    public void startPickUp() {
        pickingUp = true;
        pickUpCounter = PICKUP_DURATION;
        setSprite(uploadImage(SPRITE_DOWN)); // se agacha
    }

    // Gtterrs

    /**
     * @return velocidad en px por frame
     */
    public int getSpeed() {
        return SPEED;
    }

    /**
     * @return true si esta en animacion de recoger item
     */
    public boolean isPickingUp() {
        return pickingUp;
    }
}
