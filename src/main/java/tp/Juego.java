package tp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import algo3.motherloadV2.VistasTiendas;
import javafx.scene.input.KeyCode;
import jugador.Accion;
import jugador.AccionItem;
import jugador.AccionMovimiento;
import jugador.EstadoJugador;
import jugador.Interacciones;
import jugador.Jugador;
import jugador.Posicion;
import mejoras.*;
import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.Vista;

public class Juego {	
	public static final int FPS = 60;
	public static final long MS_PER_FRAME = 1000 / FPS;
	public static final double VELOCITY = 150 / FPS;
	private static final double COEF_REDUCCION_X = 0.005;
	private static final double COEF_REDUCCION_Y = 0.002;
	private static final double GRAVEDAD = 0.0045;
	private static final double MAX_TICKS = 75;
	
	private Suelo suelo;
	private PisoSuperior tiendas;
	private Jugador jugador;
	private Interacciones interacciones;
	private GuardarPartida gameSaver;
	private Map<KeyCode, Accion> controles;
	private double direccionVertical;
	private double direccionHorizontal;
	private double ticks;
	private double ticksVuelo;
	
	private long msSinceLastFrame = 0;
	
	public Juego(int ancho, int alto) {
		this.suelo = new Suelo(ancho, alto);
		this.jugador =new Jugador(5, 3, alto, ancho);
		this.tiendas = new PisoSuperior(jugador);
		this.gameSaver = new GuardarPartida(jugador, suelo);
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
		if(jugador.getY() < jugador.getLimiteAlto()){
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
	private void prenderTaladroCostado(double direccionHorizontal) {
		if(jugador.getEstado() == EstadoJugador.INICIAL) {
			if(direccionHorizontal > 0) {
				jugador.setEstado(EstadoJugador.TALADRANDO_DERECHA_FULL);
			} else if(direccionHorizontal< 0) {
				jugador.setEstado(EstadoJugador.TALADRANDO_IZQUIERDA_FULL);
			}
			
			//Se ponia rara la protoanimacion :p
			if(direccionHorizontal >= 0) {
				jugador.setX((int)jugador.getX());	
			} else {
				jugador.setX(jugador.getX() + 0.2);
			}
			
		}
		
		ticks = 0;
	}
	
	private void prenderTaladroAbajo() {
		if(jugador.getEstado() == EstadoJugador.INICIAL) {
			jugador.setEstado(EstadoJugador.TALADRANDO_ABAJO_FULL);
		}
		
		jugador.setX((int)jugador.getX());
		jugador.setY((int)jugador.getY());
			
		ticks = 0;
	}
	
	private double getDecimal(double numero) {
		var decimal = Math.abs(numero - ((int)numero));
		return decimal;
	}
	
	private void volar() {
		if(jugador.getVelY() < 0) {
			if(jugador.getEstado() == EstadoJugador.INICIAL) {
				jugador.setEstado(EstadoJugador.VOLANDO1);
			}
			
			if(jugador.getEstado() == EstadoJugador.VOLANDO1 && ticksVuelo > FPS) {
				jugador.setEstado(EstadoJugador.VOLANDO2);
				ticksVuelo = 0;
			}
			
			if(jugador.getEstado() == EstadoJugador.VOLANDO2 && ticksVuelo > FPS) {
				jugador.setEstado(EstadoJugador.VOLANDO1);
				ticksVuelo = 0;
			}
		}
		
		if(jugador.getVelY() >= 0) {
			jugador.setEstado(EstadoJugador.INICIAL);
		}
	}
	
	//Taladra hasta que se cumplan los MAX_TICKS.
	private void taladrar(double direccionVertical, double direccionHorizontal) {
		jugador.setX(jugador.getX() + ((direccionHorizontal * 10) / MAX_TICKS));
		jugador.setY(jugador.getY() + ((direccionVertical * 10) / MAX_TICKS));
		ticks += 1;
		
		if(ticks > MAX_TICKS) {
				if(interacciones.chequearBloques()) {
					jugador.setEstado(EstadoJugador.INICIAL);
					jugador.setVelX(0);
					jugador.setVelY(0);					
				}
		}
	}
		
	
	private void frenar() {
		this.jugador.setVelX(0);
		this.jugador.setVelY(0);
	}
	
	private void actualizarX() {
		if(this.jugador.getVelX() > 0) {
			this.jugador.setVelX(this.jugador.getVelX() - COEF_REDUCCION_X);				
		} else if(this.jugador.getVelX() < 0) {
			this.jugador.setVelX(this.jugador.getVelX() + COEF_REDUCCION_X);
		}
		
		if(Math.abs(jugador.getVelX()) <= COEF_REDUCCION_X){
			this.jugador.setVelX(0);
		}
		
		this.jugador.setX(this.jugador.getX() + this.jugador.getVelX());
	}
	
	private void actualizarY() {
		if(this.jugador.getVelY() > 0) {
			this.jugador.setVelY(this.jugador.getVelY() - COEF_REDUCCION_Y);
		} else if(this.jugador.getVelY() < 0) {
			this.jugador.setVelY(this.jugador.getVelY() + COEF_REDUCCION_Y);
		}
		
		if(Math.abs(jugador.getVelY()) <= COEF_REDUCCION_Y) {
			this.jugador.setVelY(0);
		}
		this.jugador.setY(this.jugador.getY() + this.jugador.getVelY());
	}
	
	//Realiza las acciones que encuentra en la lista de acciones y las remueve de la misma.
	//De momento, para ser utilizada por consola funciona de esta manera, pero la idea es que sea un loop que ejecute todas las acciones,
	//una por cada una de las teclas que estan siendo presionadas de momento.
	public void realizarAccion(ArrayList<Accion> acciones, HashSet<KeyCode> keysPressed, long dt) {
		for(var accion: acciones) {
			accion.aplicar();
		}
		
		direccionVertical = jugador.getVelY();
		direccionHorizontal = jugador.getVelX();
		
		msSinceLastFrame += dt / 1_000_000;
		while (msSinceLastFrame >= MS_PER_FRAME) {
			msSinceLastFrame -= MS_PER_FRAME;
			
			if(jugador.getEstado() == EstadoJugador.INICIAL) {
				direccionVertical = jugador.getVelY();
				direccionHorizontal = jugador.getVelX();
				
				if(!interacciones.chequearColisionVertical()) {
					actualizarY();
				}
				
				if(!interacciones.chequearColisionHorizontal()) {
					actualizarX();
				}
				
				if(!keysPressed.contains(KeyCode.UP)) {
					if(keysPressed.contains(KeyCode.DOWN)) {
						if(interacciones.chequearColisionVertical()) {
							prenderTaladroAbajo();
						}
					} else if(keysPressed.contains(KeyCode.LEFT) || keysPressed.contains(KeyCode.RIGHT)) {
						if(interacciones.chequearColisionHorizontal()) {
							prenderTaladroCostado(direccionHorizontal);
						}
					}
				}
			} else {
				if(!keysPressed.contains(KeyCode.UP)) {
					taladrar(direccionVertical, direccionHorizontal);
				}
			}
			
			interacciones.chequearTienda();
			if(!keysPressed.contains(KeyCode.UP) && jugador.getEstado() == EstadoJugador.INICIAL) {
				caer();
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

	public void cargarPartida() {
		gameSaver.cargarPartida(this.jugador, this.suelo);
	}

	public void guardarJuego() {
		gameSaver.guardarPartida();
	}
	
	public GuardarPartida getGuardarPartida() {
		return this.gameSaver;
	}
		
}
