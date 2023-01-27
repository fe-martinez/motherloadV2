package estados;

import java.util.ArrayList;

import jugador.Accion;
import jugador.Interacciones;
import jugador.Jugador;
import tp.Juego;

public class TaladrandoAbajo implements Estado {
	private double ticks;

	public TaladrandoAbajo() {
		this.ticks = 0;
	}

	@Override
	public Estado update(ArrayList<Accion> acciones, Jugador pj, Interacciones interacciones) {
		
		pj.setTipoAnimacion(1);
		pj.gastarCombustible(Juego.GASTO_COMBUSTIBLE_MOVIMIENTO);
		ticks += 1;
		if(ticks > Juego.MAX_TICKS) {
				pj.setY(pj.getY() + 1);
				if(interacciones.chequearBloques()) {
					pj.setVelX(0);
					pj.setVelY(0);				
				}
				return new Inicial();
		}
		return null;
	}

}
