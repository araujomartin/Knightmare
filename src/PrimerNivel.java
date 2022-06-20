import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class PrimerNivel extends Escenario{


    public PrimerNivel(){
        super("imagenes/nivel1.png");
        generarEnemigos();
        generarObstaculos();
        generarLadrillos();
        generarEsferas();
        this.jefeFinal=new Medusa("imagenes/medusa1.png", 350, 100-5450);
        
        
        checkpoint=new int[]{-4850,-4250,-2000,-700,0};
        
        
    }

    public void generarObstaculos(){

        // Pilares izquierda mapa//
        this.addObstaculo(new Rectangle(9, 4525-5450, 80, 1031));
        this.addObstaculo(new Rectangle(9, 3900-5450, 80, PILAR*3));
        this.addObstaculo(new Rectangle(9, 3500-5450, 80, PILAR*3));
        this.addObstaculo(new Rectangle(9, 2325-5450, 80, 937));
        this.addObstaculo(new Rectangle(9, 1400-5450, 80, PILAR*4));
        this.addObstaculo(new Rectangle(9, 1000-5450, 80, PILAR));
        this.addObstaculo(new Rectangle(9, 30-5450, 80, 640));

        // Pilares derecha mapa//
        this.addObstaculo(new Rectangle(710,5025-5450, 100, 544));
        this.addObstaculo(new Rectangle(710,4600-5450, 100, 197));
        this.addObstaculo(new Rectangle(710,3625-5450, 100, 444));
        this.addObstaculo(new Rectangle(710,2825-5450, 100, 444));
        this.addObstaculo(new Rectangle(710,2300-5450, 100, PILAR*2));
        this.addObstaculo(new Rectangle(710,1900-5450, 100, PILAR));
        this.addObstaculo(new Rectangle(710,1400-5450, 100, PILAR*4));
        this.addObstaculo(new Rectangle(710,30-5450, 100, 640));

        // Agua //
        this.addObstaculo(new Rectangle(10,4325-5450, 280, 60));
        this.addObstaculo(new Rectangle(505,4325-5450, 294, 60));

        this.addObstaculo(new Rectangle(10,3325-5450, 187, 60));
        this.addObstaculo(new Rectangle(305,3325-5450, 78, 60));
        this.addObstaculo(new Rectangle(505,3325-5450, 294, 60));

        this.addObstaculo(new Rectangle(10,2125-5450, 384, 60));
        this.addObstaculo(new Rectangle(505,2125-5450, 287, 60));

        this.addObstaculo(new Rectangle(10,1225-5450, 281, 60));
        this.addObstaculo(new Rectangle(410,1225-5450, 62, 60));
        this.addObstaculo(new Rectangle(605,1225-5450, 195, 60));

        this.addObstaculo(new Rectangle(10,825-5450, 195, 60));
        this.addObstaculo(new Rectangle(310,825-5450, 487, 60));

    }

    public void generarLadrillos(){
        
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 200, 5300-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 450, 5050-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 450, 5005-5450, 5, "torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 350, 4600-5450, 10,"caballo",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 150, 4400-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 250, 4000-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 250, 3900-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 550, 3750-5450, 11,"rey",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 650, 3250-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 550, 2550-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 250, 2550-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 150, 2050-5450, 5,"torre",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 300, 1950-5450, 10,"caballo",false));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 400, 1600-5450, 5,"torre",false));

        
        //Ladrillos Ocultos

        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 705, 4800-5450, 10,"caballo",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 600, 4600-5450, 5,"torre",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 600, 4500-5450, 5,"torre",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 600, 4400-5450, 5,"torre",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 250, 3800-5450, 5,"torre",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 250, 3700-5450, 5,"torre",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 300, 3050-5450, 11,"rey",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 700, 2700-5450, 12,"reina",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 400, 1450-5450, 5,"torre",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 400, 1300-5450, 5,"torre",true));
        this.addLadrillos(new Ladrillo("imagenes/vacio.png", 150, 1100-5450, 10,"caballo",true));
        
    }

    public void generarEnemigos(){

        this.addEnemigo(new BombasRobot("imagenes/1.png", 520, 5400-5450, false));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 120, 5380-5450, false));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 515, 5260-5450, false));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 400, 5200-5450, false));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 655, 5150-5450, false));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 400, 5050-5450, false));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 280, 5000-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 110, 4900-5450, false));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 520, 4880-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4800-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4795-5450, false,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4790-5450, false,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4785-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4780-5450, false,"LEFT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4700-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4695-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4690-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4685-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4680-5450, false,"RIGHT"));

        this.addEnemigo(new BombasRobot("imagenes/1.png", 450, 4680-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4650-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4645-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4640-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4635-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4630-5450, false,"RIGHT"));

        this.addEnemigo(new BombasRobot("imagenes/1.png", 260, 4450-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 500, 4400-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 380, 4255-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 4200-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 4195-5450, false,"RIGHT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 4200-5450, false,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 4195-5450, false,"LEFT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4090-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4085-5450, false,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4080-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4075-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 4070-5450, false,"LEFT"));

        this.addEnemigo(new BombasRobot("imagenes/1.png", 520, 4000-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 150, 3950-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 3840-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 3840-5450, true,"RIGHT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 3700-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 3650-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 3600-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 3580-5450, true,"RIGHT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 3540-5450, false,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 3540-5450, true,"RIGHT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 3440-5450, false,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 3440-5450, true,"RIGHT"));
        
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 235, 3340-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 435, 3335-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 235, 3240-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 435, 3235-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 235, 3140-5450, false));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 435, 3135-5450, true));
        
        this.addEnemigo(new BombasRobot("imagenes/1.png", 530, 3030-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 115, 2850-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2770-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2765-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2760-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2755-5450, true,"RIGHT"));

        this.addEnemigo(new BombasRobot("imagenes/1.png", 625, 2525-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 430, 2470-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2315-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2310-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2305-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2300-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2295-5450, false,"RIGHT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2265-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2260-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2255-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2250-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 2245-5450, false,"RIGHT"));

        this.addEnemigo(new BombasRobot("imagenes/1.png", 220, 2065-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 135, 2000-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 2015-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 2010-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 2005-5450, true,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 2000-5450, false,"LEFT"));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 1905-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 1900-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 1895-5450, false,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 1890-5450, false,"RIGHT"));

        this.addEnemigo(new BombasRobot("imagenes/1.png", 525, 1800-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 300, 1750-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 160, 1600-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 650, 1645-5450, true));

        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 575, 1470-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 575, 1400-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 115, 1410-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 1275-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 300, 1230-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 1280-5450, false,"LEFT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 500, 1240-5450, false,"LEFT"));

        this.addEnemigo(new BombasRobot("imagenes/1.png", 250, 1000-5450, true));
        this.addEnemigo(new BombasRobot("imagenes/1.png", 120, 900-5450, true));

        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 800-5450, true,"RIGHT"));
        this.addEnemigo(new Murcielago("imagenes/murcielago1.png", 460, 820-5450, false,"LEFT"));
        
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 300, 700-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 400, 700-5450, true));
        
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

        this.addEsfera(new EsferaArma("imagenes/esfera0.png", 620, 2020));
        this.addEsfera(new EsferaArma("imagenes/esfera0.png", 360, 3350));
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
