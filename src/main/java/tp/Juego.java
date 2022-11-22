package tp;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javafx.scene.input.KeyCode;
import jugador.Accion;
import jugador.AccionItem;
import jugador.AccionMovimiento;
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
	
	private Suelo suelo;
	private PisoSuperior tiendas;
	private Jugador jugador;
	private Vista vista;
	private Scanner input;
	private Map<KeyCode, Accion> controles;
	
	private long msSinceLastFrame = 0;
	
	public Juego(Suelo suelo, PisoSuperior tiendas, Jugador jugador) {
		this.suelo = suelo;
		this.jugador = jugador;
		this.tiendas = tiendas;
		this.input = null;
		this.vista = new Vista(tiendas, suelo, jugador, jugador.getInventario(), Main.ANCHO, Main.ALTURA);
		
		//Usa Character de momento pero con JavaFX pasaria a ser KeyCode.
		final Map<KeyCode, Accion> controles = Map.of(
				KeyCode.UP, new AccionMovimiento(jugador, suelo, tiendas, 0, -0.1),
				KeyCode.DOWN, new AccionMovimiento(jugador, suelo, tiendas, 0, 0.1),
				KeyCode.RIGHT, new AccionMovimiento(jugador, suelo, tiendas, 0.1, 0),
				KeyCode.LEFT, new AccionMovimiento(jugador, suelo, tiendas, -0.1, 0),
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
	
	private void caer() {
		if(jugador.getY() < jugador.getLimiteAlto()){
			Posicion debajo = new Posicion(jugador.getX(), jugador.getY() + 1);
			if(suelo.casilleroVacio(debajo) && jugador.getY() < jugador.getLimiteAlto() - 2) {
				jugador.setY(jugador.getY() + 0.01);
				debajo.setY(debajo.getY() + 1);
			}
		}
	}
	
	//Realiza las acciones que encuentra en la lista de acciones y las remueve de la misma.
	//De momento, para ser utilizada por consola funciona de esta manera, pero la idea es que sea un loop que ejecute todas las acciones,
	//una por cada una de las teclas que estan siendo presionadas de momento.
	public void realizarAccion(ArrayList<Accion> acciones, long dt) {
		for(var accion: acciones) {
			accion.aplicar();
		}
		
//		msSinceLastFrame += dt / 1000000;
//		while (msSinceLastFrame >= MS_PER_FRAME) {
//			msSinceLastFrame -= MS_PER_FRAME;
//			caer();
//		}
		caer();
	}
	
	public Suelo getSuelo() {
		return this.suelo;
	}

	public Jugador getJugador() {
		return this.jugador;
	}
		
}
