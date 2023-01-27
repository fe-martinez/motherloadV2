package vistas;

import controlador.ControladorJuego;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tp.ConfigJuego;
import tp.CreadorDeImagenes;
import tp.Sonidos;

public class VistaMenu {
	double WIDTH = 1024;
	double HEIGHT = 768;
	
	VistaJuego vistaJuego;
	Group root;
	VBox vbox;
	VBox vbox2;
	StackPane spane;
	Scene escena;
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
	private VistaMenuConfig vistaConfigs;
	private ControladorJuego mainJuego;
	private ConfigJuego configs;
	private VistaHistoria historia;
	
	public VistaMenu(Stage stage) {
    	configs = ConfigJuego.readFile();
    	if(configs != null) {
    		this.WIDTH = configs.getScreenWidth();
    		this.HEIGHT = configs.getScreenHeight();
    		stage.setFullScreen(false);
    		Sonidos.setVolumenGeneral(configs.getVolumen());
    	} else {
    		this.WIDTH = 1024;
    		this.HEIGHT = 768;
    		stage.setFullScreen(false);
    	}
    	
		Sonidos.stopReproduccion("Jugando");
	    Sonidos.reproducir("Menu");
    	
		mainJuego = new ControladorJuego(stage, configs);
		historia = new VistaHistoria(stage, WIDTH, HEIGHT, false, mainJuego);
		
		stage.setWidth(WIDTH);
		stage.setHeight(HEIGHT);
		myStage = stage;
    	
		this.inicializarMenu();
		this.inicializarBotones();
		this.inicializarFondo();
		this.configurarVBox();
		
		
		myStage.setScene(escena);
		myStage.show();
		
		this.inicializarAccionesBotones();
		
	}
	
	private void inicializarMenu() {
		root = new Group();
		vbox = new VBox();
		spane = new StackPane();
		escena = new Scene(spane, WIDTH, HEIGHT);
    	vistaConfigs = new VistaMenuConfig(escena, spane, configs);
		spane.getChildren().add(vbox);
	}
	
	private void inicializarBotones() {
		imgBotonJugar = CreadorDeImagenes.obtenerImagen("src/rsc/Menu/NuevaPartida.png",WIDTH,HEIGHT);
		viewBotonJugar = new ImageView(imgBotonJugar);
		viewBotonJugarSelected =  new ImageView(CreadorDeImagenes.obtenerImagen("src/rsc/Menu/NuevaPartidaSelected.png",WIDTH,HEIGHT));
		viewBotonJugar.setFitWidth(WIDTH/2);
		viewBotonJugar.setPreserveRatio(true);
		botonJugar = new Hyperlink();
		botonJugar.setGraphic(viewBotonJugar);
		botonJugar.setPrefSize(WIDTH/2, 100);

		
		viewBotonCargar = new ImageView(CreadorDeImagenes.obtenerImagen("src/rsc/Menu/CargarPartida.png",WIDTH,HEIGHT));
		viewBotonCargar.setFitWidth(WIDTH/2);
		viewBotonCargar.setPreserveRatio(true);
		botonCargar = new Hyperlink();
		botonCargar.setPrefSize(WIDTH/2, 100);
		botonCargar.setGraphic(viewBotonCargar);
		
		viewBotonConfiguracion = new ImageView(CreadorDeImagenes.obtenerImagen("src/rsc/Menu/Configuracion.png",WIDTH,HEIGHT));
		viewBotonConfiguracion.setFitWidth(WIDTH/2);
		viewBotonConfiguracion.setPreserveRatio(true);
		botonConfig = new Hyperlink();
		botonConfig.setPrefSize(WIDTH/2, 100);
		botonConfig.setGraphic(viewBotonConfiguracion);
		
		viewBotonSalir = new ImageView(CreadorDeImagenes.obtenerImagen("src/rsc/Menu/Salir.png",WIDTH,HEIGHT));
		viewBotonSalir.setFitWidth(WIDTH/2);
		viewBotonSalir.setPreserveRatio(true);
		botonSalir = new Hyperlink();
		botonSalir.setPrefSize(WIDTH/2, 100);
		botonSalir.setGraphic(viewBotonSalir);
	}
	
	private void configurarVBox() {
		vbox.setBackground(new Background(bfondo));
		vbox.getChildren().add(texto);
		vbox.getChildren().add(botonJugar);
		vbox.getChildren().add(botonCargar);
		vbox.getChildren().add(botonConfig);
		vbox.getChildren().add(botonSalir);
		vbox.setAlignment(Pos.CENTER);
	}
	
	private void inicializarFondo() {
		fondo = CreadorDeImagenes.obtenerImagen("src/rsc/FondoMenu.png", WIDTH, HEIGHT);
		bfondo = new BackgroundImage(fondo, null, null, null, null);
		
		texto = new Text("Motherlode");
		texto.setFont(Font.font("FreeMono", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
		texto.setFill(Color.CYAN);
		
	}
	
	private void inicializarAccionesBotones() {
		botonJugar.setOnAction(e -> historia.mostrar());
		botonSalir.setOnAction(e -> System.exit(0));
		botonCargar.setOnAction(e ->mainJuego.start(true));
		botonConfig.setOnAction(e -> vistaConfigs.mostrar());
	}
}
