package vistas;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tp.CreadorDeImagenes;
import tp.EstadoDelJuego;
import tp.Sonidos;

public class VistaEndGame {
	private final String TEXTO_LOST_GAME = "Lo sentimos, no pudiste ayudar a la princesa :(";
	private final String TEXTO_WIN_GAME = "¡Felicitaciones! Ganaste la partida :D";
	private final String IMG_LOST_GAME = "src/rsc/LostGame.png";
	private final String IMG_WIN_GAME = "src/rsc/WinGame.png";
	
	Image image;
	ImageView imgView;
	Label label;
	String texto;
	StackPane sPane;
	Color colorBordeLabel = Color.rgb(0, 0, 0);
	Color colorTextoLabel = Color.rgb(0,0,0);
	Color colorFondoLabel = Color.rgb(100,10,90,0.5);
	Stage stage;
	Scene scene;
	private double stageWidth;
	private double stageHeight;
	private boolean isFullScreen;
	Button botonSalir;
	
	public VistaEndGame(EstadoDelJuego estadoJuego, Stage stage, double stageWidth, double stageHeight) {
		this.stage = stage;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;

		this.inicializarTextoEImagen(estadoJuego);
		this.inicializarVista();
	}
	
	//Inicializa el texto y la imágen dependiendo de si se ganó o se perdió el juego.
	private void inicializarTextoEImagen(EstadoDelJuego estadoJuego) {
		sPane = new StackPane();
		if(estadoJuego == EstadoDelJuego.GANADO) {
			image = CreadorDeImagenes.obtenerImagen(IMG_WIN_GAME, this.stage.getWidth(), this.stage.getHeight());
			texto = TEXTO_WIN_GAME;
		} else if(estadoJuego == EstadoDelJuego.PERDIDO) {
			image = CreadorDeImagenes.obtenerImagen(IMG_LOST_GAME, this.stage.getWidth(), this.stage.getHeight());
			texto = TEXTO_WIN_GAME;
			texto = TEXTO_LOST_GAME;
		}
	}
	
	//Inicializa la vista con el botón de salir del juego, la imágen y el texto que corresponda.
	private void inicializarVista() {
		imgView = new ImageView(image);
		label = new Label(texto);
		label.setPrefSize(stageWidth, stageHeight/3);
		label.setFont(new Font(50));
		label.setBorder(Border.stroke(colorBordeLabel));
		label.setBackground(Background.fill(colorFondoLabel));
		label.setTextFill(colorTextoLabel);
		label.setAlignment(Pos.CENTER);
		botonSalir = new Button("Salir");
		botonSalir.setPrefSize(75,60);
		botonSalir.setOnAction(e -> System.exit(0));
		sPane.getChildren().addAll(imgView,label,botonSalir);
		StackPane.setAlignment(botonSalir,Pos.TOP_LEFT);
		StackPane.setAlignment(label, Pos.TOP_CENTER);
	}
	
	//Permite mostrar, reproduciendo el sonido correspondiente según el estado del juego.
	public void mostrar(EstadoDelJuego estadoJuego) {
		stage.setWidth(stageWidth);
		stage.setHeight(stageHeight);
		stage.setFullScreen(isFullScreen);
		Group root = new Group();
		root.getChildren().add(sPane);
		scene = new Scene(root, stage.getWidth(), stage.getHeight());
		if(estadoJuego != null) {
			stage.setScene(scene);
			stage.setFullScreen(isFullScreen);
			Sonidos.stopReproduccion("Jugando");
			if(estadoJuego == EstadoDelJuego.GANADO) {
				Sonidos.reproducir("Win Game");
			}else{
				Sonidos.reproducir("Game over");
				Sonidos.reproducir("Lost Game");
			}
		}
	}
	
}
