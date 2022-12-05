package estados;

import java.util.ArrayList;
import jugador.Accion;
import jugador.Interacciones;
import jugador.Jugador;
import tp.Juego;

public class TaladrandoIzquierda implements Estado {
	private double ticks;
	public TaladrandoIzquierda() {
		this.ticks = 0;
	}
	@Override
	public Estado update(ArrayList<Accion> acciones, Jugador pj, Interacciones interacciones) {
		//pj.setX(pj.getX() + ((-0.1 * 10) / Juego.MAX_TICKS));
		pj.setTipoAnimacion(3);
		pj.getNave().gastarCombustible(Juego.GASTO_COMBUSTIBLE_MOVIMIENTO);
		ticks += 1;
		if(ticks > Juego.MAX_TICKS) {
			pj.setX(pj.getX() - 1);
			if(interacciones.chequearBloques()) {
				pj.setVelX(0);
				pj.setVelY(0);					
			}
			return new Inicial();
		}
		return null;
	}
}
