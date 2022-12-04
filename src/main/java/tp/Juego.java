package tp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import estados.Estado;
import estados.Inicial;
import javafx.scene.input.KeyCode;
import jugador.Accion;
import jugador.AccionItem;
import jugador.AccionMovimiento;
import jugador.Interacciones;
import jugador.Jugador;
import mejoras.*;
import terreno.PisoSuperior;
import terreno.Suelo;

public class Juego {	
	public static final int FPS = 60;
	public static final long MS_PER_FRAME = 1000 / FPS;
	public static final double VELOCITY = 150 / FPS;

	public static final double GRAVEDAD = 0.0045;
	public static final double MAX_TICKS = 75;
	
	private Suelo suelo;
	private PisoSuperior tiendas;
	private Jugador jugador;
	private Interacciones interacciones;
	private GuardarPartida gameSaver;
	private Map<KeyCode, Accion> controles;
	private Estado estado;
	
	private long msSinceLastFrame = 0;
	
	public Juego(int ancho, int alto) {
		this.suelo = new Suelo(ancho, alto);
		this.jugador =new Jugador(5, 3, alto, ancho);
		this.tiendas = new PisoSuperior(jugador);
		this.gameSaver = new GuardarPartida(jugador, suelo);
		this.interacciones = new Interacciones(jugador, suelo, tiendas);
		this.estado = new Inicial();
		
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
		
	//Realiza las acciones que encuentra en la lista de acciones y las remueve de la misma.
	//De momento, para ser utilizada por consola funciona de esta manera, pero la idea es que sea un loop que ejecute todas las acciones,
	//una por cada una de las teclas que estan siendo presionadas de momento.
	public void update(ArrayList<Accion> acciones, long dt) {
		for(var accion: acciones) {
			accion.aplicar();
		}
		msSinceLastFrame += dt / 1_000_000;
		while (msSinceLastFrame >= MS_PER_FRAME) {
			var estadoActual = this.estado.update(acciones, jugador, interacciones);
			if(estadoActual != null) {
				this.estado = estadoActual;
			}
			msSinceLastFrame -= MS_PER_FRAME;
		}
		//Aca termina de procesar el turno.
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
