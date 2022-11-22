package tp;

import jugador.Jugador;
import terreno.PisoSuperior;
import terreno.Suelo;

public class Main {
	public static final int ALTURA = 30;
	public static final int ANCHO = 20;

	public static void main(String[] args) {
		Suelo suelo = new Suelo(ALTURA,ANCHO);
		PisoSuperior tiendas = new PisoSuperior();
		Jugador pj = new Jugador(5, 0, ALTURA, ANCHO);
		Juego juego = new Juego(suelo, tiendas, pj);
		System.out.println("El juego está a punto de comenzar...");
	}

}
