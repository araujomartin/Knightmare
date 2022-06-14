import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bumeran extends Municion {
    private int spriteCounter=0;
    private int spritePosition=0;
    private int posicionInicial=0;
    private boolean posicionFinal;

    public Bumeran(String filename,int x,int y) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.posicionInicial=y;
        this.isVisible=true;
        this.posicionFinal=false;
        hitboxMunicion=new Rectangle((int)this.positionX,(int)this.positionY,30,15);
    }

    @Override
    public void update(double delta) {

        if(hitBonus){
            FXPlayer.BUMERAN.stop();
            this.isVisible=false;
            return;
        }

        try {
            spriteCounter++;
            if (spriteCounter > 10) { 
                imagen = ImageIO.read(getClass().getResource("imagenes/bumeran" + spritePosition + ".png"));
                spritePosition++;
                if(spritePosition==3){
                    spritePosition=0;
                }
                spriteCounter = 0; 
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        
        this.positionX=Popolon.popolon.getX();

        if(!posicionFinal){   
        this.positionY=this.positionY-(3);
        if(this.positionY<this.posicionInicial-200){
            this.posicionFinal=true;
        }
        }


        if(posicionFinal)   
        {
            if(this.positionY>Popolon.popolon.getY()){
                FXPlayer.BUMERAN.stop();
                this.isVisible=false;
            }
            else{
                this.positionY+=3;    
            }    
         
         
        }
       updateHitbox(); 
    
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY,30,30,null);
        g2.draw(hitboxMunicion); 
    }

    
}
