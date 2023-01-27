package vistas;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tp.CreadorDeImagenes;

public class VistaEstacionDeMantenimiento {
    String pathFondo;
	Group root;
	Stage myStage;
	boolean mostrando;
	GridPane pane1, pane2, pane3;
	VBox layout;
	Image img;
	BackgroundImage backgroundImg;
	Background background;
	HashMap<String,Button> botones;
	HashMap<String,Image> imagenes;
	HashMap<String,BackgroundImage> buttonBackgroundImage;
	HashMap<String,Background> buttonBackground;
    List<Label> labels;
	List<String> imagenesFondo, imagenesBotones,keys;
	private Rectangle rectCantidad;
	private Rectangle sombra = new Rectangle(200,60);
	double porcentaje;
	private StackPane container;
	
    public VistaEstacionDeMantenimiento(Stage stage, Group root,String pathFondo,List<String> imagenesBotones,List<String> keys){
        myStage = stage;
        this.root = root;
        this.pathFondo = pathFondo;
        this.imagenesBotones = imagenesBotones;
        this.keys = keys;
        this.labels = new ArrayList<>();
        try {
			this.inicializar();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void addLabel(Label label) {
    	this.labels.add(label);
    }
    
    
    public List<String> getKeys() {
    	return this.keys;
    }
    
    public String getKey(int pos) {
    	if(pos <= this.keys.size()) {
    		return keys.get(pos);
    	}
    	return null;
    }

    private void inicializarImagenesBotones() {
		imagenes = new HashMap<>();
		
		for(int i = 0; i < 4; i++) {
			imagenes.put(keys.get(i), CreadorDeImagenes.obtenerImagen(imagenesBotones.get(i), 100, 80));
		}
		
		for(int i = 4; i < 6; i++) {
			imagenes.put(keys.get(i), CreadorDeImagenes.obtenerImagen(imagenesBotones.get(i), 200, 100));
		}
		buttonBackgroundImage = new HashMap<>();
		for(HashMap.Entry<String,Image> pair: imagenes.entrySet()) {
		        buttonBackgroundImage.put(pair.getKey(),new BackgroundImage(pair.getValue(),BackgroundRepeat.NO_REPEAT,null,null,null));
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
			
		botones.get(keys.get(4)).setPrefSize(200,100);
		botones.get(keys.get(5)).setPrefSize(200,100);
	
		this.inicializarFondoBotones();
	}
	
	private void inicializarBotones() {
		botones = new HashMap<>();
		for(String key: keys) {
			botones.put(key,new Button());
		}
		
		this.inicializarCaracteristicasBotones();	
	    /*botones.get(keys.get(0)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(0))); actualizarBarra();});
	    botones.get(keys.get(1)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(1))); actualizarBarra();});
	    botones.get(keys.get(2)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(2))); actualizarBarra();});
	    botones.get(keys.get(3)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(3))); actualizarBarra();});
	    botones.get(keys.get(4)).setOnAction(e -> {this.tienda.vender(this.pj, 100); actualizarBarra();});
	    */
	}
		
	public void inicializarGridPane() {
		GridPane.setConstraints(labels.get(0),0,0);
		GridPane.setConstraints(botones.get(keys.get(0)),0,0);
		GridPane.setConstraints(botones.get(keys.get(1)),1,0);
		GridPane.setConstraints(botones.get(keys.get(2)),0,1);
		GridPane.setConstraints(botones.get(keys.get(3)),1,1);
		GridPane.setConstraints(botones.get(keys.get(4)),0,0);
		GridPane.setConstraints(sombra, 0, 1);
		rectCantidad = new Rectangle();
		GridPane.setConstraints(rectCantidad, 0, 1);
		GridPane.setConstraints(labels.get(1), 0, 1);
		GridPane.setConstraints(botones.get(keys.get(5)),0,2);
		
		pane1.getChildren().clear();
		pane2.getChildren().clear();
		pane3.getChildren().clear();
		pane1.getChildren().add(labels.get(0));
		pane2.getChildren().addAll(botones.get(keys.get(0)),botones.get(keys.get(1)),botones.get(keys.get(2)),botones.get(keys.get(3)));
		pane3.getChildren().addAll(botones.get(keys.get(4)), sombra, rectCantidad , labels.get(1), botones.get(keys.get(5)));
		
		
	}
	
	public void configurarLabels() {
		labels.get(0).setPrefSize(400,60);
		labels.get(0).setFont(new Font(16));
		labels.get(1).setTextFill(Color.WHITE);
		labels.get(1).setAlignment(Pos.CENTER);
		labels.get(1).setBackground(Background.EMPTY);
		labels.get(1).setPrefSize(200,60);
		labels.get(1).setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 16));
	}
	
	public void reinicializar() {
		this.configurarLabels();
		this.inicializarGridPane();
	}
	
	public void actualizarBarra(String text,double porcentaje) {
		labels.get(1).setText(text);
		this.porcentaje = porcentaje;
		rectCantidad = new Rectangle(2 * porcentaje, 60);
		rectCantidad.setFill(Color.RED);
		sombra.setFill(Color.BLACK);
		GridPane.setConstraints(sombra, 0, 1);
		GridPane.setConstraints(rectCantidad, 0, 1);
		GridPane.setConstraints(labels.get(1), 0, 1);
		 
		 pane3.getChildren().clear();
		 pane3.getChildren().addAll(botones.get(keys.get(4)), sombra, rectCantidad , labels.get(1), botones.get(keys.get(5)));
		 
	}
	
	public void registrarEscuchaOpcion1(EventHandler<ActionEvent> eventHandler) {
		botones.get(keys.get(0)).setOnAction(eventHandler);
	}
	
	public void registrarEscuchaOpcion2(EventHandler<ActionEvent> eventHandler) {
		botones.get(keys.get(1)).setOnAction(eventHandler);
	}
	
	public void registrarEscuchaOpcion3(EventHandler<ActionEvent> eventHandler) {
		botones.get(keys.get(2)).setOnAction(eventHandler);
	}
	
	public void registrarEscuchaOpcion4(EventHandler<ActionEvent> eventHandler) {
		botones.get(keys.get(3)).setOnAction(eventHandler);
	}
	
	public void registrarEscuchaOpcion5(EventHandler<ActionEvent> eventHandler) {
		botones.get(keys.get(4)).setOnAction(eventHandler);
	}
	

	
	public void registrarEscuchaClose(EventHandler<ActionEvent> eventHandler) {
		botones.get(keys.get(5)).setOnAction(eventHandler);
	}
	
	
	public void inicializar() throws FileNotFoundException {
		layout = new VBox();
		img = new Image(new FileInputStream(pathFondo), 800, 600, false, false);
		backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,null, null, null);
		background = new Background(backgroundImg);
		
		layout.getStylesheets().add(
                getClass().getResource("botones.css").toExternalForm()
        );
		layout.setBackground(background);
		
		pane1 = new GridPane();
		pane2 = new GridPane();
		pane3 = new GridPane();
		
		this.inicializarBotones();
		
		VBox.setMargin(pane1,new Insets(100,0,0,450));
		VBox.setMargin(pane2,new Insets(0,0,0,450));
		VBox.setMargin(pane3,new Insets(0,200,100,450));
		
		layout.setPrefSize(800, 600);
		layout.setMaxSize(800, 600);
		
		layout.getChildren().addAll(pane1,pane2,pane3);
		botones.get(keys.get(5)).setCancelButton(true);
		
		container = new StackPane();
		container.setMaxSize(myStage.getWidth(), myStage.getHeight());
		container.setPrefSize(myStage.getWidth(), myStage.getHeight());
		container.setAlignment(Pos.CENTER);
		container.getChildren().add(layout);
	 }
	
	 public void mostrar() {
		 if(!mostrando) {
			root.getChildren().add(container);
			this.mostrando = true;
		}
	 }
	 
	public void ocultar() {
		if(mostrando) {
			root.getChildren().remove(root.getChildren().size() -1);
			this.mostrando = false;
		}
	}
}
