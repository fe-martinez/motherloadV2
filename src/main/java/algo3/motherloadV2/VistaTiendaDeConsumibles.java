package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import jugador.Jugador;
import tiendas.TiendaDeConsumibles;

public class VistaTiendaDeConsumibles implements VistaEntidad {
	private static double SIZE_BOTON = 100;
	List<Label> labels = new ArrayList<>();
	List<Background> background = new ArrayList<>();
	List<Image> images = new ArrayList<>();
	List<BackgroundImage> backImg = new ArrayList<>();
	private StackPane sPane;
	Group root;
	GridPane gridPane;
	HBox hbox = new HBox();
	VBox vbox = new VBox();
	boolean mostrando;
	Jugador jugador;
	TiendaDeConsumibles tienda;
	HashMap<String,Button> botones;
	List<String> keys = List.of("tanqueExtra","nanobots","dinamita","explosivos","teleport","close");
	
	Color grisOscuro = Color.rgb(74, 74, 74);
	Color grisPlata = Color.rgb(150, 150, 150);
	Color naranjita = Color.rgb(219, 126, 92);
	
	String mejoraSeleccionada;
	
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
	    GridPane.setConstraints(botones.get("close"), 1, 2);
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

	private void configGridPane() {
		gridPane = new GridPane();
		gridPane.setPrefSize(600,600);
		gridPane.setHgap(30);
		gridPane.setVgap(30);
		gridPane.setPadding(new Insets(50, 0, 50, 10));
		gridPane.setBackground(Background.fill(grisPlata));
	}

	private void inicializarVistaCompra() {
		hbox.setPrefSize(1000, 600);
		hbox.getChildren().add(gridPane);
		hbox.setSpacing(20);
		hbox.setBackground(Background.fill(grisPlata));
		vbox.setBackground(Background.fill(grisOscuro));

		vbox.setPrefSize(400, 600);
		Label esperando = new Label("ELIJA SU OPCION");
		esperando.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
		
		Button boton = new Button("Comprar");
		boton.setFont(Font.font(30));
		boton.setPrefSize(400, 100);
		
		Background botonBG = Background.fill(naranjita);
		boton.setBackground(botonBG);
		boton.setLayoutY(500);
		
		vbox.setAlignment(Pos.BOTTOM_CENTER);
		
		vbox.getChildren().add(esperando);
		vbox.getChildren().add(boton);
		hbox.getChildren().add(vbox);
		
		//Esto después lo seteo como corresponde
		/*boton.setOnAction(e-> {
			if (mejoraSeleccionada == null) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("No ha elegido una mejora");
				a.show();
			}
			else {
				tienda.interactuar(pj, mejoraSeleccionada);
			}
		});
		*/
	}
	
	private void inicializar() {
		this.inicializarBotones();
		configGridPane();
		gridPane.getChildren().addAll(botones.get("tanqueExtra"),botones.get("nanobots"),botones.get("dinamita"),botones.get("explosivos"),botones.get("teleport"),botones.get("close"));
		inicializarVistaCompra();
		root.getChildren().add(hbox);
	 }
	
	public void mostrar() {
		if(!this.mostrando) {
			this.inicializar();
		}
	}
	
	public VistaTiendaDeConsumibles(Stage stage, Group root, Jugador jugador, TiendaDeConsumibles tienda) {
		this.jugador = jugador;
		this.root = root;
		this.mostrando = false;
		this.tienda = tienda;
	}
}

