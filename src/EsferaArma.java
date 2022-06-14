import java.awt.Rectangle;



public class EsferaArma extends Esfera{

    public enum tipoArma{
        ESFERA,
        FLECHA,
        FLECHA_INCENDIARIA,
        BUMERAN,
        BOMBAS_FUEGO,
    }

    tipoArma municionEsfera;

    public EsferaArma(String filename,int x,int y) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.municionEsfera=tipoArma.ESFERA;
        this.hitbox= new Rectangle(x,y,50,50);
       
    }

    @Override
    public void update(double delta) {
          
    }

    @Override
    protected void cambiar() {
        
        
    }

    protected void restaurar(){
        
    }
    
}
