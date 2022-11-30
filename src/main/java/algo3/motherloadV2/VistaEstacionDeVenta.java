package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jugador.Jugador;

public class VistaEstacionDeVenta{
	List<String> valores = List.of("$5","$10","$25","$50","Fill");
	List<String> list = List.of("Cobre","Bronce","Hierro","Plata","Oro","Diamante");
	List<String> listaDePrecios = List.of("30","50","60","150","300","650");
	Stage stage;
	List<Label> labels;
    StackPane sPane = new StackPane();
    List<Integer> contador = new ArrayList<>();
    Popup popup = new Popup();
    Jugador jugador;
    boolean isShowing = false;
    Image img;
    BackgroundImage backgroundImg;
    Background background;
    Button botonCerrar;
    Button botonVender;
    
	public VistaEstacionDeVenta(Stage stage, Group root,Jugador jugador) {
		this.stage = stage;
		this.jugador = jugador;
		this.inicializar();
	}
	
	public void actualizarVista() {
		//Por ahora no anda bien, quizás sí hay que inicializar todo de nuevo zzzzzzzzzzzz ni idea :P
		this.labels.clear();
		if(!this.jugador.inventarioVacio()) {
			botonVender.setDisable(false);
			int contador = 1;
			for(int i = 0; i < this.jugador.getInventario().getCantidadDeMinerales()-1; i++) {
				if(this.jugador.getInventario().getTipoDeMineral(i) == this.jugador.getInventario().getTipoDeMineral(i+1)) {
					contador++;
				}
				else {
					this.contador.add(contador);
					contador = 1;
				}
			}
			this.contador.add(contador);
			
		    for(int i = 0; i < this.contador.size(); i++) {
		    	if(this.contador.get(i) > 0) {
		    		labels.add(new Label(list.get(i) + " " + this.contador.get(i) + " X " + listaDePrecios.get(i) + " = $" + (Integer.parseInt(listaDePrecios.get(i)) * this.contador.get(i))));
		    	}
		    }
		}
		else {
			botonVender.setDisable(true);
			labels.add(new Label("Inventario vacío :("));
		}
		
	    for(Label label: labels) {
	    	//Estos insets hay que verificarlos porque quedaron de antes :P
	    	StackPane.setMargin(label,new Insets(0,100,0,100));
	    	label.setFont(new Font(20));
	    }
	    StackPane.setMargin(labels.get(0),new Insets(0,425,400,0));
	    this.inicializarAccionesBotones();
	}

	private void inicializarAccionesBotones() {		
		botonVender.setOnAction(e -> {
			jugador.venderMinerales();
		});
		    
		botonCerrar.setOnAction(e -> {
		   	this.popup.hide();
		   	this.isShowing = false;
		});
	}
	
	private void inicializarCaracteristicas() {
		labels = new ArrayList<>();
		labels.add(new Label("Inventario vacío :("));
		StackPane.setMargin(labels.get(0),new Insets(0,425,400,0));
		labels.get(0).setFont(new Font(20));
		
	    botonCerrar = new Button("X");
	    botonCerrar.setFont(new Font(30));
	    botonCerrar.setTextFill(Paint.valueOf("White"));
	    botonCerrar.setBackground(Background.EMPTY);
	    StackPane.setMargin(botonCerrar,new Insets(0,650,500,0));
	 
	    botonVender = new Button("[Vender todo]");
	    botonVender.setFont(new Font(20));
	    //botonVender.setTextFill(Paint.valueOf("White"));
	    botonVender.setBackground(Background.EMPTY);
	    StackPane.setMargin(botonVender,new Insets(400,0,0,0));
	    
	    this.inicializarAccionesBotones();
	}
	
	private void inicializarStackPane() {
		img = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/EstacionVenta.png",800,600);
	    backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    background = new Background(backgroundImg);
		sPane.setBackground(background);
	    sPane.setPrefSize(800,600);
	}
	
	public void inicializar() {
		this.inicializarCaracteristicas();
		botonVender.setDisable(true);
	    this.inicializarStackPane();
	    sPane.getChildren().addAll(labels);
	    sPane.getChildren().addAll(botonVender,botonCerrar);
	    popup.getContent().add(sPane);
	}
	
	public void mostrar() {
		if(!this.isShowing) {
			this.actualizarVista();
			this.popup.show(this.stage);
			this.isShowing = true;
		}
	}
}