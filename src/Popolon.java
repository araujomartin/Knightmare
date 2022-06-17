import java.awt.*; //imagenes
import java.io.IOException;
import javax.imageio.*; //imagenes

public class Popolon extends Personaje {

    private estados estadoActual;
    public static Popolon popolon;
    public Escudo escudo;
    private boolean escudoActivado=false;
    private static int powerVelocidad=0;
    private boolean animacion=false;


    
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
        hitbox = new Rectangle((int) this.positionX, (int) this.positionY, 45, 45); // Tamaño total de la imagen
        canShot=true;
        popolon=this;
        arma=new Arma(Arma.tipoMunicion.FLECHA, 0, 0);
        escudo=new Escudo("imagenes/escudoCeleste.png", 0, 0);
    }

    public void display(Graphics2D g2){
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 45, 45, null, null);
        g2.draw(hitbox);

        if(escudo.isVisible()){
           escudo.display(g2);
        }
        
        
    }


    public void update(double delta) {

        
        if (animacion){
            if(this.positionY<65){
                this.animacion=false;
                return;
            }
            if((int)this.positionX==400){
                this.positionY--;
            }
            else if(this.positionX<=400){
                this.positionX+=1;
            }else if(this.positionX>=400){
                this.positionX-=1;
            }
            if(escudo.isVisible() && escudoActivado){
                updateEscudo();
            }
            
            try {
                spriteCounter++;
                if (spriteCounter > 10) { 

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/"+Knightmare.juego.PERSONAJE+ spritePosition + ".png"));
                    spriteCounter = 0; 
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
        
        else
        
        {
            updateHitBox(); // Primero que nada actualizar el hitbox
            updateArma((int)this.positionX+15, (int)this.positionY);// Actualizar la posicion del arma
            Escenario.get_nivel().colisionBonus(this.hitbox);
            if(estadoActual != estados.MURIENDO && estadoActual == estados.VIVO){
                if(Escenario.get_nivel().colisionMunicionEnemiga(this)){
                    this.cambiar(estados.MURIENDO);
                }
            }
    
            
            
            if (estadoActual == estados.VIVO) {
                if(escudo.isVisible() && escudoActivado){
                    updateEscudo();
                }
                try {
                    spriteCounter++;
                    if (spriteCounter > 10) { // Pongo un contador en 10, para que la transicion de la imagen no sea tan
                                              // rapida (60 FPS)
    
                        if (spritePosition == 0)
                            spritePosition = 1;
                        else if (spritePosition == 1)
                            spritePosition = 0;
    
                        imagen = ImageIO.read(getClass().getResource("imagenes/"+Knightmare.juego.PERSONAJE+ spritePosition + ".png"));
                        spriteCounter = 0; // Cuando cambio la imagen reseteo el contador
                    }
    
                } catch (IOException e) {
                    System.out.println(e);
                }
    
                if(Escenario.get_nivel().colisionEnemigo(this.hitbox)){
                    estadoActual = estados.MURIENDO;
                    spritePosition=4;
                    spriteCounter=0;
                }
    
                return;
            }
    
            if (estadoActual == estados.ROJO) {
                try {
                    Escenario.get_nivel().colisionEnemigo(this.hitbox);
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
    
            if (estadoActual == estados.INVISIBLE) {
                try {
                    spriteCounter++;
    
                    if (spriteCounter > 10) {
    
                        if (spritePosition == 12)
                            spritePosition = 11;
                        else if (spritePosition == 11)
                            spritePosition = 12;
    
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
    
                    if (spriteCounter > 10 && spritePosition<10) {
    
                        spritePosition++;
                        
                        imagen = ImageIO.read(getClass().getResource("imagenes/popolon" + spritePosition + ".png"));
                        spriteCounter = 0;
                    }
    
                } catch (IOException e) {
                    System.out.println(e);
                }
    
                
    
            }
        }

       
        

    }

   

    public void cambiar(estados nuevo) {

        switch (nuevo) {
            case VIVO:{
                canShot=true;
                if(escudoActivado){
                    this.escudo.isVisible=true;
                }
                spritePosition = 0;
                this.velocidad=150;
            }
                break;
            case ROJO:{
                canShot=false;
                this.escudo.isVisible=false;
                spritePosition = 2;
                this.velocidad=330;
            }
                break;
            case INVISIBLE:{
                canShot=true;
                this.escudo.isVisible=false;
                spritePosition = 11;
            }
                break;
            case MURIENDO:{
                spritePosition= 4;
                this.escudo.isVisible=false;
                this.escudoActivado=false;
            }
                break;
        }

        this.estadoActual = nuevo;

    }

    public void down(double delta) {

        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.hitbox.x, (int) (this.positionY + (velocidad + powerVelocidad) * delta),this.hitbox.width, this.hitbox.height);
        if(hitbox.y+hitbox.getHeight()<500){
            if (!nivel.colisionObstaculo(SiguientePosicion)) {
                positionY = (this.positionY + (velocidad + powerVelocidad) * delta);
            }
        }
        

    }

    public void up(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.hitbox.x, (int) (this.positionY - (velocidad + powerVelocidad) * delta),this.hitbox.width, this.hitbox.height);
       
        if(hitbox.y>80){
            if (!nivel.colisionObstaculo(SiguientePosicion)) {
                positionY = (this.positionY - (velocidad + powerVelocidad) * delta);
            }
        }
        

    }

    public void left(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX - (velocidad + powerVelocidad) * delta),this.hitbox.y,this.hitbox.width,this.hitbox.height);
    
        if(this.hitbox.x>0){
            if (!nivel.colisionObstaculo(SiguientePosicion)) {
                positionX = (this.positionX - (velocidad + powerVelocidad) * delta);
            }  
        }
       

    }

    public void right(double delta){
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX + (velocidad + powerVelocidad) * delta),this.hitbox.y,this.hitbox.width,this.hitbox.height);
        
        if(this.hitbox.x+this.getWidth()<780){
            if (!nivel.colisionObstaculo(SiguientePosicion)) {
                positionX = (this.positionX + (velocidad + powerVelocidad) * delta);
            }
        }

    }

    public estados getEstado(){
        return this.estadoActual;
    }

    public void updateEscudo(){
        escudo.setPosition((int)this.positionX, (int)this.positionY-30);
        escudo.update();
    }

    public static void velocidad(){
        if(powerVelocidad<90){
            powerVelocidad+=30;
        }
    }
    
    public boolean animacion(){
        return this.animacion;
    }

    public void setAnimacion(){
        this.animacion=true;
    }

    public void restaurar(){
        powerVelocidad=0;
        this.cambiar(estados.VIVO);
    }

    public void celeste(){
        this.escudoActivado=true;
        this.escudo.toggleEscudo();
    }

    public void finalizaPower(){

        if(estadoActual == estados.ROJO){
            switch(spritePosition){
                case 0:
                    spritePosition=1;
                break;
                case 1:
                    spritePosition=2;   
                break;
                default:
                    spritePosition=0;
                break;
            }
    }else if(estadoActual == estados.INVISIBLE){
        switch(spritePosition){
            case 0:
                spritePosition=11;
            break;
            case 1:
                spritePosition=12;   
            break;
            default:
                spritePosition=0;
            break;
        } 
    }


    }
}