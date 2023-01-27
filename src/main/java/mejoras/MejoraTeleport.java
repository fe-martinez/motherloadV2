package mejoras;

import java.util.Random;

import jugador.Jugador;
import vistas.VistaJuego;

public class MejoraTeleport extends Usable {
	public static int VALOR = 2000;
	public static char LETRA = 'Q';
	public static TipoUsable TIPO = TipoUsable.TELEPORT;
	public static final int COORD_Y_SUPERFICIE = 8;
	
	public MejoraTeleport() {
		super(LETRA, TIPO, VALOR);
	}
	
	public void utilizar(Jugador jugador) {
		jugador.setX(new Random().nextInt((int)VistaJuego.COLUMNAS));
		jugador.setY(COORD_Y_SUPERFICIE); //Siempre es a la superficie.
	}
}

