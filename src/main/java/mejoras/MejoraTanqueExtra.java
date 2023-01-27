package mejoras;

import jugador.Jugador;

public class MejoraTanqueExtra extends Usable {
	private static final char LETRA = 'F';
	public static final int VALOR = 2000;
	private static final TipoUsable TIPO = TipoUsable.TANQUE_EXTRA;
	private static final double CANTIDAD_COMBUSTIBLE = 25.0;
	
	private double cantidadCombustible;
	
	public MejoraTanqueExtra() {
		super(LETRA, TIPO, VALOR);
		this.cantidadCombustible = CANTIDAD_COMBUSTIBLE;
	}
	//Carga combustible sin pasar por la estacion de servicio.
	public void utilizar(Jugador jugador) {
		jugador.getNave().cargarCombustible(cantidadCombustible, 0);
	}
}
