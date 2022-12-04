package algo3.motherloadV2;

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

public class VistaMenu {
	double WIDTH = 1024;
	double HEIGHT = 768;
	
	VistaJuego vistaJuego;
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
	private VistaMenuConfig vistaConfigs;
	
	public VistaMenu(Stage stage) {
    	var configs = ConfigJuego.readFile();
    	if(configs != null) {
    		this.WIDTH = configs.getScreenWidth();
    		this.HEIGHT = configs.getScreenHeight();
    		stage.setFullScreen(configs.isFullScreen());
    	} else {
    		this.WIDTH = 1024;
    		this.HEIGHT = 768;
    		stage.setFullScreen(false);
    	}
    	
		vistaJuego = new VistaJuego(stage);
		myStage = stage;
    	
    	root = new Group();
		vbox = new VBox();
		spane = new StackPane();
		escena = new Scene(spane, WIDTH, HEIGHT);
    	vistaConfigs = new VistaMenuConfig(stage, spane);
		spane.getChildren().add(vbox);
		
		imgBotonJugar = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Menu/NuevaPartida.png",WIDTH,HEIGHT);
		viewBotonJugar = new ImageView(imgBotonJugar);
		viewBotonJugarSelected =  new ImageView(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Menu/NuevaPartidaSelected.png",WIDTH,HEIGHT));
		viewBotonJugar.setFitWidth(WIDTH/2);
		viewBotonJugar.setPreserveRatio(true);
		botonJugar = new Hyperlink();
		botonJugar.setGraphic(viewBotonJugar);
		botonJugar.setPrefSize(WIDTH/2, 100);

		
		viewBotonCargar = new ImageView(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Menu/CargarPartida.png",WIDTH,HEIGHT));
		viewBotonCargar.setFitWidth(WIDTH/2);
		viewBotonCargar.setPreserveRatio(true);
		botonCargar = new Hyperlink();
		botonCargar.setPrefSize(WIDTH/2, 100);
		botonCargar.setGraphic(viewBotonCargar);
		
		viewBotonConfiguracion = new ImageView(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Menu/Configuracion.png",WIDTH,HEIGHT));
		viewBotonConfiguracion.setFitWidth(WIDTH/2);
		viewBotonConfiguracion.setPreserveRatio(true);
		botonConfig = new Hyperlink();
		botonConfig.setPrefSize(WIDTH/2, 100);
		botonConfig.setGraphic(viewBotonConfiguracion);
		
		viewBotonSalir = new ImageView(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Menu/Salir.png",WIDTH,HEIGHT));
		viewBotonSalir.setFitWidth(WIDTH/2);
		viewBotonSalir.setPreserveRatio(true);
		botonSalir = new Hyperlink();
		botonSalir.setPrefSize(WIDTH/2, 100);
		botonSalir.setGraphic(viewBotonSalir);
		
		fondo = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/FondoMenu.png", WIDTH, HEIGHT);
		bfondo = new BackgroundImage(fondo, null, null, null, null);
		
		texto = new Text("Motherload");
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
		
		botonJugar.setOnAction(e -> vistaJuego.start(false, configs));
		botonSalir.setOnAction(e -> System.exit(0));
		botonCargar.setOnAction(e ->vistaJuego.start(true, configs));
		botonConfig.setOnAction(e -> vistaConfigs.mostrar());
	}
}
