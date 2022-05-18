import java.awt.*; //imagenes
import java.io.IOException;
import javax.imageio.*; //imagenes

public class Popolon extends Personaje {

    private estados estadoActual;

    public enum estados { // Los estados del personaje son publicos para poder cambiarlo a medida que
                          // transcurre el juego.
        VIVO,
        MURIENDO,
        INVISIBLE,
        ROJO,
    }

    public Popolon(String filename) {
        super(filename);
        estadoActual = estados.VIVO;
        velocidad = 150;
        hitbox = new Rectangle((int) this.positionX, (int) this.positionY, 50, 50); // Tamaño total de la imagen
    }

    protected void updateHitBox() {
        hitbox.x = (int) this.getX();
        hitbox.y = (int) this.getY();
    }

    public void update(double delta) {
        updateHitBox(); // Primero que nada actualizar el hitbox

        // Comparo el estado del personaje.

        if (estadoActual == estados.VIVO) {
            try {
                spriteCounter++;
                if (spriteCounter > 10) { // Pongo un contador en 10, para que la transicion de la imagen no sea tan
                                          // rapida (60 FPS)

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/popolon" + spritePosition + ".png"));
                    spriteCounter = 0; // Cuando cambio la imagen reseteo el contador
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }

        if (estadoActual == estados.ROJO) {
            try {

                spriteCounter++;

                if (spriteCounter > 10) {

                    if (spritePosition == 3)
                        spritePosition = 2;
                    else if (spritePosition == 2)
                        spritePosition = 3;

                    imagen = ImageIO.read(getClass().getResource("imagenes/popolon" + spritePosition + ".png"));
                    spriteCounter = 0;
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }

        if (estadoActual == estados.MURIENDO){
            try {

                spriteCounter++;

                if (spriteCounter > 10) {

                    spritePosition++;
                    if(spritePosition>9){
                        spritePosition=4;
                    }
                    imagen = ImageIO.read(getClass().getResource("imagenes/popolon" + spritePosition + ".png"));
                    spriteCounter = 0;
                }

            } catch (IOException e) {
                System.out.println(e);
            }

            

        }

    }

    public void cambiar(estados nuevo) {

        switch (nuevo) {
            case VIVO:
                spritePosition = 0;
                break;
            case ROJO:
                spritePosition = 2;
                break;
            case INVISIBLE:
                break;
            case MURIENDO:
                spritePosition= 4;
                break;
        }

        this.estadoActual = nuevo;

    }

    public void disparo() {

    }

    public void down(double delta) {

        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.hitbox.x, (int) (this.positionY + velocidad * delta),this.hitbox.width, this.hitbox.height);
    
        if (!nivel.colisionObstaculo(SiguientePosicion)) {
            positionY = (this.positionY + velocidad * delta);
        }

    }

    public void up(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.hitbox.x, (int) (this.positionY - velocidad * delta),this.hitbox.width, this.hitbox.height);
       
        if (!nivel.colisionObstaculo(SiguientePosicion)) {
            positionY = (this.positionY - velocidad * delta);
        }

    }

    public void left(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX - velocidad * delta),this.hitbox.y,this.hitbox.width,this.hitbox.height);
    
    
        if (!nivel.colisionObstaculo(SiguientePosicion)) {
            positionX = (this.positionX - velocidad * delta);
        }

    }

    public void right(double delta){
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX + velocidad * delta),this.hitbox.y,this.hitbox.width,this.hitbox.height);
    
        if (!nivel.colisionObstaculo(SiguientePosicion)) {
            positionX = (this.positionX + velocidad * delta);
        }

    }

}