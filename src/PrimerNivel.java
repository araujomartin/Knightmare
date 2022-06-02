import java.awt.*;
import java.util.ArrayList;

public class PrimerNivel extends Escenario{

    private ArrayList<Enemigo> primeraOleada;

    
    public PrimerNivel(){
        super("imagenes/nivel1.png");
        generarEnemigos();
        generarObstaculos();
        generarLadrillos();
        
        lastCheckPoint=0;
        checkpoint=new int[]{-4850,-4250};
        
        
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
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 200, 5300-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 450, 5050-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 450, 5000-5450, 5, "torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 350, 4600-5450, 10,"caballo"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 150, 4400-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 250, 4000-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 250, 3900-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 550, 3750-5450, 11,"rey"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 650, 3250-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 550, 2550-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 250, 2550-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 150, 2050-5450, 5,"torre"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 300, 1950-5450, 10,"caballo"));
        this.addLadrillos(new Ladrillo("imagenes/incognito.png", 400, 1600-5450, 5,"torre"));
    }

    public void generarEnemigos(){
        primeraOleada=new ArrayList<Enemigo>(6);

        primeraOleada.add(new BombasRobot("imagenes/1.png", 440, -20, false));
        primeraOleada.add(new BombasRobot("imagenes/1.png", 110, -100, false));
        primeraOleada.add(new BombasRobot("imagenes/1.png", 530, -200, false));
        primeraOleada.add(new BombasRobot("imagenes/1.png", 366, -300, false));
        primeraOleada.add(new BombasRobot("imagenes/1.png", 640, -400, false));
        primeraOleada.add(new BombasRobot("imagenes/1.png", 366, -500, false));
   
    }

    public void update(double delta){

        enemigosColisionables.clear();
        muncionHeroe.removeAll(municionHeroeColisionada);
        ladrillosColisionalbes.clear();
        bonusObtenibles.clear();

        // Muevo el mapa, y objetos fijos siempre y cuando mi contador se encuentre por encima de 2, y el stop sea falso
        
        if(counter>2 && stop==false)
        {
        fondo.positionY++;    
        for(Rectangle obstaculo:obstaculos){
            obstaculo.y++;
            }
        for(Ladrillo ladrillo:ladrillos){
            ladrillo.positionY++;
            ladrillo.updateHitbox();
            if(Escenario.get_nivel().limites.intersects(ladrillo.hitbox)){
                ladrillo.setVisible(true);
            }
            else
            {
                ladrillo.setVisible(false);
            }
            
            if(ladrillo.getVisible()){
                ladrillosColisionalbes.add(ladrillo);
            }
        

        }
        counter=0;
        }
        counter++;

        for(Ladrillo l: ladrillosColisionalbes){
            l.update(delta);
            if(l.getBroken() && !l.isPicked){
                System.out.println("Agrega bonus");
                bonusObtenibles.add(l);
            }
        }


        for(Enemigo e:primeraOleada){
            if(e.isVisible){
                e.update(delta);
                enemigosColisionables.add(e);
            }
        }
        
        for(Municion m: muncionHeroe){
            if(this.colisionMunicion(m)){
                m.hit();
            }
            if(m.isVisible){
            m.update(delta);
            }
            else
            municionHeroeColisionada.add(m);
    
        }

        
        // Los checkpoint se encuentran guardados por posicion del fondo, si esta posicion se supera movemos la posicion del ultimo checkpoint

        
            for(int i=0; i<checkpoint.length;i++){
                if(checkpoint[i]==fondo.positionY){
                    lastCheckPoint=checkpoint[i];
                }
            }
        
        
        
        

    }

    



    
}
