package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.event.*;
import javafx.geometry.*;

public class VistaTiendaDeConsumibles {
	List<Label> labels = new ArrayList<>();
	List<Background> background = new ArrayList<>();
	List<Image> images = new ArrayList<>();
	List<BackgroundImage> backImg = new ArrayList<>();
	private Stage stage;
	private Popup popup;
	
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
	}
	
	private void inicializarImagenes() {
		this.images.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
	}
	
	private void inicializarBackground() {
		for(int i = 0; i < this.images.size(); i++) {
			this.backImg.add(new BackgroundImage(this.images.get(i),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT));
		}
		for(int i = 0; i < this.backImg.size(); i++) {
			this.background.add(new Background(this.backImg.get(i)));
		}
	}
	
	public VistaTiendaDeConsumibles(Stage stage) {
		this.inicializarVistatiendaDeConsumibles(stage);
		this.stage = stage;
	}
	
	public void inicializarVistatiendaDeConsumibles(Stage stage) {
		//Son todas variables locales, hay que hacerlas atributos de la clase xdd
		
		this.inicializarLabels();
    	this.inicializarImagenes();
    	this.inicializarBackground();
    	
	    GridPane pane1;
	    VBox layout = new VBox();
	    pane1 = new GridPane();
	    Image img = obtenerImagen("../motherloadV2/src/rsc/FondoTiendaConsumibles.jpg", 1000, 600);
	    BackgroundImage backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background background = new Background(backgroundImg);
	    pane1.setBackground(background);
	    
	    //Creo los botones con el tamaño, el fondo, el borde y esas giladas
	    Button tanqueExtra = new Button();
	    tanqueExtra.setPrefSize(150,150);
	    tanqueExtra.setBackground(this.background.get(0));
	    tanqueExtra.setBorder(Border.stroke(Paint.valueOf("Black")));
	    tanqueExtra.setAlignment(Pos.CENTER);
	    Button nanobots = new Button();
	    nanobots.setPrefSize(150,150);
	    nanobots.setBackground(this.background.get(1));
	    nanobots.setBorder(Border.stroke(Paint.valueOf("Black")));
	    nanobots.setAlignment(Pos.CENTER);
	    Button dinamita = new Button();
	    dinamita.setPrefSize(150,150);
	    dinamita.setBackground(this.background.get(2));
	    dinamita.setBorder(Border.stroke(Paint.valueOf("Black")));
	    dinamita.setAlignment(Pos.CENTER);
	    Button explosivos = new Button();
	    explosivos.setPrefSize(150,150);
	    explosivos.setBackground(this.background.get(3));
	    explosivos.setBorder(Border.stroke(Paint.valueOf("Black")));
	    explosivos.setAlignment(Pos.CENTER);
	    Button teleport = new Button();
	    teleport.setPrefSize(150,150);
	    teleport.setBackground(this.background.get(4));
	    teleport.setBorder(Border.stroke(Paint.valueOf("Black")));
	    teleport.setAlignment(Pos.CENTER);
	   
	    //Les pongo la posición en el gridpane
	    GridPane.setConstraints(tanqueExtra,0,0);
	    GridPane.setConstraints(nanobots,1,0);
	    GridPane.setConstraints(dinamita,2,0);
	    GridPane.setConstraints(explosivos,0,1);
	    GridPane.setConstraints(teleport,1,1);
	    
	   
	    //Agrego todo al pane
	    pane1.getChildren().addAll(tanqueExtra,nanobots,dinamita,explosivos,teleport);
	    pane1.setPrefSize(1000,600);

	    //Pongo el pane y el label en el vbox xd
	    layout.getChildren().addAll(pane1,labels.get(5));
	    VBox.setMargin(pane1,new Insets(0,0,0,0));

	    //Acciones de cada botón, esto anda bien :P
	    //Cuando pasas por encima el mouse, te tira el label
	    tanqueExtra.setOnMouseEntered(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(0));
	    });
	    
	    nanobots.setOnMouseEntered(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(1));
	    });
	    
	    dinamita.setOnMouseEntered(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(2));
	    });
	    
	    explosivos.setOnMouseEntered(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(3));
	    });
	    
	    teleport.setOnMouseEntered(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(4));
	    });
	    
	    
	    //Cuando sacas el mouse de arriba del botón, se pone el label vacío
	    tanqueExtra.setOnMouseExited(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(5));
	    });
	    
	    nanobots.setOnMouseExited(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(5));
	    });
	    
	    dinamita.setOnMouseExited(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(5));
	    });
	    
	    explosivos.setOnMouseExited(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(5));
	    });
	    
	    teleport.setOnMouseExited(e -> {
	    	layout.getChildren().remove(1);
	    	layout.getChildren().add(labels.get(5));
	    });
	    
	    
	    //Compra cuando se hace click (esto después lo anexamos al tp, habría que llamar al método que compra o a algún método que se ocupe de llamar a ese)
	    tanqueExtra.setOnAction(e -> {
	    	System.out.println("Comprando...");
	    });
	    
	    nanobots.setOnAction(e -> {
	    	System.out.println("Comprando...");
	    });
	    
	    dinamita.setOnAction(e -> {
	    	System.out.println("Comprando...");
	    });
	    
	    explosivos.setOnAction(e -> {
	    	System.out.println("Comprando...");
	    });
	    
	    teleport.setOnAction(e -> {
	    	System.out.println("Comprando...");
	    });
	    
	    //Armo el vbox con las cosas y armo el popup con las cosas y le meto el vbox
	    //El vbox tiene: arriba el gridpane con los botones y abajo el label correspondiente (puede ser info o vacío, depende de si pasás el mouse por encima de los botones o no)
	    layout.setPrefSize(1000,600);
	    layout.setAlignment(Pos.CENTER);
	    Popup popup = new Popup();
	    popup.getContent().add(layout);
	    popup.setHeight(600);
	    popup.setWidth(1000);
	    this.popup = popup;
	    
	    //Un pane cualquiera que hice para que toques el botón y te salga el popup, pero en realidad normalmente sería el juego xd
	   /* Pane stackPane = new StackPane();
	    Button button = new Button("Holi");
	    stackPane.getChildren().add(button);
	    button.setOnAction(e -> {popup.show(stage);});
	   
	    Scene scene = new Scene(stackPane);
	    stage.setScene(scene);
	    */
	}
	
	private static Image obtenerImagen(String nombre, double width, double height) {
		Image image1 = null;
		try {
			image1 = new Image(new FileInputStream(nombre), width, height, true, false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image1;
	}
	  
	public void mostrar() {
	   	this.popup.show(this.stage);
	}
}

