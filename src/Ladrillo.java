public class Ladrillo extends Bonus {

    private boolean isPicked=false;
    private boolean isBroken=false;
    private boolean isVisible=false;

    public Ladrillo(String filename, int dureza){
        super(filename);
        this.dureza=dureza;
    }
    
}
