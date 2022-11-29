package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow.AnchorLocation;
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
	
	//Esto hay que ver cómo ordenarlo pero lo pongo acá por el principio de las dependencias explícitas/implícitas o cómo culo se llame :P
	VistaJuego juego;
	Group root;
	VBox vbox;
	VBox vbox2;
	StackPane spane;
	Scene escena;
	//Los botones se pueden meter en un Hash o algo :P
	Image fondo;
	BackgroundImage bfondo;
	Text texto;
	Stage myStage;
	StackPane pane;
	Rectangle rect;
	Color colorcito;
	Button botonOK;
	Label label;
	Image imgBotonJugar;
	ImageView viewBotonJugar, viewBotonJugarSelected, viewBotonCargar,viewBotonConfiguracion,viewBotonSalir;
	Hyperlink botonJugar, botonCargar, botonConfig, botonSalir;
	
    @Override
    public void start(Stage stage) {
    	juego = new VistaJuego(stage);
        myStage = stage;
    	
    	root = new Group();
		vbox = new VBox();
		spane = new StackPane();
		escena = new Scene(spane, WIDTH, HEIGHT);
		
		spane.getChildren().add(vbox);
		
		vbox.setSpacing(0);
		
		imgBotonJugar = obtenerImagen("../motherloadV2/src/rsc/Menu/NuevaPartida.png");
		viewBotonJugar = new ImageView(imgBotonJugar);
		viewBotonJugarSelected =  new ImageView(obtenerImagen("../motherloadV2/src/rsc/Menu/NuevaPartidaSelected.png"));
		viewBotonJugar.setFitWidth(WIDTH/2);
		viewBotonJugar.setPreserveRatio(true);
		botonJugar = new Hyperlink();
		botonJugar.setGraphic(viewBotonJugar);
		botonJugar.setPrefSize(WIDTH/2, 100);

		
		viewBotonCargar = new ImageView(obtenerImagen("../motherloadV2/src/rsc/Menu/CargarPartida.png"));
		viewBotonCargar.setFitWidth(WIDTH/2);
		viewBotonCargar.setPreserveRatio(true);
		botonCargar = new Hyperlink();
		botonCargar.setPrefSize(WIDTH/2, 100);
		botonCargar.setGraphic(viewBotonCargar);
		
		viewBotonConfiguracion = new ImageView(obtenerImagen("../motherloadV2/src/rsc/Menu/Configuracion.png"));
		viewBotonConfiguracion.setFitWidth(WIDTH/2);
		viewBotonConfiguracion.setPreserveRatio(true);
		botonConfig = new Hyperlink();
		botonConfig.setPrefSize(WIDTH/2, 100);
		botonConfig.setGraphic(viewBotonConfiguracion);
		
		viewBotonSalir = new ImageView(obtenerImagen("../motherloadV2/src/rsc/Menu/Salir.png"));
		viewBotonSalir.setFitWidth(WIDTH/2);
		viewBotonSalir.setPreserveRatio(true);
		botonSalir = new Hyperlink();
		botonSalir.setPrefSize(WIDTH/2, 100);
		botonSalir.setGraphic(viewBotonSalir);
		
		fondo = obtenerImagen("../motherloadV2/src/rsc/FondoMenu.png");
		bfondo = new BackgroundImage(fondo, null, null, null, null);
		
		texto = new Text("Las aventuras de\n miguelito la excavadora");
		texto.setFont(Font.font("FreeMono", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
		texto.setFill(Color.CYAN);
		
		vbox.setBackground(new Background(bfondo));
		vbox.getChildren().add(texto);
		vbox.getChildren().add(botonJugar);
		vbox.getChildren().add(botonCargar);
		vbox.getChildren().add(botonConfig);
		vbox.getChildren().add(botonSalir);
		vbox.setAlignment(Pos.CENTER);
		myStage.setScene(escena);
		myStage.show();
		
		botonJugar.setOnAction(e -> juego.start());
		botonSalir.setOnAction(e -> System.exit(0));
		botonCargar.setOnAction(e -> workInProgress());
		botonConfig.setOnAction(e -> workInProgress());
    }

    private void workInProgress() {
    	pane = new StackPane();
    	vbox2 = new VBox();
    	rect = new Rectangle(0, 0, 600, 300);
    	colorcito = new Color(0.7, 0.7, 0.7, 0.3);
    	rect.setFill(colorcito);
    	
    	label = new Label("Work in progress :p");
		label.setFont(Font.font(50));
		
		botonOK = new Button("OK");
		botonOK.setPrefWidth(100);
		
		vbox2.setSpacing(20);
		vbox2.getChildren().add(label);
		vbox2.getChildren().add(botonOK);
		vbox2.setAlignment(Pos.CENTER);
		
    	pane.getChildren().add(rect);
    	pane.getChildren().add(vbox);
    	spane.getChildren().add(pane);
    	
//    	Popup popup = new Popup();
//    	
//    	popup.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
//    	popup.getContent().add(pane);
//    	
    	botonOK.setOnAction(e -> spane.getChildren().remove(pane));
    	//popup.show(stage);
	}

	public static void main(String[] args) {
        launch();
    }
    
    private static Image obtenerImagen(String nombre) {
		Image image = null;
		try {
			image = new Image(new FileInputStream(nombre), WIDTH, HEIGHT, true, false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	

}