import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics2D;


public abstract class Personaje extends ObjetoGrafico implements Movimiento {
    
    protected int spriteCounter=0; // Variable para realizar la animacion de cada Personaje
    protected int spritePosition; // Para cambiar el archivo de la imagen de cada Personaje
    protected Rectangle hitbox; //Cada personaje va a tener una caja interna la cual detecta si es colisionado por una Municion.
    protected Arma arma; //Para poder disparar, cada personaje va a contener un Arma
    protected double velocidad; //Velocidad
    protected boolean canShot;

    public Personaje(String filename){
        super(filename);
    
    }

    public abstract void disparar(); //todos los personajes pueden disparar
    
    public void setImagen(BufferedImage img){
        this.imagen=img;
    }

    public void display(Graphics2D g2){
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);
        g2.draw(hitbox);
    }

    protected void updateHitBox() {
        hitbox.x = (int) this.getX();
        hitbox.y = (int) this.getY();
    }

    protected void updateArma(int x, int y){
        arma.setPosition(x, y);
    }

    


}
