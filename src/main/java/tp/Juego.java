package tp;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javafx.scene.input.KeyCode;
import jugador.Accion;
import jugador.AccionItem;
import jugador.AccionMovimiento;
import jugador.EnumEstadoJugador;
import jugador.Interacciones;
import jugador.Jugador;
import jugador.Posicion;
import mejoras.*;
import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.Vista;

public class Juego {	
	public static final int FPS = 90;
	public static final long MS_PER_FRAME = 1000 / FPS;
	public static final double VELOCITY = 150 / FPS;
	private static final double COEF_REDUCCION = 0.002;
	private static final double GRAVEDAD = 0.0045;
	private static final double MAX_TICKS = 75;
	
	private Suelo suelo;
	private PisoSuperior tiendas;
	private Jugador jugador;
	private Interacciones interacciones;
	private Map<KeyCode, Accion> controles;
	private double direccionVertical;
	private double direccionHorizontal;
	private double ticks;
	private double ticksVuelo;
	
	private long msSinceLastFrame = 0;
	
	public Juego(Suelo suelo, PisoSuperior tiendas, Jugador jugador) {
		this.suelo = suelo;
		this.jugador = jugador;
		this.tiendas = tiendas;
		this.interacciones = new Interacciones(jugador, suelo, tiendas);
		this.direccionVertical = 0;
		this.direccionHorizontal = 0;
		
		//Usa Character de momento pero con JavaFX pasaria a ser KeyCode.
		final Map<KeyCode, Accion> controles = Map.of(
				KeyCode.UP, new AccionMovimiento(jugador, 0, -0.1),
				KeyCode.DOWN, new AccionMovimiento(jugador, 0, 0.1),
				KeyCode.RIGHT, new AccionMovimiento(jugador, 0.1, 0),
				KeyCode.LEFT, new AccionMovimiento(jugador, -0.1, 0),
				KeyCode.F, new AccionItem(jugador, new MejoraTanqueExtra()),
				KeyCode.Q, new AccionItem(jugador, new MejoraTeleport()),
				KeyCode.R, new AccionItem(jugador, new MejoraHullRepairNanobots()),
				KeyCode.X, new AccionItem(jugador, new MejoraDinamita(suelo))
				);
		this.controles = controles;
	}
	
	//Indica el estado del juego actual.
	//Si el jugador no puede continuar, devuelve PERDIDO.
	//Si el jugador llegó al final del terreno, devuelve GANADO.
	//En todo otro caso, devuelve JUGANDO.
	private EstadoDelJuego estadoJuego(){
		if(jugador.noPuedeContinuar()){
			return EstadoDelJuego.PERDIDO;
		}
		if(jugador.getY() == suelo.getAlto()) {
			return EstadoDelJuego.GANADO;
		}
		
		return EstadoDelJuego.JUGANDO;
	}
	
	//Recibe un movimiento y lo convierte en una Accion, que será añadida a la lista de acciones si es válida.
	public Accion convertirInput(KeyCode movimiento) {
		Accion accion = controles.get(movimiento);
		if(accion != null) {
			return accion;
		}
		return null;
	}
	
	//Si no tiene suelo abajo, le suma a la VelY. Uso VelY en vez de ponerselo a Y directamente para dar la sensacion de parabola.
	private void caer() {
		if(jugador.getY() < jugador.getLimiteAlto() && jugador.getVelY() >= 0){
			Posicion debajo = Posicion.redondear(new Posicion(jugador.getX(), jugador.getY() + 1));
			if(suelo.casilleroVacio(debajo) && jugador.getY() < jugador.getLimiteAlto() - 2) {
				jugador.setVelY(jugador.getVelY() + GRAVEDAD);
				jugador.setY(jugador.getY() + jugador.getVelY());
				debajo.setY(debajo.getY() + 1);
			} else {
				jugador.setVelY(0);
			}
		}
	}
	
	//Si no esta taladrando, pasa a taladrar.
	private void prenderTaladro(double direccionVertical, double direccionHorizontal) {
		if(jugador.getEstado() == EnumEstadoJugador.INICIAL) {
			if(direccionHorizontal > 0) {
				jugador.setEstado(EnumEstadoJugador.TALADRANDO_DERECHA_FULL);
			} else if(direccionHorizontal< 0) {
				jugador.setEstado(EnumEstadoJugador.TALADRANDO_IZQUIERDA_FULL);
			} else if(direccionVertical > 0) {
				jugador.setEstado(EnumEstadoJugador.TALADRANDO_ABAJO_FULL);
			}
		}
		
		ticks = 0;
	}
	
	private double getDecimal(double numero) {
		var decimal = Math.abs(numero - ((int)numero));
		return decimal;
	}
	
	private void volar() {
		if(jugador.getVelY() < 0) {
			if(jugador.getEstado() == EnumEstadoJugador.INICIAL) {
				jugador.setEstado(EnumEstadoJugador.VOLANDO1);
			}
			
			if(jugador.getEstado() == EnumEstadoJugador.VOLANDO1 && ticksVuelo > FPS) {
				jugador.setEstado(EnumEstadoJugador.VOLANDO2);
				ticksVuelo = 0;
			}
			
			if(jugador.getEstado() == EnumEstadoJugador.VOLANDO2 && ticksVuelo > FPS) {
				jugador.setEstado(EnumEstadoJugador.VOLANDO1);
				ticksVuelo = 0;
			}
		}
		
		if(jugador.getVelY() >= 0) {
			jugador.setEstado(EnumEstadoJugador.INICIAL);
		}
	}
	
	//Taladra hasta que se cumplan los MAX_TICKS.
	private void taladrar(double direccionVertical, double direccionHorizontal) {
		jugador.setX(jugador.getX() + ((direccionHorizontal/MAX_TICKS) * 10));
		jugador.setY(jugador.getY() + ((direccionVertical/MAX_TICKS) * 10));
		ticks += 1;
		
		if(ticks > MAX_TICKS) {
				interacciones.chequearBloques();
				jugador.setEstado(EnumEstadoJugador.INICIAL);
				jugador.setVelX(0);
				jugador.setVelY(0);
		}
	}
		
	
	private void frenar() {
		this.jugador.setVelX(0);
		this.jugador.setVelY(0);
	}
	
	//Le suma la VelX y VelY al jugador. Si no se estan apretando las teclas, va bajando la velocidad en vez de pasar directamente a 0.
	private void actualizar() {
		if(this.jugador.getVelX() > 0) {
			this.jugador.setVelX(this.jugador.getVelX() - COEF_REDUCCION);				
		} else if(this.jugador.getVelX() < 0) {
			this.jugador.setVelX(this.jugador.getVelX() + COEF_REDUCCION);
		}
		
		if(Math.abs(jugador.getVelX()) <= COEF_REDUCCION){
			this.jugador.setVelX(0);
		}
			
		if(this.jugador.getVelY() > 0) {
			this.jugador.setVelY(this.jugador.getVelY() - COEF_REDUCCION);
		} else if(this.jugador.getVelY() < 0) {
			this.jugador.setVelY(this.jugador.getVelY() + COEF_REDUCCION);
		}
		
		if(Math.abs(jugador.getVelY()) <= COEF_REDUCCION) {
			this.jugador.setVelY(0);
		}
			
		this.jugador.setX(this.jugador.getX() + this.jugador.getVelX());
		this.jugador.setY(this.jugador.getY() + this.jugador.getVelY());
	}
	
	//Realiza las acciones que encuentra en la lista de acciones y las remueve de la misma.
	//De momento, para ser utilizada por consola funciona de esta manera, pero la idea es que sea un loop que ejecute todas las acciones,
	//una por cada una de las teclas que estan siendo presionadas de momento.
	public void realizarAccion(ArrayList<Accion> acciones, long dt) {
		for(var accion: acciones) {
			accion.aplicar();
		}
		
		msSinceLastFrame += dt / 1_000_000;
		while (msSinceLastFrame >= MS_PER_FRAME) {
			msSinceLastFrame -= MS_PER_FRAME;
			
			
			
			if(jugador.getEstado() == EnumEstadoJugador.INICIAL) {
				direccionVertical = jugador.getVelY();
				direccionHorizontal = jugador.getVelX();
				
				if(!interacciones.chequearColision()) {
					actualizar();
				} else {
					frenar();
					prenderTaladro(direccionVertical, direccionHorizontal);
				}
				
				volar();
				caer();
			} else {
				taladrar(direccionVertical, direccionHorizontal);
			}
		}
	}
	
	
	public Suelo getSuelo() {
		return this.suelo;
	}

	public Jugador getJugador() {
		return this.jugador;
	}
	
	public PisoSuperior getPisoSuperior() {
		return tiendas;
	}
		
}
