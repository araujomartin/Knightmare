
public abstract class Enemigo extends Personaje{

    protected boolean isHited=false;
    protected int puntosAlMorir;
    protected int timer=0;

    public enum estadoEnemigo{
        VIVO,
        MUERTO,
        CONGELADO,
        DESACTIVADO,
    }
    
    protected estadoEnemigo estado;

    public Enemigo(String filename, boolean shoot){
        super(filename);
        this.estado = estadoEnemigo.DESACTIVADO;
        isVisible=false;
        this.canShot=shoot;
    }

    public abstract void restaurar();

    public void desactivado(){
        this.positionY++;
        this.hitbox.y++;

        System.out.println(this+" y: "+hitbox.y);
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
                FXPlayer.FUEGO.play(-5.0f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (timer > 3) {
        timer=0;
        this.isVisible=false;
        Knightmare.sumarScore(this.puntosAlMorir);
        }
        timer++;
        this.update(delta);
        

    }

    public void cambiar(){

    }

  
    
    
    

    
}
