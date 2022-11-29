package algo3.motherloadV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		Color sombra = Color.rgb(46, 46, 46, 0.7);
		Color rojo = Color.rgb(209, 19, 19);
		Color amarillo = Color.rgb(181, 142, 83);
		
		double porcentajeHP = (pj.getNave().getHP() / pj.getNave().getMaxHP()) * 100;
		context.drawImage(obtenerImagen("../motherloadV2/src/rsc/Menu/Health.png", 40), 10, 10);
		context.setFill(sombra);
		context.setStroke(Color.BLACK);
		context.fillRect(60, 20, 2 * 100, 20);
		context.setFill(rojo);
		context.fillRect(60, 20, 2 * porcentajeHP, 20);
		
		double porcentajeNafta = (pj.getNave().getNivelDeCombustible() / pj.getNave().getCapacidadTanque()) * 100;
		context.setFill(sombra);
		context.setStroke(Color.BLACK);
		context.fillRect(60, 60, 2 * 100, 20);
		context.setFill(amarillo);
		context.fillRect(60, 60, 2 * porcentajeNafta, 20);
		context.drawImage(obtenerImagen("../motherloadV2/src/rsc/Menu/IconFuel.png", 40), 15, 50);
		
		context.setFont(Font.font(30));
		context.strokeText("$" + pj.getDinero(), 310, 40);
		context.setFill(Color.WHITE);
		context.fillText("$" + pj.getDinero(), 310, 40);
		
		context.strokeText(alturaPJ() + "mts", 410, 40);
		context.setFill(Color.WHITE);
		context.fillText(alturaPJ() + "mts", 410, 40);
		
		

		context.drawImage(obtenerImagen("../motherloadV2/src/rsc/Menu/InGameMenu.png", 64), 950, 10);
	}
	
	public static void checkMenu(MouseEvent e, Group root) {
		var x = e.getSceneX();
		var y = e.getSceneY();
		
		if(x >= 950 && x <= 950+64 && y >= 10 && y <= 10+64) {
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
	    	botonOK.setOnAction(t -> root.getChildren().remove(pane));
	    	botonSalir.setOnAction(t -> System.exit(0));
		}
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
	
	private int alturaPJ() {
		var yPos = pj.getY();
		yPos = yPos - 8;
		return (int) -(yPos * 4);
	}
	
	
}
