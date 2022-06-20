import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics2D;


public abstract class Personaje extends ObjetoGrafico implements Movimiento {
    
    protected int spriteCounter=0; // Variable para realizar la animacion de cada Personaje
    protected int spritePosition; // Para cambiar el archivo de la imagen de cada Personaje
    protected Rectangle hitbox; //Cada personaje va a tener una caja interna la cual detecta si es colisionado por una Municion.
    protected Arma arma; //Para poder disparar, cada personaje va a contener un Arma
    protected boolean canShot;

    public Personaje(String filename){
        super(filename);
    
    }
  
    public void setImagen(BufferedImage img){
        this.imagen=img;
    }

    public abstract void display(Graphics2D g2);
    public abstract void update(double delta);

    protected void updateHitBox() {
        hitbox.x = (int) this.getX();
        hitbox.y = (int) this.getY();
    }

    protected void updateArma(int x, int y){
        arma.setPosition(x, y);
    }

    public void disparar() {
        if(canShot){
            arma.disparo();
        }
    }

    


}
