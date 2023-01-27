package jugador;

public class AccionMovimiento implements Accion{
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
		}
		if(this.dy != 0) {
			this.pj.setVelY(dy);
		}

		return true;
	}
	
	public TipoMovimiento tipoMovimiento() {
		if(this.dy < 0) {
			return TipoMovimiento.ARRIBA;
		} else if(this.dy > 0) {
			return TipoMovimiento.ABAJO;
		} else if(this.dx < 0) {
			return TipoMovimiento.IZQUIERDA;
		} else {
			return TipoMovimiento.DERECHA;
		}
	}
	
}
