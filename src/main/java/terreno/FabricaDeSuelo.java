package terreno;

import java.util.Random;
import minerales.FabricaDeMinerales;

public class FabricaDeSuelo {
	private static Bloque ponerBloque() {
		var rand = new Random();
		int valor = rand.nextInt(100);
		
		if(valor > 0 && valor < 80) {
			return new Tierra();
		} else if(valor >= 80 && valor < 88) {
			return FabricaDeMinerales.crear("Hierro");
		} else if(valor >= 88 && valor < 94) {
			return FabricaDeMinerales.crear("Bronce");
		} else if(valor >= 94 && valor < 98) {
			return FabricaDeMinerales.crear("Plata");
		} else {
			return FabricaDeMinerales.crear("Oro");
		}
	}
	
	
	public static Bloque[][] crear(int alto, int ancho) {
		var bloques = new Bloque[alto][ancho];
		for(int k = 0; k < ancho; k++) {
			bloques[0][k] = new Aire();
			bloques[1][k] = new Aire();
			bloques[2][k] = new Aire();
			bloques[3][k] = new Aire();
			bloques[4][k] = new Aire();
			bloques[5][k] = new Aire();
			bloques[6][k] = new Aire();
			bloques[7][k] = new Aire();
			bloques[8][k] = new Aire();
		}
		for(int i = 9; i < alto; i++) {
			for(int j = 0; j < ancho; j++) {
				bloques[i][j] = ponerBloque();
			}
		}
		return bloques;
	}
}
