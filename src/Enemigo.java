import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Enemigo extends Personaje{

    protected boolean isHited=false;
    private int timer=0;

    public enum estadoEnemigo{
        VIVO,
        MUERTO
    }
    
    protected estadoEnemigo estado;

    public Enemigo(String filename, boolean shoot){
        super(filename);
        isVisible=true;
        this.canShot=shoot;
    }

    public void hitted(){
        this.isHited=true;
    }

    public void visible(){
        this.isVisible=false;
    }

    public void morir(double delta){

        if(timer==0){
            try {
                Knightmare.juego.reproducir.playEffect("sonidos/fuego.wav");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (timer > 3) {
        timer=0;
        this.isVisible=false;
        }
        timer++;
        this.update(delta);
        

    }

    
    
    

    
}
