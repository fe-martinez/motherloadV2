package algo3.motherloadV2;

import java.util.List;
import javafx.scene.Group;
import javafx.stage.Stage;
import jugador.Jugador;
import tiendas.EstacionDeServicio;

public class VistaEstacionDeServicio extends VistaEstacionDeMantenimiento {
	final static String PATH_FONDO = "../motherloadV2/src/rsc/Fuel-512.png";
	static List<String> keys = List.of("5","10","25","50","Fill","Close");
	EstacionDeServicio tienda;

	static List<String> imagePath = List.of("../motherloadV2/src/rsc/Tiendas/Botones/button5.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/button10.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/button25.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/button50.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/fill.png",
			"../motherloadV2/src/rsc/Tiendas/Botones/close.png");
	
	public VistaEstacionDeServicio(Stage stage, Group root,EstacionDeServicio tienda, Jugador pj) {
		super(stage,root,tienda,pj,PATH_FONDO,imagePath,keys);
	}

}
