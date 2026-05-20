package main.java.model;

import java.awt.image.BufferedImage;

/**
 * Clase abstracta que representa el jugador en el juego
 * Extiende de Entity y agrega la logica de estado del jugador:
 * vidas, puntos, vida, powerups y posicion de spawn
 * <p>
 * Las subclases Diego, Jhoem y Pablo definen los sprites y estadisticas propios de cada personaje
 * Aplica herencia, encapsulamiento y polimorfismo
 *
 * @author Diego, Pablo, Jhoem
 * @version 1.0
 */
public abstract class Jugador extends model.Entity {

    // Contantes globales
    public static final int INITIAL_LIVES = 3; // Numero inicial de vidas
    public static final int MAX_HEALTH = 100; // Vida maxima del jugador en %
    public static final int DAMAGE_VEHICLE = 100; // Daño recibido al chocar con un vehiculo enemigo
    public static final int POINTS_POWERUP = 50; // Puntos ganados al recoger un poder
    public static final int POWERUP_DURATION = 180; // Duracion del powerup en frames (3 segundos a 60FPS)

    // ESTADO DEL JUGADOR
    private String nombreJugador; // Nombre ingresado por el jugador en la interfaz
    private int lives; // Numero de vidas restantes
    private int score; // Puntuacion acumulada
    private int health;
    private boolean immortality; // Indica si el jugador esta en estado inmortal despues de recibir daño
    private int immortalityCount; // Contador de frames de la inmortalidad (parpadeo tras daño)
    private static final int IMMORTALITY_DURATION = 120; // Duracion de la inmortalidad tras recibir daño (2 seg por 60fps)
    private boolean powerUpActive; // Indica si hay un powerup activo
    private int powerUpCount; // Contador de frames que quedan del powerup
    private boolean paused; // indica si el juego esta en pausa o no

    // ANIMACION
    // pendiente...


    // POSICION DE SPAWN
    // Se da el valor a los final en el constructor
    private final int spawnX; // Coordenada X del punto de aparicion del personaje
    private final int spawnY; // Coordenada Y del punto de aparicion del personaje

    /**
     * Constructor de jugador
     *
     * @param x      posicion X inicial
     * @param y      posicion Y inicial
     * @param sprite imagen inicial del personaje
     */
    public Jugador(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
        this.spawnX = x;
        this.spawnY = y;
        this.lives = lives;
        this.score = score;
        this.health = health;
        this.immortality = immortality;
        this.immortalityCount = immortalityCount;
        this.powerUpActive = powerUpActive;
        this.powerUpCount = powerUpCount;
        this.paused = paused;
    }

    // logica

    /**
     * Actualiza los contadores de inmortalidad y powerup en cada frame
     * Las subclases deben llamar a update() para mantener dicha logica
     */
    @Override
    public void update() {
        // Descontar inmortalidad
        if (immortality) {
            immortalityCount--;
            if (immortalityCount <= 0) {
                immortality = false;
            }
        }

        // Descontar powerup
        if (powerUpActive) {
            powerUpCount--;
            if (powerUpCount <= 0) {
                powerUpActive = false;
            }
        }

        // Pendiente: Animacion
    }

    /**
     * Aplica daño al jugador, si esta inmortal el daño se ignora
     * Si la vida llega a 0 descuenta una vida y restaura la vida al 100%
     * Si no quedan vidas la entidad se desactiva
     *
     * @param ammount cantidad de daño a aplicar (valor positivo)
     */
    public void receiveDamage(int ammount) {
        if (immortality) return;

        health -= ammount;
        if (health <= 0) {
            health = 0;
            lives--;
            if (lives > 0) {
                resetPosition();
                health = MAX_HEALTH;
            } else {
                setActive(false);
            }
        }

        // Activar inmortalidad temporal tras recibir daño
        immortality = true;
        immortalityCount = IMMORTALITY_DURATION;
    }

    /**
     * Suma puntos a la puntuacion acumulada
     *
     * @param points puntos a agregar (positivo)
     */
    public void addScore(int points) {
        if (points > 0) {
            score += points;
        }
    }

    // Agrega una vida extra al jugador
    public void addLife() {
        lives++;
    }

    /**
     * Activa el powerup del personaje
     * Suma puntos, activa el estado y llama a onPowerUpStart()
     */
    public void activatePowerUp() {
        powerUpActive = true;
        powerUpCount = POWERUP_DURATION;
        addScore(POINTS_POWERUP);
        onPowerUpStart();
    }

    /**
     * Reinicia al jugador a su posicion de spawn sin modificar vidas ni puntaje
     * Sirve al reiniciar tras un choque con vidas restantes
     */
    public void resetPosition() {
        setX(spawnX);
        setY(spawnY);
        setDirection(DIR_NONE);
        setActive(true);


    }

    /**
     * Reinicia completamente al jugador
     * posicion, vidas, puntaje, vida y powerup
     * Se llama al iniciar una nueva partida
     */
    public void resetAll() {
        resetPosition();
        lives = INITIAL_LIVES;
        score = 0;
        health = MAX_HEALTH;
        immortality = false;
        immortalityCount = 0;
        powerUpActive = false;
        powerUpCount = 0;
    }

    // Cambia el estado de pausa
    public void togglePause() {
        paused = !paused;
    }

    // METODOS ABSTRACTOS QUE VAN A USAR LOS PERSONAJES DIEGO, PABLO o JHOEM

    /**
     * Actualiza el sprite del personaje segun el frame y direccion actual
     * Cada subclase define sus propios archivos de imagen
     */
    public abstract void updateSprite();

    /**
     * Logica que se ejecuta cuando el powerup se activa
     * Puede cambiar sprite, velocidad, etc, segun el personaje
     */
    public abstract void onPowerUpStart();

    /**
     * Logica que se ejecuta cuando el powerup termina
     * Restaura el estado normal del personaje
     */
    public abstract void onPowerUpEnd();


    // GETTERS Y SETTERS

    // retorna el nombre ingresado por el jugador
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Retorna vida actual 0-100
    public int getHealth() {
        return health;
    }

    // Nueva vida (cuando reciba daño)
    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isImmortality() {
        return immortality;
    }

    public boolean isPowerUpActive() {
        return powerUpActive;
    }

    // frames restantes del powerup activo
    public int getPowerUpCount() {
        return powerUpCount;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * @return coordenada X del punto de spawn
     */
    public int getSpawnX() {
        return spawnX;
    }

    /**
     * @return coordenada Y del punto de spawn
     */
    public int getSpawnY() {
        return spawnY;
    }
}
