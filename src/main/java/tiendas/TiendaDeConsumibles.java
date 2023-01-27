package tiendas;

import java.util.HashMap;
import java.util.Map;
import jugador.Jugador;
import jugador.Posicion;
import mejoras.MejoraDinamita;
import mejoras.MejoraExplosivos;
import mejoras.MejoraHullRepairNanobots;
import mejoras.MejoraTanqueExtra;
import mejoras.MejoraTeleport;
import mejoras.TipoUsable;
import mejoras.Usable;
import terreno.Entidad;
import terreno.TipoEntidad;

public class TiendaDeConsumibles extends Entidad {
	Posicion posicion;
	private static final char LETRA = '*';
	private static final TipoEntidad TIPO = TipoEntidad.TIENDA;
	Map<TipoUsable, Usable> usables;
	
	public TiendaDeConsumibles(Posicion posicion) {
		super(posicion, TIPO, LETRA);
		this.posicion = posicion;
		this.usables = new HashMap<>();
		inicializarConsumibles();
	}
	
	//Inicializa las mejoras en el map.
	public void inicializarConsumibles() {
		this.usables.put(TipoUsable.REPAIR, new MejoraHullRepairNanobots());
		this.usables.put(TipoUsable.TANQUE_EXTRA, new MejoraTanqueExtra());
		this.usables.put(TipoUsable.TELEPORT, new MejoraTeleport());
		this.usables.put(TipoUsable.DINAMITA, new MejoraDinamita(null));
		this.usables.put(TipoUsable.EXPLOSIVOS, new MejoraExplosivos(null));
	}
	
	//Le vende la opción dada al Jugador dado. Devuelve true si se pudo comprar, false en caso contrario.
	public boolean vender(Jugador jugador, TipoUsable opcion) {
		Usable objeto = usables.get(opcion);
		System.out.println(opcion);
		System.out.println(objeto);
		if(objeto == null) {
			return false;
		}
		
		boolean sePuedeComprar = jugador.hacerCompra(objeto.getCosto());
		if(sePuedeComprar) {
			jugador.getInventario().agregarUsable(objeto);
		}
		return sePuedeComprar;
	}

	//Permite al Jugador dado interactuar con la Tienda actual. Si en la interacción se realizó una compra, devuelve true, sino false.
	public boolean interactuar(Jugador jugador, TipoUsable opcion) {
		return vender(jugador, opcion);
	}
}
