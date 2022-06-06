import java.awt.*;

public class Fondo extends ObjetoGrafico {

    public Fondo(String filename) {
        super(filename);
        setPosition(0, -5450);
        //setPosition(0, -750);
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 815, 5950, null, null);
    }

}