package terreno;

import java.util.Map;

import algo3.motherloadV2.VistaEstacionDeReparacion;
import algo3.motherloadV2.VistaEstacionDeServicio;
import algo3.motherloadV2.VistaEstacionDeVenta;
import algo3.motherloadV2.VistaJuego;
import algo3.motherloadV2.VistaTiendaDeConsumibles;
import algo3.motherloadV2.VistaTiendaDeMejoras;
import javafx.scene.Group;
import javafx.stage.Stage;
import jugador.Posicion;
import tiendas.EstacionDeReparacion;
import tiendas.EstacionDeServicio;
import tiendas.EstacionDeVenta;
import tiendas.TiendaDeConsumibles;
import tiendas.TiendaDeMejoras;
import java.util.HashMap;

public class PisoSuperior {
	Map<Integer, Entidad> tiendas;

	public PisoSuperior(Stage stage, Group root){
		this.crearConfiguracion(stage, root);
	}

	//Crear una configuracion especificada.
	public void crearConfiguracion(Stage stage, Group root) {
		var vistaYPF = new VistaEstacionDeServicio(stage, root);
		var ypf = new EstacionDeServicio(new Posicion((int)(VistaJuego.COLUMNAS * 0.3), 0), vistaYPF);
		
		var vistaMecanico = new VistaEstacionDeReparacion();
		var mecanico = new EstacionDeReparacion(new Posicion((int)(VistaJuego.COLUMNAS * 0.6), 0), vistaMecanico);
		
		var vistaMejoras = new VistaTiendaDeMejoras(stage);
		var mejoras = new TiendaDeMejoras(new Posicion((int)(VistaJuego.COLUMNAS * 0.9), 0), vistaMejoras);
		
		var vistaConsumibles = new VistaTiendaDeConsumibles(stage);
		var consumibles = new TiendaDeConsumibles(new Posicion((int)(VistaJuego.COLUMNAS * 0.7), 0), vistaConsumibles);
		
		var vistaVentas = new VistaEstacionDeVenta(stage, root);
		var estacionDeVentas = new EstacionDeVenta(new Posicion((int)(VistaJuego.COLUMNAS * 0.4), 0), vistaVentas);

		this.tiendas = new HashMap<Integer, Entidad>();
	
		tiendas.put((int)ypf.getPosicion().getX(), ypf);
		tiendas.put((int)mecanico.getPosicion().getX(), mecanico);
		tiendas.put((int)mejoras.getPosicion().getX(), mejoras);
		tiendas.put((int)consumibles.getPosicion().getX(), consumibles);
		tiendas.put((int)estacionDeVentas.getPosicion().getX(),estacionDeVentas);
	}
	
	//Devuelve el diccionario de las tiendas
	public Map<Integer, Entidad> getTiendas(){
		return this.tiendas;
	}
	
	public Entidad getTiendaPos(int posX) {
		return tiendas.get(posX);
	}
	
	public Entidad colisionEntidad(Posicion pos) {
		if(this.tiendas != null) {
			return this.getTiendaPos((int)pos.getX());
		}
		return null;
	}
}
