package vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tp.ConfigJuego;

public class VistaMenuConfig {
	private StackPane pantallaPrincipal;
	private VBox organizador;

	private List<String> opcionesPantalla = List.of("1024x768", "1280x720", "1920x1080");
	private List<String> opcionesDificultades = List.of("Muy muy fácil", "Fácil", "Medio", "Díficil");
	private Map<String, Integer> valorDificultades = Map.of(opcionesDificultades.get(0), 50,
															opcionesDificultades.get(1), 1000,
															opcionesDificultades.get(2), 5000,
															opcionesDificultades.get(3), 10000);
	private Button botonCerrar;
	private ComboBox<String> resoluciones;
	private ComboBox<String> dificultades;
	private Button botonConfirmarCambios;
	private Button botonArriba;
	private ArrayList<KeyCode> teclasKeyCodes;
	private VBox ventanaInput;
	private Scene scene;
	private KeyCode presionada;
	private Button botonDerecha;
	private ButtonBase botonAbajo;
	private Button botonIzquierda;
	private VBox controles;
	private Slider sliderVolumen;
	private Label labelControles;
	
	public VistaMenuConfig(Scene scene, StackPane pantallaPrincipal, ConfigJuego configActual) {
		this.scene = scene;
		this.pantallaPrincipal = pantallaPrincipal;
		teclasKeyCodes = new ArrayList<KeyCode>();
		if(configActual != null) {
			teclasKeyCodes.add(KeyCode.getKeyCode(configActual.getTeclas().get(0)));
			teclasKeyCodes.add(KeyCode.getKeyCode(configActual.getTeclas().get(1)));
			teclasKeyCodes.add(KeyCode.getKeyCode(configActual.getTeclas().get(2)));
			teclasKeyCodes.add(KeyCode.getKeyCode(configActual.getTeclas().get(3)));
		} else {
			teclasKeyCodes.add(KeyCode.UP);
			teclasKeyCodes.add(KeyCode.DOWN);
			teclasKeyCodes.add(KeyCode.RIGHT);
			teclasKeyCodes.add(KeyCode.LEFT);
		}

		inicializar();
	}
	
	private void inicializarBotones() {
		botonCerrar = new Button("Cerrar");
		botonCerrar.setOnAction(e -> pantallaPrincipal.getChildren().remove(pantallaPrincipal.getChildren().size() - 1));
		botonCerrar.setMaxSize(300, 100);
		
		botonConfirmarCambios = new Button("Confirmar cambios");
		botonConfirmarCambios.setMaxSize(300, 100);
		botonConfirmarCambios.setOnAction(e -> setCambios());
		
		controles = new VBox();
		controles.setAlignment(Pos.CENTER);
		controles.setSpacing(10);
		controles.setBackground(Background.fill(Color.rgb(150, 150, 150, 0.8)));
		controles.setMaxWidth(450);
		
		labelControles = new Label("Modificar controles");
		labelControles.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 20));
		
		botonArriba = new Button("Arriba: " + teclasKeyCodes.get(0));
		botonArriba.setOnAction(e -> getKeyCode(scene, pantallaPrincipal, 0));
		botonArriba.setMaxSize(300, 100);
		
		botonAbajo = new Button("Abajo: " + teclasKeyCodes.get(1));
		botonAbajo.setOnAction(e -> getKeyCode(scene, pantallaPrincipal, 1));
		botonAbajo.setMaxSize(300, 100);
		
		botonDerecha = new Button("Derecha: " + teclasKeyCodes.get(2));
		botonDerecha.setOnAction(e -> getKeyCode(scene, pantallaPrincipal, 2));
		botonDerecha.setMaxSize(300, 100);
		
		botonIzquierda = new Button("Izquierda: " + teclasKeyCodes.get(3));
		botonIzquierda.setOnAction(e -> getKeyCode(scene, pantallaPrincipal, 3));
		botonIzquierda.setMaxSize(300, 100);
	
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
		
		Label labelVolumen = new Label("Nivel de volumen general:");
		labelVolumen.setFont(Font.font(20));
		
		sliderVolumen = new Slider(0, 100, 30);
		sliderVolumen.setShowTickMarks(true);
		sliderVolumen.setShowTickLabels(true);
		sliderVolumen.setMaxWidth(300);
		sliderVolumen.setPrefWidth(300);
		
		this.inicializarBotones();
		
		controles.getChildren().addAll(labelControles, botonArriba, botonAbajo, botonDerecha, botonIzquierda);
		
		organizador.setMaxSize(900, 500);
		organizador.setBackground(Background.fill(Color.rgb(200, 200, 200, 0.7)));
		organizador.getChildren().addAll(resoluciones, dificultades, labelVolumen, sliderVolumen, controles);
		organizador.getChildren().addAll(botonConfirmarCambios, botonCerrar);
	}
	
	public void mostrar() {
		pantallaPrincipal.getChildren().add(organizador);
	}
	
	private void getKeyCode(Scene escena, StackPane ventana, int lugarArray) {
		ventanaInput = new VBox();
		ventanaInput.setPrefSize(450, 250);
		ventanaInput.setMaxSize(450, 250);
		ventanaInput.setBackground(Background.fill(Color.rgb(200, 200, 200, 0.9)));
		ventanaInput.setAlignment(Pos.CENTER);
		ventanaInput.requestFocus();
		ventanaInput.setSpacing(20);

		Label texto = new Label("Ingrese la nueva tecla");
		texto.setFont(Font.font(20));
		
		Button cancelar = new Button("Cancelar");
		cancelar.setMaxSize(200, 100);
		cancelar.setOnAction(e -> ventana.getChildren().remove(ventana.getChildren().size() - 1));
		
		Button ok = new Button("Confirmar cambio");
		ok.setMaxSize(200, 100);
		ok.setOnAction(e -> {aplicarCambio(presionada, lugarArray); ventana.getChildren().remove(ventana.getChildren().size() - 1);});
		ok.setDisable(true);
		Label elegida = new Label("Su tecla elegida es: ");
		elegida.setFont(Font.font(20));
		
		ventanaInput.getChildren().addAll(texto, cancelar, elegida ,ok);
		ventana.getChildren().add(ventanaInput);
		ventanaInput.requestFocus();
		escena.setOnKeyPressed(e -> {	presionada = e.getCode(); 
										elegida.setText("Su tecla elegida es: " + presionada.getName()); 
										ok.setDisable(false);});
	}
	
	private void aplicarCambio(KeyCode presionada, int lugarArray) {
		teclasKeyCodes.set(lugarArray, presionada);
	}

	public void setCambios() {
		ConfigJuego configActual = ConfigJuego.readFile();
		
		double width = configActual.getScreenWidth();
		double height = configActual.getScreenHeight();
		var resolucionElegida = resoluciones.getValue();
		
		if(resolucionElegida != "Resoluciones") {	
			var widthYheight = resolucionElegida.split("x");
			width = Double.parseDouble(widthYheight[0]);
			height = Double.parseDouble(widthYheight[1]);
		}
		
		var dificultadElegida = dificultades.getValue();
		var valorDificultad = valorDificultades.get(opcionesDificultades.get(1));
		if(dificultadElegida != "Dificultad") {
			valorDificultad = valorDificultades.get(dificultadElegida);
		}
		
		double volumen = sliderVolumen.getValue()/100;
		
		var teclas = teclasKeyCodes.stream().map(e -> e.getName()).collect(Collectors.toList());
		ConfigJuego nuevo = new ConfigJuego(width, height, valorDificultad, volumen, teclas);
		nuevo.writeConfigFile();
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText("Atención");
		a.setContentText("Los cambios tendran efecto la proxima vez que inice el juego");
		a.show();
	}
	
	
	
	
	
}
