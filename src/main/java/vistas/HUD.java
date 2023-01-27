package vistas;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import javafx.stage.Stage;
import jugador.Jugador;
import tp.CreadorDeImagenes;
import tp.GuardarPartida;

public class HUD {
	private static int BUTTON_WIDTH = 500;
	private static int BUTTON_HEIGHT = 75;
	private static int ICON_WIDTH = 40;
	private static int ICON_HEIGHT = 40;
	private static int ICON_MENU_WIDTH = 64;
	private static int ICON_MENU_HEIGHT = 64;
	private static int RECTANGLE_WIDTH = 900;
	private static int RECTANGLE_HEIGHT = 600;
	
	private GraphicsContext context;
	private double screenWidth;
	private Jugador pj;
	private GuardarPartida guardar;
	List<Image> imagenes;
	private Group root;
	private boolean isShowing;
	private Stage stage;
	VistaMenu vistaMenu;
	Color sombra = Color.rgb(46, 46, 46, 0.7);
	Color rojo = Color.rgb(209, 19, 19);
	Color amarillo = Color.rgb(181, 142, 83);
	Color colorcito = new Color(0.7, 0.7, 0.7, 0.3);
	Rectangle rect;
	VBox vbox;
	StackPane pane;
	Button botonOK;
	Button botonSalir;
	Button botonMainMenu;
	Button saveGame;
	Label label;
	
	public HUD(Group root, GraphicsContext context, double screenWidth, Jugador pj, GuardarPartida guardar, Stage stage) {
		this.context = context;
		this.root = root;
		this.screenWidth = screenWidth;
		this.guardar = guardar;
		this.stage = stage;
		this.imagenes = new ArrayList<Image>();
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Menu/Health.png", ICON_WIDTH, ICON_HEIGHT));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Menu/IconFuel.png", ICON_WIDTH, ICON_HEIGHT));
		imagenes.add(CreadorDeImagenes.obtenerImagen("src/rsc/Menu/InGameMenu.png", ICON_MENU_WIDTH, ICON_MENU_HEIGHT));
		this.pj = pj;
		this.isShowing = false;
	}
	
	//Dibuja el porcentaje de nafta y vida del jugador.
	public void dibujarHUD() {
		double porcentajeHP = ((double)pj.getNave().getHP() / (double)pj.getNave().getMaxHP()) * 100;
		context.drawImage(imagenes.get(0), 10, 10);
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
		context.drawImage(imagenes.get(1), 15, 50);
		
		context.setFont(Font.font(30));
		context.strokeText("$" + pj.getDinero(), 310, 40);
		context.setFill(Color.WHITE);
		context.fillText("$" + pj.getDinero(), 310, 40);
		
		context.strokeText(alturaPJ() + "mts", screenWidth - 200, 40);
		context.setFill(Color.WHITE);
		context.fillText(alturaPJ() + "mts", screenWidth - 200, 40);

		context.drawImage(imagenes.get(2), screenWidth - 100, 10);
	}
	
	private void inicializarBotones() {
		botonOK = new Button("Continue");
		botonOK.setPrefWidth(BUTTON_WIDTH);
		botonOK.setPrefHeight(BUTTON_HEIGHT);
	
		botonSalir = new Button("Salir del juego");
		botonSalir.setPrefWidth(BUTTON_WIDTH);
		botonSalir.setPrefHeight(BUTTON_HEIGHT);
		
		botonMainMenu = new Button("Salir al menu principal");
		botonMainMenu.setPrefWidth(BUTTON_WIDTH);
		botonMainMenu.setPrefHeight(BUTTON_HEIGHT);
		
		saveGame = new Button("Guardar partida");
		saveGame.setPrefWidth(BUTTON_WIDTH);
		saveGame.setPrefHeight(BUTTON_HEIGHT);
	}
	
	//Dibuja el Menu con sus opciones.
	private void dibujarMenu() {
	pane = new StackPane();
    	vbox = new VBox();
    	rect = new Rectangle(0, 0, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
    	
    	rect.setFill(colorcito);
    	
    	label = new Label("Menu");
		label.setFont(Font.font(50));
		
		this.inicializarBotones();
		
		vbox.setSpacing(20);
		vbox.getChildren().add(label);
		vbox.getChildren().add(botonOK);
		vbox.getChildren().add(saveGame);
		vbox.getChildren().add(botonMainMenu);
		vbox.getChildren().add(botonSalir);
		vbox.setAlignment(Pos.CENTER);
		
    	pane.getChildren().add(rect);
    	pane.getChildren().add(vbox);
    	
    	pane.setAlignment(Pos.CENTER);
    	pane.setLayoutX((VistaJuego.WIDTH - 900) / 2);
    	pane.setLayoutY((VistaJuego.HEIGHT - 600) / 2);
    	
    	root.getChildren().add(pane);
    	botonOK.setOnAction(t -> {root.getChildren().remove(pane); isShowing = false;});
    	botonSalir.setOnAction(t -> System.exit(0));
    	saveGame.setOnAction(t -> guardar.guardarPartida());
    	botonMainMenu.setOnAction(e -> vistaMenu = new VistaMenu(stage));
    }
	
	//Verifica si se hizo click en el boton de menu y lo dibuja.
	public void checkMenu(MouseEvent e) {
		var x = e.getSceneX();
		var y = e.getSceneY();
		
		if(x >= this.screenWidth - 100 && x <= screenWidth - 100 +64 && y >= 10 && y <= 10+64) {
			if(!isShowing) {
				dibujarMenu();
				isShowing = true;
			}
		}
	}
	
	//Calcula a qué altura está el personaje.
	private int alturaPJ() {
		var yPos = pj.getY();
		yPos = yPos - 8;
		return (int) -(yPos * 4);
	}
	
}
