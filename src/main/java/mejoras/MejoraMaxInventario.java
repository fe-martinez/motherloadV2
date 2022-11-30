package mejoras;

import jugador.Jugador;

public class MejoraMaxInventario extends MejoraInstantanea {

	public MejoraMaxInventario(int cantidad,int valor) {
		if(cantidad > 0 && valor > 0) {
			super.cantidadAMejorar = cantidad;
			super.valor = valor;
		}
		else {
			super.cantidadAMejorar = 0;
			super.valor = 0;
		}
	}
	
	public void utilizar(Jugador jugador) {
		jugador.ampliarInventario(super.cantidadAMejorar);
	}

}
