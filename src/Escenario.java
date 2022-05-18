import java.awt.*;
import java.util.*;



public abstract class Escenario {

    protected static Escenario NIVEL= null;
    protected Fondo fondo;
    protected Rectangle limites;
    protected final int PILAR=94;
    protected ArrayList<Rectangle> obstactulos=new ArrayList<Rectangle>(1);
    protected ArrayList<Enemigo> enemigos;
    private int counter=0;
    

    protected final static double ANCHO=800; //256*3.125; //Cuenta para que me de 800 pixeles de Ancho
    protected final static double ALTO=5950; //1904*3.125; //Equivalencia para mantener el formato (5950)


    protected Escenario(String filename){ //constructor protegido, solo quiero una instancia del nivel
        fondo= new Fondo(filename);
        limites=new Rectangle(0,5350,800,600); //Los limites del mapa son del tamaño de la ventana, estos se van a ir moviendo.       
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



    public float getWidth() {
        return (float) limites.getWidth();
    }

    public float getHeight() {
        return (float) limites.getHeight();
    }

    public void display(Graphics2D g2) {
        
        fondo.display(g2);
        
        g2.draw(limites);
        for(Rectangle obstaculo:obstactulos){
            g2.draw(obstaculo);
        }
        
        if(counter>2)
        {
        fondo.positionY++;
        for(Rectangle obstaculo:obstactulos){
            obstaculo.y++;
        }
         counter=0;
        }
        counter++;
        
    
      }

    public Rectangle getLimites(){
        return limites;
    }

    public void addObstaculo(Rectangle obstaculo){
        obstactulos.add(obstaculo);
    }

    public boolean colisionObstaculo(Rectangle siguientePosicion){
        
        for(Rectangle obstaculo: obstactulos){
            if(obstaculo.intersects(siguientePosicion)){
                return true;
            } 
        }   
        return false;
    } 
    
    




    
}
