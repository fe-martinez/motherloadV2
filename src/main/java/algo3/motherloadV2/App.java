package algo3.motherloadV2;

import javafx.application.Application;
import javafx.stage.Stage;
import jugador.Jugador;
import terreno.PisoSuperior;
import terreno.Suelo;
import tp.Juego;

public class App extends Application {
	VistaJuego vistaJuego;
	Juego juego;
	Jugador pj;
	PisoSuperior tiendas;
	Suelo suelo;
		
    @Override
    public void start(Stage stage) {
    	stage.setFullScreen(true);
    	new VistaMenu(stage);        
    }

	public static void main(String[] args) {
        launch();
    }

}