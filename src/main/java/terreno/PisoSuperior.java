package terreno;

import java.util.Map;

import jugador.Jugador;
import jugador.Posicion;
import tiendas.EstacionDeReparacion;
import tiendas.EstacionDeServicio;
import tiendas.EstacionDeVenta;
import tiendas.TiendaDeConsumibles;
import tiendas.TiendaDeMejoras;
import vistas.VistaJuego;

import java.util.HashMap;

public class PisoSuperior {
	Map<Integer, Entidad> tiendas;
	Jugador pj;
	
	public PisoSuperior(Jugador pj){
		this.pj = pj;
		this.crearConfiguracion();
	}

	//Crear una configuracion especificada.
	public void crearConfiguracion() {
		var ypf = new EstacionDeServicio(new Posicion((int)(VistaJuego.COLUMNAS * 0.3), 0));
		
		var mecanico = new EstacionDeReparacion(new Posicion((int)(VistaJuego.COLUMNAS * 0.6), 0));
		
		var mejoras = new TiendaDeMejoras(new Posicion((int)(VistaJuego.COLUMNAS * 0.9), 0));
		
		var consumibles = new TiendaDeConsumibles(new Posicion((int)(VistaJuego.COLUMNAS * 0.7), 0));
		
		var estacionDeVentas = new EstacionDeVenta(new Posicion((int)(VistaJuego.COLUMNAS * 0.4), 0));

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
	
	public Entidad getTiendaTipo(TipoEntidad tipo) {
		for (var entry : tiendas.entrySet()) {
		    if(entry.getValue().getTipoEntidad() == tipo) {
		    	return entry.getValue();
		    }
		}
		return null;
	}
}
