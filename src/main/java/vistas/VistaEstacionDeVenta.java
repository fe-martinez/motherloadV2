package vistas;

import java.util.ArrayList;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tp.CreadorDeImagenes;


public class VistaEstacionDeVenta {
	List<String> valores = List.of("$5","$10","$25","$50","Fill");
	List<String> list = List.of("Cobre","Bronce","Hierro","Plata","Oro","Diamante");
	List<String> listaDePrecios = List.of("30","50","60","150","300","650");
	Stage stage;
	List<Label> labels;
    AnchorPane anchorPane;
    Image img;
    BackgroundImage backgroundImg;
    Background background;
    public Button botonCerrar;
    public Button botonVender;
	private VBox cajaLista;
	private StackPane container;
	private Group root;
	boolean mostrando;
    
	public VistaEstacionDeVenta(Stage stage, Group root) {
		this.stage = stage;
		this.root = root;
		this.inicializarBotones();
	    this.inicializarAnchorPane();
	    mostrando = false;
	}
	
	public void actualizarVista(List<Label> labels) {
		this.cerrar();
		
		this.labels.clear();
		this.labels = labels;
		
	    for(Label label: this.labels) {
	    	label.setFont(new Font(20));
	    }

	    this.inicializarAnchorPane();
	    this.mostrar();
	}
	
	public void registrarEscuchaVenta(EventHandler<ActionEvent> eventHandler) {
		botonVender.setOnAction(eventHandler);
	}
	
	public void registrarEscuchaClose(EventHandler<ActionEvent> eventHandler) {
		botonCerrar.setOnAction(eventHandler);
	}
	
	private void inicializarBotones() {
		labels = new ArrayList<>();
		labels.add(new Label("Inventario vacío :("));
		labels.get(0).setFont(new Font(20));
		
	    botonCerrar = new Button("X");
	    botonCerrar.setFont(new Font(30));
	    botonCerrar.setTextFill(Paint.valueOf("White"));
	    botonCerrar.setBackground(Background.EMPTY);
	    botonCerrar.setCancelButton(true);
	 
	    botonVender = new Button("[Vender todo]");
	    botonVender.setFont(new Font(20));
	    botonVender.setTextFill(Paint.valueOf("White"));
	    botonVender.setBackground(Background.EMPTY);
	}
	
	public void inicializarAnchorPane() {
		anchorPane = new AnchorPane();
		img = CreadorDeImagenes.obtenerImagen("src/rsc/EstacionVenta.png",800,600);
	    backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    background = new Background(backgroundImg);
		anchorPane.setBackground(background);
	    anchorPane.setPrefSize(800,600);
	    anchorPane.setMaxSize(800,600);
		
		cajaLista = new VBox();
	    cajaLista.getChildren().addAll(labels);
	    cajaLista.setPrefSize(400,400);

	   
	    AnchorPane.setTopAnchor(cajaLista, 100.0);
        AnchorPane.setLeftAnchor(cajaLista, 100.0);
        
		AnchorPane.setBottomAnchor(botonVender, 80.0);
		AnchorPane.setLeftAnchor(botonVender, 350.0);
        AnchorPane.setTopAnchor(botonCerrar, 3.0);
        AnchorPane.setRightAnchor(botonCerrar, 5.0);
        
	    anchorPane.getChildren().add(cajaLista);
	    anchorPane.getChildren().addAll(botonCerrar,botonVender);
        
		container = new StackPane();
		container.getStylesheets().add(getClass().getResource("botones.css").toExternalForm());
		container.setMaxSize(stage.getWidth(), stage.getHeight());
		container.setPrefSize(stage.getWidth(), stage.getHeight());
		container.setAlignment(Pos.CENTER);
		container.getChildren().add(anchorPane);
	}
	
	
	public void cerrar() {
		if(this.mostrando) {
			root.getChildren().remove(root.getChildren().size() - 1);
			this.mostrando = false;
		}
	}
	
	public void mostrar() {
		if(!this.mostrando) {
			this.root.getChildren().add(container);
			this.mostrando = true;
		}
	}
}
