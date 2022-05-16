public class Arma {

private int cadencia=1;
Municion bala; 


public void aumentarCadencia(){
    if(cadencia<=2){
        cadencia++;
    }
}

public void resetCadencia(){
    cadencia=1;
}

public int getCadencia(){
    return cadencia;
}

public void setMunicion(Municion m){
    this.bala=m;
}

  //Esto es un comnentario
}
