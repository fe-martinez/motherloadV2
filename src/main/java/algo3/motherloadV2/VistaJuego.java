package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jugador.Accion;
import jugador.EstadoJugador;
import jugador.Jugador;
import jugador.Posicion;
import minerales.Mineral;
import terreno.Aire;
import terreno.Suelo;
import terreno.Tierra;
import tp.Juego;

public class VistaJuego {
	private static final double WIDTH = 1024;
	private static final double HEIGHT = 768;
	
	private static final double GRILLA_ANCHO = 128;
	private static final double GRILLA_ALTO = 128;
	
	public static final double GRILLA_PJ_ANCHO = 80;
	public static final double GRILLA_PJ_ALTO = 80;
	
	private static final double COLUMNAS_DIBUJADAS = WIDTH/GRILLA_ANCHO;
	private static final double FILAS_DIBUJADAS = HEIGHT/GRILLA_ALTO;
	
	private static final double FILAS = 256;
	private static final double COLUMNAS = 64;
	
	Stage stage;

	public VistaJuego(Stage stage) {
		this.stage = stage;
	}
	
	public void start() {
		Suelo suelo = new Suelo((int)FILAS, (int)COLUMNAS);
        var imagenes = cargarImagenes();
        var imagenesJugador = cargarImagenesJugador();
        
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();
        Jugador pj = new Jugador(5, 3, (int)FILAS, (int)COLUMNAS);
        Juego juego = new Juego(suelo, null, pj);
        HUD hud = new HUD(context, WIDTH, HEIGHT, pj);
        dibujar(context, juego, imagenes, imagenesJugador);
        hud.dibujarHUD();
        
        root.getChildren().add(canvas);

        Scene escena = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(escena);
        
        var keysPressed = new HashSet<KeyCode>();
        
        escena.setOnKeyPressed(e -> {keysPressed.add(e.getCode()); });
        escena.setOnKeyReleased(e -> {keysPressed.remove(e.getCode()); });
        
        new AnimationTimer() {
        	long last = 0;
			@Override
			public void handle(long now) {
				dibujar(context, juego, imagenes, imagenesJugador);
				hud.dibujarHUD();
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
				juego.realizarAccion(acciones, dt);
				last = now;
			}
        }.start();
        
        
        stage.show();
	}
	
	
	private static void dibujar(GraphicsContext context, Juego juego, ArrayList<Image> imagenes, ArrayList<Image> imagenesJugador) {
    	context.clearRect(0, 0, WIDTH, HEIGHT);
    	context.drawImage(imagenes.get(8), 0, 0);
    	dibujarFondo(context, imagenes, juego.getJugador());
    	dibujarTerreno(context, juego.getSuelo(), imagenes, (int)juego.getJugador().getX(), (int)juego.getJugador().getY());
    	dibujarJugador(context, imagenesJugador, juego.getJugador());
    }
	
	private static void dibujarFondo(GraphicsContext context, ArrayList<Image> imagenes, Jugador pj) {
		double playerScreenX = (WIDTH/2) - Math.round(pj.getX()) - (GRILLA_ANCHO/2);
		double backgroundX = playerScreenX * (WIDTH - 1920) /(WIDTH - COLUMNAS);
		context.drawImage(imagenes.get(8), backgroundX, 0);
	}
    
    //La idea de comienzoI y comienzoJ es no dibujar al jugador antes del 0 o despues del max, probablemente haya maneras mejores
    //este es copypaste de la de abajo.
    private static void dibujarJugador(GraphicsContext context, ArrayList<Image> imagenes,  Jugador jugador) {
    	double comienzoI = jugador.getX() - (COLUMNAS_DIBUJADAS/2) <= 0 ? 0 : jugador.getX() - (COLUMNAS_DIBUJADAS/2);
    	double comienzoJ = jugador.getY() - (FILAS_DIBUJADAS/2) <= 0 ? 0 : jugador.getY() - (FILAS_DIBUJADAS/2);
    	double finI = jugador.getX() + (COLUMNAS_DIBUJADAS/2) > COLUMNAS ? COLUMNAS : comienzoI + COLUMNAS_DIBUJADAS;
    	
    	if(finI - comienzoI != COLUMNAS_DIBUJADAS) {
    		comienzoI = finI - COLUMNAS_DIBUJADAS;
    	}
    	
    	context.drawImage(tipoImagenJugador(jugador, imagenes), ((jugador.getX() - comienzoI) * GRILLA_ANCHO), ((jugador.getY() - comienzoJ) * GRILLA_ALTO) + 64);
	}

    
    //La idea principal de esto es hacer el famoso "zoom" al pj en vez de dibujar todo el mapa.
    //comienzoI, finI, comienzoJ y finJ sirven todas para chequear que no se traten de dibujar cosas que no existen.
    //Esto se ve cuando te acercas a algun borde con el pj y la camara deja de moverse con el personaje.
	private static void dibujarTerreno(GraphicsContext context, Suelo suelo, ArrayList<Image> imagenes, double pjX, double pjY) {
    	double comienzoI = pjX - (COLUMNAS_DIBUJADAS/2) < 0 ? 0 : pjX - (COLUMNAS_DIBUJADAS/2);
    	double comienzoJ = pjY - (FILAS_DIBUJADAS/2) < 0 ? 0 : pjY - (FILAS_DIBUJADAS/2);
    	double finI = pjX + (COLUMNAS_DIBUJADAS/2) > COLUMNAS ? COLUMNAS : comienzoI + COLUMNAS_DIBUJADAS;
    	double finJ = pjY + (FILAS_DIBUJADAS/2) > FILAS ? FILAS : comienzoJ + FILAS_DIBUJADAS;
    	
    	if(finI - comienzoI != COLUMNAS_DIBUJADAS) {
    		comienzoI = finI - COLUMNAS_DIBUJADAS;
    	}
    	
    	if(finJ - comienzoJ != FILAS_DIBUJADAS) {
    		comienzoJ = finJ - FILAS_DIBUJADAS;
    	}
    	
//    	for(double j = comienzoJ; j < finJ; j++) {
//        	for(double i = comienzoI; i < finI; i++) {
//        		context.drawImage(tipoImagen(suelo, imagenes, i, j), (i - comienzoI) * GRILLA_ANCHO, (j - comienzoJ) * GRILLA_ALTO);
//        	}
//        }
    	
    	for(double i = comienzoI; i < finI; i++) {
    		for(double j = comienzoJ; j < finJ; j++) {
    			context.drawImage(tipoImagen(suelo, imagenes, i, j), (i - comienzoI) * GRILLA_ANCHO, (j - comienzoJ) * GRILLA_ALTO);
    		}
    	}
    	
    }
	  
    private static Image tipoImagen(Suelo suelo, ArrayList<Image> imagenes, double x, double y) {
    	var bloque = suelo.getBloque(new Posicion((int)x, (int)y));
    	if(bloque instanceof Tierra) {
    		return imagenes.get(1);
    	} else if(bloque instanceof Aire && y < 9) {
    		return imagenes.get(0);
    	} else if(bloque instanceof Aire && y >= 9) {
    		return imagenes.get(3);
    	}
    	    	
    	if(bloque.getBloqueID() == 'P') {
    		return imagenes.get(6);
    	}
    	
    	if(bloque.getBloqueID() == 'O') {
    		return imagenes.get(7);
    	}
    	
    	if(bloque.getBloqueID() == 'B') {
    		return imagenes.get(4);
    	}
    	
    	if(bloque.getBloqueID() == 'H') {
    		return imagenes.get(5);
    	}
    	
    	return null;
    }
    
    private static Image tipoImagenJugador(Jugador pj, ArrayList<Image> imagenesJugador) {
    	if(pj.getEstado() == EstadoJugador.INICIAL) {
    		return imagenesJugador.get(0);
    	} else if(pj.getEstado() == EstadoJugador.TALADRANDO_ABAJO_INICIO) {
    		return imagenesJugador.get(1);
    	} else if(pj.getEstado() == EstadoJugador.TALADRANDO_ABAJO_FULL) {
    		return imagenesJugador.get(2);
    	}  else if(pj.getEstado() == EstadoJugador.TALADRANDO_DERECHA_INICIO) {
    		return imagenesJugador.get(3);
    	} else if(pj.getEstado() == EstadoJugador.TALADRANDO_DERECHA_FULL) {
    		return imagenesJugador.get(4);
    	} else if(pj.getEstado() == EstadoJugador.TALADRANDO_IZQUIERDA_INICIO) {
    		return imagenesJugador.get(5);
    	} else if(pj.getEstado() == EstadoJugador.TALADRANDO_IZQUIERDA_FULL) {
    		return imagenesJugador.get(6);
    	} 
    	
    	return imagenesJugador.get(0);
    }
    
    private ArrayList<Image> cargarImagenes(){
    	var imagenes = new ArrayList<Image>();
		//imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Cielo.png", 64));
    	imagenes.add(null);
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Tierra.png", GRILLA_ANCHO));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Jugador.png", GRILLA_PJ_ANCHO));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Minado.png", GRILLA_ANCHO));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Bronce.png", GRILLA_ANCHO));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Hierro.png", GRILLA_ANCHO));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Plata.png", GRILLA_ANCHO));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Oro.png", GRILLA_ANCHO));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Background.jpg", 1920));
    	
    	return imagenes;
    }
    
    private ArrayList<Image> cargarImagenesJugador(){
    	var imagenesJugador = new ArrayList<Image>();
    	imagenesJugador.add(obtenerImagen("../motherloadV2/src/rsc/Jugador.png", GRILLA_PJ_ANCHO));
    	imagenesJugador.add(obtenerImagen("../motherloadV2/src/rsc/Abajo1.png", GRILLA_PJ_ANCHO));
    	imagenesJugador.add(obtenerImagen("../motherloadV2/src/rsc/Abajo2.png", GRILLA_PJ_ANCHO));
    	imagenesJugador.add(obtenerImagen("../motherloadV2/src/rsc/Derecha1.png", GRILLA_PJ_ANCHO));
    	imagenesJugador.add(obtenerImagen("../motherloadV2/src/rsc/Derecha2.png", GRILLA_PJ_ANCHO));
    	imagenesJugador.add(obtenerImagen("../motherloadV2/src/rsc/Izquierda1.png", GRILLA_PJ_ANCHO));
    	imagenesJugador.add(obtenerImagen("../motherloadV2/src/rsc/Izquierda2.png", GRILLA_PJ_ANCHO));
		
		return imagenesJugador;
    }
    
	private static Image obtenerImagen(String nombre, double size) {
		Image image1 = null;
		try {
			image1 = new Image(new FileInputStream(nombre), size, size, true, false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image1;
	}
	
	private String listarIDMinerales(List<Mineral> minerales) {
		var result = "";
		for(var mineral: minerales) {
			result = result + mineral.getBloqueID();
		}
		return result;
	}
	
	
	//Estas son la version mas cuadrada con "zoom", si queres probarla cambia los llamados de dibujar()
    private static void dibujarJugador2(GraphicsContext context, ArrayList<Image> imagenesJugador,  Jugador jugador) {
    	context.drawImage(tipoImagenJugador(jugador, imagenesJugador), ((WIDTH/2) - (GRILLA_ANCHO/2)), (HEIGHT/2) - (GRILLA_ALTO/2) + (GRILLA_ALTO/4));
	}
	
	public static void dibujarTerreno2(GraphicsContext context, Suelo suelo, ArrayList<Image> imagenes, double pjX, double pjY) {
		double playerScreenX = (WIDTH/2) - (GRILLA_ANCHO/2);
		double playerScreenY = (HEIGHT/2) - (GRILLA_ALTO/2);
		for(double i = 9; i < FILAS; i++) {
			for(double j = 0; j < COLUMNAS; j++) {
				double worldX = j * GRILLA_ANCHO;
				double worldY = i * GRILLA_ALTO;
				double screenX = worldX - (pjX * GRILLA_ANCHO) + playerScreenX;
				double screenY = worldY - (pjY * GRILLA_ALTO) + playerScreenY;
				
				if (worldX + GRILLA_ANCHO > (pjX * GRILLA_ANCHO) - playerScreenX &&
					worldX - GRILLA_ANCHO < (pjX * GRILLA_ANCHO) + playerScreenX &&
					worldY + GRILLA_ALTO > (pjY * GRILLA_ALTO) - playerScreenY &&
					worldY - GRILLA_ALTO < (pjY * GRILLA_ALTO) + playerScreenY) {
					
					context.drawImage(tipoImagen(suelo, imagenes, j, i), screenX, screenY);
				}
			}
		}
	}
}
