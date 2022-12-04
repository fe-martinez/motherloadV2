package terreno;

import java.util.Random;
import minerales.FabricaDeMinerales;

public class FabricaDeSuelo {
	private static Bloque ponerBloque() {
		var rand = new Random();
		int valor = rand.nextInt(200);
		
		if(valor > 0 && valor < 150) {
			return new Tierra();
		} else if(valor >= 150 && valor < 168) {
			return FabricaDeMinerales.crear("Hierro");
		} else if(valor >= 168 && valor < 182) {
			return FabricaDeMinerales.crear("Cobre");
		} else if(valor >= 182 && valor < 190) {
			return FabricaDeMinerales.crear("Bronce");
		} else if(valor >= 190 && valor < 196) {
			return FabricaDeMinerales.crear("Oro");
		} else {
			return FabricaDeMinerales.crear("Diamante");
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
			bloques[9][k] = new Tierra();
		}
		for(int i = 10; i < alto; i++) {
			for(int j = 0; j < ancho; j++) {
				bloques[i][j] = ponerBloque();
			}
		}
		return bloques;
	}


	private static Bloque convertirChar(char id) {
		if(id == 'T') {
			return new Tierra();
		} else if(id == ' ') {
			return new Aire();
		} else if(id == 'B') {
			return FabricaDeMinerales.crear("Bronce");
		} else if(id == 'H') {
			return FabricaDeMinerales.crear("Hierro");
		} else if(id == 'P') {
			return FabricaDeMinerales.crear("Plata");
		} else if(id == 'O') {
			return FabricaDeMinerales.crear("Oro");
		} else if(id == 'C') {
			return FabricaDeMinerales.crear("Cobre");
		} else if(id == 'D') {
			return FabricadeMinerales.crear("Diamante");
		}
		return new Tierra();
	}


	public static Bloque[][] crearDesdeChars(int alto, int ancho, char[][] mapa){
		var bloques = new Bloque[alto][ancho];

		for(int i = 0; i < alto; i++) {
			for(int j = 0; j < ancho; j++) {
				bloques[i][j] = convertirChar(mapa[i][j]);
			}
		}

		return bloques;
	}
}
