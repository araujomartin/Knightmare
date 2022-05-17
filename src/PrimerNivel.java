import java.awt.*;

public class PrimerNivel extends Escenario{

    public PrimerNivel(){
        super("imagenes/nivel1.png");
        generarObstaculos();

        
    }

    public void generarObstaculos(){

        // PILARES MAPA//
        this.addObstaculo(new Rectangle(9, 4525, 90, 1031));
        this.addObstaculo(new Rectangle(700,5025, 100, 544));
        this.addObstaculo(new Rectangle(700,4600, 100, 197));
        this.addObstaculo(new Rectangle(700,3625, 100, 444));
        this.addObstaculo(new Rectangle(700,2825, 100, 444));
        this.addObstaculo(new Rectangle(700,2300, 100, PILAR*2));
        this.addObstaculo(new Rectangle(700,1900, 100, PILAR));
        this.addObstaculo(new Rectangle(700,1400, 100, PILAR*4));
        this.addObstaculo(new Rectangle(700,30, 100, 660));




    }

    



    
}
