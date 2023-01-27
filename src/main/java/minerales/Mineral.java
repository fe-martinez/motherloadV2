package minerales;

import terreno.Bloque;

public abstract class Mineral implements Bloque {
	private int precio;
	private char letra;
	private TipoDeBloque tipo;
	private String nombre;
	
	public Mineral(int precio, char letra, TipoDeBloque tipo,String nombre) {
		this.precio = precio;
		this.letra = letra;
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	//Devuelve el precio.
	public int getPrecio() {
		return this.precio;
	}
	
	//Devuelve la letra que representa al Mineral actual.
	public char getBloqueID() {
		return this.letra;
	}
	
	public TipoDeBloque getTipoDeBloque() {
		return this.tipo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
