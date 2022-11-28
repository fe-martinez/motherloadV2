package jugador;

import algo3.motherloadV2.VistaJuego;
import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.TipoEntidad;

public class Interacciones {
	public static final double MAX_TICKS = 15;
	
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
		var buscada = Posicion.redondear(new Posicion(pj.getX(), pj.getY()));
		
		if(suelo.casilleroVacio(buscada) || suelo.getBloque(buscada).getBloqueID() == 'T') {
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
	}
	
	public boolean chequearBloques() {
		taladrar(pj.getPosicion());
		
		if((int)pj.getY() == 8 && this.tiendas != null && tiendas.getTiendaPos((int)pj.getX()) != null) {
			if(tiendas.colisionEntidad(pj.getPosicion()).getTipoEntidad() == TipoEntidad.TIENDA) {
				tiendas.colisionEntidad(pj.getPosicion()).interactuar(pj);
			}
		}
		
		return false;
	}
	
	//Devuelve true si choca con algo arriba o false si no se choca con nada.
		private boolean chocaArriba() {
			if(pj.getY() == 0) {
				return false;
			}
			Posicion arriba = Posicion.redondear(new Posicion(pj.getX(), pj.getY() - 1));
			if(suelo.casilleroVacio(arriba)) {
					return false;
			}

			return true;
		}
		
		private boolean chocaDireccionVertical(int direccion) {
			if(pj.getY() == 0) {
				return false;
			}
			Posicion arriba = Posicion.redondear(new Posicion(pj.getX(), pj.getY() + direccion));
			if(suelo.casilleroVacio(arriba)) {
					return false;
			}

			return true;
		}
		
		private boolean chocaDireccionHorizontal(int direccion) {
			Posicion casillero = Posicion.redondear(new Posicion(pj.getX() + direccion, pj.getY()));
			if(suelo.casilleroVacio(casillero)) {
					return false;
			}
			return true;
		}
		
		//La idea general de las colisiones. EL modelo del pj se dibuja desde la esquina superior izquierda
		//pj.getX() * GRILLA_ANCHO es el pixel donde arranca a dibujarse pj. Si le sumamos PJ_ANCHO, nos da el ultimo pixel.
		//Tomando la parte entera de pj.getX() y la parte entera de la division del ultimo pixel y el ancho de la grilla
		//nos da sobre que casilleros de la matriz esta parado el dibujo del pj (puede ser 1 o maximo 2 casilleros a la vez).
		//esta funcion es para ver si puede caer, pero el concepto es ese. si el modelo del pj es un poco mas chico en pixeles que
		//el de las casillas siempre va a haber hueco para que pase el jugador.
		public boolean puedePasarEntreCasillas() {
			var limiteDerecho = (pj.getX() * VistaJuego.GRILLA_ANCHO) + VistaJuego.GRILLA_PJ_ANCHO;
			var casillaLimiteDerecho = (int)(limiteDerecho/VistaJuego.GRILLA_ANCHO);
			System.out.println((int)pj.getX() + "--" + casillaLimiteDerecho);
			
			Posicion izquierda = new Posicion((int)pj.getX(), (int)pj.getY());
			Posicion derecha = new Posicion(casillaLimiteDerecho, (int)pj.getY());
			
			if(suelo.casilleroVacio(izquierda) && suelo.casilleroVacio(derecha)) {
					return true;
			}
			return false;
		}
		
		public boolean chequearColision(double ticks) {
			if(chocaArriba() && pj.getVelY() < 0) {
				return true;
			}
			if(pj.getVelY() > 0 && chocaDireccionVertical(1)) {
				if(ticks < MAX_TICKS) {
					return true;
				}
				return false;
				
			} if(pj.getVelX() > 0 && chocaDireccionHorizontal(1)) {
				if(ticks < MAX_TICKS) {
					return true;
				}
				return false;
			}
			
			if(pj.getVelX() < 0 && chocaDireccionHorizontal(-1)) {
				if(ticks < MAX_TICKS) {
					return true;
				}
				return false;
			}
			
			
			
			
			
			return false;
		}
	
}
