import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class BombasRobot extends Enemigo {


    public BombasRobot(String filename, int x, int y, boolean shot) {
        super(filename, shot);
        this.hitbox = new Rectangle((int) x, (int) y, 40, 40);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.puntosAlMorir=10;
        this.canShot=shot;
        if(canShot){
            this.arma=new Arma(Arma.tipoMunicion.ENEMIGA, 0, 0);
        }
        
    }

    @Override
    public void update(double delta) {
        
        if(this.positionY>600){
            this.isVisible=false;
            return;
        }

        if (estado == estadoEnemigo.VIVO && this.isVisible) {
            System.out.println("estoy vivo");   
            this.positionY+=0.7;
            this.updateHitBox();
            if(canShot){
                this.updateArma((int)this.positionX+15, (int)this.positionY);
                if(timerDisparo>2.5){
                    this.disparar();
                    timerDisparo=0;     
                }
                else{
                    timerDisparo+=delta;
                }
                
            }
            try {
                spriteCounter++;
                if (spriteCounter > 10) {

                    if (spritePosition == 2)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 2;

                    imagen = ImageIO.read(getClass().getResource("imagenes/" + spritePosition + ".png"));
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

                    imagen = ImageIO.read(getClass().getResource("imagenes/fuego" + spritePosition + ".png"));
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

        if(estado == estadoEnemigo.DESACTIVADO){
            this.desactivado();
            
        }

    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 40, 40, null, null);
        g2.draw(hitbox);
    }

    public void restaurar(){
        isVisible=false;
        this.estado = estadoEnemigo.DESACTIVADO;
        this.timer=0;
        this.hitbox.y=(int)this.positionY;
        this.hitbox.x=(int)this.positionX;
        try {
            this.imagen = ImageIO.read(getClass().getResource("imagenes/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
