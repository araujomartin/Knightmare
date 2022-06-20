import java.awt.*;

public class FlechaIncendiaria extends Municion{

    public FlechaIncendiaria(String filename, int x, int y) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.isVisible=true;
        hitboxMunicion=new Rectangle((int)this.positionX,(int)this.positionY,14,30);

    }

    @Override
    public void update(double delta) {

        if(hitBonus){
            this.isVisible=false;
            return;
        }

        updateHitbox();
        this.positionY=this.positionY-(10);


        if(this.positionY<30){
        this.isVisible=false;
        }
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY,14,30,null);
        // g2.draw(hitboxMunicion); 
    }
    
    
}
