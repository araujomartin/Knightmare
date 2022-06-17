import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class Medusa extends Enemigo {

    private int vida;
    private double timerMovimientoX=0;

    public Medusa(String filename, int x, int y) {
        super(filename);
        this.hitbox = new Rectangle((int) x+10, (int) y, 110, 90);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.canShot=true;
        this.vida=15;
        this.puntosAlMorir=500;
        this.isVisible=true;
        if(canShot){
            this.arma=new Arma(Arma.tipoMunicion.RAYO, 0, 0);
        }
  
    }

    @Override
    public void update(double delta) {

        if(this.positionY>98){
            this.estado= estadoEnemigo.VIVO;
        }

        if(estado == estadoEnemigo.DESACTIVADO){
            this.positionY++;
            return;
        }

        
        if(this.isVisible==false){
            Knightmare.juego.siguienteNivel();
            Escenario.get_nivel().clearAll();
            return;
        }

        if(this.isHited){
            vida--;
            this.isHited=false;
            FXPlayer.BOSSHIT.play(-5.0f);
        }

        if(vida<0){
            this.estado = estadoEnemigo.MUERTO;
        }

        if (estado == estadoEnemigo.VIVO) {
            if(timerMovimientoX<2){
                if(Popolon.popolon.getX()>(int)this.positionX && (int)this.positionX<550){
                    positionX++;
                }
                else{
                    positionX--;
                }
            timerMovimientoX=0;
            }
            timerMovimientoX+=delta;

            this.updateHitBox();
            if(canShot){
                this.updateArma((int)this.positionX+50, (int)this.positionY);
                if(timerDisparo>2){
                    this.disparar();
                    timerDisparo=0;     
                }
                else{
                    timerDisparo+=delta;
                }
                
            }
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 2)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 2;

                    imagen = ImageIO.read(getClass().getResource("imagenes/medusa" + spritePosition + ".png"));
                    spriteCounter = 0;
                }

            } catch (IOException e) {
                System.out.println(e);
            }

            return;
        }

        if (estado == estadoEnemigo.MUERTO) {
            try {

                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 2)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 2;

                    imagen = ImageIO.read(getClass().getResource("imagenes/fuegoJefe" + spritePosition + ".png"));
                    spriteCounter = 0;
                    if (isVisible == true) {
                        this.morir(delta);
                    }
                    
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;
        }

    }

    public void morir(double delta){

        if(timer==0){
            try {
                FXPlayer.BOSSMUERTE.play(-5.0f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (timer > 3) {
        timer=0;
        this.isVisible=false;
        Knightmare.sumarScore(this.puntosAlMorir);
        }
        timer++;
        this.update(delta);
        

    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 130, 130, null, null);
        g2.draw(hitbox);
    }

    public void restaurar(){
        this.vida=15;
        this.positionX=350;
        this.positionY=100-5450;
        this.estado=estadoEnemigo.DESACTIVADO;
        try {
            this.imagen = ImageIO.read(getClass().getResource("imagenes/medusa1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}