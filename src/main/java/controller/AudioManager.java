package controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioManager {

    private Clip choque;
    private Clip musica;
    private Clip win;
    private Clip comer;

    public AudioManager() {

        choque = cargarSonido("menu.wav");
        musica = cargarSonido("game.wav");
        win = cargarSonido("choque.wav");
        comer = cargarSonido("gameover.wav");
    }

    private Clip cargarSonido(String archivo) {

        try {

            InputStream is =
                    getClass().getResourceAsStream("/sounds/" + archivo);

            if (is == null) {
                System.out.println("No encontrado: " + archivo);
                return null;
            }

            BufferedInputStream bis =
                    new BufferedInputStream(is);

            AudioInputStream ais =
                    AudioSystem.getAudioInputStream(bis);

            Clip clip = AudioSystem.getClip();

            clip.open(ais);

            return clip;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public void reproducir(Clip clip) {

        if (clip == null) return;

        if (clip.isRunning()) {
            clip.stop();
        }

        clip.setFramePosition(0);
        clip.start();
    }

    public void reproducirLoop(Clip clip) {

        if (clip == null) return;

        clip.setFramePosition(0);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void detener(Clip clip) {

        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // GETTERS

    public Clip getChoque() {
        return choque;
    }

    public void setChoque(Clip choque) {
        this.choque = choque;
    }

    public Clip getMusica() {
        return musica;
    }

    public void setMusica(Clip musica) {
        this.musica = musica;
    }

    public Clip getWin() {
        return win;
    }

    public void setWin(Clip win) {
        this.win = win;
    }

    public Clip getComer() {
        return comer;
    }

    public void setComer(Clip comer) {
        this.comer = comer;
    }
}

