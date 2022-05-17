import java.awt.*;

public class PrimerNivel extends Escenario{

    public PrimerNivel(){
        super("imagenes/nivel1.png");
        generarObstaculos();
        
    }

    public void generarObstaculos(){
        this.addObstaculo(new Rectangle(9, 4525, 94, 1031));
        System.out.println(this.obstactulos);
    }

    



    
}
