import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class SegundoNivel extends Escenario{


    public SegundoNivel(){
        super("imagenes/nivel2.png");
        generarEnemigos();
        generarObstaculos();
        generarLadrillos();
        generarEsferas();
        this.jefeFinal=new Medusa("imagenes/medusa1.png", 350, 100-5450);
        
        
        checkpoint=new int[]{-4850,-4250,-2000,-700,0};
        
        
    }

    public void generarObstaculos(){



    }

    public void generarLadrillos(){
   
        
    }

    public void generarEnemigos(){

        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 680, 5400-5450, false));
        //Enemigos reales
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 520, 5400-5450, false));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 120, 5380-5450, false));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 515, 5260-5450, false));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 400, 5200-5450, false));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 655, 5150-5450, false));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 400, 5050-5450, false));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 280, 5000-5450, true));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 110, 4900-5450, false));
        // this.addEnemigo(new BombasRobot("imagenes/1.png", 520, 4880-5450, false));

        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4800-5450, true,"LEFT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4795-5450, false,"LEFT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4790-5450, false,"LEFT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4785-5450, true,"LEFT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4780-5450, false,"LEFT"));

        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4700-5450, false,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4695-5450, true,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4690-5450, true,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4685-5450, false,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4680-5450, false,"RIGHT"));

        // this.addEnemigo(new BombasRobot("imagenes/1.png", 450, 4680-5450, true));

        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4650-5450, false,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4645-5450, true,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4640-5450, false,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4635-5450, false,"RIGHT"));
        // this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4630-5450, false,"RIGHT"));


        try {
            raf = new RandomAccessFile("nivel.dat", "rw");
 
            for (Enemigo e: enemigos) {
                raf.writeDouble(e.positionX);
                raf.writeDouble(e.positionY);
            }
         
         
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void generarEsferas(){
        
        //Esferas Power
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 630, 4650-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 350, 4000-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 350, 2350-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 150, 1700-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 400, 1100-5450));
        //Esferas Arma
        
        try {
            raf2 = new RandomAccessFile("esferas.dat", "rw");
 
            for (Esfera e: esferas) {
                raf2.writeDouble(e.positionX);
                raf2.writeDouble(e.positionY);
            }
         
         
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    


    
}
