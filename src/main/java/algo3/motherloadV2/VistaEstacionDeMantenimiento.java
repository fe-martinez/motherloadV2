package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tiendas.EstacionDeMantenimiento;
import tiendas.EstacionDeServicio;
import jugador.Jugador;

public class VistaEstacionDeMantenimiento implements VistaEntidad {
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
	EstacionDeMantenimiento tienda;
	Jugador pj;
    List<Label> labels;
	List<String> imagenesFondo, imagenesBotones,keys;
	private Rectangle rectCantidad;
	private Rectangle sombra;
    

    public VistaEstacionDeMantenimiento(Stage stage, Group root,EstacionDeMantenimiento tienda, Jugador pj,
    String pathFondo,List<String> imagenesBotones,List<String> keys){
        myStage = stage;
        this.root = root;
        this.pj = pj;
        this.pathFondo = pathFondo;
        this.imagenesBotones = imagenesBotones;
        this.keys = keys;
        this.labels = new ArrayList<>();
        this.tienda = tienda;
        if(tienda instanceof EstacionDeServicio) {	
        	labels.add(new Label("Elija la cantidad de combustible que desea cargar: "));
        	labels.add(new Label((int) pj.getNave().getNivelDeCombustible() + "/" + (int)pj.getNave().getCapacidadTanque()));
        	double porcentajeNafta = (pj.getNave().getNivelDeCombustible() / pj.getNave().getCapacidadTanque()) * 100;
        	rectCantidad = new Rectangle(2 * porcentajeNafta, 60);
        	rectCantidad.setFill(Color.rgb(181, 142, 83));
    		sombra = new Rectangle(200, 60);
    		sombra.setFill(Color.BLACK);
        } else {
        	double porcentajeHP = ((double)pj.getNave().getHP() / (double)pj.getNave().getMaxHP()) * 100;
        	rectCantidad = new Rectangle(2 * porcentajeHP, 60);
        	rectCantidad.setFill(Color.rgb(209, 19, 19));
    		sombra = new Rectangle(200, 60);
    		sombra.setFill(Color.BLACK);
        	labels.add(new Label("Elija la cantidad de daño que desea reparar: "));
        	labels.add(new Label((int) pj.getNave().getHP() + "/" + (int)pj.getNave().getMaxHP()));
        }
    }


    private void inicializarImagenesBotones() {
		imagenes = new HashMap<>();
		
		for(int i = 0; i < 4; i++) {
			imagenes.put(keys.get(i), CreadorDeImagenes.obtenerImagen(imagenesBotones.get(i), 100, 80));
		}
		
		for(int i = 4; i < 6; i++) {
			imagenes.put(keys.get(i), CreadorDeImagenes.obtenerImagen(imagenesBotones.get(i), 200, 100));
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
	    botones.get(keys.get(0)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(0))); actualizarBarra();});
	    botones.get(keys.get(1)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(1))); actualizarBarra();});
	    botones.get(keys.get(2)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(2))); actualizarBarra();});
	    botones.get(keys.get(3)).setOnAction(e -> {this.tienda.vender(this.pj, Integer.parseInt(keys.get(3))); actualizarBarra();});
	    botones.get(keys.get(4)).setOnAction(e -> {this.tienda.vender(this.pj, 100); actualizarBarra();});
	}
	
	private void inicializarGridPane() {
		this.inicializarBotones();
		
		pane1 = new GridPane();
		pane2 = new GridPane();
		pane3 = new GridPane();
		GridPane.setConstraints(labels.get(0),0,0);
		GridPane.setConstraints(botones.get(keys.get(0)),0,0);
		GridPane.setConstraints(botones.get(keys.get(1)),1,0);
		GridPane.setConstraints(botones.get(keys.get(2)),0,1);
		GridPane.setConstraints(botones.get(keys.get(3)),1,1);
		GridPane.setConstraints(botones.get(keys.get(4)),0,0);
		GridPane.setConstraints(sombra, 0, 1);
		GridPane.setConstraints(rectCantidad, 0, 1);
		GridPane.setConstraints(labels.get(1), 0, 1);
		GridPane.setConstraints(botones.get(keys.get(5)),0,2);
		
		pane1.getChildren().add(labels.get(0));
		pane2.getChildren().addAll(botones.get(keys.get(0)),botones.get(keys.get(1)),botones.get(keys.get(2)),botones.get(keys.get(3)));
		pane3.getChildren().addAll(botones.get(keys.get(4)), sombra, rectCantidad , labels.get(1), botones.get(keys.get(5)));
		
		
	}
	
	private void configurarLabels() {
  
		labels.get(0).setPrefSize(400,60);
		labels.get(0).setFont(new Font(16));
		
		
		labels.get(1).setTextFill(Color.WHITE);
		labels.get(1).setAlignment(Pos.CENTER);
		labels.get(1).setBackground(Background.EMPTY);
		labels.get(1).setPrefSize(200,60);
		labels.get(1).setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 16));
	}
	
	private void inicializarLayout() throws FileNotFoundException {
		layout = new VBox();
		img = new Image(new FileInputStream(pathFondo), 800, 600, false, false);
		backgroundImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,null, null, null);
		background = new Background(backgroundImg);
		
		layout.setAlignment(Pos.CENTER);
		
		this.configurarLabels();
		this.inicializarGridPane();
		
		VBox.setMargin(pane1,new Insets(100,0,0,450));
		VBox.setMargin(pane2,new Insets(0,0,0,450));
		VBox.setMargin(pane3,new Insets(0,200,100,450));
		layout.setBackground(background);
		layout.getChildren().addAll(pane1,pane2,pane3);
		layout.setLayoutX((VistaJuego.WIDTH / 2) - 400);
		layout.setLayoutY((VistaJuego.HEIGHT / 2) - 350);
	}
	
	private void actualizarBarra() {
		 if(tienda instanceof EstacionDeServicio) {
			 	labels.get(1).setText((int) pj.getNave().getNivelDeCombustible() + "/" + (int)pj.getNave().getCapacidadTanque());
	        	double porcentajeNafta = (pj.getNave().getNivelDeCombustible() / pj.getNave().getCapacidadTanque()) * 100;
	        	pj.getNave().getNivelDeCombustible();
	        	rectCantidad = new Rectangle(2 * porcentajeNafta, 60);
	        	rectCantidad.setFill(Color.rgb(181, 142, 83));
	    		sombra = new Rectangle(200, 60);
	    		sombra.setFill(Color.BLACK);
	        } else {
	    		labels.get(1).setText((int) pj.getNave().getHP() + "/" + (int)pj.getNave().getMaxHP());
	        	double porcentajeHP = ((double)pj.getNave().getHP() / (double)pj.getNave().getMaxHP()) * 100;
	        	rectCantidad = new Rectangle(2 * porcentajeHP, 60);
	        	rectCantidad.setFill(Color.rgb(209, 19, 19));
	    		sombra = new Rectangle(200, 60);
	    		sombra.setFill(Color.BLACK);

	        }
		 
		GridPane.setConstraints(sombra, 0, 1);
		GridPane.setConstraints(rectCantidad, 0, 1);
		GridPane.setConstraints(labels.get(1), 0, 1);
		 
		 pane3.getChildren().clear();
		 pane3.getChildren().addAll(botones.get(keys.get(4)), sombra, rectCantidad , labels.get(1), botones.get(keys.get(5)));
		 
//		 pane3.getChildren().set(2, sombra);
//		 pane3.getChildren().set(2, rectCantidad);
//		 pane3.getChildren().set(1, labels.get(1));
	}
	
	
	public void inicializar() throws FileNotFoundException {
		this.inicializarLayout();   
		botones.get(keys.get(5)).setCancelButton(true);
		botones.get(keys.get(5)).setOnAction(e -> {root.getChildren().remove(root.getChildren().size() - 1);
                                               mostrando = false;});
	    root.getChildren().add(layout);
	    this.mostrando = true;
	 }
	
	 public void mostrar() {
	    //Lo de mostando era porque se apilaban las ventanas mientras el jugador estaba parado encima de la tienda
	    //Hay que encontrar una solucion mejor!
	    if(!this.mostrando) {
		    try {
		    	this.inicializar();
		    } catch (FileNotFoundException e) {
		    	e.printStackTrace();
		    }
	    }
	 }
}
