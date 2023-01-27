package vistas;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tiendas.EstacionDeServicio;

public class VistaEstacionDeServicio extends VistaEstacionDeMantenimiento {
	final static String PATH_FONDO = "src/rsc/Fuel-512.png";
	static List<String> keys = List.of("5","10","25","50","Fill","Close");
	EstacionDeServicio tienda;

	static List<String> imagePath = List.of("src/rsc/Tiendas/Botones/button5.png",
			"src/rsc/Tiendas/Botones/button10.png",
			"src/rsc/Tiendas/Botones/button25.png",
			"src/rsc/Tiendas/Botones/button50.png",
			"src/rsc/Tiendas/Botones/fill.png",
			"src/rsc/Tiendas/Botones/close.png");
	
	double porcentajeNafta;
	
	public VistaEstacionDeServicio(Stage stage, Group root) {
		super(stage,root,PATH_FONDO,imagePath,keys);
		super.addLabel(new Label("Elija la cantidad de combustible que desea cargar: "));
	}

	public void actualizarBarra(String text, double porcentaje) {
		super.actualizarBarra(text, porcentaje);
	}
	

}
