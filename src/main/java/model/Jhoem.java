package main.java.model;

/**
 * Personaje jugable Jhoem
 * Hereda de Jugador y define sus sprites propios
 * Su powerup le permite recuperar vidas
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */

public class Jhoem extends Jugador {
    // Posicion inicial
    private static final int SPAWN_X = 200; // Posicion X de aparicion de Jhoem
    private static final int SPAWN_Y = 20; // Posicion Y de aparicion de Jhoem
    // Movimiento
    private static final int SPEED = 3; // Velocidad de movimiento en px por frame

    // Sprites
    /**
     * Frames de animacion al caminar
     * Carpeta: resources/sprites/jhoem/
     */
    private static final String[] FRAMES_WALK = {
            "main/resources/sprites/jhoem/jhoemBackStand.png",
            "main/resources/sprites/jhoem/jhoemBackWalk1.png",
            "main/resources/sprites/jhoem/jhoemBackWalk2.png",
            "main/resources/sprites/jhoem/jhoemStand.png",
            "main/resources/sprites/jhoem/jhoemStandLeft.png",
            "main/resources/sprites/jhoem/jhoemStandRight.png",
            "main/resources/sprites/jhoem/jhoemWalk1.png",
            "main/resources/sprites/jhoem/jhoemWalk2.png",
            "main/resources/sprites/jhoem/jhoemWalkLeft1.png",
            "main/resources/sprites/jhoem/jhoemWalkRight1.png",
    };

    private static final String SPRITE_POWERUP = "main/resources/sprites/jhoem/jhoemTaking.png"; // Sprite que se muestra al agarrar powerup
    private boolean showingPowerUp; // Dice si esta mostrando el efecto de agarrar un powerup

    /**
     * Constructor de Jhoem
     * Carga el primer frame y posiciona al personaje
     */
    public Jhoem() {
        super(SPAWN_X, SPAWN_Y, uploadImage(FRAMES_WALK[0]), FRAMES_WALK.length);
        this.showingPowerUp = false;
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
        setX(getX() + calculateDx() * SPEED);
        setY(getY() + calculateDy() * SPEED);

        super.update();
    }

    // Animacion

    /**
     * Actualiza el sprite segun el frame actual o el estado de powerup
     */
    @Override
    public void updateSprite() {
        if (showingPowerUp) {
            setSprite(uploadImage(SPRITE_POWERUP));
        } else {
            int frame = getCurrentFrame() % FRAMES_WALK.length;
            setSprite(uploadImage(FRAMES_WALK[frame]));
        }
    }

    // Powerup

    /**
     * Otorga inmortalidad temporal y vida extra
     * muestra el sprite especial durante el efecto
     */
    @Override
    public void onPowerUpStart() {
        addLife();
        showingPowerUp = true;
        setSprite(uploadImage(SPRITE_POWERUP));
    }

    /**
     * al terminar el powerup regresa al sprite de caminar normal
     */
    @Override
    public void onPowerUpEnd() {
        showingPowerUp = false;
        updateSprite();
    }

    // Getters

    /**
     * velocidad de Jhoem en px por frame
     *
     * @return
     */
    public int getSpeed() {
        return SPEED;
    }

    /**
     * @return true si el efecto de agarrar powerup esta activo
     */
    public boolean isShowingPowerUp() {
        return showingPowerUp;
    }
}
