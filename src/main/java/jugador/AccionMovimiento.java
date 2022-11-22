package jugador;

import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.Tierra;
import terreno.TipoEntidad;

public class AccionMovimiento implements Accion{
	private static final double GASTO_COMBUSTIBLE_MOVIMIENTO = 0.15;
	private Jugador pj;
	private Suelo suelo;
	private PisoSuperior tiendas;
	private double dx;
	private double dy;
	
	public AccionMovimiento(Jugador pj, Suelo suelo, PisoSuperior tiendas, double dx, double dy) {
		if(pj == null || suelo == null || tiendas == null) {
			//throw an exception
		}
		this.pj = pj;
		this.suelo = suelo;
		this.tiendas = tiendas;
		this.dx = dx;
		this.dy = dy;
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
	
	//Calcula el daño según la altura desde la que cae.
	private int calcularDanio(int altura) {
		return (int)(altura * 0.2);
	}
	
	//Si no hay Tierra o Minerales debajo, el Jugador cae.
	
	
	//Permite al Jugador taladrar y ver si debe recolectar un Mineral o no.
	private void taladrar(Posicion pos) {
		var buscada = new Posicion(Posicion.redondear(pj.getX()), Posicion.redondear(pj.getY()) - 1);
		
		if(suelo.casilleroVacio(buscada) || suelo.getBloque(buscada) instanceof Tierra) {
			return;
		}
		
		if(this.tiendas != null) {
			if(pj.getY() == 0 && tiendas.colisionEntidad(buscada).getTipoEntidad() == TipoEntidad.TIENDA) {
				return;
			}
		}
		pj.observarBloque(suelo.getBloque(buscada));
	}
	
	//Permite aplicar la accion de movimiento al Jugador.
	public boolean aplicar() {		
		Posicion nueva = new Posicion(pj.getX(), pj.getY());
		
		if(nueva.getY() + this.dy < 0 || nueva.getY() + this.dy >= pj.getLimiteAlto()) {
			return false;
		}
		if(nueva.getX() + this.dx < 0 || nueva.getX() + this.dx >= pj.getLimiteAncho()) {
			return false;
		}
		
		
		if(this.dy != 0) {
			if(this.dy > 0 || !chocaArriba()) {
				nueva.setY(this.pj.getY() + dy);
			}
		}
		if(this.dx != 0) {
			nueva.setX(this.pj.getX() + dx);
		}
		
		taladrar(nueva);
		pj.setX(nueva.getX());
		pj.setY(nueva.getY());
		
		if((int)pj.getY() == 0 && this.tiendas != null && tiendas.getTiendaPos((int)pj.getX()) != null) {
			if(tiendas.colisionEntidad(pj.getPosicion()).getTipoEntidad() == TipoEntidad.TIENDA) {
				tiendas.colisionEntidad(pj.getPosicion()).interactuar(pj);
			}
		}
		
		var buscada = new Posicion(Posicion.redondear(pj.getX()), Posicion.redondear(pj.getY()));
		suelo.destruirBloque(buscada);
		
		//if(this.dy >= 0) {
		//	this.pj.getNave().recibirDanio(this.caer());
		//}
		
		this.pj.getNave().gastarCombustible(GASTO_COMBUSTIBLE_MOVIMIENTO);
		return true;
	}
}
