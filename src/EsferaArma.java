import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EsferaArma extends Esfera{

    public enum tipoArma{
        ESFERA,
        FLECHA,
        FLECHA_INCENDIARIA,
        BUMERAN,
        ESPADAS,
        BOMBAS_FUEGO,
    }

    tipoArma municionEsfera;

    public EsferaArma(String filename,int x,int y) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.x_inicial=x;
        this.municionEsfera=tipoArma.ESFERA;
        this.hitbox= new Rectangle(x,y,50,50);
       
    }

    @Override
    public void update(double delta) {
        int cadencia=1;

        if(this.isPicked){
            otorgarArma();
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

        if(municionEsfera==tipoArma.ESFERA){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/esfera" + spritePosition + ".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>2){
                        spritePosition=0;
                    } 
               
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(municionEsfera==tipoArma.FLECHA){
            if(Popolon.popolon.arma.municionActual().toString()=="FLECHA"){
                cadencia=2;
            }
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/esferaFlecha"+cadencia+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(municionEsfera==tipoArma.FLECHA_INCENDIARIA){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/esferaFlechaIncendiaria"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(municionEsfera==tipoArma.BUMERAN){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/esferaBumeran"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(municionEsfera==tipoArma.ESPADAS){
            if(Popolon.popolon.arma.municionActual().toString()=="ESPADAS"){
                cadencia=2;
            }
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/esferaEspada"+cadencia+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(municionEsfera==tipoArma.BOMBAS_FUEGO){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/esferaFuego"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        

          
    }

    @Override
    protected void cambiar() {
        this.spritePosition=0;

        switch(municionEsfera){
            case ESFERA:{
                this.municionEsfera=tipoArma.FLECHA;             
            }
            break;
            case FLECHA:{
                this.municionEsfera=tipoArma.FLECHA_INCENDIARIA; 
            }
            break;
            case FLECHA_INCENDIARIA:{
                this.municionEsfera=tipoArma.BUMERAN;
            }
            break;
            case BUMERAN:{
                this.municionEsfera=tipoArma.ESPADAS;
            }
            break;
            case ESPADAS:{
                this.municionEsfera=tipoArma.BOMBAS_FUEGO;
            }
            case BOMBAS_FUEGO:{
                this.municionEsfera=tipoArma.FLECHA;
            }
            break;
        }
        
        
    }

    protected void restaurar(){
        this.municionEsfera=tipoArma.ESFERA;
        this.movimiento=tipoMovimiento.RECTO;
        this.isPicked=false;
        this.golpeado=false;
        this.isVisible=false;
        this.activado=false;
        this.hitbox.x=(int)this.positionX;
        this.hitbox.y=(int)this.positionY;
        
    }

    private void otorgarArma(){
        switch(municionEsfera){
            case ESFERA:{
                FXPlayer.BONUS.play(-5.0f);
                Knightmare.sumarScore(1000);
            }
            break;
            case FLECHA:{
                FXPlayer.BONUS.play(-5.0f);
                Popolon.popolon.arma.cambiarMunicion("FLECHA");
            }
            break;
            case FLECHA_INCENDIARIA:{
                FXPlayer.BONUS.play(-5.0f);
                Popolon.popolon.arma.cambiarMunicion("FLECHA_INCENDIARIA");
            }
            break;
            case BUMERAN:{
                FXPlayer.BONUS.play(-5.0f);
                Popolon.popolon.arma.cambiarMunicion("BUMERAN");
            }
            break;
            case ESPADAS:{
                FXPlayer.BONUS.play(-5.0f);
                Popolon.popolon.arma.cambiarMunicion("ESPADAS"); 
            }
            break;
            case BOMBAS_FUEGO:{
                FXPlayer.BONUS.play(-5.0f);
                Popolon.popolon.arma.cambiarMunicion("BOMBAS_FUEGO"); 
            }
            break;
        }

    }
    
}
