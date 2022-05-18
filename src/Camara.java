public class Camara {

	private double x,y;
	private double resX,resY;

    public Camara(double x,double y) {
    	this.x=x;
    	this.y=y;
    }

	public void seguirPersonaje(Popolon obj){
		// this.y = -obj.getY()+resY/2;
		// if (this.y>0){
		// 		this.y=0;
		// }
        
		// Escenario nivel=Escenario.get_nivel();

		this.y = -obj.getY()+resY/2;
		if (this.y>0){
				this.y=0;
		}

		if(this.y < -(Escenario.ALTO-resY)){
		this.y = -(Escenario.ALTO-resY);
		}
		
		// if(this.y < -(nivel.getHeight()-resY)){
		// 	this.y = -(nivel.getHeight()-resY);
		// }
	}

	public void seguirLimite(){
		this.y=-Escenario.get_nivel().getLimites().getY(); 
	}
	
	public void setViewPort(double x,double y){
		setRegionVisible(x,y);
	}
	public void setRegionVisible(double x,double y){
		resX=x;
		resY=y;
	}
    public void setX(double x){
    	this.x=x;

    }
     public void setY(double y){
    	this.y=y;

    }
    public double getX(){
    	return this.x;

    }
     public double getY(){
    	return this.y;

    }


}