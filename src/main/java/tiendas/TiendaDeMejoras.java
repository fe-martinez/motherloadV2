package tiendas;

import java.util.Map;
import java.util.Scanner;
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
	private static final List<Integer> TIERS_MAX_VIDA = Arrays.asList(17, 30, 50, 80, 120, 180);
	private static final List<String> CODIGO_MEJORAS_INVENTARIO = List.of("I1","I2","I3","I4","I5","I6");
	private static final List<String> CODIGO_MEJORAS_VIDA = List.of("V1","V2","V3","V4","V5","V6");
	private static final List<String> CODIGO_MEJORAS_TANQUE = List.of("T1","T2","T3","T4","T5","T6");
	private static final int CANTIDAD_DE_MEJORAS = 6;
	
	Scanner input;
	Posicion posicion;
	Map<String, MejoraInstantanea> mejoras;
	Map<String, Usable> usables;
	
	public TiendaDeMejoras(Posicion posicion) {
		super(posicion, TIPO, LETRA);
		this.posicion = posicion;
		this.mejoras = new HashMap<>();
		this.inicializarMejoras();
	}

	//Inicializa las mejoras que se pueden comprar junto con sus precios.
	private void inicializarMejoras() {
		for(int i = 0; i < CANTIDAD_DE_MEJORAS; i++) {
			this.mejoras.put(CODIGO_MEJORAS_INVENTARIO.get(i),new MejoraMaxInventario(TIERS_MAX_CAPACIDAD.get(i), TIERS_PRECIO.get(i)));
			this.mejoras.put(CODIGO_MEJORAS_VIDA.get(i), new MejoraMaxVida(TIERS_MAX_VIDA.get(i), TIERS_PRECIO.get(i)));
			this.mejoras.put(CODIGO_MEJORAS_TANQUE.get(i), new MejoraCapacidadDelTanque(TIERS_MAX_CAPACIDAD.get(i), TIERS_PRECIO.get(i)));
		}

	}
	
	private void remove(int index,List<String> lista) {
		if(index != -1) {
			for(int i = 0; i <= index; i++) {
				this.mejoras.remove(lista.get(i));
			}
		}
	}
	
	//Elimina la mejora según el código especificado.
	private void eliminarMejora(String codigo) {
		int index1,index2,index3;
		index1 = CODIGO_MEJORAS_INVENTARIO.indexOf(codigo);
		index2 = CODIGO_MEJORAS_VIDA.indexOf(codigo);
		index3 = CODIGO_MEJORAS_TANQUE.indexOf(codigo);
		this.remove(index1,CODIGO_MEJORAS_INVENTARIO);
		this.remove(index2,CODIGO_MEJORAS_VIDA);
		this.remove(index3,CODIGO_MEJORAS_TANQUE);		
	}
	
	//Permite vender la Mejora dada al Jugador dado. Devuelve true si se pudo comprar, false en caso contrario.
	public boolean vender(Jugador jugador, MejoraInstantanea mejora) {
		if(jugador.hacerCompra(mejora.getValor())){
			mejora.utilizar(jugador);		
			return true;
		}
		return false;
	}

	//Permite al Jugador dado interactuar con la Tienda actual. Devuelve true si la interacción tuvo por resultado la compra exitosa, false en caso contrario.
	public boolean interactuar(Jugador jugador, String codigo) {
		MejoraInstantanea mejora = this.mejoras.get(codigo);
		if(mejora == null) {
			return false;
		}
		if(vender(jugador, mejora)) {
			eliminarMejora(codigo);	
			return true;
		}
		return true;
	}
}
