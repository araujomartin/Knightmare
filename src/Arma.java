public class Arma {

    private int cadencia = 1;
    private int x;
    private int y;

    public enum tipoMunicion {
        FLECHA,
        FLECHA_INCENDIARIA,
        BOOMERANG,
        ESPADAS,
        ENEMIGA
    }

    private tipoMunicion municionActual;

    public Arma(tipoMunicion municionActual, int x, int y) {
        this.municionActual = municionActual;
        this.x = x;
        this.y = y;
    }

    public void aumentarCadencia() {
        if (cadencia <= 2) {
            cadencia++;
        }
    }

    public void resetCadencia() {
        cadencia = 1;
    }

    public int getCadencia() {
        return cadencia;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void disparo() {
        if (municionActual == tipoMunicion.FLECHA) {
            Escenario.get_nivel().muncionHeroe.add(new Flecha("imagenes/flecha.png", this.x, this.y, this.cadencia));
            try {
                Knightmare.juego.reproducir.playEffect("sonidos/flecha.wav");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
