package mejoras;

import java.util.Random;

import jugador.Jugador;
import tp.Main;

public class MejoraTeleport extends Usable {
	public static int VALOR = 2000;
	public static char LETRA = 'Q';
	public static TipoUsable TIPO = TipoUsable.TELEPORT;

	public MejoraTeleport() {
		super(LETRA, TIPO, VALOR);
	}
	
	public void utilizar(Jugador jugador) {
		jugador.setX(new Random().nextInt(Main.ANCHO));
		jugador.setY(0); //Siempre es a la superficie.
	}
}

