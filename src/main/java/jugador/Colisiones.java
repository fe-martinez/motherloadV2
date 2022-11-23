package jugador;

import terreno.PisoSuperior;
import terreno.Suelo;

public class Colisiones {
	private Suelo suelo;
	private PisoSuperior tiendas;
	private Jugador pj;
	
	public Colisiones(Suelo suelo, PisoSuperior tiendas, Jugador pj) {
		this.suelo = suelo;
		this.tiendas = tiendas;
		this.pj = pj;
	}
	
	
	//Devuelve true si choca con algo arriba o false si no se choca con nada.
	private boolean chocaArriba() {
		if(pj.getY() == 0) {
			return false;
		}
		Posicion arriba = new Posicion(Posicion.redondear(pj.getX()), Posicion.redondear(pj.getY()) - 1);
		if(suelo.casilleroVacio(arriba)) {
				return false;
		}

		return true;
	}
	
	public boolean chequear() {
		if(chocaArriba() && pj.getVelY() < 0) {
			return true;
		}
		return false;
	}
	
}
