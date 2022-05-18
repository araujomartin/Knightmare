import java.awt.*;

public class PrimerNivel extends Escenario{

    public PrimerNivel(){
        super("imagenes/nivel1.png");
        generarObstaculos();

        
    }

    public void generarObstaculos(){

        // Pilares izquierda mapa//
        this.addObstaculo(new Rectangle(9, 4525, 80, 1031));
        this.addObstaculo(new Rectangle(9, 3900, 80, PILAR*3));
        this.addObstaculo(new Rectangle(9, 3500, 80, PILAR*3));
        this.addObstaculo(new Rectangle(9, 2325, 80, 937));
        this.addObstaculo(new Rectangle(9, 1400, 80, PILAR*4));
        this.addObstaculo(new Rectangle(9, 1000, 80, PILAR));
        this.addObstaculo(new Rectangle(9, 30, 80, 640));

        // Pilares derecha mapa//
        this.addObstaculo(new Rectangle(700,5025, 100, 544));
        this.addObstaculo(new Rectangle(700,4600, 100, 197));
        this.addObstaculo(new Rectangle(700,3625, 100, 444));
        this.addObstaculo(new Rectangle(700,2825, 100, 444));
        this.addObstaculo(new Rectangle(700,2300, 100, PILAR*2));
        this.addObstaculo(new Rectangle(700,1900, 100, PILAR));
        this.addObstaculo(new Rectangle(700,1400, 100, PILAR*4));
        this.addObstaculo(new Rectangle(700,30, 100, 640));

        // Agua //
        this.addObstaculo(new Rectangle(10,4325, 280, 60));
        this.addObstaculo(new Rectangle(505,4325, 294, 60));

        this.addObstaculo(new Rectangle(10,3325, 187, 60));
        this.addObstaculo(new Rectangle(305,3325, 78, 60));
        this.addObstaculo(new Rectangle(505,3325, 294, 60));

        this.addObstaculo(new Rectangle(10,2125, 384, 60));
        this.addObstaculo(new Rectangle(505,2125, 287, 60));

        this.addObstaculo(new Rectangle(10,1225, 281, 60));
        this.addObstaculo(new Rectangle(410,1225, 62, 60));
        this.addObstaculo(new Rectangle(605,1225, 195, 60));

        this.addObstaculo(new Rectangle(10,825, 195, 60));
        this.addObstaculo(new Rectangle(310,825, 487, 60));










    }

    



    
}
