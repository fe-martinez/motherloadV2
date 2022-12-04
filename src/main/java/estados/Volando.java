package estados;

import java.util.ArrayList;

import jugador.Accion;
import jugador.Interacciones;
import jugador.Jugador;
import tp.Juego;

public class Volando implements Estado {
	private static final double COEF_REDUCCION_X = 0.002;
	private static final double COEF_REDUCCION_Y = 0.002;
	
	private boolean caer(Jugador pj, Interacciones interacciones) {			
		if(interacciones.puedeCaer()) {
			pj.setVelY(pj.getVelY() + Juego.GRAVEDAD);
			pj.setY(pj.getY() + pj.getVelY());
			return true;
		} else {
			pj.setVelY(0);
		}
		return false;
	}
	
	private void actualizarX(Jugador pj) {
		if(pj.getVelX() > 0) {
			pj.setVelX(pj.getVelX() - COEF_REDUCCION_X);				
		} else if(pj.getVelX() < 0) {
			pj.setVelX(pj.getVelX() + COEF_REDUCCION_X);
		}
		
		if(Math.abs(pj.getVelX()) <= COEF_REDUCCION_X){
			pj.setVelX(0);
		}
		
		pj.setX(pj.getX() + pj.getVelX());
	}
	
	private void actualizarY(Jugador pj) {
		if(pj.getVelY() > 0) {
			pj.setVelY(pj.getVelY() - COEF_REDUCCION_Y);
		} else if(pj.getVelY() < 0) {
			pj.setVelY(pj.getVelY() + COEF_REDUCCION_Y);
		}
		
		if(Math.abs(pj.getVelY()) <= COEF_REDUCCION_Y) {
			pj.setVelY(0);
		}
		pj.setY(pj.getY() + pj.getVelY());
	}

	@Override
	public Estado update(ArrayList<Accion> acciones, Jugador pj, Interacciones interacciones) {
		pj.setTipoAnimacion(4);
		
		if(!interacciones.chequearColisionVertical()) {
				actualizarY(pj);
		} else {
			return new Inicial();
		}
		
		if(!interacciones.chequearColisionHorizontal()) {
			actualizarX(pj);
		}
		
		if(!caer(pj, interacciones)) {
			return new Inicial();
		}

		return null;
	}

}
