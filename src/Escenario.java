import java.awt.*;
import java.util.*;


public abstract class Escenario {

    protected static Escenario NIVEL= null;
    protected Fondo fondo;
    protected final Rectangle limites;
    protected final int PILAR=94;
    protected ArrayList<Rectangle> obstaculos=new ArrayList<Rectangle>(1);
    protected ArrayList<Ladrillo> ladrillos=new ArrayList<Ladrillo>(1);

    protected ArrayList<Ladrillo> ladrillosColisionalbes= new ArrayList<Ladrillo>(1);
    protected ArrayList<Enemigo> enemigosColisionables=new ArrayList<Enemigo>(1);
    protected ArrayList<Municion> municionHeroeColisionada=new ArrayList<Municion>(1);
    public ArrayList<Municion> muncionHeroe=new ArrayList<Municion>(1);
    

    protected int counter=0;
    protected boolean stop=false;
    protected int lastCheckPoint=0;
    protected int[] checkpoint;
    
    
    

    protected Escenario(String filename){ //constructor protegido, solo quiero una instancia del nivel
        fondo= new Fondo(filename);
        limites=new Rectangle(0,80,805,500);        
    }

    protected static void crear_nivel(){
        if (NIVEL == null) {
            synchronized(Escenario.class) {
                if (NIVEL == null) {
                    switch(Knightmare.numeroNivel){
                        case 1:
                        NIVEL = new PrimerNivel();
                        break;
                    }
                    
                }
            }
        }

    }

    public static Escenario get_nivel() {
        if(NIVEL==null){
            crear_nivel();
        }
        return NIVEL;
    }

    public static void clear(){
        NIVEL=null;
    }

    public abstract void update(double delta);


    public float getWidth() {
        return (float) fondo.getWidth();
    }

    public float getHeight() {
        return (float) fondo.getY();
    }

    public void display(Graphics2D g2) {
        
        fondo.display(g2);

        for(Rectangle o:obstaculos){
            g2.setColor(Color.RED);
            g2.draw(o);
        }
        
        for(Municion m: muncionHeroe){
                m.display(g2);  
        }

        for(Enemigo e: enemigosColisionables){
            e.display(g2);
        }
        

    
      }
    

    public void addObstaculo(Rectangle obstaculo){
        obstaculos.add(obstaculo);
    }

    public void addEnemigo(Enemigo enemigo){
        
    }

    public boolean colisionObstaculo(Rectangle siguientePosicion){
        
        for(Rectangle obstaculo: obstaculos){
            if(obstaculo.intersects(siguientePosicion)){
                return true;
            } 
        }   
        return false;
    }
    
    public boolean colisionEnemigo(Rectangle heroeHitbox){
        // for(Enemigo enemigo: enemigos){
        //     if(enemigo.hitbox.intersects(heroeHitbox)){
        //         return true;
        //     } 
        // }   
        return false;

    }

    public void stop(){
        stop=true;
    }

    public int checkpointPosition(){
        return checkpoint[lastCheckPoint];
    }

    public void reLoadStaticObjetcs(){
        int movimiento; //Variable para guardar cuanto se movio el mapa desde que arranco el nivel
        this.stop=false;    // Para que el mapa vuelva a moverse
        
        // Calculo cuanto se movio el mapa desde el ultimo checkpoint, en caso de que no se haya llegado, se restaura por defecto.
        if(lastCheckPoint!=0){
        movimiento=(-(lastCheckPoint)+((int)this.fondo.positionY));
        this.fondo.positionY=lastCheckPoint;
        }
        else{
            movimiento=(5450)+((int)this.fondo.positionY);
            this.fondo.positionY=-5450;
        }

        // Acomodo objetos estaticos segun el movimiento
        for(Rectangle o: obstaculos){
            o.y=o.y-movimiento;
        }
                
    }

    public boolean colisionMunicion(Municion municion){

        for(Enemigo e: enemigosColisionables){
            if(e.hitbox.intersects(municion.hitboxMunicion)){
                e.estado=Enemigo.estadoEnemigo.MUERTO;
                return true;
            } 
        }   
        return false;

        
    }
    
    
    
    




    
}
