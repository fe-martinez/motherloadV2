package tiendas;

import java.util.Arrays;

import java.util.List;
import jugador.Jugador;
import jugador.Posicion;
import terreno.Entidad;
import terreno.TipoEntidad;

public class EstacionDeServicio extends Entidad implements EstacionDeMantenimiento {
	private static final char LETRA = '#';
	private static final TipoEntidad TIPO = TipoEntidad.TIENDA;
	private static final int CODIGO_TANQUE_LLENO = 1000;
	private static final List<Integer> LITROS_DISPONIBLES = Arrays.asList(5, 10, 25, 50, CODIGO_TANQUE_LLENO);
	private static final int PRECIO_COMBUSTIBLE = 1;
	
	public EstacionDeServicio(Posicion posicion) {
		super(posicion, TIPO, LETRA);
	}
	
	//Calcula la cantidad de combustible que se cargará efectivamente en base a
	//la cantidad elegida, la cantidad actual de combustible y el nivel máximo que se puede cargar en el tanque.
	public double cantidadDeCombustible(double cantidadCombustible, double capacidadTanque, double cantidadActual) {
		if(!LITROS_DISPONIBLES.contains((int)cantidadCombustible)) {
			return -1;

		}
		
		int i = LITROS_DISPONIBLES.indexOf((int)cantidadCombustible);
		if(LITROS_DISPONIBLES.get(i) == CODIGO_TANQUE_LLENO) {
			return (capacidadTanque - cantidadActual) * EstacionDeServicio.PRECIO_COMBUSTIBLE;
		}
		
		double faltante = capacidadTanque - cantidadActual;
		double cantidadCargar = faltante < cantidadCombustible ? faltante: cantidadCombustible;
		return cantidadCargar;
	}
		
	//Permite vender a un jugador dado una cantidad dada de combustible. Devuelve true si se pudo comprar, false en caso contrario.
	public boolean vender(Jugador jugador, double cantidad) {
		double cantidadCombustible = cantidadDeCombustible(cantidad, jugador.getNave().getCapacidadTanque(), jugador.getNave().getNivelDeCombustible());
		if(cantidadCombustible == -1) {
			return false;
		}
		
		boolean sePuedeComprar = false;
		if(jugador.hacerCompra(cantidadCombustible)) {
			sePuedeComprar = jugador.getNave().cargarCombustible(cantidadCombustible, cantidad);
		}
		return sePuedeComprar;
	}
}
