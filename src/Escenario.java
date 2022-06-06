import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;


public abstract class Escenario {

    protected static Escenario NIVEL= null;
    protected Fondo fondo;
    protected final Rectangle limites;
    protected final int PILAR=94;

    RandomAccessFile raf, raf2;
    protected int pos=0;
    protected int pos2=0;
    
    protected ArrayList<Rectangle> obstaculos=new ArrayList<Rectangle>(1);
    protected ArrayList<Ladrillo> ladrillos=new ArrayList<Ladrillo>(1);
    protected ArrayList<Enemigo> enemigos=new ArrayList<Enemigo>(1);
    protected ArrayList<Esfera> esferas=new ArrayList<Esfera>(1);


    protected ArrayList<Esfera> esferasRemover=new ArrayList<Esfera>(1);
    protected ArrayList<Enemigo> enemigosRemover=new ArrayList<Enemigo>(1);
    protected ArrayList<Bonus> bonusObtenibles=new ArrayList<Bonus>(1);
    protected ArrayList<Ladrillo> ladrillosColisionalbes= new ArrayList<Ladrillo>(1);
    protected ArrayList<Enemigo> enemigosColisionables=new ArrayList<Enemigo>(1);
    protected ArrayList<Municion> municionHeroeColisionada=new ArrayList<Municion>(1);
    public ArrayList<Municion> muncionHeroe=new ArrayList<Municion>(1);
    

    protected int counter=0;
    protected boolean stop=false;
    protected int lastCheckPoint=1;
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

    public void update(double delta){

    
        enemigosColisionables.clear();
        muncionHeroe.removeAll(municionHeroeColisionada);
        ladrillosColisionalbes.clear();
        bonusObtenibles.clear();

        // Muevo el mapa, y objetos fijos siempre y cuando mi contador se encuentre por encima de 2, y el stop sea falso
        
        if(fondo.positionY==0){
            this.stop=true;
        }

        if(fondo.positionY>-750 && !Knightmare.boss){
            System.out.println("Entro");
            Knightmare.toggleBoss();
            Knightmare.bossModeMusic();
        }
        

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

        for(Enemigo e: enemigos){
            if(e.estado == Enemigo.estadoEnemigo.DESACTIVADO){
                e.update(delta);
                if(e.hitbox.intersects(this.limites)){
                    e.isVisible=true;
                    e.estado = Enemigo.estadoEnemigo.VIVO;
                }
            }
            
        }
        counter=0;
        }
        counter++;

      
        for(Enemigo e: enemigos){
            if(e.isVisible){
                if(!stop && e.estado==Enemigo.estadoEnemigo.CONGELADO){
                    e.estado=Enemigo.estadoEnemigo.VIVO;
                }
                e.update(delta);
                enemigosColisionables.add(e);
            }
        }

        for(Esfera e: esferas){
            e.update(delta);
        }

        for(Ladrillo l: ladrillosColisionalbes){
            l.update(delta);
            if(l.getBroken() && !l.isPicked){
                System.out.println("Agrega bonus");
                bonusObtenibles.add(l);
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

        for(Ladrillo l:ladrillos){
            l.display(g2);
        }

        for(Esfera e:esferas){
            e.display(g2);
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

    public void addLadrillos(Ladrillo ladrillo){
        ladrillos.add(ladrillo);
    }

    public void addEnemigo(Enemigo enemigo){
        this.enemigos.add(enemigo);
    }

    public void addEsfera(Esfera esfera){
        this.esferas.add(esfera);
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

    public boolean colisionBonus(Rectangle heroeHitbox){
        for(Bonus b: bonusObtenibles){
            if(b.hitbox.intersects(heroeHitbox)){
                b.setPicked(true);
                return true;
            } 
        }
        return false;
    }

    public void stop(){
        stop=true;
    }

    public void reLoadStaticObjetcs(){
        int movimiento; //Variable para guardar cuanto se movio el mapa desde que arranco el nivel
        this.stop=false;    // Para que el mapa vuelva a moverse
        
        // Calculo cuanto se movio el mapa desde el ultimo checkpoint, en caso de que no se haya llegado, se restaura por defecto.
        if(lastCheckPoint!=1){
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
        for(Ladrillo l: ladrillos){
            l.positionY=l.positionY-movimiento;
            l.restore();
        }

        try {
            raf.seek(pos);
            raf2.seek(pos2);
            for(Enemigo e: enemigos){
                e.setX(raf.readDouble());

                if(lastCheckPoint==1){
                    e.setY(raf.readDouble());
                }
                else
                {
                    e.setY(raf.readDouble()+(5450+lastCheckPoint));
                }
                System.out.println("Posicionado en Y: "+e.getY());
                System.out.println("El hitbox quedo en:"+ e.hitbox);
                System.out.println("Ultimo checkpoint es:"+lastCheckPoint);
                e.restaurar();
                System.out.println("El hitbox restaurado en:"+ e.hitbox);

                System.out.println("Contiene el punto ? : "+e.hitbox.x+","+e.hitbox.y);
                if(limites.contains(e.hitbox.x,e.hitbox.y)){
                    System.out.println("Si, lo tiene");
                    enemigosRemover.add(e);
                    pos+=16;
                }
                else{
                    System.out.println("No lo tiene");
                }
            }

            for(Esfera e: esferas){
                e.setX(raf.readDouble());

                if(lastCheckPoint==1){
                    e.setY(raf.readDouble());
                }
                else
                {
                    e.setY(raf.readDouble()+(5450+lastCheckPoint));
                }
                System.out.println("Posicionado en Y: "+e.getY());
                System.out.println("El hitbox quedo en:"+ e.hitbox);
                System.out.println("Ultimo checkpoint es:"+lastCheckPoint);

                System.out.println("El hitbox restaurado en:"+ e.hitbox);

                System.out.println("Contiene el punto ? : "+e.hitbox.x+","+e.hitbox.y);
                if(limites.contains(e.hitbox.x,e.hitbox.y)){
                    System.out.println("Si, lo tiene");
                    esferasRemover.add(e);
                    pos+=16;
                }
                else{
                    System.out.println("No lo tiene");
                }

        }
    
        
        } catch (IOException e1) {
            
            e1.printStackTrace();
        }

        esferas.removeAll(esferasRemover);
        enemigos.removeAll(enemigosRemover);
        esferasRemover.clear();
        enemigosRemover.clear();
                
    }


    protected void caballo(){
        for(Enemigo e: enemigosColisionables){
            e.estado=Enemigo.estadoEnemigo.MUERTO;
        }
    }

    protected void rey(){
        for(Enemigo e: enemigosColisionables){
            e.estado=Enemigo.estadoEnemigo.CONGELADO;
        }
        this.stop=true;
        Knightmare.juego.bonus();
    }

    

    public boolean colisionMunicion(Municion municion){

        for(Enemigo e: enemigosColisionables){
            if(e.hitbox.intersects(municion.hitboxMunicion)){
                e.estado=Enemigo.estadoEnemigo.MUERTO;
                return true;
            } 
        }
        
        for(Ladrillo l: ladrillosColisionalbes){
            if(!l.getBroken()){
                if(l.hitbox.intersects(municion.hitboxMunicion)){
                    l.hit();
                    return true;
                }  
            }
            
        }

        for(Esfera e: esferas){
            if(e.hitbox.intersects(municion.hitboxMunicion)){
                e.golpeado=true;
                return true;
            } 
        }


        return false;

        
    }
    
    
    
    




    
}
