
/**
Compilar
javac -cp ".;bucleJuego.jar" Knightmare.java 

Ejecutar
java -cp ".;bucleJuego.jar" Knightmare
  */

import com.entropyinteractive.*;
import java.awt.*;
import java.awt.event.*; //eventos

import java.awt.Graphics2D;

public class Knightmare extends JGame {

    final double POPOLON = 250.0;
    Popolon heroe;
    Escenario nivel;
    Camara cam;
    static int numeroNivel = 1;

    public Knightmare() {
        super("Juego", 800, 600);
    }

    public static void main(String args[]) {
        Knightmare game = new Knightmare();
        game.run(1.0 / 60.0);
        System.exit(0);

    }

    @Override
    public void gameDraw(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.translate(cam.getX(), cam.getY());
        nivel.display(g);
        heroe.display(g);
        g.translate(-cam.getX(), -cam.getY());

    }

    @Override
    public void gameShutdown() {

    }

    @Override
    public void gameStartup() {
        nivel = new PrimerNivel();
        heroe = new Popolon("imagenes/popolon0.png");
        heroe.setPosition(375, 5820);
        cam = new Camara(0, 0);
        cam.setRegionVisible(800, 600);

    }

    @Override
    public void gameUpdate(double delta) {

        // System.out.println(heroe.hitbox);

        Keyboard keyboard = this.getKeyboard();

        if (keyboard.isKeyPressed(KeyEvent.VK_L)) {
            heroe.cambiar(Popolon.estados.ROJO);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_K)) {
            heroe.cambiar(Popolon.estados.VIVO);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_J)) {
            System.out.println("Hitbox heroe:" + heroe.hitbox);
        }

        // if(heroe.hitbox.intersects(Escenario.NIVEL.getLimites()))
        // {
        // System.out.println("Intersectado");
        // }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            System.out.println(heroe.getY());
            heroe.setY(heroe.getY() + POPOLON * delta);

        }
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            System.out.println(heroe.getY());

            heroe.setY(heroe.getY() - POPOLON * delta);

        }
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            System.out.println(heroe.getX());

            heroe.setX(heroe.getX() - POPOLON * delta);
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            System.out.println(heroe.getX());

            heroe.setX(heroe.getX() + POPOLON * delta);

        }

        heroe.update(delta);
        // Escenario.get_nivel().updateFondo();
        cam.seguirPersonaje(heroe);

    }

    public static int nivel() {
        return numeroNivel;
    }

}