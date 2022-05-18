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
    Popolon heroe;
    Escenario nivel;
    Camara cam;
    static int numeroNivel = 1;
    public int cantidadVidas;

    enum gameStatus{
        MENU_PRINCIPAL,
        LOOP,
        PAUSA,
        CARGANDO,
        GAME_OVER    
    }
    gameStatus estadoJuego;
    //Rectangle HUD=new Rectangle(0,5900,800,400);

    public Knightmare() {
        super("Juego", 800, 800);
        
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

        // g.setColor(Color.BLUE);
        // g.fill(HUD);
        // g.draw(HUD);
        // g.setColor(Color.BLUE);
        // g.setColor(Color.RED);
        //g.translate(-cam.getX(), -cam.getY());

        
    }

    // private void updateHUD(){
    //     HUD.y=Escenario.get_nivel().limites.y+600;
    // }

    @Override
    public void gameShutdown() {

    }

    @Override
    public void gameStartup() {
        cantidadVidas=3;
        nivel = Escenario.get_nivel();
        heroe = new Popolon("imagenes/popolon0.png");
        heroe.setPosition(375, 5820);
        cam = new Camara(0, 0);
        cam.setRegionVisible(800, 600);
        estadoJuego=gameStatus.LOOP;
        

    }

    @Override
    public void gameUpdate(double delta) {
        // updateHUD();

        Keyboard keyboard = this.getKeyboard();

        if(nivel.colisionObstaculo(heroe.hitbox)){
            heroe.setY(heroe.getY()+1);
        }

        if(heroe.getY()==5915){
            heroe.cambiar(Popolon.estados.MURIENDO);
            System.out.println("GameOver");
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

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)){
            heroe.down(delta);
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)){
            heroe.up(delta);
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)){
            heroe.left(delta);
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            heroe.right(delta);
        }


        heroe.update(delta);
        //cam.seguirPersonaje(heroe);

        cam.seguirLimite();
        

    }

    public static int nivel() {
        return numeroNivel;
    }

}