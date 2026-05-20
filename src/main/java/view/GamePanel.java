package view;

import model.Arbol;
import model.Bus;
import model.Moto;
import model.Taxi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private List<Arbol> arboles;
    private List<Bus> buses;
    private List<Moto> motos;
    private List<Taxi> taxis;

    public GamePanel() {
        //Arboles, son estaticos
        arboles = new ArrayList<>();
        arboles.add(new Arbol(100, 355));
        arboles.add(new Arbol(300, 355));
        arboles.add(new Arbol(500, 355));
        arboles.add(new Arbol(700, 355));

        // Buses
        buses = new ArrayList<>();
        buses.add(new Bus(0, 65, 3));
        buses.add(new Bus(400, 65, 3));
        buses.add(new Bus(0, 155, -3)); // negativo = va a la izquierda

        // Motos
        motos = new ArrayList<>();
        motos.add(new Moto(0, 245, 5));
        motos.add(new Moto(300, 245, 5));

        // Taxis
        taxis = new ArrayList<>();
        taxis.add(new Taxi(0, 455, -4));
        taxis.add(new Taxi(500, 545, 4));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        dibujarAnden(g2, 0);
        dibujarCarril(g2, 60);
        dibujarCarril(g2, 150);
        dibujarCarril(g2, 240);

        dibujarAnden(g2, 710);
        dibujarCarril(g2, 450);
        dibujarCarril(g2, 540);
        dibujarCarril(g2, 630);

        dibujarCiclovia(g2, 325);
        dibujarCiclovia(g2, 415);

        g.setColor(new Color(83, 147, 49));
        g.fillRect(0, 355, 1000, 60);
        for (Arbol a : arboles) {
            g2.drawImage(a.getSprite(), a.getX(), a.getY(), 50, 50, null);
        }


    }

    private Color carriles = new Color(47, 47, 51);

    public void dibujarCarril(Graphics2D g, int y) {
        g.setColor(carriles);
        g.fillRect(0, y, 1000, 80);
    }

    public void dibujarAnden(Graphics2D g, int y) {
        g.setColor(new Color(78, 77, 91));
        g.fillRect(0, y, 1000, 60);
    }

    public void dibujarCiclovia(Graphics2D g, int y) {
        g.setColor(carriles);
        g.fillRect(0, y, 1000, 30);
    }
}
