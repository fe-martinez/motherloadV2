package estados;

import java.util.ArrayList;

import jugador.Accion;
import jugador.AccionMovimiento;
import jugador.Interacciones;
import jugador.Jugador;
import jugador.TipoMovimiento;
import tp.Juego;

public class Inicial implements Estado{
	private static final double COEF_REDUCCION_X = 0.008;
	private static final double COEF_REDUCCION_Y = 0.002;
	
	
	private void caer(Jugador pj, Interacciones interacciones) {			
		if(interacciones.puedeCaer()) {
			pj.setVelY(pj.getVelY() + Juego.GRAVEDAD);
			pj.setY(pj.getY() + pj.getVelY());
		} else {
			int fallDamage = interacciones.calcularDanio(pj.getVelY());
			pj.recibirDanio(fallDamage);
			pj.setVelY(0);
		}
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
		pj.setTipoAnimacion(0);
		
		if(!interacciones.chequearColisionVertical()) {
			actualizarY(pj);
		}
		if(!interacciones.chequearColisionHorizontal()) {
			actualizarX(pj);
		}
		
		pj.gastarCombustible(Juego.GASTO_COMBUSTIBLE_IDLE);
		
		//La idea es que si se esta oprimiendo ARRIBA, no ejecute otras cosas.
		for(Accion actual: acciones) {
			if(actual instanceof AccionMovimiento) {
				if(((AccionMovimiento) actual).tipoMovimiento() == TipoMovimiento.ARRIBA) {
					if(!interacciones.chequearColisionVertical()) {
						return new Volando();
					} else {
						return null;
					}
				}
			}
		}
		
		for(Accion actual: acciones) {
			if(actual instanceof AccionMovimiento) {
				if(((AccionMovimiento) actual).tipoMovimiento() == TipoMovimiento.ABAJO) {
					if(interacciones.chequearColisionVertical()) {
						return new TaladrandoAbajo();
					}
				} else if(((AccionMovimiento) actual).tipoMovimiento() == TipoMovimiento.IZQUIERDA) {
					pj.setOrientacion(TipoMovimiento.IZQUIERDA);
					if(interacciones.chequearColisionHorizontal()) {
						return new TaladrandoIzquierda();
					}
				} else if(((AccionMovimiento) actual).tipoMovimiento() == TipoMovimiento.DERECHA) {
					pj.setOrientacion(TipoMovimiento.DERECHA);
					if(interacciones.chequearColisionHorizontal()) {
						return new TaladrandoDerecha();
					}
				}
			}
		}
		
		interacciones.chequearTienda();
		caer(pj, interacciones);
		return null;
	}
}
