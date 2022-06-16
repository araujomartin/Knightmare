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


    protected ArrayList<Bonus> bonusObtenibles=new ArrayList<Bonus>(1);
    protected ArrayList<Esfera> esferasColisionables= new ArrayList<Esfera>(1);
    protected ArrayList<Ladrillo> ladrillosColisionalbes= new ArrayList<Ladrillo>(1);
    protected ArrayList<Enemigo> enemigosColisionables=new ArrayList<Enemigo>(1);
    protected ArrayList<Municion> municionEnemigaColisionada =new ArrayList<Municion>(1);
    protected ArrayList<Municion> municionHeroeColisionada=new ArrayList<Municion>(1);
    protected ArrayList<Municion> municionEnemiga=new ArrayList<Municion>(1);
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

        esferasColisionables.clear();
        enemigosColisionables.clear();
        ladrillosColisionalbes.clear();
        muncionHeroe.removeAll(municionHeroeColisionada);
        bonusObtenibles.clear();
        municionEnemigaColisionada.clear();


        // Muevo el mapa, y objetos fijos siempre y cuando mi contador se encuentre por encima de 2, y el stop sea falso
        
        if(fondo.positionY==0){
            this.stop=true;
        }

        if(fondo.positionY>-750 && !Knightmare.boss){
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

        for(Esfera esfera: esferas){
            if(!esfera.activado){
                esfera.positionY++;
                esfera.updateHitbox();
                if(Escenario.get_nivel().limites.intersects(esfera.hitbox)){
                    esfera.setVisible(true);
                    esfera.activado=true;
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

        for(Esfera esfera: esferas){
            if(esfera.isVisible){
                esfera.update(delta);
                esferasColisionables.add(esfera);
                if(!esfera.isPicked && this.limites.contains(esfera.hitbox)){
                    bonusObtenibles.add(esfera);
                }
            }     
        }
 

        for(Ladrillo l: ladrillosColisionalbes){
            l.update(delta);
            if(l.getBroken() && !l.isPicked){
                bonusObtenibles.add(l);
            }
        }
        
        for(Municion m: muncionHeroe){
            this.colisionMunicion(m); 
            m.update(delta);
            if(!m.isVisible){
                this.municionHeroeColisionada.add(m);
            }
           
    
        }
        for(Municion m: municionEnemiga){
                m.update(delta);
            
            if(!m.isVisible){
             municionEnemigaColisionada.add(m);
            }
               
        
        }

        municionEnemiga.removeAll(municionEnemigaColisionada);

        
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

        for(Esfera e:esferasColisionables){
            e.display(g2);
        }
        
        for(Municion m: muncionHeroe){
                m.display(g2);  
        }

        for(Enemigo e: enemigosColisionables){
            e.display(g2);
        }

        for(Municion m: municionEnemiga){
            m.display(g2);
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

    public boolean colisionMunicionEnemiga(Popolon popolon){
        for(Municion municion: municionEnemiga){
            if(popolon.escudo.isVisible() && popolon.escudo.getHitbox().intersects(municion.hitboxMunicion)){
                System.out.println("entro");
                popolon.escudo.hit();
                municion.hitEnemigo();
            }
            if(popolon.hitbox.intersects(municion.hitboxMunicion)){
                municion.hitEnemigo();
                return true;
            } 
        }
        return false; 
    }
    
    public boolean colisionEnemigo(Rectangle heroeHitbox){
        for(Enemigo enemigo: enemigosColisionables){
            if(enemigo.hitbox.intersects(heroeHitbox)){
                if(Popolon.popolon.getEstado()==Popolon.estados.ROJO){
                    enemigo.estado=Enemigo.estadoEnemigo.MUERTO;
                }
                return true;
            } 
        }   
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

    public boolean colisionMunicion(Municion municion){

        for(Enemigo e: enemigosColisionables){
            if(e.hitbox.intersects(municion.hitboxMunicion)){
                municion.hitEnemigo();
                e.estado=Enemigo.estadoEnemigo.MUERTO;
                return true;
            } 
        }
        
        for(Ladrillo l: ladrillosColisionalbes){
            if(!l.getBroken()){
                if(l.hitbox.intersects(municion.hitboxMunicion)){
                    municion.hitBonus();
                    l.hit();
                    return true;
                }  
            }
            
        }

        for(Esfera e: esferasColisionables){
            if(e.hitbox.intersects(municion.hitboxMunicion)){
                municion.hitBonus();
                e.golpeado=true;
                return true;
            } 
        }


        return false;

        
    }

    public void stop(){
        stop=true;
    }

    public void reCargarObjetos(){
        ArrayList<Esfera> esferasRemover=new ArrayList<Esfera>(1);
        ArrayList<Enemigo> enemigosRemover=new ArrayList<Enemigo>(1);
        this.municionEnemiga.clear();
        this.muncionHeroe.clear();

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
                e.restaurar();
                if(limites.contains(e.hitbox.x,e.hitbox.y) || e.hitbox.y>=580){
                    enemigosRemover.add(e);
                    pos+=16;
                }
            }

            for(Esfera e: esferas){
                e.setX(raf2.readDouble());

                if(lastCheckPoint==1){
                    e.setY(raf2.readDouble());
                }
                else
                {
                    e.setY(raf2.readDouble()+(5450+lastCheckPoint));
                }
                e.restaurar();
                if(limites.contains(e.hitbox.x,e.hitbox.y) || e.hitbox.y>=580){
                    esferasRemover.add(e);
                    pos2+=16;
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

    

   
    
    
    
    




    
}
