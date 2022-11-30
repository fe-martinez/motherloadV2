package tiendas;

import java.util.Map;
import java.util.Scanner;

import algo3.motherloadV2.VistaTiendaDeMejoras;
import jugador.Jugador;
import jugador.Posicion;
import mejoras.MejoraCapacidadDelTanque;
import mejoras.MejoraInstantanea;
import mejoras.MejoraMaxInventario;
import mejoras.MejoraMaxVida;
import mejoras.Usable;
import terreno.Entidad;
import terreno.TipoEntidad;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TiendaDeMejoras extends Entidad{
	private static final char LETRA = '+';
	private static final TipoEntidad TIPO = TipoEntidad.TIENDA;
	private static final List<Integer> TIERS_PRECIO = Arrays.asList(750, 2000, 5000, 20000, 100000, 150000);
	private static final List<Integer> TIERS_MAX_CAPACIDAD = Arrays.asList(15, 25, 40, 60, 70, 120);
	Scanner input;
	Posicion posicion;
	Map<String, MejoraInstantanea> mejoras;
	Map<String, Usable> usables;
	private VistaTiendaDeMejoras vista;
	
	public TiendaDeMejoras(Posicion posicion, VistaTiendaDeMejoras vista) {
		super(posicion, TIPO, LETRA);
		this.posicion = posicion;
		this.mejoras = new HashMap<>();
		this.inicializarMejoras();
		this.vista = vista;
	}

	//Inicializa las mejoras que se pueden comprar junto con sus precios.
	private void inicializarMejoras() {
		this.mejoras.put("I1", new MejoraMaxInventario(TIERS_MAX_CAPACIDAD.get(0), TIERS_PRECIO.get(0)));
		this.mejoras.put("I2", new MejoraMaxInventario(TIERS_MAX_CAPACIDAD.get(1), TIERS_PRECIO.get(1)));
		this.mejoras.put("I3", new MejoraMaxInventario(TIERS_MAX_CAPACIDAD.get(2), TIERS_PRECIO.get(2)));
		this.mejoras.put("I4", new MejoraMaxInventario(TIERS_MAX_CAPACIDAD.get(3), TIERS_PRECIO.get(3)));
		this.mejoras.put("I5", new MejoraMaxInventario(TIERS_MAX_CAPACIDAD.get(4), TIERS_PRECIO.get(4)));
		this.mejoras.put("I6", new MejoraMaxInventario(TIERS_MAX_CAPACIDAD.get(5), TIERS_PRECIO.get(5)));
				
		this.mejoras.put("V1", new MejoraMaxVida(17, TIERS_PRECIO.get(0)));
		this.mejoras.put("V2", new MejoraMaxVida(30, TIERS_PRECIO.get(1)));
		this.mejoras.put("V3", new MejoraMaxVida(50, TIERS_PRECIO.get(2)));
		this.mejoras.put("V4", new MejoraMaxVida(80, TIERS_PRECIO.get(3)));
		this.mejoras.put("V5", new MejoraMaxVida(120, TIERS_PRECIO.get(4)));
		this.mejoras.put("V6", new MejoraMaxVida(180, TIERS_PRECIO.get(5)));
		
		this.mejoras.put("T1", new MejoraCapacidadDelTanque(TIERS_MAX_CAPACIDAD.get(0), TIERS_PRECIO.get(0)));
		this.mejoras.put("T2", new MejoraCapacidadDelTanque(TIERS_MAX_CAPACIDAD.get(1), TIERS_PRECIO.get(1)));
		this.mejoras.put("T3", new MejoraCapacidadDelTanque(TIERS_MAX_CAPACIDAD.get(2), TIERS_PRECIO.get(2)));
		this.mejoras.put("T4", new MejoraCapacidadDelTanque(TIERS_MAX_CAPACIDAD.get(3), TIERS_PRECIO.get(3)));
		this.mejoras.put("T5", new MejoraCapacidadDelTanque(TIERS_MAX_CAPACIDAD.get(4), TIERS_PRECIO.get(4)));
		this.mejoras.put("T6", new MejoraCapacidadDelTanque(TIERS_MAX_CAPACIDAD.get(5), TIERS_PRECIO.get(5)));
		
	}
	
	//Elimina la mejora según el código especificado.
	//Si no se usa borra este método xfi :P
	private void eliminarMejora(String codigo) {
		this.mejoras.remove(codigo);
	}
	
	//Permite vender la Mejora dada al Jugador dado.
	public void vender(Jugador jugador, MejoraInstantanea mejora) {
		mejora.utilizar(jugador);
		jugador.hacerCompra(mejora.getValor());	
	}

	//Permite al Jugador dado interactuar con la Tienda actual.
	@Override
	public void interactuar(Jugador jugador) {
		vista.mostrar();
		
//		String codigo = VistaTiendasConsola.mejoras();
//		MejoraInstantanea mejora = this.mejoras.get(codigo);
//		if(mejora == null) {
//			System.out.println("No tenemos esa mejora o ya la vendimos :(");
//			return;
//		}
//		vender(jugador, mejora);
//		eliminarMejora(codigo);
	}
}
