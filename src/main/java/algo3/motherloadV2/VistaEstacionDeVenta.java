package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jugador.Jugador;

public class VistaEstacionDeVenta implements VistaEntidad{
	List<String> valores = List.of("$5","$10","$25","$50","Fill");
	List<String> list = List.of("Cobre","Bronce","Hierro","Plata","Oro","Diamante");
	List<String> listaDePrecios = List.of("30","50","60","150","300","650");
	Stage stage;
	List<Label> labels;
    AnchorPane anchorPane;
    List<Integer> contador = new ArrayList<>();
    Popup popup = new Popup();
    Jugador jugador;
    Image img;
    BackgroundImage backgroundImg;
    Background background;
    Button botonCerrar;
    Button botonVender;
	private VBox cajaLista;
    
	public VistaEstacionDeVenta(Stage stage, Group root, Jugador jugador) {
		this.stage = stage;
		this.jugador = jugador;
		this.inicializarCaracteristicas();
	    this.inicializarStackPane();
	    this.inicializarAnchorPane();
	}
	
	public void actualizarVista() {
		this.inicializarCaracteristicas();
		labels.clear();
		if(!this.jugador.inventarioVacio()) {
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
			labels.add(new Label("Inventario vacío :("));
		}
		
	    for(Label label: labels) {
	    	label.setFont(new Font(20));
	    }
	    this.inicializarStackPane();
	    this.inicializarAnchorPane();
	    this.contador.clear();
	}
	
	private void inicializarCaracteristicas() {
		labels = new ArrayList<>();
		labels.add(new Label("Inventario vacío :("));
		labels.get(0).setFont(new Font(20));
		
	    botonCerrar = new Button("X");
	    botonCerrar.setFont(new Font(30));
	    botonCerrar.setTextFill(Paint.valueOf("White"));
	    botonCerrar.setBackground(Background.EMPTY);
	 
	    botonVender = new Button("[Vender todo]");
	    botonVender.setFont(new Font(20));
	    botonVender.setTextFill(Paint.valueOf("White"));
	    botonVender.setBackground(Background.EMPTY);
	    
	    botonCerrar.setOnAction(e -> {
		   	this.popup.hide();
		});
	    
	    botonVender.setOnAction(e -> {this.jugador.venderMinerales();
	    		this.actualizarVista();
	    });
	}
	
	private void inicializarStackPane() {
		anchorPane = new AnchorPane();
		img = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/EstacionVenta.png",800,600);
	    backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    background = new Background(backgroundImg);
		anchorPane.setBackground(background);
	    anchorPane.setPrefSize(800,600);
	}
	
	public void inicializarAnchorPane() {
		cajaLista = new VBox();
	    cajaLista.getChildren().addAll(labels);
	    cajaLista.setPrefSize(400,400);
	    anchorPane.getChildren().add(cajaLista);
	    anchorPane.getChildren().addAll(botonCerrar,botonVender);
	    popup.getContent().add(anchorPane);
	
	    AnchorPane.setTopAnchor(cajaLista, 100.0);
        AnchorPane.setLeftAnchor(cajaLista, 100.0);
        
		AnchorPane.setBottomAnchor(botonVender,80.0);
		AnchorPane.setLeftAnchor(botonVender, 350.0);
        AnchorPane.setTopAnchor(botonCerrar, 3.0);
        AnchorPane.setRightAnchor(botonCerrar, 5.0);
	}
	
	public void mostrar() {
		this.actualizarVista();
		this.popup.show(this.stage);
	}
}
