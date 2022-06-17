import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;

public class Murcielago extends Enemigo{
    private double variableMovimiento=Math.PI;
    private int x_inicial;
    private int mov;

    public Murcielago(String filename, int x, int y, boolean shot, String direccion) {
        super(filename);
        this.hitbox = new Rectangle((int) x, (int) y, 45, 40);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.x_inicial=x;
        this.puntosAlMorir=50;
        this.canShot=shot;
        switch(direccion){
            case "LEFT":
            this.mov=-1;
            break;
            case "RIGHT":
            this.mov=1;
            break;
        }
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
            this.positionY+=1.3;
            positionX = x_inicial + (Math.sin(variableMovimiento)*(300)*mov);
            variableMovimiento+=delta;

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

                    imagen = ImageIO.read(getClass().getResource("imagenes/murcielago" + spritePosition + ".png"));
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

    public void restaurar(){
        isVisible=false;
        this.estado = estadoEnemigo.DESACTIVADO;
        this.timer=0;
        this.hitbox.y=(int)this.positionY;
        this.hitbox.x=(int)this.positionX;
        this.variableMovimiento=Math.PI;
        try {
            this.imagen = ImageIO.read(getClass().getResource("imagenes/murcielago1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void display(Graphics2D g2) {
        if(estado ==  estadoEnemigo.MUERTO){
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 40, 40, null, null);
            g2.draw(hitbox);
        }
        else{
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 45, 70, null, null);
            g2.draw(hitbox);
        }

    }
    
}
