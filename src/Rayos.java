import java.awt.*;

public class Rayos extends Municion{

    public Rayos(String filename, int x, int y){
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.isVisible=true;
        hitboxMunicion=new Rectangle((int)this.positionX,(int)this.positionY,25,25);
    }



    @Override
    public void update(double delta) {
        if(hitEnemigo){
            this.isVisible=false;
            return;   
        }
        this.positionY=this.positionY+(4);

        updateHitbox();

        if(this.positionY>600){
        this.isVisible=false;
        }

        
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY,25,25,null);
        // g2.draw(hitboxMunicion); 
    }

}
