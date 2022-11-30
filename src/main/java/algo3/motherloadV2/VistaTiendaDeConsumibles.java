package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import jugador.Jugador;
import tiendas.TiendaDeConsumibles;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.geometry.*;

public class VistaTiendaDeConsumibles {
	List<Label> labels = new ArrayList<>();
	List<Background> background = new ArrayList<>();
	List<Image> images = new ArrayList<>();
	List<BackgroundImage> backImg = new ArrayList<>();
	private Stage stage;
	private Popup popup = new Popup();
	private StackPane sPane;
	Group root;
	GridPane gridPane;
	//Hay que ponerlos en un HashMap :P
	Button nanobots;
	Button tanqueExtra;
	Button dinamita;
	Button explosivos;
	Button teleport;
	Button close;
	boolean mostrando;
	Jugador jugador;
	TiendaDeConsumibles tienda;
	
	public VistaTiendaDeConsumibles(Stage stage,Group root,Jugador jugador,TiendaDeConsumibles tienda) {
		this.jugador = jugador;
		this.stage = stage;
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
			label.setFont(new Font(14));
			label.setTextAlignment(TextAlignment.RIGHT);
		}
	}
	
	private void inicializarImagenes() {
		
		//Esta es la que va pero a mí no me funciona ????????? :P
		//Escribilo vos de 0 que ahí sí me funca xddd
		/*this.images.add(CreadorDeImagenes.CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tiendas/TiendasDeConsumibles/tanqueExtra.png", 150, 150));
		this.images.add(CreadorDeImagenes.CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tiendas/TiendasDeConsumibles/nanobots.png", 150, 150));
		this.images.add(CreadorDeImagenes.CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tiendas/TiendasDeConsumibles/dinamita.png", 150, 150));
		this.images.add(CreadorDeImagenes.CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tiendas/TiendasDeConsumibles/explosivos.png", 150, 150));
		this.images.add(CreadorDeImagenes.CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tiendas/TiendasDeConsumibles/teleport.png", 150, 150));
		*/
		
		this.images.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.images.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
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
	

	private void inicializarBotones() {
		this.inicializarLabels();
    	this.inicializarBackground();
    	
		//Creo los botones con el tamaño, el fondo, el borde y esas giladas
	    tanqueExtra = new Button();
	    tanqueExtra.setPrefSize(150,150);
	    tanqueExtra.setBackground(this.background.get(0));
	    tanqueExtra.setBorder(Border.stroke(Paint.valueOf("Black")));
	    tanqueExtra.setAlignment(Pos.CENTER);
	    
	    nanobots = new Button();
	    nanobots.setPrefSize(150,150);
	    nanobots.setBackground(this.background.get(1));
	    nanobots.setBorder(Border.stroke(Paint.valueOf("Black")));
	    nanobots.setAlignment(Pos.CENTER);
	    
	    dinamita = new Button();
	    dinamita.setPrefSize(150,150);
	    dinamita.setBackground(this.background.get(2));
	    dinamita.setBorder(Border.stroke(Paint.valueOf("Black")));
	    dinamita.setAlignment(Pos.CENTER);
	    
	    explosivos = new Button();
	    explosivos.setPrefSize(150,150);
	    explosivos.setBackground(this.background.get(3));
	    explosivos.setBorder(Border.stroke(Paint.valueOf("Black")));
	    explosivos.setAlignment(Pos.CENTER);
	    
	    teleport = new Button();
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
	}
	
	private void inicializarGridPane() {
		gridPane = new GridPane();
	    //Este es el que iría :P
	    //Image img = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tiendas/TiendaDeConsumibles/FondoTiendaConsumibles.jpg", 1000, 600);
	   
		//Image img = CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/FondoTiendaConsumibles.jpg", 1000, 600);
	    Image img = CreadorDeImagenes.obtenerImagen("C:\\Users\\Clari\\Documents\\MotherloadV2\\motherloadV2\\src\\rsc\\Tiendas\\TiendaDeConsumibles\\FondoTiendaDeConsumibles.png", 1000, 600);
	    
	    BackgroundImage backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background background = new Background(backgroundImg);
	    gridPane.setBackground(background);
	    
	    this.inicializarBotones();
	    //Agrego todo al pane
	    gridPane.getChildren().addAll(tanqueExtra,nanobots,dinamita,explosivos,teleport);
	    gridPane.setPrefSize(1000,600);
	}
	
	private void inicializarAccionesBotones() {
		//Acciones de cada botón, esto anda bien :P
	    //Cuando pasas por encima el mouse, te tira el label
	    tanqueExtra.setOnMouseEntered(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(0));
	    });
	    
	    nanobots.setOnMouseEntered(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(1));
	    });
	    
	    dinamita.setOnMouseEntered(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(2));
	    });
	    
	    explosivos.setOnMouseEntered(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(3));
	    });
	    
	    teleport.setOnMouseEntered(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(4));
	    });
	    
	    
	    //Cuando sacas el mouse de arriba del botón, se pone el label vacío
	    tanqueExtra.setOnMouseExited(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(5));
	    });
	    
	    nanobots.setOnMouseExited(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(5));
	    });
	    
	    dinamita.setOnMouseExited(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(5));
	    });
	    
	    explosivos.setOnMouseExited(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(5));
	    });
	    
	    teleport.setOnMouseExited(e -> {
	    	sPane.getChildren().remove(1);
	    	sPane.getChildren().add(labels.get(5));
	    });
	    
	    
	    //Compra cuando se hace click (esto después lo anexamos al tp, habría que llamar al método que compra o a algún método que se ocupe de llamar a ese)
	    tanqueExtra.setOnAction(e -> {
	    	tienda.interactuar(jugador,'X');
	    });
	    
	    nanobots.setOnAction(e -> {
	    	tienda.interactuar(jugador,'R');
	    });
	    
	    dinamita.setOnAction(e -> {
	    	tienda.interactuar(jugador,'D');
	    });
	    
	    explosivos.setOnAction(e -> {
	    	System.out.println("Comprando...");
	    });
	    
	    teleport.setOnAction(e -> {
	    	tienda.interactuar(jugador,'T');
	    });
	      
    }
	
	public void inicializarVistatiendaDeConsumibles() {
		this.inicializarGridPane();
		sPane = new StackPane();
	    sPane.getChildren().addAll(gridPane,labels.get(5));
	    sPane.setPrefSize(1000,600);
	    this.inicializarAccionesBotones();
		
	    close = new Button("X");
	    close.setFont(new Font(30));
	    close.setTextFill(Paint.valueOf("White"));
	    close.setBackground(Background.EMPTY);
	    StackPane.setMargin(close,new Insets(0,650,500,0));
	    close.setOnAction(e -> { root.getChildren().remove(root.getChildren().size() - 1); mostrando = false;});
	    sPane.getChildren().add(close);
	    
	    root.getChildren().add(sPane);
	    this.mostrando = true;
	    
	    
	    
	    
	    
	    //StackPane.setMargin(gridPane,new Insets(300,300,0,0));
	    //popup.getContent().add(sPane);
	    //popup.setHeight(600);
	    //popup.setWidth(1000);
	    
	}
	  
	public void mostrar() {
		if(!this.mostrando) {
			this.inicializarVistatiendaDeConsumibles();
		}
	   	//this.popup.show(this.stage);
	}
}

