package terreno;

import jugador.Jugador;
import jugador.Posicion;

public abstract class Entidad {
	private Posicion posicion;
	private TipoEntidad tipo;
	private char letra;
	
	public Entidad(Posicion posicion, TipoEntidad tipo, char letra) {
		this.posicion = posicion;
		this.tipo = tipo;
		this.letra = letra;
	}

	public boolean interactuar(Jugador jugador) {
		return false;
	}
	
	public Posicion getPosicion() {
		return this.posicion;
	}
	
	public TipoEntidad getTipoEntidad() {
		return this.tipo;
	}
	
	public char getEntidadID() {
		return this.letra;
	}

	public boolean interactuar(Jugador jugador, char opcion) {
		return false;
	}
}
