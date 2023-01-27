package vistas;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class VistaEstacionDeReparacion extends VistaEstacionDeMantenimiento {
	final static String PATH_FONDO = "src/rsc/Tiendas/EstacionDeReparacion/HealthStation.png";
	static List<String> keys = List.of("5","10","25","50","Fill","Close");
	List<Label> labels;	
	static List<String> imagePath = List.of("src/rsc/Tiendas/EstacionDeReparacion/Botones/50.png",
			"src/rsc/Tiendas/EstacionDeReparacion/Botones/100.png",
			"src/rsc/Tiendas/EstacionDeReparacion/Botones/200.png",
			"src/rsc/Tiendas/EstacionDeReparacion/Botones/500.png",
			"src/rsc/Tiendas/EstacionDeReparacion/Botones/full.png",
			"src/rsc/Tiendas/EstacionDeReparacion/Botones/close.png");
	double porcentajeHP;
	
	public VistaEstacionDeReparacion(Stage stage, Group root) {
		super(stage,root,PATH_FONDO,imagePath,keys);
		super.addLabel(new Label("Elija la cantidad de daño que desea reparar: "));
	}
	
	public void actualizarBarra(String text,double porcentaje) {
		super.actualizarBarra(text, porcentaje);
	}
}
