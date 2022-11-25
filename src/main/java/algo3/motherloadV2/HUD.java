package algo3.motherloadV2;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import jugador.Jugador;

public class HUD {
	private GraphicsContext context;
	private double screenWidth;
	private double screenHeight;
	private Jugador pj;
	
	public HUD(GraphicsContext context, double screenWidth, double screenHeight, Jugador pj) {
		this.context = context;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.pj = pj;
	}
	
	public void dibujarHUD() {
		double porcentajeHP = (pj.getNave().getHP() / pj.getNave().getMaxHP()) * 100;
		context.setFill(Color.RED);
		context.fillRect(20, 10, 2 * porcentajeHP, 20);
		
		double porcentajeNafta = (pj.getNave().getNivelDeCombustible() / pj.getNave().getCapacidadTanque()) * 100;
		context.setFill(Color.SIENNA);
		context.fillRect(20, 40, 2 * porcentajeNafta, 20);
		
		context.setFill(Color.RED);
		context.fillRect(950, 20, 30, 30);
	}
	
	public static void checkMenu(MouseEvent e, Group root) {
		var x = e.getSceneX();
		var y = e.getSceneY();
		
		if(x >= 950 && x <= 980 && y >= 20 && y <= 50) {
	    	StackPane pane = new StackPane();
	    	VBox vbox = new VBox();
	    	Rectangle rect = new Rectangle(0, 0, 900, 600);
	    	Color colorcito = new Color(0.7, 0.7, 0.7, 0.3);
	    	rect.setFill(colorcito);
	    	
	    	Label label = new Label("Work in progress :p");
			label.setFont(Font.font(50));
			
			Button botonOK = new Button("Continue");
			botonOK.setPrefWidth(200);
			
			Button botonSalir = new Button("Salir del juego");
			botonSalir.setPrefWidth(200);
			
			Button botonMainMenu = new Button("Salir al menu principal");
			botonMainMenu.setPrefWidth(200);
			
			Button saveGame = new Button("Guadar partida");
			saveGame.setPrefWidth(200);
			
			vbox.setSpacing(20);
			vbox.getChildren().add(label);
			vbox.getChildren().add(botonOK);
			vbox.getChildren().add(botonSalir);
			vbox.getChildren().add(botonMainMenu);
			vbox.getChildren().add(saveGame);
			vbox.setAlignment(Pos.CENTER);
			
	    	pane.getChildren().add(rect);
	    	pane.getChildren().add(vbox);
	    	
	    	pane.setAlignment(Pos.CENTER);
	    	pane.setLayoutX((VistaJuego.WIDTH - 900) / 2);
	    	pane.setLayoutY((VistaJuego.HEIGHT - 600) / 2);
	    	
	    	root.getChildren().add(pane);
	    	
	    	
	    	
//	    	Popup popup = new Popup();
//	    	
//	    	popup.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
//	    	popup.getContent().add(pane);
//	    	
	    	botonOK.setOnAction(t -> root.getChildren().remove(pane));
	    	botonSalir.setOnAction(t -> System.exit(0));
		}
		
	}
	
	
}
