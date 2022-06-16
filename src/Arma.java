public class Arma {

    private int cadencia = 1;
    private int x;
    private int y;

    public enum tipoMunicion {
        FLECHA,
        FLECHA_INCENDIARIA,
        BUMERAN,
        ESPADAS,
        BOLAFUEGO,
        ENEMIGA
    }

    private tipoMunicion municionActual;

    public Arma(tipoMunicion municionActual, int x, int y) {
        this.municionActual = municionActual;
        this.x = x;
        this.y = y;
    }

    public void cambiarMunicion(String municion){
        if(municion==this.municionActual.toString()){
            cadencia=2;
        }
        else{
            cadencia=1;
        }

        switch(municion){
            case "FLECHA":{
                this.municionActual=tipoMunicion.FLECHA;
            }
            
            break;
            case "FLECHA_INCENDIARIA":{
                this.municionActual=tipoMunicion.FLECHA_INCENDIARIA;
            }
            break;
            case "BUMERAN":{
                this.municionActual=tipoMunicion.BUMERAN;
            }
            break;
            case "ESPADAS":{
                this.municionActual=tipoMunicion.ESPADAS;
            }
            break;
            case "BOLAFUEGO":{
                this.municionActual=tipoMunicion.BOLAFUEGO;
            }
            break;
        }
    }

    public void aumentarCadencia() {
       cadencia=2;
    }

    public void resetCadencia() {
        cadencia = 1;
    }

    public int getCadencia() {
        return cadencia;
    }

    public tipoMunicion municionActual(){
        return this.municionActual;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void disparo() {
        switch(cadencia){
            case 1:{
                if (municionActual == tipoMunicion.ENEMIGA){
                    Escenario.get_nivel().municionEnemiga.add(new MunicionEnemiga("imagenes/municionEnemigo.png", this.x, this.y));
                }
                if(Escenario.get_nivel().muncionHeroe.size()<2){

                    if (municionActual == tipoMunicion.FLECHA) {
                        Escenario.get_nivel().muncionHeroe.add(new Flecha("imagenes/flecha.png", this.x, this.y));
                        try {
                            FXPlayer.FLECHA.play(-5.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if(municionActual == tipoMunicion.ESPADAS){
                        Escenario.get_nivel().muncionHeroe.add(new Espada("imagenes/espada.png", this.x, this.y));
                        try {
                            FXPlayer.ESPADA.play(-5.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (municionActual == tipoMunicion.FLECHA_INCENDIARIA) {
                        Escenario.get_nivel().muncionHeroe.add(new FlechaIncendiaria("imagenes/flechaIncendiaria.png", this.x, this.y));
                        try {
                            FXPlayer.FLECHA_FUEGO.play(-5.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (municionActual == tipoMunicion.BUMERAN) {
                        Escenario.get_nivel().muncionHeroe.add(new Bumeran("imagenes/bumeran0.png", this.x, this.y));
                        try {
                            FXPlayer.BUMERAN.loop(-10.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
            }

            if(Escenario.get_nivel().muncionHeroe.size()<3){
                if (municionActual == tipoMunicion.BOLAFUEGO) {
                    Escenario.get_nivel().muncionHeroe.add(new BolaFuego("imagenes/bolaFuego.png", this.x, this.y,"left"));
                    Escenario.get_nivel().muncionHeroe.add(new BolaFuego("imagenes/bolaFuego.png", this.x, this.y,"normal"));
                    Escenario.get_nivel().muncionHeroe.add(new BolaFuego("imagenes/bolaFuego.png", this.x, this.y,"right"));
                    try {
                        FXPlayer.FLECHA_FUEGO.play(-5.0f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }

            }

        }break;
            case 2:{
                if(Escenario.get_nivel().muncionHeroe.size()<3){
                    if (municionActual == tipoMunicion.FLECHA_INCENDIARIA) {
                        Escenario.get_nivel().muncionHeroe.add(new FlechaIncendiaria("imagenes/flechaIncendiaria.png", this.x, this.y));
                        try {
                            FXPlayer.FLECHA_FUEGO.play(-5.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (municionActual == tipoMunicion.BUMERAN) {
                        Escenario.get_nivel().muncionHeroe.add(new Bumeran("imagenes/bumeran0.png", this.x, this.y));
                        try {
                            FXPlayer.BUMERAN.loop(-10.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (municionActual == tipoMunicion.BOLAFUEGO) {
                        Escenario.get_nivel().muncionHeroe.add(new BolaFuego("imagenes/bolaFuego.png", this.x-40, this.y,"normal"));
                        Escenario.get_nivel().muncionHeroe.add(new BolaFuego("imagenes/bolaFuego.png", this.x, this.y-25,"normal"));
                        Escenario.get_nivel().muncionHeroe.add(new BolaFuego("imagenes/bolaFuego.png", this.x+40, this.y,"normal"));
                        try {
                            FXPlayer.FLECHA_FUEGO.play(-5.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                  
                    
                }
                if(Escenario.get_nivel().muncionHeroe.size()<4){

                    if (municionActual == tipoMunicion.FLECHA) {
                        Escenario.get_nivel().muncionHeroe.add(new Flecha("imagenes/flecha.png", this.x-10, this.y));
                        Escenario.get_nivel().muncionHeroe.add(new Flecha("imagenes/flecha.png", this.x+10, this.y));
                        try {
                            FXPlayer.FLECHA.play(-5.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                    if (municionActual == tipoMunicion.ESPADAS) {
                        Escenario.get_nivel().muncionHeroe.add(new Espada("imagenes/flecha.png", this.x-10, this.y));
                        Escenario.get_nivel().muncionHeroe.add(new Espada("imagenes/flecha.png", this.x+10, this.y));
                        try {
                            FXPlayer.FLECHA.play(-5.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
            }
            
            

            }break;
        

        }
       
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
