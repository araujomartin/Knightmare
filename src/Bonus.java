import java.awt.*;

public abstract class Bonus extends ObjetoGrafico{

    protected int dureza;
    protected boolean isPicked;
    protected Rectangle hitbox;

    public Bonus(String filename){
        super(filename);
        
    }

    public abstract void display(Graphics2D g2);
    public abstract void update(double delta);
    

    protected void updateHitbox(){
        this.hitbox.x=(int)this.positionX;
        this.hitbox.y=(int)this.positionY;
    }

    public boolean getVisible(){
        return isVisible;
    }

    public void setVisible(boolean set){
        isVisible=set;
    }

    public void setPicked(boolean set){
        this.isPicked=set;
    }

    

    
    
}
