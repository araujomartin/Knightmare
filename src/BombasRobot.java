import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class BombasRobot extends Enemigo {


    public BombasRobot(String filename, int x, int y, boolean shot) {
        super(filename, shot);
        this.hitbox = new Rectangle((int) x, (int) y, 40, 40);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.puntosAlMorir=10;
        
    }

    @Override
    public void update(double delta) {

        if (estado == estadoEnemigo.VIVO) {
            this.positionY++; // Se mueven 1 posicion para abajo
            this.updateHitBox();
            try {
                spriteCounter++;
                if (spriteCounter > 10) {

                    if (spritePosition == 2)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 2;

                    imagen = ImageIO.read(getClass().getResource("imagenes/" + spritePosition + ".png"));
                    spriteCounter = 0;
                }

            } catch (IOException e) {
                System.out.println(e);
            }

            return;
        }

        if (estado == estadoEnemigo.MUERTO) {
            try {

                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 2)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 2;

                    imagen = ImageIO.read(getClass().getResource("imagenes/fuego" + spritePosition + ".png"));
                    spriteCounter = 0;
                    if (isVisible == true) {
                        this.morir(delta);
                    }
                    
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;
        }

        if(estado == estadoEnemigo.DESACTIVADO){
            this.desactivado();
            
        }

    }

    @Override
    public void disparar() {

    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 40, 40, null, null);
        g2.draw(hitbox);
    }

}
