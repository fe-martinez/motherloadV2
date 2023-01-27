package jugador;

import terreno.Entidad;
import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.TipoEntidad;

public class Interacciones {
	private Jugador pj;
	private Suelo suelo;
	private PisoSuperior tiendas;
	
	public Interacciones(Jugador pj, Suelo suelo, PisoSuperior tiendas) {
		this.pj = pj;
		this.suelo = suelo;
		this.tiendas = tiendas;
	}
	
	//Calcula del daño que recibe el jugador según la altura que recibe del mismo.
	public int calcularDanio(double velY) {
		return (int)(velY * 10);
	}
	
	//Permite al Jugador taladrar y ver si debe recolectar un Mineral o no.
	private boolean taladrar(Posicion pos) {
		boolean destruido = false;
		var buscada = Posicion.redondear(new Posicion(pj.getX(), pj.getY()));
		
		if(suelo.casilleroVacio(buscada) || suelo.getBloque(buscada).getBloqueID() == 'T') {
			destruido = suelo.destruirBloque(buscada);
			return destruido;
		}
		
		if(this.tiendas != null) {
			if(pj.getY() == 0 && tiendas.colisionEntidad(buscada) != null) {
				return false;
			}
		}
		
		pj.observarBloque(suelo.getBloque(buscada));
		destruido = suelo.destruirBloque(buscada);
		return destruido;
	}
	
	
	public boolean puedeCaer() {
		if(pj.getY() < pj.getLimiteAlto()){
			Posicion debajo = Posicion.redondear(new Posicion(pj.getX(), pj.getY() + 1));
			if(suelo.casilleroVacio(debajo) && pj.getY() < pj.getLimiteAlto() - 2) {
				return true;
			}
		}
		return false;
	}
	
	//Devuelve true si el personaje coincide con una tienda, false en caso contrario
	public boolean personajeCoincideConTienda() {
		if((int)pj.getY() == 8 && this.tiendas != null && tiendas.getTiendaPos((int)pj.getX()) != null) {
			return (tiendas.colisionEntidad(pj.getPosicion()).getTipoEntidad() == TipoEntidad.TIENDA);
		}
		return false;
	}
	//Devuelve una tienda si el personaje coincide con una tienda y false en caso contrario.
	public Entidad chequearTienda() {
		if(personajeCoincideConTienda()) {
				return tiendas.colisionEntidad(pj.getPosicion());
		}
		return null;
	}
	
	public boolean chequearBloques() {
		boolean destruido = taladrar(pj.getPosicion());
		return destruido;
	}
			
	public boolean chocaDireccionVertical(int direccion) {
		Posicion casillero = Posicion.redondear(new Posicion(pj.getX(), pj.getY() + direccion));
		if(suelo.casilleroVacio(casillero)) {
				return false;
		}
		return true;
	}
		
	public boolean chocaDireccionHorizontal(int direccion) {
		Posicion casillero = Posicion.redondear(new Posicion(pj.getX() + direccion, pj.getY()));
		if(suelo.casilleroVacio(casillero)) {
				return false;
		}
		return true;
	}
		
	
	public boolean chequearColisionVertical() {
		if(pj.getVelY() > 0 && chocaDireccionVertical(1)) {
			return true;
		} else if(pj.getVelY() < 0 && chocaDireccionVertical(-1)) {
			return true;
		}	
		return false;
	}

	public boolean chequearColisionHorizontal() {
		if(pj.getVelX() > 0 && chocaDireccionHorizontal(1)) {
			return true;
		} else if(pj.getVelX() < 0 && chocaDireccionHorizontal(-1)) {
			return true;
		}	
		return false;
		}

}
