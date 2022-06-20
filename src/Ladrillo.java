import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ladrillo extends Bonus {

    private boolean isBroken;
    private boolean isHited;
    private boolean oculto;
    private String identificador;

    public Ladrillo(String filename, int x, int y,int dureza, String identificador, boolean oculto){
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.dureza=dureza;
        this.identificador=identificador;
        this.oculto=oculto;
        this.isVisible=false;
        this.isBroken=false;
        this.isPicked=false;
        this.isHited=false;
        this.hitbox= new Rectangle(x,y,45,45);
        
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 45, 45, null, null);
        // g2.draw(hitbox);
    }

    @Override
    public void update(double delta) {
        updateHitbox();

        if(isHited && oculto){
            try {
                imagen = ImageIO.read(getClass().getResource("imagenes/incognito.png"));
            }
            catch (IOException e) {
            System.out.println(e);
        }

        }

        if(isHited && this.dureza>0){
            FXPlayer.INCOGNITO.play(-5.0f);
            this.dureza--;
            isHited=false;
        }

        if(this.dureza==0 && isBroken==false){
            FXPlayer.INCOGNITO_ROTO.play(-5.0f);
            isBroken=true;
            try {
                    imagen = ImageIO.read(getClass().getResource("imagenes/"+identificador+".png"));
                }
            catch (IOException e) {
                System.out.println(e);
            }
        }

        if(this.dureza==0 && this.isPicked){
            this.dureza--;
            otorgarBonus();
            try {
                imagen = ImageIO.read(getClass().getResource("imagenes/ladrillo.png"));
            }
            catch (IOException e) {
            System.out.println(e);
        }
        }


    }

    public void otorgarBonus(){

        switch(this.identificador){
            case "torre":{
                FXPlayer.BONUS.play(-5.0f);
                Knightmare.sumarScore(500); 
            }break;
            case "caballo":{
                FXPlayer.BONUS.play(-5.0f);
                Escenario.get_nivel().caballo();
            }break;
            case "rey":{
                FXPlayer.BONUS.play(-5.0f);
                Escenario.get_nivel().rey();
            }break;
            case "reina":
            {
                FXPlayer.UP.play(-10.0f);
                Knightmare.sumarVida();
            }break;
        }

    }

    public void restaurar(){

        this.isVisible=false;
        this.isPicked=false;
        this.isBroken=false;
        this.isHited=false;
        this.hitbox.x=(int)this.positionX;
        this.hitbox.y=(int)this.positionY;

        try {
            if(oculto){
                imagen = ImageIO.read(getClass().getResource("imagenes/vacio.png"));
            }
            else
            {
                imagen = ImageIO.read(getClass().getResource("imagenes/incognito.png"));
            }
        }
        catch (IOException e) {
        System.out.println(e);   
        }

        switch(this.identificador){
            case "torre":
                this.dureza=5;          
            break;
            case "caballo":
                this.dureza=10;
            break;
            case "rey":
                this.dureza=11;
            break;
            case "reina":
                this.dureza=15;
            break;
            
        }
       
    }

    public void hit(){
        this.isHited=true;
    }

    public boolean getBroken(){
        return isBroken;
    }

   


    
    
}
