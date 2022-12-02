package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jugador.Accion;
import jugador.Jugador;
import jugador.Posicion;
import terreno.Aire;
import terreno.PisoSuperior;
import terreno.Suelo;
import terreno.Tierra;
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
	

	public VistaJuego(Stage stage) {
		this.stage = stage;
	}
	
	public void start(boolean loadGame) {
		//stage.setResizable(false);
		
		stage.setResizable(true);
		WIDTH = stage.getWidth();
		HEIGHT = stage.getHeight();
        Juego juego = new Juego((int)COLUMNAS, (int)FILAS);
        var imagenes = cargarImagenes();
        AnimacionJugador imagenesPJ = new AnimacionJugador(juego.getJugador(), GRILLA_PJ_ANCHO, GRILLA_PJ_ALTO);
        //var imagenesJugador = cargarImagenesJugador();
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();
        HUD hud = new HUD(context, WIDTH, HEIGHT, juego.getJugador());
        
        dibujar(context, juego, hud, imagenes, imagenesPJ);
        hud.dibujarHUD();
        
        VistasTiendas vistasTiendas = new VistasTiendas(juego.getPisoSuperior(), juego.getJugador(), stage, root);
        
        if(loadGame) juego.cargarPartida();
        
        root.getChildren().add(canvas);
        Scene escena = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(escena);
       
        var keysPressed = new HashSet<KeyCode>();
        escena.setOnKeyPressed(e -> {keysPressed.add(e.getCode()); });
        escena.setOnKeyReleased(e -> {keysPressed.remove(e.getCode()); });
        escena.setOnMouseClicked(e -> hud.checkMenu(e, root, juego.getGuardarPartida()));
       //stage.setFullScreen(true);
        new AnimationTimer() {
        	long last = 0;
			@Override
			public void handle(long now) {
				dibujar(context, juego, hud, imagenes, imagenesPJ);
		    	//Convertir input y realizar accion son bastante diferentes a los de la Etapa 2. Estan integrados a esta version
		    	// y no a la de consola.
				var acciones = new ArrayList<Accion>();
				for(var pressed: keysPressed) {
					Accion accion = juego.convertirInput(pressed);
					if(accion != null) {
						acciones.add(accion);
					}
				}
				long dt = last == 0 ? 0 : now - last;
				juego.realizarAccion(acciones, keysPressed, dt);
				last = now;
			}
        }.start();
        stage.show();
	}
	
	
	private void dibujar(GraphicsContext context, Juego juego, HUD hud,ArrayList<Image> imagenes, AnimacionJugador imagenesJugador) {
    	context.clearRect(0, 0, WIDTH, HEIGHT);
    	dibujarFondo(context, imagenes, juego.getJugador());
    	dibujarTerreno2(context, juego.getSuelo(), juego.getPisoSuperior(), imagenes, (int)juego.getJugador().getX(), (int)juego.getJugador().getY());
    	dibujarJugador2(context, imagenesJugador, juego.getJugador());
    	
    	for(Iterator<Particulas> iterador = particulas.iterator(); iterador.hasNext(); ) {
    		Particulas p = iterador.next();
    		p.update();
    		if(!p.visible()) {
    			iterador.remove();
    			continue;
    		}
    		
    		p.dibujar(context);
    	}
    	
    	hud.dibujarHUD();
    }
	
	private static void dibujarFondo(GraphicsContext context, ArrayList<Image> imagenes, Jugador pj) {
		double playerScreenX = (WIDTH/2) - Math.round(pj.getX()) - (GRILLA_ANCHO/2);
		double backgroundX = playerScreenX * (WIDTH - 1920) /(WIDTH - COLUMNAS);
		context.drawImage(imagenes.get(8), backgroundX, 0);
	}
    
	//Estas son la version mas cuadrada con "zoom", si queres probarla cambia los llamados de dibujar()
	private void dibujarJugador2(GraphicsContext context, AnimacionJugador imagenes,  Jugador jugador) {
		context.drawImage(imagenes.imagenADibujar(), ((WIDTH/2)) - (GRILLA_PJ_ANCHO/2), (HEIGHT/2));
		
		if(jugador.getTipoAnimacion() == 1) {
			particulas.addAll(dibujarParticulas(jugador));
		}
		
	}
	
	public static void dibujarTerreno2(GraphicsContext context, Suelo suelo, PisoSuperior tiendas, ArrayList<Image> imagenes, double pjX, double pjY) {
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
						context.drawImage(imagenes.get(9), screenX, screenY);
	    			} else {
	    				context.drawImage(tipoImagen(suelo, imagenes, j, i), screenX, screenY);
	    			}
				}
			}
		}
	}
	
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
    	}
    	
    	return imagenes.get(1);
    }
    
    private ArrayList<Image> cargarImagenes(){
    	var imagenes = new ArrayList<Image>();
		//imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Cielo.png", 64));
    	imagenes.add(null); //Aca va la del cielo, puse null para probar lo del fondo!
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tierra.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Jugador.png", GRILLA_PJ_ANCHO, GRILLA_PJ_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Minado.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Bronce.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Hierro.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Plata.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Oro.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Background.jpg", WIDTH * 2, 2000));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tienda.png", GRILLA_ANCHO, GRILLA_ALTO));
		imagenes.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Pasto.png", GRILLA_ANCHO, GRILLA_ALTO));
  
    	return imagenes;
    }
    
    private List<Particulas> dibujarParticulas(Jugador pj) {
    	var particulas = new ArrayList<Particulas>();
    	
    	for(int i = 0; i < 1; i++) {
    		Particulas p = new Particulas((WIDTH/2), (HEIGHT/2) + 48, new Posicion((Math.random() - 0.5), Math.random()), Math.random() * 10, 1.0, Color.SANDYBROWN);
    		particulas.add(p);
    	}
    	
    	return particulas;
    }
    
    
}
