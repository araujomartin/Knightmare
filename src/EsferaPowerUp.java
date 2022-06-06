import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EsferaPowerUp extends Esfera {

    private int spriteCounter=0;
    private int spritePosition=0;
    

    public enum tipoPowerUp{
       NEGRO,
       CELESTE,
       AZUL,
       ROJO,
       BLANCO, 
    }

    private tipoPowerUp power;

    public EsferaPowerUp(String filename,int x,int y) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.power=tipoPowerUp.NEGRO;
        this.hitbox= new Rectangle(x,y,50,50);
        
    }


    @Override
    public void update(double delta) {

        this.moverse();
       
        if(golpeado){
            this.cambiar();
            golpeado=false;
        }

        if(power==tipoPowerUp.NEGRO){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/powerNegro" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(power==tipoPowerUp.CELESTE){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/powerCeleste" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(power==tipoPowerUp.AZUL){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/powerAzul" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(power==tipoPowerUp.ROJO){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/powerRojo" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(power==tipoPowerUp.BLANCO){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/powerBlanco" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }
        
    }

    public void otorgarPowerUp(){
        switch(power){
            case NEGRO:
            break;
            case CELESTE:
            break;
            case AZUL:
            break;
            case ROJO:
            break;
            case BLANCO:
            break;
        }
    }

    public void cambiar(){
        switch(power){
            case NEGRO:
            this.power=tipoPowerUp.CELESTE;
            break;
            case CELESTE:
            this.power=tipoPowerUp.AZUL;
            break;
            case AZUL:
            this.power=tipoPowerUp.ROJO;
            break;
            case ROJO:
            this.power=tipoPowerUp.BLANCO;
            break;
            case BLANCO:
            this.power=tipoPowerUp.NEGRO;
            break;
        }
    }
    
}

