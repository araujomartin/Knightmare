import java.awt.*;

public abstract class Municion extends ObjetoGrafico implements Movimiento{
    
    protected Rectangle hitboxMunicion;
    protected boolean hit;
    public Municion(String filename){
        super(filename);
        this.hit=false;
    }


    protected void updateHitbox(){
      hitboxMunicion.x=(int)this.positionX;
      hitboxMunicion.y=(int)this.positionY;  
    }

    public void hit(){
        this.isVisible=false;
        this.hit=true;
    }
}
