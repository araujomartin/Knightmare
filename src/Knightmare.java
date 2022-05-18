
/**
Compilar
javac -cp ".;bucleJuego.jar" Knightmare.java 

Ejecutar
java -cp ".;bucleJuego.jar" Knightmare
  */

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Graphics2D;

public class Knightmare extends JGame {
    Popolon heroe;
    Escenario nivel;
    Camara cam;
    ObjetoGrafico logo;
    static int numeroNivel = 1;
    public int cantidadVidas;
    public int score=0;
    private Font font;
    private double timerEstado = 0;

    enum gameStatus {
        MENU_PRINCIPAL,
        LOOP,
        PAUSA,
        CARGANDO,
        GAME_OVER
    }

    gameStatus estadoJuego;
    // Rectangle HUD=new Rectangle(0,5900,800,400);

    public Knightmare() {
        super("Juego", 800, 600);
        logo = new ObjetoGrafico("imagenes/logo.png");

    }

    public static void main(String args[]) {
        Knightmare game = new Knightmare();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    @Override
    public void gameDraw(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (estadoJuego == gameStatus.MENU_PRINCIPAL) {
            g.scale(2.0, 2.0);
            g.setFont(font);
            logo.display(g);
            drawStyledString(g, "KNIGHTMARE", 205, 90, true);
            drawStyledString(g, "© MARTIN ARAUJO 2022", 200, 200, true);
            if (timerEstado % 1 < 0.5)
            drawStyledString(g, "PUSH SPACE", 200, 220, true);
            
        }

        if (estadoJuego == gameStatus.CARGANDO) {
            g.scale(2.0, 2.0);
            g.setFont(font);
            drawStyledString(g, "VIDAS: "+Integer.toString(cantidadVidas), 100, 100, true);
            g.setColor(Color.BLACK);
        }

        if(estadoJuego == gameStatus.LOOP)
        {
        g.translate(cam.getX(), cam.getY());
        nivel.display(g);
        heroe.display(g);
        g.translate(-cam.getX(), -cam.getY());

        

        }
    }

    @Override
    public void gameShutdown() {

    }

    @Override
    public void gameStartup() {

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imagenes/Nintendo-NES-Font.ttf"))
                    .deriveFont(8f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (IOException e) {
            System.out.println(e);
        } catch (FontFormatException e) {
            System.out.println(e);
        }

        estadoJuego = gameStatus.MENU_PRINCIPAL;
        cam = new Camara(0, 0);
        cam.setRegionVisible(800, 600);
        cantidadVidas = 3;
        nivel = Escenario.get_nivel();
        heroe = new Popolon("imagenes/popolon0.png");
        heroe.setPosition(375, 5820);
        // estadoJuego = gameStatus.LOOP;
    }

    private void drawStyledString(Graphics2D g2, String str, int x, int y, boolean center) {
        int offset = 0;
        if (center) {
            FontMetrics metrics = g2.getFontMetrics(font);
            offset = -metrics.stringWidth(str) / 2 + 4;
        }
        g2.setColor(Color.black);
        g2.drawString(str, x + offset, y + 1);
        g2.drawString(str, x - 1 + offset, y + 1);
        g2.setColor(Color.white);
        g2.drawString(str, x + offset, y);
    }

    @Override
    public void gameUpdate(double delta) {

        Keyboard keyboard = this.getKeyboard();
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();

        if (estadoJuego == gameStatus.MENU_PRINCIPAL) {
            timerEstado += delta;

            if (timerEstado <= 3) {
                logo.setPosition(100, 100);
            } else {
                for (KeyEvent event : keyEvents) {
                    if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                        stop();
                    }

                    if (event.getID() == KeyEvent.KEY_PRESSED) {
                        try {
                            // reproducir.Stop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ;
                        estadoJuego=gameStatus.CARGANDO;
                        break;
                    }
                }
            }
        }

        if (estadoJuego == gameStatus.LOOP) {

            if (nivel.colisionObstaculo(heroe.hitbox)) {
                heroe.setY(heroe.getY() + 1);
            }

            if (heroe.getY() == 5915) {
                heroe.cambiar(Popolon.estados.MURIENDO);
                System.out.println("GameOver");
                // nivel.stop();
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_L)) {
                heroe.cambiar(Popolon.estados.ROJO);
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_K)) {
                heroe.cambiar(Popolon.estados.VIVO);
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_J)) {
                System.out.println("Hitbox heroe:" + heroe.hitbox);
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
                heroe.down(delta);
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
                heroe.up(delta);
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                heroe.left(delta);
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                heroe.right(delta);
            }
            
            heroe.update(delta);
            cam.seguirPersonaje(heroe);

            cam.seguirLimite();
        }

    }

    public static int nivel() {
        return numeroNivel;
    }

}