package algo3.motherloadV2;

import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tp.ConfigJuego;

public class VistaMenuConfig {
	private StackPane pantallaPrincipal;
	private VBox organizador;

	private List<String> opcionesPantalla = List.of("1024x768", "1280x720", "1920x1080", "Fullscreen");
	private List<String> opcionesDificultades = List.of("Muy muy fácil", "Fácil", "Medio", "Díficil");
	private Map<String, Integer> valorDificultades = Map.of(opcionesDificultades.get(0), 64,
															opcionesDificultades.get(1), 1000,
															opcionesDificultades.get(2), 5000,
															opcionesDificultades.get(3), 10000);
	private Button botonCerrar;
	private ComboBox<String> resoluciones;
	private ComboBox<String> dificultades;
	private Button botonConfirmarCambios;

	public VistaMenuConfig(Stage stage, StackPane pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		inicializar();
	}
	
	private void inicializar() {
		organizador = new VBox();
		organizador.setAlignment(Pos.CENTER);
		organizador.setSpacing(20);
		
		resoluciones = new ComboBox<String>();
		resoluciones.getItems().addAll(opcionesPantalla);
		resoluciones.setMaxSize(300, 100);
		resoluciones.setValue("Resoluciones");
		
		dificultades = new ComboBox<String>();
		dificultades.getItems().addAll(opcionesDificultades);
		dificultades.setMaxSize(300, 100);
		dificultades.setValue("Dificultad");
		
		botonCerrar = new Button("Cerrar");
		botonCerrar.setOnAction(e -> pantallaPrincipal.getChildren().remove(pantallaPrincipal.getChildren().size() - 1));
		botonCerrar.setMaxSize(300, 100);
		
		botonConfirmarCambios = new Button("Confirmar cambios");
		botonConfirmarCambios.setMaxSize(300, 100);
		botonConfirmarCambios.setOnAction(e -> setCambios());
		
		organizador.setMaxSize(900, 500);
		organizador.setBackground(Background.fill(Color.rgb(200, 200, 200, 0.3)));
		organizador.getChildren().addAll(resoluciones, dificultades, botonCerrar, botonConfirmarCambios);
	}
	
	public void mostrar() {
		pantallaPrincipal.getChildren().add(organizador);
	}
	
	public void setCambios() {
		ConfigJuego configActual = ConfigJuego.readFile();
		
		var isFullscreen = configActual.isFullScreen();
		double width = configActual.getScreenWidth();
		double height = configActual.getScreenHeight();
		var resolucionElegida = resoluciones.getValue();
		
		if(resolucionElegida != "Resoluciones") {	
			if(resolucionElegida == "Fullscreen") {
				isFullscreen = true;
			} else {
				var widthYheight = resolucionElegida.split("x");
				width = Double.parseDouble(widthYheight[0]);
				height = Double.parseDouble(widthYheight[1]);
				isFullscreen = false;
			}
		}
		
		var dificultadElegida = dificultades.getValue();
		var valorDificultad = valorDificultades.get(opcionesDificultades.get(1));
		if(dificultadElegida != "Dificultad") {
			valorDificultad = valorDificultades.get(dificultadElegida);
		}

		ConfigJuego nuevo = new ConfigJuego(isFullscreen, width, height, valorDificultad);
		nuevo.writeConfigFile();
		
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText("Atención");
		a.setContentText("Los cambios tendran efecto la proxima vez que inice el juego");
		a.show();
	}
	
	
	
	
	
}
