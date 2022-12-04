package algo3.motherloadV2;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tp.EstadoDelJuego;

public class VistaEndGame {
	private final String TEXTO_LOST_GAME = "Lo sentimos, no pudiste ayudar a la princesa :(";
	private final String TEXTO_WIN_GAME = "¡Felicitaciones! Ganaste la partida :D";
	private final String IMG_LOST_GAME = "../motherloadV2/src/rsc/LostGame.png";
	private final String IMG_WIN_GAME = "../motherloadV2/src/rsc/WinGame.png";
	
	Image image;
	ImageView imgView;
	Label label;
	String texto;
	StackPane sPane;
	Color colorBordeLabel = Color.rgb(0, 0, 0);
	Color colorTextoLabel = Color.rgb(100,10,90);
	Stage stage;
	Scene scene;
	
	public VistaEndGame(EstadoDelJuego estadoJuego,Stage stage) {
		this.stage = stage;
		
		sPane = new StackPane();
		if(estadoJuego == EstadoDelJuego.GANADO) {
			image = CreadorDeImagenes.obtenerImagen(IMG_WIN_GAME,1024,768);
			texto = TEXTO_LOST_GAME;
		}
		else if(estadoJuego == EstadoDelJuego.PERDIDO) {
			image = CreadorDeImagenes.obtenerImagen(IMG_LOST_GAME,1024,768);
			texto = TEXTO_WIN_GAME;
		}
		
		imgView = new ImageView(image);
		
		label = new Label(texto);
		label.setPrefSize(1024,230);
		label.setFont(new Font(20));
		label.setBorder(Border.stroke(colorBordeLabel));
		label.setTextFill(colorTextoLabel);
	
		sPane.getChildren().add(imgView);
		sPane.getChildren().add(label);
		StackPane.setMargin(label,new Insets(538,0,0,0));
		
	}
	
	public void mostrar() {
		scene = new Scene(sPane);
		stage.setScene(scene);
	}
	
}
