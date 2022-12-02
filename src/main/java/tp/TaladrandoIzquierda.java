package tp;

import java.util.ArrayList;
import jugador.Accion;
import jugador.Interacciones;
import jugador.Jugador;

public class TaladrandoIzquierda implements Estado {
	private double ticks;
	public TaladrandoIzquierda() {
		this.ticks = 0;
	}
	@Override
	public Estado update(ArrayList<Accion> acciones, Jugador pj, Interacciones interacciones) {
		pj.setX(pj.getX() + ((-0.1 * 10) / Juego.MAX_TICKS));
		pj.setTipoAnimacion(3);
		
		ticks += 1;
		if(ticks > Juego.MAX_TICKS) {
			if(interacciones.chequearBloques()) {
				pj.setVelX(0);
				pj.setVelY(0);					
			}
			return new Inicial();
		}
		return null;
	}
}
