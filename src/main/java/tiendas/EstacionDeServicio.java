package tiendas;

import java.util.Arrays;
import java.util.List;

import algo3.motherloadV2.VistaEstacionDeServicio;
import jugador.Jugador;
import jugador.Posicion;
import terreno.Entidad;
import terreno.TipoEntidad;

public class EstacionDeServicio extends Entidad implements EstacionDeMantenimiento {
	private static final char LETRA = '#';
	private static final TipoEntidad TIPO = TipoEntidad.TIENDA;
	private static final List<Integer> LITROS_DISPONIBLES = Arrays.asList(5, 10, 25, 50, 100);
	private static final int PRECIO_COMBUSTIBLE = 1;
	private int cantidad;

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
		if(LITROS_DISPONIBLES.get(i) == 100) {
			return (capacidadTanque - cantidadActual) * EstacionDeServicio.PRECIO_COMBUSTIBLE;
		}
		
		double faltante = capacidadTanque - cantidadActual;
		double cantidadCargar = faltante < cantidadCombustible ? faltante: cantidadCombustible;
		return cantidadCargar;
	}
	
	//Calcula la cantidad de plata que se gastará en base a la cantidad de combustible que se carga.
	public double cantidadDeDinero(double cantidadDeCombustible) {
		return cantidadDeCombustible * EstacionDeServicio.PRECIO_COMBUSTIBLE;
	}
	
	//Permite vender a un jugador dado una cantidad dada de combustible.
	public void vender(Jugador jugador, double cantidad) {
		double cantidadCombustible = cantidadDeCombustible(cantidad, jugador.getNave().getCapacidadTanque(), jugador.getNave().getNivelDeCombustible());
		if(cantidadCombustible == -1) {
			return;
		}
		
		if(jugador.hacerCompra(cantidadCombustible)) {
			jugador.getNave().cargarCombustible(cantidadCombustible, cantidad);
		}
	}

	//Realiza la interacción del Jugador dado con la Tienda actual.
	public void interactuar(Jugador jugador, int cantidad) {
		if(EstacionDeServicio.LITROS_DISPONIBLES.contains(this.cantidad)){
			vender(jugador,this.cantidad);
		}
	}
}
