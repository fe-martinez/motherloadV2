package mejoras;

import jugador.Jugador;

public class MejoraCapacidadDelTanque extends MejoraInstantanea {
	
	public MejoraCapacidadDelTanque(double cantidad, int valor) {
		if(cantidad > 0 && valor > 0) {
			super.cantidadAMejorar = (int) cantidad;
			super.valor = valor;
		}
		else {
			super.cantidadAMejorar = 0;
			super.valor = 0;
		}
	}
	
	//Utiliza la mejora en el jugador especificado
	public void utilizar(Jugador jugador) {
		jugador.ampliarTanque(cantidadAMejorar);
	}
}
