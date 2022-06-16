import java.awt.*;

public abstract class Esfera extends Bonus{

    protected boolean golpeado;
    protected int x_inicial;
    protected double variableMovimiento=0;
    protected int vecesGolpeado=0;
    protected boolean activado=false;
    protected int spriteCounter=0;
    protected int spritePosition=0;
    
    

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
    protected abstract void cambiar();
    protected abstract void restaurar();

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 50, 50, null, null);
        g2.draw(hitbox);
    }

  
    protected void moverse(double delta){
        switch(movimiento){
            case RECTO:{
                this.positionY+=0.7;
                this.updateHitbox();
            }
            
            break;
            case SENO:{
                this.positionY+=0.7;
                variableMovimiento+=delta;
                positionX = x_inicial +(int) (Math.sin(variableMovimiento)*40); // Se mueve de a 40
                this.updateHitbox(); 
            }
            break;
        }
    }

    protected void cambiarMovimiento(){
       this.movimiento=tipoMovimiento.SENO;

    }


    
}
