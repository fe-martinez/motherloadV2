package vistas;

import java.util.ArrayList;
import java.util.List;

import controlador.ControladorJuego;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tp.CreadorDeImagenes;
import tp.Sonidos;

public class VistaHistoria {	
	List<Image> images;
	List<ImageView> imgViews;
	Label label;
	StackPane sPane;
	Color colorBordeLabel = Color.rgb(0, 0, 0);
	Color colorTextoLabel = Color.rgb(0,0,0);
	Color colorFondoLabel = Color.rgb(100,10,90,0.5);
	Stage stage;
	Scene scene;
	Group root;
	List<Button> botonSiguiente;
	double stageWidth;
	double stageHeight;
	private boolean isFullScreen;
	List<String> historia = List.of("Érase una vez, en un asteroide muy muy pequeño, y muy muy lejano,\nuna princesa que gobernaba su pequeño pueblo con astucia y valentía.\r\n"
			,"Una noche de verano, la princesa Cat-Lynn estaba de cumpleaños.\nDecidió invitar a todo el pueblo a su fiesta.\r\n"
			,"Pero esa noche era muy importante\nno solo porque era el cumpleaños n°20 de la princesa\nsino también porque hoy sería coronada como la reina de su asteroide\nel asteroide B612.\r\n"
			,"Para esto, la princesa debía pasar por la ceremonia ancestral de coronación\ndonde se le entregaría la mágica rosa de la cosmovision.\r\n"
			,"La rosa es una parte vital de la ceremonia\n y el concejo de ancianos del pueblo respeta a rajatabla las tradiciones\r\n"
			,"Sin embargo, durante la celebración\nsonidos extraños comenzaron a escucharse.\r\n"
			,"..."
			,"Lo siguiente que supo la princesa\nfue que la flor había sido robada.\r\n"
			,"¿La ayudas a encontrarla?");
	
	List<String> pathImagenes = List.of("src/rsc/Historia/01.png",
									    "src/rsc/Historia/02.png",
									    "src/rsc/Historia/03.png",
									    "src/rsc/Historia/04.png",
									    "src/rsc/Historia/05.png",
									    "src/rsc/Historia/06.png",
									    "src/rsc/Historia/07.png",
									    "src/rsc/Historia/08.png",
									    "src/rsc/Historia/09.png");
	private ControladorJuego mainJuego;
	private VBox organizador;
	
	public VistaHistoria(Stage stage, double stageWidth, double stageHeight, boolean isFullScreen, ControladorJuego mainJuego) {
		this.mainJuego = mainJuego;
		this.stage = stage;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
		this.isFullScreen = isFullScreen;
		
		this.images = new ArrayList<>();
		this.imgViews = new ArrayList<>();
		this.botonSiguiente = new ArrayList<>();
		
		sPane = new StackPane();
		sPane.setMaxSize(stageWidth, stageHeight);
		sPane.setPrefSize(stageWidth, stageHeight);
		
		for(String pathImagen: pathImagenes) {
			this.images.add(CreadorDeImagenes.obtenerImagen(pathImagen, stageWidth, stageHeight));
		}
		
		for(Image image: images) {
			this.imgViews.add(new ImageView(image));
		}
		
		for(int i = 0; i < 9; i++) {
			this.botonSiguiente.add(new Button("Siguiente"));
			this.botonSiguiente.get(i).setPrefSize(75,60);
		}
			
		organizador = new VBox();
		organizador.setAlignment(Pos.CENTER);
		
		label = new Label(historia.get(0));
		label.setPrefSize(stageWidth, stageHeight/3);
		label.setMaxSize(stageWidth, stageHeight/3);
		label.setFont(Font.font("", FontWeight.EXTRA_BOLD, 25));
		label.setAlignment(Pos.CENTER);
		label.setBorder(Border.stroke(colorBordeLabel));
		label.setBackground(Background.fill(colorFondoLabel));
		label.setTextFill(colorTextoLabel);
		label.prefWidthProperty().bind(label.textProperty().length());

		organizador.getChildren().addAll(label, botonSiguiente.get(0));
		sPane.getChildren().addAll(imgViews.get(0), organizador);
		
		this.inicializarAccionesBotones();
	}
	
	private void inicializarAccionesBotones() {
		botonSiguiente.get(0).setOnAction(e -> {
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(1));
			organizador.getChildren().addAll(label, botonSiguiente.get(1));
			sPane.getChildren().addAll(imgViews.get(1), organizador);
		});
		

		botonSiguiente.get(1).setOnAction(e -> {
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(2));
			organizador.getChildren().addAll(label, botonSiguiente.get(2));
			sPane.getChildren().addAll(imgViews.get(2), organizador);
		});
		

		botonSiguiente.get(2).setOnAction(e -> {
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(3));
			organizador.getChildren().addAll(label, botonSiguiente.get(3));
			sPane.getChildren().addAll(imgViews.get(3), organizador);
		});
		

		botonSiguiente.get(3).setOnAction(e -> {
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(4));
			organizador.getChildren().addAll(label, botonSiguiente.get(4));
			sPane.getChildren().addAll(imgViews.get(4), organizador);
		});
		

		botonSiguiente.get(4).setOnAction(e -> {
			Sonidos.stopReproduccion("Inicio");
			Sonidos.reproducir("Suspenso");
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(5));
			organizador.getChildren().addAll(label, botonSiguiente.get(5));
			sPane.getChildren().addAll(imgViews.get(5), organizador);
		});
		

		botonSiguiente.get(5).setOnAction(e -> {			
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(6));
			organizador.getChildren().addAll(label, botonSiguiente.get(6));
			sPane.getChildren().addAll(imgViews.get(6), organizador);
			Sonidos.reproducir("Explosion");
		});
		

		botonSiguiente.get(6).setOnAction(e -> {
			Sonidos.stopReproduccion("Explosion");
			Sonidos.reproducir("Sad");
			Sonidos.setVolumen("Sad",100);
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(7));
			organizador.getChildren().addAll(label, botonSiguiente.get(7));
			sPane.getChildren().addAll(imgViews.get(7), organizador);
		});
		

		botonSiguiente.get(7).setOnAction(e -> {
			sPane.getChildren().clear();
			organizador.getChildren().clear();
			this.label.setText(this.historia.get(8));
			organizador.getChildren().addAll(label, botonSiguiente.get(8));
			sPane.getChildren().addAll(imgViews.get(8), organizador);
		});
		
		botonSiguiente.get(8).setOnAction(e -> {
			mainJuego.start(false);
			Sonidos.stopReproduccion("Sad");
		});
		
	}
	
	public void mostrar() {
		Sonidos.stopReproduccion("Menu");
		Sonidos.reproducir("Inicio");
		stage.setWidth(stageWidth);
		stage.setHeight(stageHeight);
		stage.setFullScreen(isFullScreen);
		root = new Group();
		root.getChildren().add(sPane);
		scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);
	}
	
	
}
