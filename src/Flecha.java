import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Flecha extends Municion{

    

    Flecha(String filename, int x, int y, int candencia){
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.isVisible=true;
        hitboxMunicion=new Rectangle((int)this.positionX,(int)this.positionY,13,30);
    }

    @Override
    public void update(double delta) {
        updateHitbox();
        this.positionY=this.positionY-5;

        

        if(this.positionY<40){
        this.isVisible=false;
        }
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY,13,30,null);
        g2.draw(hitboxMunicion); 
    }

   
    
}
