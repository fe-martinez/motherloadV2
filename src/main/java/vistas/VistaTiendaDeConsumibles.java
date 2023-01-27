package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mejoras.TipoUsable;
import tp.CreadorDeImagenes;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class VistaTiendaDeConsumibles {
	private static double SIZE_BOTON = 125;
	List<Background> background = new ArrayList<>();
	List<Image> images = new ArrayList<>();
	List<BackgroundImage> backImg = new ArrayList<>();
	private StackPane sPane;
	Group root;
	GridPane gridPane;
	private Stage stage;
	boolean mostrando;
	HashMap<String,Button> botones;
	List<String> keys = List.of("tanqueExtra","nanobots","dinamita","explosivos","teleport","close");
	List<TipoUsable> mejorasDisponibles = List.of(TipoUsable.TANQUE_EXTRA, TipoUsable.REPAIR, TipoUsable.DINAMITA, TipoUsable.EXPLOSIVOS, TipoUsable.TELEPORT);
	List<String> instrucciones = List.of("Tanque de combustible de reserva\r\n"
			+ "$2000\r\n"
			+ "Presione 'F' para usar\r\n"
			+ "Tanque portátil: recarga hasta 25 litros instantáneamente.\r\n"
			+ "Presione Aceptar para comprar la mejora o Cancelar en caso contrario.\r\n",
			
			"Nanobots de reparación\r\n"
			+ "$7500\r\n"
			+ "Presione 'R' en cualquier momento para utilizarlos\r\n"
			+ "Reparan un máximo de 30 de daño en cualquier momento y en cualquier lugar"
			+ "Presione Aceptar para comprar la mejora o Cancelar en caso contrario.\r\n",
			
			"Dinamita\r\n"
			+ "$2000\r\n"
			+ "Presione 'X' en cualquier momento para usar\r\n"
			+ "Este objeto elimina un área pequeña alrededor de tu cápsula."
			+ "Presione Aceptar para comprar la mejora o Cancelar en caso contrario.\r\n",
			
			"Explosivos\r\n"
			+ "$5000\r\n"
			+ "Presione 'C' para usar\r\n"
			+ "Crea una enorme explosión, eliminando una gran área alrededor de tu cápsula."
			+ "Presione Aceptar para comprar la mejora o Cancelar en caso contrario.\r\n",
			
			"Teletransportador cuántico\r\n"
			+ "$2000\r\n"
			+ "Presione 'Q' en cualquier momento para usar\r\n"
			+ "Te teletransporta a algún lugar por encima del nivel de la superficie (los resultados pueden variar)"
			+ "Presione Aceptar para comprar la mejora o Cancelar en caso contrario.\r\n"
		);

	public VistaTiendaDeConsumibles(Stage stage, Group root) {
		this.root = root;
		this.stage = stage;
		this.inicializarVistatiendaDeConsumibles();
		this.mostrando = false;
	}
	
	private void inicializarImagenes() {
		this.images.add(CreadorDeImagenes.obtenerImagen("src/rsc/Tiendas/TiendaDeConsumibles/tanqueExtra.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("src/rsc/Tiendas/TiendaDeConsumibles/nanobots.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("src/rsc/Tiendas/TiendaDeConsumibles/dinamita.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("src/rsc/Tiendas/TiendaDeConsumibles/explosivos.png", SIZE_BOTON, SIZE_BOTON));
		this.images.add(CreadorDeImagenes.obtenerImagen("src/rsc/Tiendas/TiendaDeConsumibles/teleport.png", SIZE_BOTON, SIZE_BOTON));
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
	    this.botones.get("close").setPrefSize(50, 50);
	    this.botones.get("close").setBackground(Background.EMPTY);
	    this.botones.get("close").setAlignment(Pos.CENTER);
	    this.botones.get("close").setFont(new Font(20));
	    this.botones.get("close").setTextFill(Color.WHITE);
	    this.botones.get("close").setCancelButton(true);
	    this.botones.get("close").setBorder(Border.EMPTY);
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
	
	private void inicializarGridPane() {
		gridPane = new GridPane();
	    Image img = CreadorDeImagenes.obtenerImagen("src/rsc/Tiendas/TiendaDeConsumibles/FondoTiendaDeConsumibles.png", 800, 600);
	    
	    BackgroundImage backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background background = new Background(backgroundImg);
	    gridPane.setBackground(background);
	    gridPane.setMaxSize(800, 600);
	    gridPane.setPadding(new Insets(100)); 
	}
	
	public void registrarEscuchaClose(EventHandler<ActionEvent> eventHandler) {
		botones.get("close").setOnAction(eventHandler);
	}
	
	public void registrarEscuchaTanqueExtra(EventHandler<ActionEvent> eventHandler) {
		botones.get("tanqueExtra").setOnAction(eventHandler);
	}
	
	public void registrarEscuchaNanobots(EventHandler<ActionEvent> eventHandler) {
		botones.get("nanobots").setOnAction(eventHandler);
	}
	
	public void registrarEscuchaDinamita(EventHandler<ActionEvent> eventHandler) {
		botones.get("dinamita").setOnAction(eventHandler);
	}
	
	public void registrarEscuchaExplosivos(EventHandler<ActionEvent> eventHandler) {
		botones.get("explosivos").setOnAction(eventHandler);
	}
	
	public void registrarEscuchaTeleport(EventHandler<ActionEvent> eventHandler) {
		botones.get("teleport").setOnAction(eventHandler);
	}
	
	public String getInstruccion(int pos) {
		if(pos <= this.instrucciones.size()) {
			return this.instrucciones.get(pos);
		}
		return null;
	}
		
	public TipoUsable getMejora(int pos) {
		if(pos <= this.mejorasDisponibles.size()) {
			return this.mejorasDisponibles.get(pos);
		}
		return null;
	}
	
	public void inicializarVistatiendaDeConsumibles() {
		this.inicializarGridPane();
		this.inicializarBackground();
    	this.crearBotones();
    	this.personalizarBotones();
    	this.crearBotonClose();
	    this.organizarBotonesEnGridpane();
		gridPane.getChildren().addAll(botones.get("tanqueExtra"),botones.get("nanobots"),botones.get("dinamita"),botones.get("explosivos"),botones.get("teleport"));
		gridPane.getStylesheets().add(getClass().getResource("botones.css").toExternalForm());
		sPane = new StackPane();
	    sPane.getChildren().addAll(gridPane,botones.get("close"));
	    StackPane.setMargin(gridPane,new Insets(0,0,0,0));
	    StackPane.setMargin(botones.get("close"),new Insets(0,650,510,0));
	    sPane.setPrefSize(stage.getWidth(), stage.getHeight());
	    sPane.setMaxSize(stage.getWidth(), stage.getHeight());
	}
	
	public void ocultar() {
		if(mostrando) {
			root.getChildren().remove(root.getChildren().size() - 1);
			mostrando = false;
		}
	}
	
	public void mostrar() {
		if(!mostrando) {
			root.getChildren().add(sPane);
			mostrando = true;
		}
	}
}

