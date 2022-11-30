package jugador;

public class Posicion {
	private double posicionX;
	private double posicionY;
	
	public Posicion(double posicionX, double posicionY) {
		if(posicionX < 0 || posicionY < 0) {
			this.posicionX = 0;
			this.posicionY = 0;
			return;
		}
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	//Devuelve la componente X de la posicion
	public double getX() {
		return posicionX;
	}
	
	//Devuelve la componente Y de la posicion
	public double getY() {
		return posicionY;
	}

	//Devuelve la posicion actual.
	public Posicion get() {
		return new Posicion(this.getX(),this.getY());
	}
	
	//Setea la componente X si es positiva.
	public void setX(double posicionX) {
		if(posicionX < 0) {
			return;
		}
		this.posicionX = posicionX;
	}
	
	//Setea la componente Y si es positiva.
	public void setY(double posicionY) {
		if(posicionY < 0) {
			return;
		}
		this.posicionY = posicionY;
	}
	
	//Setea las posiciones X e Y si son positivas.
	public void set(double posicionX, double posicionY) {
		if(posicionX < 0 || posicionY < 0) {
			return;
		}
		else {
			this.setX(posicionX);
			this.setY(posicionY);
		}
	}
	
	//Devuelve true si la posición recibida por parámetro es igual que la actual.
	public boolean esPosicionIgual(Posicion pos2) {
		if(this.posicionX == pos2.posicionX && this.posicionY == pos2.posicionY) {
			return true;
		}
		return false;
	}
	
	//Para que las interacciones concuerden con la posicion de la animacion.
	//(int) corta todo lo que viene detras del punto. (int)3.6666 --> 3.0.
	//round lo lleva al entero mas cercano. Math.round(3.666) --> 4.0.
	public static Posicion redondear(Posicion pos) {
		//var decimalX = Math.abs(pos.getX() - ((int)pos.getX()));
		var nuevaX = (int)pos.getX();
		//var decimalY = Math.abs(pos.getY() - ((int)pos.getY()));
		var nuevaY = (int)pos.getY();

		Posicion nueva = new Posicion(nuevaX, nuevaY);
		return nueva;
	}
	
	
	
	
}
