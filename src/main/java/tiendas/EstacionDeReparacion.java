package tiendas;

import jugador.Jugador;
import jugador.Posicion;
import terreno.Entidad;
import terreno.TipoEntidad;

public class EstacionDeReparacion extends Entidad implements EstacionDeMantenimiento {
	public Posicion posicion;
	private static final int PRECIO_REPARACION = 10;
	private static final char LETRA = '!';
	private static final TipoEntidad TIPO = TipoEntidad.TIENDA;
	private static final int CODIGO_REPAIR_TOTAL = 1000;
	
	public EstacionDeReparacion(Posicion posicion) {
		super(posicion, TIPO, LETRA);
	}

	//Repara la nave del Jugador elegido según la opción de gasto elegido. Devuelve true si se pudo comprar, false en caso contrario.
	public boolean vender(Jugador jugador, double gasto) {
		double hpPedidos = gasto/PRECIO_REPARACION;
		double hpFaltante = jugador.getNave().getMaxHP() - jugador.getNave().getHP();
		double hpReparados = 0;
		if(gasto == CODIGO_REPAIR_TOTAL) {
			hpReparados = hpFaltante;
		} else {
			hpReparados = hpPedidos < hpFaltante ? hpPedidos : hpFaltante;
		}
		
		double gastoEfectivo = hpReparados * PRECIO_REPARACION;
		
		boolean sePuedeComprar = false;
		if(jugador.hacerCompra(gastoEfectivo)) {
			sePuedeComprar = jugador.getNave().repararDmg((int)hpReparados);
		}
		
		return sePuedeComprar;
	}

}
