package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jugador.Accion;
import jugador.Jugador;
import jugador.Posicion;
import minerales.Bronce;
import minerales.Diamante;
import minerales.Hierro;
import minerales.Mineral;
import minerales.Oro;
import minerales.Plata;
import terreno.Aire;
import terreno.Bloque;
import terreno.Suelo;
import terreno.Tierra;
import tp.Juego;

public class App extends Application {
	static final double WIDTH = 1024;
	static final double HEIGHT = 768;
	
	private static final double FILAS_DIBUJADAS = 12;
	private static final double COLUMNAS_DIBUJADAS = 12;
	
	private static final double GRILLA_ANCHO = WIDTH/COLUMNAS_DIBUJADAS;
	private static final double GRILLA_ALTO = WIDTH/FILAS_DIBUJADAS;
	
	private static final double FILAS = 32;
	private static final double COLUMNAS = 32;
	
    @Override
    public void start(Stage stage) {
        VistaJuego juego = new VistaJuego(stage);
        
        Group root = new Group();
		StackPane spane = new StackPane();
		Scene escena = new Scene(spane, WIDTH, HEIGHT);
		Button boton = new Button("Comenzar");
		Image fondo = obtenerImagen("../motherloadV2/src/rsc/FondoMenu.png", WIDTH);
		ImageView imgView = new ImageView(fondo);
		spane.getChildren().add(imgView);
		spane.getChildren().add(boton);
		stage.setScene(escena);
		stage.show();
		
		var presionados = new ArrayList<ActionEvent>();
		
		boton.setOnAction(e -> juego.start());
    }

    public static void main(String[] args) {
        launch();
    }
    
    private static Image obtenerImagen(String nombre, double size) {
		Image image = null;
		try {
			image = new Image(new FileInputStream(nombre), size, size, true, false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	

}