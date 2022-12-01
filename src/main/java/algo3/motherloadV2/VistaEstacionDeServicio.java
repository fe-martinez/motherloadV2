package algo3.motherloadV2;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import jugador.Jugador;
import tiendas.EstacionDeServicio;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class VistaEstacionDeServicio implements VistaEntidad {
	final static String PATH_FONDO = "../motherloadV2/src/rsc/Fuel-512.png";
	Group root;
	Stage myStage;
	boolean mostrando;
	GridPane pane1, pane2, pane3;
	VBox layout;
	Image img;
	BackgroundImage backgroundImg;
	Background background;
	Label labelInstrucciones, labelCombustible;
	HashMap<String,Button> botones;
	HashMap<String,Image> imagenes;
	HashMap<String,BackgroundImage> buttonBackgroundImage;
	HashMap<String,Background> buttonBackground;
	List<String> keys = List.of("5","10","25","50","Fill","Close");
	EstacionDeServicio tienda;
	Jugador pj;
	
	//Esta lista es la que va
	List<String> imagePath = List.of("../motherloadV2/src/rsc/Tiendas/Botones/button5.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/button10.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/button25.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/button50.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/fill.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/close.png");
	
	public VistaEstacionDeServicio(Stage stage, Group root, EstacionDeServicio tienda, Jugador pj) {
		this.myStage = stage;
		this.root = root;
		this.mostrando = false;
		this.tienda = tienda;
		this.pj = pj;
	}
	
	private void inicializarImagenesBotones() {
		imagenes = new HashMap<>();
		
		for(int i = 0; i < 4; i++) {
			imagenes.put(keys.get(i), CreadorDeImagenes.obtenerImagen(imagePath.get(i), 100, 80));
		}
		
		for(int i = 4; i < 6; i++) {
			imagenes.put(keys.get(i), CreadorDeImagenes.obtenerImagen(imagePath.get(i), 200, 100));
		}
		
		/*Esta versión es más elegante pero no sé por qué no anda bien :P
		buttonBackgroundImage = new HashMap<>();
		for(int i = 0; i < keys.size(); i++) {
			buttonBackgroundImage.put(keys.get(i),new BackgroundImage(imagenes.get(keys.get(i)),null,null,null,null));
		}
		buttonBackground = new HashMap<>();
		for(int i = 0; i < keys.size(); i++) {
			buttonBackground.put(keys.get(i),new Background(buttonBackgroundImage.get(keys.get(i))));
		}
		*/
		
		//Esta es la versión que anda, no está mal pero me gusta mucho más la versión anterior así que si la arreglás mejor :P
		buttonBackgroundImage = new HashMap<>();
		for(HashMap.Entry<String,Image> pair: imagenes.entrySet()) {
		        buttonBackgroundImage.put(pair.getKey(),new BackgroundImage(pair.getValue(),null,null,null,null));
		}
		
		buttonBackground = new HashMap<>();
		for(HashMap.Entry<String,BackgroundImage> pair: buttonBackgroundImage.entrySet()) {
		        buttonBackground.put(pair.getKey(),new Background(pair.getValue()));
		}
		
	}
	
	private void inicializarFondoBotones() {
		this.inicializarImagenesBotones();
		for(HashMap.Entry<String,Button> pair: botones.entrySet()) {
			pair.getValue().setBackground(buttonBackground.get(pair.getKey()));
		}
	}
	
	private void inicializarCaracteristicasBotones() {
		for(HashMap.Entry<String,Button> pair: botones.entrySet()) {
	        pair.getValue().setPrefSize(100,80);
		}
		botones.get("Fill").setPrefSize(200,100);
		botones.get("Close").setPrefSize(200,100);
	
		this.inicializarFondoBotones();
	}
	
	private void inicializarBotones() {
		botones = new HashMap<>();
		for(String key: keys) {
			botones.put(key,new Button());
		}
		
		this.inicializarCaracteristicasBotones();
	
	}
	
	private void inicializarGridPane() {
		this.inicializarBotones();
		
		pane1 = new GridPane();
		pane2 = new GridPane();
		pane3 = new GridPane();
		GridPane.setConstraints(labelInstrucciones,0,0);
		GridPane.setConstraints(botones.get("5"),0,0);
		GridPane.setConstraints(botones.get("10"),1,0);
		GridPane.setConstraints(botones.get("25"),0,1);
		GridPane.setConstraints(botones.get("50"),1,1);
		GridPane.setConstraints(botones.get("Fill"),0,0);
		GridPane.setConstraints(labelCombustible, 0, 1);
		GridPane.setConstraints(botones.get("Close"),0,2);
		
		pane1.getChildren().add(labelInstrucciones);
		pane2.getChildren().addAll(botones.get("5"),botones.get("10"),botones.get("25"),botones.get("50"));
		pane3.getChildren().addAll(botones.get("Fill"),labelCombustible,botones.get("Close"));
		
		
	}
	
	private void inicializarLabels() {
		labelInstrucciones = new Label("Elija la cantidad de combustible que desea cargar: ");
		labelInstrucciones.setPrefSize(400,60);
		labelInstrucciones.setFont(new Font(16));
		
		int variable = 5;
		labelCombustible = new Label("Nivel de combustible: " + variable);
		labelCombustible.setBackground(Background.fill(Paint.valueOf("White")));
		labelCombustible.setPrefSize(500,60);
		labelCombustible.setTextAlignment(TextAlignment.CENTER);
		labelCombustible.setPrefSize(200,60);
		labelCombustible.setFont(new Font(16));
	}
	
	private void inicializarLayout() throws FileNotFoundException {
		layout = new VBox();
		img = new Image(new FileInputStream(VistaEstacionDeServicio.PATH_FONDO), 800, 600, false, true);
		backgroundImg = new BackgroundImage(img, null, null, null, null);
		background = new Background(backgroundImg);
		
		layout.setAlignment(Pos.CENTER);
		
		this.inicializarLabels();
		this.inicializarGridPane();
		
		VBox.setMargin(pane1,new Insets(100,0,0,400));
		VBox.setMargin(pane2,new Insets(0,0,0,400));
		VBox.setMargin(pane3,new Insets(0,200,100,400));
		layout.setBackground(background);
		layout.getChildren().addAll(pane1,pane2,pane3);
		layout.setLayoutX((VistaJuego.WIDTH - 900) / 2);
		layout.setLayoutY((VistaJuego.HEIGHT - 600) / 2);
		
	}
	
	public void inicializar() throws FileNotFoundException {
		this.inicializarLayout();   
		botones.get("Close").setOnAction(e -> { root.getChildren().remove(root.getChildren().size() - 1); mostrando = false;});
	    root.getChildren().add(layout);
	    this.mostrando = true;

	 }
	
	 public void mostrar() {
	    //Lo de mostando era porque se apilaban las ventanas mientras el jugador estaba parado encima de la tienda
	    //Hay que encontrar una solucion mejor!
	    if(!this.mostrando) {
		    try {
		    	this.inicializar();
			    botones.get("5").setOnAction(e -> this.tienda.interactuar(pj, 5));
		    } catch (FileNotFoundException e) {
		    	e.printStackTrace();
		    }
	    }
	 }
}