package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import jugador.Jugador;
import tiendas.TiendaDeConsumibles;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class VistaTiendaDeConsumibles implements VistaEntidad {
	private static double SIZE_BOTON = 125;
	List<Label> labels = new ArrayList<>();
	List<Background> background = new ArrayList<>();
	List<Image> images = new ArrayList<>();
	List<BackgroundImage> backImg = new ArrayList<>();
	private StackPane sPane;
	Group root;
	GridPane gridPane;
	boolean mostrando;
	Jugador jugador;
	TiendaDeConsumibles tienda;
	HashMap<String,Button> botones;
	List<String> keys = List.of("tanqueExtra","nanobots","dinamita","explosivos","teleport","close");
	List<String> mejorasDisponibles = List.of("F","R","X","C","Q");
	
	public VistaTiendaDeConsumibles(Stage stage, Group root, Jugador jugador, TiendaDeConsumibles tienda) {
		this.jugador = jugador;
		this.root = root;
		this.mostrando = false;
		this.tienda = tienda;
	}
	
	
	private void inicializarLabels() {
		labels.add(new Label("Tanque de combustible de reserva\r\n"
				+ "$2000\r\n"
				+ "Presione 'F' para usar\r\n"
				+ "Tanque portátil: recarga hasta 25 litros instantáneamente."));
		labels.add(new Label("Nanobots de reparación\r\n"
				+ "$7500\r\n"
				+ "Presione 'R' en cualquier momento para utilizarlos\r\n"
				+ "Reparan un máximo de 30 de daño en cualquier momento y en cualquier lugar"));
		labels.add(new Label("Dinamita\r\n"
				+ "$2000\r\n"
				+ "Presione 'X' en cualquier momento para usar\r\n"
				+ "Este objeto elimina un área pequeña alrededor de tu cápsula."));
		labels.add(new Label("Explosivos\r\n"
				+ "$5000\r\n"
				+ "Presione 'C' para usar\r\n"
				+ "Crea una enorme explosión, eliminando una gran área alrededor de tu cápsula."));
		labels.add(new Label("Teletransportador cuántico\r\n"
				+ "$2000\r\n"
				+ "Presione 'Q' en cualquier momento para usar\r\n"
				+ "Te teletransporta a algún lugar por encima del nivel de la superficie (los resultados pueden variar)"));
		labels.add(new Label());
		for(Label label: labels) {
			label.setFont(new Font(16));
			label.setTextAlignment(TextAlignment.RIGHT);
			label.setAlignment(Pos.BASELINE_RIGHT);
		}
	}
	
	private void inicializarImagenes() {
		this.images.add(CreadorDeImagenes.obtenerImagen("..\\motherloadV2\\src\\rsc\\Tiendas\\TiendaDeConsumibles\\tanqueExtra.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("..\\motherloadV2\\src\\rsc\\Tiendas\\TiendaDeConsumibles\\nanobots.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("..\\motherloadV2\\src\\rsc\\Tiendas\\TiendaDeConsumibles\\dinamita.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("..\\motherloadV2\\src\\rsc\\Tiendas\\TiendaDeConsumibles\\explosivos.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("..\\motherloadV2\\src\\rsc\\Tiendas\\TiendaDeConsumibles\\teleport.png", SIZE_BOTON, SIZE_BOTON));
	}
	
	private void inicializarBackground() {
		this.inicializarImagenes();
		for(int i = 0; i < this.images.size(); i++) {
			this.backImg.add(new BackgroundImage(this.images.get(i),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT));
		}
		for(int i = 0; i < this.backImg.size(); i++) {
			this.background.add(new Background(this.backImg.get(i)));
		}
	}
	
	private void organizarBotonesEnGridpane() {
		GridPane.setConstraints(botones.get("tanqueExtra"),0,0);
	    GridPane.setConstraints(botones.get("nanobots"),1,0);
	    GridPane.setConstraints(botones.get("dinamita"),2,0);
	    GridPane.setConstraints(botones.get("explosivos"),0,1);
	    GridPane.setConstraints(botones.get("teleport"),1,1);
	}
	
	private void crearBotonClose() {
    	this.botones.put("close",new Button("X"));
	    this.botones.get("close").setPrefSize(SIZE_BOTON, SIZE_BOTON);
	    this.botones.get("close").setBackground(Background.EMPTY);
	    this.botones.get("close").setAlignment(Pos.CENTER);
	    this.botones.get("close").setFont(new Font(20));
	    this.botones.get("close").setTextFill(Color.WHITE);
	    this.botones.get("close").setCancelButton(true);
	}
	
	private void personalizarBotones() {
		for(HashMap.Entry<String,Button> pair: botones.entrySet()) {
	        pair.getValue().setPrefSize(SIZE_BOTON,SIZE_BOTON);
	    	pair.getValue().setBorder(Border.stroke(Paint.valueOf("Black")));
	    	pair.getValue().setAlignment(Pos.CENTER);
		}
    	
    	for(int i = 0; i < this.keys.size()-1; i++) {
    		this.botones.get(keys.get(i)).setBackground(this.background.get(i));
    	}	
    	
	}
	
	private void crearBotones() {
		this.botones = new HashMap<>();
    	for(int i = 0; i < this.keys.size()-1; i++) {
    		this.botones.put(keys.get(i),new Button());
    	}
	}

	private void inicializarBotones() {
		this.inicializarLabels();
    	this.inicializarBackground();
    	this.crearBotones(); 
    	this.personalizarBotones();
    	this.crearBotonClose();
	    this.organizarBotonesEnGridpane();
	}
	
	private void inicializarGridPane() {
		gridPane = new GridPane();
	    Image img = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tiendas/TiendaDeConsumibles/FondoTiendaDeConsumibles.png", 800, 600);
	    
	    BackgroundImage backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background background = new Background(backgroundImg);
	    gridPane.setBackground(background);
	    gridPane.setMaxSize(800, 600);
	    gridPane.setPadding(new Insets(100));
	    this.inicializarBotones();
	    gridPane.getChildren().addAll(botones.get("tanqueExtra"),botones.get("nanobots"),botones.get("dinamita"),botones.get("explosivos"),botones.get("teleport"));
	    
	}
	
	private void inicializarAccionesBotones() {
		botones.get(keys.get(0)).setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(labels.get(0).getText());
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				tienda.interactuar(jugador,mejorasDisponibles.get(0).charAt(0));
			}
		});
		
		botones.get(keys.get(1)).setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(labels.get(1).getText());
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				tienda.interactuar(jugador,mejorasDisponibles.get(1).charAt(0));
			}
		});
		
		botones.get(keys.get(2)).setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(labels.get(2).getText());
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				tienda.interactuar(jugador,mejorasDisponibles.get(2).charAt(0));
			}
		});
		

		botones.get(keys.get(3)).setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(labels.get(3).getText());
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				tienda.interactuar(jugador,mejorasDisponibles.get(3).charAt(0));
			}
		});
		

		botones.get(keys.get(4)).setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText(labels.get(4).getText());
			a.showAndWait();
			var resultado = a.getResult();
			if(resultado == ButtonType.OK) {
				tienda.interactuar(jugador,mejorasDisponibles.get(4).charAt(0));
			}
		});
		
		   this.botones.get("close").setOnAction(e -> {
		    	root.getChildren().remove(root.getChildren().size() - 1);
		    	mostrando = false;
		    });
    }
	
	public void inicializarVistatiendaDeConsumibles() {
		this.inicializarGridPane();
		sPane = new StackPane();
	    sPane.getChildren().addAll(gridPane,botones.get("close"));
	    StackPane.setMargin(gridPane,new Insets(0,0,0,0));
	    StackPane.setMargin(botones.get("close"),new Insets(0,650,510,0));
	    sPane.setPrefSize(1024,768); // WIDTH Y HEIGHT
	    this.inicializarAccionesBotones();

	    root.getChildren().add(sPane);
	    this.mostrando = true;   
	}
	  
	public void mostrar() {
		if(!this.mostrando) {
			this.inicializarVistatiendaDeConsumibles();
		}
	}
}

