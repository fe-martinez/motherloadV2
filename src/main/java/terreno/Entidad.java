package terreno;

import algo3.motherloadV2.VistaTiendaDeConsumibles;
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

	public void interactuar(Jugador jugador) {
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

	public void interactuar(Jugador jugador, char opcion) {
		
	}
}
