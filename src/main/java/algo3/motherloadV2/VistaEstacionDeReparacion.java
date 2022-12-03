package algo3.motherloadV2;

import java.util.List;
import javafx.scene.Group;
import javafx.stage.Stage;
import jugador.Jugador;
import tiendas.EstacionDeReparacion;

public class VistaEstacionDeReparacion extends VistaEstacionDeMantenimiento {
	final static String PATH_FONDO = "../motherloadV2/src/rsc/Tiendas/EstacionDeReparacion/HealthStation.png";
	static List<String> keys = List.of("5","10","25","50","Fill","Close");
		
	static List<String> imagePath = List.of("../motherloadV2/src/rsc/Tiendas/EstacionDeReparacion/Botones/50.png",
			"../motherloadV2/src/rsc/Tiendas/EstacionDeReparacion/Botones/100.png",
			"../motherloadV2/src/rsc/Tiendas/EstacionDeReparacion/Botones/200.png",
			"../motherloadV2/src/rsc/Tiendas/EstacionDeReparacion/Botones/500.png",
			"../motherloadV2/src/rsc/Tiendas/EstacionDeReparacion/Botones/full.png",
			"../motherloadV2/src/rsc/Tiendas/EstacionDeReparacion/Botones/close.png");
	
	public VistaEstacionDeReparacion(Stage stage, Group root,EstacionDeReparacion estacionDeReparacion, Jugador pj) {
		super(stage,root,estacionDeReparacion,pj,PATH_FONDO,imagePath,keys);
	}

}
