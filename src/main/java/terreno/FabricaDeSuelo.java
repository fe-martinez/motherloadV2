package terreno;

import java.util.Random;
import minerales.FabricaDeMinerales;

public class FabricaDeSuelo {
	private static Bloque ponerBloque(int altura, int maxAlto) {
		int rangoTierra, rangoHierro, rangoCobre, rangoBronce, rangoOro;
		
		if(altura < maxAlto/5) {
			rangoTierra = 875;
			rangoHierro = 930;
			rangoCobre = 975;
			rangoBronce = 990;
			rangoOro = 998;
		} else if(altura < maxAlto/4) {
			rangoTierra = 830;
			rangoHierro = 890;
			rangoCobre = 935;
			rangoBronce = 978;
			rangoOro = 995;
		} else if(altura < maxAlto/3) {
			rangoTierra = 790;
			rangoHierro = 850;
			rangoCobre = 900;
			rangoBronce = 950;
			rangoOro = 980;
		} else if(altura < maxAlto/2) {
			rangoTierra = 790;
			rangoHierro = 850;
			rangoCobre = 900;
			rangoBronce = 950;
			rangoOro = 980;
		} else{
			rangoTierra = 700;
			rangoHierro = 750;
			rangoCobre = 830;
			rangoBronce = 900;
			rangoOro = 960;
		}
		
		var rand = new Random();
		int valor = rand.nextInt(1000);

		if(valor > 0 && valor < rangoTierra) {
			return new Tierra();
		} else if(valor >= rangoTierra && valor < rangoHierro) {
			return FabricaDeMinerales.crear("Hierro");
		} else if(valor >= rangoHierro && valor < rangoCobre) {
			return FabricaDeMinerales.crear("Cobre");
		} else if(valor >= rangoCobre && valor < rangoBronce) {
			return FabricaDeMinerales.crear("Bronce");
		} else if(valor >= rangoBronce && valor < rangoOro) {
			return FabricaDeMinerales.crear("Oro");
		} else {
			return FabricaDeMinerales.crear("Diamante");
		}
	}
	
	
	public static Bloque[][] crear(int alto, int ancho) {
		var bloques = new Bloque[alto][ancho];
		for(int k = 0; k < ancho; k++) {
			for(int l = 0; l < 9; l++) {
				bloques[l][k] = new Aire();
			}
			bloques[9][k] = new Tierra();
		}
		
		for(int i = 10; i < alto; i++) {
			for(int j = 0; j < ancho; j++) {
				bloques[i][j] = ponerBloque(i, alto);
			}
		}
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < ancho; j++) {
				bloques[alto - i - 1][j] = new Aire();
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
			return FabricaDeMinerales.crear("Diamante");
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
