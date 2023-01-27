package jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mejoras.Usable;
import minerales.Mineral;
import minerales.TipoDeBloque;

public class Inventario{
	private static final int MAX_INVENTARIO_INICIAL = 7;
	private List<Mineral> mineralesRecolectados;
	private List<Usable> usables;
	private int maxInventario;
	
	public Inventario() {
		this.mineralesRecolectados = new ArrayList<Mineral>();
		this.usables = new ArrayList<Usable>();
		this.maxInventario = MAX_INVENTARIO_INICIAL;
	}
	
	//------------------------------------------------
	//          		MINERALES
	//------------------------------------------------

	//Permite vender los minerales, recogiendo el dinero correspondiente a cada uno de ellos.
	public int venderMinerales() {
		int total = 0;
		for(Mineral mineral: mineralesRecolectados) {
			total += mineral.getPrecio();
		}
		this.mineralesRecolectados.clear();
		return total;
	}
	
	private void ordenarMinerales() {
		mineralesRecolectados = mineralesRecolectados.stream().sorted((x,y) -> x.getPrecio()-y.getPrecio()).collect(Collectors.toList());
	}
	
	//Permite agregar un Mineral dado al Inventario.
	public boolean agregarInventario(Mineral mineral) {
		if(mineralesRecolectados.size() < this.maxInventario) {
			mineralesRecolectados.add(mineral);
			this.ordenarMinerales();
			return true;
		}
		return false;		
	}
	
	//Devuelve la lista de minerales recolectados.
	public List<Mineral> getMinerales() {
		return this.mineralesRecolectados;
	}
	
	//Devuelve la cantidad de minerales que el Jugador tiene en su inventario.
	public int getCantidadDeMinerales() {
		return this.mineralesRecolectados.size();
	}
	
	//Devuelve el máximo del Inventario del Jugador.
	public int getMaxInventario() {
		return this.maxInventario;
	}
	
	//------------------------------------------------
	//          		MEJORAS
	//------------------------------------------------

	//Devuelve la lista de Mejoras del Jugador.
	public List<Usable> getUsables() {
		return this.usables;
	}
	
	//Devuelve true si el usable dado está en la lista de Mejoras del Jugador, false en caso contrario.
	public boolean tieneUsable(Usable item) {
		for(Usable usable: this.usables) {
			if(item.getTipo() == usable.getTipo()) {
				return true;
			}
		}
		return false;
	}
	
	//Permite agregar una mejora a la lista del Jugador.
	public void agregarUsable(Usable item) {
		this.usables.add(item);
	}
	

	//Permite cambiar el máximo del inventario si el valor ingresado es mayor al que se tenía antes, sino no hace nada.
	public void setMaxInventario(int maxInventario) {
		if(maxInventario > this.maxInventario) {
			this.maxInventario = maxInventario;
		}
		else {
			return;
		}
	}
	
	//Busca un usable y si está, lo remueve.
	public void eliminarUsable(Usable buscado) {		
		usables.remove(0);
	}
	
	public TipoDeBloque getTipoDeMineral(int posicion) {
		return this.mineralesRecolectados.get(posicion).getTipoDeBloque();
	}
	
	public char getIDMineral(int posicion) {
		return this.mineralesRecolectados.get(posicion).getBloqueID();
	}
	
	public String getNombreMineral(int posicion) {
		return this.mineralesRecolectados.get(posicion).getNombre();
	}
	
	public int getPrecioMineral(int posicion) {
		return this.mineralesRecolectados.get(posicion).getPrecio();
	}
	
	public boolean inventarioVacio() {
		return this.mineralesRecolectados.size() == 0;
	}

}
