package algo3.motherloadV2;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.stage.Stage;
import jugador.Jugador;
import terreno.Entidad;
import terreno.PisoSuperior;
import tiendas.EstacionDeServicio;
import tiendas.TiendaDeConsumibles;

public class VistasTiendas {
	private PisoSuperior tiendas;
	private Jugador pj;
	private Stage stage;
	private Group root;
	
	static Map<Entidad, VistaEntidad> vistasTiendas;
	
	public VistasTiendas(PisoSuperior tiendas, Jugador pj, Stage stage, Group root) {
		this.tiendas = tiendas;
		this.pj = pj;
		this.stage = stage;
		this.root = root;
		this.vistasTiendas = new HashMap<Entidad, VistaEntidad>();
		
		var ypf = new VistaEstacionDeServicio(stage, root, (EstacionDeServicio) tiendas.getTiendas().get((int)(VistaJuego.COLUMNAS * 0.3)), pj);
		vistasTiendas.put(tiendas.getTiendas().get((int)(VistaJuego.COLUMNAS * 0.3)), ypf);
		
		var mecanico = new VistaEstacionDeReparacion();
		vistasTiendas.put(tiendas.getTiendas().get((int)(VistaJuego.COLUMNAS * 0.6)), mecanico);
		
		var mejoras = new VistaTiendaDeMejoras(stage);
		vistasTiendas.put(tiendas.getTiendas().get((int)(VistaJuego.COLUMNAS * 0.9)), mejoras);
		
		var consumibles = new VistaTiendaDeConsumibles(stage, root, pj, (TiendaDeConsumibles) tiendas.getTiendas().get((int)(VistaJuego.COLUMNAS * 0.7)));
		vistasTiendas.put(tiendas.getTiendas().get((int)(VistaJuego.COLUMNAS * 0.7)), consumibles);
		
		var estacionDeVentas = new VistaEstacionDeVenta(stage, root, pj);
		vistasTiendas.put(tiendas.getTiendas().get((int)(VistaJuego.COLUMNAS * 0.4)), estacionDeVentas);
	}
	
	public static boolean buscarPisada(Entidad pisada) {
		vistasTiendas.get(pisada).mostrar();
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}
