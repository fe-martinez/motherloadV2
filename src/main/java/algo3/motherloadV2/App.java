package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jugador.Accion;
import jugador.Jugador;
import jugador.Posicion;
import minerales.Bronce;
import minerales.Diamante;
import minerales.Hierro;
import minerales.Oro;
import minerales.Plata;
import terreno.Aire;
import terreno.Suelo;
import terreno.Tierra;
import tp.Juego;

public class App extends Application {
	private static double width = 768;
	private static double height = 768;
	
	private static double filasDibujadas = 12;
	private static double columnasDibujadas = 12;
	
	private static double grillaAncho = width/columnasDibujadas;
	private static double grillaAlto = width/filasDibujadas;
	
	private static double filas = 32;
	private static double columnas = 32;
	
    @Override
    public void start(Stage stage) {
        Suelo suelo = new Suelo((int)filas, (int)columnas);
        var imagenes = cargarImagenes();
        
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        
        Jugador pj = new Jugador(5, 3, (int)filas, (int)columnas);
        Juego juego = new Juego(suelo, null, pj);
        
        dibujar(context, juego, imagenes);
        context.drawImage(imagenes.get(2), pj.getX() * grillaAncho, pj.getY() * grillaAlto);
        
        //Pa debuggear.
        Text text = new Text();
    	text.setText("PosX: " + pj.getX() + "\nPosY: " + pj.getY());
    	text.setX(10);
    	text.setY(20);
    	
        
        root.getChildren().add(canvas);
        root.getChildren().add(text);
        Scene escena = new Scene(root, width, height);
        stage.setScene(escena);
        
        var keysPressed = new HashSet<KeyCode>();
        
        escena.setOnKeyPressed(e -> {keysPressed.add(e.getCode()); });
        escena.setOnKeyReleased(e -> {keysPressed.remove(e.getCode()); });
        
        new AnimationTimer() {
        	long last = 0;
			@Override
			public void handle(long now) {
				dibujar(context, juego, imagenes);
				text.setText("PosX: " + pj.getX() + "\nPosY: " + pj.getY());
		    	text.setX(10);
		    	text.setY(20);
		    	//Convertir input y realizar accion son bastante diferentes a los de la Etapa 2. Estan integrados a esta version
		    	// y no a la de consola.
				var acciones = new ArrayList<Accion>();
				for(var pressed: keysPressed) {
					Accion accion = juego.convertirInput(pressed);
					if(accion != null) {
						acciones.add(accion);
					}
				}
				
				//Una copiadisima de Essaya para ver si resultaba para la animacion, no sirve para na
				long dt = last == 0 ? 0 : now - last;
				juego.realizarAccion(acciones, dt);
				last = now;
			}
        }.start();
        
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    private static void dibujar(GraphicsContext context, Juego juego, ArrayList<Image> imagenes) {
    	context.clearRect(0, 0, width, height);
    	dibujarTerreno(context, juego.getSuelo(), imagenes, (int)juego.getJugador().getX(), (int)juego.getJugador().getY());
    	dibujarJugador(context, imagenes, juego.getJugador());
    }
    
    //La idea de comienzoI y comienzoJ es no dibujar al jugador antes del 0 o despues del max, probablemente haya maneras mejores
    //este es copypaste de la de abajo.
    private static void dibujarJugador(GraphicsContext context, ArrayList<Image> imagenes,  Jugador jugador) {
    	double comienzoI = jugador.getX() - (filasDibujadas/2) <= 0 ? 0 : jugador.getX() - (filasDibujadas/2);
    	double comienzoJ = jugador.getY() - (columnasDibujadas/2) <= 0 ? 0 : jugador.getY() - (filasDibujadas/2);
    	double finI = jugador.getX() + (filasDibujadas/2) > filas ? filas : comienzoI + filasDibujadas;
    	
    	if(finI - comienzoI != filasDibujadas) {
    		comienzoI = finI - filasDibujadas;
    	}
    	
    	context.drawImage(imagenes.get(2), ((jugador.getX() - comienzoI) * grillaAncho) - 1, ((jugador.getY() - comienzoJ) * grillaAlto));
	}

    
    //La idea principal de esto es hacer el famoso "zoom" al pj en vez de dibujar todo el mapa.
    //comienzoI, finI, comienzoJ y finJ sirven todas para chequear que no se traten de dibujar cosas que no existen.
    //Esto se ve cuando te acercas a algun borde con el pj y la camara deja de moverse con el personaje.
	private static void dibujarTerreno(GraphicsContext context, Suelo suelo, ArrayList<Image> imagenes, double pjX, double pjY) {
    	double comienzoI = pjX - (filasDibujadas/2) < 0 ? 0 : pjX - (filasDibujadas/2);
    	double comienzoJ = pjY - (columnasDibujadas/2) < 0 ? 0 : pjY - (columnasDibujadas/2);
    	double finI = pjX + (filasDibujadas/2) > filas ? filas : comienzoI + filasDibujadas;
    	double finJ = pjY + (columnasDibujadas/2) > columnas ? columnas : comienzoJ + columnasDibujadas;
    	
    	if(finI - comienzoI != filasDibujadas) {
    		comienzoI = finI - filasDibujadas;
    	}
    	
    	for(double j = comienzoJ; j < finJ; j++) {
        	for(double i = comienzoI; i < finI; i++) {
        		context.drawImage(tipoImagen(suelo, imagenes, i, j), (i - comienzoI) * grillaAncho, (j - comienzoJ) * grillaAlto);
        	}
        }
    }
	
	
	//Estas son la version mas cuadrada con "zoom", si queres probarla cambia los llamados de dibujar()
    private static void dibujarJugador2(GraphicsContext context, ArrayList<Image> imagenes,  Jugador jugador) {
    	context.drawImage(imagenes.get(2), ((width/2) - (grillaAncho/2)), (height/2) - (grillaAlto/2));
	}
	
	public static void dibujarTerreno2(GraphicsContext context, Suelo suelo, ArrayList<Image> imagenes, double pjX, double pjY) {
		double playerScreenX = (width/2) - (grillaAncho/2);
		double playerScreenY = (height/2) - (grillaAlto/2);
		for(double i = 0; i < filas; i++) {
			for(double j = 0; j < columnas; j++) {
				double worldX = j * grillaAncho;
				double worldY = i * grillaAlto;
				double screenX = worldX - (pjX * grillaAncho) + playerScreenX;
				double screenY = worldY - (pjY * grillaAlto) + playerScreenY;
				
				if (worldX + grillaAncho > (pjX * grillaAncho) - playerScreenX &&
					worldX - grillaAncho < (pjX * grillaAncho) + playerScreenX &&
					worldY + grillaAlto > (pjY * grillaAlto) - playerScreenY &&
					worldY - grillaAlto < (pjY * grillaAlto) + playerScreenY) {
					
					context.drawImage(tipoImagen(suelo, imagenes, j, i), screenX, screenY);
				}
				

			}
		}
	}
	  
    private static Image tipoImagen(Suelo suelo, ArrayList<Image> imagenes, double x, double y) {
    	var bloque = suelo.getBloque(new Posicion((int)x, (int)y));
    	if(bloque instanceof Tierra) {
    		return imagenes.get(1);
    	} else if(bloque instanceof Aire && y < 4) {
    		return imagenes.get(0);
    	} else if(bloque instanceof Aire && y >= 4) {
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
    
    private ArrayList<Image> cargarImagenes(){
    	var imagenes = new ArrayList<Image>();
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Cielo.png"));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Tierra.png"));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Jugador.png"));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Minado.png"));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Bronce.png"));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Hierro.png"));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Plata.png"));
		imagenes.add(obtenerImagen("../motherloadV2/src/rsc/Oro.png"));
    	
    	return imagenes;
    }
    
	private static Image obtenerImagen(String nombre) {
		Image image1 = null;
		try {
			image1 = new Image(new FileInputStream(nombre));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image1;
	}
	

}