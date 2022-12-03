package jugador;

import mejoras.Usable;

public class AccionItem implements Accion{
	private Jugador pj;
	private Usable mejora;
	
	public AccionItem(Jugador pj, Usable mejora) {
		this.pj = pj;
		this.mejora = mejora;
	}

	@Override
	//Permite aplicar la mejora al jugador.
	public boolean aplicar() {
		if(pj.getInventario().tieneUsable(mejora)) {
			mejora.utilizar(pj);
			pj.getInventario().eliminarUsable(mejora);
			return true;
		}
		return false;
	}
}
