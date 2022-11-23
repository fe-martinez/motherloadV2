package jugador;

import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.Tierra;
import terreno.TipoEntidad;

public class AccionMovimiento implements Accion{
	private static final double GASTO_COMBUSTIBLE_MOVIMIENTO = 0.002;
	private Jugador pj;
	private double dx;
	private double dy;
	
	public AccionMovimiento(Jugador pj, double dx, double dy) {
		if(pj == null) {
			//throw an exception
		}
		this.pj = pj;
		this.dx = dx;
		this.dy = dy;
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
		
		if(this.dx != 0) {
			this.pj.setVelX(dx);
		} else if(this.dx == 0) {
			this.pj.setVelX(0);
		}
		
		if(this.dy != 0) {
			this.pj.setVelY(dy);
		} else if(this.dy == 0) {
			this.pj.setVelY(0);
		}
		
		this.pj.getNave().gastarCombustible(GASTO_COMBUSTIBLE_MOVIMIENTO);
		return true;
	}
}
