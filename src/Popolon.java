import java.awt.*; //imagenes
import java.io.IOException;
import javax.imageio.*; //imagenes

public class Popolon extends Personaje {

    private estados estadoActual;
    public static Popolon popolon;

    
    public enum estados { // Los estados del personaje son publicos para poder cambiarlo a medida que transcurre el juego.
        VIVO,
        MURIENDO,
        INVISIBLE,
        ROJO,
    }

    public Popolon(String filename) {
        super(filename);
        estadoActual = estados.VIVO;
        velocidad = 150;
        isVisible=true;
        hitbox = new Rectangle((int) this.positionX, (int) this.positionY, 50, 50); // Tamaño total de la imagen
        canShot=true;
        popolon=this;
        arma=new Arma(Arma.tipoMunicion.FLECHA, 0, 0);

    }

    public void display(Graphics2D g2){
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 50, 50, null, null);
        g2.draw(hitbox);
    }


    public void update(double delta) {
        updateHitBox(); // Primero que nada actualizar el hitbox
        updateArma((int)this.positionX+25, (int)this.positionY); // Actualizar la posicion del arma
        Escenario.get_nivel().colisionBonus(this.hitbox);
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

            // if(Escenario.get_nivel().colisionEnemigo(this.hitbox)){
            //     estadoActual = estados.MURIENDO;
            //     spritePosition=4;
            //     spriteCounter=0;
            // }
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

                if (spriteCounter > 40 && spritePosition<10) {

                    spritePosition++;
                    
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

    public void disparar() {
        if(canShot)
        {
         arma.disparo();
          
        }
        
    }

    public void down(double delta) {

        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.hitbox.x, (int) (this.positionY + velocidad * delta),this.hitbox.width, this.hitbox.height);
        if(hitbox.y+hitbox.getHeight()<500){
            if (!nivel.colisionObstaculo(SiguientePosicion)) {
                positionY = (this.positionY + velocidad * delta);
            }
        }
        

    }

    public void up(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.hitbox.x, (int) (this.positionY - velocidad * delta),this.hitbox.width, this.hitbox.height);
       
        if(hitbox.y>80){
            if (!nivel.colisionObstaculo(SiguientePosicion)) {
                positionY = (this.positionY - velocidad * delta);
            }
        }
        

    }

    public void left(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX - velocidad * delta),this.hitbox.y,this.hitbox.width,this.hitbox.height);
    
        if(this.hitbox.x>0){
            if (!nivel.colisionObstaculo(SiguientePosicion)) {
                positionX = (this.positionX - velocidad * delta);
            }  
        }
       

    }

    public void right(double delta){
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX + velocidad * delta),this.hitbox.y,this.hitbox.width,this.hitbox.height);
    
        if (!nivel.colisionObstaculo(SiguientePosicion)) {
            positionX = (this.positionX + velocidad * delta);
        }

    }

    public estados getEstado(){
        return this.estadoActual;
    }

    

}