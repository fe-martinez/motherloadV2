package mejoras;

import jugador.Jugador;
//Mejora que se acciona al momento de la compra.
public abstract class MejoraInstantanea {
	public int cantidadAMejorar;
	public int valor;
	
	public abstract void utilizar(Jugador jugador);
	
	public int getValor() {
		return this.valor;
	}
}
