package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jugador.Accion;
import jugador.Jugador;
import jugador.Posicion;
import jugador.TipoMovimiento;
import terreno.Aire;
import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.Tierra;
import tiendas.EstacionDeReparacion;
import tiendas.EstacionDeServicio;
import tiendas.EstacionDeVenta;
import tiendas.TiendaDeConsumibles;
import tiendas.TiendaDeMejoras;
import tp.ConfigJuego;
import tp.EstadoDelJuego;
import tp.Juego;

public class VistaJuego {
	public static double WIDTH = 1024;
	public static double HEIGHT = 768;

	public static final double GRILLA_ANCHO = 80;
	public static final double GRILLA_ALTO = 80;
	
	public static final double GRILLA_PJ_ANCHO = 64;
	public static final double GRILLA_PJ_ALTO = 64;
	
	public static final double FILAS = 64;
	public static final double COLUMNAS = 64;
	
	public static final double MID_X = WIDTH / 2;
	public static final double MID_Y = HEIGHT / 2;
	Stage stage;
	private List<Particulas> particulas = new ArrayList<>();
	private VistaInventario vistaInventario;
	private HUD hud;
	private Sonidos sonidos;
	private Juego juego;

	public VistaJuego(Stage stage) {
		this.stage = stage;
	}
	
	public void start(boolean loadGame, ConfigJuego configs) {
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		WIDTH = stage.getWidth();
		HEIGHT = stage.getHeight();
<<<<<<< Updated upstream
		
        juego = new Juego((int)COLUMNAS, configs.getDificultad(), configs);
=======
		FILAS = configs.getDificultad();
        juego = new Juego((int)COLUMNAS, (int)FILAS, configs);
        //Sonidos.cargarSonidos();
        //Alertas.cargarAlertas();
        var imagenesTerreno = cargarImagenesTerreno();
>>>>>>> Stashed changes
        var imagenes = cargarImagenes();
        AnimacionJugador imagenesPJ = new AnimacionJugador(juego.getJugador(), GRILLA_PJ_ANCHO, GRILLA_PJ_ALTO);
        //var imagenesJugador = cargarImagenesJugador();
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();
        hud = new HUD(root, context, WIDTH, HEIGHT, juego.getJugador(), juego.getGuardarPartida());
        vistaInventario = new VistaInventario(root, juego.getJugador(), WIDTH, HEIGHT);
        sonidos = new Sonidos();

        dibujar(context, juego, hud, imagenes, imagenesTerreno,imagenesPJ);
        hud.dibujarHUD();
        
        VistasTiendas vistasTiendas = new VistasTiendas(juego.getPisoSuperior(), juego.getJugador(), stage, root);
        
        if(loadGame) juego.cargarPartida();
        
        root.getChildren().add(canvas);
        Scene escena = new Scene(root, WIDTH, HEIGHT);
        
        stage.setScene(escena);
       
        var keysPressed = new HashSet<KeyCode>();
        escena.setOnKeyPressed(e -> {keysPressed.add(e.getCode()); });
        escena.setOnKeyReleased(e -> {keysPressed.remove(e.getCode()); });
        escena.setOnMouseClicked(e -> checkInteraccionesMouse(e));

        new AnimationTimer() {
        	long last = 0;
			@Override
			public void handle(long now) {
				dibujar(context, juego, hud, imagenes, imagenesTerreno, imagenesPJ);
				var acciones = new ArrayList<Accion>();
				for(var pressed: keysPressed) {
					Accion accion = juego.convertirInput(pressed);
					if(accion != null) {
						acciones.add(accion);
					}
				}
				long dt = last == 0 ? 0 : now - last;
				juego.update(acciones, dt);
				last = now;
				if(juego.getEstado() != EstadoDelJuego.JUGANDO) {
					this.stop();
					var vistaEndGame = new VistaEndGame(juego.getEstado(), stage);
					vistaEndGame.mostrar();
				}
			}
        }.start();
        stage.setFullScreen(configs.isFullScreen());
        stage.show();
	}
	
	private void dibujar(GraphicsContext context, Juego juego, HUD hud, ArrayList<Image> imagenes, Map<Character, Image> imagenesTerreno,AnimacionJugador imagenesJugador) {
    	context.clearRect(0, 0, WIDTH, HEIGHT);
    	dibujarFondo(context, imagenes, juego.getJugador());
    	dibujarTerreno2(context, juego.getSuelo(), juego.getPisoSuperior() ,imagenesTerreno, imagenes, (int)juego.getJugador().getX(), (int)juego.getJugador().getY());
    	dibujarJugador2(context, imagenesJugador, juego.getJugador());
    	actualizarParticulas(context);
    	hud.dibujarHUD();
    	vistaInventario.dibujarBotonInventario(context);
    }
	

	private static void dibujarFondo(GraphicsContext context, ArrayList<Image> imagenes, Jugador pj) {
		double playerScreenX = (WIDTH/2) - Math.round(pj.getX()) - (GRILLA_ANCHO/2);
		double backgroundX = playerScreenX * (WIDTH - 1920) /(WIDTH - COLUMNAS);
		context.drawImage(imagenes.get(0), backgroundX, 0);
	}
    
	private void dibujarJugador2(GraphicsContext context, AnimacionJugador imagenes,  Jugador jugador) {
		context.drawImage(imagenes.imagenADibujar(), ((WIDTH/2)) - (GRILLA_PJ_ANCHO/2), (HEIGHT/2));
		if(jugador.getTipoAnimacion() == 1) {
			particulas.addAll(dibujarParticulasTierra(jugador, (WIDTH/2), (HEIGHT/2) + 56, 0));
			sonidos.reproducirSonidoTaladro();
		} else if(jugador.getTipoAnimacion() == 2) {
			particulas.addAll(dibujarParticulasTierra(jugador, (WIDTH/2) + 32, (HEIGHT/2) + 32, 0.5));
			sonidos.reproducirSonidoTaladro();
		} else if(jugador.getTipoAnimacion() == 3) {
			particulas.addAll(dibujarParticulasTierra(jugador, (WIDTH/2) - 32, (HEIGHT/2) + 32, -0.5));
			sonidos.reproducirSonidoTaladro();
		} else {
			sonidos.pararSonidoTaladro();
		}
		
		
		if(jugador.getTipoAnimacion() == 4) {
			sonidos.reproducirSonidoHelicoptero();
		} else {
			sonidos.pararSonidoHelicoptero();
		}
		
	
		if(jugador.getOrientacion() == TipoMovimiento.DERECHA) {
			particulas.addAll(dibujarParticulasHumo(jugador, WIDTH/2 - GRILLA_PJ_ANCHO/2, HEIGHT/2 + GRILLA_PJ_ALTO/2 - 10, -2));
		} else {
			particulas.addAll(dibujarParticulasHumo(jugador, WIDTH/2 + GRILLA_PJ_ANCHO/4, HEIGHT/2 + GRILLA_PJ_ALTO/2 - 10, 2));
		}
	}
	
	public static void dibujarTerreno2(GraphicsContext context, Suelo suelo, PisoSuperior tiendas, Map<Character, Image> imagenesTerreno, ArrayList<Image> imagenes,double pjX, double pjY) {
		double playerScreenX = (WIDTH/2) - (GRILLA_ANCHO/2);
		double playerScreenY = (HEIGHT/2) - (GRILLA_ALTO/2);
		for(double i = 0; i < FILAS; i++) {
			for(double j = 0; j < COLUMNAS; j++) {
				double objWorldX = j * GRILLA_ANCHO;
				double objWorldY = i * GRILLA_ALTO;
				double screenX = objWorldX - (pjX * GRILLA_ANCHO) + playerScreenX;
				double screenY = objWorldY - (pjY * GRILLA_ALTO) + playerScreenY;
				
				//Para evitar renderear de mas.
				if (	objWorldX + GRILLA_ANCHO > (pjX * GRILLA_ANCHO) - playerScreenX &&
						objWorldX - GRILLA_ANCHO < (pjX * GRILLA_ANCHO) + playerScreenX &&
						objWorldY + GRILLA_ALTO > (pjY * GRILLA_ALTO) - playerScreenY &&
						objWorldY - GRILLA_ALTO < (pjY * GRILLA_ALTO) + playerScreenY) {
						
					if(i == 8 && tiendas.getTiendaPos((int) j) != null) {
						dibujarTienda(context, tiendas, imagenes, screenX, screenY, (int)j);
	    			} else {
	    				context.drawImage(tipoImagen(suelo, imagenesTerreno, j, i), screenX, screenY);
	    			}
				}
			}
		}
		
	}
	
	private static void dibujarTienda(GraphicsContext context, PisoSuperior tiendas, ArrayList<Image> imagenes, double screenX, double screenY, int posX) {
		if(tiendas.getTiendaPos(posX) instanceof EstacionDeServicio) {
			context.drawImage(imagenes.get(3), screenX, screenY - 48);
		} else if(tiendas.getTiendaPos(posX) instanceof EstacionDeVenta) {
			context.drawImage(imagenes.get(4), screenX, screenY - 48);
		} else if(tiendas.getTiendaPos(posX) instanceof EstacionDeReparacion) {
			context.drawImage(imagenes.get(5), screenX, screenY - 48);
		} else if(tiendas.getTiendaPos(posX) instanceof TiendaDeMejoras || tiendas.getTiendaPos(posX) instanceof TiendaDeConsumibles) {
			context.drawImage(imagenes.get(6), screenX, screenY - 48);
		}
	}
	
<<<<<<< Updated upstream
    private static Image tipoImagen(Suelo suelo, ArrayList<Image> imagenes, double x, double y) {
    	var bloque = suelo.getBloque(new Posicion((int)x, (int)y));
    	if(bloque instanceof Tierra && y == 9) {
    		return imagenes.get(10);
    	} else if(bloque instanceof Tierra) {
    		return imagenes.get(1);
    	} else if(bloque instanceof Aire && y < 9) {
    		return imagenes.get(0);
    	} else if(bloque instanceof Aire && y >= 9) {
    		return imagenes.get(3);
    	} else if(bloque.getBloqueID() == 'P') {
    		return imagenes.get(6);
    	} else if(bloque.getBloqueID() == 'O') {
    		return imagenes.get(7);
    	} else if(bloque.getBloqueID() == 'B') {
    		return imagenes.get(4);
    	} else if(bloque.getBloqueID() == 'H') {
    		return imagenes.get(5);
    	} else if(bloque.getBloqueID() == 'D') {
    		return imagenes.get(11);
    	} else if(bloque.getBloqueID() == 'C') {
    		return imagenes.get(12);
    	}
    	
    	return imagenes.get(1);
=======
	
    private static Image tipoImagen(Suelo suelo, Map<Character, Image> imagenesTerreno,double x, double y) {
    	var bloqueID = suelo.getBloque(new Posicion((int)x, (int)y)).getBloqueID();
    	if(bloqueID == 'T' && y == 9) {
    		bloqueID = 'G';
    	} else if(bloqueID == ' ' && y < 9) {
    		return null;
    	} else if(bloqueID == ' ' && y > FILAS - 15) {
    		return null;
    	} else if(bloqueID == ' ' && y >= 9) {
    		bloqueID = 'M';
    	} 
    	var imagen = imagenesTerreno.get(bloqueID);
    	if(imagen == null) {
    		imagen = imagenesTerreno.get('T');
    	}
    	return imagen;
    }
    
    private Map<Character, Image> cargarImagenesTerreno() {
    	var mapImagenes = new HashMap<Character, Image>();
    	mapImagenes.put('T', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tierra.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('M', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Minado.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('B', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Bronce.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('H', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Hierro.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('P', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Plata.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('O', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Oro.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('G', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Pasto.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('D', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Diamante.png", GRILLA_ANCHO, GRILLA_ALTO));
    	mapImagenes.put('C', CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Cobre.png", GRILLA_ANCHO, GRILLA_ALTO));
    	return mapImagenes;
>>>>>>> Stashed changes
    }
    
    
    private ArrayList<Image> cargarImagenes(){
    	var imagenes = new ArrayList<Image>();
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Background.jpg", WIDTH * 2, 2000));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tienda.png", GRILLA_ANCHO, GRILLA_ALTO));
<<<<<<< Updated upstream
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Pasto.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Diamante.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Cobre.png", GRILLA_ANCHO, GRILLA_ALTO));
  
=======
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/FondoFinal.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Shops/Fuel128.png", 128, 128));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Shops/Venta128.png", 128, 128));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Shops/Repair128.png", 128, 128));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Shops/Upgrade128.png", 128, 128));
>>>>>>> Stashed changes
    	return imagenes;
    }
    
    
	private void actualizarParticulas(GraphicsContext context) {
		for(Iterator<Particulas> iterador = particulas.iterator(); iterador.hasNext(); ) {
    		Particulas p = iterador.next();
    		p.update();
    		if(!p.visible()) {
    			iterador.remove();
    			continue;
    		}
    		
    		p.dibujar(context);
    	}
	}
    
    private List<Particulas> dibujarParticulasTierra(Jugador pj, double posStartX, double posStartY, double sentido) {
    	var particulas = new ArrayList<Particulas>();
    	for(int i = 0; i < 2; i++) {
    		//En orden -> Posicion de inicio X, Posicion de inicio Y, vector con la velocidad, tamaño de la particula, duracion, color.
    		Particulas p = new Particulas(posStartX, posStartY, new Posicion((Math.random() - 0.5 + sentido) * 2, Math.random()), Math.random() * 10, 0.5, Color.rgb(74, 48, 35));
    		particulas.add(p);
    	}
    	return particulas;
    }
    
    private List<Particulas> dibujarParticulasHumo(Jugador pj, double posStartX, double posStartY, double sentido) {
    	var particulas = new ArrayList<Particulas>();
    	Particulas p = new Particulas(posStartX, posStartY, new Posicion((Math.random()) * sentido, Math.random() * -0.5), Math.random() * 30, 0.3, Color.rgb(224, 224, 224, 0.2));
    	particulas.add(p);
    	return particulas;
    }
    
    private void checkInteraccionesMouse(MouseEvent e) {
        hud.checkMenu(e);
        vistaInventario.checkInteraccionInventario(e);
    }
    
    
    
}
