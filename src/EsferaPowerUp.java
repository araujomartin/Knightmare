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
        this.x_inicial=x;
        this.power=tipoPowerUp.NEGRO;
        this.hitbox= new Rectangle(x,y,50,50);
        
    }


    @Override
    public void update(double delta) {

        if(this.isPicked){
            otorgarPowerUp();
            isVisible=false;
            return;
        }


        this.moverse(delta);    
       
        if(golpeado){
            FXPlayer.ESFERA.play(-5.0f);
            vecesGolpeado++;
            this.cambiar();
            golpeado=false;
            if(vecesGolpeado>3){
                this.cambiarMovimiento();
            }
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
            case NEGRO:{
                FXPlayer.BONUS.play(-5.0f);
                Knightmare.sumarScore(1000);
            }
            break;
            case CELESTE:{
                FXPlayer.POWERUP.play(-20.0f);
                Popolon.popolon.celeste();
            }
            break;
            case AZUL:{
                FXPlayer.POWERUP.play(-20.0f);
                Popolon.velocidad();
            }
            break;
            case ROJO:{
                FXPlayer.POWERUP.play(-20.0f);
                Popolon.popolon.cambiar(Popolon.estados.ROJO);
                Knightmare.juego.powerup();
            }
            break;
            case BLANCO:{
                FXPlayer.POWERUP.play(-20.0f);
                Popolon.popolon.cambiar(Popolon.estados.INVISIBLE);
                Knightmare.juego.powerup();
            }
            break;
        }
    }

    protected void cambiar(){
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

    public void restaurar(){
        this.power=tipoPowerUp.NEGRO;
        this.movimiento=tipoMovimiento.RECTO;
        this.isPicked=false;
        this.golpeado=false;
        this.isVisible=false;
        this.activado=false;
        this.hitbox.x=(int)this.positionX;
        this.hitbox.y=(int)this.positionY;
        
    }

    
    
}

