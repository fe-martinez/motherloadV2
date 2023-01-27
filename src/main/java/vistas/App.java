package vistas;

import javafx.application.Application;
import javafx.stage.Stage;
import jugador.Jugador;
import terreno.PisoSuperior;
import terreno.Suelo;
import tp.Alertas;
import tp.Juego;
import tp.Sonidos;

public class App extends Application {
	VistaJuego vistaJuego;
	Juego juego;
	Jugador pj;
	PisoSuperior tiendas;
	Suelo suelo;
		
    @Override
    public void start(Stage stage) {
    	Sonidos.cargarSonidos();
    	Alertas.cargarAlertas();
    	stage.setResizable(false);
    	new VistaMenu(stage);        
    }

	public static void main(String[] args) {
        launch();
    }

}