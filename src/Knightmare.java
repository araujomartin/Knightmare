
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
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.awt.Graphics2D;

public class Knightmare extends JGame {

   
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");
    public static Knightmare juego;
    public static boolean boss=false;
    Popolon heroe = new Popolon("imagenes/popolon0.png");
    Escenario nivel;
    
    //Sonido reproducir;
    Rectangle hud;
    Rectangle bonus;
    private final ObjetoGrafico logo = new ObjetoGrafico("imagenes/logo.png");
    static int numeroNivel = 1;
    public static int cantidadVidas = 3;
    public static int score = 0;
    public int hiScore = 0;
    private Font font;
    private double timer = 0;
    private double timerBonus=0;

    gameStatus estadoJuego;

    enum gameStatus {
        MENU_PRINCIPAL,
        LOOP,
        PAUSA,
        CARGANDO,
        GAME_OVER,
        BONUS,
    }

    // Rectangle HUD=new Rectangle(0,5900,800,400);

    public Knightmare() {
        super("Juego", 800, 600);
        juego=this;
        FXPlayer.init();

    }

    public static void main(String args[]) {
        Knightmare game = new Knightmare();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    @Override
    public void gameStartup() {

        //reproducir = new Sonido();
        logo.setPosition(300, 250);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imagenes/Nintendo-NES-Font.ttf"))
                    .deriveFont(15f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (IOException e) {
            System.out.println(e);
        } catch (FontFormatException e) {
            System.out.println(e);
        }
        estadoJuego = gameStatus.MENU_PRINCIPAL;
        hud = new Rectangle(0, 500, 850, 500);
        bonus= new Rectangle(650,95,67,34);

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
    public void gameDraw(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (estadoJuego == gameStatus.MENU_PRINCIPAL) {
            g.setFont(font);
            logo.display(g);
            drawStyledString(g, "KNIGHTMARE", 400, 200, true);
            drawStyledString(g, "© MARTIN ARAUJO SOFTWARE 2022", 400, 400, true);
            if (timer % 1 < 0.5) {
                drawStyledString(g, "PUSH SPACE", 405, 450, true);
            }
            return;
        }

        if (estadoJuego == gameStatus.CARGANDO) {
            g.setFont(font);
            drawStyledString(g, "STAGE "+Integer.toString(numeroNivel), 400, 200, true);
            g.setColor(Color.BLACK);
            g.fill(hud);
            updateHud(g);

            return;
        }

        if (estadoJuego == gameStatus.LOOP) {

            // g.translate(cam.getX(), cam.getY());
            nivel.display(g);
            heroe.display(g);
            // g.translate(-cam.getX(), -cam.getY());
            g.setColor(Color.BLACK);
            g.draw(hud);
            g.fill(hud);
            updateHud(g);

            return;
        }

        if (estadoJuego == gameStatus.BONUS){
            nivel.display(g);
            heroe.display(g);
            g.setColor(Color.BLACK);
            g.draw(hud);
            g.draw(bonus);
            g.fill(bonus);
            g.fill(hud);
            updateBonus(g);
            updateHud(g);
            return;
        }
    }

    public void updateBonus(Graphics2D g){
        g.setFont(font.deriveFont(20f));
        drawStyledString(g, Integer.toString((int)timerBonus),671,120,false);
    }

    public void updateHud(Graphics2D g) {

        g.setFont(font.deriveFont(20f));
        drawStyledString(g, "SCORE", 100, 530, false);
        drawStyledString(g, Integer.toString(score), 90, 570, false);
        drawStyledString(g, "HISCORE", 255, 530, false);
        drawStyledString(g, Integer.toString(hiScore), 245, 570, false);
        drawStyledString(g, "REST", 455, 530, false);
        drawStyledString(g, Integer.toString(cantidadVidas), 515, 570, false);
        drawStyledString(g, "STAGE", 600, 530, false);
        drawStyledString(g, Integer.toString(numeroNivel), 670, 570, false);
        g.setColor(Color.BLACK);

    }

    @Override
    public void gameShutdown() {
    }

    @Override
    public void gameUpdate(double delta) {

        Keyboard keyboard = this.getKeyboard();
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();

        if (estadoJuego == gameStatus.MENU_PRINCIPAL) {
            timer += delta;
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    stop();
                }

                if (event.getID() == KeyEvent.KEY_PRESSED) {
                    try {
                        //reproducir.play("sonidos/intro.wav");
                        FXPlayer.INTRO.play(-20.0f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ;
                    timer = 0;
                    estadoJuego = gameStatus.CARGANDO;
                    break;
                }
            }
        }

        if (estadoJuego == gameStatus.CARGANDO) {
            timer += delta;
            nivel = Escenario.get_nivel();
            heroe.setPosition(382, 373);
            if (timer > 8) {
                estadoJuego = gameStatus.LOOP;
                try {
                    //reproducir.looping("sonidos/stage" + numeroNivel + ".wav");
                    FXPlayer.STAGE1.loop(-20.0f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ;
                timer=0;
            }

        }

        if (estadoJuego == gameStatus.LOOP || estadoJuego==gameStatus.BONUS) {

            if(estadoJuego==gameStatus.BONUS){
                if(timerBonus==30){
                    try {
                        FXPlayer.STAGE1.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if(timerBonus<0){
                    estadoJuego= gameStatus.LOOP;
                    FXPlayer.STAGE1.loop(-20.0f);
                    nivel.stop=false;
                }
                timerBonus-=delta;

                

            }
            nivel.update(delta);

            if (heroe.getEstado() == Popolon.estados.MURIENDO) {
                if(timer==0){
                    try {
                        FXPlayer.STAGE1.stop();
                        FXPlayer.BOSS1.stop();
                        FXPlayer.MUERTE.play(-20.0f);
                        nivel.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        
                if (timer > 4) {
                this.estadoJuego = gameStatus.CARGANDO;
                heroe.cambiar(Popolon.estados.VIVO);
                cantidadVidas--;
                nivel.reLoadStaticObjetcs();
                boss=false;
                
                try {
                    //reproducir.play("sonidos/intro.wav");
                    FXPlayer.INTRO.play(-20.0f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ;
                timer=0;
                }
                timer += delta;
                heroe.update(delta);
                
                return;
            }

            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_RELEASED) && (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                    heroe.disparar();
                }

                
            }

            if(keyboard.isKeyPressed(KeyEvent.VK_T)){
                heroe.cambiar(Popolon.estados.MURIENDO);
            }

            if (nivel.colisionObstaculo(heroe.hitbox)) {
                heroe.setY(heroe.getY() + 1);
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_L)) {
                heroe.cambiar(Popolon.estados.ROJO);
                score = 0;
                hiScore = 0;
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_K)) {
                heroe.cambiar(Popolon.estados.VIVO);
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_J)) {
                System.out.println("Hitbox heroe:" + heroe.hitbox);
                score = score + 10000;
                hiScore = hiScore + 1000;
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

        
            

            // cam.seguirPersonaje(heroe);

            // cam.seguirLimite();
        }

    }

    public static int nivel() {
        return numeroNivel;
    }

    public static void sumarScore(int puntos){
        score=score+puntos;
    }

    public static void bossModeMusic(){

        if(boss){
            FXPlayer.STAGE1.stop();
            FXPlayer.BOSS1.loop(-20.0f);
        }
        
    }

    protected void bonus(){
        this.estadoJuego=gameStatus.BONUS;
        timerBonus=30;
    }

    public static void toggleBoss(){
        boss=!boss;
    }

    public static void sumarVida(){
        cantidadVidas++;
    }

    

}