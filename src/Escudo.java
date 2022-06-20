import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Escudo extends ObjetoGrafico{

    private boolean isHitted=false;
    private int resistencia;
    private Rectangle hitbox;

    public Escudo(String filename, int x, int y) {
        super(filename);
        this.resistencia=12;
        this.isVisible=false;
        this.positionX=x;
        this.positionY=y;
        hitbox=new Rectangle(x,y,45,25);
    }

    public void update(){
        
        if(isHitted){
            FXPlayer.ESCUDO.play(-5.0f);
            resistencia--;
            isHitted=false;
        }
        updateHitbox();

        if(resistencia==8){
            try {
                imagen = ImageIO.read(getClass().getResource("imagenes/escudoAmarillo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        
            return;
        }

        if(resistencia==3){
            try {
                imagen = ImageIO.read(getClass().getResource("imagenes/escudoRojo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        
            return;
        }



        if(resistencia==0){
            this.isVisible=false;
            try {
                imagen = ImageIO.read(getClass().getResource("imagenes/escudoCeleste.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void display(Graphics2D g2){
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 45, 25, null, null);
        // g2.draw(hitbox);
    }

    public boolean isVisible(){
        return isVisible;
    }

    public void toggleEscudo(){
        this.isVisible=true;
    }

    protected void updateHitbox(){
        this.hitbox.x=(int)this.positionX;
        this.hitbox.y=(int)this.positionY;
    }

    public void hit(){
        isHitted=true;
    }

    public Rectangle getHitbox(){
        return this.hitbox;
    }
    
    
}
