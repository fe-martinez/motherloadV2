package jugador;

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


	
	//Calcula el daño según la altura desde la que cae.
	private int calcularDanio(int altura) {
		return (int)(altura * 0.2);
	}
	
	//Permite al Jugador taladrar y ver si debe recolectar un Mineral o no.
	private void taladrar(Posicion pos) {
		var buscada = new Posicion(Posicion.redondear(pj.getX()), Posicion.redondear(pj.getY()));
		
		if(suelo.casilleroVacio(buscada) || suelo.getBloque(buscada).getBloqueID() == 'T') {
			System.out.println("Es tierra o aire " + buscada.getX() + buscada.getY());
			suelo.destruirBloque(buscada);
			return;
		}
		
		if(this.tiendas != null) {
			if(pj.getY() == 0 && tiendas.colisionEntidad(buscada).getTipoEntidad() == TipoEntidad.TIENDA) {
				return;
			}
		}
		
		pj.observarBloque(suelo.getBloque(buscada));
		suelo.destruirBloque(buscada);
		System.out.println("Se lee el bloque de la posicion " + buscada.getX() + "///" + buscada.getY());
	}
	
	public boolean chequear() {
		taladrar(pj.getPosicion());
		
		if((int)pj.getY() == 0 && this.tiendas != null && tiendas.getTiendaPos((int)pj.getX()) != null) {
			if(tiendas.colisionEntidad(pj.getPosicion()).getTipoEntidad() == TipoEntidad.TIENDA) {
				tiendas.colisionEntidad(pj.getPosicion()).interactuar(pj);
			}
		}
		
		return false;
	}
	
}
