package mejoras;

import jugador.Jugador;
import jugador.Posicion;
import terreno.Suelo;

public class MejoraExplosivos extends Usable {
	public static int VALOR = 5000;
	public static char LETRA = 'C';
	public static TipoUsable TIPO = TipoUsable.EXPLOSIVOS;
	private Suelo suelo;
	
	public MejoraExplosivos(Suelo suelo) {
		super(LETRA, TIPO, VALOR);
		this.suelo = suelo;
	}
	
	//Rompe los bloques a distancia 2 del pj.
	@Override
	public void utilizar(Jugador jugador) {
		Posicion actual = jugador.getPosicion();
		Posicion aRomper = new Posicion(0, 0);
		for(int i = -2; i < 3; i++) {
			for(int j = -2; j < 3; j++) {
				aRomper.set(actual.getX() + i, actual.getY() + j);
				suelo.destruirBloque(aRomper);
			}
		}
	}
}
