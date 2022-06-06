import java.awt.Rectangle;

public class EsferaArma extends Esfera{

    public enum tipoArma{
        FLECHA,
        FLECHA_INCENDIARIA,
        BOOMERANG,
        BOMBAS_FUEGO,
    }

    tipoArma arma;

    public EsferaArma(String filename,int x,int y) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.hitbox= new Rectangle(x,y,50,50);
       
    }

    @Override
    public void update(double delta) {
          
    }
    
}
