import java.awt.*;

public abstract class Esfera extends Bonus{

    protected boolean golpeado;

    protected enum tipoMovimiento{
        RECTO,
        SENO,
    }

    protected tipoMovimiento movimiento;

    public Esfera(String filename) {
        super(filename);
        this.movimiento=tipoMovimiento.RECTO;
        this.isPicked=false;
        this.golpeado=false;
        this.isVisible=false;
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 50, 50, null, null);
        g2.draw(hitbox);
    }

  
    protected void toggleMovimiento(){

    }

    protected void moverse(){
        switch(movimiento){
            case RECTO:{
                this.positionY+=0.5;
                this.updateHitbox();
            }
            
            break;
            case SENO:
            break;
        }
    }
    
}
