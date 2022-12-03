package algo3.motherloadV2;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jugador.Jugador;
import mejoras.MejoraInstantanea;
import tiendas.TiendaDeMejoras;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Popup;

public class VistaTiendaDeMejoras implements VistaEntidad{
	//Tanque
	List<String> descripcionesTanque = List.of(
				"Tanque mediano\r\n" + "15 Litros\r\n" + "$750\r\n", 
				"Tanque grande\r\n" + "25 Litros\r\n" + "$2.000\r\n", 
				"Tanque gigante\r\n" + "40 Litros\r\n" + "$5.000\r\n",
				"Tanque titánico\r\n" + "60 Litros\r\n" + "$20.000\r\n",
				"Tanque nahuelito\r\n" + "100 Litros\r\n" + "$20.000\r\n",
				"Mega tanque\r\n" + "150 Litros\r\n" + "$500.000\r\n");
	List<Label> labelsTanque = new ArrayList<Label>();
	List<Button> botonesTanque = new ArrayList<>();
	List<Image> imgsTanque = new ArrayList<>();
	List<BackgroundImage> backImgTanque = new ArrayList<>();
	List<Background> backTanque = new ArrayList<>();
	GridPane gridPaneTanque = new GridPane();
	Tab tanque = new Tab("Tanque");
	
	//Inventario
	List<String> descripcionesInventario = List.of(
				"Inventario intermedio\r\n" + "15 elementos máximo\r\n" + "$750\r\n",
				"Inventario grande\r\n" + "25 elementos máximo\r\n" + "$2.000\r\n",
				"Inventario gigante\r\n" + "40 elementos máximo\r\n" + "$5.000\r\n",
				"Inventario titánico\r\n" + "70 elementos máximo\r\n" + "$20.000\r\n",
				"Inventario nahuelito\r\n" + "80 elementos máximo\r\n" + "$20.000\r\n");
	
	List<Label> labelsInventario = new ArrayList<>();
	List<Button> botonesInventario = new ArrayList<>();
	List<Image> imgsInventario = new ArrayList<>();
	List<BackgroundImage> backImgInventario = new ArrayList<>();
	List<Background> backInventario = new ArrayList<>();
	GridPane gridPaneInventario = new GridPane();
	Tab inventario = new Tab("Inventario");
	
	//MaxHealth
	List<String> descripcionesMaxHealth = List.of(
				"Caso de Hierro\r\n" + "17 de vida\r\n" + "$750\r\n",
				"Caso de Bronce\r\n" + "30 de vida\r\n" + "$2.000\r\n",
				"Caso de Acero\r\n" + "50 de vida\r\n" + "$5.000\r\n",
				"Caso de Platino\r\n" + "80 de vida\r\n" + "$20.000\r\n",
				"Casco de Einstenio\r\n" + "120 de vida\r\n" + "$20.000\r\n",
				"Caso Supremo lml\r\n" + "180 de vida\r\n" + "$500.000\r\n"
			);
	
	List<Label> labelsMaxHealth = new ArrayList<>();
	List<Button> botonesMaxHealth = new ArrayList<>();
	List<Image> imgsMaxHealth = new ArrayList<>();
	List<BackgroundImage> backImgMaxHealth = new ArrayList<>();
	List<Background> backMaxHealth = new ArrayList<>();
	GridPane gridPaneMaxHealth = new GridPane();
	Tab maxHealth = new Tab("Max Health");
	
	//Inicio
	Label labelInicio;
	GridPane gridPaneInicio = new GridPane();
	Tab inicio = new Tab("Inicio");
	
	//Fondo Blanco
	//Image fondoBlanco = new Image("C:\\Users\\Clari\\Documents\\MotherloadV2\\motherloadV2\\src\\rsc\\Tiendas\\TiendaDeMejoras\\arena.jpg",1000,600,true,true);

	//Tabpane
	TabPane tabPane = new TabPane();
	
	//VBox
	VBox vbox = new VBox();
	
	VBox vboxTanque;
	HBox hboxActual;
	
	//Popup

	Popup popup = new Popup();
	
	//Pantalla de inicio:
	
	//Botoncito
	Button button;
	//Scene
	Scene myScene;
	StackPane stackPane;
	//Stage
	Stage myStage;
	
	Color verdeTransparente = Color.rgb(74, 74, 74);
	Color verdeMasOscuro = Color.rgb(150, 150, 150);
	Color rojo = Color.rgb(219, 126, 92);
	
	String mejoraSeleccionada;
	private TiendaDeMejoras tienda;
	private Jugador pj;
	private Group root;
	private AnchorPane ventana;
	private boolean mostrando;
	private VBox vboxInventario;
	private VBox vboxMaxHealth;
	
	private void inicializarLabelsInventario() {
		for(int i = 0; i < 5; i++) {
			Label label = new Label(descripcionesInventario.get(i));
			label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
			labelsInventario.add(label);
		}
	}
	
	private void inicializarLabelsMaxHealth() {
		for(int i = 0; i < 6; i++) {
			Label label = new Label(descripcionesMaxHealth.get(i));
			label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
			labelsMaxHealth.add(label);
		}
	}
	
	private void inicializarLabelsTanque() {
		for(int i = 0; i < 6; i++) {
			Label label = new Label(descripcionesTanque.get(i));
			label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
			labelsTanque.add(label);
		}
	}
		
	private void inicializarImagenesTanque() {
		this.imgsTanque.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150, 150));
		this.imgsTanque.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque2.png",150, 150));
		this.imgsTanque.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque3.png",150, 150));
		this.imgsTanque.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque4.png",150, 150));
		this.imgsTanque.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque5.png",150, 150));
		this.imgsTanque.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque6.png",150, 150));
	}
	
	private void inicializarImagenesInventario() {
		this.imgsInventario.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Inventario/inventario1.png", 150, 150));
		this.imgsInventario.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Inventario/inventario2.png", 150, 150));
		this.imgsInventario.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Inventario/inventario3.png", 150, 150));
		this.imgsInventario.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Inventario/inventario4.png", 150, 150));
		this.imgsInventario.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/Inventario/inventario5.png", 150, 150));
		
	}
	
	private void inicializarImagenesMaxHealth() {
		this.imgsMaxHealth.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/MaxHealth/maxhealth1.png", 150, 150));
		this.imgsMaxHealth.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/MaxHealth/maxhealth2.png", 150, 150));
		this.imgsMaxHealth.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/MaxHealth/maxhealth3.png", 150, 150));
		this.imgsMaxHealth.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/MaxHealth/maxhealth4.png", 150, 150));
		this.imgsMaxHealth.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/MaxHealth/maxhealth5.png", 150, 150));
		this.imgsMaxHealth.add(CreadorDeImagenes.obtenerImagen("../motherloadV2/src/rsc/MaxHealth/maxhealth6.png", 150, 150));
		

		
	}
	
	private void inicializarBackgroundTanque() {
		this.inicializarImagenesTanque();
		for(int i = 0; i < this.imgsTanque.size(); i++) {
			this.backImgTanque.add(new BackgroundImage(this.imgsTanque.get(i),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT));
		}
		for(int i = 0; i < this.backImgTanque.size(); i++) {
			this.backTanque.add(new Background(this.backImgTanque.get(i)));
		}
	}
	
	private void inicializarBackgroundInventario() {
		this.inicializarImagenesInventario();
		for(int i = 0; i < this.imgsInventario.size(); i++) {
			this.backImgInventario.add(new BackgroundImage(this.imgsInventario.get(i),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT));
		}
		for(int i = 0; i < this.backImgInventario.size(); i++) {
			this.backInventario.add(new Background(this.backImgInventario.get(i)));
		}
	}
	
	private void inicializarBackgroundMaxHealth() {
		this.inicializarImagenesMaxHealth();
		for(int i = 0; i < this.imgsMaxHealth.size(); i++) {
			this.backImgMaxHealth.add(new BackgroundImage(this.imgsMaxHealth.get(i),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT));
		}
		for(int i = 0; i < this.backImgMaxHealth.size(); i++) {
			this.backMaxHealth.add(new Background(this.backImgMaxHealth.get(i)));
		}
	}
	
		
	private void inicializarBotonesTanque() {
		this.inicializarBackgroundTanque();
		for(int i = 0; i < 6; i++) {
			this.botonesTanque.add(new Button());
			this.botonesTanque.get(i).setBackground(this.backTanque.get(i));
			this.botonesTanque.get(i).setBorder(Border.stroke(Paint.valueOf("Black")));
			this.botonesTanque.get(i).setPrefSize(200,200);
		}
		
	    GridPane.setConstraints(this.botonesTanque.get(0),0,0);
	    GridPane.setConstraints(this.botonesTanque.get(1),1,0);
	    GridPane.setConstraints(this.botonesTanque.get(2),2,0);
	    GridPane.setConstraints(this.botonesTanque.get(3),0,1);
	    GridPane.setConstraints(this.botonesTanque.get(4),1,1);
	    GridPane.setConstraints(this.botonesTanque.get(5),2,1);
	 
	}
	
	private void inicializarBotonesInventario() {
		this.inicializarBackgroundInventario();
		for(int i = 0; i < 5; i++) {
			this.botonesInventario.add(new Button());
			this.botonesInventario.get(i).setBackground(this.backInventario.get(i));
			this.botonesInventario.get(i).setBorder(Border.stroke(Paint.valueOf("Black")));
			this.botonesInventario.get(i).setPrefSize(200,200);
		}
		
	    GridPane.setConstraints(this.botonesInventario.get(0),0,0);
	    GridPane.setConstraints(this.botonesInventario.get(1),1,0);
	    GridPane.setConstraints(this.botonesInventario.get(2),2,0);
	    GridPane.setConstraints(this.botonesInventario.get(3),0,1);
	    GridPane.setConstraints(this.botonesInventario.get(4),1,1);
	}
	
	private void inicializarBotonesMaxHealth() {
		this.inicializarBackgroundMaxHealth();
		for(int i = 0; i < 6; i++) {
			this.botonesMaxHealth.add(new Button());
			this.botonesMaxHealth.get(i).setBackground(this.backMaxHealth.get(i));
			this.botonesMaxHealth.get(i).setBorder(Border.stroke(Paint.valueOf("Black")));
			this.botonesMaxHealth.get(i).setPrefSize(200,200);
		}
		
	    GridPane.setConstraints(this.botonesMaxHealth.get(0),0,0);
	    GridPane.setConstraints(this.botonesMaxHealth.get(1),1,0);
	    GridPane.setConstraints(this.botonesMaxHealth.get(2),2,0);
	    GridPane.setConstraints(this.botonesMaxHealth.get(3),0,1);
	    GridPane.setConstraints(this.botonesMaxHealth.get(4),1,1);
	    GridPane.setConstraints(this.botonesMaxHealth.get(5),2,1);
	}

	private void inicializarAccionesBotonesTanque() {
		this.inicializarLabelsTanque();
		this.botonesTanque.get(0).setOnAction(e -> {vboxTanque.getChildren().set(0, labelsTanque.get(0)); mejoraSeleccionada = "T1";});
		this.botonesTanque.get(1).setOnAction(e -> {vboxTanque.getChildren().set(0, labelsTanque.get(1)); mejoraSeleccionada = "T2";});
		this.botonesTanque.get(2).setOnAction(e -> {vboxTanque.getChildren().set(0, labelsTanque.get(2)); mejoraSeleccionada = "T3";});
		this.botonesTanque.get(3).setOnAction(e -> {vboxTanque.getChildren().set(0, labelsTanque.get(3)); mejoraSeleccionada = "T4";});
		this.botonesTanque.get(4).setOnAction(e -> {vboxTanque.getChildren().set(0, labelsTanque.get(4)); mejoraSeleccionada = "T5";});
		this.botonesTanque.get(5).setOnAction(e -> {vboxTanque.getChildren().set(0, labelsTanque.get(5)); mejoraSeleccionada = "T6";});
	}
	
	private void inicializarAccionesBotonesInventario() {
		this.inicializarLabelsInventario();
		this.botonesInventario.get(0).setOnAction(e -> {vboxInventario.getChildren().set(0, labelsInventario.get(0)); mejoraSeleccionada = "I1";});
		this.botonesInventario.get(1).setOnAction(e -> {vboxInventario.getChildren().set(0, labelsInventario.get(1)); mejoraSeleccionada = "I2";});
		this.botonesInventario.get(2).setOnAction(e -> {vboxInventario.getChildren().set(0, labelsInventario.get(2)); mejoraSeleccionada = "I3";});
		this.botonesInventario.get(3).setOnAction(e -> {vboxInventario.getChildren().set(0, labelsInventario.get(3)); mejoraSeleccionada = "I4";});
		this.botonesInventario.get(4).setOnAction(e -> {vboxInventario.getChildren().set(0, labelsInventario.get(4)); mejoraSeleccionada = "I5";});
	}
	
	private void inicializarAccionesBotonesMaxHealth() {
		this.inicializarLabelsMaxHealth();
		this.botonesMaxHealth.get(0).setOnAction(e -> {vboxMaxHealth.getChildren().set(0, labelsMaxHealth.get(0)); mejoraSeleccionada = "V1";});
		this.botonesMaxHealth.get(1).setOnAction(e -> {vboxMaxHealth.getChildren().set(0, labelsMaxHealth.get(1)); mejoraSeleccionada = "V2";});
		this.botonesMaxHealth.get(2).setOnAction(e -> {vboxMaxHealth.getChildren().set(0, labelsMaxHealth.get(2)); mejoraSeleccionada = "V3";});
		this.botonesMaxHealth.get(3).setOnAction(e -> {vboxMaxHealth.getChildren().set(0, labelsMaxHealth.get(3)); mejoraSeleccionada = "V4";});
		this.botonesMaxHealth.get(4).setOnAction(e -> {vboxMaxHealth.getChildren().set(0, labelsMaxHealth.get(4)); mejoraSeleccionada = "V5";});
		this.botonesMaxHealth.get(5).setOnAction(e -> {vboxMaxHealth.getChildren().set(0, labelsMaxHealth.get(5)); mejoraSeleccionada = "V6";});
	}
	
	
	
	private void configGridPane(GridPane gridPane) {
		gridPane.setPrefSize(600,600);
		gridPane.setHgap(30);
		gridPane.setVgap(30);
		gridPane.setPadding(new Insets(50, 0, 50, 10));
		gridPane.setBackground(Background.fill(verdeMasOscuro));
	}
	
	//LO DE VBOXTANQUE no se si es estrictamente necesario pasa que para poner los labels necesitaba la referencia.
	//Pero creo que se puede utilizar un unico vbox para los 3.
	private void inicializarTabTanque() {
		this.inicializarBotonesTanque();
		configGridPane(gridPaneTanque);
		gridPaneTanque.getChildren().addAll(botonesTanque);
		HBox hbox = new HBox();
		vboxTanque = new VBox();
		tanque.setContent(inicializarVistaCompra(gridPaneTanque, hbox, vboxTanque));
		tanque.setClosable(false);
	 }
	
	
	private HBox inicializarVistaCompra(GridPane opciones, HBox hbox, VBox vbox) {
		hbox.setPrefSize(1000, 600);
		hbox.getChildren().add(opciones);
		hbox.setSpacing(20);
		hbox.setBackground(Background.fill(verdeMasOscuro));
		vbox.setBackground(Background.fill(verdeTransparente));

		vbox.setPrefSize(400, 600);
		Label esperando = new Label("ELIJA SU OPCION");
		esperando.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
		
		Button boton = new Button("Comprar");
		boton.setFont(Font.font(30));
		boton.setPrefSize(400, 100);
		
		Background botonBG = Background.fill(rojo);
		boton.setBackground(botonBG);
		boton.setLayoutY(500);
		
		vbox.setAlignment(Pos.BOTTOM_CENTER);
		
		vbox.getChildren().add(esperando);
		vbox.getChildren().add(boton);
		hbox.getChildren().add(vbox);
		
		boton.setOnAction(e-> {if (mejoraSeleccionada == null) {
									Alert a = new Alert(AlertType.ERROR);
									a.setContentText("No ha elegido una mejora");
									a.show();
									} else {
										tienda.interactuar(pj, mejoraSeleccionada);
									}
		}
		);
		return hbox;
	}
	
	
	private void inicializarTabMaxHealth() {
		this.inicializarBotonesMaxHealth();
		configGridPane(gridPaneMaxHealth);
		gridPaneMaxHealth.getChildren().addAll(botonesMaxHealth);
		HBox hbox = new HBox();
		vboxMaxHealth = new VBox();
		maxHealth.setContent(inicializarVistaCompra(gridPaneMaxHealth, hbox, vboxMaxHealth));
		maxHealth.setClosable(false);
	}
	
	private void inicializarTabInventario() {
		this.inicializarBotonesInventario();
		configGridPane(gridPaneInventario);
		gridPaneInventario.getChildren().addAll(botonesInventario);
		HBox hbox = new HBox();
		vboxInventario = new VBox();
		inventario.setContent(inicializarVistaCompra(gridPaneInventario, hbox, vboxInventario));
		inventario.setClosable(false);
	}
	
	private void inicializarTabInicio() {
		this.labelInicio = new Label("¡Bienvenido a la tienda de actualizaciones!\r\n"
				+ "Si estás buscando mejorar tu máquina excavadora, ¡has venido al lugar correcto!\r\n"
				+ "Podés navegar por las diferentes categorías de actualización usando los botones de arriba.");
		labelInicio.setFont(new Font(20));
		gridPaneInicio.setPrefSize(1000,600);
		gridPaneInicio.setBackground(Background.fill(Paint.valueOf("White")));
		gridPaneInicio.getChildren().add(labelInicio);
		inicio.setContent(gridPaneInicio);
		inicio.setClosable(false);
	}
	
	
	private void inicializarTabPane() {
		this.inicializarTabInicio();
		this.inicializarTabTanque();
		this.inicializarTabInventario();
		this.inicializarTabMaxHealth();
		this.tabPane.getTabs().addAll(this.inicio,this.tanque,this.maxHealth,this.inventario);
	}
	
	private void inicializarPopup(Group root) {
		this.ventana = new AnchorPane();
		ventana.setLayoutY(768/2 - 300);
		ventana.setLayoutX(1024/2 - 500); //WIDTH / 2 - TAMAÑO VENTANA / 2;
		ventana.setMaxSize(1000, 600);
		ventana.getChildren().add(this.tabPane);
		
		AnchorPane.setTopAnchor(tabPane, 1.0);
        AnchorPane.setRightAnchor(tabPane, 1.0);
        AnchorPane.setLeftAnchor(tabPane, 1.0);
        AnchorPane.setBottomAnchor(tabPane, 1.0);

		
        Button botonClose = new Button("X");
        botonClose.setCancelButton(true);
        ventana.getChildren().add(botonClose);
		AnchorPane.setTopAnchor(botonClose, 3.0);
		AnchorPane.setRightAnchor(botonClose, 5.0);

		botonClose.setOnAction(e -> {this.root.getChildren().remove(this.root.getChildren().size() - 1); this.mostrando = false;});
	}

	
	private void interaccionesTabs() {
		this.inicializarAccionesBotonesTanque();
		this.inicializarAccionesBotonesInventario();
		inicializarAccionesBotonesMaxHealth();
	}
	
	private void inicializarVistaTiendaDeMejoras(Group root) {
		mejoraSeleccionada = null;
		this.inicializarTabPane();
    	this.inicializarPopup(root);
    	this.interaccionesTabs();
    	//No me lo carga, ni idea :P
    	//this.tabPane.setBackground(new Background(new BackgroundImage(fondoBlanco,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, null, null)));
	}
	
	public VistaTiendaDeMejoras(Stage stage, Group root, TiendaDeMejoras tienda, Jugador pj) {
		inicializarVistaTiendaDeMejoras(root);
		this.tienda = tienda;
		this.pj = pj;
		this.root = root;
		this.mostrando = false;
	}
	
	//No sé si está bien así, cuando se crea recibe la referencia al Stage así que debería funcionar, sino bueno, la otra idea es que lo reciba por parámetro
	//Pero si lo vamos a llamar desde otro lado no tiene sentido pasarlo por parámetro xdd
	public void mostrar() {
		if(!this.mostrando) {
			try{
				this.root.getChildren().add(this.ventana);
			}
			catch(IllegalArgumentException e){
				return;
			}
		}
	}

}
