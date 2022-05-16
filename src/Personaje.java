import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics2D;


public abstract class Personaje extends ObjetoGrafico {
    
    protected int spriteCounter=0; // Variable para realizar la animacion de cada Personaje
    protected int spritePosition; // Para cambiar el archivo de la imagen de cada Personaje
    protected Rectangle hitbox; //Cada personaje va a tener una caja interna la cual detecta si es colisionado por una Municion.
    protected Arma arma; //Para poder disparar, cada personaje va a contener un Arma
    protected double velocidad; //Velocidad

    public Personaje(String filename){
        super(filename);
    }
    
    public abstract void disparo(); //todos los personajes pueden disparar
    public abstract void update(double delta); //todos los personajes se deben actualizar en el bucle del juego
    protected abstract void updateHitBox(); //el hitbox de cada personaje se debe actualizar tambien

    public void setImagen(BufferedImage img){
        this.imagen=img;
    }

    public void setX(double x){
        positionX=x;
    }

    public void setY(double y){
        positionY=y;
    }
    public double getX(){
        return positionX; 
    }

    public double getY(){
        return positionY; 
    }

    public void display(Graphics2D g2){
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 50, 50, null, null);
        g2.draw(hitbox);
    }


}
