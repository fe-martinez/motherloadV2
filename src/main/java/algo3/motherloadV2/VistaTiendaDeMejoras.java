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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

public class VistaTiendaDeMejoras {
	//Tanque
	List<Label> labelsTanque = new ArrayList<>();
	List<Button> botonesTanque = new ArrayList<>();
	List<Image> imgsTanque = new ArrayList<>();
	List<BackgroundImage> backImgTanque = new ArrayList<>();
	List<Background> backTanque = new ArrayList<>();
	GridPane gridPaneTanque = new GridPane();
	Tab tanque = new Tab("Tanque");
	
	//Inventario
	List<Label> labelsInventario = new ArrayList<>();
	List<Button> botonesInventario = new ArrayList<>();
	List<Image> imgsInventario = new ArrayList<>();
	List<BackgroundImage> backImgInventario = new ArrayList<>();
	List<Background> backInventario = new ArrayList<>();
	GridPane gridPaneInventario = new GridPane();
	Tab inventario = new Tab("Inventario");
	
	//MaxHealth
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
	Image fondoBlanco = new Image("https://fondosmil.com/fondo/17538.jpg",1000,600,true,true);

	//Tabpane
	TabPane tabPane = new TabPane();
	
	//VBox
	VBox vbox = new VBox();
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
	
	
	private void inicializarLabelsInventario() {
		labelsInventario.add(new Label("Inventario intermedio\r\n" + "15 elementos máximo\r\n" + "$750\r\n"));
		labelsInventario.add(new Label("Inventario grande\r\n" + "25 elementos máximo\r\n" + "$2.000\r\n"));
		labelsInventario.add(new Label("Inventario gigante\r\n" + "40 elementos máximo\r\n" + "$5.000\r\n"));
		labelsInventario.add(new Label("Inventario titánico\r\n" + "70 elementos máximo\r\n" + "$20.000\r\n"));
		labelsInventario.add(new Label("Inventario nahuelito\r\n" + "80 elementos máximo\r\n" + "$20.000\r\n"));
		labelsInventario.add(new Label());
	}
	
	private void inicializarLabelsMaxHealth() {
		labelsMaxHealth.add(new Label("Caso de Hierro\r\n" + "17 de vida\r\n" + "$750\r\n"));
		labelsMaxHealth.add(new Label("Caso de Bronce\r\n" + "30 de vida\r\n" + "$2.000\r\n"));
		labelsMaxHealth.add(new Label("Caso de Acero\r\n" + "50 de vida\r\n" + "$5.000\r\n"));
		labelsMaxHealth.add(new Label("Caso de Platino\r\n" + "80 de vida\r\n" + "$20.000\r\n"));
		labelsMaxHealth.add(new Label("Casco de Einstenio\r\n" + "120 de vida\r\n" + "$20.000\r\n"));
		labelsMaxHealth.add(new Label("Caso Supremo lml\r\n" + "180 de vida\r\n" + "$500.000\r\n"));
		labelsMaxHealth.add(new Label());
	}
	
	private void inicializarLabelsTanque() {
		labelsTanque.add(new Label("Tanque mediano\r\n" + "15 Litros\r\n" + "$750\r\n"));
		labelsTanque.add(new Label("Tanque grande\r\n" + "25 Litros\r\n" + "$2.000\r\n"));
		labelsTanque.add(new Label("Tanque gigante\r\n" + "40 Litros\r\n" + "$5.000\r\n"));
		labelsTanque.add(new Label("Tanque titánico\r\n" + "60 Litros\r\n" + "$20.000\r\n"));
		labelsTanque.add(new Label("Tanque nahuelito\r\n" + "100 Litros\r\n" + "$20.000\r\n"));
		labelsTanque.add(new Label("Mega tanque\r\n" + "150 Litros\r\n" + "$500.000\r\n"));
		labelsTanque.add(new Label());
	}
		
	private void inicializarImagenesTanque() {
		this.imgsTanque.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsTanque.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque2.png",150));
		this.imgsTanque.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque3.png",150));
		this.imgsTanque.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque4.png",150));
		this.imgsTanque.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque5.png",150));
		this.imgsTanque.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque6.png",150));
	}
	
	private void inicializarImagenesInventario() {
		this.imgsInventario.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsInventario.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsInventario.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsInventario.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsInventario.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		
	}
	
	private void inicializarImagenesMaxHealth() {
		this.imgsMaxHealth.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsMaxHealth.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsMaxHealth.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsMaxHealth.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsMaxHealth.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		this.imgsMaxHealth.add(obtenerImagen("../motherloadV2/src/rsc/Tanques/tanque.png", 150));
		
	}
	
	private static Image obtenerImagen(String nombre, double size) {
		Image image1 = null;
		try {
			image1 = new Image(new FileInputStream(nombre), size, size, true, false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image1;
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
			this.botonesTanque.get(i).setBackground(this.backTanque.get(0));
			this.botonesTanque.get(i).setBorder(Border.stroke(Paint.valueOf("Black")));
			this.botonesTanque.get(i).setPrefSize(150,150);
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
			this.botonesInventario.get(i).setBackground(this.backInventario.get(0));
			this.botonesInventario.get(i).setBorder(Border.stroke(Paint.valueOf("Black")));
			this.botonesInventario.get(i).setPrefSize(150,150);
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
			this.botonesMaxHealth.get(i).setBackground(this.backMaxHealth.get(0));
			this.botonesMaxHealth.get(i).setBorder(Border.stroke(Paint.valueOf("Black")));
			this.botonesMaxHealth.get(i).setPrefSize(150,150);
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
		
		this.botonesTanque.get(0).setOnMouseEntered(e -> {
			this.vbox.getChildren().remove(1);
			this.vbox.getChildren().add(labelsTanque.get(0));
	    });
		
		this.botonesTanque.get(1).setOnMouseEntered(e -> {
			this.vbox.getChildren().remove(1);
			this.vbox.getChildren().add(labelsTanque.get(1));
	    });
		
		this.botonesTanque.get(2).setOnMouseEntered(e -> {
			this.vbox.getChildren().remove(1);
			this.vbox.getChildren().add(labelsTanque.get(2));
	    });
		
		this.botonesTanque.get(2).setOnMouseEntered(e -> {
			this.vbox.getChildren().remove(1);
			this.vbox.getChildren().add(labelsTanque.get(2));
	    });
		
		this.botonesTanque.get(3).setOnMouseEntered(e -> {
			this.vbox.getChildren().remove(1);
			this.vbox.getChildren().add(labelsTanque.get(3));
	    });
		
		this.botonesTanque.get(4).setOnMouseEntered(e -> {
			this.vbox.getChildren().remove(1);
			this.vbox.getChildren().add(labelsTanque.get(4));
	    });
		
		for(int i = 0; i < this.botonesTanque.size(); i++) {
			this.botonesTanque.get(i).setOnMouseExited(e -> {
				this.vbox.getChildren().remove(1);
				this.vbox.getChildren().add(labelsTanque.get(6));
		    });
		}
		
		for(int i = 0; i < this.botonesTanque.size(); i++) {
			this.botonesTanque.get(i).setOnAction(e -> {
		    	System.out.println("Comprando...");
		    });
		}
	      
	}
	
	private void inicializarTabTanque() {
		this.inicializarBotonesTanque();
		gridPaneTanque.setPrefSize(1000,600);
		gridPaneTanque.setBackground(Background.fill(Paint.valueOf("White")));
	    gridPaneTanque.getChildren().addAll(botonesTanque);
	    tanque.setContent(gridPaneTanque);
		tanque.setClosable(false);
	 }
	
	private void inicializarTabMaxHealth() {
		this.inicializarBotonesMaxHealth();
		gridPaneMaxHealth.setPrefSize(1000,600);
		gridPaneMaxHealth.setBackground(Background.fill(Paint.valueOf("White")));
		gridPaneMaxHealth.getChildren().addAll(botonesMaxHealth);
		maxHealth.setContent(gridPaneMaxHealth);
		maxHealth.setClosable(false);
	}
	
	private void inicializarTabInventario() {
		this.inicializarBotonesInventario();
		gridPaneInventario.setPrefSize(1000,600);
		gridPaneInventario.setBackground(Background.fill(Paint.valueOf("White")));
		gridPaneInventario.getChildren().addAll(botonesInventario);
		inventario.setContent(gridPaneInventario);
		inventario.setClosable(false);
	}
	
	private void inicializarTabInicio() {
		this.labelInicio = new Label("¡Bienvenido a la tienda de actualizaciones!\r\n"
				+ "Si estás buscando mejorar tu máquina excavadora, ¡has venido al lugar correcto!\r\n"
				+ "Podés navegar por las diferentes categorías de actualización usando los botones de arriba.");
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
	
	private void inicializarPopup() {
		this.vbox.getChildren().add(this.tabPane);
		this.popup.setWidth(1000);
		this.popup.setHeight(600);
		this.popup.getContent().add(this.vbox);
	}

	
	private void interaccionesTabs() {
		if(tanque.isSelected()) {
			this.inicializarAccionesBotonesTanque();
		}
	}
	
	private void inicializarVistaTiendaDeMejoras(Stage stage) {
		this.myStage = stage;
		this.inicializarTabPane();
    	this.inicializarPopup();
    	this.interaccionesTabs();
    	
 	      
	    button = new Button("Holi");
	    stackPane = new StackPane();
 	    stackPane.getChildren().add(button);
 	    button.setOnAction(e -> {popup.show(myStage);});
 	    
	    myScene = new Scene(stackPane);
	    myStage.setScene(myScene);
	}
	
	public VistaTiendaDeMejoras(Stage stage) {
		inicializarVistaTiendaDeMejoras(stage);
	}
	
	//No sé si está bien así, cuando se crea recibe la referencia al Stage así que debería funcionar, sino bueno, la otra idea es que lo reciba por parámetro
	//Pero si lo vamos a llamar desde otro lado no tiene sentido pasarlo por parámetro xdd
	public void mostrar() {
		this.popup.show(myStage);
	}

}