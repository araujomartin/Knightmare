import java.awt.*;


public class BolaFuego extends Municion{

    private String idMovimiento;

    public BolaFuego(String filename, int x, int y, String id) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.isVisible=true;
        this.idMovimiento=id;
        hitboxMunicion=new Rectangle((int)this.positionX,(int)this.positionY,14,30);
    }

    @Override
    public void update(double delta) {

        if(hitBonus){
            this.isVisible=false;
            return;
        }

        switch(idMovimiento){
            case "left":{
            this.positionX=this.positionX-(5);         
            }break;
            case "right":{
                this.positionX=this.positionX+(5);
            }break;

        }
        this.positionY=this.positionY-(8);
       
        updateHitbox();

        if(this.positionY<30){
        this.isVisible=false;
        }
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY,14,30,null);
        // g2.draw(hitboxMunicion); 
    }

    
}
