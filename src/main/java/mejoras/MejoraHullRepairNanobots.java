package mejoras;

import jugador.Jugador;

public class MejoraHullRepairNanobots extends Usable {
	public static int VALOR = 7500;
	private static int VIDA_REGENERADA = 30;
	public static char LETRA = 'R';
	private static TipoUsable TIPO = TipoUsable.REPAIR;
	
	public MejoraHullRepairNanobots() {
		super(LETRA, TIPO, VALOR);
	}
	
	public void utilizar(Jugador jugador) {
		jugador.getNave().repararDmg(MejoraHullRepairNanobots.VIDA_REGENERADA);
	}
}
