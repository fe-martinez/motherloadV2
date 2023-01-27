package controlador;

import java.util.ArrayList;

import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jugador.Accion;
import jugador.AccionItem;
import terreno.Entidad;
import tiendas.EstacionDeReparacion;
import tiendas.EstacionDeServicio;
import tiendas.EstacionDeVenta;
import tiendas.TiendaDeConsumibles;
import tiendas.TiendaDeMejoras;
import tp.ConfigJuego;
import tp.EstadoDelJuego;
import tp.GuardarPartida;
import tp.Juego;
import tp.Sonidos;
import vistas.HUD;
import vistas.VistaEndGame;
import vistas.VistaEstacionDeReparacion;
import vistas.VistaEstacionDeServicio;
import vistas.VistaEstacionDeVenta;
import vistas.VistaInventario;
import vistas.VistaJuego;
import vistas.VistaTiendaDeConsumibles;
import vistas.VistaTiendaDeMejoras;

public class ControladorJuego {
	private static final int GAME_WIDTH = 64;
	Stage stage;
	Juego juego;
	ConfigJuego configs;
	Group root;
	private VistaEndGame vistaEndGame;
	private VistaEstacionDeVenta vistaVenta;
	private ControladorEstacionDeVenta controladorVenta;
	private VistaTiendaDeConsumibles vistaConsumibles;
	private ControladorEstacionDeMantenimiento controladorEstServicio;
	private VistaEstacionDeServicio vistaEstServicio;
	private VistaEstacionDeReparacion vistaEstReparacion;
	private ControladorTiendaDeConsumibles controladorConsumibles;
	private ControladorEstacionDeMantenimiento controladorEstReparacion;
	private VistaTiendaDeMejoras vistaMejoras;
	private ControladorTiendaDeMejoras controladorMejoras;
	private HUD hud;
	private VistaInventario vistaInventario;
	private Scene escena;
	private Canvas canvas;
	private HashSet<KeyCode> keysPressed;
	private GuardarPartida guardar;
	private Entidad tienda;
	private VistaJuego vista;
	private int dificultad;
	
	public ControladorJuego(Stage stage, ConfigJuego configs) {
		this.stage = stage;
		this.configs = configs;
	}
	
	public void inicializarControladoresTiendas() {
	    vistaVenta = new VistaEstacionDeVenta(stage, root);
        controladorVenta = new ControladorEstacionDeVenta((EstacionDeVenta) juego.getTienda((int)(VistaJuego.COLUMNAS * 0.4)),vistaVenta,juego.getJugador());
        controladorVenta.vender();
        controladorVenta.cerrarTienda();
        
        vistaConsumibles = new VistaTiendaDeConsumibles(stage,root);
        controladorConsumibles = new ControladorTiendaDeConsumibles((TiendaDeConsumibles)juego.getTienda((int)(VistaJuego.COLUMNAS * 0.7)),vistaConsumibles,juego.getJugador()); 
        controladorConsumibles.vender();
        controladorConsumibles.cerrarTienda();
        
        
        vistaEstServicio = new VistaEstacionDeServicio(stage, root);
        controladorEstServicio = new ControladorEstacionDeMantenimiento((EstacionDeServicio)juego.getTienda((int)(VistaJuego.COLUMNAS * 0.3)), vistaEstServicio, juego.getJugador());
        controladorEstServicio.setLabels();
        controladorEstServicio.vender();
        controladorEstServicio.cerrarTienda();
        
        
        vistaEstReparacion = new VistaEstacionDeReparacion(stage, root);
        controladorEstReparacion = new ControladorEstacionDeMantenimiento((EstacionDeReparacion)juego.getTienda((int)(VistaJuego.COLUMNAS * 0.6)), vistaEstReparacion, juego.getJugador());
        controladorEstReparacion.setLabels();
        controladorEstReparacion.vender();
        controladorEstReparacion.cerrarTienda();
        
        vistaMejoras = new VistaTiendaDeMejoras(stage,root);
        controladorMejoras = new ControladorTiendaDeMejoras((TiendaDeMejoras) juego.getTienda((int)(VistaJuego.COLUMNAS * 0.9)), vistaMejoras, juego.getJugador());
        controladorMejoras.comprar();
	}
	
	private void inicializarStage() {
		stage.setTitle("Motherload");
		root = new Group();
		
		if(configs != null) {
			stage.setHeight(configs.getScreenHeight());
			stage.setWidth(configs.getScreenWidth());
		} else {
			stage.setHeight(768);
			stage.setWidth(1024);
		}
			
		stage.setFullScreen(false);
        canvas = new Canvas(stage.getWidth(), stage.getHeight());
        root.getChildren().add(canvas);
		escena = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(escena);
	}
	
	private void inicializarSonidos() {
		Sonidos.stopReproduccion("Menu");
		Sonidos.reproducir("Jugando");
		Sonidos.reproducir("Get ready");
	}
	
	public void start(boolean loadGame) {
		this.inicializarStage();
		this.inicializarSonidos();
		
		keysPressed = new HashSet<KeyCode>();
        escena.setOnKeyPressed(e -> {keysPressed.add(e.getCode()); });
        escena.setOnKeyReleased(e -> {keysPressed.remove(e.getCode()); });
        escena.setOnMouseClicked(e -> checkInteraccionesMouse(e));
        dificultad = 1000;
        if(configs != null) {
        	dificultad = configs.getDificultad();
        }
        this.juego = new Juego(GAME_WIDTH, dificultad, configs);
        guardar = new GuardarPartida(juego.getJugador(), juego.getSuelo());
        hud = new HUD(root, canvas.getGraphicsContext2D(), stage.getWidth(), juego.getJugador(), guardar, stage);
        vistaInventario = new VistaInventario(root, juego.getJugador(), stage.getWidth(), stage.getHeight());
        
        this.inicializarControladoresTiendas();
        
        if(loadGame) {
        	juego.cargarPartida();
        }
        
        vista = new VistaJuego(canvas.getGraphicsContext2D(), juego, root, stage, dificultad);
        vista.avisoInicio();
        this.loop();
	}
	
	private void loop() {
		new AnimationTimer() {
        	long last = 0;
			@Override
			public void handle(long now) {
				vista.dibujar();
				var acciones = new ArrayList<Accion>();
				for(var pressed: keysPressed) {
					Accion accion = juego.convertirInput(pressed);
					if(accion != null) {
						acciones.add(accion);
					} if(accion instanceof AccionItem) {
						keysPressed.remove(pressed);
					}
				}
				long dt = last == 0 ? 0 : now - last;
				juego.update(acciones, dt);
				last = now;
			
				if(juego.getEstado() != EstadoDelJuego.JUGANDO) {
					if(juego.getEstado() == EstadoDelJuego.PERDIDO) {
						Sonidos.stopReproduccion("Jugando");
						Sonidos.reproducir("Muerte");
					}
					this.stop();
					checkEndGame();
				}
				
				tienda = juego.getInteracciones().chequearTienda();
				chequearInteraccionesTiendas();
			}
        }.start();
	}
	
	private void chequearInteraccionesTiendas() {
		if(tienda != null) {
			juego.getJugador().setX(juego.getJugador().getX() + 1);
			if(tienda instanceof EstacionDeVenta) {
				controladorVenta.actualizarVista();
				vistaVenta.mostrar();
			}
			else if(tienda instanceof TiendaDeConsumibles) {
				vistaConsumibles.mostrar();
			}
			else if(tienda instanceof EstacionDeServicio) {
				controladorEstServicio.actualizarBarra();
				vistaEstServicio.mostrar();
			}
			else if(tienda instanceof EstacionDeReparacion) {
				controladorEstReparacion.actualizarBarra();
				vistaEstReparacion.mostrar();
			}
			else if(tienda instanceof TiendaDeMejoras) {
				vistaMejoras.mostrar();
				controladorMejoras.comprar();
			}
		}
		
	}
	
	private void checkEndGame() {
		vistaEndGame = new VistaEndGame(juego.getEstado(), stage, configs.getScreenWidth(), configs.getScreenHeight());
		vistaEndGame.mostrar(juego.getEstado());
	}

	
    private void checkInteraccionesMouse(MouseEvent e) {
        hud.checkMenu(e);
        vistaInventario.checkInteraccionInventario(e);
    }
    
}
