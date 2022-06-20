/**
Compilar
javac -cp ".;bucleJuego.jar" Knightmare.java 

Ejecutar
java -cp ".;bucleJuego.jar" Knightmare
  */

import com.entropyinteractive.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Graphics2D;

public class Knightmare extends JGame {
    
    private final ObjetoGrafico logo = new ObjetoGrafico("imagenes/logo.png");
    private Popolon heroe;
    private Escenario nivel;
    private Rectangle hud;
    private Rectangle bonus;
    private Ranking rank;
    private Font font;
    private gameStatus estadoJuego;
    private int up,down,left,right;
    private int hiScore = 0;
    private boolean musicaGeneral;
    private double timer = 0;
    private double timerBonus=0;
    private double timerPower=0;
    private static int score = 0;
    private static int numeroNivel = 1;
    private static int cantidadVidas = 2;

    public static boolean boss=false;
    public static Knightmare juego;
    public final String PERSONAJE = appProperties.getProperty("personaje");
    public final String MUSICAFONDO=appProperties.getProperty("pista")+".wav";
    
    public enum gameStatus {
        MENU_PRINCIPAL,
        LOOP,
        PAUSA,
        CARGANDO,
        GAME_OVER,
        BONUS,
        POWERUP,
        SIGUIENTE,
        WIN,
    }

    public Knightmare() {
        super("Juego", 800, 600);
        juego=this;
    }

    public static void main(String args[]) {
        Knightmare game = new Knightmare();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    @Override
    public void gameStartup() {

        switch(appProperties.getProperty("sonidoGeneral")){
            case "false":
            FXPlayer.volume=FXPlayer.Volume.MUTE;
            musicaGeneral=false;
            break;
            case "true":
            FXPlayer.volume=FXPlayer.Volume.LOW;
            musicaGeneral=true;
            break;
        }

        FXPlayer.init();

        this.heroe = new Popolon("imagenes/"+PERSONAJE+"0.png"); // CREO MI HEROE CON IMAGEN DEL PROPERTIES
        logo.setPosition(300, 250);

        rank=new Ranking(); // CREO MI RANKING, LEE UN ARCHIVO DE TEXTO LO ORDENA
        this.hiScore=rank.getTop(); //SE QUEDA CON EL VALOR MAS ALTO

        // CARGO FUENTE DE NINTENDO
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
        hud = new Rectangle(0, 500, 850, 500); // HUD PRINCIPAL
        bonus= new Rectangle(650,95,67,34); // HUD TIMER BONUS/POWERUP
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

        if (estadoJuego == gameStatus.GAME_OVER) {

            g.setFont(font);
            drawStyledString(g, "GAME OVER", 400, 200, true);
            g.setColor(Color.BLACK);
            g.fill(hud);
            updateHud(g);

            return;
        }
        
        if (estadoJuego == gameStatus.LOOP || estadoJuego == gameStatus.PAUSA || estadoJuego == gameStatus.SIGUIENTE) {

            nivel.display(g);
            heroe.display(g);
            g.setColor(Color.BLACK);
            g.draw(hud);
            g.fill(hud);
            updateHud(g);

            return;
        }

        if (estadoJuego == gameStatus.BONUS || estadoJuego == gameStatus.POWERUP){

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

        if (estadoJuego == gameStatus.WIN){

            g.setFont(font);
            drawStyledString(g, "APROBADO", 400, 200, true);
            g.setColor(Color.BLACK);
            g.draw(hud);
            g.fill(hud);
            updateHud(g);
            return;
        }

    }

    private void updateBonus(Graphics2D g){

        g.setFont(font.deriveFont(20f));

        if(estadoJuego == gameStatus.BONUS){
            drawStyledString(g, Integer.toString((int)timerBonus),671,120,false);
        }
        else if(estadoJuego == gameStatus.POWERUP){
            drawStyledString(g, Integer.toString((int)timerPower),671,120,false);  
        }
        
    }

    private void updateHud(Graphics2D g) {

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

        if(score>=hiScore){
            hiScore=score;
        }

        if (estadoJuego == gameStatus.MENU_PRINCIPAL) {
            timer += delta;
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    stop();
                }

                if (event.getID() == KeyEvent.KEY_PRESSED) {
                    try {
                        FXPlayer.INTRO.play(-20.0f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ;
                    timer = 0;
                    estadoJuego = gameStatus.CARGANDO;
                    numeroNivel=1;
                    break;
                }
            }
        }

        if(estadoJuego == gameStatus.SIGUIENTE){
            if(heroe.animacion()){
                heroe.update(delta);
            }else{
                if(numeroNivel==2){
                    estadoJuego = gameStatus.WIN;
                    return;
                }
                Escenario.clear();
                numeroNivel++;
                estadoJuego= gameStatus.CARGANDO;
                timer=0;
            }
        }

        if (estadoJuego == gameStatus.CARGANDO) {
            timer += delta;
            nivel = Escenario.get_nivel();
            heroe.setPosition(382, 373);
            if (timer > 8) {
                estadoJuego = gameStatus.LOOP;
                try {
                    // FXPlayer.STAGE1.loop(-20.0f);
                    reproducirMusica();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ;
                timer=0;
            }

        }

        if (estadoJuego == gameStatus.WIN) {
            timer += delta;
            if (timer > 11) {
                estadoJuego = gameStatus.MENU_PRINCIPAL;
                cantidadVidas=2;
                Escenario.clear();
                timer=0;
                rank.actualizar(score);
                score=0;
                rank=new Ranking();
                hiScore=rank.getTop();
            }

        }

        if (estadoJuego == gameStatus.GAME_OVER) {
            timer += delta;
            if (timer > 11) {
                estadoJuego = gameStatus.MENU_PRINCIPAL;
                cantidadVidas=2;
                timer=0;
            }

        }

        if (estadoJuego == gameStatus.PAUSA){
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_RELEASED) && (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("pausa")))) {
                    this.estadoJuego = gameStatus.LOOP;
                    // FXPlayer.STAGE1.loop(-20.0f);
                    reproducirMusica();
                    FXPlayer.PAUSA.play(-5.0f);
                    keyEvents.clear();
                }     
            }

        }

        if (estadoJuego == gameStatus.LOOP || estadoJuego==gameStatus.BONUS || estadoJuego == gameStatus.POWERUP) {


            
                if((estadoJuego==gameStatus.BONUS) && !(heroe.getEstado() == Popolon.estados.MURIENDO)){
                    if(timerBonus==20){
                        try { 
                            // FXPlayer.STAGE1.stop();
                            stopMusica();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if(timerBonus<0){
                        estadoJuego = gameStatus.LOOP;
                        // FXPlayer.STAGE1.loop(-20.0f);
                        reproducirMusica();
                        nivel.stop=false;
                    }
                    timerBonus-=delta*3;                
    
                } 
                
                if((estadoJuego == gameStatus.POWERUP) && !(heroe.getEstado() == Popolon.estados.MURIENDO)){
                   
                    if(timerPower<10){
                        FXPlayer.POWEREND.play2(-20.0f);
                        heroe.finalizaPower();
                    }
                    
                    if(timerPower<0){
                    FXPlayer.POWEREND.stop();
                    estadoJuego = gameStatus.LOOP;
                    heroe.cambiar(Popolon.estados.VIVO);
                    }
                    
                    timerPower-=delta*3;     
                }
            
            
            nivel.update(delta);

            if (heroe.getEstado() == Popolon.estados.MURIENDO) {
                if(timer==0){
                    try {
                        // FXPlayer.STAGE1.stop();
                        stopMusica();
                        if(boss){
                        FXPlayer.BOSS1.stop();
                        }
                        FXPlayer.MUERTE.play(-20.0f);
                        nivel.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        
                if (timer > 4) {
                cantidadVidas--;
                boss=false;
                heroe.restaurar();
                if(cantidadVidas<0){
                    cantidadVidas=0;
                    this.estadoJuego = gameStatus.GAME_OVER;
                    try {
                        FXPlayer.GAME_OVER.play(-20.0f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    rank.actualizar(score);
                    score=0;
                    rank=new Ranking();
                    hiScore=rank.getTop();
                    Escenario.clear();
                }
                else{
                    this.estadoJuego = gameStatus.CARGANDO;
                    nivel.reCargarObjetos();
                    rank.actualizar(score);
                    try {
                        FXPlayer.INTRO.play(-20.0f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ;
                    timer=0;
                    }
                }    

                timer += delta;
                heroe.update(delta);
                
                return;
            }

            for (KeyEvent event : keyEvents) {

                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    stop();
                }

                if ((event.getID() == KeyEvent.KEY_RELEASED) && (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("disparo")))) {
                    heroe.disparar();
                }

                if ((event.getID() == KeyEvent.KEY_RELEASED) && (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("pausa")))) {
                    this.estadoJuego = gameStatus.PAUSA;
                    // FXPlayer.STAGE1.stop();
                    stopMusica();
                    FXPlayer.PAUSA.play(-5.0f);
                }

                if ((event.getID() == KeyEvent.KEY_RELEASED) && (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("toggle")))) {
                    if(musicaGeneral){
                        stopMusica();
                        musicaGeneral=false;
                    }else{
                        reproducirMusica();
                        musicaGeneral=true;
                    }     
                }

                if (keyboard.isKeyPressed(KeyEvent.VK_K)) {
                    heroe.cambiar(Popolon.estados.MURIENDO);
                }

                if ((KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("abajo")))){
                    this.down=event.getKeyCode();
                }

                if ((KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("arriba")))){
                    this.up=event.getKeyCode();
                }
        
                if ((KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("izquierda")))){
                    this.left=event.getKeyCode();
                }

                if ((KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("derecha")))){
                    this.right=event.getKeyCode();
                }
                      
            }

            if (nivel.colisionObstaculo(heroe.hitbox)) {
                heroe.setY(heroe.getY() + 1);
            }
            
            if (keyboard.isKeyPressed(down)) {
                heroe.down(delta);
            }
            if (keyboard.isKeyPressed(up)) {
                heroe.up(delta);
            }
            if (keyboard.isKeyPressed(left)) {
                heroe.left(delta);
            }
            if (keyboard.isKeyPressed(right)) {
                heroe.right(delta);
            }

            heroe.update(delta);

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
            stopMusica();
            FXPlayer.BOSS1.loop(-20.0f);
        }
        
    }

    protected  void bonus(){
        this.estadoJuego=gameStatus.BONUS;
        timerBonus=20;
    }

    protected void powerup(){
        this.estadoJuego=gameStatus.POWERUP;
        timerPower=35;
    }

    public static void toggleBoss(){
        boss=true;
    }

    public static void sumarVida(){
        cantidadVidas++;
    }

    public void siguienteNivel(){
        stopMusica();
        FXPlayer.BOSS1.stop();
        if(numeroNivel==2){
            this.estadoJuego = gameStatus.SIGUIENTE;
            this.heroe.setAnimacion();
            FXPlayer.WINGAME.play(-10f);
        }
        else{
            this.estadoJuego = gameStatus.SIGUIENTE;
            this.heroe.setAnimacion();
        }

    }

    private static void reproducirMusica(){
        switch(numeroNivel){
            case 1:{
                FXPlayer.MUSICA.loop(-20.0f);
            }
            break;
            default:
                FXPlayer.MUSICA.loop(-20.0f);
            break;
        }
    }

    private static void stopMusica(){
        switch(numeroNivel){
            case 1:{
                FXPlayer.MUSICA.stop();
            }
            break;
            default:
            FXPlayer.MUSICA.stop();
            break;
        }
    }

}